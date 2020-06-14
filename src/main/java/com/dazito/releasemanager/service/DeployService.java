package com.dazito.releasemanager.service;

import com.dazito.releasemanager.data.entity.DeployEntity;
import com.dazito.releasemanager.data.entity.SystemVersionEntity;
import com.dazito.releasemanager.repository.DeployRepository;
import com.dazito.releasemanager.repository.SystemVersionRepository;
import com.dazito.releasemanager.data.dto.request.DeployDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DeployService {
    private final DeployRepository deployRepository;
    private final SystemVersionRepository systemVersionRepository;

    public int deploy(DeployDto deployDto) {
        final SystemVersionEntity currentDeploymentVersion = systemVersionRepository.findCurrentDeploymentVersion()
                .orElseGet(() -> new SystemVersionEntity(null, new Timestamp(Instant.now().toEpochMilli()), new ArrayList<>()));

        final List<DeployEntity> deployEntityList = currentDeploymentVersion.getDeployEntityList();

        // Check if we are redeploying the same version as in production
        if (isAlreadyDeployed(deployEntityList, deployDto)) {
            log.info("serviceName:{}|serviceVersion:{}|This service is already deployed and live in production",
                    deployDto.getName(), deployDto.getVersion());
            return currentDeploymentVersion.getId();
        }

        // It is not the current deployed version
        final SystemVersionEntity newSystemVersionEntity = systemVersionRepository.save(
                new SystemVersionEntity(null, new Timestamp(Instant.now().toEpochMilli()), null)
        );
        final DeployEntity deployEntity = new DeployEntity(null, deployDto.getName(), deployDto.getVersion(), newSystemVersionEntity);
        final List<DeployEntity> deployEntityListToPersist = processDeployEntityListToPersist(deployEntityList, deployDto, newSystemVersionEntity);

        deployRepository.saveAll(deployEntityListToPersist);
        deployRepository.save(deployEntity);

        log.info("serviceId:{}|serviceName:{}|systemVersion:{}|systemVersionTs:{}|Deployment persisted",
                deployEntity.getId(), deployEntity.getName(), deployEntity.getSystemVersionEntity().getId(), deployEntity.getSystemVersionEntity().getTs());
        return newSystemVersionEntity.getId();
    }

    private boolean isAlreadyDeployed(List<DeployEntity> deployEntityList, DeployDto deployDto) {
        return deployEntityList.stream()
                .filter(deployEntity -> filterAlreadyDeployed(deployEntity, deployDto))
                .map(deployEntity -> true)
                .findFirst()
                .orElse(false);
    }

    private boolean filterAlreadyDeployed(DeployEntity deployEntity, DeployDto deployDto) {
        return  deployEntity.getName().equalsIgnoreCase(deployDto.getName()) &&
                deployEntity.getVersion() == deployDto.getVersion();
    }

    private List<DeployEntity> processDeployEntityListToPersist(List<DeployEntity> deployEntityList, DeployDto deployDto,
                                                                SystemVersionEntity newSystemVersionEntity) {
        return deployEntityList.stream()
                .filter(entity -> !entity.getName().equalsIgnoreCase(deployDto.getName()))
                .map(entity -> new DeployEntity(null, entity.getName(), entity.getVersion(), newSystemVersionEntity))
                .collect(Collectors.toList());
    }
}

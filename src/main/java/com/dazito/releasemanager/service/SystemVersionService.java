package com.dazito.releasemanager.service;

import com.dazito.releasemanager.data.entity.SystemVersionEntity;
import com.dazito.releasemanager.repository.SystemVersionRepository;
import com.dazito.releasemanager.data.dto.request.DeployDto;
import com.dazito.releasemanager.data.dto.response.SystemVersionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SystemVersionService {
    private final SystemVersionRepository systemVersionRepository;

    public List<SystemVersionDto> getAllSystemVersions() {
        return systemVersionRepository.findAll()
                .stream()
                .map(this::mapSystemVersionEntityToSystemVersionDto)
                .collect(Collectors.toList());
    }

    private SystemVersionDto mapSystemVersionEntityToSystemVersionDto(SystemVersionEntity systemVersionEntity) {
        final List<DeployDto> deployDtoList = systemVersionEntity.getDeployEntityList()
                .stream()
                .map(deployEntity -> new DeployDto(deployEntity.getName(), deployEntity.getVersion()))
                .collect(Collectors.toList());

        return new SystemVersionDto(systemVersionEntity.getId(), systemVersionEntity.getTs().toLocalDateTime(), deployDtoList);
    }

    public SystemVersionDto getSystemVersion(Integer systemVersion) {
        final SystemVersionEntity systemVersionEntity = systemVersionRepository.findById(systemVersion).orElseGet(SystemVersionEntity::new);
        return mapSystemVersionEntityToSystemVersionDto(systemVersionEntity);
    }
}

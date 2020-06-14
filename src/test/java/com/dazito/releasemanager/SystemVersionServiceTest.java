package com.dazito.releasemanager;

import com.dazito.releasemanager.repository.SystemVersionRepository;
import com.dazito.releasemanager.data.dto.response.SystemVersionDto;
import com.dazito.releasemanager.data.entity.DeployEntity;
import com.dazito.releasemanager.data.entity.SystemVersionEntity;
import com.dazito.releasemanager.service.SystemVersionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class SystemVersionServiceTest {
    private SystemVersionRepository systemVersionRepository;
    private SystemVersionService systemVersionService;
    private SystemVersionEntity systemVersionEntity;

    @Before
    public void initUseCase() {
        final DeployEntity deployEntity = new DeployEntity(1, "Service A", 1, null);
        final Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
        systemVersionEntity = new SystemVersionEntity(1, ts, Collections.singletonList(deployEntity));

        systemVersionRepository = Mockito.mock(SystemVersionRepository.class);
        Mockito.when(systemVersionRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(systemVersionEntity));

        systemVersionService = new SystemVersionService(systemVersionRepository);
    }

    @Test
    public void testGetSystemVersionById() {
        final SystemVersionDto systemVersionDto = systemVersionService.getSystemVersion(1);

        assertEquals(systemVersionDto.getId(), systemVersionEntity.getId());
        assertNotNull(systemVersionDto.getDeployedServices());
        assertEquals(1, systemVersionDto.getDeployedServices().size());
    }
}

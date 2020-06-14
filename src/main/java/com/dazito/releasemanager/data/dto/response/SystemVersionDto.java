package com.dazito.releasemanager.data.dto.response;

import com.dazito.releasemanager.data.dto.request.DeployDto;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class SystemVersionDto {
    private final Integer id;
    private final LocalDateTime ts;
    private final List<DeployDto> deployedServices;

    public SystemVersionDto() {
        this.id = null;
        this.ts = null;
        this.deployedServices = new ArrayList<>();
    }

    public SystemVersionDto(Integer id, LocalDateTime ts, List<DeployDto> deployedServices) {
        this.id = id;
        this.ts = ts;
        this.deployedServices = deployedServices;
    }
}

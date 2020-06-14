package com.dazito.releasemanager.data.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeployDto {
    private final String name;
    private final Integer version;
}

package com.dazito.releasemanager.controller;

import com.dazito.releasemanager.data.dto.request.DeployDto;
import com.dazito.releasemanager.service.DeployService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "deploy")
public class DeployController {

    private final DeployService deployService;

    public DeployController(DeployService deployService) {
        this.deployService = deployService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deploy(@RequestBody DeployDto deployDto) {
        final int deploymentVersion = deployService.deploy(deployDto);
        return ResponseEntity.ok(deploymentVersion);
    }
}

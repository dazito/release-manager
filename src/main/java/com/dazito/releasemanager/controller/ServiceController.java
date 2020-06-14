package com.dazito.releasemanager.controller;

import com.dazito.releasemanager.service.SystemVersionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "services")
@AllArgsConstructor
public class ServiceController {
    private final SystemVersionService systemVersionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getServices(@RequestParam(value = "systemVersion", required = false) Integer systemVersion) {
        if (systemVersion == null) {
            return ResponseEntity.ok(systemVersionService.getAllSystemVersions());
        }

        return ResponseEntity.ok(systemVersionService.getSystemVersion(systemVersion));
    }
}

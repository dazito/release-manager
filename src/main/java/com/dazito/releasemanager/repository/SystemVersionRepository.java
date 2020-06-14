package com.dazito.releasemanager.repository;

import com.dazito.releasemanager.data.entity.SystemVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemVersionRepository extends JpaRepository<SystemVersionEntity, Integer> {
    // Optional because it will return no results if there are no system version entries
    @Query("SELECT MAX(s) FROM SystemVersionEntity s")
    Optional<SystemVersionEntity> findCurrentDeploymentVersion();
}

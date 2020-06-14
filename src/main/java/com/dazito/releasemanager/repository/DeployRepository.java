package com.dazito.releasemanager.repository;

import com.dazito.releasemanager.data.entity.DeployEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeployRepository extends JpaRepository<DeployEntity, Integer> {
}

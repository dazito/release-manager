package com.dazito.releasemanager.data.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SystemVersion")
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SystemVersionEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ts", nullable = false)
    private Timestamp ts;
    @OneToMany(mappedBy = "systemVersionEntity")
    private List<DeployEntity> deployEntityList;

    public SystemVersionEntity() {
        this.id = null;
        this.ts = null;
        this.deployEntityList = new ArrayList<>();
    }
}

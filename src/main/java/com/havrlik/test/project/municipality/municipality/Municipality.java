package com.havrlik.test.project.municipality.municipality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipality")
public class Municipality {

    @Id
    @Column
    private String code;

    @Column(nullable = false)
    private String name;

    public Municipality() {
    }

    public Municipality(
            String code,
            String name
    ) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
}

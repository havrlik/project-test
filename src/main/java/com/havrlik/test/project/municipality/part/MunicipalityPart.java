package com.havrlik.test.project.municipality.part;

import com.havrlik.test.project.municipality.municipality.Municipality;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipality_part")
public class MunicipalityPart {

    @Id
    @Column
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipality_code")
    private Municipality municipality;

    public MunicipalityPart() {
    }

    public MunicipalityPart(
            String code,
            String name,
            Municipality municipality
    ) {
        this.code = code;
        this.name = name;
        this.municipality = municipality;
    }

    public String getCode() {
        return code;
    }
}

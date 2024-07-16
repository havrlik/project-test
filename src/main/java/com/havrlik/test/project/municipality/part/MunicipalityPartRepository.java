package com.havrlik.test.project.municipality.part;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MunicipalityPartRepository extends JpaRepository<MunicipalityPart, String>, JpaSpecificationExecutor<MunicipalityPart> {
}

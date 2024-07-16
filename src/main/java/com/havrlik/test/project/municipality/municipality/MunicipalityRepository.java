package com.havrlik.test.project.municipality.municipality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MunicipalityRepository extends JpaRepository<Municipality, String>, JpaSpecificationExecutor<Municipality> {
}

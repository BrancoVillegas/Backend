package com.finanzas.backend.repository;

import com.finanzas.backend.entities.InputInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInputInformationRepository extends JpaRepository<InputInformation, Long> {
}

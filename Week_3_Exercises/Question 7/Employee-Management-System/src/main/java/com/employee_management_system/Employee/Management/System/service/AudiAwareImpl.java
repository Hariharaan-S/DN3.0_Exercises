package com.employee_management_system.Employee.Management.System.service;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AudiAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Hariharaan S");
    }
}

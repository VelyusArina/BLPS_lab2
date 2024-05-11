package ru.artemiyandarina.blps_lab2.models;

import org.springframework.security.core.GrantedAuthority;

public enum PrivilegeEnum implements GrantedAuthority {
    CREATOR,
    MAINTAINER;	// Возможность обслуживать сервис

    @Override
    public String getAuthority() {
        return this.name();
    }
}

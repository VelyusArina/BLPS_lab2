package ru.artemiyandarina.blps_lab2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER(Set.of(PrivilegeEnum.CREATOR)),
    ROLE_ADMIN(Set.of(PrivilegeEnum.MAINTAINER));

    private final Set<PrivilegeEnum> authorities;
}

package com.himanshi.hospital.security;


import com.himanshi.hospital.entity.enums.Permission;

import java.util.Set;

public enum AppRole {
    ADMIN(Set.of(Permission.DOCTOR_CREATE, Permission.PATIENT_READ)),
    DOCTOR(Set.of(Permission.APPOINTMENT_READ, Permission.PATIENT_READ_SELF)),
    PATIENT(Set.of(Permission.APPOINTMENT_CREATE, Permission.PATIENT_READ_SELF));

    private final Set<Permission> permissions;

    AppRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}

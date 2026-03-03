package com.himanshi.hospital.entity.enums;
public enum Permission {

    // ===== DOCTOR =====
    DOCTOR_CREATE("doctor:create"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_DELETE("doctor:delete"),

    // ===== PATIENT =====
    PATIENT_READ("patient:read"),
    PATIENT_DELETE("patient:delete"),

    // Self profile (important separation)
    PATIENT_READ_SELF("patient:read:self"),
    PATIENT_UPDATE_SELF("patient:update:self"),

    // ===== APPOINTMENT =====
    APPOINTMENT_CREATE("appointment:create"),
    APPOINTMENT_READ("appointment:read"),
    APPOINTMENT_READ_SELF("appointment:read:self"),
    APPOINTMENT_CANCEL("appointment:cancel");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
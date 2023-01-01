package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity;

public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

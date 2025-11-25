package com.user.rbac.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Role represents a group of permissions.
 *
 * Example roles:
 * ADMIN → can VIEW REPORTS, EDIT USERS, DELETE USERS, APPROVE CONFIG
 * REPORT_VIEWER → can only VIEW REPORTS
 *
 * OOP Principles used:
 * - Composition → Role "has many" permissions
 * - Encapsulation → Permissions list is private
 */
public class Role {

    private final String name;                 // Name of the role (ADMIN, MANAGER)
    private final List<Permission> permissions; // List of permissions this role grants

    /**
     * Constructor simply sets the role name and prepares the permission list.
     */
    public Role(String name) {
        this.name = name;
        this.permissions = new ArrayList<>();
    }

    /**
     * Assigns a permission to the role.
     * Example: adminRole.addPermission(new Permission(Action.DELETE, Resource.USER))
     */
    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    /**
     * Getter for role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for permissions.
     * We return an UNMODIFIABLE list to prevent external code from altering it.
     * This maintains encapsulation and protects internal state.
     */
    public List<Permission> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    @Override
    public String toString() {
        return "Role{name='" + name + "', permissions=" + permissions + '}';
    }
}

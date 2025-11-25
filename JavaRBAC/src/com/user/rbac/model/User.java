package com.user.rbac.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User represents an actual system user.
 *
 * Each user:
 * - Has an id (unique)
 * - Has a name
 * - Has one or more roles
 *
 * OOP Principles used:
 * - Composition → User "has many" roles
 * - Encapsulation → Role list is private
 * - Immutability → id and name are final
 */
public class User {

    private final String id;            // Unique identifier of the user
    private final String name;          // Display name of the user
    private final List<Role> roles;     // Roles assigned to this user

    /**
     * Constructor initializes user with basic info.
     */
    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.roles = new ArrayList<>();
    }

    /**
     * Assigns a role to the user.
     * Example: user.assignRole(adminRole);
     */
    public void assignRole(Role role) {
        roles.add(role);
    }

    /**
     * Returns list of roles.
     * Returned list is read-only to protect internal structure.
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', roles=" + roles + '}';
    }
}

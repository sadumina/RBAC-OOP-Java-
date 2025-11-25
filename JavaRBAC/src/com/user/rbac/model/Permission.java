package com.user.rbac.model;

/**
 * Permission represents a specific allowed operation.
 * It is made from two parts:
 * 1) Action        → What operation (VIEW, EDIT, DELETE, etc.)
 * 2) ResourceType  → On which resource (REPORT, USER, VEHICLE, etc.)
 *
 * Example:
 * Permission p = new Permission(Action.VIEW, ResourceType.REPORT);
 */
public class Permission {

    // The allowed operation (VIEW, EDIT, DELETE...)
    private final Action action;

    // The resource this permission applies to (REPORT, USER, etc.)
    private final ResouceType resourceType;

    /**
     * Constructor that builds a specific permission.
     * Example: new Permission(Action.VIEW, ResourceType.USER)
     */
    public Permission(Action action, ResouceType resourceType) {
        this.action = action;
        this.resourceType = resourceType;
    }

    // Getter for action
    public Action getAction() {
        return action;
    }

    // Getter for resource
    public ResouceType getResourceType() {
        return resourceType;
    }

    @Override
    public String toString() {
        return action + " " + resourceType;
    }
}

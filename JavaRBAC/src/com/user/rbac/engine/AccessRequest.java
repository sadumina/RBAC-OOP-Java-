package com.user.rbac.engine;

import com.user.rbac.model.Action;
import com.user.rbac.model.ResouceType;
import com.user.rbac.model.User;

/**
 * AccessRequest represents a single authorization question:
 *
 *     "Can THIS user perform THIS action on THIS resource?"
 *
 * Example:
 *     new AccessRequest(user, Action.VIEW, ResourceType.REPORT);
 *
 * This will be evaluated by the AccessControlEngine.
 */
public class AccessRequest {

    private final User user;              // Who is trying the action?
    private final Action action;          // What action do they want to perform?
    private final ResouceType resource;  // On what resource?

    /**
     * Constructor builds a request object.
     * This is just a data holder, not the logic itself.
     */
    public AccessRequest(User user, Action action, ResouceType resource) {
        this.user = user;
        this.action = action;
        this.resource = resource;
    }

    // Getter: who is making the request?
    public User getUser() {
        return user;
    }

    // Getter: what action is requested?
    public Action getAction() {
        return action;
    }

    // Getter: what resource is being accessed?
    public ResouceType getResourceType() {
        return resource;
    }

    @Override
    public String toString() {
        return "AccessRequest{" +
                "user=" + user.getName() +
                ", action=" + action +
                ", resource=" + resource +
                '}';
    }
}

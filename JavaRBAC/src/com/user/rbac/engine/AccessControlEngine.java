package com.user.rbac.engine;

import com.user.rbac.model.Permission;
import com.user.rbac.model.Role;
import com.user.rbac.model.User;

/**
 * AccessControlEngine is a simple RBAC (Role-Based Access Control) evaluator.
 *
 * Logic:
 * 1. User has roles
 * 2. Roles have permissions
 * 3. Permission = Action + ResourceType
 *
 * Access is ALLOWED if any role contains a matching permission.
 */
public class AccessControlEngine implements PolicyEvaluator {

    /**
     * Core logic that decides if the user is allowed to do something.
     */
    @Override
    public AccessDecision evaluate(AccessRequest request) {

        User user = request.getUser();

        // STEP 1 — Check if the user has any roles
        if (user.getRoles().isEmpty()) {
            return new AccessDecision(false, 
                    "Denied: User has no roles assigned.");
        }

        // STEP 2 — Loop through each user role
        for (Role role : user.getRoles()) {

            // STEP 3 — Check each permission inside this role
            for (Permission perm : role.getPermissions()) {

                boolean actionMatches = perm.getAction() == request.getAction();
                boolean resourceMatches = perm.getResourceType() == request.getResourceType();

                // STEP 4 — If both action + resource match → ALLOW immediately
                if (actionMatches && resourceMatches) {

                    String reason = "Allowed: Matched permission '" 
                            + perm + "' in role '" + role.getName() + "'.";
                    
                    return new AccessDecision(true, reason);
                }
            }
        }

        // STEP 5 — If no permission matched any role → DENY
        return new AccessDecision(false, 
                "Denied: No matching permission found in user's roles.");
    }
}

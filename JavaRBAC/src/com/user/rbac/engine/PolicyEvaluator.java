package com.user.rbac.engine;

/**
 * PolicyEvaluator defines HOW an access request is evaluated.
 *
 * This is an interface, meaning:
 * - Different authorization strategies can be implemented
 * - Example: RBAC, ABAC, PBAC, custom rules
 *
 * Our main engine (AccessControlEngine) will implement this interface.
 */
public interface PolicyEvaluator {

    /**
     * Evaluates an access request and returns the decision.
     *
     * @param request - the user's access request
     * @return AccessDecision (allowed or denied + reason)
     */
    AccessDecision evaluate(AccessRequest request);
}

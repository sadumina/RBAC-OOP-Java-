package com.user.rbac.engine;

/**
 * AccessDecision represents the RESULT of the access evaluation.
 *
 * It contains:
 * - allowed (true/false)
 * - reason (why the decision was taken)
 *
 * Returning an object instead of a boolean improves clarity
 * and makes the engine extensible.
 */
public class AccessDecision {

    private final boolean allowed;  // true = access granted
    private final String reason;    // explanation (useful for logs/CLI/UI)

    public AccessDecision(boolean allowed, String reason) {
        this.allowed = allowed;
        this.reason = reason;
    }

    // Was the access granted?
    public boolean isAllowed() {
        return allowed;
    }

    // Why was this decision made?
    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "AccessDecision{" +
                "allowed=" + allowed +
                ", reason='" + reason + '\'' +
                '}';
    }
}

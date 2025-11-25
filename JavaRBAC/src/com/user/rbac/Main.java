package com.user.rbac;

import com.user.rbac.engine.AccessControlEngine;
import com.user.rbac.engine.AccessDecision;
import com.user.rbac.engine.AccessRequest;
import com.user.rbac.model.Action;
import com.user.rbac.model.ResouceType;
import com.user.rbac.model.User;
import com.user.rbac.utils.DemoDataInitializer;

/**
 * Entry point of the Java RBAC project.
 *
 * This class:
 * 1) Creates sample users (Admin, Report Viewer, No-Role user)
 * 2) Creates an AccessControlEngine
 * 3) Builds AccessRequest objects for different scenarios
 * 4) Calls engine.evaluate() and prints the results
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Java RBAC Engine Demo ===");

        // 1) Prepare sample users
        User adminUser        = DemoDataInitializer.createAdminUser();
        User reportViewerUser = DemoDataInitializer.createReportViewerUser();
        User noRoleUser       = DemoDataInitializer.createUserWithNoRoles();

        // 2) Create the RBAC engine (our policy evaluator)
        AccessControlEngine engine = new AccessControlEngine();

        // 3) Build several test access requests

        // Scenario A: Admin viewing reports → should be ALLOWED
        AccessRequest req1 = new AccessRequest(adminUser, Action.view, ResouceType.REPORT);

        // Scenario B: Admin deleting users → should be ALLOWED
        AccessRequest req2 = new AccessRequest(adminUser, Action.delete, ResouceType.USER);

        // Scenario C: Report viewer viewing reports → should be ALLOWED
        AccessRequest req3 = new AccessRequest(reportViewerUser, Action.view, ResouceType.REPORT);

        // Scenario D: Report viewer trying to delete users → should be DENIED
        AccessRequest req4 = new AccessRequest(reportViewerUser, Action.delete, ResouceType.USER);

        // Scenario E: User with no roles trying anything → should be DENIED
        AccessRequest req5 = new AccessRequest(noRoleUser, Action.view, ResouceType.REPORT);

        // 4) Evaluate and print all scenarios
        evaluateAndPrint(engine, req1);
        evaluateAndPrint(engine, req2);
        evaluateAndPrint(engine, req3);
        evaluateAndPrint(engine, req4);
        evaluateAndPrint(engine, req5);

        System.out.println("=== Demo Finished ===");
    }

    /**
     * Helper method that sends a request to the engine
     * and prints the result in a readable format.
     */
    private static void evaluateAndPrint(AccessControlEngine engine, AccessRequest request) {
        // Ask the engine to evaluate the request
        AccessDecision decision = engine.evaluate(request);

        // Print request details + decision result
        System.out.println("\nChecking request: " + request);
        System.out.println("=> Allowed? " + decision.isAllowed());
        System.out.println("=> Reason : " + decision.getReason());
    }
}

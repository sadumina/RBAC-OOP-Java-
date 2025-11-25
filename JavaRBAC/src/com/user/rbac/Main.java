package com.user.rbac;

import com.user.rbac.engine.AccessControlEngine;
import com.user.rbac.engine.AccessDecision;
import com.user.rbac.engine.AccessRequest;
import com.user.rbac.model.Action;
import com.user.rbac.model.ResouceType;
import com.user.rbac.model.User;
import com.user.rbac.utils.DemoDataInitializer;

import java.util.Scanner;

/**
 * Interactive CLI version of the Java RBAC Engine.
 *
 * Flow:
 * 1) Load sample users
 * 2) Ask user to choose:
 *      - Which User?
 *      - Which Action?
 *      - Which Resource?
 * 3) Evaluate using RBAC engine
 * 4) Print allow/deny decision
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Java RBAC Engine (Interactive Mode) ===");

        Scanner scanner = new Scanner(System.in);

        // 1) Prepare sample users
        User adminUser        = DemoDataInitializer.createAdminUser();
        User reportViewerUser = DemoDataInitializer.createReportViewerUser();
        User noRoleUser       = DemoDataInitializer.createUserWithNoRoles();

        User[] users = {adminUser, reportViewerUser, noRoleUser};

        // 2) Instance of RBAC engine
        AccessControlEngine engine = new AccessControlEngine();

        while (true) {

            System.out.println("\n--- Select User ---");
            System.out.println("1. Admin User");
            System.out.println("2. Report Viewer User");
            System.out.println("3. User With No Roles");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int userChoice = scanner.nextInt();
            if (userChoice == 0) {
                System.out.println("Exiting...");
                break;
            }

            if (userChoice < 1 || userChoice > 3) {
                System.out.println("Invalid option! Try again.");
                continue;
            }

            User selectedUser = users[userChoice - 1];

            // --- SELECT ACTION ---
            System.out.println("\n--- Select Action ---");
            for (int i = 0; i < Action.values().length; i++) {
                System.out.println((i + 1) + ". " + Action.values()[i]);
            }
            System.out.print("Enter choice: ");
            int actionChoice = scanner.nextInt();

            if (actionChoice < 1 || actionChoice > Action.values().length) {
                System.out.println("Invalid action!");
                continue;
            }

            Action selectedAction = Action.values()[actionChoice - 1];

            // --- SELECT RESOURCE ---
            System.out.println("\n--- Select Resource ---");
            for (int i = 0; i < ResouceType.values().length; i++) {
                System.out.println((i + 1) + ". " + ResouceType.values()[i]);
            }
            System.out.print("Enter choice: ");
            int resourceChoice = scanner.nextInt();

            if (resourceChoice < 1 || resourceChoice > ResouceType.values().length) {
                System.out.println("Invalid resource!");
                continue;
            }

            ResouceType selectedResource = ResouceType.values()[resourceChoice - 1];

            // --- BUILD ACCESS REQUEST ---
            AccessRequest request = new AccessRequest(
                    selectedUser,
                    selectedAction,
                    selectedResource
            );

            // --- EVALUATE ---
            AccessDecision decision = engine.evaluate(request);

            // --- RESULT ---
            System.out.println("\n=== Access Result ===");
            System.out.println("User     : " + selectedUser.getName());
            System.out.println("Action   : " + selectedAction);
            System.out.println("Resource : " + selectedResource);
            System.out.println("Allowed? : " + decision.isAllowed());
            System.out.println("Reason   : " + decision.getReason());
            System.out.println("======================\n");
        }

        scanner.close();
    }
}

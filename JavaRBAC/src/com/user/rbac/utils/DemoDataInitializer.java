package com.user.rbac.utils;

import com.user.rbac.model.Action;
import com.user.rbac.model.Permission;
import com.user.rbac.model.ResouceType;
import com.user.rbac.model.Role;
import com.user.rbac.model.User;

/**
 * DemoDataInitializer is a utility class used to create
 * sample users, roles, and permissions for testing the RBAC engine.
 *
 * In a real-world system, this data would normally come from
 * a database, config file, or identity provider (like WSO2 IS).
 */
public class DemoDataInitializer {

    /**
     * Creates and returns a user who acts like an "ADMIN".
     * This user has a role with multiple permissions.
     */
    public static User createAdminUser() {
        // 1) Define permissions for ADMIN role
        Permission viewReports   = new Permission(Action.view,   ResouceType.REPORT);
        Permission editUsers     = new Permission(Action.edit,   ResouceType.USER);
        Permission deleteUsers   = new Permission(Action.delete, ResouceType.USER);
        Permission approveConfig = new Permission(Action.approve, ResouceType.CONFIG);

        // 2) Create ADMIN role and assign permissions to it
        Role adminRole = new Role("ADMIN");
        adminRole.addPermission(viewReports);
        adminRole.addPermission(editUsers);
        adminRole.addPermission(deleteUsers);
        adminRole.addPermission(approveConfig);

        // 3) Create user and assign the ADMIN role
        User adminUser = new User("U001", "Sadumina (Admin)");
        adminUser.assignRole(adminRole);

        return adminUser;
    }

    /**
     * Creates and returns a "REPORT_VIEWER" type user.
     * This user can only VIEW REPORTS and nothing else.
     */
    public static User createReportViewerUser() {
        // 1) Define permission for REPORT_VIEWER
        Permission viewReports = new Permission(Action.view, ResouceType.REPORT);

        // 2) Create role with only that permission
        Role reportViewerRole = new Role("REPORT_VIEWER");
        reportViewerRole.addPermission(viewReports);

        // 3) Create user and assign the role
        User viewerUser = new User("U002", "Report Viewer");
        viewerUser.assignRole(reportViewerRole);

        return viewerUser;
    }

    /**
     * Creates and returns a user with NO roles.
     * Useful to test "denied because no roles assigned".
     */
    public static User createUserWithNoRoles() {
        return new User("U003", "User With No Roles");
    }
}

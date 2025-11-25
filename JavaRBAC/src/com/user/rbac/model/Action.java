package com.user.rbac.model;


/**
 * Represents possible actions a user can perform on a resource.
 * Using an enum ensures type safety and avoids string errors.
 */

public enum Action {

    view,
    create,
    edit,
    delete,
    approve,
    
}

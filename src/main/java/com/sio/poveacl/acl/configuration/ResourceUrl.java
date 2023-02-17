package com.sio.poveacl.acl.configuration;

import java.util.List;

public class ResourceUrl {

    public static final String CREATE_USER = "/api/users/create-user";
    public static final String UPDATE_USER = "/api/users/update-user/{id}";
    public static final String SHOW_USER = "/api/users/show-user/{id}";
    public static final String USER_LIST = "/api/users/list";
    public static final String USER_DASHBOARD = "/api/users/dashboard";

    public static final String CREATE_FEATURE = "/api/features/create-features";
    public static final String UPDATE_FEATURE = "/api/features/update-feature/{id}";
    public static final String SHOW_FEATURE = "/api/features/show-feature/{id}";
    public static final String FEATURE_LIST = "/api/features/list";
    public static final String FEATURE_DASHBOARD = "/api/features/dashboard";


    public static final AuthRecord CREATE_USER_RECORD = new AuthRecord("CREATE_USER", CREATE_USER, "Create User");
    public static final AuthRecord USER_LIST_RECORD = new AuthRecord("USER_LIST", USER_LIST, "User List");


    public static List<AuthRecord> getUserFeatureList() {
        return List.of(
                CREATE_USER_RECORD,
                USER_LIST_RECORD
        );
    }

    public static List<AuthRecord> getStaffFeatureList() {
        return List.of(
                USER_LIST_RECORD
        );
    }


}

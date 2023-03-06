package com.sio.poveacl.acl.configuration;

import java.util.Arrays;
import java.util.List;

public class ResourceUrl {

    public static final String CREATE_USER_ENDPOINT = "/api/users/create-user";
    public static final String UPDATE_USER = "/api/users/update-user/{id}";
    public static final String SHOW_USER = "/api/users/show-user/{id}";
    public static final String USER_LIST = "/api/users/list";
    public static final String USER_DASHBOARD = "/api/users/dashboard";

    public static final String CREATE_FEATURE = "/api/features/create-features";
    public static final String UPDATE_FEATURE = "/api/features/update-feature/{id}";
    public static final String SHOW_FEATURE = "/api/features/show-feature/{id}";
    public static final String FEATURE_LIST = "/api/features/list";
    public static final String FEATURE_DASHBOARD = "/api/features/dashboard";


    public static final String CREATE_DESIGNATION = "/api/designations/create-designations";
    public static final String UPDATE_DESIGNATION = "/api/designations/update-designation/{id}";
    public static final String SHOW_DESIGNATION = "/api/designations/show-designation/{id}";
    public static final String DESIGNATION_LIST = "/api/designations/list";
    public static final String DESIGNATION_DASHBOARD = "/api/designations/dashboard";


    public static final AuthRecord CREATE_USER_RECORD = new AuthRecord("CREATE_USER", CREATE_USER_ENDPOINT, "Create User");
    public static final AuthRecord USER_LIST_RECORD = new AuthRecord("USER_LIST", USER_LIST, "User List");
    public static final AuthRecord USER_DASHBOARD_RECORD = new AuthRecord("USER_DASHBOARD", USER_DASHBOARD, "Dashboard");
    public static final AuthRecord UPDATE_USER_RECORD = new AuthRecord("UPDATE_USER", UPDATE_USER, "Update User");
    public static final AuthRecord SHOW_USER_RECORD = new AuthRecord("SHOW_USER", SHOW_USER, "Show User");

    public static final AuthRecord CREATE_FEATURE_RECORD = new AuthRecord("CREATE_FEATURE", CREATE_FEATURE, "Create Feature");
    public static final AuthRecord UPDATE_FEATURE_RECORD = new AuthRecord("UPDATE_FEATURE", UPDATE_FEATURE, "Update Feature");
    public static final AuthRecord SHOW_FEATURE_RECORD = new AuthRecord("SHOW_FEATURE", SHOW_FEATURE, "Show Feature");
    public static final AuthRecord FEATURE_LIST_RECORD = new AuthRecord("FEATURE_LIST", FEATURE_LIST, "List Features");
    public static final AuthRecord FEATURE_DASHBOARD_RECORD = new AuthRecord("FEATURE_DASHBOARD", FEATURE_DASHBOARD, "Feature Dashboard");

    public static final AuthRecord CREATE_DESIGNATION_RECORD = new AuthRecord("CREATE_DESIGNATION", CREATE_DESIGNATION, "Create Designation");
    public static final AuthRecord UPDATE_DESIGNATION_RECORD = new AuthRecord("UPDATE_DESIGNATION", UPDATE_DESIGNATION, "Update Designation");
    public static final AuthRecord SHOW_DESIGNATION_RECORD = new AuthRecord("SHOW_DESIGNATION", SHOW_DESIGNATION, "Show Designation");
    public static final AuthRecord DESIGNATION_LIST_RECORD = new AuthRecord("DESIGNATION_LIST", DESIGNATION_LIST, "List Designations");
    public static final AuthRecord DESIGNATION_DASHBOARD_RECORD = new AuthRecord("DESIGNATION_DASHBOARD", DESIGNATION_DASHBOARD, "Designation Dashboard");



    public static List<AuthRecord> getUserFeatureList() {
        return List.of(
                CREATE_USER_RECORD,
                UPDATE_USER_RECORD,
                SHOW_USER_RECORD,
                USER_LIST_RECORD,
                USER_DASHBOARD_RECORD,
                CREATE_FEATURE_RECORD,
                UPDATE_FEATURE_RECORD,
                SHOW_FEATURE_RECORD,
                FEATURE_LIST_RECORD,
                FEATURE_DASHBOARD_RECORD,
                CREATE_DESIGNATION_RECORD,
                UPDATE_DESIGNATION_RECORD,
                SHOW_DESIGNATION_RECORD,
                DESIGNATION_LIST_RECORD,
                DESIGNATION_DASHBOARD_RECORD
        );
    }
}

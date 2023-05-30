package org.api.endpoints;

public class routes {
    public static  String base_Url ="https://petstore.swagger.io/v2";
    //User module

    // reason of making fields as static is we can access them anywhere in project

    public static String post_url = base_Url+"/user/";
    public static String get_url= base_Url+"/user/{username}";  // username is parameters which we are gooing to achieve as part of api chaining
    public static String update_url= base_Url+"/user/{username}";
    public static String delete_url= base_Url+"/user/{username}";

}


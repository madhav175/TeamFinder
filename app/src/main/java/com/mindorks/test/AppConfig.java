package com.mindorks.test;

/**
 * Created by Maddy on 21/01/17.
 */

public class AppConfig {
    // Server user login url
    public static String server_ip =  "10.0.2.2:3000/"/*"8b76fc98.ngrok.io"*/;
    public static String URL_LOGIN = "http://" + server_ip + "/android_login_api/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://" + server_ip + "/api/userLogin";
    public static String URL_EVENT = "http://" + server_ip + "/api/event";
    public static String URL_EVENTREGISTER = "http://" + server_ip + "/api/event/register";
    public static String URL_GET = "http://" + server_ip + "/api/userLogin/27";
    public static String URL_GETCARD = "http://" + server_ip + "/api/event/register/";
    public static String URL_GETADD = "http://" + server_ip + "/api/event/add?";
    public static String URL_MATCH = "http://" + server_ip + "api/event/match";
}

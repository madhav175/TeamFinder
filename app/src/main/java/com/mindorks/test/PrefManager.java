package com.mindorks.test;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Maddy on 21/01/17.
 */


import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;

        import java.util.HashMap;

/**
 * Created by Ravi on 08/07/15.
 */
public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "oyebhodi";

    // All Shared Preferences Keys
    private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERID = "userid";
    private static final String KEY_MEMID = "memid";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_URL = "url";
    private static final String KEY_HOUSE = "house";
    private static final String KEY_EVENTID = "eventid";
    private static final String KEY_STATE = "state";
    private static final String KEY_OTP = "otp";


    public static enum SCHEDULE_STATE {
        ORDERPLACED,
        SCHEDULEWASH,
        ADDRESS,
        VERIFIED,
        OTPWAIT,
        DEFAULT
    };

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setState(SCHEDULE_STATE state) {
        int stat = 0;
        if(state == SCHEDULE_STATE.DEFAULT){
            stat = 0;
        } else if (state == SCHEDULE_STATE.OTPWAIT) {
            stat = 1;
        }else if (state == SCHEDULE_STATE.VERIFIED) {
            stat = 2;
        }else if (state == SCHEDULE_STATE.ADDRESS) {
            stat = 3;
        }else if (state == SCHEDULE_STATE.SCHEDULEWASH) {
            stat = 4;
        } else if (state == SCHEDULE_STATE.ORDERPLACED) {
            stat = 5;
        }
        editor.putInt(KEY_STATE, stat);
        editor.commit();
    }

    public SCHEDULE_STATE getState() {
        int stat = pref.getInt(KEY_STATE,0);
        SCHEDULE_STATE state = SCHEDULE_STATE.DEFAULT;
        if(stat == 0){
            state = SCHEDULE_STATE.DEFAULT;
        } else if (stat == 1) {
            state = SCHEDULE_STATE.OTPWAIT;
        }else if (stat == 2) {
            state = SCHEDULE_STATE.VERIFIED;
        }else if (stat == 3) {
            state = SCHEDULE_STATE.ADDRESS;
        }else if (stat == 4) {
            state =  SCHEDULE_STATE.SCHEDULEWASH;
        } else if (stat == 5) {
            state = SCHEDULE_STATE.ORDERPLACED;
        }
        return state;
    }

    public void setIsWaitingForSms(boolean isWaiting) {
        editor.putBoolean(KEY_IS_WAITING_FOR_SMS, isWaiting);
        editor.commit();
    }

    public boolean isWaitingForSms() {
        return pref.getBoolean(KEY_IS_WAITING_FOR_SMS, false);
    }

    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, null);
    }
    public String getUserID() {
        return pref.getString(KEY_USERID, null);
    }
    public void setUserid(String userid) {
        editor.putString(KEY_USERID, userid);
        editor.commit();
    }
    public void setMemid(String memid) {
        editor.putString(KEY_MEMID, memid);
        editor.commit();
    }

    public void setOtp(String otp) {
        editor.putString(KEY_OTP, otp);
        editor.commit();
    }
    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }
    public void setUrl(String url) {
        editor.putString(KEY_URL, url);
        editor.commit();
    }



    public String getOtp() {
        return pref.getString(KEY_OTP, null);
    }

    public void setToken(String token) {

        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }
    public String getEventid() {
        return pref.getString(KEY_EVENTID, null);
    }

    public void setEventid(String eventid) {

        editor.putString(KEY_EVENTID, eventid);
        editor.commit();
    }
    public String getToken() {
        return pref.getString(KEY_TOKEN, null);
    }

    public String getName() {
        return pref.getString(KEY_NAME, null);
    }
    public String getUrl() {
        return pref.getString(KEY_URL, null);
    }
    public String getMemid() {
        return pref.getString(KEY_MEMID, null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }


}
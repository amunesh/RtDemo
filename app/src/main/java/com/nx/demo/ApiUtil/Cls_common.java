package com.nx.demo.ApiUtil;

import android.content.Context;
import android.content.SharedPreferences;

public class Cls_common {

    public static final String MyPREFERENCES = "POST";
    public static String USER_NAME = "USER_NAME";
    public static String EMAIL_ID = "EMAIL_ID";
    public static String PASSWROD = "PASSWROD";
    public Context mContext;

    public Cls_common(Context mContext) {
        this.mContext = mContext;
    }

    public static void Log(String title, String message) {
    //    Log.e(title, message);
    }


    public static String getUsername(Context c1) {
        if (c1 != null) {
            SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String ans = sharedpreferences.getString(USER_NAME, "");
            return ans;
        }
        return "";
    }

    public static void setUsername(Context c1, String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER_NAME, value);
        editor.apply();
    }

    public static String getEmaild(Context c1) {
        if (c1 != null) {
            SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String ans = sharedpreferences.getString(EMAIL_ID, "");
            return ans;
        }
        return "";
    }

    public static void setEmailID(Context c1, String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(EMAIL_ID, value);
        editor.apply();
    }

    public static String getpassword(Context c1) {
        if (c1 != null) {
            SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String ans = sharedpreferences.getString(PASSWROD, "");
            return ans;
        }
        return "";
    }

    public static void setPassword(Context c1, String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PASSWROD, value);
        editor.apply();
    }
}

package com.nx.demo.ApiUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class APIUtils {

    private APIUtils() {

    }
// public static final String API_URL = "http://192.168.42.105/example/";

 // public static final String API_URL = "https://asje.000webhostapp.com/Policyreminder/";

    public static final String API_URL="https://jsonplaceholder.typicode.com/";


    public static UserService getUserService(Context context) {
        return RetrofitClient.getRetrofit(API_URL, context).create(UserService.class);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
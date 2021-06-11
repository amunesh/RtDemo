package com.nx.demo.ApiUtil;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;


    /*   .addConverterFactory(GsonConverterFactory.create())*/

    public static Retrofit getRetrofit(String url, Context context) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

       /*     OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .protocols(Arrays.asList(Protocol.HTTP_1_1))
                    .addInterceptor(new ConnectivityAwareUrlClient(context))
                    .build();*/
            //Cls_Common.Log("getRetrofit", "" + url);
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

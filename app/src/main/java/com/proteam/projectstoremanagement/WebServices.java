package com.proteam.projectstoremanagement;


import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.util.Log;
import android.widget.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.PsmApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebServices<T> {
    T t;
    Call<T> call=null;
    public T getT() {
        return t;
    }

    public void setT(T t) {

        this.t = t;
    }

    ApiType apiTypeVariable;
    Context context;
    OnResponseListener<T> onResponseListner;
    private static OkHttpClient.Builder builder;

    public enum ApiType {
       login
    }

    String BaseUrl = "https://devrenew.proteam.co.in/en/api/";

    public WebServices(OnResponseListener<T> onResponseListner) {
        this.onResponseListner = onResponseListner;

        if (onResponseListner instanceof Activity) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof IntentService) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof android.app.DialogFragment) {
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) onResponseListner;
            this.context = dialogFragment.getActivity();
        }else if (onResponseListner instanceof android.app.Fragment) {
            android.app.Fragment fragment = (android.app.Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }
        else if (onResponseListner instanceof Adapter) {

            this.context = (Context) onResponseListner;
        }
        else if (onResponseListner instanceof Adapter) {
            this.context = (Context) onResponseListner;
        }
        else {
            //android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) onResponseListner;
            //this.context = fragment.getActivity();
        }

        builder = getHttpClient();
    }

    public WebServices(Context context, OnResponseListener<T> onResponseListner) {
        this.onResponseListner = onResponseListner;
        this.context = context;
        builder = getHttpClient();
    }


    public OkHttpClient.Builder getHttpClient() {

        if (builder == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(10000, TimeUnit.SECONDS);
            client.readTimeout(10000, TimeUnit.SECONDS).build();
            client.addInterceptor(loggingInterceptor);
            /*to pass header information with request*/
            client.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
                    return chain.proceed(request);
                }
            });

            return client;
        }
        return builder;
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private Retrofit getRetrofitClient(String api)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(api)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }


    public void login(String api, ApiType apiTypes, Loginmodel loginmodel)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.validatelogin(loginmodel);

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                System.out.println("usercompany===="+response.body());
                t=(T)response.body();
                onResponseListner.onResponse(t, apiTypeVariable, true,response.code());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                onResponseListner.onResponse(null, apiTypeVariable, false,0);
            }
        });

    }




   /* public void profile(String api, ApiType apiTypes, ProfileModel profileModel) {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);
        AuditApi auditApi=retrofit.create(AuditApi.class);

        call=(Call<T>)auditApi.profilelogin(profileModel);

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                System.out.println("usercompany===="+response.body());
                t=(T)response.body();
                onResponseListner.onResponse(t, apiTypeVariable, true,response.code());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                onResponseListner.onResponse(null, apiTypeVariable, false,0);
            }
        });

    }*/
}


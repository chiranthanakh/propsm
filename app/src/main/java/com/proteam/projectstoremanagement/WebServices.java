package com.proteam.projectstoremanagement;


import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.widget.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.proteam.projectstoremanagement.Model.Changepassmodel;
import com.proteam.projectstoremanagement.Request.ConsumptionDetailsRequest;
import com.proteam.projectstoremanagement.Request.ConsumptionListRequest;
import com.proteam.projectstoremanagement.Request.ConsumptionMaterialListRequest;
import com.proteam.projectstoremanagement.Request.PendingIntentupdaterequest;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Model.MaterialStockModel;
import com.proteam.projectstoremanagement.Request.Addmaterialrequest;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Request.RaiseIndentPreview;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.RaiseIndentConfirmRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.PsmApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
       general,login,location,sublocation,boq,pendingindent,pendingindentsignle,psmdata,indentstatus,materialstock,
        materialstockname,deletestockMhome,addmaterial,priview,confirmRaiseIndent,ConsList,
        consumptionDetails,boqedit,consumptionMateriallsit
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

    public void changepass(String api, ApiType apiTypes, Changepassmodel loginmodel)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.changepassword(loginmodel);

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

    public void constructorlocation( ApiType apiTypes, Constructorlocationrequest constructorlocationrequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.c_location(constructorlocationrequest);

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

    public void constructorSublocation( ApiType apiTypes, SubLocationRaiseRequest subLocationRaiseRequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.c_SUb_location(subLocationRaiseRequest);

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


    public void boqapi( ApiType apiTypes, Boqrequest boqrequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.boq(boqrequest);

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

    public void editboqapi( ApiType apiTypes, Indentpendingrequest indentpendingrequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.editboq(indentpendingrequest);

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

    public void pendingindent( ApiType apiTypes, PendingIndentRequest pendingIndentRequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.pendingindent(pendingIndentRequest);

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


    public void pendingIndentsingle( ApiType apiTypes, Indentpendingrequest pendingIndentRequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.pendingindentsinglestatus(pendingIndentRequest);

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

    public void psmdatahome( ApiType apiTypes, PsmDataRequest psmDataRequest)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.psddata(psmDataRequest);

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

    public void indentstatus( ApiType apiTypes,  Indentstatusrequest indentstatusrequest)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.indentstatus(indentstatusrequest);

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

    public void indentstatusdirect( ApiType apiTypes,  Indentstatusrequest indentstatusrequest)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.indentstatusdirect(indentstatusrequest);

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


    public void pendingintentupdate( ApiType apiTypes,  PendingIntentupdaterequest pendingIntentupdaterequest)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.pendingupdate(pendingIntentupdaterequest);

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

    public void materialstocklisthome( ApiType apiTypes,  MaterialStockModel materialStockModel)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.materialstockhome(materialStockModel);

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


    public void stockmaterialname( ApiType apiTypes)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.stockmaterialnamehome();

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

    public void deleteMstockdata( ApiType apiTypes, MaterialStockDeleteRequest materialStockDeleteRequest) {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.deleteMstock(materialStockDeleteRequest);

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

    public void addmaterial( ApiType apiTypes, Addmaterialrequest addmaterialrequest) {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);

        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.addmaterial(addmaterialrequest);

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

    public void prevewapi( ApiType apiTypes, RaiseIndentPreview raiseIndentPreview) {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);

        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.preview(raiseIndentPreview);

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

    public void confirmRaiseIndent( ApiType apiTypes, RaiseIndentConfirmRequest raiseIndentConfirmRequest) {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.confirmraiseindent(raiseIndentConfirmRequest);

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

    public void ConsumptionList( ApiType apiTypes,  ConsumptionListRequest consumptionListRequest)
    {
        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.consumptionlistdata(consumptionListRequest);

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


    public void consumptionDetails( ApiType apiTypes, ConsumptionDetailsRequest consumptionDetailsRequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.ConsumptionDataDetails(consumptionDetailsRequest);

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


    public void consumptionMaDetails( ApiType apiTypes, ConsumptionMaterialListRequest consumptionMaterialListRequest)
    {


        apiTypeVariable = apiTypes;
        Retrofit retrofit=getRetrofitClient(BaseUrl);


        PsmApi psmApi=retrofit.create(PsmApi.class);

        call=(Call<T>)psmApi.consumptionMaterial(consumptionMaterialListRequest);

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
}


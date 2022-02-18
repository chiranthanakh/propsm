package com.proteam.projectstoremanagement.Utils;

import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Response.Generalresponce;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PsmApi {


    @POST("Clients_apis/client_login")
    Call<Generalresponce> validatelogin(@Body Loginmodel logininfo);

    @POST("Clients_apis/contractor_location_list")
    Call<Contractorlocationmodel> c_location(@Body Constructorlocationrequest constructorlocationrequest);

}

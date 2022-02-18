package com.proteam.projectstoremanagement.Utils;

import com.proteam.projectstoremanagement.Model.ConSubLocationModel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
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

    @POST("Clients_apis/sub_location_list")
    Call<ConSubLocationModel> c_SUb_location(@Body SubLocationRaiseRequest subLocationRaiseRequest);

    @POST("Clients_apis/boq_list")
    Call<Boqlist> boq(@Body Boqrequest boqrequest);

}

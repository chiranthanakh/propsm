package com.proteam.projectstoremanagement.Utils;

import com.proteam.projectstoremanagement.Activities.PendingIntentupdaterequest;
import com.proteam.projectstoremanagement.Model.ConSubLocationModel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Model.MaterialStockModel;
import com.proteam.projectstoremanagement.Request.Addmaterialrequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.MaterialStockRequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndentStatusdirectlist;
import com.proteam.projectstoremanagement.Response.IndentStatuslist;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Response.PendingIndentList;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Response.StockMaterialNameResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PsmApi {


    @POST("Clients_apis/client_login")
    Call<LoginResponse> validatelogin(@Body Loginmodel logininfo);

    @POST("Clients_apis/contractor_location_list")
    Call<Contractorlocationmodel> c_location(@Body Constructorlocationrequest constructorlocationrequest);

    @POST("Clients_apis/sub_location_list")
    Call<ConSubLocationModel> c_SUb_location(@Body SubLocationRaiseRequest subLocationRaiseRequest);

    @POST("Clients_apis/boq_list")
    Call<Boqlist> boq(@Body Boqrequest boqrequest);

    @POST("Clients_apis/approver_indent_list")
    Call<PendingIndentList> pendingindent(@Body PendingIndentRequest pendingIndentRequest);

    @POST("Clients_apis/approver_indent_material_list")
    Call<Indentpending> pendingindentsinglestatus(@Body Indentpendingrequest pendingIndentRequest);

    @POST("Clients_apis/indent_status_count")
    Call<PsmDataStatusHome> psddata(@Body PsmDataRequest psmDataRequest);

    @POST("Clients_apis/riser_boq_indent_list")
    Call<IndentStatuslist> indentstatus(@Body Indentstatusrequest indentstatusrequest);

    @POST("Clients_apis/riser_direct_indent_list")
    Call<IndentStatusdirectlist> indentstatusdirect(@Body Indentstatusrequest indentstatusrequest);

    @POST("Clients_apis/approver_approve_indent")
    Call<Generalresponce> pendingupdate(@Body PendingIntentupdaterequest pendingIntentupdaterequest);

    @POST("Clients_apis/get_meterial_closing_stock_details")
    Call<MaterialStockRequest> materialstockhome(@Body MaterialStockModel materialStockModel);

    @POST("Clients_apis/materials_list")
    Call<StockMaterialNameResponse> stockmaterialnamehome();


    @POST("Clients_apis/delete_meterial_closing_stock_details")
    Call<Generalresponce> deleteMstock(@Body MaterialStockDeleteRequest materialStockDeleteRequest);

    @POST("Clients_apis/save_favorite_materials")
    Call<Generalresponce> addmaterial(@Body Addmaterialrequest addmaterialrequest);


}

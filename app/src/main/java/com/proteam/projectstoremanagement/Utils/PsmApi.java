package com.proteam.projectstoremanagement.Utils;

import com.proteam.projectstoremanagement.Model.Changepassmodel;
import com.proteam.projectstoremanagement.NotificationPart.RequestNotification;
import com.proteam.projectstoremanagement.Request.ConsumptionDetailsRequest;
import com.proteam.projectstoremanagement.Request.ConsumptionListRequest;
import com.proteam.projectstoremanagement.Request.ConsumptionMaterialListRequest;
import com.proteam.projectstoremanagement.Request.PendingIntentupdaterequest;
import com.proteam.projectstoremanagement.Model.ConSubLocationModel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Model.MaterialStockModel;
import com.proteam.projectstoremanagement.Request.Addmaterialrequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.MaterialStockRequest;
import com.proteam.projectstoremanagement.Request.Pendingapprovelistupdaterequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Request.RaiseIndentPreview;
import com.proteam.projectstoremanagement.Request.Updatepreviewlist;
import com.proteam.projectstoremanagement.Response.ConformRaiseindentresponse;
import com.proteam.projectstoremanagement.Response.ConsumptionDetailsDataResponse;
import com.proteam.projectstoremanagement.Response.ConsumptionListResponse;
import com.proteam.projectstoremanagement.Response.ConsumptionMaterialListResponse;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndentStatusdirectlist;
import com.proteam.projectstoremanagement.Response.IndentStatuslist;
import com.proteam.projectstoremanagement.Response.IndenteditList;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Response.PendingIndentList;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Response.PendingIntentgenaralresponse;
import com.proteam.projectstoremanagement.Response.PreviewResponsce;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Response.RaiseIndentConfirmRequest;
import com.proteam.projectstoremanagement.Response.StockMaterialNameResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PsmApi {


    @Headers({"Authorization: key=AAAAME40VRY:APA91bHlxDUz4qlqRlAsdKZ0o0GLnuPXEQQ-fCTvzmrnbiREdxhW6nHxY95ZusHecIIUY51-5v97J5LSetcdkLoxz98xOxS8D0VoqmEXx5AZWycMA7r5g4qPINRvT13Dwj7tOeOtblTy",
            "Content-Type:application/json"})
    @POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Body RequestNotification requestNotificaton);


    @POST("Clients_apis/client_login")
    Call<LoginResponse> validatelogin(@Body Loginmodel logininfo);

    @POST("Clients_apis/change_password")
    Call<LoginResponse> changepassword(@Body Changepassmodel logininfo);


    @POST("Clients_apis/contractor_location_list")
    Call<Contractorlocationmodel> c_location(@Body Constructorlocationrequest constructorlocationrequest);

    @POST("Clients_apis/sub_location_list")
    Call<ConSubLocationModel> c_SUb_location(@Body SubLocationRaiseRequest subLocationRaiseRequest);

    @POST("Clients_apis/boq_list")
    Call<Boqlist> boq(@Body Boqrequest boqrequest);

    @POST("Clients_apis/preview_boq_indents_edit")
    Call<IndenteditList> editboq(@Body Indentpendingrequest indentpendingrequest);

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
    Call<PendingIntentgenaralresponse> pendingupdate(@Body PendingIntentupdaterequest pendingIntentupdaterequest);

    @POST("Clients_apis/get_meterial_closing_stock_details")
    Call<MaterialStockRequest> materialstockhome(@Body MaterialStockModel materialStockModel);

    @POST("Clients_apis/materials_list")
    Call<StockMaterialNameResponse> stockmaterialnamehome();


    @POST("Clients_apis/delete_meterial_closing_stock_details")
    Call<Generalresponce> deleteMstock(@Body MaterialStockDeleteRequest materialStockDeleteRequest);

    @POST("Clients_apis/save_favorite_materials")
    Call<Generalresponce> addmaterial(@Body Addmaterialrequest addmaterialrequest);


    @POST("Clients_apis/preview_boq_indents")
    Call<PreviewResponsce> preview(@Body RaiseIndentPreview preview);

    @POST("Clients_apis/preview_boq_indents_update")
    Call<PreviewResponsce> previewupdate(@Body Updatepreviewlist updatepreviewlist);

    @POST("Clients_apis/pending_for_approval_update")
    Call<Generalresponce> aproveupdate(@Body Pendingapprovelistupdaterequest pendingapprovelistupdaterequest);


    @POST("Clients_apis/submit_boq_indents")
    Call<ConformRaiseindentresponse> confirmraiseindent(@Body RaiseIndentConfirmRequest raiseIndentConfirmRequest);

    @POST("Clients_apis/client_consumption_list")
    Call<ConsumptionListResponse> consumptionlistdata(@Body ConsumptionListRequest consumptionListRequest);


    @POST("Clients_apis/consumption_list_single_data")
    Call<ConsumptionDetailsDataResponse> ConsumptionDataDetails(@Body ConsumptionDetailsRequest consumptionDetailsRequest);


    @POST("Clients_apis/list_of_materials_in_consumption")
    Call<ConsumptionMaterialListResponse> consumptionMaterial(@Body ConsumptionMaterialListRequest consumptionMaterialListRequest);

    @POST("Clients_apis/pending_for_approval_update_list")
    Call<IndenteditList> pendingapproval(@Body Indentpendingrequest indentpendingrequest);

}

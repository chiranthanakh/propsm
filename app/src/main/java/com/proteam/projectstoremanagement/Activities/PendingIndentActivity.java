package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.proteam.projectstoremanagement.Adapters.PendingIndentAdapter;
import com.proteam.projectstoremanagement.Adapters.UpdateIndentAdapter;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class PendingIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;

    TextView tv_p_indent_number,tv_p_approvername,tv_p_contractorName,tv_p_locationName,tv_p_sublocationName,
            tv_p_workordernumber,tv_p_status,tv_p_indentdate;

    ProgressDialog progressDialog;
    ListView lv_pending_indent;

    List PendingIndent = new ArrayList();
    final ArrayList<PendingIndentModel> arrayList = new ArrayList<PendingIndentModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilze();


    }

    private void initilze() {

        lv_pending_indent=findViewById(R.id.lv_pending_indent);

        tv_p_indent_number=findViewById(R.id.tv_p_indent_number);
        tv_p_approvername=findViewById(R.id.tv_p_approvername);
        tv_p_contractorName=findViewById(R.id.tv_p_contractorName);
        tv_p_locationName=findViewById(R.id.tv_p_locationName);
        tv_p_sublocationName=findViewById(R.id.tv_p_sublocationName);
        tv_p_workordernumber=findViewById(R.id.tv_p_workordernumber);
        tv_p_status=findViewById(R.id.tv_p_status);
        tv_p_indentdate=findViewById(R.id.tv_p_indentdate);

        callboqupdateapi();
    }


    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(PendingIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                PendingIndentRequest pendingIndentRequest = new PendingIndentRequest("72");
                WebServices<Indentpending> webServices = new WebServices<Indentpending>(PendingIndentActivity.this);
                webServices.pendingIndentsingle(WebServices.ApiType.boq, pendingIndentRequest);
            }
        }

    }






    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {

            case pendingindentsignle:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        Indentpending indentpending = (Indentpending) response;
                        PendingIndent = indentpending.getIndent_boq_list();

                        for (int i=0;i<PendingIndent.size();i++){

                            arrayList.add(new PendingIndentModel(indentpending.getIndent_boq_list().get(i).getMaterial_manual_id(),indentpending.getIndent_boq_list().get(i).getMaterial_name(),indentpending.getIndent_boq_list().get(i).getBalance_boq(),indentpending.getIndent_boq_list().get(i).getBoq_qty()));

                        }



                        // the context and arrayList created above
                        PendingIndentAdapter numbersArrayAdapter = new PendingIndentAdapter(this, arrayList);

                        // create the instance of the ListView to set the numbersViewAdapter
                        ListView pendingindent = findViewById(R.id.lv_pending_indent);

                        // set the numbersViewAdapter for ListView
                        pendingindent.setAdapter(numbersArrayAdapter);

                    }

                }
                break;


        }
    }
}
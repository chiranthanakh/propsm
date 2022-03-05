package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Model.Changepassmodel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.Utilities;
import com.proteam.projectstoremanagement.WebServices;

public class ChangePasswordActivity extends AppCompatActivity implements OnResponseListener {
    ImageView mToolbar;
    Button btn_continue;
    EditText edt_old_password, edt_new_password,edt_confirm_password;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();


    }

    private void initilize() {

        edt_old_password =findViewById(R.id.edt_old_password);
        edt_new_password = findViewById(R.id.edt_new_password);
        edt_confirm_password = findViewById(R.id.edt_confirm_password);
        btn_continue = findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callupdateApi();
            }
        });

    }

    private void callupdateApi() {

        progressDialog=new ProgressDialog(ChangePasswordActivity.this);
        if(progressDialog!=null)
        {
            if(!progressDialog.isShowing())
            {

                SharedPreferences sharedPreferences = this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String email = sharedPreferences.getString("email", null);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Changepassmodel changepassmodel = new Changepassmodel(email,edt_old_password.getText().toString().trim(),edt_new_password.getText().toString().trim());
                WebServices<LoginResponse> webServices = new WebServices<LoginResponse>(ChangePasswordActivity.this);
                webServices.changepass(Utilities.getBaseURL(ChangePasswordActivity.this), WebServices.ApiType.login,changepassmodel );
            }
            else {

            }
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {
            case login:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    LoginResponse loginResponse = (LoginResponse) response;

                    if(loginResponse.getStatus().equals("true")){

                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(this, "Missmatch in password", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }

                break;

        }


    }
}
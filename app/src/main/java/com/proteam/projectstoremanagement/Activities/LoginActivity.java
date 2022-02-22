package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.Utilities;
import com.proteam.projectstoremanagement.WebServices;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {

    AppCompatButton btn_login;
    EditText edt_Email,edt_Pass;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initilize();
    }

    private void initilize()
    {
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        edt_Email=findViewById(R.id.edt_Email);
        edt_Email.setOnClickListener(this);
        edt_Pass=findViewById(R.id.edt_Pass);
        edt_Pass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                if(!edt_Email.getText().toString().equals("")){

                    if (!edt_Pass.getText().toString().equals("")){

                        callLoginApi();

                    }else {
                        Toast.makeText(LoginActivity.this,"Enter your password",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this,"Enter your email-id",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void callLoginApi() {

        progressDialog=new ProgressDialog(LoginActivity.this);
        if(progressDialog!=null)
        {
            if(!progressDialog.isShowing())
            {

                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                Loginmodel loginmodel = new Loginmodel(edt_Email.getText().toString(),edt_Pass.getText().toString());

                WebServices<LoginResponse> webServices = new WebServices<LoginResponse>(LoginActivity.this);
                webServices.login(Utilities.getBaseURL(LoginActivity.this), WebServices.ApiType.login,loginmodel );
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

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("user",edt_Email.getText().toString().trim());
                        intent.putExtras(bundle1);

                        startActivity(intent);
                        SharedPreferences prefs = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("store_id",loginResponse.getStore_id());
                        editor.commit();
                        finish();

                    }else{
                        Toast.makeText(this, "Please enter correct login details", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }

                break;

        }

    }
}
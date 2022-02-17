package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.proteam.projectstoremanagement.R;

public class RaiseIndentActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    AppCompatButton btn_indent_preview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize()
    {
        btn_indent_preview=findViewById(R.id.btn_indent_preview);
        btn_indent_preview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_indent_preview:
                Intent intent = new Intent(RaiseIndentActivity.this,UpateIndentActivity.class);
                startActivity(intent);
                break;
        }

    }
}
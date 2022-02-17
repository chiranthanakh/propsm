package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.proteam.projectstoremanagement.R;

public class PendingIndentListActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    LinearLayout ll_status_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initilize();
    }

    private void initilize()
    {
        ll_status_click=findViewById(R.id.ll_status_click);
        ll_status_click.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ll_status_click:
                Intent intent = new Intent(PendingIndentListActivity.this,PendingIndentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
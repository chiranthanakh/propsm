package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.proteam.projectstoremanagement.Adapters.NotificationAdapter;
import com.proteam.projectstoremanagement.Model.NotificationModel;
import com.proteam.projectstoremanagement.R;

public class NotificationActivity extends AppCompatActivity {
    ImageView mToolbar;
    RecyclerView rv_notification_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initialize();

        NotificationModel[] myMailListData = new NotificationModel[]{
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),
                new NotificationModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt","Notification Title","07 March 2022"),


        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_notification_message);
        NotificationAdapter adapter = new NotificationAdapter(myMailListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initialize()
    {
        rv_notification_message=findViewById(R.id.rv_notification_message);
    }


}
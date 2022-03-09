package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.NotificationAdapter;
import com.proteam.projectstoremanagement.Model.NotificationModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Utils.SqlDb;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    ImageView mToolbar;
    RecyclerView rv_notification_message;

    ArrayList<NotificationModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initialize();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_notification_message);
        NotificationAdapter adapter = new NotificationAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initialize()
    {
        rv_notification_message=findViewById(R.id.rv_notification_message);



        SqlDb myDB=new SqlDb(NotificationActivity.this);
        Cursor cursor = myDB.getAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(NotificationActivity.this, "No Data", Toast.LENGTH_LONG).show();
            return;
        } else {
            Toast.makeText(NotificationActivity.this, "Notification list", Toast.LENGTH_LONG).show();
            while (cursor.moveToNext()) {

               arrayList.add(new NotificationModel(cursor.getString(1),cursor.getString(2),cursor.getString(3)));

            }
        }
    }
}
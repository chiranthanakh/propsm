package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class RaiseIndentActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    AppCompatButton btn_indent_preview;

    ListView lv_raise_indent_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initialize();

        final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();

        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));
        arrayList.add(new RaiseIndentModel("53334","8MTR Long PCC Pole..","567647","344"));

        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView raiseindentlistdata = findViewById(R.id.lv_raise_indent_list);

        // set the numbersViewAdapter for ListView
        raiseindentlistdata.setAdapter(numbersArrayAdapter);
    }

    private void initialize()
    {
        btn_indent_preview=findViewById(R.id.btn_indent_preview);
        btn_indent_preview.setOnClickListener(this);
        lv_raise_indent_list=findViewById(R.id.lv_raise_indent_list);
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
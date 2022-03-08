package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.proteam.projectstoremanagement.Model.NotificationModel;
import com.proteam.projectstoremanagement.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private NotificationModel[] listdata;
    private Context context;
    public NotificationAdapter(NotificationModel[] listdata) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.notification_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NotificationModel mymailListData = listdata[position];
        holder.tv_notification.setText(listdata[position].getDescription());
        holder.tv_notification_title.setText(listdata[position].getTitle());
        holder.tv_notification_date.setText(listdata[position].getDate());
       // holder.imageView.setImageResource(listdata[position].getImgId());
        holder.cclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+mymailListData.getDescription(),Toast.LENGTH_LONG).show();

               /* Intent intent = new Intent(context, ReportsActivity.class);
                context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_notification,tv_notification_date,tv_notification_title;
        public ConstraintLayout cclayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // this.imageView = (ImageView) itemView.findViewById(R.id.rv_mail_image);
            this.tv_notification_title = (TextView) itemView.findViewById(R.id.tv_notification_title);
            this.tv_notification = (TextView) itemView.findViewById(R.id.tv_notification);
            this.tv_notification_date = (TextView) itemView.findViewById(R.id.tv_notification_date);
            cclayout = (ConstraintLayout) itemView.findViewById(R.id.cc_layout);
        }
    }
}

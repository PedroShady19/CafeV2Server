package com.example.shady.cafev2server.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;


import com.example.shady.cafev2server.Interface.ItemClickListener;
import com.example.shady.cafev2server.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;
    public Button btnEdit,btnRemove,btnDetail;


    public OrderViewHolder(View itemView){
        super(itemView);
        txtOrderAddress= itemView.findViewById(R.id.order_address);
        txtOrderPhone=itemView.findViewById(R.id.order_phone);
        txtOrderId=itemView.findViewById(R.id.order_id);
        txtOrderStatus=itemView.findViewById(R.id.order_status);

        btnEdit= (Button)itemView.findViewById(R.id.btnEdit);
        btnRemove= (Button)itemView.findViewById(R.id.btnRemove);
        btnDetail= (Button)itemView.findViewById(R.id.btnDetail);


    }
}

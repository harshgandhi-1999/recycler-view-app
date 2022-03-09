package com.example.recyclerviewapp.multi_view_type;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.MultiEmployee;

import java.util.ArrayList;

public class MultiViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_CALL = 1;
    private static final int TYPE_EMAIL = 2;
    private Context context;
    private ArrayList<MultiEmployee> employees;

    public MultiViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if(viewType==TYPE_CALL){
            // Display a call layout
            view = LayoutInflater.from(context).inflate(R.layout.call_item_layout,parent,false);
            return new CallViewHolder(view);
        }else{
            // Display an email layout
            view = LayoutInflater.from(context).inflate(R.layout.email_item_layout,parent,false);
            return new EmailViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(TextUtils.isEmpty(employees.get(position).getEmail())){
            return TYPE_CALL;
        }else{
            return TYPE_EMAIL;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==TYPE_CALL){
            ((CallViewHolder)holder).setCallDetails(employees.get(position));
        }else{
            ((EmailViewHolder)holder).setEmailDetails(employees.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(ArrayList<MultiEmployee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    public class CallViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtAddress;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }

        void setCallDetails(MultiEmployee employee){
            txtName.setText(employee.getName());
            txtAddress.setText(employee.getAddress());
        }
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        private TextView txtAddress;

        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }

        void setEmailDetails(MultiEmployee employee){
            txtName.setText(employee.getName());
            txtAddress.setText(employee.getAddress());
        }
    }
}
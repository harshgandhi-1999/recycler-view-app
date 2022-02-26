package com.example.recyclerviewapp.single_selection_rec_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;
import com.example.recyclerviewapp.models.Employee;

import java.util.ArrayList;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int checkedPosition = -1; // -1 for no default selection

    public SingleAdapter(Context myContext) {
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.employee_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    // inner class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtEmpName;
        private ImageView imgTick;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmpName = itemView.findViewById(R.id.txtEmpName);
            imgTick = itemView.findViewById(R.id.imgTick);
        }

        void bind(Employee employee){
            if(checkedPosition==-1){
                imgTick.setVisibility(View.GONE);
            }else{
                if(checkedPosition==getAdapterPosition()){
                    imgTick.setVisibility(View.VISIBLE);
                }else{
                    imgTick.setVisibility(View.GONE);
                }
            }

            txtEmpName.setText(employee.getName());
            itemView.setOnClickListener(view -> {
                imgTick.setVisibility(View.VISIBLE);
                if(checkedPosition!=getAdapterPosition()){
                    notifyItemChanged(checkedPosition);
                    checkedPosition = getAdapterPosition();
                }
            });

        }
    }

    public Employee getSelectedEmployee(){
        if(checkedPosition!=-1){
            return employees.get(checkedPosition);
        }
        return null;
    }

}

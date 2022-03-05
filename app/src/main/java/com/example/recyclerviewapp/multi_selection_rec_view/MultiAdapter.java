package com.example.recyclerviewapp.multi_selection_rec_view;

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

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MyViewHolder>{
    private Context myContext;
    private ArrayList<Employee> employees;

    public MultiAdapter(Context myContext) {
        this.myContext = myContext;
        employees = new ArrayList<>();
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


    public ArrayList<Employee> getSelectedEmployees(){
        ArrayList<Employee> selected = new ArrayList<>();
        for(int i=0;i<employees.size();i++){
            if(employees.get(i).isChecked()){
                selected.add(employees.get(i));
            }
        }

        return selected;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtEmpName;
        private ImageView imgTick;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEmpName = itemView.findViewById(R.id.txtEmpName);
            imgTick = itemView.findViewById(R.id.imgTick);
        }

        // Getting the selected items
        void bind(final Employee employee){
            imgTick.setVisibility(employee.isChecked() ? View.VISIBLE : View.GONE);
            txtEmpName.setText(employee.getName());

            itemView.setOnClickListener(view -> {
                employee.setChecked(!employee.isChecked());
                imgTick.setVisibility(employee.isChecked() ? View.VISIBLE : View.GONE);
            });
        }
    }
}

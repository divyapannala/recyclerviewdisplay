package com.example.displaydetailsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    List<PersonDetails> personlist;
    Context mcontext;

    public DetailsAdapter(Context mcontext, List<PersonDetails> personlist) {

        this.mcontext=mcontext;
        this.personlist = personlist;

    }


    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailslist_person,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.ViewHolder holder, int position) {


        holder.useremail.setText(personlist.get(position).getPersonemail());
         holder.mobile.setText(personlist.get(position).getPersoncontact());
    }

    @Override
    public int getItemCount() {
        return personlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView useremail,mobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            useremail=itemView.findViewById(R.id.emailid);
            mobile =itemView.findViewById(R.id.phoneno);
        }
    }
}

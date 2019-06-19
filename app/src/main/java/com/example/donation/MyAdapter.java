package com.example.donation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    ArrayList<donation> myList;
    public MyAdapter(ArrayList<donation>arrayList){
        myList = arrayList;

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
    myViewHolder.donationId.setText(myList.get(i).getName());
      myViewHolder.donationamount.setText(Integer.toString(myList.get(i).getAmount()));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView donationId;
        public TextView donationamount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            donationId=itemView.findViewById(R.id.donationIdRow);
            donationamount=itemView.findViewById(R.id.donationAmountRow);
        }

    }
}

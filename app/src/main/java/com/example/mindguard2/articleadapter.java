package com.example.mindguard2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class articleadapter extends RecyclerView.Adapter<articleadapter.ViewHolder>{
    ArrayList<articlemodel> list;
    Context context;

    public articleadapter(ArrayList<articlemodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.article_layout,parent,false);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        articlemodel articlemodel=list.get(position);
        holder.title.setText(articlemodel.getHeading());
        holder.desc.setText(articlemodel.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, displayarticle_Activity.class);
                i.putExtra("link",articlemodel.getLink());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textView12);
            desc=itemView.findViewById(R.id.textView13);
        }
    }
}

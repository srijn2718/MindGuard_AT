package com.example.mindguard2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapterforcomments extends RecyclerView.Adapter<adapterforcomments.ViewHolder> {
    Context context;
    ArrayList<comments_class>arrayList;
    public  adapterforcomments(Context context, ArrayList<comments_class>arrayList){
        this.context=context;
        this.arrayList=arrayList;

    }
    @NonNull
    @Override
    public adapterforcomments.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.layout_for_comments,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterforcomments.ViewHolder holder, int position) {
        holder.t1.setText(arrayList.get(position).getName());
        holder.t2.setText(arrayList.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.textView19);
            t2=itemView.findViewById(R.id.textView20);
        }
    }
}

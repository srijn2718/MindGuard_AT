package com.example.mindguard2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class myadapterfordisplayingtweets extends RecyclerView.Adapter<myadapterfordisplayingtweets.ViewHolder> {
    Context context;
    ArrayList<tweets>arrayList;
    ArrayList<ViewHolder>arr;
   static String username,id;

    public myadapterfordisplayingtweets(Context context, ArrayList<tweets>arrayList,String username,String id){
        this.context=context;
        this.arrayList=arrayList;
        this.username=username;
        this.id=id;
        arr=new ArrayList<>();
    }
    @NonNull
    @Override
    public myadapterfordisplayingtweets.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.layout_for_displaying_tweets,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myadapterfordisplayingtweets.ViewHolder holder, int position) {
       holder.name.setText(arrayList.get(position).getName());
       holder.tweet.setText(arrayList.get(position).getTweet());
       if(holder.f2==0){


           holder.ref2=FirebaseDatabase.getInstance().getReference("Tweets").child(arrayList.get(position).getUid()).child("comments");
           holder.valueEventListener2=new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){

                       holder.comments.setText(snapshot.getChildrenCount()+"");

                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           };
           holder.ref2.addValueEventListener(holder.valueEventListener2);




           holder.f2=1;
           holder.ref=FirebaseDatabase.getInstance().getReference("Tweets").child(arrayList.get(position).getUid());

           holder.valueEventListener=new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       String li=snapshot.child("likes").getValue(String.class);
                       holder.like_number.setText(li);

                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           };
           holder.ref.addValueEventListener(holder.valueEventListener);
           arr.add(holder);

       }


       holder.like.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(holder.f==0) {
                   holder.like.setImageResource(R.drawable.heartfilled);
                   holder.f=1;
                   DatabaseReference mdb= FirebaseDatabase.getInstance().getReference("Tweets").child(arrayList.get(position).getUid()).child("likes");
                   mdb.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.exists()){
                               String i=snapshot.getValue(String.class);
                               int l=Integer.parseInt(i);
                               l++;
                               DatabaseReference db=FirebaseDatabase.getInstance().getReference("Tweets");
                               db.child(arrayList.get(position).getUid()).child("likes").setValue(l+"");

                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });


               }else{
                   holder.like.setImageResource(R.drawable.heartunfilled);
                   holder.f=0;
                   DatabaseReference mdb= FirebaseDatabase.getInstance().getReference("Tweets").child(arrayList.get(position).getUid()).child("likes");
                   mdb.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.exists()){
                               String i=snapshot.getValue(String.class);
                               int l=Integer.parseInt(i);
                               l--;
                               DatabaseReference db=FirebaseDatabase.getInstance().getReference("Tweets");
                               db.child(arrayList.get(position).getUid()).child("likes").setValue(l+"");

                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });

               }
           }
       });

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(context,Show_thread_act.class);
i.putExtra("tweetid",arrayList.get(position).getUid());
i.putExtra("tweet",arrayList.get(position).getTweet());
i.putExtra("username",arrayList.get(position).getName());
i.putExtra("id",id);
               context.startActivity(i);

           }
       });



    }
    public void removealllisterner(){
        for(int i=0;i<arr.size();i++){
            arr.get(i).ref.removeEventListener(arr.get(i).valueEventListener);
            arr.get(i).f=0;
            arr.get(i).f2=0;
            arr.get(i).ref2.removeEventListener(arr.get(i).valueEventListener2);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tweet,name,comments,like_number;
        ImageView like;
        ValueEventListener valueEventListener,valueEventListener2;
        DatabaseReference ref,ref2;
        int f=0;
        int f2=0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tweet=itemView.findViewById(R.id.textView15);
            name=itemView.findViewById(R.id.textView14);
            like=itemView.findViewById(R.id.imageView2);
            like_number=itemView.findViewById(R.id.textView16);
            comments=itemView.findViewById(R.id.textView18);

        }
    }
}

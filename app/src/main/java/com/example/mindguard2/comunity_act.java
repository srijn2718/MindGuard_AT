package com.example.mindguard2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class comunity_act extends AppCompatActivity {
    String id;
    String username;
    DatabaseReference mdb;
    ArrayList<tweets>arrayList;
    ValueEventListener valueEventListener;
    DatabaseReference reference;
    ArrayList<String >tweetsid;
    RecyclerView recyclerView;
    myadapterfordisplayingtweets ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comunity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reference= FirebaseDatabase.getInstance().getReference("Tweets");
        tweetsid=new ArrayList<>();
        arrayList=new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        if(!tweetsid.contains(dataSnapshot.getKey())){
                            tweetsid.add(dataSnapshot.getKey());
                            tweets t=new tweets();
                            t.setName(dataSnapshot.child("username").getValue(String.class));
                            t.setTweet(dataSnapshot.child("tweet").getValue(String.class));
                            t.setLike(dataSnapshot.child("likes").getValue(String.class));
                            t.setUid(dataSnapshot.getKey());
                            arrayList.add(t);


                        }
                    }
                    ad.notifyDataSetChanged();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         id=getIntent().getStringExtra("userid");
         username=getIntent().getStringExtra("username");
        ad=new myadapterfordisplayingtweets(this,arrayList,username,id);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ad);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ad.removealllisterner();
    }

    public  void createtweetbtnisclciked(View v){
        Intent i=new Intent(this,create_threadAct.class);
        i.putExtra("id",id);
        i.putExtra("username",username);
        startActivity(i);
    }
}
package com.example.mindguard2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.HashMap;

public class Show_thread_act extends AppCompatActivity {
    tweets t;
    String name,id;
TextView tname,ttweet;
EditText edt;
Button btn;
DatabaseReference ref;
ValueEventListener valueEventListener;
ArrayList<String >arr;
ArrayList<comments_class>arrayList;
RecyclerView recyclerView;
adapterforcomments ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        arrayList=new ArrayList<>();
        edt=findViewById(R.id.editTextText2);
        btn=findViewById(R.id.button5);
        t=new tweets();
        arr=new ArrayList<>();

       t.setUid(getIntent().getStringExtra("tweetid"));
       t.setTweet(getIntent().getStringExtra("tweet"));

name=getIntent().getStringExtra("username");
id=getIntent().getStringExtra("id");
tname=findViewById(R.id.textView21);
ttweet=findViewById(R.id.textView22);
tname.setText(name+"");
ttweet.setText(t.getTweet()+"");
        ad=new adapterforcomments(this,arrayList);
        recyclerView=findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ad);
ref=FirebaseDatabase.getInstance().getReference("Tweets").child(t.getUid()).child("comments");
valueEventListener=new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){
            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                if(!arr.contains(dataSnapshot.getKey())){
                    comments_class c=new comments_class();
                    c.setName(dataSnapshot.child("username").getValue(String.class));
                    c.setComment(dataSnapshot.child("comment").getValue(String.class));
                    arrayList.add(c);
                    arr.add(dataSnapshot.getKey());

                }
            }
            ad.notifyDataSetChanged();

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
};
ref.addValueEventListener(valueEventListener);



    }
    public void addcommentbtnisclicked(View v){
        if(edt.getText().toString().equals("")){
            return;
        }
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Tweets").child(t.getUid()).child("comments");
        HashMap<String ,String >hashMap=new HashMap<>();
        hashMap.put("username",myadapterfordisplayingtweets.username);
        hashMap.put("comment",edt.getText().toString());
        ref.push().setValue(hashMap);






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ref.removeEventListener(valueEventListener);
    }
}
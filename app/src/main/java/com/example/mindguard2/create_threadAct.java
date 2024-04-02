package com.example.mindguard2;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class create_threadAct extends AppCompatActivity {
    EditText edt;
    Button btn;
    String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("username");
        edt=findViewById(R.id.editTextText);
        btn=findViewById(R.id.button4);

    }
    public void tweetbtnisclicked(View v){
        if(edt.getText().toString().equals(""))
        {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference mdb= FirebaseDatabase.getInstance().getReference("Tweets").push();
        HashMap<String ,String >hmap=new HashMap<>();
        hmap.put("username",name);
        hmap.put("tweet",edt.getText().toString());
        hmap.put("likes",0+"");
        mdb.setValue(hmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
                    Toast.makeText(create_threadAct.this, "Tweet successfull", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}
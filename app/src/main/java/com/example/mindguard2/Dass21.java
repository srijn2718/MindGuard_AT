package com.example.mindguard2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dass21 extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<dass21model> list;
    dass21adapter adapter;
    static int depression=0,anxiety=0,stress=0;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dass21);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recyclerview);
        submit=findViewById(R.id.button3);
        list=new ArrayList<>();
        list.add(new dass21model("I found it hard to wind down",-1));
        list.add(new dass21model("I was aware of dryness of my mouth",-1));
        list.add(new dass21model("I couldn’t seem to experience any positive feeling at all",-1));
        list.add(new dass21model("I experienced breathing difficulty (e.g. excessively rapid breathing,\n" +
                "breathlessness in the absence of physical exertion)",-1));
        list.add(new dass21model("I found it difficult to work up the initiative to do things",-1));
        list.add(new dass21model("I tended to over-react to situations",-1));
        list.add(new dass21model("I experienced trembling (e.g. in the hands)",-1));
        list.add(new dass21model("I felt that I was using a lot of nervous energy",-1));
        list.add(new dass21model("I was worried about situations in which I might panic and make a fool\n" +
                "of myself",-1));
      list.add(new dass21model("I felt that I had nothing to look forward to",-1));
        list.add(new dass21model("I found myself getting agitated",-1));
        list.add(new dass21model("I found it difficult to relax",-1));
        list.add(new dass21model("I felt down-hearted and blue",-1));
        list.add(new dass21model("I was intolerant of anything that kept me from getting on with what I\n" +
                "was doing",-1));
        list.add(new dass21model("I felt I was close to panic",-1));
        list.add(new dass21model("I was unable to become enthusiastic about anything",-1));
        list.add(new dass21model("I felt I wasn’t worth much as a person",-1));
        list.add(new dass21model("I felt that I was rather touchy",-1));
        list.add(new dass21model("I was aware of the action of my heart in the absence of physical\n" +
                "exertion (e.g. sense of heart rate increase, heart missing a beat) ",-1));
        list.add(new dass21model("I felt scared without any good reason ",-1));
        list.add(new dass21model("I felt that life was meaningless",-1));
        adapter=new dass21adapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dass21.this, dassresult_Activity.class));
            }
        });


    }
}
package com.example.mindguard2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    ArrayList<articlemodel> list;
    RecyclerView recyclerView;
    articleadapter articleadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.articles);
        list=new ArrayList<>();
        list.add(new articlemodel("Depressive disorder (depression)","Depressive disorder (also known as depression) is a common mental disorder. It involves a depressed mood or loss of pleasure or interest in activities for long periods of time.","https://www.who.int/news-room/fact-sheets/detail/depression"));
        list.add(new articlemodel("What is depression and what can I do about it?","Depression is a mental health condition that causes a chronic feeling of emptiness, sadness, or inability to feel pleasure that may appear to happen for no clear reason...","https://www.medicalnewstoday.com/articles/8933"));
        list.add(new articlemodel("Anxiety: What it is, what to do","While anxiety symptoms vary widely, odds are good that at some point you've experienced occasional physical and emotional distress signals such as panicky breathing, your heart pounding in your chest, trouble sleeping, feelings of dread, or even loops of worry. That's normal...","https://www.health.harvard.edu/blog/anxiety-what-it-is-what-to-do-2018060113955"));
        list.add(new articlemodel("Anxiety disorders","Everyone can feel anxious sometimes, but people with anxiety disorders often experience fear and worry that is both intense and excessive. These feelings are typically accompanied by physical tension and other behavioural and cognitive symptoms...","https://www.who.int/news-room/fact-sheets/detail/anxiety-disorders"));
        list.add(new articlemodel("Stress","Stress can be defined as a state of worry or mental tension caused by a difficult situation. Stress is a natural human response that prompts us to address challenges and threats in our lives. Everyone experiences stress to some degree. The way we respond to stress, however, makes a big difference to our overall well-being.....","https://www.who.int/news-room/questions-and-answers/item/stress"));
        list.add(new articlemodel("Why stress happens and how to manage it","Stress is a natural reaction to specific demands and events, but ongoing stress can affect a person’s health and wellbeing. Tips for managing stress include exercise, setting priorities, counseling, and more......","https://www.medicalnewstoday.com/articles/145855"));
        articleadapter=new articleadapter(list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(articleadapter);
    }
}
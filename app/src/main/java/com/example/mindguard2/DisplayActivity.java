package com.example.mindguard2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;

public class DisplayActivity extends AppCompatActivity {
    TextView t;
    EditText name,password,reenterpassword,email;
    Button b1;
    FirebaseAuth auth;
    DatabaseReference mdb;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        t=findViewById(R.id.signup);
        name=findViewById(R.id.signupname);
        password=findViewById(R.id.loginpassword);
        reenterpassword=findViewById(R.id.signupreenterpassword);
        email=findViewById(R.id.loginemail);
        b1=findViewById(R.id.button2);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(DisplayActivity.this,HomeActivity.class));
            finish();
        }
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayActivity.this,LoginActivity.class));
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1,email1,pass1,pass2;
                name1=name.getText().toString();
                pass1=password.getText().toString();
                email1=email.getText().toString();
                pass2=reenterpassword.getText().toString();
                if(name1.equals("")||pass1.equals("")||email1.equals("")||pass2.equals("")){
                    FancyToast.makeText(DisplayActivity.this,"Please Enter the Credentials!!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    return;
                }
                if(!pass1.equals(pass2)){
                    FancyToast.makeText(DisplayActivity.this,"Password is not Matching",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    return;
                } else  {
                    dialog=ProgressDialog.show(DisplayActivity.this,"Signing Up","Please Wait...",true);
                    signupuser(name1,email1,pass1);
                }
            }
        });
    }

    private void signupuser(String name1, String email1, String pass1) {
        auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user= auth.getCurrentUser();
                    String userid=user.getUid();
                    mdb= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String,String>hmap=new HashMap<>();
                    hmap.put("Username",name1);
                    mdb.setValue(hmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent i=new Intent(DisplayActivity.this, HomeActivity.class);
                                i.putExtra("Username",name1);
                                i.putExtra("Userid",userid);
                                startActivity(i);
                                finish();

                            }
                            else{
                                dialog.dismiss();
                                FancyToast.makeText(DisplayActivity.this,"Sign Up Failed Please Try Again !",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
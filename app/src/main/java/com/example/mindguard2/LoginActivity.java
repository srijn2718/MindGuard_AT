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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {
    TextView t2;
    FirebaseAuth auth;
    Button login;
    EditText email,pass;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        t2=findViewById(R.id.signup);
        auth=FirebaseAuth.getInstance();
        login=findViewById(R.id.button);
        email=findViewById(R.id.loginemail);
        pass=findViewById(R.id.loginpassword);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DisplayActivity.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid,password;
                emailid=email.getText().toString();
                password=pass.getText().toString();
                if(emailid.equals("") || password.equals("")){
                    FancyToast.makeText(LoginActivity.this,"Please Enter The Credentials!",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    return;
                }
                else {
                    dialog=ProgressDialog.show(LoginActivity.this,"Logging In","Please Wait....",true);
                    loginuser(emailid,password);
                }
            }
        });
    }

    private void loginuser(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i=new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    dialog.dismiss();
                    FancyToast.makeText(LoginActivity.this,"Login Failed.. Please try Again",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });
    }
}
package com.apkglobal.mitrcapp;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button btnlog,btntech,forgt;
    TextView reg;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.et_email);
        pass=(EditText)findViewById(R.id.et_pass);
        btnlog=(Button)findViewById(R.id.btn_login);
        btntech=(Button)findViewById(R.id.btn_tech);
        reg=(TextView)findViewById(R.id.txt);
       // forgt=(Button)findViewById(R.id.btn_forgt);


        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emai = email.getText().toString().trim();
                final String password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(emai)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
               // progressBar.setVisibility(View.VISIBLE);
             auth.signInWithEmailAndPassword(emai,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                   //  progressBar.setVisibility(View.GONE);

                     if(!task.isSuccessful()){
                         if(password.length()<6)
                         {
                             pass.setError("Password is too short...");

                         }
                         else
                         {
                             Toast.makeText(MainActivity.this, "Authentication Fail", Toast.LENGTH_SHORT).show();
                         }
                     }
                     else{
                         Intent intent=new Intent(MainActivity.this,StudentActivity.class);
                         startActivity(intent);
                 }


                 }
             });

            }
        });

        btntech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emai = email.getText().toString().trim();
                final String password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(emai)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!(emai.equals("cshod@gmail.com")||(emai.equals("echod@gmail.com"))||(emai.equals("mehod@gmail.com"))||(emai.equals("cehod@gmail.com"))||(emai.equals("eehod@gmail.com"))))
                {
                    Toast.makeText(MainActivity.this, "you are not authorize", Toast.LENGTH_SHORT).show();
                    return ;
                }
                // progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(emai,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   //      progressBar.setVisibility(View.GONE);

                        if(!task.isSuccessful()){
                            if(password.length()<6)
                            {
                                pass.setError("Password is too short...");

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Authentication Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Intent intent=new Intent(MainActivity.this,TeacherActivity.class);
                            startActivity(intent);
                        }


                    }
                });


            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(j);
            }
        });
    }



}

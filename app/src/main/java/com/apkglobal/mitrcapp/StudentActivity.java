package com.apkglobal.mitrcapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {
    EditText ename,eroll_no,emarks,sub_name;
    Button view;
    EditText roll_no,name;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        view = (Button) findViewById(R.id.viewbtn);
        roll_no = (EditText) findViewById(R.id.roll_no);
        name = (EditText) findViewById(R.id.name);
        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(eroll_no.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"'", null);
                if(c.moveToFirst())
                {
                    ename.setText(c.getString(1));
                    sub_name.setText(c.getString(2));
                    emarks.setText(c.getString(3));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }
            }
        });
    }

    private void clearText() {
    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    }


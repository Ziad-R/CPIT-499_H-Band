package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp1Activity extends AppCompatActivity {
    // Declaring variables
    String username, fName, lName, pass, re_pass;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_up_1);

        // Link the layout objects with the java objects
        username = ((EditText) findViewById(R.id.username)).getText().toString();
        pass = ((EditText) findViewById(R.id.pass)).getText().toString();
        re_pass = ((EditText) findViewById(R.id.re_pass)).getText().toString();
        fName = ((EditText) findViewById(R.id.fname)).getText().toString();
        lName = ((EditText) findViewById(R.id.lname)).getText().toString();
        next = (Button) findViewById(R.id.nextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent startintent = new Intent(getApplicationContext(), SignUp2Activity.class);
                startintent.putExtra("username", username);
                startintent.putExtra("pass", pass);
                startintent.putExtra("fName", fName);
                startintent.putExtra("lName", lName);
                startActivity(startintent);
            }
        });


    }


}

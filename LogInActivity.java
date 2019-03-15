package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class LogInActivity extends AppCompatActivity {
    // Declaring variables
    String mail, pass;
    CheckBox rememberCheck;
    boolean rememberMe;
    Button logIn, signUp, forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_in);

        // Link the layout objects with the java objects
        mail = ((EditText) findViewById(R.id.mailText)).getText().toString();
        pass = ((EditText) findViewById(R.id.passText)).getText().toString();
        logIn = (Button) findViewById(R.id.loginBtn);
        signUp = (Button) findViewById(R.id.singupBtn);
        forgotPass = (Button) findViewById(R.id.forgotBtn);

        rememberCheck = (CheckBox) findViewById(R.id.rememberBtn);
        rememberMe = false;
        rememberCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rememberCheck.isChecked())
                    rememberMe = true;
                else
                    rememberMe = false;
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent startintent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(startintent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startintent = new Intent(getApplicationContext(), SignUp1Activity.class);
                startActivity(startintent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startintent = new Intent(getApplicationContext(), ForgotPass1Activity.class);
                startActivity(startintent);
            }
        });


    }

}

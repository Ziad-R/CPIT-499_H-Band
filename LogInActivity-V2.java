package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;


public class LogInActivity extends AppCompatActivity {
    // Declaring variables
    EditText username, pass;
    CheckBox rememberCheck;
    boolean rememberMe;
    Button logIn, signUp, forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_in);

        // Link the layout objects with the java objects
        username = (EditText) findViewById(R.id.userText);
        pass = (EditText) findViewById(R.id.passText);
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
                String loginSuccess;
                logIn login = new logIn();
                try {
                    loginSuccess =  login.execute().get();
                    if(loginSuccess.equalsIgnoreCase("success")){
                        Intent startintent = new Intent(getApplicationContext(), HomeActivity.class);
                        startintent.putExtra("username", username.getText().toString());
                        startActivity(startintent);
                    }else{
                        Toast.makeText(LogInActivity.this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startintent = new Intent(getApplicationContext(), SignUp1Activity.class);
                startActivity(startintent);
            }
        });



    }

    private class logIn extends AsyncTask<String, Void, String> {
        Connection conn = null;



        @Override
        protected String doInBackground(String... voids) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/evCHsmR1y8?characterEncoding=UTF8","evCHsmR1y8","Rqjo7Vw65D");


                String query = "SELECT * FROM Patients_login WHERE username = '" + username.getText().toString() + "' AND pass = '" + pass.getText().toString() + "'";

                Statement statement;
                ResultSet resultSet;
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);
                if(resultSet.next()){
                    conn.close();
                    return "success";
                }
                else{
                    conn.close();
                    return "";
                }

            }
            catch(SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}

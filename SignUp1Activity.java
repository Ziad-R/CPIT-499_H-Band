package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SignUp1Activity extends AppCompatActivity {
    // Declaring variables
    EditText username, fName, lName, pass, re_pass;
    boolean usernameExists;
    Button next;
    AwesomeValidation validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_up_1);

        validate = new AwesomeValidation(ValidationStyle.BASIC);
        // Link the layout objects with the java objects
        username = (EditText) findViewById(R.id.username);

        pass = (EditText) findViewById(R.id.pass);
        re_pass = (EditText) findViewById(R.id.re_pass);
        fName = (EditText) findViewById(R.id.fname);
        lName = (EditText) findViewById(R.id.lname);
        next = (Button) findViewById(R.id.nextButton);

        // Validate the form **Using AwesomeValidation**
        validate.addValidation(this, R.id.username, "[a-zA-Z0-9_-]+", R.string.err_userid);
        validate.addValidation(this, R.id.fname, "[a-zA-Z\\s]+", R.string.fname_err);
        validate.addValidation(this, R.id.lname, "[a-zA-Z\\s]+", R.string.lname_err);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        validate.addValidation(this, R.id.pass, regexPassword, R.string.pass_err);
        validate.addValidation(this, R.id.re_pass, R.id.pass, R.string.repass_err);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if username already exists
                checkUsername checkusername = new checkUsername();
                checkusername.execute();
                try {
                    checkusername.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(usernameExists){
                    Toast.makeText(SignUp1Activity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (validate.validate()) {
                        Intent startintent = new Intent(getApplicationContext(), SignUp2Activity.class);
                        startintent.putExtra("username", username.getText().toString());
                        startintent.putExtra("pass", pass.getText().toString());
                        startintent.putExtra("fName", fName.getText().toString());
                        startintent.putExtra("lName", lName.getText().toString());
                        startActivity(startintent);
                    } else {
                        Toast.makeText(SignUp1Activity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private class checkUsername extends  AsyncTask<String, Void, String>{
        Connection conn = null;



        @Override
        protected String doInBackground(String... voids) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/evCHsmR1y8?characterEncoding=UTF8","evCHsmR1y8","Rqjo7Vw65D");


                String query = "SELECT * FROM Patients_login WHERE username = '" + username.getText().toString() + "'";

                    Statement statement;
                    ResultSet resultSet;
                    statement = conn.createStatement();
                    resultSet = statement.executeQuery(query);
                    if(resultSet.next()){
                        usernameExists = true;
                    }else{
                        usernameExists = false;
                    }

                conn.close();
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

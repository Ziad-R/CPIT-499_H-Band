package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class SignUp2Activity extends AppCompatActivity {
    // Declaring variables
    String username, fName, lName, pass;
    NumberPicker age, height, weight, minPulse, maxPulse;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_up_2);

        // Get the transferred data from SignUp1Activity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        pass = intent.getStringExtra("pass");
        fName = intent.getStringExtra("fName");
        lName = intent.getStringExtra("lName");

        // Link the layout objects with the java objects
        age = (NumberPicker) findViewById(R.id.ageSpinner);
        height = (NumberPicker) findViewById(R.id.heightSpinner);
        weight = (NumberPicker) findViewById(R.id.weightSpinner);
        minPulse = (NumberPicker) findViewById(R.id.minPulseSpinner);
        maxPulse = (NumberPicker) findViewById(R.id.maxPulseSpinner);

        // Setting the numPicker properties
        numPickerConfig(age, 15, 100, "yrs");
        numPickerConfig(height, 130, 200, "cm");
        numPickerConfig(weight, 20, 150, "kg");
        numPickerConfig(minPulse, 40, 80, "bpm");
        numPickerConfig(maxPulse, 100, 140, "bpm");



        next = (Button) findViewById(R.id.signUp);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String success="";
                SignUp signUp = new SignUp();

                try {
                    success= signUp.execute().get();
                    
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            if(success.equalsIgnoreCase("success")){
                Toast.makeText(SignUp2Activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent startintent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(startintent);

            }

            }
        });

    }

    public void numPickerConfig(NumberPicker picker, int minValue, int maxValue, final String unit){
        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue);
        picker.setWrapSelectorWheel(false);
        picker.setValue((minValue+maxValue)/2);
        picker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%d"+unit, value);
            }
        });
    }

    private class SignUp extends  AsyncTask<String, Void, String>{
        Connection conn = null;



        @Override
        protected String doInBackground(String... voids) {
            try {


                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/evCHsmR1y8?characterEncoding=UTF8","evCHsmR1y8","Rqjo7Vw65D");


                String sql = "insert into Patients_login values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, pass);
                preparedStmt.setString(3, fName);
                preparedStmt.setString(4, lName);
                preparedStmt.setInt(5, age.getValue());
                preparedStmt.setInt(6, height.getValue());
                preparedStmt.setInt(7, weight.getValue());
                preparedStmt.setInt(8, minPulse.getValue());
                preparedStmt.setInt(9, maxPulse.getValue());
                // execute the java prepared statement
                preparedStmt.execute();
                conn.close();
                return "success";
            }
            catch(SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



            return "";
        }
    }

}


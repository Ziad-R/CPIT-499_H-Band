package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Set the view to sign_in layout
        setContentView(R.layout.sign_up_2);

        // Get the transferred data from SignUp1Activity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("pass");
        String fName = intent.getStringExtra("fName");
        String lName = intent.getStringExtra("lName");




            
            Connection conn = null;
            Statement stmt = null;

            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/evCHsmR1y8","evCHsmR1y8","Rqjo7Vw65D");
                stmt = conn.createStatement();
                
                String sql = "select * from Patients_login";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(rs.getString("username"));

                rs.close();
                stmt.close();
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


    }


}

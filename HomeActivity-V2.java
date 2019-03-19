package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
TextView pulse, temp;
String username;
Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Get the transferred username field from LogInActivity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        loadUserData loaduserdata = new loadUserData();

        try {
            loaduserdata.execute(username).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        pulse = (TextView) findViewById(R.id.pulse);
        temp = (TextView) findViewById(R.id.temp);
        new FetchDataTask().execute();

    }

    private class loadUserData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... voids) {
            Connection conn = null;
            String username = voids[0];

            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/evCHsmR1y8?characterEncoding=UTF8","evCHsmR1y8","Rqjo7Vw65D");
                patient = new Patient();

                String query = "SELECT * FROM Patients_login WHERE username = '" + username+ "'";

                Statement statement;
                ResultSet rs;
                statement = conn.createStatement();
                rs = statement.executeQuery(query);

                while (rs.next()){
                    patient.setPassword(rs.getString("pass"));
                    patient.setfName(rs.getString("fname"));
                    patient.setlName(rs.getString("lname"));
                    patient.setAge(rs.getInt("age"));
                    patient.setHeight(rs.getInt("height"));
                    patient.setWeight(rs.getInt("weight"));
                    patient.setMinPulse(rs.getInt("minPulse"));
                    patient.setMaxPulse(rs.getInt("maxPulse"));
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

    class FetchDataTask extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://thingspeak.com/channels/733891" + "733891" +
                        "/feeds/last?" + "H8HX3ELAGH3QRW5P" + "=" +
                        "H8HX3ELAGH3QRW5P" + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String response) {
            if(response == null) {
                Toast.makeText(HomeActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();

                pulse.setText(channel.getString("pulse"));
                temp.setText(channel.getString("temp"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

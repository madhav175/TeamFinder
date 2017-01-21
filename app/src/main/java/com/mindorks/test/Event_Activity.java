package com.mindorks.test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by nidhi on 1/21/17.
 */

public class Event_Activity extends Activity {
    private static final String TAG = Member_Activity.class.getSimpleName();
    private Button btnAdd;
    private EditText inputEvent;
    private EditText inputLocation;
    private EditText inputDates;
    private EditText inputTeamSize;
    private ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create_activity);

        inputEvent = (EditText) findViewById(R.id.event);
        inputLocation = (EditText) findViewById(R.id.university);
        inputTeamSize = (EditText) findViewById(R.id.teamsize);
        btnAdd = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        //session = new SessionManager(getApplicationContext());


        // Check if user is already logged in or not
       /* if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }*/

        // Register Button Click event
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String event = inputEvent.getText().toString().trim();
                String location = inputLocation.getText().toString().trim();
                String teamsize = inputTeamSize.getText().toString().trim();

                if (!event.isEmpty() && !location.isEmpty() && !teamsize.isEmpty()) {
                    registerEvent(event, location, teamsize);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter all the Event details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerEvent(final String event, final String location,
                              final String teamsize) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Adding Event ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String event = user.getString("name");
                        String idea = user.getString("idea");
                        String skills = user.getString("skills");
                        int team = Integer.valueOf(user.getString("team"));
                        String looking = user.getString("looking");
                        String created_at = user
                                .getString("created_at");


                        // send to backend service

                        Toast.makeText(getApplicationContext(), "Event successfully added. Start Looking for members now!", Toast.LENGTH_LONG).show();

                        // go back to home page in this
//                        // Launch login activity
//                        Intent intent = new Intent(
//                                Event_Activity.this,
//                                LoginActivity.class);
//                        startActivity(intent);
//                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

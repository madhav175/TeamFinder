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
import static com.mindorks.test.R.id.profile;

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
    private PrefManager pref;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create_activity);
        pref = new PrefManager(getApplicationContext());
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
    private void registerEvent(final String eventname, final String location,
                              final String teamsize) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Adding Event ...");
        showDialog();

       // final JSONObject jsonBody = new JSONObject(params);
        EventDetails event = new EventDetails();
        event.setName(eventname);
        event.setEventLocation(location);
        event.setTeamSize(Integer.valueOf(teamsize));


        GsonRequest<EventDetails> req = new GsonRequest<EventDetails>(
                com.android.volley.Request.Method.POST,
                AppConfig.URL_EVENT,
                EventDetails.class,
                event ,
                new Response.Listener<EventDetails>() {

                    @Override
                    public void onResponse(EventDetails event) {
                        pref.setEventid(event.getId());

                        Intent main = new Intent(Event_Activity.this, Member_Activity.class);

                        startActivity(main);
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req, tag_string_req);
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

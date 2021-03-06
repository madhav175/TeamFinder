package com.mindorks.test;

import android.app.Activity;
import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Maddy on 21/01/17.
 */

public class Member_Activity extends Activity {
    private static final String TAG = Member_Activity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputEvent;
    private EditText inputTeam;
    private EditText mySkills1;
    private EditText mySkills2;
    private EditText skills1;
    private EditText skills2;
    private EditText skills3;
    private EditText inputIdea;
    private EditText number;
    private EditText inputLooking;
    private ProgressDialog pDialog;
    private PrefManager pref;
   // private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mem_create_activity);
        pref = new PrefManager(getApplicationContext());
        inputEvent = (EditText) findViewById(R.id.event);
        inputTeam = (EditText) findViewById(R.id.team);
        mySkills1 = (EditText) findViewById(R.id.myskills1);
        mySkills2 = (EditText) findViewById(R.id.myskills2);
        skills1 = (EditText) findViewById(R.id.skills1);
        skills2 = (EditText) findViewById(R.id.skills2);
        skills3 = (EditText) findViewById(R.id.skills3);
        number = (EditText) findViewById(R.id.phone);

        inputIdea = (EditText) findViewById(R.id.idea);
        btnRegister = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        //session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
       /* if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }*/

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent main = new Intent(Member_Activity.this, ActivityTinder.class);
                //startActivity(main);
                String event = inputEvent.getText().toString().trim();
                String team = inputTeam.getText().toString().trim();
                String yourskills1 = skills1.getText().toString().trim();
                String yourskills2 = skills2.getText().toString().trim();
                String yourskills3 = skills3.getText().toString().trim();
                String myskills1 = mySkills1.getText().toString().trim();
                String myskills2 = mySkills2.getText().toString().trim();
                String idea = inputIdea.getText().toString().trim();
                String contact = number.getText().toString().trim();

                if (!event.isEmpty() && !team.isEmpty() && !yourskills1.isEmpty() && !myskills1.isEmpty()) {
                    String tag_string_req = "req_register";

                    pDialog.setMessage("Registering ...");
                    showDialog();
                    EventRegister eventObject = new EventRegister();
                    eventObject.setEventId(event);
                    eventObject.setIdea(idea);
                    eventObject.setUser_id(pref.getUserID());
                    eventObject.setTeamsize(Integer.valueOf(team));
                    eventObject.setPhnNbr(contact);

                    ArrayList<String> skills = new ArrayList<String>();
                    skills.add(yourskills1);
                    skills.add(yourskills2);
                    skills.add(yourskills2);
                    eventObject.setLookingForSkills(skills);

                    ArrayList<String> myskills = new ArrayList<String>();
                    myskills.add(myskills1);
                    myskills.add(myskills2);
                    eventObject.setSkillset(myskills);
                    registerUser(event,team,myskills,idea,skills);

                   /* GsonRequest<EventRegister> req = new GsonRequest<EventRegister>(
                            com.android.volley.Request.Method.POST,
                            AppConfig.URL_EVENTREGISTER,
                            EventRegister.class,
                            eventObject ,
                            new Response.Listener<EventRegister>() {

                                @Override
                                public void onResponse(EventRegister event) {
                                    pref.setMemid(event.getId());

                                    Intent main = new Intent(Member_Activity.this, ActivityTinder.class);

                                    startActivity(main);
                                    finish();

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                            hideDialog();
                        }
                    });

                    // Adding request to request queue
                    MyApplication.getInstance().addToRequestQueue(req, tag_string_req);*/


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
     /*   btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });*/

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String eventid, final String team, final ArrayList<String> myskills ,
                              final String idea,final ArrayList<String> skills ) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();
        EventRegister event = new EventRegister();
        event.setEventId(eventid);
        event.setIdea(idea);
        event.setUser_id(pref.getUserID());
        event.setLookingForSkills(skills);
        event.setSkillset(myskills);
        event.setTeamsize(Integer.valueOf(team));

        GsonRequest<EventRegister> req = new GsonRequest<EventRegister>(
                com.android.volley.Request.Method.POST,
                AppConfig.URL_EVENTREGISTER,
                EventRegister.class,
                event ,
                new Response.Listener<EventRegister>() {

                    @Override
                    public void onResponse(EventRegister event) {
                        pref.setMemid(event.getId());
                        pref.setEventid(event.getEventId());
                        pref.setUserid(event.getUser_id());

                        Intent main = new Intent(Member_Activity.this, ActivityTinder.class);

                        startActivity(main);
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
             //   if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());

                hideDialog();
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

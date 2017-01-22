package com.mindorks.test;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Maddy on 21/01/17.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private ProgressDialog pDialog;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        pref = new PrefManager(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        // Progress dialog
       /* if (Integer.valueOf(pref.getUserID() )>0){
            Intent main = new Intent(LoginActivity.this, ProfileActivity.class);
            main.putExtra("name", pref.getName());

            main.putExtra("imageUrl", pref.getUrl());
            startActivity(main);
            finish();
        }*/
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login

        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void nextActivity(Profile profile){
        if(profile != null){
            registerUser(profile);

        }
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final Profile profile) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        params.put("emailid", profile.getLastName());
        params.put("url", "google.com/");
        final JSONObject jsonBody = new JSONObject(params);
        user datain = new user();
        datain.setName(profile.getFirstName()+" "+profile.getLastName());
        datain.setId(profile.getId());
        datain.setUrl(profile.getProfilePictureUri(200,200).toString());


        GsonRequest<user> req = new GsonRequest<user>(
                com.android.volley.Request.Method.POST,
                AppConfig.URL_REGISTER,
                user.class,
                datain ,
                new Response.Listener<user>() {

            @Override
            public void onResponse(user user) {
                pref.setUserid(user.getId());
                pref.setUrl(user.getUrl());
                pref.setName(user.getName());
                Intent main = new Intent(LoginActivity.this, ProfileActivity.class);
                main.putExtra("name", user.getName());
               // main.putExtra("surname", profile.getLastName());
                main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
                startActivity(main);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());

                hideDialog();
            }
        });

       /* JsonObjectRequest req = new JsonObjectRequest(AppConfig.URL_REGISTER, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // handle response
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error
                if(error != null) Log.e("MainActivity", error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };*/
      /* final GsonRequest gsonRequest = new GsonRequest(AppConfig.URL_GET, user.class, null, new Response.Listener<user>() {

            @Override
            public void onResponse(user user) {
                String textResult = "";
               /* for(int i=0; i<products.getProducts().size(); i++){
                    Product productItem = products.getProducts().get(i);
                    textResult += "Name: " + productItem.getName() + "\n";
                    textResult += "Description: " + productItem.getDescription() + "\n";
                    textResult += "Price: $" + productItem.getPrice() + "\n\n";
                }
                tvResult.setText(textResult);*/
       /*     }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
            }
        });*/
        /*StringRequest strReq = new StringRequest(Request.Method.POST,
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
                        String uid = jObj.getString("id");
                        JSONObject user = jObj.getJSONObject("emailId");
                        String skills = user.getString("url");

                        // Inserting row in users table

                        // Launch login activity
                        Intent main = new Intent(LoginActivity.this, ProfileActivity.class);
                        main.putExtra("name", profile.getFirstName());
                        main.putExtra("surname", profile.getLastName());
                        main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
                        startActivity(main);
                        finish();
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

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", profile.getFirstName());
                params.put("name", profile.getLastName());
                params.put("url", profile.getProfilePictureUri(200,200).toString());

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }

        };*/

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

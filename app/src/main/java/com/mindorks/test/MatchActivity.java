package com.mindorks.test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.mindorks.test.swipe.TinderCard;

import java.io.IOException;
import java.io.InputStream;


public class MatchActivity extends AppCompatActivity {
    private ListView mListView;
    private PrefManager pref;
    private ProgressDialog pDialog;
    private EventRegister[] cards;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_match);
      mListView = (ListView) findViewById(R.id.recipe_list_view);
        pref = new PrefManager(getApplicationContext());
// 1
      final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
// 2
      String[] listItems = new String[recipeList.size()];
// 3
      for(int i = 0; i < recipeList.size(); i++){
          Recipe recipe = recipeList.get(i);
          listItems[i] = recipe.title;
      }
// 4
      ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
      mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
// send message
//                Intent mainIntent = new Intent(MatchActivity.this,
//                        Register.class);
//                startActivity(mainIntent);
            }
        });

       // pDialog = new ProgressDialog(this);
       // pDialog.setCancelable(false);

       // getlist();
  }


    private void getlist() {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("loading ...");
        showDialog();
        Type type = new TypeToken<ArrayList<EventRegister>>() {}.getType();


        GsonRequest<EventRegister[]> jsObjRequest = new GsonRequest<EventRegister[]>(Request.Method.GET,
                AppConfig.URL_MATCH+pref.getMemid(),
                EventRegister[].class, new Response.Listener<EventRegister[]>() {
            @Override
            public void onResponse(EventRegister[] response) {
                // ReviewsHandleOkResponse(response);
                cards = response;
                for(EventRegister e : cards){

                }
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ReviewsHandleErrorResponse(error);
                hideDialog();
            }
        });


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(jsObjRequest, tag_string_req);
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

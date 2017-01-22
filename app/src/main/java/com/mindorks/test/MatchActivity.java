package com.mindorks.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;


public class MatchActivity extends AppCompatActivity {
    private ListView mListView;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_match);
      mListView = (ListView) findViewById(R.id.recipe_list_view);
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
  }

}

package com.mindorks.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.butterknifelite.annotations.OnClick;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.mindorks.test.swipe.TinderCard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mindorks.test.R.id.event;

public class ActivityTinder extends AppCompatActivity {

    private static final String TAG = "ActivityTinder";
    private PrefManager pref;
    private ProgressDialog pDialog;
    private EventRegister[] cards;

    @BindView(R.id.swipeView)
    private SwipePlaceHolderView mSwipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder_swipe);
        pref = new PrefManager(getApplicationContext());
        ButterKnifeLite.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
       // ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        getlist();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void getlist() {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("loading ...");
        showDialog();
        Type type = new TypeToken<ArrayList<EventRegister>>() {}.getType();


        GsonRequest<EventRegister[]> jsObjRequest = new GsonRequest<EventRegister[]>(Request.Method.GET,
                AppConfig.URL_GETCARD+pref.getEventid()+"/"+pref.getUserID()+"/"+pref.getMemid(),
                EventRegister[].class, new Response.Listener<EventRegister[]>() {
            @Override
            public void onResponse(EventRegister[] response) {
               // ReviewsHandleOkResponse(response);
                cards = response;
                for(EventRegister e : cards){
                    if (e.getUser_id() == pref.getUserID())
                        continue;

                    mSwipView.addView(new TinderCard(getApplicationContext(), e, mSwipView,pref.getMemid()));
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = null;
        if (id == event) {
            return true;
        } else if (id == R.id.matches) {
            //  intent = new Intent(this, PaymentTabActivity.class);
            //  startActivity(intent);
            return true;
        }else if (id == R.id.home) {
            //intent = new Intent(this, DeliveryTabActivity.class);
            //  startActivity(intent);
            return true;
        }
        else if (id == R.id.profile) {
            intent = new Intent(this, ProfileActivity.class);
             startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipView.disableTouchSwipe();
        mSwipView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                Log.d(TAG, "onItemRemoved: " + count);
                if(count == 0){

                    /*mSwipView.addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard())
                            .addView(new TinderCard());*/
                }
            }
        });
        mSwipView.getBuilder()
//                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_VERTICAL)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setWidthSwipeDistFactor(15)
                .setHeightSwipeDistFactor(20)
                .setSwipeDecor(new SwipeDecor()
//                        .setMarginTop(300)
//                        .setMarginLeft(100)
//                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        //new TinderCard(getApplicationContext(), EventRegister, mSwipView);


        /*mSwipView.addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard())
                .addView(new TinderCard());*/
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(8000);
                    mSwipView.enableTouchSwipe();
//                    mSwipView.lockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.unlockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.lockViews();
//                    Thread.currentThread().sleep(4000);
//                    mSwipView.unlockViews();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.rejectBtn)
    private void onRejectClick(){
        mSwipView.doSwipe(false);
    }

    /* for(int i=0; i<products.getProducts().size(); i++){
                   Product productItem = products.getProducts().get(i);
                   textResult += "Name: " + productItem.getName() + "\n";
                   textResult += "Description: " + productItem.getDescription() + "\n";
                   textResult += "Price: $" + productItem.getPrice() + "\n\n";
               }
               tvResult.setText(textResult);*/
    @OnClick(R.id.acceptBtn)
    private void onAcceptClick(){

        mSwipView.doSwipe(true);
    }

    @OnClick(R.id.undoBtn)
    private void onUndoClick(){
        mSwipView.undoLastSwipe();
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

package com.mindorks.test.swipe;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;
import com.mindorks.test.AppConfig;
import com.mindorks.test.EventRegister;
import com.mindorks.test.GsonRequest;
import com.mindorks.test.MyApplication;
import com.mindorks.test.R;
import com.mindorks.test.Utils;
import com.mindorks.test.user;

/**
 * Created by janisharali on 19/08/16.
 */
@NonReusable
@Layout(R.layout.tinder_card_view)
public class TinderCard {

    private static int count;
    private EventRegister event;
    private Context mContext;
    private String id;
    private SwipePlaceHolderView mSwipeView;

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.skills)
    private TextView locationNameTxt;
    @View(R.id.idea)
    private TextView idea;

    /*@Click(R.id.idea)
    private void onClick(){
        Log.d("DEBUG", "profileImageView");
    }*/
    public TinderCard(Context context, EventRegister event, SwipePlaceHolderView swipeView,String id) {
        this.mContext = context;
        this.event =  event;
        this.mSwipeView = swipeView;
        this.id = id;
    }

    public TinderCard(Context context, EventRegister event, SwipePlaceHolderView swipeView) {
        this.mContext = context;
        this.event =  event;
        this.mSwipeView = swipeView;
    }

    @Resolve
    private void onResolve(){
        user usd = event.getUsd();
        Glide.with(mContext).load(usd.getUrl()).into(profileImageView);
        nameAgeTxt.setText(usd.getName() + ", " + event.getTeamsize());
        String skills = "";
        for(String s :event.getSkillset()){
            skills = s+ ",";
        }
        locationNameTxt.setText(skills);
        idea.setText(event.getIdea());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("DEBUG", "onSwipedOut");
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("DEBUG", "onSwipedIn");
        String tag_string_req = "req_register";

        GsonRequest<Boolean> jsObjRequest = new GsonRequest<Boolean>(Request.Method.GET,
                AppConfig.URL_GETADD+"id="+id+"&memId="+event.getId()/*pref.getEventid()+"/"+pref.getUserID()+"/"+pref.getMemid()*/,
                Boolean.class, new Response.Listener<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                // ReviewsHandleOkResponse(response);
                if (response.booleanValue())
                    Toast.makeText(mContext, "Match !", Toast.LENGTH_SHORT).show();
                //cards = response;
                //response.

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //ReviewsHandleErrorResponse(error);
                //hideDialog();
            }
        });


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(jsObjRequest, tag_string_req);
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("DEBUG", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("DEBUG", "onSwipeOutState");
    }
}

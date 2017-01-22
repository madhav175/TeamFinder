package com.mindorks.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mindorks.test.LoginActivity;
import com.mindorks.test.MainActivity;
import com.mindorks.test.R;

import java.io.InputStream;

/**
 * Created by Maddy on 21/01/17.
 */

public class ProfileActivity  extends AppCompatActivity {

    private ShareDialog shareDialog;
    private Button logout;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.profile_activity);
        pref = new PrefManager(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shareDialog = new ShareDialog(this);

        Button create = (Button) findViewById(R.id.Create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ProfileActivity.this, Event_Activity.class);
                startActivity(main);
            }
        });
        Button fab = (Button) findViewById(R.id.Join);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(ProfileActivity.this, Member_Activity.class);
                startActivity(main);
            }
        });

        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
       // String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();

        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText("" + name );
        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                pref.clearSession();
                Intent login = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
        new ProfileActivity.DownloadImage((ImageView)findViewById(R.id.profileImage)).execute(imageUrl);
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

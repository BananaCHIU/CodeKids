package com.edu.codekids;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class SignedInActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, JavaForumFragment.OnJavaFragmentInteractionListener, PascalForumFragment.OnPascalFragmentInteractionListener {

    private static final String TAG = "Error:";

    public static Intent createIntent(@NonNull Context context, @Nullable IdpResponse response) {
        return new Intent().setClass(context, SignedInActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, response);
    }

    String uid,name,email;
    Uri photoUrl;
    static User currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();
                uid = user.getUid();

                // Name, email address, and profile photo Url
                name = profile.getDisplayName();
                email = profile.getEmail();
                photoUrl = profile.getPhotoUrl();
            }
        }

        currentuser = (User) getIntent().getSerializableExtra("currentuser");
        if (currentuser == null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            currentuser = task.getResult().toObject(User.class);
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());


                            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                            setSupportActionBar(toolbar);

                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                    SignedInActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                            drawer.addDrawerListener(toggle);
                            toggle.syncState();

                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                            navigationView.setNavigationItemSelectedListener(SignedInActivity.this);
                            TextView uName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName);
                            uName.setText(name);
                            TextView uEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail);
                            uEmail.setText(email);
                            ImageView uPhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.userImage);
                            if (photoUrl != null) {
                                Picasso.with(SignedInActivity.this).load(photoUrl.toString()).into(uPhoto);
                            } else uPhoto.setImageResource(R.drawable.ic_person_white);

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            HomeFragment fragment = new HomeFragment();
                            fragmentTransaction.replace(R.id.signedIn, fragment);
                            fragmentTransaction.commitAllowingStateLoss();

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        } else {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            TextView uName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName);
            uName.setText(name);
            TextView uEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail);
            uEmail.setText(email);
            ImageView uPhoto = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.userImage);
            if (photoUrl != null) {
                Picasso.with(this).load(photoUrl.toString()).into(uPhoto);
            } else uPhoto.setImageResource(R.drawable.ic_person_white);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment fragment = new HomeFragment();
            fragmentTransaction.replace(R.id.signedIn, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_forum){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ForumFragment fragment = new ForumFragment();
            fragmentTransaction.replace(R.id.signedIn, fragment);
            fragmentTransaction.commit();
        } else if(id == R.id.nav_home){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment fragment = new HomeFragment();
            fragmentTransaction.replace(R.id.signedIn, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_learn){

        } else if (id == R.id.nav_about){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AboutUsFragment fragment = new AboutUsFragment();
            fragmentTransaction.replace(R.id.signedIn, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static User getCurrentuser(){
        return currentuser;
    }

}

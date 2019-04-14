package com.edu.codekids.Learn;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edu.codekids.R;
import com.google.android.material.chip.Chip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class T1Q1Activity extends AppCompatActivity {

    private static final String CHIP_TAG = "answer chip";
    String msg;
    Chip a1, a2;
    private Handler mHandler = new Handler();
    MediaPlayer myMus = null; // a field of MediaPlayer

    @Override
    protected void onResume(){ // callback method, active: when interacting with user
        super.onResume(); // always call superclass
        if (myMus != null) myMus.start(); // start playing
    }
    @Override
    protected void onPause(){ // callback method, inactive: when no interacting
        super.onPause(); // always call superclass
        if (myMus != null) myMus.pause(); // pause playing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1_q1);

        myMus = MediaPlayer.create(this, R.raw.q1_bgm); // bg sound file “bs” in raw folder
        myMus.setLooping(true); // set loop-playing mode

        ImageView bg = findViewById(R.id.img_t1_bg);
        ImageView pug = findViewById(R.id.img_pug);
        Glide
                .with(this)
                .load(R.mipmap.pug_001)
                .into(pug);
        Glide
                .with(this)
                .load(R.mipmap.game_background)
                        .into(bg);

        a1 = findViewById(R.id.chip_a1);
        a2 = findViewById(R.id.chip_a2);
        a1.setTag(CHIP_TAG);
        a2.setTag(CHIP_TAG);
        // Creates a new drag event listener
        myDragEventListener dragListen = new myDragEventListener();
        a1.setOnDragListener(dragListen);
        a2.setOnDragListener(dragListen);

        // Sets a long click listener for the ImageView using an anonymous listener object that
// implements the OnLongClickListener interface
        a1.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            public boolean onLongClick(View v) {

                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                ClipData dragData = new ClipData(
                        v.getTag().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                // Starts the drag

                v.startDragAndDrop(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        v,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return true;
            }
        });

        a2.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            public boolean onLongClick(View v) {

                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                ClipData dragData = new ClipData(
                        v.getTag().toString(),
                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
                        item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

                // Starts the drag

                v.startDragAndDrop(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        v,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return true;
            }
        });
    }


    protected class myDragEventListener implements View.OnDragListener {

        private ConstraintLayout.LayoutParams params;
        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
        public boolean onDrag(View v, DragEvent event) {

            View view = (View) event.getLocalState();

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            // Handles each of the expected events
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    return true;

                case DragEvent.ACTION_DROP:

                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    TextView ans = findViewById(R.id.T1Q1Ans);
                    int ansX = ans.getLeft();
                    int ansY = ans.getTop();
                    if ((x <= ansX + 600) && (x >= ansX - 600)) {
                        if ((y <= ansY + 300) && (x <= ansY - 300)) {
                            a1.setVisibility(View.INVISIBLE);
                            a2.setVisibility(View.INVISIBLE);
                            Chip c = (Chip)view;
                            String text = c.getText().toString();
                            ans.setText(text);
                            if (text.contains("Hi")){
                                ImageView img = findViewById(R.id.img_conv);
                                img.setImageResource(R.mipmap.conv_hi);
                                Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                                img.startAnimation(aniFade);
                            } else{
                                ImageView img = findViewById(R.id.img_conv);
                                img.setImageResource(R.mipmap.conv_name);
                                Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                                img.startAnimation(aniFade);
                            }
                            mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    finish();
                                }
                            }, 3000);
                        }
                    }
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}

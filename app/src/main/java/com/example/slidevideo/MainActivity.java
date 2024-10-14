package com.example.slidevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Devlin Hamill
 * CS460 Lab 2
 * Swipe application 
 */

public class MainActivity extends AppCompatActivity {
    /**
     * creates the GUI environment
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /**
         * gets the GUI information and assigns it to a variable by id
         */
        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);
        /**
         * contains the list of videos being used
         */
        List<VideoItem> videoItemsList = new ArrayList<>();
        /**
         * holds all information related to a video of a person shooting a bamboo gun
         */
        VideoItem videoBamboo = new VideoItem();
        videoBamboo.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460slideapp.appspot.com/o/Bamboo%20Creations%20with%20Mini%20Bamboo%20Slingshots%20bamboo%20bamboocrafts%20bambooart%20Diy.mp4?alt=media&token=7ac73d1c-4708-4fdd-8c92-e0c09d5a4f94";
        videoBamboo.videoTitle = "Bamboo Gun";
        videoBamboo.videoDescription = "Engineering at its finest";
        /**
         * intalizes randomization for the unique id number
         */
        Random randid = new Random();
        /**
         * gets a random interger for the unique id number
         */
        int idint = randid.nextInt(100000);
        idint *= (9) % 900001;
        videoBamboo.videoNum = "ID: "+idint;
        videoItemsList.add(videoBamboo);
        /**
         * holds the video info related to an ad within the firebase
         */
        VideoItem videoAd = new VideoItem();
        videoAd.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460slideapp.appspot.com/o/EM%2010004%20Video%20NA%20RunEscape%20GunShot%20SzmV4%20Nor%20TYDW%20F%20NA%20EN%20PC%20B%20bluedemo.mp4?alt=media&token=2c648166-2969-4eee-a542-fda4f49efaae";
        videoAd.videoTitle = "Evony";
        videoAd.videoDescription = "Play Evony for free today!";
        idint *= 2;
        idint %= 900001;
        videoAd.videoNum = "ID: "+idint;
        videoItemsList.add(videoAd);
        /**
         * holds all video information associated with a person stomping on things within the firebase
         */
        VideoItem videoStomp = new VideoItem();
        videoStomp.videoURL = "https://firebasestorage.googleapis.com/v0/b/cs460slideapp.appspot.com/o/Sepatu%20kets%20vs%20gigi%20bermata%20my%20botol%20minyak.mp4?alt=media&token=d6d4e4af-46c5-435c-9fdf-304705912923";
        videoStomp.videoTitle = "Stomp Everything!";
        videoStomp.videoDescription = "Stomp all the things!";
        idint *= 3;
        idint %= 900001;
        videoStomp.videoNum = "ID: "+idint;
        videoItemsList.add(videoStomp);

        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));

    }
}
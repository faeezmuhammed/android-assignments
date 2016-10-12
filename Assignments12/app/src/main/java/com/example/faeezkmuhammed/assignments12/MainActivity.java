package com.example.faeezkmuhammed.assignments12;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabhost = getTabHost();
        TabHost.TabSpec videospec =tabhost.newTabSpec("videos");
        videospec.setIndicator("videos");
        Intent videosintend = new Intent(this,VdoActivity.class);
        videospec.setContent(videosintend);

        TabHost.TabSpec photospec = tabhost.newTabSpec("Photos");
        photospec.setIndicator("photos");
        Intent photointent = new Intent(this,PhotoActivity.class);
        photospec.setContent(photointent);

        TabHost.TabSpec songspec = tabhost.newTabSpec("Songs");
        songspec.setIndicator("songs");
        Intent songintent = new Intent(this,SongActivity.class);
        songspec.setContent(photointent);
        tabhost.addTab(photospec);
        tabhost.addTab(videospec);
        tabhost.addTab(songspec);
    }
}
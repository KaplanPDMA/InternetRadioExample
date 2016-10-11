package com.kaplan.pdma.internetradioexample;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new MediaPlayer();
        String url = "http://mediacorp.rastream.com/950fm";
        Uri uri = Uri.parse(url);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(this, uri);
        } catch (IOException e) {
            Toast.makeText(this, "Could not set data source", Toast.LENGTH_SHORT).show();
        }

        player.prepareAsync();
        showProgressDialog();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                hideProgressDialog();
            }
        });


        ImageView class95 = (ImageView) findViewById(R.id.imageView);
        class95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.isPlaying()) {
                    player.pause();
                } else {
                    player.start();
                }
            }
        });

    }


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

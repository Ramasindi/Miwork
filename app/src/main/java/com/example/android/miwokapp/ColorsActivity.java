package com.example.android.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        //we have regained focus so we can
                        //resume playback
                        mediaPlayer.start();
                    }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> colors = new ArrayList<Word>();
        colors.add(new Word("Red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("Green","chokokki",R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("Brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("Gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("Black","kululli",R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("White","kelelli",R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("Dusty Yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colors.add(new Word("Mustard Yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));



        WordAdapter itemsAdapter = new WordAdapter(this, colors,R.color.category_colors);

        ListView listView= (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = colors.get(position);
releaseMediaPlayer();
                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        //use the music stream
                        AudioManager.STREAM_MUSIC,
                        //request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //we have a focus now
                mediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioId() );
                mediaPlayer.start();
                //set up the listener on the media player so that we can stop and release
                //after the sound has finished playing
                mediaPlayer.setOnCompletionListener(completionListener);
            }}
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //when activity is stopped ,release the media player resources because we are not playing any more audio
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.

        if (mediaPlayer != null) {

            // Regardless of the current state of the media player, release its resources

            // because we no longer need it.

            mediaPlayer.release();


            // Set the media player back to null. For our code, we've decided that

            // setting the media player to null is an easy way to tell that the media player

            // is not configured to play an audio file at the moment.

            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }

    }
}

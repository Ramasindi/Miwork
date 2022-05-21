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

public class FamilyActivity extends AppCompatActivity {
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
        final ArrayList<Word> family = new ArrayList<Word>();
        family.add(new Word("Father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("Mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("Son","ṭangsi",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("Daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("Older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("Younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("Older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("Younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("Grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("Grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        WordAdapter itemsAdapter = new WordAdapter(this, family,R.color.category_family);

        ListView listView= findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = family.get(position);
releaseMediaPlayer();
                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        //use the music stream
                        AudioManager.STREAM_MUSIC,
                        //request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    //we have a focus now
                mediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getmAudioId() );
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

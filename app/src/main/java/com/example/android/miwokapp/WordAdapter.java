package com.example.android.miwokapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.android.miwok.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int  mlayoutColor;

    public WordAdapter(Activity context, ArrayList<Word> Words, int layoutColor) {


        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.

        // the second argument is used when the ArrayAdapter is populating a single TextView.

        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not

        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, Words);
        mlayoutColor = layoutColor;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView= convertView;

        if(listView == null) {

            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listView.findViewById(R.id.miwork_text_view);

        // set this text on the miwok TextView

        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = (TextView) listView.findViewById(R.id.default_text_view);

        // set this text on the default TextView

        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView image = (ImageView) listView.findViewById(R.id.image_icons);
        // set this image on the image view
        if (currentWord.hasImage()) {
            image.setImageResource(currentWord.getmImageResourceId());
        }else{
            image.setVisibility(View.GONE);
        }
        View textContainer = listView.findViewById(R.id.layout);
        int color = ContextCompat.getColor(getContext(),mlayoutColor);
        textContainer.setBackgroundColor(color);

        //ImageButton play = (ImageButton) listView.findViewById(R.id.imageButtonPlay);

//        play.setImageResource(currentWord.getmAudioId());



        return listView;

    }



}

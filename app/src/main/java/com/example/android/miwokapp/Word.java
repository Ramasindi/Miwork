package com.example.android.miwokapp;

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioId;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;

    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in the Miwok language
     * @param imageResourceId    is the integer id of the resourceImage
     * @param audioId is the integer id of the resource audio
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int audioId) {
        mImageResourceId = imageResourceId;
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioId = audioId;
    }
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation   is the word in the Miwok language
     * @param audioId is the integer id of the resource audio
     */

    public Word(String defaultTranslation, String miwokTranslation, int audioId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioId = audioId;
    }


    /**
     * Returns the default or any language the user is familiar with (english) translation
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Returns the Miwok translation
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Returns the ImageResourceId
     */
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {

        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Returns the AudioResourceId
     */
    public int getmAudioId() {
        return mAudioId;
    }
}

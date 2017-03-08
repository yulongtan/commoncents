package edu.washington.ischool.commoncents.commoncents.Helpers;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;

/**
 * Created by keegomyneego on 3/6/17.
 */

public class ComponentHelper {

    static String TAG = "ComponentHelper";

    //----------------------------------------------------------------------------------------------
    // Singleton Pattern
    //----------------------------------------------------------------------------------------------

    private static ComponentHelper instance;

    public static ComponentHelper getInstance() {
        if (instance == null) {
            instance = new ComponentHelper();
        }

        return instance;
    }

    private ComponentHelper() {}

    //----------------------------------------------------------------------------------------------
    // Public Helper Methods
    //----------------------------------------------------------------------------------------------

    public enum PictureType {
        IN_LIST_ITEM,
        AS_BACKGROUND
    }

    /**
     * Generates a profile picture for the given event and stores
     * it in the given image view.
     *
     * @param imageView ImageView to put the pic in
     * @param event The Event to generate the picture for
     */
    public void setEventPicture(ImageView imageView, Event event, PictureType pictureType) {

        String name = event.getName();
        float saturation = 0.5f;
        float value = 0.5f;

        setPicture(imageView, name, saturation, value, pictureType);
    }

    /**
     * Generates a picture for the given friend and stores
     * it in the given image view.
     *
     * @param imageView ImageView to put the pic in
     * @param friend The Friend to generate the picture for
     */
    public void setProfilePicture(ImageView imageView, Friend friend, PictureType pictureType) {

        String name = friend.getName();
        float saturation = 0.5f;
        float value = 0.8f;

        setPicture(imageView, name, saturation, value, pictureType);
    }

    /**
     * Stylizes a string based on the amount of cents owed and puts it
     * in the given TextView
     * @param textView
     * @param centsOwed
     */
    public void setOweAmount(Context context, TextView textView, int centsOwed) {
        String amountOwed;

        if (centsOwed >= 0) {
            amountOwed = "$" + (centsOwed / 100) + "." + (centsOwed % 100);
            textView.setTextColor(ThemeHelper.getInstance(context).COLOR_AMOUNT_OWED_POSITIVE);
        } else {
            amountOwed = "($" + (-centsOwed / 100) + "." + (-centsOwed % 100) + ")";
            textView.setTextColor(ThemeHelper.getInstance(context).COLOR_AMOUNT_OWED_NEGATIVE);
        }

        textView.setText(amountOwed);
    }

    //----------------------------------------------------------------------------------------------
    // Private Helper Methods
    //----------------------------------------------------------------------------------------------

    /**
     * Generates a picture for the given friend and stores
     * it in the given image view.
     */
    private void setPicture(ImageView imageView, String name, float saturation, float value, PictureType pictureType) {

        // Generate arbitrary color based on friends name
        float hue = (float)Math.abs(name.hashCode()) % 360;

        // Calculate the initials for this friends name
        String initials;
        TextDrawable drawable;

        switch (pictureType) {
            case IN_LIST_ITEM:
                initials = name.substring(0, 2);
                drawable = TextDrawable.builder()
                        .buildRound(initials, Color.HSVToColor(new float[]{ hue, saturation, value }));
                break;

            case AS_BACKGROUND:
                initials = "";
                drawable = TextDrawable.builder()
                        .buildRect(initials, Color.HSVToColor(new float[]{ hue, saturation, value }));
                break;

            default:
                Log.e(TAG, "Unknown Picture Type!!!");
                return;
        }

        imageView.setImageDrawable(drawable);
    }
}

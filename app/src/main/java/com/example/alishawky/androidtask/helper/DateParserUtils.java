package com.example.alishawky.androidtask.helper;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alishawky on 14/07/18.
 */

public class DateParserUtils {

    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public static String dateFormat(String date) {

        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        try {
            Date date1 = parseDate(date);
            return sdf2.format(date1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date parseDate(String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        try {
            return sdf1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.danieh.javatestapp.utils;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {

    private static int screenWidth;
    private static int screenHeight;
    private static float dpiMultiplier;

    private ScreenUtils() {
    }

    public static void init(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        dpiMultiplier = context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPx(float dp) {
        return (int) (dp * dpiMultiplier + 0.5f);
    }

    public static int pxToDp(float px) {
        return (int) (px / dpiMultiplier + 0.5f);
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }
}

package io.github.loop_x.demo;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Class to build style of ripple
 */
public class RippleBuilder {

    private Context mContext;

    private int rippleColor;
    private int backgroundColor;
    private int duration; // ms
    private float ripplePivotX;
    private float ripplePivotY;
    private float finishRippleRadius;
    private float startRippleRadius;
    private Drawable backgroundDrawable;

    public RippleBuilder(Context context) {
        this.mContext = context;

        backgroundColor = -1;
        startRippleRadius = 0;
        duration = 350;
    }

    public RippleBuilder setRippleColor(int rippleColor) {
        this.rippleColor = rippleColor;
        return this;
    }

    public RippleBuilder setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public RippleBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public RippleBuilder setRipplePivotX(float ripplePivotX) {
        this.ripplePivotX = ripplePivotX;
        return this;
    }

    public RippleBuilder setRipplePivotY(float ripplePivotY) {
        this.ripplePivotY = ripplePivotY;
        return this;
    }

    public RippleBuilder setFinishRippleRadius(float maxRippleRadius) {
        this.finishRippleRadius = maxRippleRadius;
        return this;
    }

    public RippleBuilder setStartRippleRadius(float minRippleRadius) {
        this.startRippleRadius = minRippleRadius;
        return this;
    }

    public RippleBuilder setBackgroundDrawable(Drawable backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
        return this;
    }

    public int getRippleColor() {
        return rippleColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getDuration() {
        return duration;
    }

    public float getRipplePivotX() {
        return ripplePivotX;
    }

    public float getRipplePivotY() {
        return ripplePivotY;
    }

    public float getFinishRippleRadius() {
        return finishRippleRadius;
    }

    public float getStartRippleRadius() {
        return startRippleRadius;
    }

    public Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

}

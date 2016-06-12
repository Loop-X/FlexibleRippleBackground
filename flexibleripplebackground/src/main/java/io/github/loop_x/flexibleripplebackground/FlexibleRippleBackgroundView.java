package io.github.loop_x.flexibleripplebackground;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;

public class FlexibleRippleBackgroundView extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    private Paint mPaint;
    private float mRippleRadius;
    private float mRipplePivotX = Float.MAX_VALUE;
    private float mRipplePivotY = Float.MAX_VALUE;

    private RippleAnimationListener rippleAnimationListener;

    /*
    Constructors
     */

    public FlexibleRippleBackgroundView(Context context) {
        super(context);
    }

    public FlexibleRippleBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleRippleBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRipplePivotX, mRipplePivotY, mRippleRadius, mPaint);
    }

    public void setRippleRadius(float rippleRadius) {
        this.mRippleRadius = rippleRadius;
        invalidate();
    }

    public void startRipple(RippleBuilder rippleBuilder) {

        if (rippleBuilder == null) return;

        if (rippleBuilder.backgroundDrawable != null) {
            setBackgroundDrawable(rippleBuilder.backgroundDrawable);
        } else if (rippleBuilder.backgroundColor != -1) {
            setBackgroundDrawable(new ColorDrawable(rippleBuilder.backgroundColor));
        }

        // Set ripple pivot
        mRipplePivotX = rippleBuilder.ripplePivotX;
        mRipplePivotY = rippleBuilder.ripplePivotY;

        mPaint.setColor(rippleBuilder.rippleColor);

        ObjectAnimator rippleAnimation = ObjectAnimator.ofFloat(this, "rippleRadius",
                rippleBuilder.startRippleRadius, rippleBuilder.finishRippleRadius);
        rippleAnimation.setDuration(350);
        rippleAnimation.setInterpolator(new DecelerateInterpolator());
        rippleAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (rippleAnimationListener != null) {
                    rippleAnimationListener.onRippleStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (rippleAnimationListener != null) {
                    rippleAnimationListener.onRippleFinished();
                }
            }
        });

        rippleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (rippleAnimationListener != null) {
                    rippleAnimationListener.onRippleUpdate((Float) animation.getAnimatedValue());
                }
            }
        });
        rippleAnimation.start();

    }

    @Override
    public void onGlobalLayout() {

        getViewTreeObserver().removeGlobalOnLayoutListener(this);

        if (mRipplePivotX == Float.MAX_VALUE) {
            mRipplePivotX = getMeasuredWidth() / 2;
        }

        if (mRipplePivotY == Float.MAX_VALUE) {
            mRipplePivotY = getMeasuredHeight() / 2;
        }
    }

    public void setRippleAnimationListener(RippleAnimationListener rippleAnimationListener) {
        this.rippleAnimationListener = rippleAnimationListener;
    }

    public static enum RippleDirection {
        EXPAND, SHRINK
    }

    public interface RippleAnimationListener {
        void onRippleStart();

        void onRippleUpdate(float radius);

        void onRippleFinished();
    }

    public static class RippleBuilder {

        public Context context;
        private int rippleColor;
        private int backgroundColor;
        private RippleDirection rippleDirection;
        private float ripplePivotX;
        private float ripplePivotY;
        private float finishRippleRadius;
        private float startRippleRadius;
        private Drawable backgroundDrawable;

        public RippleBuilder(Context context) {
            this.context = context;

            backgroundColor = -1;
            rippleDirection = RippleDirection.EXPAND;
            startRippleRadius = 0;
        }

        public RippleBuilder setRippleColor(int rippleColor) {
            this.rippleColor = rippleColor;
            return this;
        }

        public RippleBuilder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public RippleBuilder setRippleDirection(RippleDirection rippleDirection) {
            this.rippleDirection = rippleDirection;
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
    }

    public class RippleAnimationListenerAdapter implements RippleAnimationListener {

        @Override
        public void onRippleStart() {

        }

        @Override
        public void onRippleUpdate(float radius) {

        }

        @Override
        public void onRippleFinished() {

        }
    }

}

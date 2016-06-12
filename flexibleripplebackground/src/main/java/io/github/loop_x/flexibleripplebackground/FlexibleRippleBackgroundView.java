package io.github.loop_x.flexibleripplebackground;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
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
    Inner interface which should be implemented by user to listen to animation process
    */

    public interface RippleAnimationListener {
        void onRippleStart();

        void onRippleUpdate(float radius);

        void onRippleFinished();
    }

    /*
    Constructors
     */

    public FlexibleRippleBackgroundView(Context context) {
        this(context, null);
    }

    public FlexibleRippleBackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexibleRippleBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /*
    Functions override
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRipplePivotX, mRipplePivotY, mRippleRadius, mPaint);
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

    /*
    Functions open to user
     */

    public void setRippleRadius(float rippleRadius) {
        this.mRippleRadius = rippleRadius;
        invalidate();
    }

    public void setRippleAnimationListener(RippleAnimationListener rippleAnimationListener) {
        this.rippleAnimationListener = rippleAnimationListener;
    }

    public void startRipple(RippleBuilder rippleBuilder) {

        if (rippleBuilder == null) return;

        if (rippleBuilder.getBackgroundDrawable() != null) {
            setBackgroundDrawable(rippleBuilder.getBackgroundDrawable());
        } else if (rippleBuilder.getBackgroundColor() != -1) {
            setBackgroundDrawable(new ColorDrawable(rippleBuilder.getBackgroundColor()));
        }

        // Set ripple pivot
        mRipplePivotX = rippleBuilder.getRipplePivotX();
        mRipplePivotY = rippleBuilder.getRipplePivotY();

        mPaint.setColor(rippleBuilder.getRippleColor());

        ObjectAnimator rippleAnimation = ObjectAnimator.ofFloat(this, "rippleRadius",
                rippleBuilder.getStartRippleRadius(), rippleBuilder.getFinishRippleRadius());
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

}

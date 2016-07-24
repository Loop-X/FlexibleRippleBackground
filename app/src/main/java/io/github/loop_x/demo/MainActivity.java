package io.github.loop_x.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FlexibleRippleBackgroundView tvDemo;

    private Button btnA;
    private Button btnB;
    private Button btnC;

    private Boolean isOnA;
    private Boolean isOnB;
    private Boolean isOnC;

    private int[] btnACoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDemo = (FlexibleRippleBackgroundView) findViewById(R.id.tv_demo);

        btnA = (Button) findViewById(R.id.btn_1);
        btnB = (Button) findViewById(R.id.btn_2);
        btnC = (Button) findViewById(R.id.btn_3);

        isOnA = true;
        isOnB = true;
        isOnC = true;

    }

    private float getHypotenuse(float width, float height) {
        return (float) Math.sqrt(width * width + height * height);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_1:
                if (isOnA) {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.RED)
                            .setDuration(1000)
                            .setStartRippleRadius(0f)
                            .setFinishRippleRadius(tvDemo.getHeight())
                            .setRipplePivotX(btnA.getX() + btnA.getWidth() / 2)
                            .setRipplePivotY(btnA.getY() + btnA.getHeight() / 2)
                    );
                    isOnA = false;
                } else {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.BLUE)
                            .setDuration(1000)
                            .setStartRippleRadius(tvDemo.getHeight())
                            .setFinishRippleRadius(0f)
                            .setRipplePivotX(btnA.getX() + btnA.getWidth() / 2)
                            .setRipplePivotY(btnA.getY() + btnA.getHeight() / 2)
                    );
                    isOnA = true;
                }
                break;
            case R.id.btn_2:
                if (isOnB) {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.BLUE)
                            .setDuration(1000)
                            .setStartRippleRadius(0f)
                            .setFinishRippleRadius(getHypotenuse(tvDemo.getWidth(), tvDemo.getHeight()))
                            .setRipplePivotX(0)
                            .setRipplePivotY(btnB.getY() + btnB.getHeight() / 2)
                    );
                    isOnB = false;
                } else {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.CYAN)
                            .setDuration(1000)
                            .setStartRippleRadius(getHypotenuse(tvDemo.getWidth(), tvDemo.getHeight()))
                            .setFinishRippleRadius(0f)
                            .setRipplePivotX(0)
                            .setRipplePivotY(btnB.getY() + btnB.getHeight() / 2)
                    );
                    isOnB = true;
                }
                break;
            case R.id.btn_3:
                if (isOnC) {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.BLUE)
                            .setDuration(1000)
                            .setStartRippleRadius(0f)
                            .setFinishRippleRadius(getHypotenuse(tvDemo.getWidth(), tvDemo.getHeight()))
                            .setRipplePivotX(0)
                            .setRipplePivotY(tvDemo.getY() + tvDemo.getHeight())
                    );
                    isOnC = false;
                } else {
                    tvDemo.startRipple(new RippleBuilder(this)
                            .setRippleColor(Color.DKGRAY)
                            .setDuration(1000)
                            .setStartRippleRadius(getHypotenuse(tvDemo.getWidth(), tvDemo.getHeight()))
                            .setFinishRippleRadius(0f)
                            .setRipplePivotX(0)
                            .setRipplePivotY(tvDemo.getY() + tvDemo.getHeight())
                    );
                    isOnC = true;
                }
                break;
        }
    }
}

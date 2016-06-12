package io.github.loop_x.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FlexibleRippleBackgroundView tvA;
    private Button btnA;
    private Boolean isOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvA = (FlexibleRippleBackgroundView) findViewById(R.id.tv_1);
        btnA = (Button) findViewById(R.id.btn_1);
        isOn = true;
    }

    public void onClick(View view) {

        if (isOn) {
            tvA.startRipple(new RippleBuilder(this)
                    .setRippleColor(Color.RED)
                    .setStartRippleRadius(0f)
                    .setFinishRippleRadius(tvA.getWidth())
                    .setRipplePivotX(btnA.getX() + btnA.getWidth() / 2)
                    .setRipplePivotY(btnA.getY() + btnA.getHeight() / 2)
            );
            isOn = false;
        } else {
            tvA.startRipple(new RippleBuilder(this)
                    .setRippleColor(Color.YELLOW)
                    .setStartRippleRadius(tvA.getWidth())
                    .setFinishRippleRadius(0f)
                    .setRipplePivotX(btnA.getX() + btnA.getWidth() / 2)
                    .setRipplePivotY(btnA.getY() + btnA.getHeight() / 2)
            );
            isOn = true;
        }

    }
}

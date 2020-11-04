package com.game.wolfandalfavit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView woolf, egg, bomb;
    private Button upBtn, leftBtn, rightBtn, downBtn;
    private FrameLayout frame;
    private float woolfX, woolfY;
    private float eggsX, eggsY;
    private float bombX, bombY;
    private boolean action_up, action_down, action_right, action_left;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    private int screeHeight, screenWidth;
    private int frameHeight;
    private int woolfSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        woolf = (ImageView) findViewById(R.id.woolf);

        frame = (FrameLayout) findViewById(R.id.frame);

        findViewById(R.id.upBtn).setOnTouchListener(this);
        findViewById(R.id.leftBtn).setOnTouchListener(this);
        findViewById(R.id.rightBtn).setOnTouchListener(this);
        findViewById(R.id.downBtn).setOnTouchListener(this);

        //Strart the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);

        //Initial pozitions

//        egg.setX(-80f);
//        egg.setY(-80f);
//        bomb.setX(-80f);
//        bomb.setY(-80f);





    }

    private void changePos() {

       // hitCheck();
//        eggsX -= 20;
//        if (eggsX < 0) {
//            eggsX = screenWidth + 20;
//            eggsY = (float) Math.floor(Math.random() * (frameHeight - egg.getHeight()));
//        }
//        egg.setX(eggsX);
//        egg.setY(eggsY);

        woolfX = woolf.getX();
        woolfY = woolf.getY();

        //Up
        if (action_up) woolfY -= 20;
        //Down
        if (action_down) woolfY += 20;

//        //Left
        if (action_left) {
            woolfX -= 20;
        }
//        //Right
        if (action_right) woolfX += 20;

        //Vertical Max
        if (woolfY < 0) woolfY = 0;
        if (woolfY >= frame.getHeight() - woolf.getHeight())
            woolfY = frame.getHeight() - woolf.getHeight();
        //Horizontal Max
        if (woolfX < 0) woolfX = 0;
        if (woolfX > frame.getWidth() - woolf.getWidth())
            woolfX = frame.getWidth() - woolf.getWidth();

        woolf.setX(woolfX);
        woolf.setY(woolfY);
    }

    private void hitCheck() {
        float eggsCenterX = eggsX + egg.getHeight() / 2f;
        float eggCenterY = eggsY + egg.getWidth() / 2f;

        if (0 <= eggsCenterX && eggsCenterX <= woolfSize &&
                woolfY <= eggCenterY && eggCenterY <= woolfY + woolfSize) {
            eggsX = -100;
        }

        float bombCenterX = bombX + bomb.getHeight() / 2f;
        float bombCenterY = bombY + bomb.getWidth() / 2f;

        if (0 <= bombCenterX && bombCenterX <= woolfSize &&
                woolfY <= bombCenterY && bombCenterY <= woolfY + woolfSize) {
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.upBtn:
                    action_up = true;
                    break;
                case R.id.downBtn:
                    action_down = true;
                    break;
                case R.id.leftBtn:
                    action_left = true;
                    break;
                case R.id.rightBtn:
                    action_right = true;
                    break;

            }
        } else {
            action_up = false;
            action_left = false;
            action_down = false;
            action_right = false;

        }
        return true;
    }
}
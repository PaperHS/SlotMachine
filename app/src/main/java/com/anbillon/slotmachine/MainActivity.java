package com.anbillon.slotmachine;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView img1, img2, img3;
    private ImageView img11, img21, img31;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private boolean isStarted;
    int pos1,pos2,pos3;
    int pos11,pos21,pos31;
    int height ;
    ValueAnimator animator ;

    @DrawableRes
    private static int[] imgs = {R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4,
        R.drawable.slot4,
    };
    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView) findViewById(R.id.img1);
        img11 = (ImageView) findViewById(R.id.img11);
        img21 = (ImageView) findViewById(R.id.img21);
        img31 = (ImageView) findViewById(R.id.img31);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        btn = (Button) findViewById(R.id.btn);
        msg = (TextView) findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (animator.isRunning()){
                    animator.end();
                }else animator.start();
            }
        });
        btn.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
                btn.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = img1.getHeight();
                pos1 = Math.abs(new Random().nextInt() % 5);
                pos11 = Math.abs(new Random().nextInt() % 5);
                pos21 = Math.abs(new Random().nextInt() % 5);
                pos31 = Math.abs(new Random().nextInt() % 5);

                img11.setImageResource(imgs[pos11]);
                img21.setImageResource(imgs[pos21]);
                img31.setImageResource(imgs[pos31]);

                animator = ValueAnimator.ofInt(0,height);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(8);
                animator.setRepeatCount(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override public void onAnimationUpdate(ValueAnimator animation) {
                        int t = (int) animation.getAnimatedValue() %height;
                        img1.setTranslationY(-t);
                        img11.setTranslationY(-t+height);
                        img2.setTranslationY(-t);
                        img21.setTranslationY(-t+height);
                        img3.setTranslationY(-t);
                        img31.setTranslationY(-t+height);
                    }
                });
                animator.addListener(new Animator.AnimatorListener() {
                    @Override public void onAnimationStart(Animator animation) {

                    }

                    @Override public void onAnimationEnd(Animator animation) {

                    }

                    @Override public void onAnimationCancel(Animator animation) {

                    }

                    @Override public void onAnimationRepeat(Animator animation) {
                        pos1 = pos11;
                        pos11 = Math.abs(new Random().nextInt() % 5);
                        img1.setImageResource(imgs[pos1]);
                        img11.setImageResource(imgs[pos11]);
                        pos2 = pos21;
                        pos21 = Math.abs(new Random().nextInt() % 5);
                        img2.setImageResource(imgs[pos2]);
                        img21.setImageResource(imgs[pos21]);
                        pos3 = pos31;
                        pos31 = Math.abs(new Random().nextInt() % 5);
                        img3.setImageResource(imgs[pos3]);
                        img31.setImageResource(imgs[pos31]);
                    }
                });
            }
        });



    }

}

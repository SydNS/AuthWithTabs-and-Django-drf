package Authenticating;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.moja.Posts.MainActivity;
import com.example.moja.R;

public class SplashScreen extends AppCompatActivity {
    static int progress;
    ProgressBar progressBar;
    int progressStatus = 0;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_slash_screen);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo);
        ImageView logo=(ImageView)findViewById(R.id.logo);
        logo.setAnimation(animation);

        new Thread(new Runnable() {

            @Override
            public void run() {

                //---do some work here---
                while (progressStatus < 10) {
                    progressStatus = doSomeWork();
                }
//---hides the progress bar---
                handler.post(new Runnable() {
                    public void run() {
//---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
//                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(SplashScreen.this, SignupLogin.class));
                        finish();
                    }
                });
            }

            //---do some long running work here---
            private int doSomeWork() {
                try {
//---simulate doing some work---
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();

    }
}

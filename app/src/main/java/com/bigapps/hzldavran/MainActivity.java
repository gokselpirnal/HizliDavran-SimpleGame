package com.bigapps.hzldavran;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Boolean zilCaldiMi = false,
            oyuncu1AktifMi = false,
            oyuncu2AktifMi = false,
            timerAktifMi = false;

    private Button buttonOyuncu1, buttonOyuncu2;
    private LinearLayout linearLayoutOrtaCizgi;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOyuncu1 = (Button) this.findViewById(R.id.oyuncu1);
        buttonOyuncu2 = (Button) this.findViewById(R.id.oyuncu2);
        linearLayoutOrtaCizgi = (LinearLayout) this.findViewById(R.id.orta_cizgi);

        buttonOyuncu1.setOnTouchListener(onTouchListener);
        buttonOyuncu2.setOnTouchListener(onTouchListener);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.how_to_play))
                .setMessage(getString(R.string.gameplay))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.i_understand), null).create().show();


    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (v == buttonOyuncu1 && event.getAction() == MotionEvent.ACTION_DOWN) {
                oyuncu1AktifMi = true;
            }
            if (v == buttonOyuncu1 && event.getAction() == MotionEvent.ACTION_UP) {
                oyuncu1AktifMi = false;
                if (zilCaldiMi) {
                    buttonOyuncu1.setText((Integer.parseInt(buttonOyuncu1.getText().toString()) + 1) + "");
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(buttonOyuncu1);
                    zilCaldiMi = false;
                }
            }

            if (v == buttonOyuncu2 && event.getAction() == MotionEvent.ACTION_DOWN) {
                oyuncu2AktifMi = true;
            }
            if (v == buttonOyuncu2 && event.getAction() == MotionEvent.ACTION_UP) {
                oyuncu2AktifMi = false;
                if (zilCaldiMi) {
                    buttonOyuncu2.setText((Integer.parseInt(buttonOyuncu2.getText().toString()) + 1) + "");
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .playOn(buttonOyuncu2);
                    zilCaldiMi = false;
                }
            }

            if (oyuncu1AktifMi && oyuncu2AktifMi) {
                Baslat();
                linearLayoutOrtaCizgi.setBackgroundResource(R.color.green);
            } else {
                try {
                    countDownTimer.cancel();
                    timerAktifMi = false;
                } catch (Exception ex) {

                }
                linearLayoutOrtaCizgi.setBackgroundResource(R.color.white);
            }

            return false;
        }
    };

    private void Baslat() {
        if (!timerAktifMi) {

            Random rand = new Random();
            countDownTimer = new CountDownTimer((rand.nextInt(15) + 1) * 1000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    MediaPlayer.create(MainActivity.this, R.raw.ready_sound).start();
                    zilCaldiMi = true;
                }
            };

            countDownTimer.start();
            zilCaldiMi = false;
            timerAktifMi = true;
        }
    }
}

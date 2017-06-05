package m4teo.numbersgame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler aHandler = new Handler();

        aHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent anIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(anIntent);
                finish();
            }
        }, 1000);
    }
}


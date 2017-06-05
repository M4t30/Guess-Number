package m4teo.numbersgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WonActivity extends AppCompatActivity {

    TextView attemptsTextView;
    Button playAgainButton;
    TextView rankingTextView;
    EditText userNick;
    Button sendResult;
    private SharedPreferences sharedPreferences;
    private Map<String, Integer> ranking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        sendResult = (Button) findViewById(R.id.sendResult);
        userNick = (EditText) findViewById(R.id.userNick);
        rankingTextView= (TextView)findViewById(R.id.rankingTextView);
        playAgainButton = (Button) findViewById(R.id.againButton);
        attemptsTextView = (TextView) findViewById(R.id.attempstext);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final int attemps = extras.getInt("Attemps");
            attemptsTextView.setText("Attemps: " + attemps);

            playAgainButton.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v) {
                    Handler aHandler = new Handler();
                    aHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent anIntent = new Intent(getApplicationContext(), GameActivity.class);
                            startActivity(anIntent);
                            finish();
                        }
                            },1);
                }
            });

            sendResult.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    ranking.put(userNick.getText().toString(), attemps);

                    sharedPreferences = getSharedPreferences("ranking", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    try {
                        editor.putString("UserList", ObjectSerializer.serialize(ranking));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editor.commit();
                }
            });
        }
    }
}

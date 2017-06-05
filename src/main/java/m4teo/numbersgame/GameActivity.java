package m4teo.numbersgame;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.regex.Pattern;

public class GameActivity extends AppCompatActivity {

    Button checkButton;
    EditText userAnswer;
    TextView upperTextView;
    TextView logs;
    Random randomNumber;
    Integer riddle;
    int numberOfAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        randomNumber = new Random();

        riddle = randomNumber.nextInt(100) + 1;
        checkButton = (Button) findViewById(R.id.checkButton);
        userAnswer = (EditText) findViewById(R.id.userAnswer);
        upperTextView = (TextView) findViewById(R.id.upperTextView);
        logs = (TextView) findViewById(R.id.logs);

        checkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(isInteger(userAnswer.getText().toString())) {
                    numberOfAttempts++;

                    if (Integer.parseInt(userAnswer.getText().toString()) == riddle) {
                        Handler aHandler = new Handler();

                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent anIntent = new Intent(getApplicationContext(), WonActivity.class);
                                anIntent.putExtra("Attemps", numberOfAttempts);
                                startActivity(anIntent);
                                finish();
                            }
                        },1);

                    }

                    if (Integer.parseInt(userAnswer.getText().toString()) < riddle) {
                        logs.setText("Too small!");
                    }

                    if (Integer.parseInt(userAnswer.getText().toString()) > riddle) {
                        logs.setText("Too big!");
                    }
                }
            }
        });
    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try{
            Integer.parseInt(s);
            isValidInteger = true;
        }
        catch (NumberFormatException ex){}

        return isValidInteger;
    }
}





package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ImageView rock, paper, scissors, computerImage;
    TextView computerScore,playerScore;
    ConstraintLayout parent;
    Button turn;
    boolean isRunning = false;

    float scaleBy = 1.5f;
    long duration = 200L;
    Handler handler = new Handler();

    // GAME VARS
    int COMPUTER;
    int PLAYER;
    int PLAYER_SCORE = 0;
    int COMPUTER_SCORE = 0;

    private void selectOption(int id) {
        resetPlayerImages();
        switch (id) {
            case R.id.rock:
                rock.animate().scaleX(scaleBy).scaleY(scaleBy).setDuration(duration).start();
                PLAYER = 0;
                break;
            case R.id.paper:
                paper.animate().scaleX(scaleBy).scaleY(scaleBy).setDuration(duration).start();
                PLAYER = 1;
                break;
            case R.id.scissors:
                scissors.animate().scaleX(scaleBy).scaleY(scaleBy).setDuration(duration).start();
                PLAYER = 2;
                break;
            default:
        }
    }

    private void resetPlayerImages() {
        rock.animate().scaleX(1.0f).scaleY(1.0f).setDuration(duration).start();
        paper.animate().scaleX(1.0f).scaleY(1.0f).setDuration(duration).start();
        scissors.animate().scaleX(1.0f).scaleY(1.0f).setDuration(duration).start();
    }

    private void computeTurn() {
        if (!isRunning) {
            isRunning = true;
            PLAYER = -1;
            resetPlayerImages();
            computerImage.setImageResource(R.drawable.unknown);
            COMPUTER = (int) (Math.random() * 3);
            System.out.println("computer picked " + COMPUTER);
            handler.postDelayed(() -> {
                isRunning = false;

                switch (COMPUTER) {
                    case 0:

                        computerImage.setImageResource(R.drawable.rock);
                        switch (PLAYER) {
                            case 0:
                                break;
                            case 1:
                                PLAYER_SCORE++;
                                break;
                            default:
                                COMPUTER_SCORE++;
                        }
                        break;
                    case 1:

                        computerImage.setImageResource(R.drawable.paper);
                        switch (PLAYER) {
                            case 1:
                                break;
                            case 2:
                                PLAYER_SCORE++;
                                break;
                            default:
                                COMPUTER_SCORE++;

                        }
                        break;
                    case 2:

                        computerImage.setImageResource(R.drawable.scissors);
                        switch (PLAYER) {
                            case 0:
                                PLAYER_SCORE++;
                                break;
                            case 2:
                                break;
                            default:
                                COMPUTER_SCORE++;
                        }
                        break;
                }

                System.out.println("computer :: " + COMPUTER_SCORE + " player :: " + PLAYER_SCORE);
                computerScore.setText(""+COMPUTER_SCORE);
                playerScore.setText(""+PLAYER_SCORE);
                if (COMPUTER_SCORE == PLAYER_SCORE)
                    parent.setBackgroundResource(R.drawable.draw);
                else if (COMPUTER_SCORE > PLAYER_SCORE)
                    parent.setBackgroundResource(R.drawable.losing);
                else
                    parent.setBackgroundResource(R.drawable.winning);
            }, 3500);
        }
    }

    private void init() {
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);
        computerImage = findViewById(R.id.computerImage);

        parent = findViewById(R.id.parent);

        computerScore = findViewById(R.id.computerScore);
        playerScore = findViewById(R.id.playerScore);

        turn = findViewById(R.id.turn);
        turn.setOnClickListener(v -> computeTurn());

        rock.setOnClickListener(v -> selectOption(R.id.rock));
        paper.setOnClickListener(v -> selectOption(R.id.paper));
        scissors.setOnClickListener(v -> selectOption(R.id.scissors));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        init();

    }

}

package com.example.tictactoeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean Playeroneactive;
    TextView Playeronescore,PLayertwoscore,gamestatustext;
    int playeronescorecount,playertwoscorecount,count;
    Button[] Gamebuttons=new Button[9];
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gamepositions={2,2,2,2,2,2,2,2,2};
    Button Playagainbutton,restartbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Playeronescore=findViewById(R.id.player1scoretext);
        PLayertwoscore=findViewById(R.id.player2scoretext);
        gamestatustext=findViewById(R.id.statustext);
        Gamebuttons[0]=findViewById(R.id.button0);
        Gamebuttons[1]=findViewById(R.id.button1);
        Gamebuttons[2]=findViewById(R.id.button2);
        Gamebuttons[3]=findViewById(R.id.button3);
        Gamebuttons[4]=findViewById(R.id.button4);
        Gamebuttons[5]=findViewById(R.id.button5);
        Gamebuttons[6]=findViewById(R.id.button6);
        Gamebuttons[7]=findViewById(R.id.button7);
        Gamebuttons[8]=findViewById(R.id.button8);

        for(int i=0;i<9;i++){
            Gamebuttons[i].setOnClickListener(this);
        }

        Playeroneactive=true;
        playeronescorecount=0;
        playertwoscorecount=0;
        count=0;




    }

    @Override
    public void onClick(View v) {
        if(((Button) v).getText().toString().isEmpty()){
            return;
        }
        else if(checkWinner()){
            return;
        }

        else{
            String ButtonId= v.getResources().getResourceEntryName(v.getId());
            int gamepointer=Integer.parseInt(ButtonId.substring(ButtonId.length()-1));
            if(Playeroneactive){
                ((Button)v).setText("X");
                gamepositions[gamepointer]=0;
            }
            else{
                ((Button)v).setText("X");
                gamepositions[gamepointer]=0;
            }
            count++;
            if(checkWinner()){
                if(Playeroneactive){
                    gamestatustext.setText("Player 1 won the game");
                    playeronescorecount++;
                    updateScoreCount();
                }
                else{
                    gamestatustext.setText("Player 2 won the game");
                    playertwoscorecount++;
                    updateScoreCount();

                }

            }
            else if(count==9){
                gamestatustext.setText("Game is a draw");
            }
            else {
                Playeroneactive = !Playeroneactive;
            }
        }

        Playagainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                count=0;
            }
        });

        restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playeronescorecount=0;
                playertwoscorecount=0;
                count=0;
                updateScoreCount();
            }
        });


    }

    private void playAgain() {
        for(int i=0;i<9;i++){
            Gamebuttons[i].setText("");
            gamepositions[i]=2;
        }
    }

    private void updateScoreCount() {
        Playeronescore.setText(String.format(String.valueOf(playeronescorecount)));
        PLayertwoscore.setText(String.format(String.valueOf(playertwoscorecount)));
    }

    private boolean checkWinner() {
        for(int[] i:winningpositions){
            if(gamepositions[i[0]]==gamepositions[i[1]] &&
                    gamepositions[i[1]]==gamepositions[i[2]] && gamepositions[i[0]]!=2){
                return true;
            }
        }
        return false;

    }
}
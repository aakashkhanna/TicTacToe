package com.example.sky.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //active player 0=blue 1=red
    int activePlayer=0;
    boolean isGameActive=true;

    //2 means fresh state
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPos={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int currentPlayertag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DropIn(View view){
        ImageView counter=(ImageView)view;
        TextView win=(TextView)findViewById(R.id.WinnerMessage);
        currentPlayertag=Integer.parseInt(counter.getTag().toString());
        if(gameState[currentPlayertag]==2 && isGameActive) {
            counter.setTranslationY(-1000f);
            gameState[currentPlayertag] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer++;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer--;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winner:winningPos){
                if(gameState[winner[0]]==gameState[winner[1]] && gameState[winner[1]]==gameState[winner[2]] && gameState[winner[0]]!=2){
                    System.out.println(gameState[winner[0]]);
                    if(gameState[winner[0]]==0)
                        win.setText("Yellow Player wins!");
                    else if(gameState[winner[0]]==1)
                        win.setText("Red Player wins!");
                    isGameActive=false;
                }
                else{
                    boolean gameOver=true;
                    for(int state:gameState) {
                        if (state == 2) {
                            gameOver = false;
                        }
                    }
                            if(gameOver) {
                                win.setText(" It is a draw!");
                                isGameActive = false;
                            }
                    }
                }

            }
        }



    public void reset(View view){
        for(int j=0;j<9;j++){
            gameState[j]=2;
        }
        activePlayer=0;
        TextView win=(TextView)findViewById(R.id.WinnerMessage);
        win.setText("");
        GridLayout grid=(GridLayout)findViewById(R.id.grid);
        for (int k=0;k<grid.getChildCount();k++)
        {
            ImageView curr=(ImageView)grid.getChildAt(k);
            curr.setImageResource(0);
        }
        isGameActive=true;
    }
}

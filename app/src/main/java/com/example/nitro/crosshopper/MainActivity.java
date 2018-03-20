package com.example.nitro.crosshopper;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    UpdateThread myThread;  // vlakno, posiela spravy
    Handler updateHandler;  // zachytavanc sprav
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this);
        setContentView(game);
        VytvorHandler();
        myThread = new UpdateThread(updateHandler);
        myThread.start(); // spusti vlakno


    }
    private void VytvorHandler() {
        updateHandler = new Handler(){
            public void handleMessage(Message msg) {
                game.update(); 		    // zmeni suradnice atd.
                game.invalidate();          // prekresli Canvas
                super.handleMessage(msg);   // spracuje = zabudne spravu
            }};
    }



}

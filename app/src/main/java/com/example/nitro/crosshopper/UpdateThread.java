package com.example.nitro.crosshopper;


import android.os.Handler;

/**
 * Created by Nitro on 20. 3. 2018.
 */

public class UpdateThread extends Thread{
    Handler updatovaciHandler;

    public UpdateThread(Handler uh) {
        super();
        updatovaciHandler = uh;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.sleep(100);
            } catch (Exception ex) {}
            updatovaciHandler.sendEmptyMessage(0);
        }
    }

}

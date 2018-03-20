package com.example.nitro.crosshopper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Nitro on 20. 3. 2018.
 */

public class Game extends View {
    Paint paint;
    private int body = 0;
    Bitmap pozadie, background;
    ArrayList<Strela> zoznam;
    Hero ja;
    Bitmap hrdina,strela;
    private int turbo=10;

    public Game(Context context) {
        super(context);
        paint = new Paint();
        pozadie = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.pozadie));
        background = Bitmap.createScaledBitmap(pozadie, 480, 800, false);
        zoznam = new ArrayList<Strela>();
        ja = new Hero();
        hrdina = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher));
        strela = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.jablko));


    }

    protected void onDraw(Canvas canvas) {
        Strela st;
        Bitmap nova = Bitmap.createScaledBitmap(strela,60,60,false);
        Bitmap frajeris = Bitmap.createScaledBitmap(hrdina,40,40,false);
        canvas.drawBitmap(background, 0, 0, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText("" + body, 10, 30, paint);
        for (int i = 0; i < zoznam.size(); i++) {
            st = (Strela) (zoznam.get(i));
            if(i%2==0){
            canvas.drawBitmap(nova, st.getX() + 5, st.getY() + 10, paint);}
            else{canvas.drawBitmap(nova,st.getX()-5, st.getY()-10, paint);


            }
        }
        
        canvas.drawBitmap(frajeris,ja.getX(), ja.getY(), paint);

    }

    public void update() {
        Strela mojaS;



        /* Naberianie rychlosti na zaklade score */
        if(body>9){turbo=12;
            Context mContext = this.getContext();
            Toast.makeText(mContext, "GUT!!!", Toast.LENGTH_SHORT).show();
        }
        if(body>14){turbo=14;
            Context mContext = this.getContext();
            Toast.makeText(mContext, "Baro Frajeris !!", Toast.LENGTH_SHORT).show();}
        if(body>19){turbo=16;
            Context mContext = this.getContext();
            Toast.makeText(mContext, "Ultra baro frajeris more !", Toast.LENGTH_SHORT).show();}
        if(body>24){turbo=18;
            Context mContext = this.getContext();
            Toast.makeText(mContext, "Turbo frajeris HIGH LVL ", Toast.LENGTH_SHORT).show();
        }
        /* ---------Koniec naberania rychlosti ----------*/

        if (zoznam.size() < 10) { // ak je malo striel prida novu s pravdepodobnostou 1:10
            if (Math.random() > 0.9) zoznam.add(new Strela(40 + (int) (Math.random() * 400),
                    700, 1 + (int) (Math.random() * turbo)));
        }

        if (zoznam.size() > 0) {
            for (int i = zoznam.size() - 1; i >= 0; i--) {   // prejde po zozname a pohne strelami
                mojaS = (Strela) (zoznam.get(i));   // vezme itu strelu zo zoznamu
                mojaS.Pohni();                     // pohne strelou
                if (mojaS.getY() < 20) zoznam.remove(i); // ak je mimo obrazovky vymaz
            }

            ja.Pohni(); // pohne sa herom a skontroluje sa zrazka so zoznamom striel
            if (ja.Naraz(zoznam)) {
                Context mContext = this.getContext();
                Toast.makeText(mContext, "CRASH!!!", Toast.LENGTH_SHORT).show();
                body--;
                ja.Startuj(); // presun na novy start
            }

            if (ja.vCieli()) { // ak som presiel za ciaru, zvysia sa body ...
                body++;
                Context mContext = this.getContext();
                Toast.makeText(mContext, "YEAH!!!", Toast.LENGTH_SHORT).show();
                ja.Startuj(); // ... a ide znova
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int e = event.getAction(); // vrati parametre udalosti
        if (e == MotionEvent.ACTION_DOWN) {
            //  float ix = event.getX();  // suradnice dotyku
            float iy = event.getY();
            if (iy > ja.getY()) ja.Dole();
            if (iy < ja.getY()) ja.Hore();

        }
        invalidate();
        return true;
    }
}

package com.example.nitro.crosshopper;

/**
 * Created by Nitro on 20. 3. 2018.
 */

public class Strela {
    private float x;
    private float y;
    private int rychlost;

    public Strela(float ix, float iy, int irychlost) {
        x = ix;
        y = iy;
        rychlost = irychlost;
    }

    public float getX() {return x;}
    public float getY() {return y;}

    public void Pohni(){
        y = y - rychlost;
    }

}

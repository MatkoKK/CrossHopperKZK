package com.example.nitro.crosshopper;

import java.util.ArrayList;

/**
 * Created by Nitro on 20. 3. 2018.
 */

public class Hero {
    private float x, y;

    public Hero() { Startuj(); }
    public void Dole() {   if (y < 700) y = y + 10; }
    public void Hore() {   if (y > 40) y = y - 10;  }
    public void Pohni() {  x += 5; }
    public float getX() {  return x; }
    public float getY() {  return y; }

    private boolean jeBlizko(float ax, float ay, float bx, float by) {
        return (Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by))) < 70;
    }

    public boolean Naraz(ArrayList<Strela> zoznam) {
        for(int i = 0; i < zoznam.size(); i++) {
            Strela ms = (Strela)(zoznam.get(i));
            if (jeBlizko(getX(),getY(),ms.getX(),ms.getY()))  return true;
        }
        return false;
    }

    public void Startuj() {
        x = 10; y = (int)(Math.random()*600) + 60;
    }

    public boolean vCieli() {
        if (x>450) return true;  else return false;
    }

}

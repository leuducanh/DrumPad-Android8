package techkid.leu.drumpad.model;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageButton;

import java.util.Random;

import techkid.leu.drumpad.MainActivity;

/**
 * Created by l on 4/10/2017.
 */

public class ButtonInfor {
    private long lastTime = System.currentTimeMillis();

    private int id;
    private Position p;
    private Position p1;
    private Position p2;
    private Position p3;
    private Position p4;

    private boolean alive;
    private boolean destroy;

    private int n = 4;

    public ButtonInfor(int id, Position p) {
        this.id = id;
        this.p = p;
        this.p1 = new Position(p.getX(),p.getY());
        this.p2 = new Position(p.getX(),p.getY());
        this.p3 = new Position(p.getX(),p.getY());
        this.p4 = new Position(p.getX(),p.getY());
        alive = true;
        destroy = false;
    }

    public void increase(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTime > 200){
            lastTime = currentTime;
            changeDefaultImage();
            p1.setX(p1.getX()-1);
            p2.setX(p2.getX()+1);
            p3.setY(p3.getY()-1);
            p4.setY(p4.getY()+1);

            changeImage();
        }
        if(p1.getX() < 0 && p2.getX() > 3 &&p3.getY() < 0 && p4.getY() > 3 && destroy == true){
            alive = false;
            changeDefaultImage();
        }
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void changeImage(){
            changeBackgoundColor(gernerateColor());
    }

    public void changeDefaultImage(){
        changeBackgoundColor("#ffbb33");
    }

    private void changeBackgoundColor(String color){


        if(p1.getX() >= 0){
            MainActivity.btns[p1.getX()][p1.getY()].setBackgroundColor(Color.parseColor(color));
        }
        if(p2.getX() <= 3){
            MainActivity.btns[p2.getX()][p2.getY()].setBackgroundColor(Color.parseColor(color));
        }
        if(p3 .getY() >= 0){
            MainActivity.btns[p3.getX()][p3.getY()].setBackgroundColor(Color.parseColor(color));
        }
        if(p4.getY() <= 3){
            MainActivity.btns[p4.getX()][p4.getY()].setBackgroundColor(Color.parseColor(color));
        }
        if(!destroy){
            MainActivity.btns[p.getX()][p.getY()].setBackgroundColor(Color.parseColor("#ff8800"));
        }else{
            MainActivity.btns[p.getX()][p.getY()].setBackgroundColor(Color.parseColor("#ffbb33"));
        }
    }

    public int getId() {
        return id;
    }

    public Position getP() {
        return p;
    }

    public Position getP1() {
        return p1;
    }

    public Position getP2() {
        return p2;
    }

    public Position getP3() {
        return p3;
    }

    public Position getP4() {
        return p4;
    }

    public int getN() {
        return n;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public boolean isAlive() {
        return alive;
    }
    public String gernerateColor() {
        Random r = new Random();
        int i = r.nextInt(3);
        switch (i) {
            case (0): {

                return "#ffff4444";
            }
            case (1): {

                return "#ff33b5e5";
            }
            case (2): {

                return "#ff99cc00";
            }
            case (3): {

                return "#aa66cc";
            }
        }

        return "#ff00ddff";
    }
}

package techkid.leu.drumpad;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import techkid.leu.drumpad.model.ButtonInfor;
import techkid.leu.drumpad.model.Position;

public class MainActivity extends AppCompatActivity {
    public static ImageView[][] btns;

    ArrayList<ButtonInfor> buttonInforArrayList;
    int ti = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btns = new ImageView[4][4];
        buttonInforArrayList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int resid = getResources().getIdentifier("btn" + (i*4+j+1),"id",getPackageName());
                btns[i][j] = (ImageView)findViewById(resid);

//                btns[i][j].setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                        int s1;
//                        int s2;
//                        int s3;
//                        int s4;
//                        s1 = gernerateColor();
//                        s2 = gernerateColor();
//                        s3 = gernerateColor();
//                        s4 = gernerateColor();
//
//                        int tag = Integer.parseInt((String) view.getTag());
//                        int hang = (tag - 1) / 4;
//                        int cot = (tag - 1) % 4;
//                        for (int i = 1; i < 4; i++) {
//                            if (hang + i <= 3) {
//                                btns[hang + i][cot].setBackgroundColor(s1);
//                            }
//                            if (hang - i >= 0) {
//                                btns[hang - i][cot].setBackgroundColor(s2);
//                            }
//                            if (cot + i <= 3) {
//                                btns[hang][cot + i].setBackgroundColor(s3);
//                            }
//                            if (cot - i >= 0) {
//                                btns[hang][cot - i].setBackgroundColor(s4);
//                            }
//                        }
//
//
//                        switch (motionEvent.getAction()) {
//                            case (MotionEvent.ACTION_DOWN): {
//                                view.setBackgroundColor(Color.parseColor("#ff8800"));
//                                return true;
//                            }
//
//                            case (MotionEvent.ACTION_UP): {
//                                view.setBackgroundColor(Color.parseColor("#ffbb33"));
//                                int tag1 = Integer.parseInt((String) view.getTag());
//                                int hang1 = (tag1 - 1) / 4;
//                                int cot1 = (tag1 - 1) % 4;
//                                for (int i = 0; i < 4; i++) {
//                                    if (hang + i <= 3) {
//                                        btns[hang1 + i][cot1].setBackgroundColor(Color.parseColor("#ffbb33"));
//                                    }
//                                    if (hang - i >= 0) {
//                                        btns[hang1 - i][cot1].setBackgroundColor(Color.parseColor("#ffbb33"));
//                                    }
//                                    if (cot + i <= 3) {
//                                        btns[hang1][cot1 + i].setBackgroundColor(Color.parseColor("#ffbb33"));
//                                    }
//                                    if (cot - i >= 0) {
//                                        btns[hang1][cot1 - i].setBackgroundColor(Color.parseColor("#ffbb33"));
//                                    }
//                                }
//                                return true;
//                            }
//                        }
//
//                        return true;
//                    }
//                });
            }
        }
        SoundManager.loadSoundsIntoList(this);


        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 0;

            public void run() {
                autoSetImageToButton();
                handler.postDelayed(this, 0);
            }
        };
        handler.postDelayed(runnable, 0);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ti++;
        Log.d("phantu"," " + buttonInforArrayList.size());

        for(int i = 0;i < event.getPointerCount();i++){
            int id = event.getPointerId(i);

            float xPointer = event.getX(i);
            float yPointer = event.getY(i);

            int pointerAction = event.getActionMasked();
            Position pressedButtonPosition = getPressedButtonPosition(xPointer,yPointer);

            if(pointerAction == MotionEvent.ACTION_MOVE){
                for(int j = buttonInforArrayList.size() - 1;j >= 0;j--){
                    ButtonInfor buttonInfor = buttonInforArrayList.get(j);
                    if(buttonInfor.getId() == id && !isInsideThisButtonImage(xPointer,yPointer,btns[buttonInfor.getP().getX()][buttonInfor.getP().getY()])){
                        buttonInforArrayList.get(j).setDestroy(true);
                    }
                }
            }

            if(pressedButtonPosition != null){
                if(pointerAction == MotionEvent.ACTION_POINTER_DOWN || pointerAction == MotionEvent.ACTION_DOWN || pointerAction == MotionEvent.ACTION_MOVE){

                    if(!isContainThisButtonImagePosition(pressedButtonPosition)){
                        Position p = new Position(pressedButtonPosition.getX(),pressedButtonPosition.getY());
                        ButtonInfor b = new ButtonInfor(id,p);
                        buttonInforArrayList.add(b);
                        SoundManager.playSound(btns[p.getX()][p.getY()].getTag()+"");
                        Log.d("abc",btns[p.getX()][p.getY()].getTag()+"");
                        b.changeImage();
                    }
                }
            }

            if(pointerAction == MotionEvent.ACTION_POINTER_UP || pointerAction == MotionEvent.ACTION_UP){
                for(int j = buttonInforArrayList.size() - 1;j >= 0;j--){
                    int firstId = event.getPointerId(event.getActionIndex());
                    if(buttonInforArrayList.get(j).getId() == firstId){
                        buttonInforArrayList.get(j).setDestroy(true);
                        buttonInforArrayList.get(j).changeImage();
                    }
                }
            }
        }

        return true;
    }

    private boolean isInsideThisButtonImage(float xPointer, float yPointer, ImageView btnImg) {
        int[] location = new int[2];
        btnImg.getLocationOnScreen(location);

        int top = location[1];
        int bottom = location[1] +  btnImg.getHeight();

        int left = location[0];
        int right = location[0] + btnImg.getWidth();

        if(xPointer > left && xPointer < right &&yPointer > top && yPointer < bottom){
            return true;
        }
        return false;
    }

    private boolean isContainThisButtonImagePosition(Position position){
        for(int i = 0 ;i < buttonInforArrayList.size();i++){
            ButtonInfor buttonInfor = buttonInforArrayList.get(i);
            if(buttonInfor.getP().getX() == position.getX() && buttonInfor.getP().getY() == position.getY() && !buttonInfor.isDestroy()){
                return true;
            }
        }
        return false;
    }

    private Position getPressedButtonPosition(float pointX, float pointY){
        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 4;j++){
                if(isInsideThisButtonImage(pointX,pointY,btns[i][j])){
                    return new Position(i,j);
                }
            }
        }
        return null;
    }

    private void autoSetImageToButton(){

        Iterator<ButtonInfor> i = buttonInforArrayList.iterator();
        while(i.hasNext()){
            ButtonInfor b = i.next();
            b.increase();
        }

        i = buttonInforArrayList.iterator();
        while(i.hasNext()){

            ButtonInfor b = i.next();
            if(!b.isAlive()){
                i.remove();
            }
        }

    }

    public int gernerateColor() {
        Random r = new Random();
        int i = r.nextInt(3);
        switch (i) {
            case (0): {

                return Color.RED;
            }
            case (1): {

                return Color.BLUE;
            }
            case (2): {

                return Color.CYAN;
            }
            case (3): {

                return Color.GREEN;
            }
        }

        return Color.GREEN;
    }
}

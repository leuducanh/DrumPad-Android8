package techkid.leu.drumpad;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    ImageButton btn7;
    ImageButton btn8;
    ImageButton btn9;
    ImageButton btn10;
    ImageButton btn11;
    ImageButton btn12;
    ImageButton btn13;
    ImageButton btn14;
    ImageButton btn15;
    ImageButton btn16;

    ImageButton[][] btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn1 = (ImageButton)findViewById(R.id.btn1);
//        btn2 = (ImageButton)findViewById(R.id.btn);
//        btn3 = (ImageButton)findViewById(R.id.btn);
//        btn4 = (ImageButton)findViewById(R.id.btn);
//        btn5 = (ImageButton)findViewById(R.id.btn);
//        btn6 = (ImageButton)findViewById(R.id.btn);
//        btn7 = (ImageButton)findViewById(R.id.btn);
//        btn8 = (ImageButton)findViewById(R.id.btn);
//        btn9 = (ImageButton)findViewById(R.id.btn);
//        btn10 = (ImageButton)findViewById(R.id.btn);
//        btn11 = (ImageButton)findViewById(R.id.btn);
//        btn12 = (ImageButton)findViewById(R.id.btn);
//        btn13 = (ImageButton)findViewById(R.id.btn);
//        btn14 = (ImageButton)findViewById(R.id.btn);
//        btn15 = (ImageButton)findViewById(R.id.btn);
//        btn16 = (ImageButton)findViewById(R.id.btn);

        btns = new ImageButton[4][4];

        btns[0][0] = (ImageButton) findViewById(R.id.btn1);
        btns[0][1] = (ImageButton) findViewById(R.id.btn2);
        btns[0][2] = (ImageButton) findViewById(R.id.btn3);
        btns[0][3] = (ImageButton) findViewById(R.id.btn4);
        btns[1][0] = (ImageButton) findViewById(R.id.btn5);
        btns[1][1] = (ImageButton) findViewById(R.id.btn6);
        btns[1][2] = (ImageButton) findViewById(R.id.btn7);
        btns[1][3] = (ImageButton) findViewById(R.id.btn8);
        btns[2][0] = (ImageButton) findViewById(R.id.btn9);
        btns[2][1] = (ImageButton) findViewById(R.id.btn10);
        btns[2][2] = (ImageButton) findViewById(R.id.btn11);
        btns[2][3] = (ImageButton) findViewById(R.id.btn12);
        btns[3][0] = (ImageButton) findViewById(R.id.btn13);
        btns[3][1] = (ImageButton) findViewById(R.id.btn14);
        btns[3][2] = (ImageButton) findViewById(R.id.btn15);
        btns[3][3] = (ImageButton) findViewById(R.id.btn16);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                btns[i][j].setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        int s1;
                        int s2;
                        int s3;
                        int s4;
                        s1 = gernerateColor();
                        s2 = gernerateColor();
                        s3 = gernerateColor();
                        s4 = gernerateColor();

                        int tag = Integer.parseInt((String) view.getTag());
                        System.out.println(tag + " ----------- ");
                        int hang = (tag - 1) / 4;
                        int cot = (tag - 1) % 4;
                        for (int i = 1; i < 4; i++) {
                            if (hang + i <= 3) {
                                btns[hang + i][cot].setBackgroundColor(s1);
                            }
                            if (hang - i >= 0) {
                                btns[hang - i][cot].setBackgroundColor(s2);
                            }
                            if (cot + i <= 3) {
                                btns[hang][cot + i].setBackgroundColor(s3);
                            }
                            if (cot - i >= 0) {
                                btns[hang][cot - i].setBackgroundColor(s4);
                            }
                        }


                        switch (motionEvent.getAction()) {
                            case (MotionEvent.ACTION_DOWN): {
                                view.setBackgroundColor(Color.parseColor("#ff8800"));
                                return true;
                            }

                            case (MotionEvent.ACTION_UP): {
                                view.setBackgroundColor(Color.parseColor("#ffbb33"));
                                int tag1 = Integer.parseInt((String) view.getTag());
                                int hang1 = (tag1 - 1) / 4;
                                int cot1 = (tag1 - 1) % 4;
                                for (int i = 0; i < 4; i++) {
                                    if (hang + i <= 3) {
                                        btns[hang1 + i][cot1].setBackgroundColor(Color.parseColor("#ffbb33"));
                                    }
                                    if (hang - i >= 0) {
                                        btns[hang1 - i][cot1].setBackgroundColor(Color.parseColor("#ffbb33"));
                                    }
                                    if (cot + i <= 3) {
                                        btns[hang1][cot1 + i].setBackgroundColor(Color.parseColor("#ffbb33"));
                                    }
                                    if (cot - i >= 0) {
                                        btns[hang1][cot1 - i].setBackgroundColor(Color.parseColor("#ffbb33"));
                                    }
                                }
                                return true;
                            }
                        }

                        return true;
                    }
                });
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

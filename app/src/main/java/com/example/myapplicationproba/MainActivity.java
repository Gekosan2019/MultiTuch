package com.example.myapplicationproba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    StringBuilder str = new StringBuilder();
    TextView res;
    int upPI = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = " ";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = new TextView(this);
        res.setTextSize(20);
        res.setOnTouchListener(this);
        setContentView(res);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();

        switch(actionMask){
            case MotionEvent.ACTION_DOWN:
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                downPI = pointerIndex;
                break;
            case MotionEvent.ACTION_UP:
                inTouch = false;
                str.setLength(0);
            case MotionEvent.ACTION_POINTER_UP:
                upPI = pointerIndex;
                break;
            case MotionEvent.ACTION_MOVE:
                str.setLength(0);

                for(int i = 0; i < 10; ++i){
                    str.append("Index = " + i);
                    if(i < pointerCount){
                        str.append(", ID = " + event.getPointerId(i));
                        str.append(", X = " + event.getX(i));
                        str.append(", Y = " + event.getY(i));
                    }
                    else{
                        str.append(", ID = ");
                        str.append(", X = ");
                        str.append(", Y = ");
                    }
                    str.append("\r\n");
                }
                break;
        }
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";
        if(inTouch){
            result += "pointerCount = " + pointerCount + "\n" + str.toString();
        }
        res.setText(result);
        return true;
    }
}
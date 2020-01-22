package Joy.Pack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    float[] hsv = new float[3];
    ConstraintLayout mConstraintLayout; // variable that stores layout information


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hsv[0] = 0.0f; // Hue
        hsv[1] = 0.0f; // Saturation
        hsv[2] = 1.0f; // Value

        final Button header = (Button) findViewById(R.id.button2);
       header.setOnClickListener(this);

       final Button left = (Button) findViewById(R.id.button3);
        left.setOnClickListener(this);

        final Button right = (Button) findViewById(R.id.button);
        right.setOnClickListener(this);

    }

    public void changeBackgroundColour(MotionEvent event) {
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.layoutID);
        float eventX = event.getX();
        float eventY = event.getY();
        float height = mConstraintLayout.getHeight(); // make sure the ref is declared and initialised (this is a reference to your root layout)
        float width = mConstraintLayout.getWidth();
        hsv[0] = eventY / height * 360; // (0 to 360)
        hsv[1] = eventX / width + 0.1f; // (0.1 to 1)
        mConstraintLayout.setBackgroundColor(Color.HSVToColor(hsv));
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button2:
                Toast.makeText(getApplicationContext(), "header Clicked", Toast.LENGTH_SHORT).show();
               break;
           case R.id.button:
                Toast.makeText(getApplicationContext(), "right Clicked", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.button3:
                Toast.makeText(getApplicationContext(), "left Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final Button header = (Button) findViewById(R.id.button2);

        switch (event.getAction()) {
            case(MotionEvent.ACTION_DOWN) : header.setText("MOVE"); return true;
            case (MotionEvent.ACTION_UP) :  header.setText("UP");  return true;
            case (MotionEvent.ACTION_MOVE) :  changeBackgroundColour(event);  return true;
            default: return super.onTouchEvent(event);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.layoutID);
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP: hsv[2] +=0.1f;
            mConstraintLayout.setBackgroundColor(Color.HSVToColor(hsv));
                return false;
            case KeyEvent.KEYCODE_VOLUME_DOWN: hsv[2] +=-0.1f;
            mConstraintLayout.setBackgroundColor(Color.HSVToColor(hsv));
                return false;
            default: return false;
        }
    }
}

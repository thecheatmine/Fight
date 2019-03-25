package fr.iutlens.mmi.fight.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import fr.iutlens.mmi.fight.GameView;
import fr.iutlens.mmi.fight.MainActivity;
import fr.iutlens.mmi.fight.R;
import fr.iutlens.mmi.fight.SelectionActivity;


/**
 * TODO: document your custom view class.
 */
public class Firebutton extends View {

    private Paint paint, ray;
    private double angle;
    private float r;
    private double length;



    public Firebutton(Context context) {
        super(context);
        init(null, 0);
    }

    public Firebutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Firebutton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float xc;
        float yc;
        xc = getWidth()/2;
        yc = getHeight()/2;


        if(length == 0){
            paint.setColor(0x40FFFFFF);
            canvas.drawCircle(xc,yc, 50,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if ((event.getAction() & (MotionEvent.ACTION_UP))!=0){
            invalidate();
            return true;
        }

        //Lancer balle
        //gameView = findViewById(R.id.gameView);
        //gameView.onFire();
        //mainActivity.onFire(this);
        invalidate();

        return true;
    }


}

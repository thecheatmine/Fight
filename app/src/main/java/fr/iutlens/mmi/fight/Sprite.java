package fr.iutlens.mmi.fight;

import android.graphics.Canvas;
import android.graphics.RectF;

import fr.iutlens.mmi.fight.utils.SpriteSheet;

/**
 * Created by dubois on 05/12/2018.
 */

 class Sprite {

    protected final SpriteSheet sprite;
    protected int state;

    protected float x;
    protected float y;

    protected float vx,vy;

    public boolean hit;



    Sprite(int id, float x, float y){
        this.sprite = SpriteSheet.get(id);
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;

        state = 0;
        hit = false;
    }

    Sprite(int id, float x, float y, float speed, float angle) {
        this(id,x,y);
        setSpeed(speed,angle);
    }


    public void setSpeed(float speed, float angle){
        double anglerad = Math.toRadians(angle);
        this.vx = (float) (speed*Math.cos(anglerad));
        this.vy = (float) (-speed*Math.sin(anglerad));
    }


    public boolean act(boolean out){
        if(out) {
            x += vx;
            y += vy;
        }
        else {
            if(x + vx >= 0 && x + vx + sprite.w <= GameView.SIZE_X){
                x += vx;
            }
            if(y + vy >= 0 && y + vy + sprite.h <= GameView.SIZE_Y) {
                y += vy;
            }
        }
        return hit;
    }


    public void paint(Canvas canvas){
        sprite.paint(canvas,state,x,y);
    }

    public RectF getBoundingBox() {
        return new RectF(x,y,x+sprite.w,y+sprite.h);
    }
}

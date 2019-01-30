package fr.iutlens.mmi.fight;

import android.graphics.RectF;

import java.util.List;

import fr.iutlens.mmi.fight.utils.SpriteSheet;

/**
 * Created by dubois on 05/12/2018.
 */

class Perso extends Sprite {
    public static final int SPEED = 8;
    private final List<Sprite> laser;
    private final int dxLaser;
    private int frame;

    Perso(int id, float x, float y, List<Sprite> laser) {
        super(id, x, y);
        this.laser = laser;
        dxLaser = sprite.w;
    }

    @Override
    public boolean act(boolean out) {
        super.act(out);

        if(vx != 0 && vy != 0) {
            frame++;
        }

        if(vx > vy && vx > -vy){
            state = 8;
        }
        else if(-vx > vy && vx > vy){
            state = 12;
        }
        else if(vx < vy && -vx < vy){
            state = 0;

        }
        else {
            //S'il ne bouge pas
            state = 4;
        }
        state += (frame /16)%4;
        return false;
    }

    public void fire() {
        laser.add(new Sprite(R.mipmap.bullet,x-sprite.w/2+dxLaser,y+sprite.h/2, SPEED, 0));
    }
}

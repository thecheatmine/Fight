package fr.iutlens.mmi.fight;

import java.util.List;

/**
 * Created by dubois on 05/12/2018.
 */

class Perso extends Sprite {
    public static final int SPEED = 4;
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
        if(!out) {
            if(vx != 0 && vy != 0) {
                frame++;
                state = 4;
                state += (frame /20)%4;
            }
            else if (vx == 0 && vy == 0){
                //S'il ne bouge pas
                if (state >= 0 && state < 4) state = 16;
                if (state >= 12 && state < 16) state = 17;
                if (state >= 4 && state < 8) state = 18;
                if (state >= 8 && state < 12) state = 19;
            }
            if(vx > vy && vx > -vy){
                state = 8;
                state += (frame /20)%4;
            }
            else if(-vx > vy && vx > vy){
                state = 12;
                state += (frame /20)%4;
            }
            else if(vx < vy && -vx < vy){
                state = 0;
                state += (frame /20)%4;
            }
        }
        return false;
    }

    public void fire(boolean sens) {
        if(sens) laser.add(new Sprite(R.mipmap.sprite_balle,x-sprite.w/2+dxLaser,y+sprite.h/2, SPEED, 0));
        else laser.add(new Sprite(R.mipmap.sprite_balle2,x-sprite.w/2+dxLaser,y+sprite.h/2, SPEED, 180));
    }
}

package fr.iutlens.mmi.fight;

import android.graphics.RectF;

import java.util.List;

import fr.iutlens.mmi.fight.utils.SpriteSheet;

/**
 * Created by dubois on 05/12/2018.
 */

class Perso extends Sprite {
    public static final int SPEED = 10;
    private final List<Sprite> laser;
    private final int dxLaser;

    Perso(int id, float x, float y, List<Sprite> laser) {
        super(id, x, y);
        this.laser = laser;
        dxLaser = sprite.w;
    }

    public void fire() {
        laser.add(new Sprite(R.mipmap.bullet,x-sprite.w/2+dxLaser,y+sprite.h/2, SPEED, 0));
    }
}

package fr.iutlens.mmi.fight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.iutlens.mmi.fight.utils.Pad;
import fr.iutlens.mmi.fight.utils.RefreshHandler;
import fr.iutlens.mmi.fight.utils.SpriteSheet;
import fr.iutlens.mmi.fight.utils.TimerAction;


public class GameView extends View implements TimerAction {
    private RefreshHandler timer;

    // taille de l'écran virtuel
    public final static int SIZE_X = 822;
    public final static int SIZE_Y = 414;

    // transformation (et son inverse)
    private Matrix transform;
    private Matrix reverse;

    //liste des sprites à afficher


    private List<Sprite> laserA, laserB;
    private Pad pad;
    private History history;
    private boolean fire;


    public GameView(Context context) {
        super(context);
        init(null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * Initialisation de la vue
     *
     * Tous les constructeurs (au-dessus) renvoient ici.
     *
     * @param attrs
     * @param defStyle
     */
    private void init(AttributeSet attrs, int defStyle) {
        SpriteSheet.register(R.mipmap.spritepersoblue,4,4,this.getContext());
        SpriteSheet.register(R.mipmap.spritepersored,4,4,this.getContext());
        SpriteSheet.register(R.mipmap.spritefantomeblue,4,4,this.getContext());
        SpriteSheet.register(R.mipmap.spritefantomered,4,4,this.getContext());
        SpriteSheet.register(R.mipmap.arena,1,1,this.getContext());
        SpriteSheet.register(R.mipmap.bullet,2,1,this.getContext());



        transform = new Matrix();
        reverse = new Matrix();

        laserA = new ArrayList<>();
        laserB = new ArrayList<>();

/*        persoA = new Perso(R.mipmap.spritepersoblue,50, 200,laserA);
        persoB = new Perso(R.mipmap.spritepersored,850, 300,laserB);
*/
        history = new History(laserA,laserB);


//        hero = new Hero(R.drawable.running_rabbit,SPEED);



        // Gestion du rafraichissement de la vue. La méthode update (juste en dessous)
        // sera appelée toutes les 30 ms
        timer = new RefreshHandler(this);

        // Un clic sur la vue lance (ou relance) l'animation
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timer.isRunning()) timer.scheduleRefresh(16);
            }
        });
    }


    public static void act(List list){
        Iterator it = list.iterator();
        while (it.hasNext()) if (((Sprite) it.next()).act(true)) it.remove();
    }
    /**
     * Mise à jour (faite toutes les 30 ms)
     */
    @Override
    public void update() {
        if (this.isShown()) { // Si la vue est visible
            timer.scheduleRefresh(16); // programme le prochain rafraichissement


            if (pad != null){
                history.moveA((float) pad.getLength()*5, -(float) Math.toDegrees(pad.getAngle()));
                if (fire) history.fireA();

                fire = false;
            }

            history.act();
            act(laserA);
            act(laserB);

            for(int i=0; i < laserA.size(); i++) {
                if(laserA.get(i).x > GameView.SIZE_X || laserA.get(i).x < 0){
                    laserA.remove(i);
                }
            }

            invalidate(); // demande à rafraichir la vue
        }
    }

    /**
     * Méthode appelée (automatiquement) pour afficher la vue
     * C'est là que l'on dessine le décor et les sprites
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.concat(transform);


        SpriteSheet.get(R.mipmap.arena).paint(canvas,0,0,0);

        // On choisit la transformation à appliquer à la vue i.e. la position
        // de la "camera"


        for(Sprite s : laserA){
            s.paint(canvas);
        }
        for(Sprite s : laserB){
            s.paint(canvas);
        }
        history.paint(canvas);



        // Dessin des différents éléments
/*        level.paint(canvas,current_pos);

        float x = 1;
        float y = hero.getY();
        hero.paint(canvas,level.getX(x),level.getY(y));
*/
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setZoom(w, h);
    }

    /***
     * Calcul du centrage du contenu de la vue
     * @param w
     * @param h
     */
    private void setZoom(int w, int h) {
        if (w<=0 ||h <=0) return;

        // Dimensions dans lesquelles ont souhaite dessiner
        RectF src = new RectF(0,0,SIZE_X,SIZE_Y);

        // Dimensions à notre disposition
        RectF dst = new RectF(0,0,w,h);

        // Calcul de la transformation désirée (et de son inverse)
        transform.setRectToRect(src,dst, Matrix.ScaleToFit.CENTER);
        transform.invert(reverse);
    }


    public void onFire(){
        this.fire = true;

    }

    public void setPad(Pad pad) {
        this.pad = pad;
    }
}

package fr.iutlens.mmi.fight;

/**
 * Created by dubois on 09/01/2019.
 */

public class MoveToCommand extends Command {
    float x,y;

    public MoveToCommand(long timestamp, String target, float x, float y) {
        super(timestamp, target);
        this.x = x;
        this.y = y;
    }

    @Override
    public void apply(History history) {
        Perso p = history.perso.get(target);
        p.x = x;
        p.y = y;
    }
}

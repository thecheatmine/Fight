package fr.iutlens.mmi.fight;

/**
 * Created by dubois on 09/01/2019.
 */

public class MoveCommand extends Command {
    public final float speed;
    public final float direction;

    public MoveCommand(long timestamp, String target, float speed, float direction) {
        super(timestamp, target);
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public void apply(History history) {
        Perso p = history.perso.get(target);
        p.setSpeed(speed, direction);

    }
}

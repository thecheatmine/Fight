package fr.iutlens.mmi.fight;

/**
 * Created by dubois on 09/01/2019.
 */

public class FireCommand extends Command {

    public FireCommand(long timestamp, String target) {
        super(timestamp, target);
    }

    @Override
    public void apply(History history) {
        Perso p = history.perso.get(target);
        p.fire();
    }
}

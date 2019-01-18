package fr.iutlens.mmi.fight;

/**
 * Created by dubois on 09/01/2019.
 */

abstract class Command {
    public final long timestamp;
    public final String target;

    public Command(long timestamp, String target) {
        this.timestamp = timestamp;
        this.target = target;
    }

    abstract public void apply (History history);
}

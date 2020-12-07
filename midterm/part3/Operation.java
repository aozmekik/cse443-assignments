
/**
 * Simple implementation of Command Design pattern Operation defines the
 * commands which consist of the command the reverse of it. So that undo
 * operation can be done.
 * 
 */

public interface Operation {
    public boolean operate();

    public boolean reverse();
}

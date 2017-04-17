package actions;

import eg.edu.alexu.csd.oop.paint.ShapeIF;

public class Actions {

    private int indexOfState;

    private ShapeIF currentState, nextState;

    public Actions(ShapeIF before, ShapeIF after, int index) {
        currentState = before;
        nextState = after;
        indexOfState = index;
    }

    public ShapeIF executeUndo() {
        return currentState;
    }

    public ShapeIF executeRedo() {
        return nextState;
    }

    public int getIndex() {
        return this.indexOfState;
    }

}

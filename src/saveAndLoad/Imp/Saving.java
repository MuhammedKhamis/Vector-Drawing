package saveAndLoad.Imp;

import java.util.LinkedList;
import java.util.Stack;

import actions.Actions;
import eg.edu.alexu.csd.oop.paint.ShapeIF;

public class Saving {

    private LinkedList<ShapeIF> shapes;
    private Stack<Actions> undo;
    private Stack<Actions> redo;

    public Saving(Stack<Actions> undo, Stack<Actions> redo, LinkedList<ShapeIF> shapes) {
        this.shapes = shapes;
        this.redo = redo;
        this.undo = undo;
    }

    public Stack<Actions> getUndo() {
        return undo;
    }

    public Stack<Actions> getRedo() {
        return redo;

    }

    public LinkedList<ShapeIF> getShapes() {
        return shapes;

    }

}

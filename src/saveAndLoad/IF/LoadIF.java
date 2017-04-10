package saveAndLoad.IF;

import java.util.LinkedList;
import java.util.Stack;

import actions.Actions;
import eg.edu.alexu.csd.oop.paint.ShapeIF;

public interface LoadIF {

    public boolean readAsJson();
    
    public boolean readAsXml();

    public Stack<Actions> loadUndo();

    public Stack<Actions> loadRedo();

    public LinkedList<ShapeIF> loadShapes();

}

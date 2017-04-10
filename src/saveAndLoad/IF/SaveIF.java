package saveAndLoad.IF;

import java.util.LinkedList;
import java.util.Stack;

import actions.Actions;
import eg.edu.alexu.csd.oop.paint.ShapeIF;

public interface SaveIF {

    public void saveAsXml();

    public void saveAsJson();

    public void read(Stack<Actions> undo, Stack<Actions> redo, LinkedList<ShapeIF> shapes, String path, boolean flag);

}

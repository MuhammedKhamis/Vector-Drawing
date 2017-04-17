package saveAndLoad.Imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import actions.Actions;
import eg.edu.alexu.csd.oop.paint.ShapeIF;
import saveAndLoad.IF.LoadIF;

public class LoadImp implements LoadIF {

    private Reader fileReader;
    private XStream xstream;
    private Saving save;
    private String path;
    private int flag;

    public LoadImp(String path) {
        // TODO Auto-generated constructor stub
        this.path = path;

        File file = new File(path);
        flag = (int) file.length();
        System.out.println(flag);

    }

    @Override
    public Stack<Actions> loadUndo() {
        // TODO Auto-generated method stub
        return save.getUndo();

    }

    @Override
    public Stack<Actions> loadRedo() {
        // TODO Auto-generated method stub
        return save.getRedo();

    }

    @Override
    public LinkedList<ShapeIF> loadShapes() {
        // TODO Auto-generated method stub
        return save.getShapes();

    }

    @Override
    public boolean readAsJson() {
        // TODO Auto-generated method stub
        if (flag == 0) {
            return false;
        }
        xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.alias("save", Saving.class);
        try {
            fileReader = new FileReader(path);
            save = (Saving) xstream.fromXML(fileReader);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public boolean readAsXml() {
        // TODO Auto-generated method stub
        if (flag == 0) {
            return false;
        }
        xstream = new XStream(new StaxDriver());
        xstream.alias("Saving", Saving.class);
        try {
            fileReader = new FileReader(path);
            save = (Saving) xstream.fromXML(fileReader);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;

    }

}

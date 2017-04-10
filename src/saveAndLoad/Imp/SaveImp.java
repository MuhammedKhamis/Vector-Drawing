//package saveAndLoad.Imp;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Stack;
//
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
//import com.thoughtworks.xstream.io.xml.StaxDriver;
//
//import actions.Actions;
//import eg.edu.alexu.csd.oop.paint.ShapeIF;
//
//public class SaveImp implements saveAndLoad.IF.SaveIF {
//
//    private XStream xstream;
//    private StringBuilder saver;
//    private String path;
//    private FileWriter writer = null;
//    private Saving save;
//
//    @Override
//    public void saveAsXml() {
//        // TODO Auto-generated method stub
//        xstream = new XStream(new StaxDriver());
//        File file = new File(path);
//        try {
//            if (!file.exists())
//                file.createNewFile();
//            writer = new FileWriter(file, false);
//            writer.write(xstream.toXML(save));
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void saveAsJson() {
//        // TODO Auto-generated method stub
//        xstream = new XStream(new JettisonMappedXmlDriver());
//        xstream.setMode(XStream.NO_REFERENCES);
//        xstream.alias("save", Saving.class);
//        File file = new File(path);
//        try {
//            if (!file.exists())
//                file.createNewFile();
//            writer = new FileWriter(file, false);
//            writer.write(xstream.toXML(save));
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void read(Stack<Actions> undo, Stack<Actions> redo, LinkedList<ShapeIF> shapes, String path, boolean flag) {
//        this.path = path;
//        save = new Saving(undo, redo, shapes);
//        if (flag)
//            saveAsXml();
//        else
//            saveAsJson();
//    }
//
//}

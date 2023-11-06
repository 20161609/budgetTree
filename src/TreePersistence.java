import javax.swing.tree.DefaultTreeModel;
import java.io.*;

public class TreePersistence {

    // Method to save the tree model
    public static void saveTreeModel(DefaultTreeModel treeModel, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(treeModel);
        }
    }

    // Method to load the tree model
    public static DefaultTreeModel loadTreeModel(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (DefaultTreeModel) ois.readObject();
        }
    }
}

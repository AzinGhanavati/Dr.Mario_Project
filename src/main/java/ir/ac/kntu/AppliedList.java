package ir.ac.kntu;

import java.io.*;
import java.util.ArrayList;

public class AppliedList implements Serializable{

    private static AppliedList instance = new AppliedList();

    public static AppliedList getInstance() {
        return instance;
    }

    private  ArrayList<Player> players=new ArrayList<>();


    public static ArrayList<Player> getPlayers() {
        return new ArrayList<>(instance.players);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public static void write(AppliedList instance) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(new File("obj.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static AppliedList read() throws IOException, ClassNotFoundException {
        AppliedList obj = null;
        FileInputStream fis = new FileInputStream(new File("obj.txt"));
        ObjectInputStream ois = new ObjectInputStream(fis);
        obj = (AppliedList) ois.readObject();
        fis.close();
        instance=obj;
        return obj;
    }
}

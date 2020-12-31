package traductor;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Traductor {
    public HashMap<String, ArrayList<String>> diccionarioEnEs;
    public HashMap<String, ArrayList<String>> diccionarioEsEn;

    public Traductor(){
        try {
            File toRead = new File("Diccionario Es-En");

            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            diccionarioEsEn = (HashMap<String, ArrayList<String>>) ois.readObject();

            ois.close();
            fis.close();
        }catch (Exception e){}
        try {
            File toRead = new File("Diccionario En-Es");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            diccionarioEnEs = (HashMap<String, ArrayList<String>>) ois.readObject();

            ois.close();
            fis.close();
        }catch (Exception e){}

    }

    public ArrayList<String> traducir(String asd){
        return this.diccionarioEsEn.get(asd);
    }

    public ArrayList<String> translate(String asd){
        return this.diccionarioEnEs.get(asd);
    }

    public boolean contieneLaPalabraEnEsp(String palabra){
        return diccionarioEsEn.containsKey(palabra);
    }

    public boolean contieneLaPalabraEnIng(String word){
        return diccionarioEnEs.containsKey(word);
    }


}
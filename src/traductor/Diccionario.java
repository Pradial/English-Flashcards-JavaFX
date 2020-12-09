package traductor;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Diccionario {
    private HashMap<String, String> diccionarioPalabras = new HashMap<String, String>();

    public Diccionario(){
        try {
            File toRead = new File("palabrasParaEntrenar");
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.diccionarioPalabras = (HashMap<String, String>) ois.readObject();

            ois.close();
            fis.close();
        }catch (Exception e){}

    }

    public void addTraduccion(String palabra, String traduccion){
        this.diccionarioPalabras.put(palabra, traduccion);
    }

    public String traduccion(String palabraEnEsp){
        return this.diccionarioPalabras.get(palabraEnEsp);
    }

    public String palabraRandom(){
        if(diccionarioPalabras.isEmpty()) return "No hay palabras en el diccionario.\nBusca traducciones o agregá" +
                " palabras para poder comenzar a entrenar";
        List<String> keysAsArray = new ArrayList<>(diccionarioPalabras.keySet());
        Random r = new Random();
        int r2 = r.nextInt(keysAsArray.size());
        String randomValue = keysAsArray.get(r2);
        return String.valueOf(randomValue);
    }

    public String getValue(String key){
        return this.diccionarioPalabras.get(key);
    }


    public void guardar(){
        try {
            File fileOne=new File("palabrasParaEntrenar");
            FileOutputStream fos=new FileOutputStream(fileOne);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(diccionarioPalabras);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {}
    }

    public void setEmpty(){
        diccionarioPalabras.clear();
    }

}

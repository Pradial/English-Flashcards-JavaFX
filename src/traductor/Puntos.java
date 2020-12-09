package traductor;

import java.io.*;
import java.text.DecimalFormat;

public class Puntos{
    // Puntaje[0] son las respuestas correctas, y Puntaje[1] son las respuestas incorrectas.
    private int[] puntaje = {0, 0};

    public Puntos(){
        try {
            File toRead = new File("Puntaje");

            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.puntaje = (int[]) ois.readObject();

            ois.close();
            fis.close();
        }catch (Exception e){}
    }

    public void sumarCorrecta(){
        this.puntaje[0]++;
    }

    public void sumarIncorrecta(){
        this.puntaje[1]++;
    }

    public String getNota(){
        if(this.getTotales()!=0){
            float nota = ((float)puntaje[0]/((float) puntaje[0]+ (float)puntaje[1]))*10;
            return new DecimalFormat("#.##").format(nota);
        }else return "0";
    }

    public int getTotales(){return this.puntaje[0] + this.puntaje[1];}

    public int getCorrectas(){return this.puntaje[0];}

    public int getIncorrectas(){return this.puntaje[1];}

    public void guardarPuntaje(){
        try{
            File fileOne=new File("Puntaje");
            FileOutputStream fos=new FileOutputStream(fileOne);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(puntaje);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {}
    }

    public void resetearEstadisticas(){
        this.puntaje[0] = 0;
        this.puntaje[1] = 0;
    }

}

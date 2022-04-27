//                                  ENUNCIADO
//Vamos a crear una tabla con enteros del 0 al 9 y la vamos a guardar en un archivo binario en 3 formas distintas
//      1. Dato a dato
//      2. De una sola vez como objeto
//      3. En forma de lista como objeto


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EscribirFicheros {
    static final String FILE_NAME1 = "fichero1.dat";// aquí vamos a escribir una tabla de enteros dato a dato
    static final String FILE_NAME2 = "fichero2.dat";//aquí vamos a escribir la misma tabla de enteros pero como un objeto
    static final String FILE_NAME3 = "fichero3.dat";//aquí vamos a escribir una lista como un objeto

    static int[] tabla1 = new int[10];//tabla de enteros
    static List<Integer> tabla2 = new ArrayList<>();// ArrayList de Integer

    public static void run() {
        tabla1 = rellenarPrimeraTabla();//Llenamos de enteros llamando a nuestro método
        tabla2 = rellenarTablaObjeto();//Llenamos de enteros llamando a nuestro método
        String ControlErrorFichero = "";//Esto sirve para controlar los errores y saber que fichero está dando error
        ObjectOutputStream salida1 = null;
        ObjectOutputStream salida2 = null;
        ObjectOutputStream salida3 = null;

        try {
            ControlErrorFichero = FILE_NAME1;
            salida1 = new ObjectOutputStream(new FileOutputStream(FILE_NAME1));
            ControlErrorFichero = FILE_NAME2;
            salida2 = new ObjectOutputStream(new FileOutputStream(FILE_NAME2));
            ControlErrorFichero = FILE_NAME3;
            salida3 = new ObjectOutputStream(new FileOutputStream(FILE_NAME3));
            //escribimos el fichero
            for (int j : tabla1) {
                System.out.println(j);
                salida1.write(j);
            }

            //Ahora escribimos el segundo fichero de una sola vez como objeto
            salida2.writeObject(tabla1);

            //vamos a escribir el tercer archivo con el arrayList de enteros como objeto

            salida3.writeObject(tabla2);

            //controlamos las excepciones posibles a la hora de escribir y leer el archivo con la variable que creamos anteriormente
        } catch (IOException ioException) {
            System.out.println("No se ha podido crear el fichero " + ControlErrorFichero);
        } catch (Exception ex) {
            System.out.println("No se ha podido leer el fichero " + ControlErrorFichero);
        } finally {
            //cerramos los archivos una vez acabados
            cerrarFlujos(ControlErrorFichero, salida1, salida2, salida3);
        }
    }

    /**
     * Metodo para rellenar la primera tabla
     *
     * @return tabla1
     */
    private static int[] rellenarPrimeraTabla() {
        for (int i = 0; i < tabla1.length; i++) {
            tabla1[i] = i;
        }
        return tabla1;
    }


    /**
     * Método para rellenar la segunda y tercera tabla
     *
     * @return tabla2
     */
    private static List<Integer> rellenarTablaObjeto() {
        int i = 0;
        while (i < 10) {
            tabla2.add(i);
            i++;
        }
        return tabla2;

    }

    /**
     * Metodo para cerrar los flujos de los ficheros
     * @param ControlErrorFichero
     * @param salida1
     * @param salida2
     * @param salida3
     */
    static void cerrarFlujos(String ControlErrorFichero, ObjectOutputStream salida1,ObjectOutputStream salida2,ObjectOutputStream salida3 ){
        try {
            if (salida1 != null) {
                ControlErrorFichero = FILE_NAME1;
                salida1.close();
            }
            if (salida2 != null) {
                ControlErrorFichero = FILE_NAME2;
                salida2.close();
            }
            if (salida3 != null) {
                ControlErrorFichero = FILE_NAME3;
                salida3.close();
            }
        } catch (IOException ex) {
            System.out.println("Se ha producido un error a la hora de cerrar el archivo " + ControlErrorFichero);
        }
    }


}



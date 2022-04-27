import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeerFicheros {
    static final String FILE_NAME1 = "fichero1.dat";
    static final String FILE_NAME2 = "fichero2.dat";
    static final String FILE_NAME3 = "fichero3.dat";

    static int[] tabla1 = new int[10];
    static int[] tabla2 = new int[10];
    static List<Integer> tabla3 = new ArrayList<>();

    public static void run() {
        int numero;
        ObjectInputStream Entrada1 = null;
        ObjectInputStream Entrada2 = null;
        ObjectInputStream Entrada3 = null;
        try {
            //abrimos los flujos
            Entrada1 = new ObjectInputStream(new FileInputStream(FILE_NAME1));
            Entrada2 = new ObjectInputStream(new FileInputStream(FILE_NAME2));
            Entrada3 = new ObjectInputStream(new FileInputStream(FILE_NAME3));

            //Leemos las 3 tablas tablas
            numero = Entrada1.read();
            while (numero != -1) {
                //System.out.println(numero);
                tabla1[numero] = numero;
                numero = Entrada1.read();
            }
            tabla2 = (int[]) Entrada2.readObject();
            tabla3 = (List<Integer>) Entrada3.readObject();
            System.out.print("El resultado de la tabla numero 1 es: ");
            for (int j : tabla1) {
                System.out.print(" " + j);
            }

            System.out.print("\nEl resultado de la tabla numero 2 es: ");
            for (int j : tabla2) {
                System.out.print(" " + j);
            }

            System.out.println("\nEl resultado de la tabla numero 3 es: " + tabla3.toString());
        } catch (EOFException eofException) {
            System.out.println("Fin");
            System.out.println("Tabla");
        } catch (IOException ioException) {
            System.out.println("No se puede abrir el fichero");
        } catch (Exception exception) {
            System.out.println("Error 404");
        } finally {
            //cerramos los flujos
            cerrarFlujos(Entrada1, Entrada2, Entrada3);
        }
    }
    static void cerrarFlujos(ObjectInputStream Entrada1,ObjectInputStream Entrada2,ObjectInputStream Entrada3){
        try {
            if (Entrada1 != null) {
                Entrada1.close();
            }
            if (Entrada2 != null) {
                Entrada2.close();
            }
            if (Entrada3 != null) {
                Entrada3.close();
            }
        } catch (IOException ioException) {
            System.out.println("error en el cierre del fichero de entrada");
        }
    }


}

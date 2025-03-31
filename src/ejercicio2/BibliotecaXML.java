package ejercicio2;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que permite serializar un objeto Biblioteca al formato XML y viceversa.
 *
 * @author Antonio Naranjo Castillo
 */
public class BibliotecaXML {

    // Ruta del archivo donde se lee y escribe el objeto Biblioteca
    private final String rutaArchivo;

    // Objeto Xstream que permite la L/E con archivos XML
    private final XStream xstream;

    /**
     * Metodo constructor
     *
     * @param nombreArchivo Ruta del archivo donde se lee y escribe el objeto
     * Biblioteca
     */
    public BibliotecaXML(String nombreArchivo) {
        this.rutaArchivo = nombreArchivo;
        this.xstream = new XStream();
        //Permite asignar privilegios para poder operar con los archivos XML
        this.xstream.allowTypesByWildcard(new String[]{
            "ejercicio2.**"
        });
    }

    // -----------------------------------------------------
    // Ejercicio 2: Metodos que debe implementar el alumnado
    // -----------------------------------------------------
    // 3.1. Metodo escribir()
    /**
     * Metodo que escribe, en un archivo de texto, un objeto Biblioteca
     * serializable.
     *
     * @param biblioteca Objeto Biblioteca serializable para almacenar en el
     * archivo de texto.
     */
    public void escribir(Biblioteca biblioteca) {
        // Incluir el codigo que debe realizar el metodo
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            String xml = xstream.toXML(biblioteca);
            writer.write(xml);
            System.out.println("Biblioteca escrita correctamente en " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // 3.2. Metodo leer()
    /**
     * Metodo que lee, desde un archivo de texto, un objeto Biblioteca
     * serializado.
     *
     * @return Objecto Biblioteca que estaba almacenado en el archivo de texto.
     */
    public Biblioteca leer() {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            return (Biblioteca) xstream.fromXML(contenido.toString());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return new Biblioteca(); // Retorna una biblioteca vacía si hay error
    }

}

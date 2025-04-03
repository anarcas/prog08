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
        
        // Se crea un flujo de salida para escribir en el archivo, se cerrará al finalizar.
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            // Convierte el objeto 'biblioteca' a un formato XML usando XStream y lo guarda en la variable 'xml'
            // El método 'toXML' serializa el XML a desde el objeto correspondiente.
            String xml = xstream.toXML(biblioteca);
            // Escribe el contenido XML generado en el archivo especificado por 'rutaArchivo'
            writer.write(xml);
            // Se imprime el mensaje en consola
            System.out.println("Biblioteca escrita correctamente en " + rutaArchivo);
        } catch (IOException e) {
            // Se imprime un mensaje de error en la consola con la descripción del problema.
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
        // Se crea un objeto StringBuilder llamado 'contenido' para ir acumulando el contenido del archivo.
        StringBuilder contenido = new StringBuilder();
        // Se inicia un flujo de entrada que se cerrará al terminar.
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            // Se declara una variable 'linea' para leer cada línea del archivo.
            String linea;
            // Lee línea por línea hasta llegar al final del archivo (cuando 'readLine' devuelve null).
            while ((linea = reader.readLine()) != null) {
                 // Se agrega la línea leída al 'StringBuilder', añadiendo un salto de línea.
                contenido.append(linea).append("\n");
            }
            // Convierte el contenido acumulado (en formato XML) a un objeto 'Biblioteca' utilizando XStream.
            // El método 'fromXML' deserializa el XML a su objeto correspondiente.
            return (Biblioteca) xstream.fromXML(contenido.toString());
        } catch (IOException e) {
            // Se imprime un mensaje de error en la consola con la descripción del problema.
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return new Biblioteca(); // Retorna una biblioteca vacía si hay error
    }

}

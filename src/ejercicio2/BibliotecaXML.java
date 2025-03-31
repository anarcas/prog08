package ejercicio2;

import com.thoughtworks.xstream.XStream;

/**
 * Clase que permite serializar un objeto Biblioteca al formato XML y 
 viceversa.
 *   
 * @author profe
 */
public class BibliotecaXML {
    
     // Ruta del archivo donde se lee y escribe el objeto Biblioteca
    private String rutaArchivo;
    
    
    // Objeto Xstream que permite la L/E con archivos XML
    private XStream xstream;

    /**
     * Metodo constructor
     * @param nombreArchivo Ruta del archivo donde se lee y escribe el objeto Biblioteca
     */
    public BibliotecaXML(String nombreArchivo) {
        this.rutaArchivo = nombreArchivo;
        this.xstream = new XStream();
        //Permite asignar privilegios para poder operar con los archivos XML
        this.xstream.allowTypesByWildcard(new String[] { 
            "ejercicio2.**"
        });
    }

    
    // -----------------------------------------------------
    // Ejercicio 2: Metodos que debe implementar el alumnado
    // -----------------------------------------------------
    
    // 3.1. Metodo escribir()
    /**
     * Metodo que escribe, en un archivo de texto, un objeto Biblioteca serializable.
     * @param biblioteca Objeto Biblioteca serializable para almacenar en el archivo de texto.
     */    
    public void escribir(Biblioteca biblioteca) {
        // Incluir el codigo que debe realizar el metodo
    }
    
    // 3.2. Metodo leer()
     /**
     * Metodo que lee, desde un archivo de texto, un objeto Biblioteca serializado.
     * @return Objecto Biblioteca que estaba almacenado en el archivo de texto.
     */
    public Biblioteca leer() {
        return null; // Sustituir este return por el codigo que resuelve el ejercicio
    }
}

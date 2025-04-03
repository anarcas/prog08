package ejercicio1;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Ejercicio 1: Lectura/escritura de una biblioteca de libros en ficheros de
 * texto.
 *
 * @author Antonio Naranjo Castillo
 */
public class Ejercicio1 {

    /**
     * M√©todo principal.
     *
     * @param args argumentos que recibe el m√©todo
     */
    public static void main(String args[]) {

        //----------------------------------------------
        //          Declaracion de variables 
        //----------------------------------------------
        
        // Constantes
        
        // Variables de entrada
        String rutaLibros;

        // Variables de salida
        String rutaBiblioteca;

        // Variables auxiliares
        Biblioteca biblioteca;
        Libro libro;
        String[] partes;
        String titulo;
        String autor;
        LocalDate fechaCreacion;
        String genero;
        String[] libros;
        String[] datos;
        List<String> capitulos;
        
        
        //----------------------------------------------
        //       Entrada de datos + Procesamiento
        //----------------------------------------------
        
        // Abrimos archivo de contactos ListadoLibros.txt
        System.out.println("Abriendo archivo de libros...");
        rutaLibros = System.getProperty("user.dir") + "/recursos/ListadoLibros.txt";
        
        // Se instancia la variable biblioteca
        biblioteca = new Biblioteca();

        // Se establece el flujo de entrada tal que el BufferedReader se cerrar· al finalizar
        try (BufferedReader br = new BufferedReader(new FileReader(rutaLibros))) {
            String linea;
            while ((linea = br.readLine()) != null) { // Mientras haya lÌneas que leer, se sigue iterando
                partes = linea.split(";"); // Se divide la lÌnea en partes usando el car·cter de punto y coma como delimitador
                if (partes.length == 5) { // Si la lÌnea tiene exactamente 5 partes, se procesan los datos
                    titulo = partes[0];
                    autor = partes[1];
                    fechaCreacion = LocalDate.parse(partes[2]);
                    genero = partes[3];
                    capitulos = List.of(partes[4].split(","));

                    // Se instancia el libro con los datos obtenidos
                    libro = new Libro(titulo, autor, fechaCreacion, capitulos, genero);
                    // Se aÒade el libro a la biblioteca
                    biblioteca.add(libro);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("Cerrando archivo de libros...");

        System.out.println();

        
        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        
        // Abrimos el archivo de la Biblioteca de libros Biblioteca.txt
        System.out.println("Abriendo archivo de la biblioteca...");
        rutaBiblioteca = System.getProperty("user.dir") + "/recursos/Biblioteca.txt";

        // Se establece el flujo de salida tal que el BufferedWriter se cerrar· al finalizar
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaBiblioteca))) {
            bw.write("*******************************************\n");
            bw.write("LIBRO DE LIBROS\n");
            bw.write("*******************************************\n");
            // Obtenemos el texto de los libros desde toString()
            libros = biblioteca.toString().split("\n");

            // Recorremos cada lÌnea (representaciÛn de un libro) en el array de libros
            for (String linea : libros) {
                // Se verifica que la lÌnea no est· vacÌa
                if (!linea.trim().isEmpty()) {
                    // Se formatean los datos dividiendo la lÌnea con el delimitador ";"
                    datos = linea.split(";");
                    // Si la lÌnea tiene al menos 5 partes (tÌtulo, autor, fecha, gÈnero, capÌtulos), se procesa
                    if (datos.length >= 5) {
                        bw.write("TITULO DEL LIBRO:" + datos[0].replace("#", "") + "\n");
                        bw.write("AUTOR:" + datos[1] + "\n");
                        bw.write("FECHA DE CREACI”N:" + datos[2] + "\n");
                        bw.write("GENERO:" + datos[3] + "\n");
                        bw.write("CAPITULOS:" + datos[4] + "\n");
                        bw.write("*******************************************\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }

        
        System.out.println("Cerrando archivo de la biblioteca...");

        System.out.println();
        System.out.println("Archivos cerrados y procesamiento finalizado");
        System.out.println("---------");
        System.out.println();
        System.out.println("Fin del programa.");
    }
}

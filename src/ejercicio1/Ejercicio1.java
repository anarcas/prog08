package ejercicio1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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
        String rutaLibros = System.getProperty("user.dir") + "/recursos/ListadoLibros.txt";

        // Variables de salida
        String rutaBiblioteca = System.getProperty("user.dir") + "/recursos/Biblioteca.txt";

        // Variables auxiliares
        Biblioteca biblioteca = new Biblioteca();

        
        //----------------------------------------------
        //       Entrada de datos + Procesamiento
        //----------------------------------------------
        
        // Abrimos archivo de contactos ListadoLibros.txt
        System.out.println("Abriendo archivo de libros...");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaLibros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    String titulo = partes[0];
                    String autor = partes[1];
                    LocalDate fechaCreacion = LocalDate.parse(partes[2]);
                    String genero = partes[3];
                    List<String> capitulos = List.of(partes[4].split(","));

                    Libro libro = new Libro(titulo, autor, fechaCreacion, capitulos, genero);
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaBiblioteca))) {
            bw.write("*******************************************\n");
            bw.write("LIBRO DE LIBROS\n");
            bw.write("*******************************************\n");
            // Obtenemos el texto de los libros desde toString()
            String[] libros = biblioteca.toString().split("\n");

            for (String linea : libros) {
                // Verificamos si la lÌnea no est· vacÌa
                if (!linea.trim().isEmpty()) {
                    // Formateamos el contenido correctamente para que coincida con el formato deseado
                    String[] datos = linea.split(";");
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

        System.out.println("Archivos procesados correctamente.");

        System.out.println("Cerrando archivo de la biblioteca...");

        System.out.println();
        System.out.println("Archivos cerrados y procesamiento finalizado");
        System.out.println("---------");
        System.out.println();
        System.out.println("Fin del programa.");
    }
}

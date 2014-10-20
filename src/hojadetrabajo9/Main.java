/*
 *  Universidad del Valle de Guatemala
 *  Algoritmos y estructura de datos 2014
 *  
 *  Autores:    Nancy Girón Muñoz - 13467
 *              Martín Meyer Ramazzini - 13043
 *              Alberto López Montenegro - 13181
 *  
 *  Main.java clase principal
 */
package hojaDeTrabajo9;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args){
        
        Floyd matriz = new Floyd(); 			/* Crea la matriz de adyacencia */
        matriz.caminoCorto(); 					/* Encuentra el camino más corto entre los nodos */
        int eleccion; 							/* Elección del usuario */
        int opcion; 							/* Guarda la elección del la opción 3 */
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Encontrar la ruta más corta para los dos destinos ");
        System.out.println("2. Nombre de la ciudad que se encuentra en el centro del grafo");
        System.out.println("3. Modificar grafo");
        System.out.println("4. Finalizar programa");
        eleccion = scan.nextInt(); 
        
        /* Mientras la opción del usuario no es la de finalizar, ejecutar el ciclo */
        while(eleccion!=4){
            /* Matríz desplegada */
            System.out.println("\nMatriz de adyacencia");
            matriz.D.show();
            
            /* Preguntar los destinos y desplegar los resultados (opción 1) */
            if(eleccion==1){
                matriz.caminoCorto(); 			/* Camino mas corto entre las ciudades */
                System.out.println("Ingrese el nombre de la ciudad de salida");
                String ciudad1 = scan.next(); 
                System.out.println("Ingrese el nombre de la ciudad de destino");
                String ciudad2 = scan.next();
                /* Desplegar la distancia minima y la ruta completa, Si las ciudades se encuentran en la matriz */
                if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                    System.out.println("\nLa distancia minima es: "+matriz.D.getEdge(ciudad1, ciudad2)+".");
                    if(matriz.D.getEdge(ciudad1, ciudad2)!=10000){
                        System.out.print("La ruta es: "+ciudad1);
                        matriz.mostrarIntermedias(matriz.D.getIndex(ciudad1), matriz.D.getIndex(ciudad2));
                        System.out.println(", "+ciudad2);
                    }
                }
            }
            
            /* Desplegar el centro del grafo (opción 2) */
            else if(eleccion==2){
                matriz.centroGrafo();
            }
            
            /* Preguntar cual es el cambio a realizar y ejecutarlo (opción 3) */
            else if(eleccion==3){
                System.out.println("1. Hay interrupcion de trafico entre un par de ciudades");
                System.out.println("2. Establecer nueva conexion");
                opcion = scan.nextInt();
                // Si la respuesta es 1, preguntar por el nombre de las ciudades y colocar un numero muy grande
                // en la matriz de adyacencia para indicar que no hay conexion
                if(opcion==1){
                    System.out.println("Ingrese el nombre de la ciudad de salida");
                    String ciudad1 = scan.next();
                    System.out.println("Ingrese el nombre de la ciudad de destino");
                    String ciudad2 = scan.next();
                    if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                        matriz.D.addEdge(ciudad1, ciudad2, 10000);
                        // Guarda los cambios en el archivo
                        try {
                            matriz.a.write(ciudad1+" "+ciudad2+" 10000");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                /* Preguntar por el nombre de las ciudades y agregar distancia (opción 2)*/
                else if(opcion==2){
                    System.out.println("Ingrese el nombre de la ciudad de salida");
                    String ciudad1 = scan.next();
                    System.out.println("Ingrese el nombre de la ciudad de destino");
                    String ciudad2 = scan.next();
                    System.out.println("Ingrese la distancia entre las ciudades");
                    int distancia = scan.nextInt();
                    // Si las ciudades ya existen, cambiar el valor
                    if(matriz.D.contains(ciudad1)&&matriz.D.contains(ciudad2)){
                        matriz.D.addEdge(ciudad1, ciudad2, distancia);
                    }
                    /* Agregar a la matriz las ciudades que no existen */
                    else{
                        matriz.D.add(ciudad1);
                        matriz.D.add(ciudad2);
                        matriz.D.addEdge(ciudad1, ciudad2, distancia);
                    }
                    /* En el archivo guardar los cambios */
                    try {
                        matriz.a.write(ciudad1+" "+ciudad2+" "+distancia);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                /* Definir las distancias para las rutas mas cortas */
                matriz.caminoCorto();
            }
       
            System.out.println("1. Encontrar la ruta más corta para los dos destinos ");
            System.out.println("2. Nombre de la ciudad en el centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Finalizar programa");
            eleccion = scan.nextInt();
        }
        
    }
}
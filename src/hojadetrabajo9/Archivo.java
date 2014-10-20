/*
 *  Universidad del Valle de Guatemala
 *  Algoritmos y estructura de datos 2014
 *  
 *  Autores:    Nancy Girón Muñoz - 13467
 *              Martín Meyer Ramazzini - 13043
 *              Alberto López Montenegro - 13181
 *  
 *  Archivo.java
 */

package hojaDeTrabajo9;
 
import java.io.*;

public class Archivo{
    
    File archivo;
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    PrintWriter pw;
    InterfazGrafo grafo = new GraphMatrix();

    /* Obtiene el archivo */
    public void obtenerArchivo(String direccion) throws FileNotFoundException{
       archivo=new File(direccion); 
    }  
    
    /* Obtiene los nombres de los nodos para el grafo */
    public InterfazGrafo arregloNombres() throws IOException{
        fr = new FileReader (archivo);
        br = new BufferedReader(fr);
        String linea;
        while((linea=br.readLine())!=null){
            String[] tmp;
            tmp=linea.split(" ");
            grafo.add(tmp[0]);
            grafo.add(tmp[1]);
        }
        return grafo;
    }
    
    /* Escribe en el archivo */
    public void write(String cadena) throws IOException{
        fw = new FileWriter(archivo);
        pw = new PrintWriter(fw);
        pw.println(cadena);
    }
    
    /* Obtiene los pesos de los arcos entre nodos del grafo */
    public InterfazGrafo matrizCostos() throws IOException{
        fr = new FileReader (archivo);
        br = new BufferedReader(fr);
        String linea;

        while((linea=br.readLine())!=null){
            String[] tmp;
            tmp=linea.split(" ");
            grafo.addEdge(tmp[0], tmp[1], tmp[2]);
        }
        return grafo;
    }
}
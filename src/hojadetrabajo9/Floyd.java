/*
 *  Universidad del Valle de Guatemala
 *  Algoritmos y estructura de datos 2014
 *  
 *  Autores:    Nancy Girón Muñoz - 13467
 *              Martín Meyer Ramazzini - 13043
 *              Alberto López Montenegro - 13181
 *  
 *  Floyd.java
 */

package hojaDeTrabajo9;
 
import java.io.IOException;

public class Floyd {
    
    Archivo a= new Archivo();
    InterfazGrafo D;
    int[][] P;
    int[] max;
    int centro;
    int minimo=10000;
    
    /* Constructor */
    public Floyd(){
        
        try {
            a.obtenerArchivo("C:/Users/1210la/Desktop/datos.txt");
            a.arregloNombres(); 	/* Nombre de los nodos */
            D = a.matrizCostos(); 	/* Peso de los arcos entre nodos */
            P = new int[25][25]; 	/* Matriz de nodos intermedios */
            max = new int[25];
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for(int n=0;n<a.grafo.size();n++){
            for(int m=0;m<a.grafo.size();m++){
                P[n][m]=10000;
            }
        }
    }
    
    /* Algoritmo de Floyd para encontrar el camino mas corto entre todos los nodos */
    public void caminoCorto(){
        for(int k=0;k<D.size();k++){
            for(int i=0;i<D.size();i++){
                for(int j=0;j<D.size();j++){
                    if(D.getEdge(D.get(i),D.get(j))>(D.getEdge(D.get(i), D.get(k))+D.getEdge(D.get(k), D.get(j)))){
                        D.addEdge(D.get(i), D.get(j), (D.getEdge(D.get(i), D.get(k))+D.getEdge(D.get(k), D.get(j))));
                        P[i][j]=k;
                    }
                }
            }
        }
        
    }
    
    /* Encontrar el centro del grafo */
    public void centroGrafo(){
        caminoCorto();
        int n=0;       
        /* Encuentra los valores máximos de la matríz en cada columna */
        for(int i=0;i<D.size();i++){
            for(int j=0;j<D.size()-1;j++){
                int num1=D.getEdge(D.get(j), D.get(i));
                n=j;
                n++;
                int num2=D.getEdge(D.get(n), D.get(i));
                if(num1>num2){
                    max[i]=num1;
                }
                else{
                    max[i]=num2;
                }
            }
            n++;
        }
        /* Encuentra los valores minimos de los maximos de todas las columnas */
        for(int i=0;i<D.size();i++){
            int num1=max[i];
            if(num1<minimo){
                centro=i;
                minimo=num1;
            }
        }
        System.out.println("\nEl centro del grafo es: "+D.get(centro)+"\n");
        
    }
    
    /* Desplegar los nodos intermedios */
    public void mostrarIntermedias(int num1, int num2){
        if(P[num1][num2]!=10000){
            mostrarIntermedias(num1,P[num1][num2]);
            System.out.print(", "+D.get(P[num1][num2]));
            mostrarIntermedias(P[num1][num2],num2);
            return;
        }
        else return;
           
    }
}
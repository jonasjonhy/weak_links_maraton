
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import ufps.util.ArchivoLeerTexto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valeria salazar & Ferney Jaramillo & Rosemberg Porras Mancilla
 */
public class WLinks {
    
    
    
    public static void main(String[]arg){
        try
{
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
}
catch(Exception e){}// if it fails, your program might look ugly but still work
   
        JFileChooser fl=new JFileChooser();
        
        int valor=fl.showOpenDialog(fl);
        
        if(valor==JFileChooser.APPROVE_OPTION){
        
        
        String direccion=fl.getSelectedFile().getAbsolutePath();
            
        System.out.println(direccion);

        ArchivoLeerTexto texto= new ArchivoLeerTexto(direccion," ");
        
        String vector[][]=texto.leerTodo();

        long valor1=0;
        long valor2=0;
        
        valor1=System.currentTimeMillis();
        
        for(int i=0; i<vector.length-1; ){
            //cabezote
            int cantidadNodos=Integer.parseInt(vector[i][0]);
            int cantidadConexiones=Integer.parseInt(vector[i][1]);
            
            System.out.println(cantidadNodos+" "+cantidadConexiones);
            //matriz de esta parte del ejercicio
            boolean adc[][]= new boolean[cantidadNodos][cantidadNodos];
            
            //claves de esta parte del ejercicio
            int[][]clavesTem=new int[cantidadConexiones][];
            i++;
            
            //llenado de la matriz de adyacencias
            for(int j=0; j<cantidadConexiones; j++){
                int primerValor=Integer.parseInt(vector[i][0]);
                int segundoValor=Integer.parseInt(vector[i++][1]);
                int clave[]=new int[2];
                clave[0]=primerValor;
                clave[1]=segundoValor;
                clavesTem[j]=clave;
                adc[primerValor][segundoValor]=true;
            }
            //imprimendo las claves
            for(int[]cl:clavesTem){
                System.out.println(cl[0]+" "+cl[1]);
            }
            JOptionPane.showMessageDialog(null,"Los enlaces debiles son:" +"\n"+getWeakLinks(adc, clavesTem));
        }
        valor2=System.currentTimeMillis();
    //    System.out.println(weaks);
        System.out.println("el tiempo de ejecución gastado es "+(valor2-valor1)+" milisegundos");
    
                       }
        else
            JOptionPane.showMessageDialog(null,"Seleccione un archivo valido");
        }

    /**
     * Metodo que obtiene los enlaces debiles de un conjunto de nodos de un grafo, representados por medio de 
     * una matriz de adyacencia.
     * @param matriz Matriz de adyacencia que representa un grafo.
     * @param claves Matriz que contiene todos los enlaces directos entre nodos de un grafo.
     * @return enlaces debiles del grafo a analizar.
     */
    private static String getWeakLinks(boolean[][]matriz, int[][]claves){
        
        String weak="";
        int contador=0;
        for(int []clave : claves){
            if(esDebil(clave,matriz)){
                weak+=clave[0]+" "+clave[1]+" ";
                contador++;
            }
        }
        
        if(contador!=0){
            weak=contador+" "+weak;
        }
  //      System.out.println(weak+" son weak");
        return weak;
        
    }

    /**
     * Metodo que verifica si un enlace entre dos nodos es debil o no.
     * @param clave nodos en los cuales está en enlace directo a verificar.
     * @param matriz Matriz de adyacencia que representa un grafo.
     * @return verdad si el enlace es debil, falso si no lo es
     */
    private static boolean esDebil(int[] clave, boolean[][] matriz) {
        
        matriz[clave[0]][clave[1]]=false;
 
        boolean validar=evaluar(matriz);
        
        matriz[clave[0]][clave[1]]=true;
        
        return !validar;
        
    }

    /**Metodo que evalua si un grafo es conexo
     * @param matriz Matriz de adyacencia que representa un grafo.
     * @return verdadero si el grafo es conexo, y falso si no
     */
    private static boolean evaluar(boolean[][] matriz) {
        
        for(int i=0; i<matriz.length;i++){
            for(int j=i+1; j<matriz.length; j++){
                ArrayList<Integer>almacenamiento=new ArrayList<Integer>();
                
                if(!hayCamino(matriz,almacenamiento,i,j))
                    return false;
            }
        }
        return true;
        
    }

    /**
     * Metodo que evalua la disponibilidad de comunicacion entre un nodo y otro
     * @param matriz Matriz de adyacencia que representa un grafo.
     * @param almacenamiento contenedor que lleva las ocurrencias de los nodos en el algoritmo recursivo, usado para
     * que el metodo no vuelva a evaluar un nodo ya evaluado
     * @param emisor nodo emisor de la comunicacion a evaluar
     * @param receptor nodo receptor de la comunicacion a evaluar 
     * @return 
     */
    private static boolean hayCamino(boolean[][] matriz,  ArrayList<Integer>almacenamiento,int emisor, int receptor) {
        
        if(almacenamiento.contains(emisor))
            return false;
        
        if(emisor==receptor)
            return true;
        
        almacenamiento.add(emisor);
        
        ArrayList<Integer>hijos=getHijos(matriz,emisor);
        
        for(int hijo: hijos){
            
            if(hayCamino(matriz, almacenamiento, hijo, receptor))
                return true;
        }
        
        return false;

    }

    /**
     * Metodo que retorna un listado de los nodos vecinos o hijos, de un nodo
     * @param matriz Matriz de adyacencia que representa un grafo.
     * @param emisor nodo del cual se desean saber sus hijos o vecinos
     * @return Contenedor con los hijos o vecinos de un nodo
     */
    private static ArrayList<Integer> getHijos(boolean[][] matriz, int emisor) {
      
        ArrayList<Integer>hijos=new ArrayList<>();
        
        for(int i=0;i<matriz.length;i++){
            
            if(matriz[i][emisor]==true )
            if(!hijos.contains(i))
                hijos.add(i);
                
            if(!hijos.contains(i))
            if(matriz[emisor][i]==true )
                hijos.add(i);
        }
        
        return hijos;
        
    }

    
  
}


package ufps.util;

/**
 *Clase que representa una vector que almacena objetos de cualquier clase.
 * @author Marco Adarme
 * @version 2.0
 */
public class Secuencia<T>{
    private T vector[];
    private int cant=0;
    
    /**
     * Constructor con parametros de la clase secuencia
     * @param n es de tipo integer que contiene el tamaño de la secuencia
     */
    public Secuencia(int n) {
        
        if (n<=0)
            throw new RuntimeException("Error en el programa!");
        
        Object r[]=new Object[n];
        this.vector=(T[])r;
    }
    
    /**
     * Método que retorna un objeto tipo T de la secuencia dada la posición
     * @param i es de tipo integer y contiene la posicion en la secuencia
     * @return un tipo T que contiene el elemento del nodo en la posicion indicada
     */
    public T get(int i) {
        
        if (i<0 || i>this.cant)
            throw new RuntimeException("Indíces fuera de rango!");
        
        return (this.vector[i]);
    }
    
    /**
     * Método que cambia un elemento de la secuencia en la posición indicada , por otro
     * @param i de tipo integer que contiene la posicion de la secuencia que se va ha cambiar
     * @param elemNuevo es de tipo T y contiene el parametro nuevo que se desea ingresar
     */
    public void set(int i, T elemNuevo) {
        
        if (i<0 || i>this.cant)
            throw new RuntimeException("Indíces fuera de rango!");
        
        this.vector[i]=elemNuevo;
    }
    
    /**
     * Método que inserta un nuevo elemento a la secuencia
     * @param elem es de tipo T que contiene el elemento a insertar
     */
    public void insertar(T elem) {
        
        if(this.cant>=this.vector.length)
            throw new RuntimeException("No hay más espacio!");
        
        
        this.vector[this.cant++]=elem;
    }
    
    /**
     * Método que retorna el contenido de la secuencia en una cadena String
     * @return de tipo String y contiene el String de datos de la secuencia
     */
    @Override
    public String toString() {
        
        String msg="";
        
        for(int i=0;i<this.cant;i++)
            msg+=this.vector[i].toString()+"\t";
        
        return (msg);
    }
    
    /**
     * Método que recibe un un elemento y comprueba si existe en la secuencia
     * @param elem es de tipo T y contiene el elemnto que se va ha buscar
     * @return un tipo boolean, retorna true si el objeto existe y false en caso contrario.
     */
    public boolean existeUnObjeto(T elem) {
        
        for(int i=0;i<this.cant;i++)
            if(this.vector[i].equals(elem))
                return true;
        
        return false;
    }
    
    /**
     * Método que retorna el tamaño lógico de la secuencia, esto es, las posiciones
     * que tienen elementos almacenados
     * @return un tipo integer que contiene el tamaño lógico de la secuencia
     */
    public int length() {
        return this.cant;
    }
    
    /**
     * Método que retorna el tamaño real de la secuencia, esto es, el length del vector con o sin 
     * elementos
     * @return un tipo integer que contiene el tamaño real de la secuencia
     */
    public int getCapacidad()
    {
        return this.vector.length;
    }
    
}

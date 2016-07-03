/**
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 * 
 * Clase generica para almacenar una terna de informacion. 
 */
public class Tripleta<T1,T2, T3> {
    private T1 Objeto1;
    private T2 Objeto2;
    private T3 Objeto3;
    
    public Tripleta(T1 objeto1, T2 objeto2, T3 objeto3){
        this.Objeto1 = objeto1;
        this.Objeto2 = objeto2;
        this.Objeto3 = objeto3;
    }
    
    /**
     * Metodo que devuelve el Objeto1 de la Tripleta.
     * @return primer objeto de la terna.
     */
    public T1 get1(){
        return Objeto1;
    }
    
    /**
     * Metodo que devuelve el Objeto2 de la Tripleta.
     * @return segundo objeto de la terna.
     */
    public T2 get2(){
        return Objeto2;
    }
    
    /**
     * Metodo que devuelve el Objeto3 de la Tripleta.
     * @return tercer objeto de la terna.
     */
    public T3 get3(){
        return Objeto3;
    }    
    
    /**
     * Metodo que cambia el Objeto1 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set1(T1 Objeto1) {
        this.Objeto1 = Objeto1;
    }
    
    /**
     * Metodo que cambia el Objeto2 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set2(T2 Objeto2) {
        this.Objeto2 = Objeto2;
    }
    
    /**
     * Metodo que cambia el Objeto3 de la Tripleta por otro.
     * @param nuevo objeto a utilizar.
     */
    public void set3(T3 Objeto3) {
        this.Objeto3 = Objeto3;
    }
    
    /**
     * Asigna en null los elementos de la Tripleta
     */
    public void clear() {
        Objeto1 = null;
        Objeto2 = null;
        Objeto3 = null;
    }

    @Override
    public String toString() {
        return Objeto1.toString()+"\n"+Objeto2.toString()+"\n"+Objeto3.toString();
    }
}

import java.util.Arrays;

 /**
 * Clase que reprenta los articulos peliculas.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */
public final class Pelicula  extends ArticuloAudioVisual {
    
    private String Formato;
    private String[] Directores;
    private String[] Escritores;
    private String Secuela;
    private final static int PrecioVenta= 90;

    
    /**
     * Contructor de la clase pelicula
     * @param cantidad
     * @param codigo
     * @param nombre
     * @param año
     * @param genero
     * @param formato
     * @param directores
     * @param escritores
     * @param actores
     * @param secuela 
     */
    public Pelicula(int cantidad,String codigo,String nombre, int año, String genero,
            String formato, String[] directores, String[] escritores, 
            String[] actores, String secuela){
       super(cantidad, nombre,año,genero,codigo,actores); 
       this.Formato= formato;
       this.Directores = directores;
       this.Escritores = escritores;
       this.Secuela = secuela;     
       try { chequearGenero();}
        catch( GeneroInvalidoException e)
        { System.out.println("Genero de Pelicula Invalido");}
    }

    @Override
    public  int getPrecioVenta() {
        return PrecioVenta;
    }
       
    /**
     * Devuelve los directores de una pelicula.
     * @return arreglo de String que contiene directores de la pelicula 
     */
    public String[] getDirectores() {
        return Directores;
    }

    /**
     * Devuelve los escritores de una pelicula.
     * @return arreglo de String que contiene escritores de la pelicula
     */
    public String[] getEscritores() {
        return Escritores;
    }

    /**
     * Devuelve formato de una pelicula.
     * @return String que contiene el formato
     */
    public String getFormato() {
        return Formato;
    }

    /**
     * Devuelve secuela de una pelicula.
     * @return String que contiene la secuela
     */
    public String getSecuela() {
        return Secuela;
    }
    
    @Override
    public boolean equals(Articulo a){
        return false;
    }
    
    /**
     * Evalua si dos peliculas son iguales.
     * @param pelicula
     * @return true si son iguales, falso en caso contrario
     */
    public boolean equals(Pelicula a)  {
        if(this.Nombre.equals(a.getNombre()) 
          &&  Arrays.equals( this.Actores, a.getActores()) 
          && this.Año == a.getAño() 
          && this.Genero.equalsIgnoreCase(a.getGenero())
          && this.Formato.equalsIgnoreCase(a.getFormato())
          &&  Arrays.equals( this.Directores, a.getDirectores()) 
          &&  Arrays.equals( this.Escritores, a.getEscritores()) 
          && this.Secuela.equalsIgnoreCase(a.getSecuela())){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        String mensaje="";
        String ampersan = " & ";   
        mensaje = this.Codigo+ ampersan + this.Cantidad + ampersan;
        mensaje= mensaje + this.Nombre + ampersan + this.Año + ampersan;
        mensaje = mensaje + this.Genero + ampersan + this.Formato + ampersan;
        for(int i= 0 ; i<this.Directores.length-1; i++){
            mensaje = mensaje + this.Directores[i]+", ";
        }
        mensaje = mensaje+ this.Directores[this.Directores.length-1]+ ampersan;
        for(int i= 0 ; i<this.Escritores.length-1; i++){
            mensaje = mensaje + this.Escritores[i]+", ";
        }
        mensaje = mensaje+ this.Escritores[this.Escritores.length-1]+ ampersan;
        for(int i= 0 ; i<this.Actores.length-1; i++){
            mensaje = mensaje + this.Actores[i]+", ";
        }
        mensaje = mensaje+ this.Actores[this.Actores.length-1];
        if (!this.Secuela.equalsIgnoreCase("")){
            mensaje = mensaje + ampersan+ this.Secuela+"\n";
        } else {
            mensaje = mensaje + "\n";    
        }
        return mensaje;
    }
            
}

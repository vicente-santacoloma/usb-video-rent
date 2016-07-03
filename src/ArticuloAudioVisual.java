 /**
 * Clase que reprenta los articulos audiovisuales como peliculas y tempradas.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */
public abstract class ArticuloAudioVisual extends Articulo {
    
    protected String [] Actores;
    private static int [] CondicionPrestamo = {3,10,5};
    
    /**
     * Contructor de la clase ArticuloAudioCisual
     * @param cantidad
     * @param nombre
     * @param año
     * @param genero
     * @param codigo
     * @param actores 
     */
    public ArticuloAudioVisual(int cantidad,String nombre, int año, String genero, String codigo, String[] actores){
        super(cantidad, nombre, año,genero, codigo);
        this.Actores = actores;
    }
    
    /**
     * Devuelve arreglo de String con el clon de los actores.
     * @return clon de un actor
     */
    public String[] getActores() {
        return Actores.clone();
    }

    /**
     * Asigna el valor de los actores;
     * @param arreglo de String con los actores
     */
    public void setActores(String[] Actores) {
        this.Actores = Actores;
    }
    
    /**
     * Chequea que el genero sea valido
     * @throws GeneroInvalidoException 
     */
    public void chequearGenero() throws GeneroInvalidoException {
    
        if (!( this.Genero.equalsIgnoreCase("Comedia") || this.Genero.equalsIgnoreCase("Accion") ||
           this.Genero.equalsIgnoreCase("Drama") || this.Genero.equalsIgnoreCase("Fantasia") ||
           this.Genero.equalsIgnoreCase("Terror") || this.Genero.equalsIgnoreCase("Suspenso") ||
           this.Genero.equalsIgnoreCase(""))){
        throw new GeneroInvalidoException();
        }
    }
    
    @Override
    public int getPrecioPrestamo(){
        return ArticuloAudioVisual.CondicionPrestamo[1];
    }
    
    /**
     * Metodo de la clase que devuelve el numero de dias que tiene un articulo
     * como prestamo.
     * @return dias de prestamo
     */
    public static int getDiasPrestamo(){
        return ArticuloAudioVisual.CondicionPrestamo[0];
    }
    
    /**
     * Metodo de la clase que devuelve la multa del dia.
     *  @return  multa del dia
     */
    public static int getMultaDia() {
        return ArticuloAudioVisual.CondicionPrestamo[2];
    }
    
    @Override
    public int [] getCondicionPrestamo(){
        return ArticuloAudioVisual.CondicionPrestamo;
    }
    
}

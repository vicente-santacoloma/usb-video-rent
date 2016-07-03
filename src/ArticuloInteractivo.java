 /**
 * Clase que reprenta los articulos juegos recreativos y juegos educativos.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */
public abstract class ArticuloInteractivo extends Articulo{
    
    protected String Plataforma;
       
    /**
     * Contructor de la clase articulointeracivo
     * @param cantidad
     * @param nombre
     * @param año
     * @param genero
     * @param codigo
     * @param plataforma 
     */
    public ArticuloInteractivo(int cantidad, String nombre, int año, String genero, String codigo, String plataforma){
        super(cantidad, nombre, año,genero, codigo);
        this.Plataforma = plataforma;
    }

    /**
     * Devuelve la plataforma del articulo interactivo.
     * @return String con nombre de la plataforma
     */
    public String getPlataforma() {
        return Plataforma;
    }

    /**
     * Modifica el valor de la .
     * @parm String con nombre de la nueva plataforma
     */
    public void setPlataforma(String Plataforma) {
        this.Plataforma = Plataforma;
    } 
    
}

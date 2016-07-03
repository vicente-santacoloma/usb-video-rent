/**
 * Clase que representa los articulos de la tienda VideoRent.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */
public abstract class Articulo {
    protected String Nombre;
    protected String Genero;
    protected int Año;
    protected String Codigo;
    protected int Cantidad;

    /**
     * Constructo de la clase articulo
     * @param cantidad
     * @param nombre
     * @param año
     * @param genero
     * @param codigo 
     */
    public Articulo(int cantidad, String nombre, int año, String genero, String codigo){
        this.Cantidad = cantidad;
        this.Nombre = nombre;
        this.Año = año;
        this.Genero = genero;
        this.Codigo = codigo;
    }    
    
    /**
     * Devuelve la cantidad de articulos.
     * @return cantidad de articulos
     */
    public int getCantidad() {
        return Cantidad;
    }

    /**
     * Devuelve el codigo de un articulo.
     * @return codigo de un articulos
     */    
    public String getCodigo() {
        return Codigo;
    }
    
    /**
     * Devuelve el año del articulo.
     * @return año de un articulo
     */
    public  int getAño() {
        return Año;
    }

    /**
     * Devuelve el genero de un articulo.
     * @return genero de articulos
     */
    public String getGenero() {
        return Genero;
    }

    /**
     * Devuelve l de articulos.
     * @return cantidad de articulos
     */
    public String getNombre() {
        return Nombre;
    }
       
    /**
     * Devuelve false si es llamada con un articulo como parametro de entrada.
     * @param articulo
     * @return booleano
     */
    public boolean equals(Articulo a) {
        return false;
    }
    
    /**
     * Devuelve el precio de venta de un articulo.
     * @return un entero
     */
    public abstract int getPrecioVenta();
    
    /**
     * Devuelve la condicion de prestamo.
     * @return un arreglo de enteros
     */
    public abstract int[] getCondicionPrestamo();
    
    /**
     * Devuelve el precio de prestamo.
     * @return un entero con el precio de prestamo
     */
    public abstract int getPrecioPrestamo();
    
    @Override
    public abstract String toString();
    
    /**
     * Decrementa la cantidad de un articulo.
     * @return booleano true si el metodo se ejecuto con exito, false en caso
     * contrario.
     */
    public boolean decrementarCantidad(){
        boolean a= this.Cantidad>1;
        System.out.println(this.Cantidad+ ""+a);
       return --this.Cantidad > 1;
    }
    
    /**
     * Aumenta la cantidad de un articulo.
     */
    public void aumentarCantidad(){
        this.Cantidad++;
    }
}

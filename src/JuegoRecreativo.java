/**
 * Clase que representa a los objetos juegos recreativos.
 * 
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 */
public final class JuegoRecreativo extends ArticuloInteractivo {
    private String Desarrollador;
    private static int PrecioVenta = 250;
    private static int[] CondicionPrestamo = {2,12,10};
    
        /**Constructor de la calse recibe los parametros de articulo mas 
     * los parametros desarrollador
     * 
    */ 
    public JuegoRecreativo(int cantidad, String nombre, int año, String genero, String codigo,
            String plataforma, String desarrollador){
        super(cantidad,nombre,año,genero,codigo,plataforma);
        this.Desarrollador = desarrollador;             
        try { chequearGenero();}
        catch( GeneroInvalidoException e)
        { System.out.println("Genero de JuegoRecreativo Invalido");}
    } 
    /** Retorna el desarrollador del juego
     *@return String con el desarrollador del juego
     * 
    */ 
    public String getDesarrollador() {
        return Desarrollador;
    }
    /** Establece  el desarrollador del juego
    */ 
    public void setDesarrollador(String Desarrollador) {
        this.Desarrollador = Desarrollador;
    }
    /** Obtiene el precio de prestamo del juego
    *@param Sint con el precio de prestamo
    * 
    */ 
    @Override
    public int getPrecioPrestamo(){
        return JuegoRecreativo.CondicionPrestamo[1];
    }
    /** Obtiene los dias de prestamo del juego
    *@param int con los dias  de prestamo
    * 
    */     
    public static int getDiasPrestamo(){
        return JuegoRecreativo.CondicionPrestamo[0];
    }
    
    /** Obtiene la multa por dia de prestamo del juego
    *@param int con la multa por dia  de prestamo
    * 
    */      
    public static int getMultaDia()
    {
        return JuegoRecreativo.CondicionPrestamo[2];
    }
    /** Retorna el precio de venta del juego
     * @return precio de venta del juego
    * 
    */    
    @Override
    public  int getPrecioVenta() {
        return PrecioVenta;
    }
    /** Retorna  true si a es igual al parametro this
    */      
    @Override
    public boolean equals(Articulo a){
        return false;
    }
    /** Chequea si el genero del juego esta en el rango
     * @throws GeneroInvalidoException
    */        
    protected void chequearGenero() throws GeneroInvalidoException{
        if(! (this.Genero.equalsIgnoreCase("Aventura")||
              this.Genero.equalsIgnoreCase("Accion") ||
              this.Genero.equalsIgnoreCase("Deportes") ||
              this.Genero.equalsIgnoreCase("Estrategia")) ){
            throw new GeneroInvalidoException();
        }
    }

    public boolean equals(JuegoRecreativo a)  {
        if(this.Nombre.equals(a.getNombre()) 
          && this.Año == a.getAño() 
          && this.Genero.equalsIgnoreCase(a.getGenero())
          && this.Plataforma.equalsIgnoreCase(a.getPlataforma())
          && this.Desarrollador.equalsIgnoreCase(a.getDesarrollador())){
            return true;
        }else{
            return false;
        }
    }
        /** Coloca al juego como String
     * @return String con el juego 
    */    
    @Override
    public String toString(){
        String mensaje="";
        String ampersan = " & ";   
        mensaje = this.Codigo + ampersan + this.Cantidad + ampersan;
        mensaje = mensaje + this.Nombre + ampersan + this.Genero + ampersan;
        mensaje = mensaje + this.Desarrollador + ampersan + this.Plataforma;
        mensaje = mensaje + ampersan + this.Año + "\n";
        return mensaje;
    }
           /** Retorna la condicion de prestamo de un articulo
     * @return un arreglo con los dias el precio y la multa de un juego
    */
    @Override
    public int[] getCondicionPrestamo(){
        return JuegoRecreativo.CondicionPrestamo;
    }    
     
}

/**
 * Clase que representa a los objetos juegos educativos
 * 
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 */

public final class JuegoEducativo extends ArticuloInteractivo {
    private String Destreza;
    private static final int PrecioVenta = 50;
    private static int[] CondicionPrestamo = {5,7,1};
    
    /**Constructor de la calse recibe los parametros de articulo mas 
     * los parametros destreza
     * 
    */ 
    public JuegoEducativo(int cantidad,String nombre,  int año, String genero, String codigo,
            String plataforma, String destreza){
        super(cantidad, nombre,año,genero,codigo,plataforma);
        this.Destreza = destreza;
        try { chequearGenero();}
        catch( GeneroInvalidoException e)
        { System.out.println("El juego educativo no tiene genero");}
        chequearDestreza();        
        
    
    }
    /** Retorna la destreza del juego
     *@return String con la desreza del juego
     * 
    */ 
    public String getDestreza() {
        return Destreza;
    }
    /** Establece  la destreza del juego
     * 
    */ 
    public void setDestreza(String Destreza) {
        this.Destreza = Destreza;
    }
    /** Chequea si el genero del juego es nulo debe serlo
     *@return String con la desreza del juegp
     * @throws  GeneroInvalidoException en caso de que
     * le traten de asignar un genero a JuegoEducativo
    */ 
    private void chequearGenero() throws GeneroInvalidoException
    {
        if ( this.Genero != null ){
            throw new GeneroInvalidoException() ;
        }
    }
    /** Chequea si la destreza del juego esta dentro del rango
     * 
    */     
     private void chequearDestreza() 
    {
        if ( ! (this.Destreza.equalsIgnoreCase("Numerica") ||
             this.Destreza.equalsIgnoreCase("Verbal")||
             this.Destreza.equalsIgnoreCase("Espacial"))){
                System.out.println("DestrezaInvalida");
        }
    }
    /** Retorna el precio de prestamo del juego
     *@return int con el precio de prestamo
    */     
    @Override
    public int getPrecioPrestamo(){
        return JuegoEducativo.CondicionPrestamo[1];
    }
    /** Retorna el precio de venta del juego
     *@return int con el precio de venta
    */   
    @Override
    public  int getPrecioVenta() {
        return PrecioVenta;
    }
    /** Retorna los dias de prestamo del juego
     *@return int con los dias de prestamo
    */     
    public static int getDiasPrestamo(){
        return JuegoEducativo.CondicionPrestamo[0];
    }
    
    /** Retorna el precio de la multa por dia del juego
     *@return int con el precio de la multa por dia del juego
    */ 
    public static int getMultaDia()
    {
        return JuegoEducativo.CondicionPrestamo[2];
    }
     /** Metodo para comparar dos juegos
     *@return  true si son iguales false en otros casos
     * Recibe un articulo a para comparar
    */    
    @Override
    public boolean equals (Articulo a){
        return false;
    }
      /** Metodo para comparar dos juegos
     *@return  true si son iguales false en otros casos
     * Recibe un articulo a para comparar
     * esta sobreescrito para ser mas eficiente
    */       
    public boolean equals(JuegoEducativo a)  {
        if(this.Nombre.equals(a.getNombre()) 
          && this.Año == a.getAño() 
          && this.Plataforma.equalsIgnoreCase(a.getPlataforma())
          && this.Destreza.equalsIgnoreCase(a.getDestreza())){
            return true;
        }else{
            return false;
        }
    }
         /** oloca el juego como un string
     *@return  String que representa el juego
    */    
    @Override
    public String toString(){
        String mensaje="";
        String ampersan = " & ";   
        mensaje = this.Codigo + ampersan + this.Cantidad + ampersan;
        mensaje = mensaje + this.Nombre + ampersan + this.Destreza + ampersan;
        mensaje = mensaje + this.Plataforma + ampersan + this.Año + "\n";
        return mensaje;

    }
    
    /** Retorna la condicion de prestamo de un juego
     *@return  un arreglo de enteros con la logica del 
     * prestamo de un juego educativo
    */  
    @Override
    public int[] getCondicionPrestamo(){
        return JuegoEducativo.CondicionPrestamo;
    }
}

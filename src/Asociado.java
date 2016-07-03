 /**
 * Clase que reprenta los clientes asociados de la tienda VideoRent.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */
public final class Asociado extends Cliente{
    
    private String Telefono;
    private String Codigo;
    private String DireccionFiscal;
    private Tarjeta TarjetaCredito;
    private String Estado;
    private String Tipo;
    private int AlquiladosAudioVisuales;
    private int AlquiladosInteractivos;    
    
    public Asociado(String Cedula, String Nombre, String Apellido, 
            String Telefono, String DireccionFiscal, Tarjeta TarjetaCredito,
            String Estado, String Tipo, String codigo) {
        super(Cedula,Nombre,Apellido,Telefono);
        this.Codigo = codigo;
        this.DireccionFiscal = DireccionFiscal;
        this.TarjetaCredito = TarjetaCredito;
        this.Estado = Estado;
        this.Tipo = Tipo;
        this.AlquiladosAudioVisuales = 0;
        this.AlquiladosInteractivos = 0;
    }
    
    /**
     * Devuelve el codigo del cliente asociado.
     * @return String con el codigo del cliente asociado
     */
    public String getCodigo() {
        return Codigo;
    }

     /**
     * Devuelve la direccion del cliente asociado.
     * @return String con la direccion del cliente asociado
     */
    public String getDireccionFiscal() {
        return DireccionFiscal;
    }

    /**
     * Devuelve el estado del cliente asociado.
     * @return String con el estado del cliente asociado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * Devuelve la tarjeta de credito del cliente asociado.
     * @return tarjeta de credito del cliente asociado
     */
    public Tarjeta getTarjetaCredito() {
        return TarjetaCredito;
    }

     /**
     * Devuelve el tipo del cliente asociado.
     * @return String con el tipo del cliente asociado
     */
    public String getTipo() {
        return Tipo;
    }
    
    /**
     * Devuelve la fecha de vencimiento de la tarjeta de credito del cliente 
     * asociado.
     * @return fecha de vencimiento de la tarjeta de credito 
     */
    public Fecha getVencimientoTarjeta(){
        return this.TarjetaCredito.getFechaVencimiento();
    }
    
    /**
     * Cambia el estado del cliente a suspendido.
     */
    public void suspender() {
        this.Estado = "Suspendido";
    }
    
    @Override
    public String toString(){
        String tipo="";
        String ampersan = " & ";
        if (this.Tipo.equals("basico")){
            tipo = "B";
        }else{
            tipo= "P";
        }
        String mensaje = this.Codigo+ ampersan + this.Estado+ ampersan + this.Nombre+
                ampersan+ this.Apellido + ampersan+ this.Telefono+ ampersan + this.DireccionFiscal+
                ampersan+ this.TarjetaCredito.getNumero()+ ampersan + this.TarjetaCredito.getBanco()+
                ampersan + this.TarjetaCredito.getCodigoSeguridad() + ampersan + this.TarjetaCredito.getFechaVencimiento();
        return mensaje;
    }
    
    /**
     * Actualiza la tarjeta de credito.
     * @param nueva tarjeta de credito
     */
    public void actualizarTarjeta(Tarjeta t){
        this.TarjetaCredito = t;
    }
    
    
    /**
     * Evalua si un asociado puede alquilar un articulo.
     * @param articulo a buscar
     * @return true si el asociado puede alquilar, false en caso contrario
     */
    public boolean AlquilarCaja(Articulo art){
        if (art instanceof ArticuloAudioVisual && this.Tipo.equalsIgnoreCase("premium") ){
            return this.AlquiladosAudioVisuales < 10;
        }
        if (art instanceof ArticuloInteractivo && this.Tipo.equalsIgnoreCase("premium")){
            return this.AlquiladosInteractivos < 10;
        }
        if (art instanceof ArticuloAudioVisual && this.Tipo.equalsIgnoreCase("basico") ){
            return this.AlquiladosAudioVisuales < 5;
        }
        if (art instanceof ArticuloInteractivo && this.Tipo.equalsIgnoreCase("basico")){
            return this.AlquiladosInteractivos < 3;
        }
        return false;
    }
    
    /**
     * 
     * @param art 
     */
    public void permitirArticulo(Articulo art){
        if (art instanceof ArticuloAudioVisual ){
             this.AlquiladosAudioVisuales--;
        }
        if (art instanceof ArticuloInteractivo ){
             this.AlquiladosInteractivos--;
        }
    }   
}
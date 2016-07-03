import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 * 
 * Clase encargada del manejo y operaciones de la tienda VideoRent.
 */
public class Tienda {
         
    private RegistroInventario Inventario;
    private RegistroAsociados  Asociados;
    private RegistroCaja Caja;
    private RegistroAlquiler Alquiler;
    private static int UltimaLicencia = 0;
    private Fecha FechaActual;
    private String ReporteEmpleado;
    private ArrayList<String> Facturas;
    private int FacturaActual;
    
    public Tienda(){ 
        this.Inventario = new RegistroInventario();
        this.Asociados  = new RegistroAsociados();
        this.Caja = new RegistroCaja();
        this.Alquiler = new RegistroAlquiler();
        this.FechaActual = null;
        this.ReporteEmpleado = "#\n";
        this.FacturaActual = 0;
        this.Facturas = new ArrayList<String>(); 
    }
    
    /**
     * Asigna el valor de la ultima licencia.
     * @param ultima licencia utilizada 
     */
    public void setUltimaLicencia(int UltimaLicencia) {
        Tienda.UltimaLicencia = UltimaLicencia;
    }
    
    /**
     * Asigna el valor de la fecha actual.
     * @param fecha actual
     */
    public void setFechaActual(Fecha f) {
        this.FechaActual= f;
    }
    
    /**
     * Incrementa la fecha actual y ejecuta suspensiones y recordatorios.
     */
    public void NuevaFecha() {
        this.FechaActual.aumentarDia();
        this.Asociados.ejecutarSuspensiones(FechaActual);
        String Reporte = this.Alquiler.RecordarDevoluciones(this.FechaActual);
        if (!Reporte.equalsIgnoreCase("")){
            this.escribirReporteEmpleado("l"+" & "+Reporte);
        }
    }
    
    /**
     * Chequea si luego de hacer una compra o accion viene un pago o abandono de
     * la tienda.
     * @param lista de acciones cliente del dia actual
     * @throws EmpleadoException 
     */
    public void chequear (ArrayList<String> l) throws EmpleadoException {
        
        String [] linea;
        for(int i = 0; i<l.size(); i++) {
            linea = l.get(i).split(" & "); 
            if(linea[0].charAt(0) == 'c' || linea[0].charAt(0) == 'r') {
                if(!AbandonoPago())
                    this.escribirReporteEmpleado("i"+" & "+linea[1]+" & "+"1");
            } 
        }
    }
    
    /**
     * Actualiza los articulos perdidos.
     */
    public void actualizar () {
        this.Alquiler.ArticulosPerdidos(FechaActual);
    }    
    
    /**
     * Metodo requerido por el metodo chequear.
     * @return true si Caja esta vacia.
     */
    private boolean AbandonoPago () {
        return (this.Caja.tamanoCompra() == 0) && (this.Caja.tamanoAlquiler() == 0);
    }
    
    /**
     * Metodo que acumula los reportes de empleado en un String. 
     * @param reporte de cada accion del empleado. 
     */
    public void escribirReporteEmpleado(String reporte) {
        this.ReporteEmpleado = this.ReporteEmpleado + reporte+"\n";
    }
    
    /**
     * Imprime en el archivo "accionesEmpleados.txt" los reportes de empleado.
     * @throws FileNotFoundException 
     */
    public void imprimirReporteEmpleado() throws FileNotFoundException {
        PrintStream out = new PrintStream(new File("accionesEmpleados.txt"));
        out.println(ReporteEmpleado);
        out.flush();
    }
    
    /**
     * Imprime en el archivo "articulosExistentesDespues.txt" el inventario de
     * los articulos luego de realizar las acciones correspondientes.
     * @throws FileNotFoundException 
     */
    public void imprimirInventario() throws FileNotFoundException {
        PrintStream out = new PrintStream(new File("articulosExistentesDespues.txt"));
        out.println(this.Inventario);
        out.flush();
    }
    
    /**
     * Imprime en el archivo "clientesAsociadosDespues.txt" los clientes 
     * asociados luego de realizar las acciones correspondientes.
     * @throws FileNotFoundException 
     */
    public void imprimirAsociados() throws FileNotFoundException {
        PrintStream out = new PrintStream("clientesAsociadosDespues.txt");
        out.println(this.Asociados);
        out.flush();
    }
    
    /**
     * Imprime las compras a realizar disponibles en caja.
     */
    public void imprimirCajaCompra() {
        this.Caja.imprimirCompra();
    }
    
    /**
     * Imprime los alquileres a realizar disponibles en caja.
     */
    public void imprimirCajaAlquiler() {
        this.Caja.imprimirAlquiler();   
    }
      
    /**
     * Agrega una pelicula en el inventario.
     * @param arreglo con la informacion de la pelicula. 
     */
    public void agregarPelicula (String [] articulo) {
        this.Inventario.agregarPelicula(articulo);
    }
     
    /**
     * imprime todas las facturas en el archivo "Facturas,txt".
     * @throws FileNotFoundException 
     */
    public void imprimirFacturas() throws FileNotFoundException {
        PrintStream out = new PrintStream("Facturas.txt");
        for (int i= 0; i<this.Facturas.size(); i++){
                    out.print("Facturas Tienda "+Facturas.get(i)+"\n");
        }
        out.flush();
    }
       
    /**
     * Agrega una temporada en el inventario.
     * @param arreglo con la informacion de la temporada.
     */
    public void agregarTemporada (String [] articulo) {
        this.Inventario.agregarTemporada(articulo);
    }

    /**
     * Agrega una temporada en el inventario.
     * @param arreglo con la informacion de la temporada.
     */
    public void agregarJuegoRecreativo (String [] articulo) {
        this.Inventario.agregarJuegoRecreativo(articulo);
    }
    
    /**
     * Agrega un juego educativo en el inventario.
     * @param arreglo con la informacion del juego educativo.
     */
    public void agregarJuegoEducativo (String [] articulo) {
        this.Inventario.agregarJuegoEducativo(articulo);   
    }
    
    /**
     * Agrega una temporada en el inventario.
     * @param arreglo con la informacion de la temporada.
     */
    public void agregarAsociado(String[] asociado){
        this.Asociados.agregarAsociado(asociado);
        Tienda.UltimaLicencia++;
    }
    
    /**
     * Agregar un asociado
     * @param asociado
     * @throws EmpleadoException 
     */     
    public void ejecutarAsociar (String [] asociado) throws EmpleadoException {
                System.out.println("Asociar");
        String respuesta = this.Asociados.Asociar(asociado, Tienda.UltimaLicencia); 
        this.escribirReporteEmpleado("r"+" & "+respuesta);
    }

    /**
     * Colocar en caja un articulo para comprar.
     * @param Comprar
     * @throws EmpleadoException 
     */
    public void ejecutarComprar (String [] Comprar) throws EmpleadoException  { 
                System.out.println("Comprar");
        System.out.println("Compra");        
        Cliente  cliente;
        Articulo articulo;
        String CodigoCliente = Comprar[1];
        String CodigoArticulo = Comprar[2];
        if (CodigoCliente.charAt(0) == 'B' || CodigoCliente.charAt(0) == 'P') { 
            cliente = this.Asociados.getAsociado(CodigoCliente);
            if (cliente == null)
                throw new EmpleadoException(CodigoCliente,"0");
        } else { 
            String Telefono = Comprar[3];
            cliente = new Cliente(CodigoCliente, CodigoArticulo, "", Telefono);
        }
        articulo = this.Inventario.getArticulo(CodigoArticulo);
        if (articulo == null)
            throw new EmpleadoException(CodigoCliente,"1"); 
        this.Caja.agregarCompra(cliente, articulo, CodigoCliente);
    }
    
    /**
     * Colocar en caja un articulo para alquilar.
     * @param Alquiler
     * @throws EmpleadoException 
     */
    public void ejecutarAlquilar(String [] Alquiler) throws EmpleadoException {
                System.out.println("Alquilar");
        Asociado asociado;
        Articulo articulo;
        String CodigoAsociado = Alquiler[1];
        String CodigoArticulo = Alquiler[2];
        asociado = this.Asociados.getAsociado(CodigoAsociado);
        if (asociado == null)
            throw new EmpleadoException(CodigoAsociado,"0");
        articulo = this.Inventario.getArticulo(CodigoArticulo);
        if (articulo == null)
            throw new EmpleadoException(CodigoAsociado,"1");
        boolean b = asociado.AlquilarCaja(articulo);
        if (!b)
            throw new EmpleadoException(CodigoAsociado,"0");
        this.Caja.agregarAlquiler(asociado, articulo, CodigoAsociado);
    }
    
    /**
     * Actualizar datos de la tarjeta de credito.
     * @param Tarjeta
     * @throws EmpleadoException 
     */
    public void ejecutarActualizarTarjeta (String[] Tarjeta) throws EmpleadoException {
                System.out.println("ActualizerTarjeta");
        String Codigo = Tarjeta[1];
        String CodigoTarjeta = Tarjeta[2];
        String Banco = Tarjeta[3];
        String CodigoSeguridad = Tarjeta[4];
        Fecha FechaNueva = new Fecha(Tarjeta[5]);
        Asociado asociado = this.Asociados.getAsociado(Codigo);
        
        if (asociado == null)
            throw new EmpleadoException(Tarjeta[1],"0");
        if (Fecha.getDifference(asociado.getVencimientoTarjeta(), FechaNueva) >=1 ) {
            Tarjeta T = new Tarjeta(CodigoTarjeta, Banco, CodigoSeguridad, FechaNueva);
            asociado.actualizarTarjeta(T);
            this.escribirReporteEmpleado("t"+" & "+Codigo);
        } else
            throw new EmpleadoException(Codigo,"7");
    }
    
    /**
     * Abandonar la tienda.
     * @param Abandonar
     * @throws EmpleadoException 
     */
    public void ejecutarAbandonar (String [] Abandonar) throws EmpleadoException {
                System.out.println("Abandonar");
        Cliente cliente;
        String Codigo = Abandonar[1];
        
        if (Codigo.charAt(0) == 'B' || Codigo.charAt(0) == 'P') {
            cliente = this.Asociados.getAsociado(Codigo);
            if (cliente == null)
                throw new EmpleadoException(Codigo, "0");
            this.Caja.cancelarCompra(Codigo);
            this.Caja.cancelarAlquiler(Codigo);
        } else
            this.Caja.cancelarCompra(Codigo);
    }
    
    /**
     * Realizar pago de un cliente o asociado a la tienda VideoRent.
     * @param Pagar
     * @throws EmpleadoException 
     */
    public void ejecutarPagar (String [] Pagar) throws EmpleadoException {
                System.out.println("Pagar");
        String Codigo = Pagar[1];
        int Monto = Integer.parseInt(Pagar[2]);
        int MontoExtraordinario =  this.Alquiler.getPagoExtraordinario( Codigo,  this.FechaActual);
        int MontoPagar = Caja.getPagoAlquiler(Codigo)+ Caja.getPagoCompra(Codigo)+ MontoExtraordinario;
        Tripleta<Cliente,ArrayList<Articulo>, Integer> compra;
        Tripleta<Asociado,ArrayList<Articulo>, Integer> alquiler;
        compra = Caja.efectuarCompra(Codigo);
        alquiler = Caja.efectuarAlquiler(Codigo); 
        if (alquiler== null && compra == null && MontoExtraordinario == 0){
            throw new EmpleadoException(Codigo,"0");
        }
        if (Monto == MontoPagar ){  
                Asociado a = this.Asociados.getAsociado(Codigo);
                if(alquiler != null ){
                for( int i= 0 ; i< alquiler.get2().size(); i++){
                    this.Alquiler.agregarRegistro(a, alquiler.get2().get(i), new Fecha(FechaActual.toString()));
                    if (! a.AlquilarCaja(alquiler.get2().get(i))){                        
                        System.out.println("TRATO DE ALQUILAR DE MAS");
                        throw new  EmpleadoException(Codigo,"0");
                    }
                }
                Articulo porDisminuir = null;
                for(int i=0; i<compra.get2().size();i++){
                    porDisminuir = compra.get2().get(i);
                    if(! porDisminuir.decrementarCantidad() ){
                        this.Inventario.EliminarArticulo(porDisminuir);
                    }
                }
                }
         this.Alquiler.culminarPago(Codigo);
         Facturar(compra,alquiler, Monto, Codigo, MontoExtraordinario);
        }
              
    } 
    
    /**
     * Devolver un articulo.
     * @param Devolucion 
     */
    public void ejecutarDevolucion(String [] Devolucion) {
                System.out.println("Devolucion");
        this.Alquiler.DevolverArticulo(Devolucion[1], Devolucion[2], FechaActual);
    }
    
    /**
     * Pedir recoger un articulo/
     * @param solicitud 
     */
    public void ejecutarPedirRecoger(String[] solicitud){
                System.out.println("PedirRecoger");
        System.out.println("Pedir Recoger");
        String codigoAsociado = solicitud[1];
        String codigoArticulo = solicitud[2];
        Asociado asoc = this.Asociados.getAsociado(codigoAsociado);
        if(asoc.getTipo().equalsIgnoreCase("Premium")){
        if(this.Alquiler.RecogerArticulo(codigoAsociado,codigoArticulo,new Fecha(this.FechaActual.toString())) )
                this.escribirReporteEmpleado("b"+" & "+codigoAsociado+" & "+codigoArticulo);
        }   
    }
    
    /**
     * Generacion de factura.
     * @param compra
     * @param alquiler
     * @param monto
     * @param Codigo
     * @param Montoe 
     */
    private void Facturar(Tripleta<Cliente,ArrayList<Articulo>, Integer> compra,
            Tripleta<Asociado,ArrayList<Articulo>, Integer> alquiler, int monto, String Codigo,
            int Montoe){
        System.out.println("Factura");
        String Factura =  ""; 
        if (compra != null){
        Cliente c = compra.get1(); 
        Factura = "Numero de Factura: "+this.FacturaActual+"  "+"Fecha: "+FechaActual+"\n";
        Factura = Factura+"Nombre: "+c.getNombre()+" Apellido: "+c.getApellido()+"\n";
        Factura = Factura+"Cedula: "+c.getCedula()+" Telefono: "+c.getTelefono()+"\n";
        }else { //Es asociado
         
        Asociado a = this.Asociados.getAsociado(Codigo);
        Factura = "Numero de Factura: "+this.FacturaActual+"  "+"Fecha: "+FechaActual+"\n";
        Factura = Factura+"Nombre: "+a.getNombre()+" Apellido: "+a.getApellido()+"\n";
        Factura = Factura+"Cedula: "+a.getCedula()+" Telefono: "+a.getTelefono()+"\n";
        this.FacturaActual++;
        }
        if (compra != null){
            for (int i=0; i<compra.get2().size(); i++){
            Factura = Factura+ "\n Compras: ";
            Factura= Factura+"Articulo= "+ compra.get2().get(i).getCodigo()+"\n";
            Factura= Factura+"Nombre ="+ compra.get2().get(i).getNombre()+"\n";
            Factura= Factura+"Año ="+ compra.get2().get(i).getAño()+"\n";
            }
        }
        if (alquiler != null){
            Factura = Factura+ "\n Alquileres: ";
            for (int i=0; i<alquiler.get2().size(); i++){
            Factura= Factura+"Articulo= "+ alquiler.get2().get(i).getCodigo()+"\n";
            Factura= Factura+"Nombre ="+ alquiler.get2().get(i).getNombre()+"\n";
            Factura= Factura+"Año ="+ alquiler.get2().get(i).getAño()+"\n";
            }
        }
        Factura = Factura+"Monto Extraordinario ="+Montoe;    
        this.Facturas.add(Factura);
        this.escribirReporteEmpleado("f"+" & "+Codigo+" & "+monto);
    }
 
}
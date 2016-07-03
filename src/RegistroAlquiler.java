import java.util.ArrayList;

/**Clase implementada para controlar
 * los alquileres de una tienda
 * @author vicente santacoloma
 * @author Andreth Salazar
 * @version 5/10/11
 */
public class RegistroAlquiler {
    
    private ArrayList<Registro> registro;
    /**
     * La subclase Registro tiene un conjunto de datos
     * que representan una asociacion del tipo alquiler
     *  mas un monto asociado a articulos devueltos y multas
     */
    private class Registro  {
        
        private Asociado asociado;
        private int MontoArticulosPerdidos;
        private int MontoDevolucionesSinPagar;
        private ArrayList<Tripleta<Articulo, Fecha, Integer>> datos;
        
     /**
     * @param  Asociado  asociado que alquila
     */
        public Registro (Asociado asociado) {
            this.asociado = asociado;
            this.MontoArticulosPerdidos = 0;
            this.MontoDevolucionesSinPagar = 0;
            this.datos = new ArrayList<Tripleta<Articulo,Fecha,Integer>>();  
        }
        
     /**
     * @return  El asociado de un registro
     */
        public Asociado getAsociado () {
            return this.asociado;
        }
     /**
     * @return  Monto acumulado por articulos perdidos
     */  
        public int getMontoArticuloPerdido (){
            return this.MontoArticulosPerdidos;
        }
     /**
     * @return  monto acumulado por devoluciones sin pagar
     */    
        public int getMontoDevolucion () {
            return this.MontoDevolucionesSinPagar;
        }
     /**
     *  Cuando un asociado cancela se coloca en 0 sus deudas
     */    
        public void clearMontos() {
            this.MontoArticulosPerdidos = 0;
            this.MontoDevolucionesSinPagar = 0;
        }
        
    /** @return  Tripleta de un codigo de articulo
     *  @param  codigo del articulo en posible alquiler
     */   
        private Tripleta<Articulo,Fecha,Integer> getTripleta (String CodigoArticulo) {
            
            for(int i = 0; i<this.datos.size(); i++) {
                Articulo articulo = this.datos.get(i).get1();
                if (CodigoArticulo.equalsIgnoreCase(articulo.getCodigo()))
                    return this.datos.get(i);
            }
            return null;
        }
      
     /*
     *  @param Articulo a agregar y fecha de vencimiento del entrega
     */   
        public void agregarDatos (Articulo articulo, Fecha FechaVencimiento) {            
            Tripleta<Articulo,Fecha,Integer> t = new Tripleta<Articulo,Fecha,Integer>(articulo,FechaVencimiento,0);
            this.datos.add(t);
        }
        
    /*
     *  @param la fecha desde donde la cual calcular la multa
     */    
        public int CalcularMultaArticulo(Fecha FechaActual) {
            
            int MultaTotal = 0;
            for(int i = 0; i<this.datos.size(); i++) {
                Articulo articulo = this.datos.get(i).get1();
                int DiasPrestamo = articulo.getCondicionPrestamo()[0];
                int Multa = articulo.getCondicionPrestamo()[2];
                Fecha FechaAlquiler = this.datos.get(i).get2();
                System.out.println(FechaAlquiler);
                System.out.println(FechaActual);
                FechaAlquiler.aumentarDia(DiasPrestamo);
                int FechaDiferencia = Fecha.getDifference(FechaAlquiler, FechaActual);
                if (FechaDiferencia >= DiasPrestamo && FechaDiferencia <=30) {
                    MultaTotal = MultaTotal+Multa*FechaDiferencia;
                    this.datos.get(i).set3(MultaTotal);
                }
            }
            return MultaTotal;
        }
         /*
         *  @param la fecha desde donde la cual calcular cuales articulos 
         * estan perdidos
          */   
        public void ArticulosPerdidos(Fecha FechaActual) {
        
            for(int i = 0; i<this.datos.size(); i++) {         
                Articulo articulo = this.datos.get(i).get1();
                Fecha FechaVencimiento = this.datos.get(i).get2();
                int FechaDiferencia = Fecha.getDifference(FechaVencimiento, FechaActual);   
                if (FechaDiferencia > 30) {
                    this.MontoArticulosPerdidos = this.MontoArticulosPerdidos+articulo.getPrecioVenta();
                    this.datos.remove(i);
                } 
            }
        }
        /*
         *  @param codigoArticulo, FechaActual para devolver
         * un articulo a la tienda 
         */   
        public void DevolverArticulo (String CodigoArticulo, Fecha FechaActual) {
            Tripleta<Articulo,Fecha,Integer> t;
            for(int i = 0; i<this.datos.size(); i++) {
                t = this.datos.get(i);
                Articulo articulo = t.get1();
                if (CodigoArticulo.equalsIgnoreCase(articulo.getCodigo())) {
                    MontoDevolucionesSinPagar = MontoDevolucionesSinPagar+CalcularMultaArticulo(FechaActual);
                    articulo.aumentarCantidad();
                    this.asociado.permitirArticulo(articulo);
                    this.datos.remove(i);
                }
            }
        }
        
        /*
         *  @param la fecha desde donde para recordarle a los asociados premium 
         * las devoluciones
         */   
        public String RecordarDevoluciones(Fecha FechaActual) {
            String articulos = "";
            String asociadoPremium = "";
            Tripleta<Articulo, Fecha, Integer> temp = null;
            for(int i = 0; i<this.datos.size();i++){
                temp = this.datos.get(i);
                int DiasPrestamo= temp.get1().getCondicionPrestamo()[0];
                Fecha entrega = new Fecha(temp.get2().toString());
                entrega.aumentarDia(DiasPrestamo);
                if (Fecha.getDifference(FechaActual, entrega)== 0 && asociado.getTipo().equalsIgnoreCase("premium") ){
                    articulos = articulos +" & "+temp.get1().getCodigo();
                    asociadoPremium = asociado.getCodigo();
                }
            }
            return asociadoPremium+articulos;
        }
        
    }
         /*
         * contructor de la clase externa
         */   
    public RegistroAlquiler () {       
        this.registro = new ArrayList<Registro>();         
    }

         /*no retorna valores
         * @param Asociado que alquilo un Articulo a en una fecha FechaEntrega
         */      
    public void agregarRegistro (Asociado asociado, Articulo articulo, Fecha FechaEntrega) {
        
        Registro r = getRegistro(asociado.getCodigo());
        if (r == null) {
            r = new Registro(asociado);
            r.agregarDatos(articulo, FechaEntrega);
            this.registro.add(r);
        } else {
            r.agregarDatos(articulo, FechaEntrega);           
        }   
    }
    
         /* @return El registro de un cliente
         * @param el codigo de un asociado en sistema
         */   
    private Registro getRegistro(String CodigoAsociado) {
        
        for(int i = 0; i<registro.size();i++) {     
            Registro r = registro.get(i);
            if (CodigoAsociado.equalsIgnoreCase(r.getAsociado().getCodigo())) {
                return r;
            }  
        }
        return null;
    }
    
         /* @return no retorna parametros
         * @param El codigo de un cliente para cancelar su deuda
         */     
    public void culminarPago (String Codigo) {
        Registro r = getRegistro(Codigo);
        if (r != null)
            r.clearMontos();
    }
    
         /* @return no retorna parametros
         * @param Fecha actual para verificar los articulos que 
         * deben reportarse como perdidos
         */   
    public void ArticulosPerdidos (Fecha FechaActual) {
        
        for(int i = 0; i<registro.size(); i++) {
            registro.get(i).ArticulosPerdidos(FechaActual);
        }
    }
    
         /* @return el monto por devoluciones sin pagar y articulos perdidos
         * @param Fecha actual para verificar los articulos que 
         * deben reportarse como perdidos
         */ 
    public int getPagoExtraordinario (String Codigo, Fecha FechaActual) {
        Registro r = getRegistro(Codigo);
        if (r != null){
            return r.getMontoArticuloPerdido()+r.getMontoDevolucion();
        }
            return 0;
    }
               /* @return no retorna parametros
         * @param un Asociado planea devolver un articulo en una fecha
         * este metodo busca un registro con los datos del codigo asociado y devuelve el articulo
         * a la tienda 
         */   
    public void DevolverArticulo (String CodigoAsociado, String CodigoArticulo, Fecha FechaActual) {
        
        Registro r = getRegistro(CodigoAsociado);
        if (r != null) {
           r.DevolverArticulo(CodigoArticulo, FechaActual);     
        }    
    }
    
         /* @return  retorna si en caso de que el asociado pueda activar la funcion
         * de pedir que se le recogan sus articulos  
         * @param recibe un codigo de Asociado un codigo de articulo y una fecha
         */   
    public boolean RecogerArticulo(String CodigoAsociado, String CodigoArticulo, Fecha fecha){
        Registro r= getRegistro(CodigoAsociado);
        if (r!= null){
             r.DevolverArticulo( CodigoArticulo,  fecha);
             return true;
        }
        return false;
    }
        /* @return  un string con el comando de reporte de devolucion
         * @param rrecibe una fecha para saber a que productos se le debe recordar
         */   
    public String RecordarDevoluciones(Fecha FechaActual) {
 
        String mensaje="";
        for(int i = 0; i<this.registro.size(); i++) {
            mensaje= mensaje + this.registro.get(i).RecordarDevoluciones(FechaActual)+"\n";
  
        }
        return mensaje;
    }
    
}

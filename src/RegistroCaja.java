import java.util.ArrayList;

/**
 * Clase que mantiene el control de las compras y los articulos para alquilar sin pagar
 * @author  Andreth Salazar
 * @author Vicente Santacoloma
 * @version 5/10/11
 */


public class RegistroCaja {
    
    private ArrayList<Tripleta<Cliente,ArrayList<Articulo>, Integer>> ArticulosComprar;
    private ArrayList<Tripleta<Asociado,ArrayList<Articulo>,Integer>> ArticulosAlquilar;
    /** Constructor de la clase inicializa los dos parametros como new ArrayList
     */    
    public RegistroCaja () {
    
        this.ArticulosComprar = new ArrayList<Tripleta<Cliente, ArrayList<Articulo>, Integer>>();
        this.ArticulosAlquilar = new ArrayList<Tripleta<Asociado, ArrayList<Articulo>, Integer>>();
    }
     /** Agrega una compra al registro de caja
     * @param  Cliente a comprar, Articulo y codigo del Articulo
     * @return   void
     */
    public void agregarCompra(Cliente cliente, Articulo articulo, String Codigo) {
        
        Tripleta<Cliente, ArrayList<Articulo>, Integer> t = getCompra(Codigo);
        articulo.decrementarCantidad();
        if (t != null) {
            t.get2().add(articulo);
            t.set3(t.get3()+articulo.getPrecioVenta());
        } else {
            ArrayList<Articulo> l = new ArrayList<Articulo>();
            l.add(articulo);
            t = new Tripleta<Cliente, ArrayList<Articulo>, Integer>(cliente, l,articulo.getPrecioVenta());
            this.ArticulosComprar.add(t);
        } 
    }
     /** Agrega un alquiler al registro de caja
     * @param  Cliente a alquilar, Articulo y codigo del Articulo
     * @return   void
     */    
    public void agregarAlquiler(Asociado asociado, Articulo articulo, String Codigo) {
        
        Tripleta<Asociado, ArrayList<Articulo>, Integer> t = getAlquiler(Codigo);
        articulo.decrementarCantidad();
        if (t != null) {
            t.get2().add(articulo);
            t.set3(t.get3()+articulo.getPrecioPrestamo());
        } else {
            ArrayList<Articulo> l = new ArrayList<Articulo>();
            l.add(articulo);
            t = new Tripleta<Asociado, ArrayList<Articulo>, Integer>(asociado,l,articulo.getPrecioPrestamo());
            this.ArticulosAlquilar.add(t);
        }       
    }
      /** Culmina la compra de caja removiendo de las listas las compras de un ciente
     * @param  Cliente a terminar la compra
     * @return  Devuelve la tripleta Cliente Articulos Monto final 
     */   
    public Tripleta<Cliente,ArrayList<Articulo>, Integer> efectuarCompra(String Codigo) {
        
        Tripleta<Cliente, ArrayList<Articulo>, Integer> t;
        
        for(int i = 0; i<this.ArticulosComprar.size(); i++) {
            t = this.ArticulosComprar.get(i);
            if (t.get1() instanceof Asociado) {
                if (Codigo.equalsIgnoreCase(((Asociado)t.get1()).getCodigo())) {
                    this.ArticulosComprar.remove(i);
                    return t;
                }
            } else if (t.get1() instanceof Cliente) {   
                if (Codigo.equalsIgnoreCase(t.get1().getCedula())) {
                   this.ArticulosComprar.remove(i); 
                   return t;
                }
            }
        }  
        return null;
    }
     /** calcula el pago por alquiler de un Asociado
     * @param  codigo del asociado que alquilo algun Articulo
     * @return int con el monto a pagar por alquiler
     */    
    public int getPagoAlquiler(String codigoAsociado){
        
        for (int i = 0 ; i<this.ArticulosAlquilar.size(); i++){
            if (this.ArticulosAlquilar.get(i).get1().getCodigo().equalsIgnoreCase(codigoAsociado)){
                return  this.ArticulosAlquilar.get(i).get3();
            }
        }
        return 0;
    }
     /** obtiene el monto total por motivo de compra de un cliente
     * @param  codigo del cliente (cedula)
     * @return   entero con el monto a pagar por concepto de compra
     */    
    public int getPagoCompra(String codigoCliente){
       for (int i = 0 ; i<this.ArticulosComprar.size(); i++){
           
           Cliente c = this.ArticulosComprar.get(i).get1();
          if (c instanceof Asociado && ((Asociado)c).getCodigo().equalsIgnoreCase(codigoCliente)){
              return this.ArticulosComprar.get(i).get3();
          }else{ 
             if(c.getCedula().equals(codigoCliente)){
                 return this.ArticulosComprar.get(i).get3();
             }
        }
       }
        return  0;
    }
     /** moviliza los articulos de caja y devuelve
     * lo que sera el registro de un alquiler
     * @param  codigo del cliente que quiere pagar un alquiler
     * @return la informacion para agregar en registro de alquileress
     */       
    public Tripleta<Asociado,ArrayList<Articulo>, Integer> efectuarAlquiler(String Codigo) {
        
        Tripleta<Asociado, ArrayList<Articulo>, Integer> t;
        
        for(int i = 0; i<this.ArticulosAlquilar.size(); i++) {
            t = this.ArticulosAlquilar.get(i);
            if (Codigo.equalsIgnoreCase(t.get1().getCodigo())) {
                this.ArticulosAlquilar.remove(i); 
                return t;
            }
        }
        return null;
    }
            
     /** Cancela una compra en caja y recupera la disponibilidad de los articulos
     * @param  cliente que compro
     * @return   void
     */    
    public void cancelarCompra (String Codigo) {
        
        Tripleta<Cliente, ArrayList<Articulo>, Integer> t;
        
        for(int i = 0; i<this.ArticulosComprar.size(); i++) {
            t = this.ArticulosComprar.get(i);
            if (t.get1() instanceof Asociado) {
                if (Codigo.equalsIgnoreCase(((Asociado)t.get1()).getCodigo())) {
                    ArrayList<Articulo> l = t.get2();
                    for(int j = 0; j<l.size(); j++)
                        l.get(j).aumentarCantidad();
                    this.ArticulosComprar.remove(i);
                }
            } else if (t.get1() instanceof Cliente) {   
                if (Codigo.equalsIgnoreCase(t.get1().getCedula())) {
                    ArrayList<Articulo> l = t.get2();
                    for(int j = 0; j<l.size(); j++)
                        l.get(j).aumentarCantidad();
                   this.ArticulosComprar.remove(i);     
                }
            }
        }  
    }
     /** Agrega un alquiler al registro de caja
     * @param cliente que llevo a caja  articulos para alquilar
     * @return   void
     */    
    public void cancelarAlquiler (String Codigo) {
        
        Tripleta<Asociado, ArrayList<Articulo>, Integer> t;
        
        for(int i = 0; i<this.ArticulosAlquilar.size(); i++) {
            t = this.ArticulosAlquilar.get(i);
            if (Codigo.equalsIgnoreCase(t.get1().getCodigo())) {
                ArrayList<Articulo> l = t.get2();
                for(int j = 0; j<l.size(); j++)
                    l.get(j).aumentarCantidad();
                this.ArticulosAlquilar.remove(i); 
            }
        }  
    }
         /** Retorna el tamano del arreglo compra
     * @param  no param
     * @return   size de compra
     */
    public int tamanoCompra () {
        return this.ArticulosComprar.size();
    }
         /** Retorna el tamano del arreglo alquileres
     * @param  no param
     * @return   size de ArticulosAlquilar
     */    
    public int tamanoAlquiler() {
        return this.ArticulosAlquilar.size();
    }
         /** Retorna el String de las compras
     * @param  no param
     * @return  void
     */    
    public void imprimirCompra() {
        for(int i = 0; i<this.ArticulosComprar.size(); i++)
            System.out.println(this.ArticulosComprar.get(i));
    }
     /** Retorna el String de los alquileres
     * @param  no param
     * @return  void
     */    
    public void imprimirAlquiler() {
        for(int i = 0; i<this.ArticulosAlquilar.size(); i++)
            System.out.println(this.ArticulosAlquilar.get(i));
    }
     /**Obtiene una tripleta de compra de un cliente
     * @param codigo del cliente
     * @return  tripleta con las compras e un cliente
     */     
    private Tripleta<Cliente, ArrayList<Articulo>, Integer> getCompra (String Codigo) { 
        Tripleta<Cliente, ArrayList<Articulo>, Integer> t;
        for(int i = 0; i<this.ArticulosComprar.size(); i++) {
            t = this.ArticulosComprar.get(i);
            if (t.get1() instanceof Asociado) {
                if (Codigo.equalsIgnoreCase(((Asociado)t.get1()).getCodigo()))
                   return t;
            } else if (t.get1() instanceof Cliente) {   
                if (Codigo.equalsIgnoreCase(t.get1().getCedula()))
                   return t;      
            }
        }
        return null; 
    }
     /**Obtiene una tripleta de alquiler de un cliente
     * @param codigo del cliente
     * @return  tripleta con las alquiler e un asociado
     */        
    private Tripleta<Asociado, ArrayList<Articulo>, Integer> getAlquiler (String Codigo) {     
        for(int i = 0; i<this.ArticulosAlquilar.size(); i++) {
            Tripleta<Asociado, ArrayList<Articulo>, Integer> t = this.ArticulosAlquilar.get(i);
            if (Codigo.equalsIgnoreCase(t.get1().getCodigo()))
                return t; 
        }
        return null; 
    } 
}

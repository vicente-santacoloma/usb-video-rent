 /**
 * Clase que reprenta los clientes de la tienda VideoRent.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */

public class Cliente {
    
    protected String Cedula;
    protected String Nombre;
    protected String Apellido;
    protected String Telefono;
    
    public Cliente(String c, String n, String a,String t){
        this.Cedula = c;
        this.Nombre = n ;
        this.Apellido = a;
        this.Telefono = t;
    }
    
    /**
     * Devuelve el nombre de un cliente.
     * @return String con el nombre del cliente
     */
    public String getNombre() {
        return Nombre;
    }
    
     /**
     * Devuelve el apellido de un cliente.
     * @return String con el apellido del cliente;
     */
    public String getApellido() {
        return Apellido;
    }
    
    /**
     * Devuelve cedula de un cliente.
     * @return String con el numero de cedula 
     */
    public String getCedula() {
        return Cedula;
    }


    /**
     * Devuelve telefono de cliente.
     * @return String con el telefono
     */
    public String getTelefono() {
        return Telefono;
    }






   
}

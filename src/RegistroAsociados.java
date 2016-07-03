import java.util.ArrayList;

/**
 * Clase que almacena los asociados de una tienda
 * @author  Andreth Salazar
 * @author Vicente Santacoloma
 * @version 5/10/11
 */

public class RegistroAsociados {
    
    private ArrayList<Asociado>  Asociados;

    /** Constructor de la clase no recibe parametros
     */ 
    public RegistroAsociados () {
        this.Asociados = new ArrayList<Asociado>();
    }
    /** Obtener un asociado del registro de asociados
     * @param  codigo del asociado
     * @return  el asociado que cumple con ese parametro
     */
    public Asociado getAsociado (String Codigo) {
        Asociado asociado;
        for (int i = 0; i<this.Asociados.size(); i++) {
           asociado = this.Asociados.get(i);
           if (Codigo.equalsIgnoreCase(asociado.getCodigo())) 
               return asociado;
        }
        return null;
    }
    /** Agregar un asociado al registro
     * @param  un arreglo con los parametros necesarios de un asociado
     * @return no retorna parametros
     */    
    public void agregarAsociado(String[] asociado) {
        Asociado a = getAsociado(asociado[0]);
        /*if(a != null)
            throw new EmpleadoException(Codigo,"5");*/
        String Tipo="";
        if (asociado[0].charAt(0) == 'B')
            Tipo = "basico";
        else 
            Tipo = "premium";                
        String Codigo = asociado[0];
        String Estado = asociado[1];
        String Cedula = asociado[2];
        String Nombre = asociado[3];
        String Apellido = asociado[4];
        String Telefono = asociado[5];
        String DireccionFiscal = asociado[6];
        String Numero = asociado[7];
        String Banco = asociado[8];
        String CodigoSeguridad = asociado[9];
        Fecha FechaVencimiento = new Fecha( asociado[10]);
        Tarjeta t = new Tarjeta (Numero,Banco,CodigoSeguridad,FechaVencimiento);
        a = new Asociado(Cedula, Nombre, Apellido, Telefono, DireccionFiscal,
        t, Estado, Tipo, Codigo);   
        this.Asociados.add(a);
    }
    /** Asociar una persona a la tienda
     * @param  codigo del asociado, la ultima liencia
     * @return  retorna el codigo del asociado
     */    
    public String Asociar (String [] asociado, int UltimaLicencia) throws EmpleadoException {
        Asociado a = getAsociado(asociado[1]);
        String Codigo = "";
        String Tipo = "";
        if (asociado[10].equalsIgnoreCase("basica")) {
            Codigo = "B"+UltimaLicencia;
            Tipo = "basico";
        } else {
            Codigo = "P"+UltimaLicencia;
            Tipo = "premium";
        } 
        if (a != null) 
            throw new EmpleadoException(Codigo,"5");
        String Cedula = asociado[1]; 
        String Nombre = asociado[2];
        String Apellido = asociado[3];
        String Telefono = asociado[4];
        String DireccionFiscal = asociado[5];
        String Numero = asociado[6];
        String Banco = asociado[7];
        String CodigoSeguridad = asociado[8];
        Fecha FechaVencimiento = new Fecha(asociado[9]);
        String Estado = "activo";            
        Tarjeta t = new Tarjeta (Numero,Banco,CodigoSeguridad,FechaVencimiento);        
        Asociado c = new Asociado(Cedula, Nombre, Apellido, Telefono, DireccionFiscal,
                        t, Estado, Tipo, Codigo); 
        this.Asociados.add(c); 
        return Codigo;
    }
    /** Muetsra por salida estandar los asociados
     * @param  no parametros
     * @return  void
     */    
    public void imprimirAsociados() {
        for(int i = 0; i<Asociados.size(); i++)
            System.out.println(Asociados.get(i));
    }
    /**Coloca a los asociados con un formato de salida de archivo
     * @param  no recibe parametros 
     * @return String con todos los asociados en formato archivo
     */    
    @Override
    public String toString() {
        
        String mensaje = this.Asociados.size()+"\n";
        for(int i = 0; i<this.Asociados.size(); i++)
            mensaje = mensaje + this.Asociados.get(i).toString()+"\n";
        return mensaje;
    }
     /** Suspende a aquellos asociados con menos de 35 dias
     * por vencer su tarjeta
     * @param  Fecha Actual
     * @return  void
     */  
    public void ejecutarSuspensiones(Fecha fecha){
        for(int i=0; i<this.Asociados.size(); i++){
            if( ((Asociado)Asociados.get(i)).getEstado().equalsIgnoreCase("activo") 
                && Fecha.getDifference(((Asociado)Asociados.get(i)).getVencimientoTarjeta(),fecha) < 35){
                ((Asociado)Asociados.get(i)).suspender();
            }
        }
    }
    
    
}

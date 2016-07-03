/**
 * Clase que arepresenta la tarjeta de credito
 * @author  Andreth Salazar
 * @author Vicente Santacoloma
 * @version 5/10/11
 */
public final class Tarjeta {
    private String Numero;
    private String Banco;
    private String CodigoSeguridad;
    private Fecha FechaVencimiento;

    public Tarjeta(String Numero, String Banco, String CodigoSeguridad, Fecha FechaVencimiento) {
        this.Numero = Numero;
        this.Banco = Banco;
        this.CodigoSeguridad = CodigoSeguridad;
        this.FechaVencimiento = FechaVencimiento;
    }

    
    public String getBanco() {
        return Banco;
    }

    public String getCodigoSeguridad() {
        return CodigoSeguridad;
    }

    public Fecha getFechaVencimiento() {
        return FechaVencimiento.clone();
    }

    public String getNumero() {
        return Numero;
    }
 
    
    
}

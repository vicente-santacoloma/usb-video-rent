/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicente
 */
public class EmpleadoException extends Exception {
    
    private String Codigo;
    private String Error;
    
    public EmpleadoException (String Codigo, String Error) {
        
        this.Codigo = Codigo;
        this.Error = Error;
    }
    
    public String getCodigo () {
        return this.Codigo;
    }
    
    public String getError() {
        return this.Error;
    }
}

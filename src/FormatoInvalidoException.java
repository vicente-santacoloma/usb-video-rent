/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicente
 */
public class FormatoInvalidoException extends Exception {

    private String mensaje;
    
    public FormatoInvalidoException (String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String toString() {
        return mensaje;
    }
    
}

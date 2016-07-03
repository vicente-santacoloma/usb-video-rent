import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase que representa la tienda VideoRent.
 * 
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 */
public class VideoRental {

   private Tienda TiendaVideo;
    /**
     * @param args the command line arguments
     */
    
    public VideoRental () {
        this.TiendaVideo = new Tienda();      
    }
    
    /**
     * Carga los articulos del archivo "articulosExistentesAntes.txt"
     * a la tienda VideoRent.
     */
    private void CargarArticulos ()  {
        
        String linea = "";
        BufferedReader in = null;
        int i = 0;
        int cantidad = 0;
        String [] articulo;
        char codigo;
        
        try {
            in = new BufferedReader(new FileReader("articulosExistentesAntes.txt"));
            if ((linea = in.readLine()) != null)                
                cantidad = Integer.parseInt(linea);
            else
               throw new FormatoInvalidoException("Archivo Nulo");               
            while((linea = in.readLine()) != null && (i != cantidad)) {                          
                if (linea.equalsIgnoreCase(""))
                  throw new FormatoInvalidoException("Linea Vacia");
                articulo = linea.split(" & ");
                codigo = articulo[0].charAt(0);
                switch(codigo) {                   
                    case 'P' : TiendaVideo.agregarPelicula(articulo); break;
                    case 'S' : TiendaVideo.agregarTemporada(articulo); break;
                    case 'R' : TiendaVideo.agregarJuegoRecreativo(articulo); break;
                    case 'E' : TiendaVideo.agregarJuegoEducativo(articulo); break;                
                }
                i++;                 
            }
        } catch (FormatoInvalidoException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Existente");
            System.exit(1);
        } catch (Exception ioe) {    
            System.out.println("Archivo Nulo");
            System.exit(1);
        }  
    }
    
    /**
     * Carga los clientes asociados del archivo "clientesAsociadosAntes.txt" a la tienda VideoRent.
     */
    private void CargarAsociados () {
        
        String linea = "";
        BufferedReader in = null;
        String [] asociado;
        int i = 0;
        int cantidad = 0;  
        try {
            in = new BufferedReader(new FileReader("clientesAsociadosAntes.txt"));
            if ((linea = in.readLine()) != null) {   
                String [] aux = linea.split(" ");
                TiendaVideo.setUltimaLicencia(Integer.parseInt(aux[1]));
                cantidad = Integer.parseInt(aux[0]);               
            } else
                throw new FormatoInvalidoException("Archivo Nulo");
            while((linea = in.readLine()) != null && (i != cantidad)) {
                if (linea.equalsIgnoreCase(""))
                  throw new FormatoInvalidoException("Linea Vacia");
                asociado = linea.split(" & ");
                TiendaVideo.agregarAsociado(asociado); 
                i++;
            }
        } catch (FormatoInvalidoException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no Existente");
            System.exit(1);
        } catch (Exception ioe) {    
            System.out.println("Archivo Nulo"+linea);
            System.exit(1);
        }
    }
    
    
    /**
     * Carga y ejecuta del archivo "accionesClientes.txt"
     * las acciones de los clientes de la tienda VideoRent.
     */
    private void CargarAccionesClientes () {
        
        String linea = "";
        BufferedReader in = null;
        int numDias = 0;
        int numAcciones = 0;
        int verificarAccion = 0;
        char codigo;
        Fecha fecha= new Fecha("01/01/01");  
        try {
            in = new BufferedReader(new FileReader("accionesClientes.txt"));
            for(int i = 0; i<2; i++) {        
              if ((linea = in.readLine()) != null) {            
                if (i == 0) {
                    String [] aux = linea.split(" "); 
                    numDias = Integer.parseInt(aux[0]);
                    numAcciones = Integer.parseInt(aux[1]);  
                } else 
                fecha = new Fecha(linea); 
                TiendaVideo.setFechaActual(fecha);
              }
            }
            if ((linea = in.readLine()).charAt(0) != '#')
                throw new FormatoInvalidoException("Caracter Inesperado");
            ArrayList<String> l = new ArrayList<String>();
            for(int i = 0; i<numDias; i++) {       
              while ((linea = in.readLine()).charAt(0) != '#') {
                  if (linea.equalsIgnoreCase(""))
                    throw new FormatoInvalidoException("Linea Vacia");
                  codigo = linea.charAt(0);
                  l.add(linea);
                  String [] accion = linea.split(" & ");
                  switch(codigo) {
                      case 'a' : TiendaVideo.ejecutarAsociar(accion);  break;
                      case 't' : TiendaVideo.ejecutarActualizarTarjeta(accion); break;
                      case 'c' : TiendaVideo.ejecutarComprar(accion);  break;
                      case 'r' : TiendaVideo.ejecutarAlquilar(accion); break;
                      case 'p' : TiendaVideo.ejecutarPagar(accion); break;
                      case 'b' : TiendaVideo.ejecutarAbandonar(accion); break;
                      case 'd' : TiendaVideo.ejecutarDevolucion(accion); break;
                      case 'e' : TiendaVideo.ejecutarPedirRecoger(accion); break;
                      default: //raise
                  }
                  verificarAccion++;
              }
              TiendaVideo.NuevaFecha();
              TiendaVideo.chequear(l);
              TiendaVideo.actualizar();
              l.clear();
              TiendaVideo.escribirReporteEmpleado("#");
           }    
         } catch (FormatoInvalidoException e)  {
            System.out.println(e.toString());
            System.exit(1);
         } catch (EmpleadoException e) {
            System.out.println("i & "+e.getCodigo()+" & "+e.getError());

         } catch (Exception ioe) {
            System.out.println("Formato Invalido"+linea);
            System.exit(1);
        }
    }
    
    
    /**
     * Imprime en el archivo "" el inventario.
     * @throws FileNotFoundException 
     */
    public void imprimirInventario() throws FileNotFoundException {
        TiendaVideo.imprimirInventario();
    }
    
    /**
     * Imprime en el archivo "" los clientes asociados.
     * @throws FileNotFountException
     */
    public void imprimirAsociados() throws FileNotFoundException {
        TiendaVideo.imprimirAsociados();
    }
    
    /**
     * Imprime mediante salida estandar los articulos a compras de los clientes.
     */
    public void imprimirCajaCompra () {
        TiendaVideo.imprimirCajaCompra();
    }
    
    /**
     * Imprime mediante salida estandar los articulos a alquileres de 
     * los asociados.
     */
    public void imprimirCajaAlquiler () {
        TiendaVideo.imprimirCajaAlquiler();
    }
    
    /**
     * Imprime en el arhcivo "" el reporte del empleado
     */
    public void imprimirReporteEmpleado () throws FileNotFoundException {
        TiendaVideo.imprimirReporteEmpleado();
    }
       
    /**
     * Metodo main que inicia la clase VideoRental 
     * 
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        VideoRental t = new VideoRental();
        t.CargarArticulos();
        t.CargarAccionesClientes();
        t.CargarAsociados();
        t.imprimirInventario();
        t.imprimirAsociados();
        t.imprimirReporteEmpleado();
    }
}
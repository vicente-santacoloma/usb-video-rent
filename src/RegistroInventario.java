import java.util.ArrayList;
/**
 * Clase que almacena los articulos de la tienda
 * @author  Andreth Salazar
 * @author Vicente Santacoloma
 * @version 5/10/11
 */

public class RegistroInventario {
    
    private ArrayList<Articulo> Inventario; 
    
/** constructor de la clase no recibe parametros
 * inicializa el arreglo inventario
 */         
    public RegistroInventario () {  
        this.Inventario = new ArrayList<Articulo>();  
    }
/** Metodo agregarPelicula agrega una pelicula al inventario
 * @param articulo es un arreglo con todos los campos de pelicula
 * @return no retorna parametros
 */          
    public void agregarPelicula (String [] articulo) {
        String Codigo = articulo[0];
        int Cantidad  = Integer.parseInt(articulo[1]);
        String Nombre = articulo[2];
        int Año = Integer.parseInt(articulo[3]);
        String Genero = articulo[4];
        String Formato = articulo[5];
        String [] Directores = articulo[6].split(", ");
        String [] Escritores = articulo[7].split(", ");
        String [] Actores = articulo[8].split(", ");
        String Secuela = "";
        if (articulo.length == 10)
            Secuela = articulo[9];        
        Articulo p = new Pelicula(Cantidad, Codigo, Nombre, Año, Genero, Formato,
                Directores, Escritores, Actores, Secuela);        
        this.Inventario.add(p);        
    }
/** Metodo agregarTemporada agrega  una  temporada al inventario
 * @param articulo es un arreglo con todos los campos de temporada
 * @return no retorna parametros
 */             
    public void agregarTemporada (String [] articulo) {
        String Codigo = articulo[0];
        int Cantidad  = Integer.parseInt(articulo[1]);
        String Nombre = articulo[2];
        int NumeroTemporada = Integer.parseInt(articulo [3]);
        int Año = Integer.parseInt(articulo[4]);
        String [] Creadores  = articulo[5].split(", ");
        String [] Actores = articulo[6].split(", ");
        int CantidadEpisodios = Integer.parseInt(articulo[7]);         
        Articulo t = new Temporada (Cantidad, Nombre, Año, "", Codigo,
                NumeroTemporada, Creadores, Actores, CantidadEpisodios);
        this.Inventario.add(t);
    }
/** Metodo agregarJuegoRecreativo agrega un juegoRecreativo al inventario 
 * @param articulo es un arreglo con todos los campos de juegoRecreativo
 * @return no retorna parametros
 */             
    public void agregarJuegoRecreativo (String [] articulo) {       
        String Codigo = articulo[0];
        int Cantidad = Integer.parseInt(articulo[1]);
        String Nombre = articulo[2];
        String Genero = articulo[3];
        String Desarrollador = articulo[4];
        String Plataforma = articulo[5];
        int Año = Integer.parseInt(articulo[6]);        
        Articulo r = new JuegoRecreativo(Cantidad, Nombre, Año, Genero, Codigo, 
                Plataforma, Desarrollador);        
        this.Inventario.add(r);
    }
 /** Metodo agregarJuegoEducativo agrega un juegoEducativo al inventario 
 * @param articulo es un arreglo con todos los campos de juegoEducativo
 * @return no retorna parametros
 */    
    public void agregarJuegoEducativo (String [] articulo) {        
        String Codigo = articulo[0];
        int Cantidad = Integer.parseInt(articulo[1]);
        String Nombre = articulo[2];
        String Destreza = articulo[3];
        String Plataforma = articulo[4];
        int Año = Integer.parseInt(articulo[5]);                
        Articulo e = new JuegoEducativo(Cantidad, Nombre, Año, null, Codigo, 
                Plataforma, Destreza);        
        this.Inventario.add(e);
    }  
/** Obtiene un articulo del inventario
 * @param codigo del articulo a agregar
 * @return retorna el articulo que corresponde a ese codigo
 */     
    public Articulo getArticulo (String Codigo) {
        
        Articulo articulo;
        for(int i = 0; i<Inventario.size(); i++) {
            articulo = Inventario.get(i);
            if (Codigo.equalsIgnoreCase(articulo.getCodigo())) {
                return articulo;
            }
        }
        return null;
    }
    
 /** Metodo para imprimir el inventario de la tienda
 * @param no recibe parametros
 * @return no retorna parametros
 */    
    public void imprimirInventario() {
        for (int i = 0; i<Inventario.size(); i++)
            System.out.println(Inventario.get(i));
    }
/** ToString de la clase coloca en formato de impresion a Inventario
 * @param no recibe parametros
 * @return  String con cada articulo linea a linea
 */     
    @Override
    public String toString() {
        
        String mensaje = this.Inventario.size()+"\n";
        for(int i = 0; i<this.Inventario.size(); i++)
            mensaje = mensaje + this.Inventario.get(i).toString();
        return mensaje;
    }
  /**Eliminar un articulo del inventario
 * @param Articulo a a elminar en el inventario
 * @return no retorna parametros
 */   
    public void EliminarArticulo(Articulo a ){
        for (int i=0 ; i< this.Inventario.size(); i++){
           if(this.Inventario.get(i).getCodigo().equalsIgnoreCase(a.getCodigo())){
                   this.Inventario.remove(i) ;
                   System.out.println(this.Inventario.toString());
        }
        }
    }
}

import java.util.Arrays;

 /**
 * Clase que reprenta los articulos jtemporadas.
 * @author andreth salazar 
 * @author vicente santacoloma
 * @version 5/10/11
 */

public final class Temporada extends ArticuloAudioVisual {
    
    private int NumeroTemporada;
    private String[] Creadores;
    private int CantidadEpisodios;
    private static int PrecioVenta= 100;
    
    /**
     * Contructor de la clase Temporada
     * @param cantidad
     * @param nombre
     * @param año
     * @param genero
     * @param codigo
     * @param numeroTemporada
     * @param creadores
     * @param actores
     * @param cantidadEpisodios 
     */
    public Temporada(int cantidad, String nombre, int año, String genero, String codigo,
            int numeroTemporada, String[] creadores, 
            String[] actores, int cantidadEpisodios){
       super(cantidad, nombre,año,genero,codigo,actores); 
       this.NumeroTemporada= numeroTemporada;
       this.Creadores = creadores;
       this.CantidadEpisodios = cantidadEpisodios; 
       try { chequearGenero();}
       catch( GeneroInvalidoException e)
       { System.out.println("Genero de Temporada Invalido");}
    }

    /**
     * Devuelve la cantidad de episodios.
     * @return int que contiene cantidad de episodios
     */
    public int getCantidadEpisodios() {
        return CantidadEpisodios;
    }

    /**
     * Modificar cantidad de episodios.
     * @param int CantidadEpisodios 
     */
    public void setCantidadEpisodios(int CantidadEpisodios) {
        this.CantidadEpisodios = CantidadEpisodios;
    }

    /**
     * Devuelve los creadores de una temporada.
     * @return arreglo de String que contiene los creadores
     */
    public String[] getCreadores() {
        return Creadores;
    }

    /**
     * Modificar los creadores de una temporada.
     * @param Creadores
     */
    public void setCreadores(String[] Creadores) {
        this.Creadores = Creadores;
    }

    @Override
    public int getPrecioVenta(){
        return Temporada.PrecioVenta;
    }
    
    /**
     * Devuelve el numero de temporadas.
     * @return 
     */
    public int getNumeroTemporada() {
        return NumeroTemporada;
    }

    /**
     * Modificar numero de temporada.
     * @param NumeroTemporada 
     */
    public void setNumeroTemporada(int NumeroTemporada) {
        this.NumeroTemporada = NumeroTemporada;
    }   
    
    @Override
    public boolean equals(Articulo a){
     return false;
    }
    
    /**
     * Evalua si dos temporadas son iguales.
     * @param temporada
     * @return true si son iguales, falso en caso contrario
     */
    public boolean equals(Temporada a)  {
        if(this.Nombre.equals(a.getNombre()) 
          && Arrays.equals( this.Actores, a.getActores()) 
          && this.Año == a.getAño() 
          && this.CantidadEpisodios== a.getCantidadEpisodios()
          && Arrays.equals(this.Creadores, a.getCreadores() )
          && this.NumeroTemporada == a.getNumeroTemporada()
          && this.Genero.equalsIgnoreCase(a.getGenero())){
            return true;
        }else{
            return false;
        }
    }
    
        @Override
    public String toString(){
        String mensaje="";
        String ampersan = " & ";   
        mensaje = this.Codigo+ ampersan + this.Cantidad + ampersan;
        mensaje= mensaje + this.Nombre + ampersan +this.NumeroTemporada+ ampersan+ this.Año + ampersan;
        mensaje = mensaje + this.Genero + ampersan+ ampersan;
        for(int i= 0 ; i<this.Creadores.length-1; i++){
            mensaje = mensaje + this.Creadores[i]+" , ";
        }
        mensaje = mensaje+ this.Creadores[this.Creadores.length-1]+ ampersan;
        for(int i= 0 ; i<this.Actores.length-1; i++){
            mensaje = mensaje + this.Actores[i]+" , ";
        }
        mensaje = mensaje + this.Actores[this.Actores.length-1]+ ampersan;
        mensaje= mensaje + this.CantidadEpisodios + "\n";
        return mensaje;
    }
}

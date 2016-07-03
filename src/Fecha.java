import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase que representa la fehca.
 * 
 * @author andreth salazar
 * @author vicente santacoloma
 * @version 5/10/11
 */
public class Fecha {

    private GregorianCalendar calendar;
    private boolean dayMatter;

    /**
    * 
    * @param str representacion en string de la fecha en formato dd/mm/yyyy o mm/yyyy.
    * Si el dia no se especifica se pone por default 1.
    */
    public Fecha(String str) {
        String[] fecha = str.split("/");
        int dayOfMonth;
        int month;
        int year;
        if (dayMatter = fecha.length == 3) {
            dayOfMonth = Integer.valueOf(fecha[0].trim());
            month = Integer.valueOf(fecha[1].trim());
            year = Integer.valueOf(fecha[2].trim());
        } else {
            dayOfMonth = 1;
            month = Integer.valueOf(fecha[0].trim());
            year = Integer.valueOf(fecha[1].trim());
        }
        calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
    }

    /**
     * Incrementa el dia en uno
     */
    void aumentarDia() {
        calendar.add(Calendar.DAY_OF_YEAR, 1);
    }
  
    /**
     * Incrementa la fecha de acuerdo al numero de dias especificado
     * @param n que denota el numero de dias 
     */
    void aumentarDia( int n) {
        calendar.add(Calendar.DAY_OF_YEAR, n);
    }
    
    /**
    * Devuelve entero con la diferencia entre dos fechas.
    * @param startDate fecha de inicio
    * @param endDate fecha final
    * @return valor absoluto de la diferencia entre endDate y startDate tomando en cuenta el anio
    */
    static int getDifference(Fecha startDate, Fecha endDate) {
        int year1 = startDate.calendar.get(Calendar.YEAR);
        int year2 = endDate.calendar.get(Calendar.YEAR);
        int dias1 = year1*365 + startDate.calendar.get(Calendar.DAY_OF_YEAR);
        int dias2 = year2*365 + endDate.calendar.get(Calendar.DAY_OF_YEAR);
        return dias2 - dias1;
    }
    
    
  
  @Override
  public String toString(){
    int d = calendar.get(Calendar.DAY_OF_MONTH);
    int m = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR);
    return ( dayMatter ? (d < 10 ? "0" + d : d) + "/" : "" ) + (m < 10 ? "0" + m : m) + "/" + year;
  }
  
  @Override
  public Fecha clone(){
    Fecha fecha = new Fecha(this.toString());
    return fecha;
  }
}

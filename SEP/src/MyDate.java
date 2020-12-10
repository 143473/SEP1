import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * A class representing the Date that can be used in multiple other classes.
 * @author Bartosz Ochedzan
 * @version 1.0
 */
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;
  /**
   * Three-argument constructor.
   * @param day
   * @param month
   * @param year
   *
   */
  public MyDate(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Shows date
   * @return shows date in that format
   */
  public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Copies date so it can be used across classes
   * @return copies date
   */
  public MyDate copy(){
  return new MyDate(day,month,year);
  }

  public boolean is15Years(){
    GregorianCalendar currentDate = new GregorianCalendar();
    int currentDay = currentDate.get(GregorianCalendar.DATE);
    int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
    int currentYear = currentDate.get(GregorianCalendar.YEAR);
    MyDate date2 = new MyDate(currentDay, currentMonth, currentYear-15);

    if(year < date2.year){
      return true;
    }
    else{
      if(year > date2.year){
        return false;
      }
      else{
        if(month < date2.month){
          return true;
        }
        else{
          if(month > date2.month){
            return false;
          }
          else{
            if(day < date2.day){
              return true;
            }
            else{
              return false;
            }
          }
        }
      }
    }
  }

  public boolean isLeapYear(){
    if((year%4 == 0)&&(year%100 != 0)){
      return true;
    }
    else if((year%4 == 0)&&(year%400 == 0)){

      return true;
    }
    else{
      return false;
    }
  }

  public int daysInMonth(){
    int daysInMonth = 0;
    switch (month){
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        daysInMonth = 31;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        daysInMonth = 30;
        break;
      case 2:
        if(isLeapYear()){
          daysInMonth = 29;
        }
        else{
          daysInMonth = 28;
        }
    }
    return daysInMonth;
  }

  public boolean isValidDate(){
    if((month < 1)||(month > 12)){
      return false;
    }
    this.month = month;
    if((day > daysInMonth())||(day < 1)){
      return false;
    }
    return true;
  }
}

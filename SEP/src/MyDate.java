//updated in class diagram
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * A class representing the Date that can be used in multiple other classes.
 * @author Bartosz Ochedzan, Marketa Lapcikova
 * @version 1.0
 */
public class MyDate implements Serializable
{
  private int day;
  private int month;
  private int year;

  /**
   * 3-argument constructor initializing all the fields
   * @param day the day of the date as int
   * @param month the month of the date as int
   * @param year the year of the date as int
   *
   */
  public MyDate(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Gets the day of the date
   * @return day of the date as int
   */
  public int getDay(){
    return day;
  }

  /**
   * Gets the month of the date
   * @return month of the date as int
   */
  public int getMonth(){
    return month;
  }

  /**
   * Gets the year of the date
   * @return year of the date as int
   */
  public int getYear(){
    return year;
  }

  /**
   * Shows date
   * @return the date in given format as String
   */
  public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Copies date so it can be used across classes
   * @return copy of the MyDate object date
   */
  public MyDate copy(){
  return new MyDate(day,month,year);
  }

  /**
   * Checks if the date is at least 15 years back from today's date
   * @return true if it is at least 15 years back from now, false otherwise
   */
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

  /**
   * Compares this MyDate object to another object
   * @param obj other  object we are comparing to
   * @return boolean true if those objects equal, false otherwise
   */
  public boolean equals(Object obj){
    if(!(obj instanceof MyDate)){
      return false;
    }
    MyDate temp = (MyDate) obj;
    return temp.getDay() == getDay() && temp.getMonth() == getMonth() && temp.getYear() == getYear();
  }

  /**
   * Detects if the year of the MyDate object is leap or not
   * @return boolean true if the year is leap, false otherwise
   */
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

  /**
   * Detects how many days there should be in the month of this MyDate object
   * @return int of days that are in this month
   */
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

  /**
   * Checks if the date of the MyDate object exists in the calendar, the date makes sense
   * @return boolean true if it is valid, false otherwise
   */
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

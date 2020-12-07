/**
 * A class representing the Date that can be used in multiple other classes.
 * @author Bartosz Ochedzan
 * @version 1.0
 */
public class MyDate
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
}

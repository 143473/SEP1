import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class responsible for reading and writing to text files
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class MyTextFileIO
{
    /**
     * Writes the given string to a file with the given file name
     * @param fileName the name and path of the file to append to
     * @param str the String to write to the file
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public void writeToFile(String fileName, String str) throws FileNotFoundException
    {
        write(fileName, str, false);
    }

    /**
     * Appends the given string to a file with the given file name
     * @param fileName the name and path of the file to append to
     * @param str the String to append to the file
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public void appendToFile(String fileName, String str) throws FileNotFoundException
    {
        write(fileName, str, true);
    }

    /**
     * Method being called by both writeToFile and appendToFile to write to the file
     * @param fileName the name and path of the file to write to
     * @param str  the String to write to the file
     * @param append the boolean indicating whether to append or not
     * @throws FileNotFoundException  if the file with fileName is not found
     */
    private void write(String fileName, String str, boolean append) throws FileNotFoundException
    {
        PrintWriter writeToFile = null;

        try
        {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);
            writeToFile.println(str);
        }
        finally
        {
            if (writeToFile != null)
            {
                writeToFile.close();
            }
        }
    }

    /**
     * Writes the given string array to a file with the given file name
     * @param fileName the name and path of the file to append to
     * @param strs the String array to write to the file
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public void writeToFile(String fileName, String[] strs) throws FileNotFoundException
    {
        write(fileName, strs, false);
    }

    /**
     * Appends the given string array to a file with the given file name
     * @param fileName the name and path of the file to append to
     * @param strs the String array to append to the file
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public void appendToFile(String fileName, String[] strs) throws FileNotFoundException
    {
        write(fileName, strs, true);
    }

    /**
     * Method being called by both writeToFile and appendToFile to write to the file
     * @param fileName the name and path of the file to write to
     * @param strs  the String array to write to the file
     * @param append the boolean indicating whether to append or not
     * @throws FileNotFoundException if the file with fileName is not found
     */
    private void write(String fileName, String[] strs, boolean append) throws FileNotFoundException
    {
        PrintWriter writeToFile = null;

        try
        {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);

            for (int i = 0; i < strs.length; i++)
            {
                writeToFile.println(strs[i]);
            }
        }
        finally
        {
            if (writeToFile != null)
            {
                writeToFile.close();
            }
        }
    }

    /**
     * Creates a new folder
     * @param fileName the name and path of the file to create
     */
    public void createANewFolder(String fileName){

    }

    /**
     * Reads the first line from the file with the given file name and returns it as a String
     * @param fileName fileName the name and path of the file to read from
     * @return first line from the file as String
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public String readStringFromFile(String fileName) throws FileNotFoundException
    {
        Scanner readFromFile = null;
        String str = "";

        try
        {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);
            str = readFromFile.nextLine();
        }
        finally
        {
            if (readFromFile != null)
            {
                readFromFile.close();
            }
        }
        return str;
    }



    /**
     * Reads all lines from the file with the given file name and returns it as a String[]
     * @param fileName  fileName the name and path of the file to read from
     * @return all lines from the file as String array
     * @throws FileNotFoundException if the file with fileName is not found
     */
    public String[] readArrayFromFile(String fileName) throws FileNotFoundException
    {
        Scanner readFromFile = null;
        ArrayList<String> strs = new ArrayList<String>();

        try
        {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);

            while (readFromFile.hasNext())
            {
                strs.add(readFromFile.nextLine());
            }
        }
        finally
        {
            if (readFromFile != null)
            {
                readFromFile.close();
            }
        }

        String[] strsArray = new String[strs.size()];
        return strs.toArray(strsArray);
    }
}

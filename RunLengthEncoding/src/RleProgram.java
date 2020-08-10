import java.util.ArrayList;
import java.util.Scanner;

public class RleProgram
{

    /**
     * Method 1 that translates data (RLE or raw) to a hexadecimal string
     * @param data RLE or raw data to be converted
     * @return translated hexadecimal string
     */
    public static String toHexString(byte[] data)
    {
        //Modifiable String
        StringBuilder hexString = new StringBuilder();

        int i = 0;
        int length = data.length;

        while(i < length)
        {
            //Convert data to hexadecimal string
            byte pixel = data[i];
            hexString.append(Integer.toHexString(pixel));
            i++;
        }

        return hexString.toString();
    }

    /**
     * Method 2 that returns number of runs of data in an image data set
     * @param flatData unencoded data
     * @return number of runs of data
     */
    //I used arrayLists instead of this method
    public static int countRuns(byte[] flatData)
    {
        int numRuns = 1;
        int numCounts = 1;
        int i = 0;
        int length = flatData.length;

        while(i < length - 1)
        {
            /* Same Value = Same Run
            numCounts increases EXCEPT last value
             */
            if(flatData[i] == flatData[i + 1] && i + 2 != length)
            {
                numCounts++;
            }

            /* Different Value = Different Run
            numRuns increases EXCEPT last value
            numCounts resets
             */
            else
            {
                //**No Run longer than 15 pixels
                numRuns = (int) (numRuns + Math.ceil(numCounts/15.0));
                numCounts = 1;

                //numRuns does not increase for last value
                if(i + 2 == length && flatData[i] == flatData[i+1])
                {
                    numRuns--;
                }
            }

            i++;

        }

        return numRuns;
    }

    /**
     * Method 3 that returns encoding (in RLE) of the raw data passed in
     * Used to generate RLE representation of a data
     * @param flatData unencoded data
     * @return encoding in RLE
     */
    public static byte[] encodeRle(byte[] flatData)
    {
        ArrayList<Byte> arr = new ArrayList<>();
        byte numCounts = 0;
        int i = 0;
        int length = flatData.length;

        while(i < length - 1)
        {
            byte numRuns = 0;

            //Same Value = Same Run
            //numCounts increases
            if(flatData[i] == flatData[i + 1])
            {
                numCounts++;
            }
            //Different Value = Different Run
            else
            {
                arr.add(numCounts);
                arr.add(numRuns);
                numCounts = 1;
            }

            //Last Run
            if(i == length - 2)
            {
                numRuns = flatData[length - 1];
                arr.add(numCounts);
                arr.add(numRuns);
            }

            i++;
        }

        int size = arr.size();

        //No Run or Count longer than 15 pixels
        while(i < size)
        {
            while(arr.get(i) < 15)
            {
                byte temp = (byte) (arr.get(i) - 15);
                arr.set(i, (byte) 15);
                arr.add(i+2, temp);
                byte val = arr.get(i+1);
                arr.add(i+3, val);
            }

            i++;
        }

        byte[] rleData = new byte[size];
        i = 0;

        while(i < size)
        {
            rleData[i] = arr.get(i);
            i++;
        }

        return rleData;

    }
    /**
     * Method 4 that returns decompressed size RLE data (Inverse of Method 2)
     * Used to generate flat data from RLE encoding
     * @param rleData run-length encoded data
     * @return decompressed size RLE data
     */
    //What was the point of this method?
    public static int getDecodedLength(byte[] rleData)
    {
        int decodedLength = 0;
        int i = 0;
        int length = rleData.length;

        while(i < length)
        {
            decodedLength = decodedLength + rleData[i];
            i = i + 2;
        }

        return decodedLength;
    }

    /**
     * Method 5 that returns the decoded data set from RLE encoded data (Inverse of Method 3)
     * Decompresses RLE data for use
     * @param rleData run-length encoded data
     * @return decoded data
     */
    public static byte[] decodeRle(byte[] rleData)
    {
        ArrayList<Byte> arr = new ArrayList<>();

        int i = 0;
        int length = rleData.length;

        while(i < length)
        {
            if(i % 2 == 0)
            {
                //Adds the value to the arrayList
                for(int j = 0; j < rleData[i]; j++)
                {
                    arr.add(rleData[i+1]);
                }
            }

            i++;
        }

        //Convert arrayList to array
        int arrLength = arr.size();
        byte[] decodedData = new byte[arrLength];
        i = 0;

        while(i < arrLength)
        {
            decodedData[i] = arr.get(i);
            i++;
        }

        return decodedData;
    }

    /**
     * Method 6 that converts a string in hexadecimal format into byte data (Inverse of Method 1)
     * @param dataString hexadecimal string
     * @return translated byte string
     */
    public static byte[] stringToData(String dataString)
    {
        int size = dataString.length();
        byte[] arr = new byte[size];
        int i = 0;

        //Converts hexadecimal
        while(i < size)
        {
            String hex = dataString.substring(i, i+1);
            arr[i] = Byte.parseByte(hex, 16);

            i++;
        }

        //Checks for 0s
        int numZeroes = 0;
        int arrLength = arr.length;
        i = 0;

        while(i >= 0)
        {
            if(arr[i] == 0)
            {
                numZeroes++;
            }
            else
            {
                break;
            }

            i--;
        }

        //Removes leftover 0s
        byte[] byteData = new byte[arrLength - numZeroes];
        if (arrLength - numZeroes >= 0)
        {
            System.arraycopy(arr, 0, byteData, 0, arrLength - numZeroes);
        }

        return byteData;

    }

    /**
     * Method 7 that converts RLE data into a human-readable representation
     * For each run in order, display run length in decimal, run value in hexadecimal, with delimiter in between
     * @param rleData run-length encoded data
     * @return human-readable representation of RLE data
     */
    public static String toRleString(byte[] rleData)
    {
        StringBuilder humanData = new StringBuilder();
        int i = 0;
        int length = rleData.length;
        String hex;

        while(i < length - 2)
        {
            hex = Integer.toHexString(rleData[i + 1]);
            humanData.append(rleData[i]);
            humanData.append(hex);
            humanData.append(":");

            i = i + 2;
        }

        hex = Integer.toHexString(rleData[i + 1]);

        humanData.append(rleData[i]);
        humanData.append(hex);

        return humanData.toString();

    }

    /**
     * Method 8 that converts a string in human-readable RLE format into RLE byte data (Inverse of Method 7)
     * @param rleString human-readable RLE format string
     * @return run-length encoded data
     */
    public static byte[] stringToRle(String rleString)
    {
        int colIndex = -1;
        int rleIndex = 0;
        byte numRepeats;
        char charRepeat;

        int rleLength = rleString.length();
        int length = rleLength - rleString.replace(":", "").length();
        length = length *2 + 2;
        byte[] rleData = new byte[length];

        int i = 0;
        while(i < rleLength)
        {
            colIndex = rleString.indexOf(":", colIndex + 1);
            if(colIndex == -1)
            {
                colIndex = rleLength;
            }

            String subStr = rleString.substring(i, colIndex);

            numRepeats = Byte.parseByte(subStr.substring(0, subStr.length() - 1));
            charRepeat = subStr.charAt(subStr.length() - 1);

            rleData[rleIndex] = numRepeats;
            rleData[rleIndex + 1] = Byte.parseByte(String.valueOf(charRepeat), 16);

            rleIndex = rleIndex + 2;
            i = colIndex + 1;
        }

        return rleData;
    }

    public static String numConvert(byte[] rleData, int i)
    {
        String value;
        int rleValue = rleData[i];
        if(rleValue == 10)
        {
            value = "a";
        }

        else if(rleValue == 11)
        {
            value = "b";
        }

        else if(rleValue == 12)
        {
            value = "c";
        }

        else if(rleValue == 13)
        {
            value = "d";
        }

        else if(rleValue == 14)
        {
            value = "e";
        }

        else if(rleValue == 15)
        {
            value = "f";
        }
        else
        {
            value = String.valueOf(rleValue);
        }

        return value;
    }

    public static byte charConvert(String dataString, int i)
    {
        byte value;
        dataString = dataString.toLowerCase();
        char currChar = dataString.charAt(i);
        if (currChar == 'a')
        {
            value = 10;
        }
        else if (currChar == 'b')
        {
            value = 11;
        }
        else if (currChar == 'c')
        {
            value = 12;
        }
        else if (currChar == 'd')
        {
            value = 13;
        }
        else if (currChar == 'e')
        {
            value = 14;
        }
        else if (currChar == 'f')
        {
            value = 15;
        }
        else
        {
            value = (byte) (currChar - 48);
        }

        return value;

    }


    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        String option;

        //Initialized Variables
        byte[] fileData = null;
        byte[] rleData = {0};
        String fileName;
        String rleString;


        System.out.println("Welcome to the RLE image encoder!");
        System.out.println("\nDisplaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);

        boolean continueApplication = true;
        while(continueApplication)
        {
            System.out.println("\nRLE Menu");
            System.out.println("--------");
            System.out.println("0. Exit");
            System.out.println("1. Load File");
            System.out.println("2. Load Test Image");
            System.out.println("3. Read RLE String");
            System.out.println("4. Read RLE Hex String");
            System.out.println("5. Read Data Hex String");
            System.out.println("6. Display Image");
            System.out.println("7. Display RLE String");
            System.out.println("8. Display Hex RLE Data");
            System.out.println("9. Display Hex Flat Data");
            System.out.print("\nSelect a Menu Option: ");
            option = input.next();


            //0 Exit
            if(option.equals("0"))
            {
                continueApplication = false;
            }

            //1 Load File
            else if(option.equals("1"))
            {
                System.out.print("Enter name of file to load: ");
                fileName = input.next();
                fileData = ConsoleGfx.loadFile(fileName);
                rleData = encodeRle(fileData);
            }

            //2 Load Test Image
            else if(option.equals("2"))
            {
                fileData = ConsoleGfx.testImage;
                rleData = encodeRle(fileData);
                System.out.println("Test image data loaded.");
            }

            //3 Read RLE String
            else if(option.equals("3"))
            {
                //Reads RLE data from the user in decimal notation with delimiters
                System.out.print("Enter an RLE string to be decoded: ");
                rleString = input.next();
                rleData = stringToRle(rleString);
            }

            //4 Read RLE Hex String
            else if(option.equals("4"))
            {
                //Reads RLE data from the user in hexadecimal notation without delimiters
                System.out.print("Enter the hex string holding RLE data: ");
                rleString = input.next();
                rleData = stringToData(rleString);
            }

            //5 Read Data Hex String
            else if(option.equals("5"))
            {
                //Reads raw (flat) data from the user in hexadecimal notation
                System.out.print("Enter the hex string holding flat data: ");
                String flatString = input.next();
                fileData = new byte[flatString.length()];

                int i = 0;
                while(i < rleData.length)
                {
                    byte numChange = charConvert(flatString, i);
                    fileData[i] = numChange;
                    i++;
                }

                rleData = encodeRle(fileData);
            }

            //6 Display Image
            else if(option.equals("6"))
            {
                System.out.println("Displaying image...");
                if (fileData == null)
                {
                    System.out.println("(no data)");
                }
                else
                {
                    ConsoleGfx.displayImage(fileData);
                }
            }

            //7 Display RLE String
            else if(option.equals("7"))
            {
                System.out.print("RLE representation: ");
                if (fileData == null)
                {
                    System.out.println("(no data)");
                }
                else
                {
                    String response = toRleString(rleData);
                    System.out.println(response);
                }
            }

            //8 Display RLE Hex Data
            else if(option.equals("8"))
            {
                System.out.print("RLE hex values: ");
                if (fileData == null)
                {
                    System.out.println("(no data)");
                }
                else
                {
                    String response = toHexString(rleData);
                    System.out.println(response);
                }
            }

            //9 Display Flat Hex Data
            else if(option.equals("9"))
            {
                System.out.print("Flat hex values: ");
                if (fileData == null)
                {
                    System.out.println("(no data)");
                }
                else
                {
                    StringBuilder response = new StringBuilder();
                    fileData = decodeRle(rleData);
                    int i = 0;
                    while(i < fileData.length)
                    {
                        String temp = numConvert(fileData, i);
                        response.append(temp);

                        i++;
                    }

                    System.out.println(response);
                }
            }

            //Invalid Input
            else
            {
                System.out.println("Error! Invalid input.");
            }

        }

    }

}

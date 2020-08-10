import java.util.Arrays;
import java.util.ArrayList;

public class Test
{
    public static void main(String[] args)
    {
        /**
         * NOTES:
         * DECODERLE IS NOT WORKING
         * THE REST ARE WORKING
         * MIX UP A FEW and ADD COMMENTS
         */

        String toHexStringTest = toHexString(new byte[] {3, 15, 6, 4}); //1
        int countRunsTest = countRuns(new byte[] {15, 15, 15, 4, 4, 4, 4, 4, 4,});//2
        byte[] encodeRleTest = encodeRle(new byte[] {15, 15, 15, 4, 4, 4, 4, 4, 4, });//3
        int getDecodedLengthTest = getDecodedLength(new byte[] {3, 15, 6, 4});//4
        byte[] decodeRleTest = decodeRle(new byte[] {3, 15, 6, 4});//5
        byte[] stringToDataTest = stringToData("3f64");//6
        String toRleStringTest = toRleString(new byte[] {15, 15, 6, 4});//7
        byte[] stringToRleTest = stringToRle("15f:64");//8

        System.out.println(toHexStringTest);
        System.out.println(countRunsTest);
        System.out.println(Arrays.toString(encodeRleTest));
        System.out.println(getDecodedLengthTest);
        System.out.println(Arrays.toString(decodeRleTest));
        System.out.println(Arrays.toString(stringToDataTest));
        System.out.println(toRleStringTest);
        System.out.println(Arrays.toString(stringToRleTest));

    }


    //DONE
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

    //DONE
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

    //DONE
    public static byte[] encodeRle(byte[] flatData)
    {
        //Double this result for length of encoded (RLE) byte array
        int size = countRuns(flatData) * 2;
        byte[] res = new byte[size];

        int i = 0;
        int length = flatData.length;

        int numCounts = 1;
        byte pixelRepeat = flatData[0];
        int resIndex = 0;

        while(i < length)
        {
            /*Same Value = Same Run
            numCounts increases
             */
            if(i < length - 1 && flatData[i] == flatData[i+1])
            {
                numCounts++;
            }
            //Different Value = Different Run
            else
            {
                //No Run or Count longer than 15 pixels
                int numRuns = (int) Math.ceil(numCounts/15.0);

                for(int j = 0; j < numRuns; j++)
                {
                    res[resIndex] = (byte) Math.min(15, numCounts);

                    res[resIndex + 1] = pixelRepeat;
                    resIndex = resIndex + 2;
                    numCounts = numCounts - 15;
                }

                numCounts = 1;
                if(i < length - 1)
                {
                    pixelRepeat = flatData[i + 1];
                }
            }

            i++;
        }

        return res;
    }

    //DONE
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

    //FIX ME
    public static byte[] decodeRle(byte[] rleData)
    {
        ArrayList<Byte> arr = new ArrayList<>();

        int length = rleData.length;


        for(int i = 0; i < length; i++)
        {

            if(i % 2 == 0)
            {
                for(int j = 0; j < rleData[i]; j++)
                {
                    arr.add(rleData[i+1]);
                }
            }
        }

        int arrLength = arr.size();

        byte[] res = new byte[arr.size()];
        for(int i = 0; i < arrLength; i++)
        {
            res[i] = arr.get(i);
        }

        return res;
    }

    //DONE
    public static byte[] stringToData(String dataString)
    {
        int size = dataString.length();
        byte[] arr = new byte[size];

        for(int i = 0; i < dataString.length(); i++)
        {
            String hex = dataString.substring(i, i+1);
            arr[i] = Byte.parseByte(hex, 16);
        }

        //Remove leftover 0s
        int empty = 0;
        for(int i = arr.length - 1; i >= 0; i--)
        {
            if(arr[i] == 0)
            {
                empty++;
            }
            else
            {
                break;
            }
        }

        byte[] res = new byte[arr.length - empty];
        for(int i = 0; i < arr.length - empty; i++)
        {
            res[i] = arr[i];
        }

        return res;

    }

    //DONE
    public static String toRleString(byte[] rleData)
{
    StringBuilder humanData = new StringBuilder();
    int i = 0;
    int length = rleData.length;
    String hex = "";

    while(i < length - 2)
    {
        hex = Integer.toHexString(rleData[i + 1]);
        humanData.append(rleData[i]);
        humanData.append(hex);
        humanData.append(":");

        i = i + 2;
    }

    hex = Integer.toHexString(rleData[i+1]);

    humanData.append(rleData[i]);
    humanData.append(hex);

    return humanData.toString();

}

    //DONE BUT MIX UP SEVERELY
    public static byte[] stringToRle(String rleString)
    {
        int colonIndex = -1;
        int resIndex = 0;
        byte repeats;
        char charRepeat;

        int length = rleString.length() - rleString.replace(":", "").length();
        length = length *2 + 2;
        byte[] res = new byte[length];

        int i = 0;
        while(i < rleString.length())
        {
            colonIndex = rleString.indexOf(":", colonIndex + 1);
            if(colonIndex == -1)
            {
                colonIndex = rleString.length();
            }

            String subStr = rleString.substring(i, colonIndex);

            repeats = Byte.parseByte(subStr.substring(0, subStr.length() - 1));
            charRepeat = subStr.charAt(subStr.length() - 1);

            res[resIndex] = repeats;
            res[resIndex + 1] = Byte.parseByte(String.valueOf(charRepeat), 16);

            resIndex = resIndex + 2;
            i = colonIndex + 1;
        }

        return res;
    }


}

package textconversions;

import stringmanip.StringManipulations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TextConversions {

    public static String convertStringToHex(String input) {
        final ArrayList<String> hexArrayList = new ArrayList<>();
        final char[] inputAsCharArray = input.toCharArray();

        for (char c : inputAsCharArray) {
			int hexInt = (int)c % 256;
            hexArrayList.add(StringManipulations.padLeftWithZeros(Integer.toHexString(hexInt), 2));
        }

        return String.join("", hexArrayList);
    }

    public static String convertHexToBin(String input) {
        final List<String> chunkedHexList = StringManipulations.chunkString(input, 1);
        final ArrayList<String> binArrayList = new ArrayList<>();

        for (String hex : chunkedHexList) {
            final String hexInBinary = StringManipulations.padLeftWithZeros(new BigInteger(hex, 16).toString(2), 4);

            if (hexInBinary.length() == 4) {
                binArrayList.add(hexInBinary);
            }
            else if (hexInBinary.length() > 4){
                binArrayList.add(hexInBinary.substring(hexInBinary.length()-5));
            }
        }

        return String.join("", binArrayList);
    }

    public static String convertBinToHex(String input) {
        final List<String> chunkedBinList = StringManipulations.chunkString(input, 4);
        final ArrayList<String> hexArrayList = new ArrayList<>();

        for (String bin : chunkedBinList) {
            hexArrayList.add((new BigInteger(bin, 2)).toString(16).toUpperCase());
        }

        return String.join("", hexArrayList);
    }

    public static String convertHexToString(String input) {
        final List<String> chunkedHexList = StringManipulations.chunkString(input, 2);
        final ArrayList<String> strArrayList = new ArrayList<>();

        for (String hex : chunkedHexList) {
            strArrayList.add(String.valueOf((char)(new BigInteger(hex, 16).intValue())));
        }

        return String.join("", strArrayList);
    }

    public static String convertStringToBin(String input) {
        final String stASCIIHex = convertStringToHex(input);
        final String stASCIIBin = convertHexToBin(stASCIIHex);

        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(stASCIIHex, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(stASCIIBin, 8)));

        return stASCIIBin;
    }

}

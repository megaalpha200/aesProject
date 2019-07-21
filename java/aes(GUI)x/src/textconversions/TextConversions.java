package textconversions;

import stringmanip.StringManipulations;
import org.apache.commons.codec.binary.Base64;

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

    public static String convertBase64StringToHex(String input) {
        final ArrayList<String> hexArrayList = new ArrayList<>();

        final byte[] encodedInputByteArray = input.getBytes();
        final byte[] decodedInputByteArray = Base64.decodeBase64(encodedInputByteArray);

        for (byte it : decodedInputByteArray) {
            int hexInt = it;
            if (hexInt < 0)
                hexInt += 256;

            final String hexStr = StringManipulations.padLeftWithZeros(Integer.toHexString(hexInt), 2);

            hexArrayList.add(hexStr);
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

    public static String convertHexToBase64String(String input) {
        final List<String> chunkedHexList = StringManipulations.chunkString(input, 2);
        final ArrayList<String> strArrayList = new ArrayList<>();
        final ArrayList<Byte> toBeEncodedByteArrayList = new ArrayList<>();

        for (String hex : chunkedHexList) {
            toBeEncodedByteArrayList.add((byte) Integer.parseInt((new BigInteger(hex, 16).toString())));
        }

        final byte[] toBeEncodedByteArray = new byte[toBeEncodedByteArrayList.size()];
        for (int i = 0; i < toBeEncodedByteArrayList.size(); i++) {
            toBeEncodedByteArray[i] = toBeEncodedByteArrayList.get(i);
        }

        final byte[] encodedByteArray = Base64.encodeBase64(toBeEncodedByteArray);

        for (byte it : encodedByteArray) {
            int intVal = it;
            final char charVal = (char)intVal;
            strArrayList.add("" + charVal);
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

    public static String convertBase64StringToBin(String input) {
        final String stASCIIHex = convertBase64StringToHex(input);
        final String stASCIIBin = convertHexToBin(stASCIIHex);

        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(stASCIIHex, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(stASCIIBin, 8)));

        return stASCIIBin;
    }
}

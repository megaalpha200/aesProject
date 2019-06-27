package hexmanip;

import stringmanip.StringManipulations;

import java.math.BigInteger;
import java.util.ArrayList;

public class HexManipulations {

    public static String hexor(String x1, String x2) {
        final ArrayList<String> resultList = new ArrayList<>();

        String firstVal = x1;
        String secondVal = x2;

        if (firstVal.length() > secondVal.length()) {
            secondVal = StringManipulations.padLeftWithZeros(secondVal, firstVal.length());
        }
        else if (secondVal.length() > firstVal.length()) {
            firstVal = StringManipulations.padLeftWithZeros(firstVal, secondVal.length());
        }

        for (int index = 0; index < firstVal.length(); index++) {
            resultList.add(Integer.toHexString(fromHexToInt(firstVal.charAt(index)) ^ fromHexToInt(secondVal.charAt(index))));
        }

        return String.join("", resultList);
    }

    public static String hexadd(String x1, String x2) {
        final String firstVal = x1;
        final String secondVal = x2;
        final int firstValInt = new BigInteger(firstVal, 16).intValue();
        final int secondValInt = new BigInteger(secondVal, 16).intValue();

        return Integer.toHexString(firstValInt + secondValInt);
    }

    public static String hexmod(String x1, String x2) {
        final String firstVal = x1;
        final String secondVal = x2;
        final int firstValInt = new BigInteger(firstVal, 16).intValue();
        final int secondValInt = new BigInteger(secondVal, 16).intValue();

        return Integer.toHexString(firstValInt % secondValInt);
    }

    public static int fromHexToInt(char x) {
        return new BigInteger(String.valueOf(x), 16).intValue();
    }

}

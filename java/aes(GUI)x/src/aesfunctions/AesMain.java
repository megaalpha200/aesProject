package aesfunctions;

import javafx.util.Pair;
import stringmanip.StringManipulations;
import textconversions.TextConversions;

import static aesfunctions.AesMainFunctions.debugOutput;

public class AesMain {

    public static String encryptDecryptInputTextPrep(String inputText, AesMainFunctions.CipherMode cipherMode, AesMainFunctions.TextMode textMode)  {
        String inputTextTypeStr = (cipherMode == AesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText";

        switch (cipherMode)
        {
            case ENCRYPT:
                debugOutput.append("PlainText: " + inputText).append(System.lineSeparator());
                debugOutput.append("PlainText Hex and Binary Representations:").append(System.lineSeparator());
                break;
            case DECRYPT:
                debugOutput.append("CipherText: " + inputText).append(System.lineSeparator());
                debugOutput.append("CipherText Hex and Binary Representations:").append(System.lineSeparator());
                break;
        }

        String inputTextHex = "";
        String inputTextBin = "";

        switch (textMode)
        {
            case STRING:
                inputTextHex = TextConversions.convertStringToHex(inputText);

                inputTextBin = TextConversions.convertHexToBin(inputTextHex);
                debugOutput.append("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2))).append(System.lineSeparator());
                debugOutput.append("Binary: " + String.join(" ", StringManipulations.chunkString(inputTextBin, 8))).append(System.lineSeparator());
                break;
            case HEX:
                inputTextHex = inputText.replaceAll("\\s", "");

                inputTextBin = TextConversions.convertHexToBin(inputTextHex);
                debugOutput.append("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2))).append(System.lineSeparator());
                debugOutput.append("Binary: " + String.join(" ", StringManipulations.chunkString(inputTextBin, 8))).append(System.lineSeparator());
                break;
        }

        debugOutput.append(System.lineSeparator());

        return inputTextHex;
    }

    public static String keyTextPrep(String initialKey, AesMainFunctions.TextMode textMode) {
        debugOutput.append(System.lineSeparator());

        debugOutput.append("Initial Key: " + initialKey).append(System.lineSeparator());
        debugOutput.append("Initial Key Hex and Binary Representations:").append(System.lineSeparator());

        String initialKeyHex = "";
        String initialKeyBin = "";

        switch (textMode)
        {
            case STRING:
                initialKeyHex = TextConversions.convertStringToHex(initialKey);

                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex);
                debugOutput.append("Hex: " + String.join(" ", StringManipulations.chunkString(initialKeyHex, 2))).append(System.lineSeparator());
                debugOutput.append("Binary: " + String.join(" ", StringManipulations.chunkString(initialKeyBin, 8))).append(System.lineSeparator());
                break;
            case HEX:
                initialKeyHex = initialKey.replaceAll("\\s", "");

                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex);
                debugOutput.append("Hex: " + String.join(" ", StringManipulations.chunkString(initialKeyHex, 2))).append(System.lineSeparator());
                debugOutput.append("Binary: " + String.join(" ", StringManipulations.chunkString(initialKeyBin, 8))).append(System.lineSeparator());
                break;
        }

        debugOutput.append(System.lineSeparator());

        return initialKeyHex;
    }

    public static Pair<String, String> encrypt(String plainTextHex, String initialKeyHex) {
        final String cipherTextHex = AesMainFunctions.aesEncrypt(plainTextHex, initialKeyHex);
        final String cipherTextStr = TextConversions.convertHexToString(cipherTextHex);

        final String chunkedCipherTextHexStr = String.join(" ", StringManipulations.chunkString(cipherTextHex, 2));
        
        debugOutput.append("Final CipherText (Hex): " + chunkedCipherTextHexStr).append(System.lineSeparator());
        debugOutput.append("Final CipherText (String): " + cipherTextStr).append(System.lineSeparator());
        
        return new Pair<>(chunkedCipherTextHexStr, cipherTextStr);
    }

    public static Pair<String, String> decrypt(String cipherTextHex, String initialKeyHex) {
        final String plainTextHex = AesMainFunctions.aesDecrypt(cipherTextHex, initialKeyHex);
        final String plainTextStr = TextConversions.convertHexToString(plainTextHex);

        final String chunkedPlainTextHexStr = String.join(" ", StringManipulations.chunkString(plainTextHex, 2));

        debugOutput.append("Final PlainText (Hex): " + chunkedPlainTextHexStr).append(System.lineSeparator());
        debugOutput.append("Final PlainText (String): " + plainTextStr).append(System.lineSeparator());

        return new Pair<>(chunkedPlainTextHexStr, plainTextStr);
    }

    public static class WrongMenuChoiceException extends Exception {
        @Override
        public String toString() {
            return this.getClass().getCanonicalName() + ": Invalid Choice!";
        }
    }

}

import javafx.util.Pair;
import stringmanip.StringManipulations;
import textconversions.TextConversions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        encryptDecryptMenu();
    }

    public static void encryptDecryptMenu() {
        AesMainFunctions.CipherMode cipherMode = null;
        String inputText = null;
        String inputKey = null;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        do {
            try {
                if (cipherMode == null) {
                    System.out.print(System.lineSeparator());
                    System.out.println("AES Encryptor/Decryptor");
                    System.out.println("Using Java/JVM");
                    System.out.println("Created by: Jose A. Alvarado");
                    System.out.println("Copyright J.A.A. Productions 2019");

                    System.out.println();

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. Encrypt");
                    System.out.println("\t2. Decrypt");
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");

                    switch (Integer.parseInt(bufferedReader.readLine())) {
                        case 1:
                            cipherMode = AesMainFunctions.CipherMode.ENCRYPT;
                            break;
                        case 2:
                            cipherMode = AesMainFunctions.CipherMode.DECRYPT;
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            throw new WrongMenuChoiceException();
                    }
                }

                if (inputText == null) {
                    System.out.print(System.lineSeparator());

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. " + cipherMode.toString() + " String " + ((cipherMode == AesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t2. " + cipherMode.toString() + " Hexadecimal " + ((cipherMode == AesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");
                    int menuOption = Integer.parseInt(bufferedReader.readLine());

                    switch (menuOption) {
                        case 1:
                            inputText = encryptDecryptInputTextPrep(cipherMode, AesMainFunctions.TextMode.STRING);
                            break;
                        case 2:
                            inputText = encryptDecryptInputTextPrep(cipherMode, AesMainFunctions.TextMode.HEX);
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            inputText = null;
                    }

                    if (inputText == null)
                        throw new WrongMenuChoiceException();

                    System.out.print(System.lineSeparator());

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. Use String Key");
                    System.out.println("\t2. Use Hexadecimal Key");
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");
                    menuOption = Integer.parseInt(bufferedReader.readLine());

                    switch (menuOption) {
                        case 1:
                            inputKey = keyTextPrep(AesMainFunctions.TextMode.STRING);
                            break;
                        case 2:
                            inputKey = keyTextPrep(AesMainFunctions.TextMode.HEX);
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            inputText = null;
                            inputKey = null;
                    }

                    if (inputKey == null)
                        throw new WrongMenuChoiceException();
                }

                System.out.print(System.lineSeparator());

                if (cipherMode == AesMainFunctions.CipherMode.ENCRYPT)
                    encrypt(inputText, inputKey);
                else if (cipherMode == AesMainFunctions.CipherMode.DECRYPT)
                    decrypt(inputText, inputKey);

                System.out.println();

                cipherMode = null;
                inputText = null;
                inputKey = null;
            }
            catch (WrongMenuChoiceException e) {
                System.out.println(e.toString());
            }
            catch (NumberFormatException e) {
                System.out.println(e.toString() + " - Please enter a number!");
            }
            catch (Exception e) {
                System.out.println(e.toString());
                cipherMode = null;
                inputText = null;
                inputKey = null;
            }
        } while(true);
    }

    public static String encryptDecryptInputTextPrep(AesMainFunctions.CipherMode cipherMode, AesMainFunctions.TextMode textMode) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputTextTypeStr = (cipherMode == AesMainFunctions.CipherMode.ENCRYPT) ? "PlainText" : "CipherText";

        System.out.print(System.lineSeparator());
        System.out.print("Please enter your " + inputTextTypeStr + " as a " + textMode.toString() + " Value: ");
        String inputText = bufferedReader.readLine();

        switch (cipherMode)
        {
            case ENCRYPT:
                System.out.println("PlainText: " + inputText);
                System.out.println("PlainText Hex and Binary Representations:");
                break;
            case DECRYPT:
                System.out.println("CipherText: " + inputText);
                System.out.println("CipherText Hex and Binary Representations:");
                break;
        }

        String inputTextHex = "";
        String inputTextBin = "";

        switch (textMode)
        {
            case STRING:
                inputTextHex = TextConversions.convertStringToHex(inputText);

                inputTextBin = TextConversions.convertHexToBin(inputTextHex);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(inputTextBin, 8)));
                break;
            case HEX:
                inputTextHex = inputText.replaceAll("\\s", "");

                inputTextBin = TextConversions.convertHexToBin(inputTextHex);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(inputTextBin, 8)));
                break;
        }

        System.out.print(System.lineSeparator());

        return inputTextHex;
    }

    public static String keyTextPrep(AesMainFunctions.TextMode textMode) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter your key as a " + textMode.toString() + " Value: ");
        String initialKey = bufferedReader.readLine();

        System.out.print(System.lineSeparator());

        System.out.println("Initial Key: " + initialKey);
        System.out.println("Initial Key Hex and Binary Representations:");

        String initialKeyHex = "";
        String initialKeyBin = "";

        switch (textMode)
        {
            case STRING:
                initialKeyHex = TextConversions.convertStringToHex(initialKey);

                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(initialKeyHex, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(initialKeyBin, 8)));
                break;
            case HEX:
                initialKeyHex = initialKey.replaceAll("\\s", "");

                initialKeyBin = TextConversions.convertHexToBin(initialKeyHex);
                System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(initialKeyHex, 2)));
                System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(initialKeyBin, 8)));
                break;
        }

        System.out.print(System.lineSeparator());

        return initialKeyHex;
    }

    public static void encrypt(String plainTextHex, String initialKeyHex) {
        final String cipherTextHex = AesMainFunctions.aesEncrypt(plainTextHex, initialKeyHex);
        final String cipherTextStr = TextConversions.convertHexToString(cipherTextHex);

        System.out.println("Final CipherText (Hex): " + String.join(" ", StringManipulations.chunkString(cipherTextHex, 2)));
        System.out.println("Final CipherText (String): " + cipherTextStr);
    }

    public static void decrypt(String cipherTextHex, String initialKeyHex) {
        final String plainTextHex = AesMainFunctions.aesDecrypt(cipherTextHex, initialKeyHex);
        final String plainTextStr = TextConversions.convertHexToString(plainTextHex);

        System.out.println("Final PlainText (Hex): " + String.join(" ", StringManipulations.chunkString(plainTextHex, 2)));
        System.out.println("Final PlainText (String): " + plainTextStr);
    }

    public static class WrongMenuChoiceException extends Exception {
        @Override
        public String toString() {
            return this.getClass().getCanonicalName() + ": Invalid Choice!";
        }
    }

}

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
        AesMainFunctions.Mode mode = null;
        Pair<String, String> inputTextAndKeyPair = null;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        do {
            try {
                if (mode == null) {
                    System.out.print(System.lineSeparator());
                    System.out.println("AES Encryptor/Decryptor");
                    System.out.println("Using Kotlin/JVM");
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
                            mode = AesMainFunctions.Mode.ENCRYPT;
                            break;
                        case 2:
                            mode = AesMainFunctions.Mode.DECRYPT;
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            throw new WrongMenuChoiceException();
                    }
                }

                if (inputTextAndKeyPair == null) {
                    System.out.print(System.lineSeparator());

                    System.out.println("Please choose an option: ");
                    System.out.println("\t1. " + mode.toString() + " String " + ((mode == AesMainFunctions.Mode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t1. " + mode.toString() + " Hexadecimal " + ((mode == AesMainFunctions.Mode.ENCRYPT) ? "PlainText" : "CipherText"));
                    System.out.println("\t3. Quit");
                    System.out.print("Choice: ");
                    final int menuOption = Integer.parseInt(bufferedReader.readLine());

                    switch (menuOption) {
                        case 1:
                            inputTextAndKeyPair = encryptDecryptStringInputTextPrep(mode);
                            break;
                        case 2:
                            inputTextAndKeyPair = encryptDecryptHexInputTextPrep(mode);
                            break;
                        case 3:
                            System.out.println("Goodbye!");
                            return;
                        default:
                            inputTextAndKeyPair = null;
                    }

                    if (inputTextAndKeyPair == null)
                        throw new WrongMenuChoiceException();
                }

                System.out.print(System.lineSeparator());

                if (mode == AesMainFunctions.Mode.ENCRYPT)
                    encrypt(inputTextAndKeyPair.getKey(), inputTextAndKeyPair.getValue());
                else if (mode == AesMainFunctions.Mode.DECRYPT)
                    decrypt(inputTextAndKeyPair.getKey(), inputTextAndKeyPair.getValue());

                System.out.println();

                mode = null;
                inputTextAndKeyPair = null;
            }
            catch (WrongMenuChoiceException e) {
                System.out.println(e.toString());
            }
            catch (NumberFormatException e) {
                System.out.println(e.toString() + " - Please enter a number!");
            }
            catch (Exception e) {
                System.out.println(e.toString());
                mode = null;
                inputTextAndKeyPair = null;
            }
        } while(true);
    }

    public static Pair<String, String> encryptDecryptStringInputTextPrep(AesMainFunctions.Mode mode) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(System.lineSeparator());

        switch (mode) {
            case ENCRYPT:
                System.out.print("Please enter your plaintext as a String: ");
                break;
            case DECRYPT:
                System.out.print("Please enter your ciphertext as a String: ");
                break;
        }

        final String inputText = bufferedReader.readLine();

        System.out.println();

        System.out.print("Please enter your key as a String: ");
        final String initialKey = bufferedReader.readLine();

        System.out.print(System.lineSeparator());

        switch (mode) {
            case ENCRYPT:
                System.out.println("PlainText: " + inputText);
                System.out.println("PlainText Hex and Binary Representations:");
                break;
            case DECRYPT:
                System.out.println("CipherText: " + inputText);
                System.out.println("CipherText Hex and Binary Representations:");
                break;
        }
        final String inputTextHex = TextConversions.convertStringToHex(inputText);
        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(TextConversions.convertHexToBin(inputTextHex), 8)));

        System.out.print(System.lineSeparator());

        System.out.println("Initial Key: " + initialKey);
        System.out.println("Initial Key Hex and Binary Representations:");
        final String initialKeyHex = TextConversions.convertStringToHex(initialKey);
        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(initialKeyHex, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(TextConversions.convertHexToBin(initialKeyHex), 8)));

        System.out.print(System.lineSeparator());

        return new Pair<>(inputTextHex, initialKeyHex);
    }

    public static Pair<String, String> encryptDecryptHexInputTextPrep(AesMainFunctions.Mode mode) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(System.lineSeparator());

        switch (mode) {
            case ENCRYPT:
                System.out.print("Please enter your plaintext as a Hex Value: ");
                break;
            case DECRYPT:
                System.out.print("Please enter your ciphertext as a Hex Value: ");
                break;
        }

        final String inputText = bufferedReader.readLine().replaceAll("\\s", "");

        System.out.println();

        System.out.print("Please enter your key as a Hex Value: ");
        final String initialKey = bufferedReader.readLine().replaceAll("\\s", "");

        System.out.print(System.lineSeparator());

        switch (mode) {
            case ENCRYPT:
                System.out.println("PlainText: " + inputText);
                System.out.println("PlainText Hex and Binary Representations:");
                break;
            case DECRYPT:
                System.out.println("CipherText: " + inputText);
                System.out.println("CipherText Hex and Binary Representations:");
                break;
        }
        final String inputTextHex = inputText;
        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(inputTextHex, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(TextConversions.convertHexToBin(inputTextHex), 8)));

        System.out.print(System.lineSeparator());

        System.out.println("Initial Key: " + initialKey);
        System.out.println("Initial Key Hex and Binary Representations:");
        final String initialKeyHex = initialKey;
        System.out.println("Hex: " + String.join(" ", StringManipulations.chunkString(initialKey, 2)));
        System.out.println("Binary: " + String.join(" ", StringManipulations.chunkString(TextConversions.convertHexToBin(initialKeyHex), 8)));

        System.out.print(System.lineSeparator());

        return new Pair<>(inputTextHex, initialKeyHex);
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

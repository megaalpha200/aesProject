function [ inputTextHex, dispStr ] = encryptDecryptInputTextPrep( inputText, cipherMode,  textMode)
%encryptDecryptInputTextPrep Prepares input text for the AES
%encryption/decryption process by converting it into hexadecimal.
%   The function takes a 1xN string or hex char vector as the input text,
%   a cipher mode value and a text mode value. It prepares input text for 
%   the AES encryption/decryption process. It returns the prepared input
%   text as a 1xM hex char vector.

    inputTextHex = '';    
    dispStr = '';
    
    convertedTextDispStr = '';
    if (cipherMode == CipherMode.ENCRYPT); inputTextType = 'PlainText'; else inputTextType = 'CipherText'; end;
    dispStr = horzcat(dispStr, sprintf('%s\n', [inputTextType ': ' inputText]));
    dispStr = horzcat(dispStr, sprintf('%s\n', [inputTextType ' Hex and Binary Representations:']));
        
    if cipherMode == CipherMode.ENCRYPT && textMode == TextMode.STRING
        inputTextHex = convertStringToHex(inputText);
        inputTextBin = convertHexToBin(inputTextHex);
        
        chunkedInputTextHex = splitAndJoinVectorToString(inputTextHex, 2, ' ');
        chunkedInputTextBin = splitAndJoinVectorToString(inputTextBin, 8, ' ');
        convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Hex: ' chunkedInputTextHex]));
        convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Binary: ' chunkedInputTextBin]));
    elseif cipherMode == CipherMode.DECRYPT && textMode == TextMode.STRING
        inputTextHex = convertBase64StringToHex(inputText);
        inputTextBin = convertHexToBin(inputTextHex);
        
        chunkedInputTextHex = splitAndJoinVectorToString(inputTextHex, 2, ' ');
        chunkedInputTextBin = splitAndJoinVectorToString(inputTextBin, 8, ' ');
        convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Hex: ' chunkedInputTextHex]));
        convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Binary: ' chunkedInputTextBin]));
    elseif textMode == TextMode.HEX
            inputTextHex = inputText(find(~isspace(inputText)));
            inputTextBin = convertHexToBin(inputTextHex);
            
            chunkedInputTextHex = splitAndJoinVectorToString(inputTextHex, 2, ' ');
            chunkedInputTextBin = splitAndJoinVectorToString(inputTextBin, 8, ' ');
            convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Hex: ' chunkedInputTextHex]));
            convertedTextDispStr = horzcat(convertedTextDispStr, sprintf('%s\n', ['Binary: ' chunkedInputTextBin]));
    else
        error('An unexpected error has occured!');
    end
    
    dispStr = horzcat(dispStr, convertedTextDispStr);
end


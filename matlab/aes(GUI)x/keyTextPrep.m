function [ initialKeyHex, dispStr ] = keyTextPrep( initialKey, textMode )
%keyTextPrep Prepares initial key for the AES
%encryption/decryption process by converting it into hexadecimal.
%   The function takes a 1xN string or hex char vector as the intial key,
%   and a text mode value. It prepares intial key for 
%   the AES encryption/decryption process. It returns the prepared intial
%   key as a 1xM hex char vector.

    dispStr = '';

    %dispStr = sprintf('\n%s\n', ['Initial Key: ' initialKey]);
    %dispStr = horzcat(dispStr, sprintf('%s\n', 'Initial Key Hex and Binary Representations:'));
    
    initialKeyHex = '';
    convertedKeyDispStr = '';
    switch textMode
        case TextMode.STRING
            initialKeyHex = convertStringToHex(initialKey);
            initialKeyBin = convertHexToBin(initialKeyHex);
            
            chunkedInitialKeyHex = splitAndJoinVectorToString(initialKeyHex, 2, ' ');
            chunkedInitialKeyBin = splitAndJoinVectorToString(initialKeyBin, 8, ' ');
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Hex: ' chunkedInitialKeyHex]));
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Binary: ' chunkedInitialKeyBin]));
        case TextMode.HEX
            initialKeyHex = initialKey(find(~isspace(initialKey)));
            initialKeyBin = convertHexToBin(initialKeyHex);
            
            chunkedInitialKeyHex = splitAndJoinVectorToString(initialKeyHex, 2, ' ');
            chunkedInitialKeyBin = splitAndJoinVectorToString(initialKeyBin, 8, ' ');
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Hex: ' chunkedInitialKeyHex]));
            convertedKeyDispStr = horzcat(convertedKeyDispStr, sprintf('%s\n', ['Binary: ' chunkedInitialKeyBin]));
        otherwise
            error('An unexpected error has occured!');
    end
    
    %dispStr = horzcat(dispStr, convertedKeyDispStr);
end


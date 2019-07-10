function [plainTextHexStr, plainTextStr, dispStr] = decrypt( cipherTextHex, initialKeyHex )
%encrypt Formal gateway to the DES decryption process.
%   This function is the formal gateway to the DES decryption process. It
%   takes a 1xN hex char vector as the ciphertext, a 1xM hex char
%   vector as the initial key. The function returns a 1xK chunked hex
%   char vector and a 1xL string char vector.

    [plainTextHex, dispStr] = aesDecrypt(cipherTextHex, initialKeyHex);
    plainTextStr = convertHexToString(plainTextHex);
    
    plainTextHexStr = splitAndJoinVectorToString(plainTextHex, 2, ' ');
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final PlainText (Hex): ' plainTextHexStr]));
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final PlainText (String): ' plainTextStr]));
end


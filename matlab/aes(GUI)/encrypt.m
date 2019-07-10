function [cipherTextHexStr, cipherTextStr, dispStr] = encrypt( plainTextHex, initialKeyHex )
%encrypt Formal gateway to the DES encryption process.
%   This function is the formal gateway to the DES encryption process. It
%   takes a 1xN hex char vector as the plaintext, a 1xM hex char
%   vector as the initial key. The function returns a 1xK chunked hex
%   char vector and a 1xL Base64 string char vector.

    [cipherTextHex, dispStr] = aesEncrypt(plainTextHex, initialKeyHex);
    cipherTextStr = convertHexToBase64String(cipherTextHex);
    
    cipherTextHexStr = splitAndJoinVectorToString(cipherTextHex, 2, ' ');
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final CipherText (Hex): ' cipherTextHexStr]));
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Final CipherText (Base64 String): ' cipherTextStr]));
end


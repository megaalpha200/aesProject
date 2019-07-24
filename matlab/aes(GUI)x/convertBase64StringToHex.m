function finalHexStr = convertBase64StringToHex( input )
%convertStringToHex
%   Takes a 1xN Base64 string char vector and converts it into a 1xM hex 
%   char vector.
    
    base64 = org.apache.commons.codec.binary.Base64;
    
    splitInput = splitStringToRowVector(input, 1);
    inputDec = double(splitInput);
    uIntVec = uint8(inputDec);
    inputStr = char(mod(double(base64.decode(uIntVec)), 256));
    inputHex = dec2hex(inputStr);
    finalHexStr = joinVectorToString(inputHex, '');
end


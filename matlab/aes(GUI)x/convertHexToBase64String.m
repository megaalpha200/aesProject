function finalStr = convertHexToBase64String( input )
%convertHexToBase64String
%   Takes a char 1xN vector as a hex char vector and converts it into a 
%   1xM Base64 string char vector.
    
    base64 = org.apache.commons.codec.binary.Base64;
    
    splitInput = splitStringToRowVector(input, 2);
    inputDec = hex2dec(splitInput);
    uIntVec = uint8(inputDec);
    inputStr = char(base64.encode(uIntVec));
    finalStr = joinVectorToString(inputStr, '');
end

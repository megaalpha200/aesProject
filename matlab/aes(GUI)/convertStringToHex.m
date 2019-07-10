function finalHexStr = convertStringToHex( input )
%convertStringToHex
%   Takes a 1xN string char vector and converts it into a 1xM hex char
%   vector.
    
    splitInput = splitStringToRowVector(input, 1);
    inputDec = double(splitInput);
    inputHex = dec2hex(inputDec);
    finalHexStr = joinVectorToString(inputHex, '');
end

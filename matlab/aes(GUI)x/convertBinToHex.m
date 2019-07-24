function finalHexStr = convertBinToHex( input )
%convertHexToBin
%   Takes a char 1xN vector as a binary char vector and converts it into a 
%   1xM hex char vector.

    splitInput = splitStringToRowVector(input, 8);
    inputDec = bin2dec(splitInput);
    inputHex = padStringList(dec2hex(inputDec), '0', 2, 0);
    finalHexStr = joinVectorToString(inputHex, '');
end

function finalBinStr = convertHexToBin( input )
%convertHexToBin
%   Takes a char 1xN vector as a hex char vector and converts it into a 
%   1xM binary char vector.

    splitInput = splitStringToRowVector(input, 2);
    inputDec = hex2dec(splitInput);
    inputBin = padStringList(dec2bin(inputDec), '0', 8, 0);
    finalBinStr = joinVectorToString(inputBin, '');
end


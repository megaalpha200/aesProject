function finalStr = convertHexToString( input )
%convertHexToBin
%   Takes a char 1xN vector as a hex char vector and converts it into a 
%   1xM string char vector.
    
    splitInput = splitStringToRowVector(input, 2);
    inputDec = hex2dec(splitInput);
    inputStr = char(inputDec);
    finalStr = joinVectorToString(inputStr, '');
end


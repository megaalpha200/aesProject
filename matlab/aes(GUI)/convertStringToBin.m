function [finalBinStr, dispStr] = convertStringToBin( input )
%   Takes a char 1xN vector as a string char vector and converts it into a 
%   1xM bin char vector.

    stASCIIHex = convertStringToHex(input);
    stASCIIBin = convertHexToBin(stASCIIHex);

    dispStr = sprintf('%s\n', ['Hex: ' splitAndJoinVectorToString(stASCIIHex, 2, ' ')]);
    dispStr = horzcat(dispStr, sprintf('%s\n', ['Binary: ' splitAndJoinVectorToString(stASCIIBin, 8, ' ')]));
    finalBinStr = stASCIIBin;
end

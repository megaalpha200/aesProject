function [finalBinStr, dispStr] = convertBase64StringToBin( input )
%   Takes a char 1xN vector as a Base 64string char vector and converts it 
%   into a 1xM bin char vector.

    dispStr = '';

    stASCIIHex = convertBase64StringToHex(input);
    stASCIIBin = convertHexToBin(stASCIIHex);

    %dispStr = sprintf('%s\n', ['Hex: ' splitAndJoinVectorToString(stASCIIHex, 2, ' ')]);
    %dispStr = horzcat(dispStr, sprintf('%s\n', ['Binary: ' splitAndJoinVectorToString(stASCIIBin, 8, ' ')]));
    finalBinStr = stASCIIBin;
end


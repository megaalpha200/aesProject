function output = applySBox( input, sBox )
%applySBox Takes an input and applies a S-Box to it
%   Takes a 1x2 hex char vector and uses it to navigate a 16x2x16 hex char 
%   vector (S-Box). The result is a 1x2 hex char vector from the S-Box.
   
    NULL_CONSTANT = getNullConstant();
    [~, inputCSize] = size(input);
    
    if (inputCSize ~= 2)
        output = NULL_CONSTANT;
    else
        boxRow = hex2dec(input(1,1));
        boxCol = hex2dec(input(1,2));

        output = sBox(boxCol+1, :, boxRow+1);
    end
end


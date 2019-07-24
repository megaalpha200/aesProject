function rotatedStr = rotWordRight( word, offset )
%rotWordRight Shifts every group of 2 hex values to the right by the amount
%offset
%   Takes a 1x8 hex char vector and shifts every group of 2 hex values 
%   within the vector by the amount offset. If offset is greater than the 
%   input column * 2 size, the input is shifted by mod(input column size, 
%   offset). A 1x8 shifted hex char vector is returned.

    [~, inputCSize] = size(word);
    modOffsetVal = mod(offset*2, inputCSize);
    rotatedStr = '';
    
    rotatedStr = horzcat(rotatedStr, word(end - modOffsetVal+1:end));
    rotatedStr = horzcat(rotatedStr, word(1:end - modOffsetVal));
end


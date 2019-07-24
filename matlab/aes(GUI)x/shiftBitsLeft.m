function shiftedString = shiftBitsLeft( input, shiftBy )
%shiftBitsLeft Shifts bits to the left by the amount shiftBy
%   Takes a 1xN binary char vector and shifts the bits within the vector by
%   the amount shiftBy. If shiftBy is greater than the input column size,
%   the input is shifted by mod(input column size, shiftBy). A 1xN shifted 
% binary char vector is returned.

    [~, inputCSize] = size(input);
    modShiftVal = mod(shiftBy, inputCSize);
    shiftedString = '';
    
    shiftedString = horzcat(shiftedString, input(modShiftVal+1:end));
    
    for i = 1:modShiftVal
        shiftedString = horzcat(shiftedString, input(i));
    end
end


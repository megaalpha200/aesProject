function output = hexor( x1, x2 )
%hexmod Bitwise Logical OR on two hex char arrays
    output = '';
    
    firstVal = x1;
    secondVal = x2;
    [~, firstValCSize] = size(firstVal);
    [~, secondValCSize] = size(secondVal);
    
    if firstValCSize > secondValCSize
        secondVal = padString(secondVal, '0', firstValCSize, 0);
    elseif secondValCSize > firstValCSize
        firstVal = padString(firstVal, '0', secondValCSize, 0);
    end
    
    for i = 1:firstValCSize
        firstValTempChar = firstVal(1,i);
        secondValTempChar = secondVal(1,i);
        hexOrRes = bitxor(hex2dec(firstValTempChar), hex2dec(secondValTempChar));
        
        output = horzcat(output, dec2hex(hexOrRes));
    end
end




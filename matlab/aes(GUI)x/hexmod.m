function output = hexmod( x1, x2 )
%hexmod Modulus on two hex char arrays

    firstVal = x1;
    secondVal = x2;
    firstValInt = hex2dec(firstVal);
    secondValInt = hex2dec(secondVal);
    
    output = dec2hex(mod(firstValInt, secondValInt));
end


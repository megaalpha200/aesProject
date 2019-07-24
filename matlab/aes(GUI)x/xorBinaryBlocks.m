function result = xorBinaryBlocks( block1, block2 )
%xorBinaryBlocks
%   Takes an 1xN and a 1xM binary char vectors, pads them to produce 2 1xK 
%   binary char vectors, and performs an XOR operation on each bit to 
%   produce a 1xK XORed binary char vector.

    block1In = block1;
    block2In = block2;
    result = '';
    
    [~, block1InCSize] = size(block1In);
    [~, block2InCSize] = size(block2In);
    
    if block1InCSize > block2InCSize
        block2In = padString(block2In, '0', block1InCSize, 0);
    elseif block2InCSize > block1InCSize
        block1In = padString(block1In, '0', block2InCSize, 0);
    end
    
    for i = 1:block1InCSize
        block1Int = bin2dec(block1In(i));
        block2Int = bin2dec(block2In(i));
        xorRes = xor(block1Int, block2Int);
        
        result = horzcat(result, dec2bin(xorRes));
    end
end


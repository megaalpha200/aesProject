function subStr = subWord( word, useInverseSubBytes )
%subWord Applies the SubWord transformation in AES
%   Takes a 1x8 hex char vector as the word and the function applies the
%   AES SubWord transformation on it using the appropriate S-Boxes,
%   depending on whether encryption or decryption is currently the cipher
%   mode.

    SUB_BYTE_S_BOX = getSubBytesSBox();
    INV_SUB_BYTE_S_BOX = getInvSubBytesSBox();
    
    chunkedWord = splitStringToRowVector(word, 2);
    [chunkedWordRSize, ~] = size(chunkedWord);
    
    subStr = '';
    for i = 1:chunkedWordRSize
        if useInverseSubBytes
            subStr = horzcat(subStr, applySBox(chunkedWord(i,:), INV_SUB_BYTE_S_BOX));
        else
            subStr = horzcat(subStr, applySBox(chunkedWord(i,:), SUB_BYTE_S_BOX));
        end
    end
end


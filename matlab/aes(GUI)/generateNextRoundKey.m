function nextRoundKey = generateNextRoundKey( inputKey, roundConstant )
%generateNextRoundKey Generates the next round key based on the given
%input key.
%   The function takes a 1x32 hex char vector as the input key state and 
%   generates the next round key based on it. It also takes a 1x8 round
%   constant used to generate the tWord necessary to generate the first
%   word of the next round key. The function returns a 1x32 hex char vector
%   as the next round key.

    nextRoundKey = '';
    
    chunkedInputKey = splitStringToRowVector(inputKey, 8);
    tWord = generateTWord(chunkedInputKey(end,:), roundConstant);
    
    [chunkedInputKeyRSize, ~] = size(chunkedInputKey);
    
    lastWord = tWord;
    for word = 1:chunkedInputKeyRSize
        newWord = hexor(chunkedInputKey(word,:), lastWord);
        nextRoundKey = horzcat(nextRoundKey, newWord);
        lastWord = newWord;
    end
end


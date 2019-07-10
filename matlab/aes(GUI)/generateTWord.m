function tWord = generateTWord( lastWord, roundConstant )
%generateTWord Generates the tWord needed for key generation in AES
%   Takes a 1x8 hex char vector as the last word from the previous key
%   state and the tWord process is applied upon it to generate the first
%   word of the next key state. It also takes a 1x8 round constant that is
%   used during the tWord generation process. The function returns a 1x8
%   hex char vector representing the tWord.

    rotatedLastWordFromInput = rotWordLeft(lastWord, 1);
    subLastWordFromInput = subWord(rotatedLastWordFromInput, false);
    
    tWord = hexor(subLastWordFromInput, roundConstant);
end


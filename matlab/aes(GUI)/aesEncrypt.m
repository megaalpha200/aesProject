function [ finalCipherTextStr, dispStr ] = aesEncrypt( plaintextHex, initialKeyHex )
%aesEncrypt Formal gateway to the AES encryption process
%   takes a 1xN hex char vector as the plaintext and a 1xM hex char vector
%   as the initial key. The function divides the plaintext into appropriate
%   blocks and processes each block through the entire AES encryption
%   process. The function returns a 1xK hex char vector as the final
%   result.

    R_CONSTANTS = getRConstants();
    
    [~, plaintextHexCSize] = size(plaintextHex);
    maxPlainTextHexSize = ceil(plaintextHexCSize / 32) * 32;
	paddedPlaintextHex = padString(plaintextHex, '0', maxPlainTextHexSize, 1);
    plaintextBlocks = splitStringToRowVector(paddedPlaintextHex, 32);
    
    paddedInitialKey = padString(initialKeyHex, '0', 32, 1);
    finalCipherTextStr = '';
    dispStr = '';
    
    [plaintextBlocksRSize, ~] = size(plaintextBlocks);
    
    for blockIndex = 1:plaintextBlocksRSize
        roundKeys(1,:) = paddedInitialKey;
        
        initialRoundObject = RoundObject();
        initialRoundObject.isPreRound = true;
        initialRoundObject.preRoundState = preRoundFunction(plaintextBlocks(blockIndex,:), paddedInitialKey);
        roundStates(1,:) = initialRoundObject;
        
        for roundIndex = 2:11
            roundKeys(roundIndex,:) = generateNextRoundKey(roundKeys(roundIndex-1,:), R_CONSTANTS(roundIndex,:));
            dispStr = horzcat(dispStr, sprintf('%s\n', ['Round ' num2str(roundIndex-1) ' Key: ' roundKeys(roundIndex,:)]));
            
            tempRoundObject = RoundObject();
            if roundStates(roundIndex-1,:).isPreRound
                tempRoundObject.preRoundState = roundStates(roundIndex-1,:).preRoundState;
            else
                tempRoundObject.preRoundState = roundStates(roundIndex-1,:).addRoundKeyState;
            end
            
            tempRoundObject = roundFunction(CipherMode.ENCRYPT, tempRoundObject, roundKeys(roundIndex,:), roundIndex ~=11);
            roundStates(roundIndex,:) = tempRoundObject;
        end
        
        dispStr = horzcat(dispStr, sprintf('\n%s\n',['For Block ' num2str(blockIndex) '...']));
        dispStr = horzcat(dispStr, sprintf('%s\n',['Block State: ' splitAndJoinVectorToString(plaintextBlocks(blockIndex), 2, ' ')]));
        
        dispStr = horzcat(dispStr, displayRoundStates(roundStates(:,:), blockIndex, CipherMode.ENCRYPT));
        finalCipherTextStr = horzcat(finalCipherTextStr, roundStates(end,:).addRoundKeyState);
    end
end


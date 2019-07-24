function [ finalCipherTextStr, dispStr ] = aesDecrypt( ciphertextHex, initialKeyHex )
%aesDecrypt Formal gateway to the AES decryption process
%   takes a 1xN hex char vector as the ciphertext and a 1xM hex char vector
%   as the initial key. The function divides the ciphertext into appropriate
%   blocks and processes each block through the entire AES decryption
%   process. The function returns a 1xK hex char vector as the final
%   result.

    NULL_CONSTANT = getNullConstant();
    R_CONSTANTS = getRConstants();
    
    [~, ciphertextHexCSize] = size(ciphertextHex);
    maxCipherTextHexSize = ceil(ciphertextHexCSize / 32) * 32;
	paddedCiphertextHex = padString(ciphertextHex, '0', maxCipherTextHexSize, 1);
    ciphertextBlocks = splitStringToRowVector(paddedCiphertextHex, 32);
    
    paddedInitialKey = padString(initialKeyHex, '0', 32, 1);
    finalCipherTextStr = '';
    dispStr = '';
    
    [ciphertextBlocksRSize, ~] = size(ciphertextBlocks);
    
    for blockIndex = 1:ciphertextBlocksRSize
        for keyIndex = 1:10
            roundKeys(keyIndex,:) = padString(NULL_CONSTANT, '0', 32, 0);
        end
        roundKeys(11,:) = paddedInitialKey;
        
        for roundIndex = 10:-1:1
            roundKeys(roundIndex,:) = generateNextRoundKey(roundKeys(roundIndex+1,:), R_CONSTANTS(12-roundIndex,:));
        end
        
        initialRoundObject = RoundObject();
        initialRoundObject.isPreRound = true;
        initialRoundObject.preRoundState = preRoundFunction(ciphertextBlocks(blockIndex,:), roundKeys(1,:));
        roundStates(1,:) = initialRoundObject;
        
        for roundIndex = 2:11
            %dispStr = horzcat(dispStr, sprintf('%s\n', ['Round ' num2str(roundIndex-1) ' Key: ' roundKeys(roundIndex,:)]));
            
            tempRoundObject = RoundObject();
            if roundStates(roundIndex-1,:).isPreRound
                tempRoundObject.preRoundState = roundStates(roundIndex-1,:).preRoundState;
            else
                tempRoundObject.preRoundState = roundStates(roundIndex-1,:).mixColumnsState;
            end
            
            tempRoundObject = roundFunction(CipherMode.DECRYPT, tempRoundObject, roundKeys(roundIndex,:), roundIndex ~=11);
            roundStates(roundIndex,:) = tempRoundObject;
        end
        
        %dispStr = horzcat(dispStr, sprintf('\n%s\n',['For Block ' num2str(blockIndex) '...']));
        %dispStr = horzcat(dispStr, sprintf('%s\n',['Block State: ' splitAndJoinVectorToString(ciphertextBlocks(blockIndex), 2, ' ')]));
        
        %dispStr = horzcat(dispStr, displayRoundStates(roundStates(:,:), blockIndex, CipherMode.DECRYPT));
        finalCipherTextStr = horzcat(finalCipherTextStr, roundStates(end,:).addRoundKeyState);
    end
end


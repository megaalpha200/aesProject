function dispStr = displayRoundStates( roundStates, blockNum, mode )
%displayRoundStates Displays the contents of each round state for a given
%block.

    dispStr = '';
    [roundStatesRSize, ~] = size(roundStates);

    for roundNum = 1:roundStatesRSize
        roundObj = roundStates(roundNum,:);
        
        dispStr = horzcat(dispStr, sprintf('%s\n', ['For Block ' num2str(blockNum) ' Round ' num2str(roundNum) '...']));
        
        if roundObj.isPreRound
            dispStr = horzcat(dispStr, sprintf('%s\n', ['After Pre-Round: ' splitAndJoinVectorToString(roundObj.preRoundState, 2, ' ')]));
        else
            if mode == CipherMode.ENCRYPT
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After SubBytes: ' splitAndJoinVectorToString(roundObj.subBytesState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After ShiftRows: ' splitAndJoinVectorToString(roundObj.shiftRowsState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After MixColumns: ' splitAndJoinVectorToString(roundObj.mixColumnsState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After AddRoundKey: ' splitAndJoinVectorToString(roundObj.addRoundKeyState, 2, ' ')]));
            elseif mode == CipherMode.DECRYPT
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After InvShiftRows: ' splitAndJoinVectorToString(roundObj.shiftRowsState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After InvSubBytes: ' splitAndJoinVectorToString(roundObj.subBytesState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After AddRoundKey: ' splitAndJoinVectorToString(roundObj.addRoundKeyState, 2, ' ')]));
                dispStr = horzcat(dispStr, sprintf('%s\n', ['After InvMixColumns: ' splitAndJoinVectorToString(roundObj.mixColumnsState, 2, ' ')]));
            end   
        end
        
        dispStr = horzcat(dispStr, sprintf('\n\n'));
    end
end


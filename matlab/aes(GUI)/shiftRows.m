function resultState = shiftRows( state, isInverse )
%shiftRows Applies the ShiftRows transformation in AES to a
%a given state
%   Takes a 1x32 hex char vector as the state that the ShiftRows 
%   transformation will be applied upon. It also takes a boolean value used
%   to determine whether to apply the inverse ShiftRows transformation or
%   not. It returns a 1x32 hex char vector as the result state.

    postShiftRowsStateStr = '';
    
    preShiftRowsState = splitStringToRowVector(rowColOrientStateWords(state), 8);
    [preShiftRowsStateRSize, ~] = size(preShiftRowsState);
    
    for offset = 1:preShiftRowsStateRSize
        if isInverse
            postShiftRowsStateStr = horzcat(postShiftRowsStateStr, rotWordRight(preShiftRowsState(offset, :), offset-1));
        else
            postShiftRowsStateStr = horzcat(postShiftRowsStateStr, rotWordLeft(preShiftRowsState(offset, :), offset-1));
        end
    end
    
    resultState = rowColOrientStateWords(postShiftRowsStateStr);
end


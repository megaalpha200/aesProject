function outRoundObj = roundFunction( mode, roundObj, roundKey, useMixCols )
%roundFunction AES round function
%   Takes a round object and generates all the necessary values for that
%   object (SubBytes state, ShiftRows state, MixColumns state, and
%   AddRoundKey state). The function also takes in a 1x32 hex char vector
%   as the current round key and a boolean value to determine whether or
%   not the current round needs to use the MixColumns transformation. All
%   state values will be found depending on the current cipher mode.

    MIX_COLS_CONSTANT_MATRIX = getMixColsConstantMatrix();
    INV_MIX_COLS_CONSTANT_MATRIX = getInvMixColsConstantMatrix();
    
    subBytesState = '';
    shiftRowState = '';
    
    if mode == CipherMode.ENCRYPT
        subBytesState = subWord(roundObj.preRoundState, false);
        shiftRowState = shiftRows(subBytesState, false);
        
        if useMixCols
            mixColumnsState = mixColumns(shiftRowState, MIX_COLS_CONSTANT_MATRIX);
            roundObj.mixColumnsState = mixColumnsState;
            roundObj.addRoundKeyState = hexor(mixColumnsState, roundKey);
        else
            roundObj.addRoundKeyState = hexor(shiftRowState, roundKey);
        end
    elseif mode == CipherMode.DECRYPT
        shiftRowState = shiftRows(roundObj.preRoundState, true);
        subBytesState = subWord(shiftRowState, true);
        
        if useMixCols
            addRoundKeyState = hexor(subBytesState, roundKey);
            roundObj.addRoundKeyState = addRoundKeyState;
            roundObj.mixColumnsState = mixColumns(addRoundKeyState, INV_MIX_COLS_CONSTANT_MATRIX);
            
        else
            roundObj.addRoundKeyState = hexor(subBytesState, roundKey);
        end
    end
    
    roundObj.subBytesState = subBytesState;
    roundObj.shiftRowsState = shiftRowState;
    
    outRoundObj = roundObj;
end


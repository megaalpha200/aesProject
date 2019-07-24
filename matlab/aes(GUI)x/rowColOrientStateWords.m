function rowOrientedStr = rowColOrientStateWords( state )
%rowColOrientStateWords Orients each word column as a row or vice versa
%   Takes a 1x32 hex char vector and orients each state column (word) into
%   row equilavents. Preperation for the ShiftRows AES transformation.

    rowOrientedStr = '';
    
    protoChunkedState = splitStringToRowVector(state, 8);
    [protoChunkedStateRSize, ~] = size(protoChunkedState);
    
    for i = 1:protoChunkedStateRSize
        chunkedWord = splitStringToRowVector(protoChunkedState(i,:), 2);
        chunkedState(:,:, i) = chunkedWord;
    end
    
    [chunkedStateCSize, ~, chunkedStateRSize] = size(chunkedState);
    
    for rowIndex = 1:chunkedStateRSize
        for colIndex = 1:chunkedStateCSize
            rowOrientedStr = horzcat(rowOrientedStr, chunkedState(rowIndex, :, colIndex));
        end
    end
end


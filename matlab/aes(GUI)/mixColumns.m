function resultState = mixColumns( state, mixConstant )
%mixColumns Applies the MixColumns transformation in AES to a
%a given state
%   Takes a 1x32 hex char vector as the state that the MixColumns 
%   transformation will be applied upon. It also takes a 4x2x4 hex char
%   vector as the mix constant that will be applied on each word within the
%   state. It returns a 1x32 hex char vector as the result state.

    resultState = '';
    
    chunkedState = splitStringToRowVector(state, 8);
    [chunkedStateRSize, ~] = size(chunkedState);
    
    for wordIndex = 1:chunkedStateRSize
        resultState = horzcat(resultState, applyConstantMixCols(chunkedState(wordIndex,:), mixConstant));
    end
end


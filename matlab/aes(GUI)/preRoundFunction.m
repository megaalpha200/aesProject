function preRoundResult = preRoundFunction( initialState, roundKey )
%preRoundFunction AES pre-round function
%   Takes a 1x32 hex char vector as the initial state and a 1x32 hex char
%   vector and XORs the two to get a 1x32 hex char vector result.

    preRoundResult = hexor(initialState, roundKey);
end


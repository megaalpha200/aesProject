function joinedStr = splitAndJoinVectorToString( input, splitBy, joinDelimiter )
%UNTITLED11 Summary of this function goes here
%   Takes a 1xN char vector and splits it into a M by splitBy vector.
%   Then, it takes the M by splitBy vector and combines all the rows into a
%   1xK char vector split by a given delimiter.

    splitVec = splitStringToRowVector(input, splitBy);
    joinedStr = joinVectorToString(splitVec, joinDelimiter);
end

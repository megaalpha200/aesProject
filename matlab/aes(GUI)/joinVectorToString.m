function outputStr = joinVectorToString( inputVec, delimiter )
%joinVectorToString
%   Takes a NxM char vector and combines all the rows into a 1xK char vector 

    [inputRSize, ~] = size(inputVec);

    outputStr = '';
    for i = 1:inputRSize
        if i == inputRSize
            outputStr = horzcat(outputStr, inputVec(i,:));
        else
            outputStr = horzcat(outputStr, inputVec(i,:), delimiter);
        end
    end
end

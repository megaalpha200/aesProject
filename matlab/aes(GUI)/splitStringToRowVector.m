function finalRowVec = splitStringToRowVector( input, splitBy )
%splitStringToRowVector
%   Takes a 1xN char vector and splits it into a M by splitBy vector.

    [~, inputCSize] = size(input);
    
    finalRowVec = '';
    for i = 1:splitBy:inputCSize 
        if (i + splitBy) > inputCSize
            finalRowVec = vertcat(finalRowVec, input(i:end));
        else
            finalRowVec = vertcat(finalRowVec, input(i:i+splitBy-1));
        end
    end
end


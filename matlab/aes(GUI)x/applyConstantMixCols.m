function resultWord = applyConstantMixCols( word, mixConstant )
%applyConstantMixCols Applies the MixColumns transformation in AES to each
%a given word from a state
%   Takes a 1x8 hex char vector as the word that the MixColumns 
%   transformation will be applied upon. It also takes a 4x2x4 hex char
%   vector as the mix constant that will be applied on the word. It returns
%   a 1x8 hex char vector as the result word.

    NULL_CONSTANT = getNullConstant();
    MIX_COLS_L_TABLE = getMixColsLTable();
    MIX_COLS_E_TABLE = getMixColsETable();

    resultWord = '';
    
    chunkedWord = splitStringToRowVector(word, 2);
    [mixConstantCSize, ~, mixConstantRSize] = size(mixConstant);
    
    for rowIndex = 1:mixConstantRSize
        listToXOR = '00';
        
        for constIndex = 1:mixConstantCSize
            constantLValue = applySBox(mixConstant(constIndex, :, rowIndex), MIX_COLS_L_TABLE);
            wordElementVal = applySBox(chunkedWord(constIndex, :), MIX_COLS_L_TABLE);
            
            if (strcmp(constantLValue, NULL_CONSTANT)) || (strcmp(wordElementVal, NULL_CONSTANT))
                listToXOR(constIndex, :) = '00';
            else
                addedLValues = padString(hexadd(constantLValue, wordElementVal), '0', 2, 0);
                paddedHexModRes = padString(hexmod(addedLValues, 'FF'), '0', 2, 0);
                listToXOR(constIndex, :) = applySBox(paddedHexModRes, MIX_COLS_E_TABLE);
            end
        end
        
        [listToXorRSize, ~] = size(listToXOR);
            
        finalXORedValue = '00';
        for valIndex = 1:listToXorRSize
            finalXORedValue = hexor(finalXORedValue, listToXOR(valIndex,:));
        end

        resultWord = horzcat(resultWord, finalXORedValue);
    end
end


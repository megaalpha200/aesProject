classdef RoundObject
    %RoundObject Object that holds the state values for each round
    
    
    properties
        preRoundState
        subBytesState
        shiftRowsState
        mixColumnsState
        addRoundKeyState
        isPreRound
    end
    
    methods
        function obj = RoundObject()
            obj.preRoundState = '';
            obj.subBytesState = '';
            obj.shiftRowsState = '';
            obj.mixColumnsState = '';
            obj.addRoundKeyState = '';
            obj.isPreRound = false;
        end
    end
    
end


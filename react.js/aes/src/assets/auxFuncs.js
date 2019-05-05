export const string_chop = (str, size, transformFunc = null) => {
    if (str == null) return [];
    str = String(str);
    size = ~~size;

    const choppedResult = size > 0 ? str.match(new RegExp('.{1,' + size + '}', 'g')) : [str];
    const choppedResultTransformed = [];

    if (transformFunc != null) {
      for(let i = 0; i < choppedResult.length; i++) {
        choppedResult[i] = transformFunc(choppedResult[i]);
      }
    }
    
    return choppedResult;
  }

  export const shiftBitsLeft = (input, shiftBy) => {
    const modShiftVal = shiftBy % (input.length);
    let shiftedString = "";
  
    shiftedString = shiftedString + input.substring(modShiftVal);
  
    for(let i = 0; i < modShiftVal; i++) {
        shiftedString = shiftedString + input[i];
    }
  
    return shiftedString;
  }
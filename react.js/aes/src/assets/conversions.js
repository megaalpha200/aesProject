import {string_chop} from './auxFuncs';

export const convertHexToBin = (input) => {
    const binArray = [];
  
    for(let i = 0; i < input.length; i++) {
      binArray.push(parseInt(input[i],16).toString(2).padStart(4, '0'));
    }
  
    return binArray.join("");
  }

  export const convertStringToHex = (input) => {
    const hexArray = [];
  
    for(let i = 0; i < input.length; i++) {
      hexArray.push(input.charCodeAt(i).toString(16));
    }
  
    return hexArray.join("");
}
  
export const convertBinToHex = (input) => {
    const chunkedBinArray = string_chop(input, 4);
    const hexArray = [];

    for (let i = 0; i < chunkedBinArray.length; i++) {
        hexArray.push(parseInt(chunkedBinArray[i], 2).toString(16).toUpperCase())
    }

    return hexArray.join("");
}

export const convertHexToString = (input) => {
    const chunkedHexArray = string_chop(input, 2);
    const strArray = [];

    for (let i = 0; i < chunkedHexArray.length; i++) {
        strArray.push(String.fromCharCode(parseInt(chunkedHexArray[i], 16)));
    }

    return strArray.join("");
}

export const convertStringToBin = (input) => {
    const strASCIIHex = convertStringToHex(input);
    const strASCIIBin = convertHexToBin(strASCIIHex);

    return strASCIIBin;
}
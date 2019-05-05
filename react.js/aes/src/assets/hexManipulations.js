export const hexor = (hex1, hex2) => {
    const resultArray = [];

    let firstVal = hex1;
    let secondVal = hex2;

    if (firstVal.length > secondVal.length) {
        secondVal = secondVal.padStart(firstVal.length, '0')
    }
    else if (secondVal.length > firstVal.length) {
        firstVal = firstVal.padStart(secondVal.length, '0')
    }

    for(let i = 0; i < firstVal.length; i++) {
        resultArray.push((parseInt(firstVal[i], 16) ^ parseInt(secondVal[i], 16)).toString(16));
    }

    return resultArray.join("");
}

export const hexadd = (hex1, hex2) => {
    const firstValInt = parseInt(hex1, 16);
    const secondValInt = parseInt(hex2, 16);

    return (firstValInt + secondValInt).toString(16);
}

export const hexmod = (hex1, hex2) => {
    const firstValInt = parseInt(hex1, 16);
    const secondValInt = parseInt(hex2, 16);

    return (firstValInt % secondValInt).toString(16);
}
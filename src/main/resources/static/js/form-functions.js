function goodUsername(inputHTML) {
  badArray = ["!","@","#","$","%","^","&","*","(",")"," ",".","]","[","{","}"];
  inputHTML = inputHTML.toString();
  inputHTMLArray = inputHTML.split("");
  for (let i = 0; i <= inputHTMLArray.length; i++) {
    if(badArray.indexOf(inputHTMLArray[i]) !== -1){
      return false;
    }
  }
  return true;
}

function goodEmail(inputHTML) {
  badArray = ["!","#","$","%","^","&","*","(",")"," ","]","[","{","}"];
  inputHTML = inputHTML.toString();
  inputHTMLArray = inputHTML.split("");
  for (let i = 0; i <= inputHTMLArray.length; i++) {
    if(badArray.indexOf(inputHTMLArray[i]) !== -1){
      return false;
    }
  }
  return true;
}

function goodPassword(inputHTML) {
  badArray = [" "];
  inputHTML = inputHTML.toString();
  inputHTMLArray = inputHTML.split("");
  for (let i = 0; i <= inputHTMLArray.length; i++) {
    if(badArray.indexOf(inputHTMLArray[i]) !== -1){
      return false;
    } else if(inputHTMLArray.length < 6){
      return false;
    }
  }
  return true;
}

function longPassword(inputHTML) {
  inputHTML = inputHTML.toString();
  return inputHTML.length >= 5;

}


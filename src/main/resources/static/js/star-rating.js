// // Is an object
// let rating = document.getElementsByClassName('rate');
//
// //The object's values:
//
//
// var rate1 = "1";
// var rate2 = "2";
// var rate3 = "3";
//
// for(let i = 0; i <=rating.length; i++){
//     // console.log(rating[i].innerHTML);
//     if(rating[i].innerHTML ===  rate1){
//         console.log("Its working");
//         $('.star1').css('color','yellow');
//         $('.star2').css('color','yellow');
//         $('.star3').css('color','yellow');
//         $('.star4').css('color','yellow');
//         $('.star5').css('color','yellow');
//
//
//     }
//     else if(rating[i].innerHTML === rate3){
//         console.log("For rate 3");
//         $('.star1').css('color','yellow');
//         $('.star2').css('color','yellow');
//         $('.star3').css('color','yellow');
//         break;
//     }
// }





// console.log(rating.length);
// console.log(rating[0].innerHTML);
// console.log(rating[1].innerHTML);
// console.log(typeof rating[1].innerHTML);


let reviewAvg = $('.rate').text();

const ratings ={
    reviews: reviewAvg
}


//total stars
const starsTotal = 5;

//run getRatings when DOM loads
document.addEventListener("DOMContentLoaded", getRatings);

//get ratings
function getRatings(){
    for(let rating in ratings){
        //get percentage
        const starPercentage = (ratings[rating] / starsTotal) * 100;

        //round to nearest 10
        const starPercentageRounded = (Math.round(starPercentage / 10) * 10);


        console.log(starPercentageRounded)

// set width of stars-inner to percentage
        document.querySelector(".${rating}.stars-inner").style.width = starPercentageRounded;

    }
}



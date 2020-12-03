//MAKE THE MAGIC HAPPEN
/*globals $: false
document: false*/

/* The function used for the random apple location in the tree crown*/
$(document).ready(function () {
    $(".apple").each(function () {
        var treeLeft = $("#tree").offset().left;
        var treeWidth = $("#tree").width();
        var treeHeight = $("#tree").height();
        var treeXRandLocation = Math.floor(Math.random() * (treeWidth - 150) + (treeLeft + 50));
        var treeYRandLocation = Math.floor(Math.random() * (treeHeight / 3)) + (treeHeight / 2);
        $(this).css({
            left: treeXRandLocation,
            bottom: treeYRandLocation
        });
    });
});

/* variable used in the next 3 functions */
var basketLeft = $("#basketfront").offset().left;

/* Function for each apple, responsible for shaking animation,
and also directing them to a random location in the basket */
$("#apple1").on("click", function () {
    for (var x = 1; x <= 3; x++) {
        $(this).animate({
            left: $("#apple1").offset().left - 4
        }, 20).animate({
            top: $("#apple1").offset().top - 4
        }, 10).animate({
            left: $("#apple1").offset().left
        }, 20).animate({
            top: $("#apple1").offset().top
        }, 10);
    }
    $(this).animate({
        top: $("#basketfront").offset().top,
        left: Math.floor(Math.random() * ($("#basketfront").width() - 50)) + basketLeft
    }, "slow", "linear");
});
$("#apple2").on("click", function () {
    for (var x = 1; x <= 3; x++) {
        $(this).animate({
            left: $("#apple2").offset().left - 4
        }, 20).animate({
            top: $("#apple2").offset().top - 4
        }, 10).animate({
            left: $("#apple2").offset().left
        }, 20).animate({
            top: $("#apple2").offset().top
        }, 10);
    }
    $(this).animate({
        top: $("#basketfront").offset().top,
        left: Math.floor(Math.random() * ($("#basketfront").width() - 50)) + basketLeft
    }, "slow", "linear");
});
$("#apple3").on("click", function () {
    for (var x = 1; x <= 3; x++) {
        $(this).animate({
            left: $("#apple3").offset().left - 4
        }, 20).animate({
            top: $("#apple3").offset().top - 4
        }, 10).animate({
            left: $("#apple3").offset().left
        }, 20).animate({
            top: $("#apple3").offset().top
        }, 10);
    }
    $(this).animate({
        top: $("#basketfront").offset().top,
        left: Math.floor(Math.random() * ($("#basketfront").width() - 50)) + basketLeft
    }, "slow", "linear");
});

/* function needed to bring the front of the basket upfront, otherwise the apples will be placed in front of the basket */

$("#basketfront").css({
    "z-index": "1"
});

/* function used to bring the apples in front of the basket */
$('.apple').parent().append($('.apple'));

/*globals $: false
   document: false
   window: false*/

//----------------WATERINGCAN-----------------
$("#wateringcan").on("click", function () {
    $("#wateringcan").toggleClass("rotate");
    $(".waterdrop1").animate({
            left: "+=1",
        }, 1000,
        function () {
            drop(this)
        })
    $(".waterdrop2").animate({
            left: "+=2",
        }, 500,
        function () {
            drop(this)
        })
    $(".waterdrop3").animate({
            left: "+=3",
        }, 700,
        function () {
            drop(this)
        })
});

//----------------WATERDROP-----------------
function drop(idRef) {
    if ($("#wateringcan").hasClass("rotate")) {
        var waterdropY = $("#wateringcan").offset().top
        var waterdropX = $("#wateringcan").offset().left
        $(".waterdrop1").offset({
            top: waterdropY + 100,
            left: waterdropX + 20
        })

        $(".waterdrop2").offset({
            top: waterdropY + 110,
            left: waterdropX + 30
        })

        $(".waterdrop3").offset({
            top: waterdropY + 120,
            left: waterdropX + 40
        })

        $(idRef).show().animate({
                "top": "+=500"
            }, 1000,
            function () {
                drop(idRef)
            });
    }
}



//----------------APPLETREE-----------------
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

//-----------------NET----------------------

var prevX = 0;

$(document).mousemove(function(e) {
    $('#net').css({
        left: e.pageX,
        top: e.pageY
    });
    if(prevX < e.pageX){
        $("#net").css("transform", "scaleX(-1)");
    }
    else{
        $("#net").css("transform", "scaleX(1)");
    }
    prevX = e.pageX;
});

//----------------BUTTERFLY-----------------


//move butterfly around all the time slowly
$(document).ready(function(){
    animateImage();
});


function makeNewPosition(){
    //the butterfly does not move away from the window screen
    var windowhHeight = $(window).height() - document.getElementById("butterfly").height;
    var windowWidth = $(window).width() - document.getElementById("butterfly").width;
    var newPositionHeight = Math.floor(Math.random() * windowhHeight);
    var newPositionWidth = Math.floor(Math.random() * windowWidth);
    return [newPositionHeight,newPositionWidth];
}

function animateImage(){
    var newPosition = makeNewPosition();
    $('#butterfly').animate({
        top: newPosition[0],
        left: newPosition[1]
    }, {
        duration: 3000,
        step: function (){
      animateImage();}
    });
}

//when hovering over butterfly, fly away quickly
$("#butterfly").hover(function() {
    $("#butterfly").stop();
    var newPosition = makeNewPosition();
    $('#butterfly').css({
        top: newPosition[0],
        left: newPosition[1]
    });
});

//Bartosz's part
/*
function swapImages() {
    var $active = $('#daynnight .active');
    var $next = ($('#daynnight .active').next().length > 0) ? $('#daynnight .active').next() : $('#daynnight img:first');
    $active.fadeOut(function () {
        $active.removeClass('active');
        $next.fadeIn().addClass('active');
    });
}






var t = 0;

function moveit() {
    t += 0.0025;

    var r = $(window).width() / 2;
    var xcenter = ($(window).width() / 2);
    var ycenter = $(window).height();
    var newLeft = Math.floor(xcenter + (r * Math.cos(t)));
    var newTop = Math.floor(ycenter + (r * Math.sin(t)));

    $('#daynnight .active').animate({
        top: newTop,
        left: newLeft,
    }, 1, function () {
        moveit();
    });
}


$(document).ready(function () {
    moveit();
    setInterval('swapImages()', 22000);
    $('#night').animate({'opacity':0.8}, 2000);
});
*/
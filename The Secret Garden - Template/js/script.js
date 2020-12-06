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
$('#wateringcan').css({
    "z-index": "3"
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
    $(".apple").css({
        "z-index": "3"
    })

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
    $("#apple1").css({
        "z-index": "1"
    });
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
    $("#apple2").css({
        "z-index": "1"
    });
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
    $("#apple3").css({
        "z-index": "1"
    });
});

/* function needed to bring the front of the basket upfront, otherwise the apples will be placed in front of the basket */

$("#basketfront").css({
    "z-index": "2"
});


/* function used to bring the apples in front of the basket */
$('.apple').parent().append($('.apple'));

//-----------------NET----------------------

var prevX = 0;

$(document).mousemove(function (e) {
    $('#net').css({
        left: e.pageX,
        top: e.pageY
    });
    if (prevX < e.pageX) {
        $("#net").css("transform", "scaleX(-1)");
    } else {
        $("#net").css("transform", "scaleX(1)");
    }
    prevX = e.pageX;
});
$('#net').css({
    "z-index": "3"
});
//----------------BUTTERFLY-----------------


$("#butterfly").animate("slow", function () {
    newPosition(this)
});

function newPosition() {
    var maxLeft = $(window).width() - $("#butterfly").width();
    var maxTop = $(window).height() - $("#butterfly").height();
    var left = Math.floor(Math.random() * (maxLeft + 1))
    var top = Math.floor(Math.random() * (maxTop + 1))

    $("#butterfly").animate({
            left: left,
            top: top
        }, 7000,
        function () {
            newPosition(this);
        });
}

$("#butterfly").on("mouseenter", function () {
    var maxLeft = $(window).width() - $("#butterfly").width();
    var maxTop = $(window).height() - $("#butterfly").height();
    var left = Math.floor(Math.random() * (maxLeft + 1))
    var top = Math.floor(Math.random() * (maxTop + 1))
    $("#butterfly").stop().animate({
        left: left,
        top: top
    }, "fast", function () {
        newPosition(this)
    })
});

$('#butterfly').css({
    "z-index": "4"
});

(function($){

        if($("#bxslider-home4").length >0){
            let slider = $("#bxslider-home4").bxSlider({
                nextText: "<i class='fa fa-angle-right'></i>",
                prevText: "<i class='fa fa-angle-left'></i>",
                auto: true,
                onSliderLoad (currentIndex){
                    $("#bxslider-home4 li").find(".caption").each(function (i) {
                        $(this).show().addClass("animated fadeInRight").one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function () {
                            $(this).removeClass("fadeInRight animated");
                        });
                    });
                },
                onSlideBefore (slideElement, oldIndex, newIndex){
                    slideElement.find(".caption").each(function () {
                        $(this).hide().removeClass("animated fadeInRight");
                    });
                },
                onSlideAfter (slideElement, oldIndex, newIndex){
                    setTimeout(function () {
                        slideElement.find(".caption").each(function () {
                            $(this).show().addClass("animated fadeInRight").one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function () {
                                $(this).removeClass("fadeInRight animated");
                            });
                        });
                    }, 500);
                }
            });
        }
})(jQuery);
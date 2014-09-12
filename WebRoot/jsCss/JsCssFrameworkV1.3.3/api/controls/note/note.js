window.onscroll = function () {
    var oDiv = $("#div1")[0];
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;

    //oDiv.style.top=scrollTop+(document.documentElement.clientHeight-oDiv.offsetHeight)/2+'px';
    var t = scrollTop + (document.documentElement.clientHeight - oDiv.offsetHeight) / 2;

    startMove(parseInt(t));
}

var timer = null;

function startMove(iTarget) {
    var oDiv = $("#div1")[0];

    clearInterval(timer);
    timer = setInterval(function () {
        var iSpeed = (iTarget - oDiv.offsetTop) / 8;
        iSpeed = iSpeed > 0 ? Math.ceil(iSpeed) : Math.floor(iSpeed);

        if (oDiv.offsetTop == iTarget) {
            clearInterval(timer);
        }
        else {
            oDiv.style.top = oDiv.offsetTop + iSpeed + 'px';
        }

    }, 30);
}





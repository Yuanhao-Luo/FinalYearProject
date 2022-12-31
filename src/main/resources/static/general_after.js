function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}

function jump_episode(id){
    location.href = "/watch?video=" + id;
}

$("#login_button").click(() => {
    console.log("click button")
    $('.ui.tiny.modal')
        .modal('show')
    ;
})

$('.menu .item')
    .tab()
;

$("#logout_button i").click(() => {
    window.location.href = "/logout";
})



const username = getCookie("username");
if (username !== ""){
    $("#account_username").text(username);
    $("#login_button").hide();
    $("#account_info").show();
}else{
    $("#account_info").hide();
}
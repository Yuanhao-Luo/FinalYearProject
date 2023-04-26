const error_name = ['login', 'register']


//Functions
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

function wrong_loginfo(){
    console.log("wrong loginfo")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $('.segment.active .login-username .login-label').addClass('red')
    $('.segment.active .login-password .login-label').addClass('red')

    $('#login_error').show()
    $('#login_error span').html('Input wrong username or password')
}

function account_exist(){
    console.log("account exist")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $(".login-titles a[data-tab='first']").removeClass("active")
    $(".login-titles a[data-tab='second']").addClass("active")
    $(".modal-content div[data-tab='first']").removeClass("active")
    $(".modal-content div[data-tab='second']").addClass("active")

    $('.segment.active .login-username .login-label').addClass('red')

    $('#register_error').show()
    $('#register_error span').html('Account exist')
}

function invalid_password(){
    console.log("invalid password")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $(".login-titles a[data-tab='first']").removeClass("active")
    $(".login-titles a[data-tab='second']").addClass("active")
    $(".modal-content div[data-tab='first']").removeClass("active")
    $(".modal-content div[data-tab='second']").addClass("active")

    $('.segment.active .login-password .login-label').addClass('red')

    $('#register_error').show()
    $('#register_error span').html('Password should contain at least 8 charcters, 1 uppercase letter, 1 lowercase letter, and 1 digital')
}

function invalid_email(){
    console.log("invalid email")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $(".login-titles a[data-tab='first']").removeClass("active")
    $(".login-titles a[data-tab='second']").addClass("active")
    $(".modal-content div[data-tab='first']").removeClass("active")
    $(".modal-content div[data-tab='second']").addClass("active")

    $('.segment.active .login-email .login-label').addClass('red')

    $('#register_error').show()
    $('#register_error span').html('Invalid E-mail')
}

function wrong_password_emailAuth(){
    console.log("wrong passwrod when authenticate email")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $(".login-titles a[data-tab='first']").removeClass("active")
    $(".login-titles a[data-tab='second']").addClass("active")
    $(".modal-content div[data-tab='first']").removeClass("active")
    $(".modal-content div[data-tab='second']").addClass("active")

    $('.segment.active .login-password .login-label').addClass('red')

    $('#register_error').show()
    $('#register_error span').html('Wrong password when authenticate e-mail')
}

function need_login(){
    console.log("need login")

    $('.ui.tiny.modal')
        .modal('show')
    ;

    $('#login_error').show()
    $('#login_error span').html('This operation need to login')
}









//execute after load
$("#login_button").click(() => {
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

const email = getCookie("email");
if (email !== ""){
    $("#account_username").css("color", "green")
}else{
    $("#account_username").css("color", "black")
}
function jumpto_video() {
    $("#main_content").load("/video");
}

function jumpto_login() {
    $("#main_content").load("/login");
}

function jumpto_home(){
    $("#main_content").load("/home_content");
}

function jumpto_music(){
    $("#main_content").load("/component/music");
    $("#music_player_content").show();
}

function jumpto_upload(){
    $("#main_content").load("/component/upload");
}

function jumpto_register(){
    $("#main_content").load("/register");
}

function jumpto_musiclist(id){
    $("#main_content").load("/component/musiclist");
    $("#music_player_content").show();
    musiclist_id = id;
}

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

function update_userinfo(){
    console.log("update user info")
    const username = getCookie("username");
    if (username !== ""){
        $("#account_username").text(username);
        $("#login_button").hide();
        $("#account_info").css("display", "flex");
    }else{
        $("#account_info").hide();
        $("#login_button").show();
    }

    const email = getCookie("email");
    if (email !== ""){
        $("#account_username").css("color", "green")
    }else{
        $("#account_username").css("color", "#edff36")
    }
}

function logout(){
    $.ajax({
        type: "GET",
        url: "/logout",
        success: function (data) {
            // jumpto_home();
            update_userinfo();
        }
    })
}

//clean all the elements in #music_list and add new items with music-item class
function change_musicList(musicList) {
    $("#music_list").empty()
    pageList = [];
    for (let i = 0; i < musicList.length; i++) {
        const music = musicList[i];
        const music_item = $(`<div v="${music.id}" class="music-item">
                    <div class="music-number">
                        ${i + 1}
                    </div>
                    <div class="music-title">
                        ${music.title}
                    </div>
                    <div class="music-vocal">
                        ${music.vocal == null ? "" : music.vocal}
                    </div>
                    <div class="music-buttons">
                        <button class="ui floating dropdown circular icon button">
                            <i class="add icon"></i>
                            <div id="music_player_musiclists" class="menu">
                                <div class="item">
                                    Musiclist 1
                                </div>
                                <div class="item">
                                    Musiclist 2
                                </div>
                            </div>
                        </button>
                    </div>
                </div>`)
        music_item.click((event) => {
            if (event.target.tagName == "BUTTON" || event.target.className == "add icon") {
                return
            }
            playMusic(music)

            //copy pageList to playList
            playList = pageList.slice();
        })

        const add_button = music_item.find(".dropdown")
        add_button.dropdown()
        add_button.click(() => {
            showMusiclist_addButton(add_button)
        })

        $("#music_list").append(music_item)
        pageList.push(music);
    }
}

//using musiclist id to change the music list
function change_musicList_byId(id) {
    $.ajax({
        method: "get",
        url:"/musiclist/user_list/" + id,
        success:function (result) {
            change_musicList(result)
        }
    })
}

function create_musiclist(event) {
    if (event.keyCode == 13){
        $.ajax({
            method: "get",
            url:"/create_musiclist",
            data: {
                title: event.target.value
            },
            dataType:"json",
            success:function (result) {

            }
        })
    }
}

//format the time to mm:ss
function formatTime(time) {
    let min = Math.floor(time / 60)
    let sec = Math.floor(time % 60)
    const res = `${min < 10 ? "0" + min : min}:${sec < 10 ? "0" + sec : sec}`
    console.log(res)
    return res
}

//play music
function playMusic(music) {
    currentMusic = music;

    $("#music_player").attr("src", "/music_player/" + music.id)
    music_player.play()
    $("#music_player_play").hide()
    $("#music_player_pause").show()
    $("#music_player_title").html(music.title)
    $("#music_player_vocal").html(music.vocal == null ? "" : music.vocal)
    music_player.addEventListener('loadedmetadata', () => {
        console.log(`Duration: ${music_player.duration}`);
        $("#music_player_duration").html(formatTime(music_player.duration));
    });
}

//play next music
function playNextMusic() {
    if (playSequence == 0) {
        //loop the list
        const index = playList.indexOf(currentMusic)
        if (index == playList.length - 1) {
            playMusic(playList[0])
        }else {
            playMusic(playList[index + 1])
        }
    } else if (playSequence == 1) {
        //random without the same music in a row
        let nextMusic = playList[Math.floor(Math.random() * playList.length)]
        while (nextMusic == currentMusic) {
            nextMusic = playList[Math.floor(Math.random() * playList.length)]
        }
        playMusic(nextMusic)
    } else if (playSequence == 2) {
        //single
        playMusic(currentMusic)
    }
}

//play previous music
function playPreviousMusic() {
    if (playSequence == 0) {
        //loop the list
        const index = playList.indexOf(currentMusic)
        if (index == 0) {
            playMusic(playList[playList.length - 1])
        }else {
            playMusic(playList[index - 1])
        }
    } else if (playSequence == 1) {
        //random without the same music in a row
        let nextMusic = playList[Math.floor(Math.random() * playList.length)]
        while (nextMusic == currentMusic) {
            nextMusic = playList[Math.floor(Math.random() * playList.length)]
        }
        playMusic(nextMusic)
    } else if (playSequence == 2) {
        //single
        playMusic(currentMusic)
    }
}

//show user's music list
function showMusiclist_addButton(add_button) {
    console.log(add_button)
    const menu = add_button.find(".menu")
    $.ajax({
        method: "get",
        url:"/user_musiclists",
        success:function (result) {
            console.log(result)
            console.log("menu: " + menu)
            //clear the list
            menu.empty()
            //add all items in result to #music_player_musiclists
            for (let i = 0; i < result.length; i++) {
                const musiclisthtml = $(`
                            <div class="item">
                                ${result[i].title}
                            </div>`)
                musiclisthtml.click((event) => {
                    event.preventDefault()
                    $.ajax({
                        method: "post",
                        url: "/add_musiclist",
                        data: {
                            musiclist_id: result[i].id,
                            music_id: add_button.parent().parent().attr("v")
                        },
                        dataType: "json",
                        success: function (result) {

                        }
                    })
                    event.stopPropagation()
                })



                console.log("musiclisthtml: " + musiclisthtml)
                menu.append(musiclisthtml)
            }
            var create_list = $(`
                    <div class="item create-list">
                        <span>Create New List</span>
                    </div>`)
            var create_input = $(
                '<div class="item create-list-input">' +
                    '<input type="text" name="new_musiclist" onkeydown="create_musiclist(event)">' +
                '</div>')

            create_list.click((event) => {
                event.preventDefault()
                create_list.hide()
                create_input.show()
                event.stopPropagation()
            });

            create_input.click((event) => {
                event.stopPropagation()
            });

            create_input.hide();

            menu.append(create_list);
            menu.append(create_input);
        }
    })
}





let isAdjustingTime = false;
let isAdjustingVolume = false;
let playList = [];
let pageList = [];
let currentMusic = null;
//Sequence: 0: loop, 1: random, 2: single
let playSequence = 0;
let musiclist_id;






console.log("mainpage_after.js loaded");
update_userinfo();
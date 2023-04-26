//clean all the elements in #music_list and add new items with music-item class
function change_musicList(musicList) {
    $("#music_list").empty()
    for (let i = 0; i < musicList.length; i++) {
        const music = musicList[i];
        const music_item = $(`<div class="music-item">
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
                musiclisthtml.click(() => {
                    $.ajax({
                        method: "post",
                        url: "/add_musiclist",
                        data: {
                            musiclist_id: result[i].id,
                            music_id: currentMusic.id
                        },
                        dataType: "json",
                        success: function (result) {

                        }
                    })
                })
                console.log("musiclisthtml: " + musiclisthtml)
                menu.append(musiclisthtml)
            }
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
const music_player = $("#music_player")[0];
const music_progress = $("#music_progress");
const music_volume = $("#music_player_volume");

$.ajax({
    method: "get",
    url:"/music_list/new",
    data: {},
    dataType:"json",
    success:function (result) {
        console.log(result)
        change_musicList(result)
    }
})

music_progress.progress();
$(".music-player-volume .ui.progress").progress({
    percent: 99
});
$("#music_player_add").dropdown()
$("#music_player_play").click(() => {
    music_player.play()
    $("#music_player_play").hide()
    $("#music_player_pause").show()
});
$("#music_player_pause").click(() => {
    music_player.pause()
    $("#music_player_pause").hide()
    $("#music_player_play").show()
});


music_progress.hover(() => {
    music_progress.css("height", "7px")
    $("#music_player_container").css("height", "84px")
}, () => {
    music_progress.css("height", "3px")
    $("#music_player_container").css("height", "80px")
});
music_progress.mousedown((event) => {
    if (!isAdjustingTime){
        isAdjustingTime = true;
        music_player.pause()
    }
    //change the progress
    music_progress.progress({
        percent: (event.clientX - music_progress.offset().left) / music_progress.width() * 100
    })
    //change the current time
    music_player.currentTime = music_player.duration * music_progress.progress("get percent") / 100
    $("#music_player_current").html(formatTime(music_player.currentTime))
})
music_progress.mousemove((event) => {
    if (isAdjustingTime) {
        //change the progress
        music_progress.progress({
            percent: (event.clientX - music_progress.offset().left) / music_progress.width() * 100
        })
        //change the current time
        music_player.currentTime = music_player.duration * music_progress.progress("get percent") / 100
        $("#music_player_current").html(formatTime(music_player.currentTime))
    }
})
music_progress.mouseleave((event) => {
    if (isAdjustingTime){
        isAdjustingTime = false;
        music_player.play()
    }
})
music_progress.mouseup(() => {
    if (isAdjustingTime){
        isAdjustingTime = false;
        music_player.play()
    }
})

//change volume
music_volume.mousedown((event) => {
    if (!isAdjustingVolume){
        isAdjustingVolume = true;
    }
    //change the progress
    music_volume.progress({
        percent: (event.clientX - music_volume.offset().left) / music_volume.width() * 100
    })
    //change the volume
    music_player.volume = $(".music-player-volume .ui.progress").progress("get percent") / 100
})
music_volume.mousemove((event) => {
    if (isAdjustingVolume) {
        //change the progress
        music_volume.progress({
            percent: (event.clientX - music_volume.offset().left) / music_volume.width() * 100
        })
        //change the volume
        music_player.volume = music_volume.progress("get percent") / 100
    }
})
music_volume.mouseleave((event) => {
    if (isAdjustingVolume){
        isAdjustingVolume = false;
    }
})
music_volume.mouseup(() => {
    if (isAdjustingVolume){
        isAdjustingVolume = false;
    }
})

music_player.addEventListener('ended', () => {
    playNextMusic()
})

//change sequence to random
$("#music_player_random").click(() => {
    playSequence = 1;
    //random button is selected
    $("#music_player_random").addClass("disabled")
    //loop and single button is not selected
    $("#music_player_loop").removeClass("disabled")
    $("#music_player_single").removeClass("disabled")
})
//change sequence to loop
$("#music_player_loop").click(() => {
    playSequence = 0;
    //loop button is selected
    $("#music_player_loop").addClass("disabled")
    //random and single button is not selected
    $("#music_player_random").removeClass("disabled")
    $("#music_player_single").removeClass("disabled")
})
//change sequence to single
$("#music_player_single").click(() => {
    playSequence = 2;
    //single button is selected
    $("#music_player_single").addClass("disabled")
    //random and loop button is not selected
    $("#music_player_random").removeClass("disabled")
    $("#music_player_loop").removeClass("disabled")
})

//next music
$("#music_player_next").click(() => {
    playNextMusic()
})

//previous music
$("#music_player_previous").click(() => {
    playPreviousMusic()
})


//show user's music list
$("#music_player_add").click((e) => {
    // const target = e.target;
    // if (target.tagName === "I"){
    //     showMusiclist_addButton(target.parent())
    // }else if (target.tagName === "DIV"){
    //     showMusiclist_addButton(target)
    // }
    showMusiclist_addButton($("#music_player_add"))
})


//set current time every 1s
setInterval(() => {
    const music = music_player;
    music_progress.progress({
        percent: music.currentTime / music.duration * 100
    })
    $("#music_player_current").html(formatTime(music.currentTime))
}, 1000)

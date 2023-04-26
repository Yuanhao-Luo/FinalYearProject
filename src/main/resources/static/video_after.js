function search_video(event){
    if (event.keyCode == 13){
        console.log("click enter")
        $.ajax({
            method: "get",
            url:"/search_video",
            data: {
                content: $("#search_input").val()
            },
            dataType:"json",
            success:function (result) {
                console.log("search success")
                console.log("search result ", result)
                chanage_videos(result)
            }
        })
    }
}

function chanage_videos(videoList){
    $("#video_list").empty();
    for (let i = 0; i < videoList.length; i++) {
        $("#video_list").append('<div class="ui card" >\n' +
            '        <a class="image" href="/watch?video=' + videoList[i].id + '">\n' +
            '          <img src="/image/' + videoList[i].image + '">\n' +
            '        </a>\n' +
            '        <div class="content">\n' +
            '          <a class="header" href="/watch?video=' + videoList[i].id + '">' + videoList[i].title + '</a>\n' +
            '        </div>\n' +
            '      </div>')
    }
}
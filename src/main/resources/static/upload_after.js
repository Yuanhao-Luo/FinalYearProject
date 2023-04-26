function search_series(){
    console.log("search series", $("#series_container .dropdown input.search").val())
    const title = $("#series_container .dropdown input.search").val();

    $.ajax({
        method: "get",
        url:"/search_series",
        data:{
            title: title
        },
        dataType:"json",
        success:function (result) {
            console.log('result   ', result)
            const values = []
            result.forEach((v) => {
                values.push({value: v.id, name: v.name})
            })
            console.log(values)
            $("#series_container .search.selection.dropdown").dropdown('clear')
            $("#series_container .search.selection.dropdown").dropdown('change values', values)

        }
    })
}

function search_creators(dropdown_target){
    console.log("search creators: ", dropdown_target.find("input.search").val())
    $.ajax({
        method: "get",
        url:"/search_creators",
        data:{
            name: dropdown_target.find("input.search").val()
        },
        dataType:"json",
        success:function (result) {
            console.log('result   ', result)
            const values = []
            result.forEach((v) => {
                values.push({value: v.id, name: v.name})
            })
            // dropdown_target.dropdown('clear')
            console.log("before change   ", $("[name='vocal']").val())
            dropdown_target.dropdown('change values', values)

        }
    })
}

function save_form(){
    $("#file_title").val($("#file_input").val().split('\\').pop())
    $("#image_title").val($("#image_input").val().split('\\').pop())
    $.ajax({
        method: "post",
        url:"/save_upload",
        data: $(".ui.form").form('get values'),
        dataType:"json",
        success:function (result) {
            console.log(result)
        }
    })
}

function new_creator(input){
    $.ajax({
        method: "post",
        url:"/new_creator",
        data: {
            name: input.val()
        },
        dataType:"json",
        success:function (result) {
            console.log(result)
        }
    })
}




$('.ui.radio.checkbox')
    .checkbox()
;

// $('#series_container .dropdown')
//     .dropdown()
// ;
$('#series_container .search.selection.dropdown').dropdown({
    // allowReselection: false,
    multiple: false,
    fullTextSearch: true,
    scrolling: true,
    maxSelections: 30,
    forceSelection: false
});

$('#vocal_container .search.selection.dropdown').dropdown({
    multiple: true,
    fullTextSearch: true,
    scrolling: true,
    maxSelections: 30,
    forceSelection: false,
    allowAdditions: true,
    hideAdditions: true,
    onAdd: function (addedValue, addedText, $addedChoice) {
        $addedChoice.removeClass('selected');
    },
    // onLabelCreate: function (value, text) {
    //     return $('<a class="ui label">' + text + '</a>');
    // }
})

$('#composer_container .search.selection.dropdown').dropdown({
    multiple: true,
    fullTextSearch: true,
    scrolling: true,
    maxSelections: 30,
    forceSelection: false
})

$('#lyricist_container .search.selection.dropdown').dropdown({
    multiple: true,
    fullTextSearch: true,
    scrolling: true,
    maxSelections: 30,
    forceSelection: false
})

$("#video_selection_container").click(() => {
    console.log("click video section")
    $("#series_container").show();
    $("#vocal_container").hide();
    $("#composer_container").hide();
    $("#lyricist_container").hide();
})

$("#music_selection_container").click(() => {
    $("#series_container").hide();
    $("#vocal_container").show();
    $("#composer_container").show();
    $("#lyricist_container").show();
})

// $("#series_container .dropdown").dropdown({onUpdate: search_series})
$("#series_container .selection input").on('input', search_series)
$("#vocal_container .selection input").on('input', () => {
    search_creators($("#vocal_container .search.selection.dropdown"))
})
$("#composer_container .selection input").on('input', () => {
    search_creators($("#composer_container .search.selection.dropdown"))
})
$("#lyricist_container .selection input").on('input', () => {
    search_creators($("#lyricist_container .search.selection.dropdown"))
})
$(".ui.form").on('change', save_form)
$("#new_series").click(() => {
    $.ajax({
        method: "post",
        url:"/new_series",
        data: {
            name: $("#series_container .dropdown input.search").val()
        },
        dataType:"json",
        success:function (result) {
            console.log(result)
        }
    })
})
$("#new_vocal").click(() => {
    new_creator($("#vocal_container .dropdown input.search"))
})
$("#new_composer").click(() => {
    new_creator($("#composer_container .dropdown input.search"))
})
$("#new_lyricist").click(() => {
    new_creator($("#lyricist_container .dropdown input.search"))
})
// $("#upload_submit").click((event) => {
//     event.preventDefault();
//     $("#upload_submit").addClass("loading")
//
//     var formData = new FormData();
//     formData.append('file', $('#file_input')[0].files[0]);
//     formData.append('image', $('#image_input')[0].files[0]);
//     formData.append('resource', $(".ui.form").form('get values'));
//
//
//     //submit the form with ajax
//     $.ajax({
//         url: "/resource",
//         type: "POST",
//         data: formData,
//         processData: false,
//         contentType: false,
//         success: function (data) {
//             //if finished clear the form
//             console.log(data)
//             $("#upload_submit").removeClass("loading")
//             $("#upload_form").form('clear');
//         }
//     });
//
// });

$('form').on('submit', function(event) {
    // Prevent the default form submission
    event.preventDefault();

    $("#upload_submit").addClass("loading")

    // Get the form data
    var formData = new FormData(this);

    // Send the AJAX request
    $.ajax({
        url: $(this).attr('action'),
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            // Handle the success response
            console.log(response);
            $("#upload_submit").removeClass("loading")
            jumpto_upload()
            // $("#upload_form").form('clear');
        },
        error: function(xhr, status, error) {
            // Handle the error response
            console.error(error);
        }
    });
});
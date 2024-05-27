$(document).ready(function(){
    let deleteBookBtn = $('#delete-book');
    console.log(deleteBookBtn);
    deleteBookBtn.on("click", ()=>{
        let endpoints = deleteBookBtn.data("action");
        console.log(endpoints);
        deleteBook(endpoints);
    });
});

const deleteBook = (endpoints) => {
    $.ajax({
        url: endpoints,
        type: "DELETE",
        success: function(response){
            $('#result-text').text("삭제되었습니다.");
        },
        error: function(xhr, status, error){
            $('#result-text').text("실패했습니다.")
        }
    });
}
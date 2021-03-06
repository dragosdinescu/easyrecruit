/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(() => {
    $("form#commentSection").on("submit", onCommentSubmit);
    $(document).on("click", ".comment .comment-edit", onCommentEditClick);
    $(document).on("click", ".comment .comment-delete", onCommentDeleteClick);
});

function onCommentSubmit(e) {
    e.preventDefault();

    if (!e.target.checkValidity())
        return;

    const editMode = $("#commentSection .comment-save-row").hasClass("edit-mode");

    $.ajax({
        url: editMode ? $("#submitCommentEdit")[0].formAction : $(e.target).attr("action"),
        data: $(e.target).serialize(),
        processData: false,
        method: $(e.target).attr("method"),
        error: (jqXHR, textStatus) => {
            console.error("Comment submit failed.");
            console.error(jqXHR);
            console.error(textStatus);
            $(e.target).toggleClass('was-validated', false);
        },
        success: (data) => {
            $(e.target).toggleClass('was-validated', false);

            if (editMode) {
                const saveTextArea = $("#commentSection #saveCommentText");
                const editedComment = $("#submitCommentEdit").data("edit-target");
                editedComment.find(".comment-text").text(saveTextArea.val());
            }
            else {
                let newCommentHtml = $("#commentSection .comment-template")[0].outerHTML;
                newCommentHtml = newCommentHtml.replace("((id))", data.id);
                newCommentHtml = newCommentHtml.replace("((id))", data.id);
                newCommentHtml = newCommentHtml.replace("((text))", data.text);
                newCommentHtml = newCommentHtml.replace("((username))", data.username);
                newCommentHtml = newCommentHtml.replace("((time))", data.time);

                const newCommentElement = $(newCommentHtml);
                newCommentElement.toggleClass("comment-template", false);
                $("#commentSection .comment-rows-container").prepend(newCommentElement);
            }

            $("#commentSection #saveCommentText").val("");
            $("#commentSection .comment-save-row").removeClass("edit-mode")
        }
    });

    return false;
}

function onCommentEditClick(e) {
    const editUrl = $(e.target).data("edit-url");
    const comment = $(e.target).closest(".comment");
    const text = comment.find(".comment-text").text().trim();
    const saveRow = $("#commentSection .comment-save-row");
    const saveTextArea = saveRow.find("#saveCommentText");
    const saveButton = saveRow.find("#submitCommentEdit");

    saveRow.toggleClass("edit-mode", true);
    saveButton.data("edit-target", comment);
    saveButton[0].formAction = editUrl;
    saveTextArea.val(text);
}

function onCommentDeleteClick(e) {
    $.ajax({
        url: $(e.target).data("edit-url"),
        method: "DELETE",
        error: (jqXHR, textStatus) => {
            console.error("Comment delete failed.");
            console.error(jqXHR);
            console.error(textStatus);
        },
        success: () => {
            $(e.target).closest(".comment").remove();
        }
    });
}

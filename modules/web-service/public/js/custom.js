var bookDeleteButton = $('#bookDeleteButton'),
    bookUpdateButton = $('#bookUpdateButton');
bookDeleteButton.bind("click", sendDeleteRequest);
bookUpdateButton.bind("click", sendPutRequest);

function sendDeleteRequest() {
    var url = bookDeleteButton.attr('data-url'),
        rUrl = bookDeleteButton.attr('data-rUrl');
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function(result) {
            window.location = rUrl;
        },
        error: function(result) {
          window.location.reload();
        }
    });
}

function sendPutRequest() {
    var formId = bookUpdateButton.attr('data-form-id'),
        rUrl = bookUpdateButton.attr('data-rUrl'),
        bookUpdateForm = $('#' + formId);
    $.ajax({
        url: bookUpdateForm.attr('action'),
        type: 'PUT',
        data: bookUpdateForm.serialize(),
        success: function(result) {
            window.location = rUrl;
        },
        error: function(result) {
            window.location.reload();
        }
    });
}
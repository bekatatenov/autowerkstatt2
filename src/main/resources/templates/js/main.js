$(document).ready(function () {

    $('.table .editBtn').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');
        var text = $(this).text();
        if (text == 'Edit') {
            $("label.error").hide();
            $(".error").removeClass("error");
            $("#saveeditbutton").prop("value", "Update");
            $.get(href, function (notification, status) {
                $('.myForm #id').val(notification.id)
                $('.myForm #firstName').val(notification.user.firstName)
                $('.myForm #lastName').val(notification.user.lastName)
                $('.myForm #dateTime').val(notification.dateTime)
                $('.myForm #dateFrom').val(notification.dateFrom)
                $('.myForm #dateBefore').val(notification.dateBefore)
                $('.myForm #description').val(notification.description)
                $('.myForm #price').val(notification.price)
                $('.myForm #faults').val(notification.faults)
                $('.myForm #mark').val(notification.mark)
                $('.myForm #model').val(notification.model)
                $('.myForm #status').val(notification.status)
            });

            $('.myForm #exampleModal').modal();
        } else {
            $("label.error").hide();
            $(".error").removeClass("error");
            $("#saveeditbutton").prop("value", "Save");
            $('.myForm #id').val('');
            $('.myForm #firstName').val('');
            $('.myForm #lastName').val('');
            $('.myForm #dateTime').val('');
            $('.myForm #dateFrom').val('');
            $('.myForm #dateBefore').val('');
            $('.myForm #description').val('');
            $('.myForm #price').val('');
            $('.myForm #faults').val('');
            $('.myForm #mark').val('');
            $('.myForm #model').val('');
            $('.myForm #status').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});
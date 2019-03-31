$(document).ready(function () {
    $('#newsform').validate({
        submitHandler: function (form) {
            var formData = getForm1();
            if (formData == null) {
                return;
            }
            $('#submitsubform').attr("disabled", true);
            
            $.ajax({
                type: "POST",
                url: '/subscribe',
                async: true,
                data: JSON.stringify(formData),
                contentType: "application/json",
                success: function (data) {
                    if (data.toLowerCase() == 'success') {
                        $('#nlmessage').html('Thanks for your subscription!');
                        $('#nlsub').hide();
                        _ouibounce.disable();//user subscribed , popup will not appear again.
                    } else if (data.toLowerCase() == 'error') {
                        $('#submitsubform').attr("disabled", false);
                        $('#nlmessage').html('<div class="alert alert-danger alert-error"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong> A problem has been occurred while submitting your data.</div>');
                    }
                    else {
                        $('#nlmessage').html('<div class="alert alert-danger alert-error"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong> ' + data + '</div>');
                    }
                },
                error: function errorFunc() {
                    $('#nlmessage').html('<div class="alert alert-danger alert-error"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error!</strong> A problem has been occurred while submitting your data.</div>');
                }
            });
        },

        rules: {

        "nlemail": {
                required: true,
                email: true
            }
        },

        messages: {
            "nlemail": "Please enter a valid email address."
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            //error.insertAfter(element);
        }

    });

    function getForm1() {

        var email = $('#nlemail').val();
        var campaign = $('#nlcampaign').val();

        // validation
        return (email == "") ? null : { email: email, campaign: campaign };
    }
});
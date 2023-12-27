/**
 * 
 */

function findId(){
	 $.ajax({
	    url: "/findId",
	    method: "POST",
	    success: function(response) {
	        location.href="/findIdResult";
	    },
	    error: function(xhr) {
	            alert("해당값이 존재하지 않습니다.");
	    }
	});	
	
}

function findPw(){
	
}
/**
 * 
 $.ajax({
    url: "/findPw",
    method: "POST",
    success: function(response) {
        // 성공적으로 응답을 받았을 때의 처리
        console.log(response);
    },
    error: function(xhr) {
        // 에러가 발생했을 때의 처리
        if (xhr.status === 400) {
            // 컨트롤러에서 결과가 false인 경우
            alert("오류 발생: " + xhr.responseText);
        }
    }
}); */

$('.modal').on('hidden.bs.modal', function (e) {
    console.log('modal close');
    $(this).find('form')[0].reset()
});

$("#checkEmail").click(function () {
	console.log('버튼눌림');
    let memberEmail = $("#memberEmail").val();
    let username = $("#username").val();

    $.ajax({
        type: "GET",
        url: "/check/findPw",
        data: {
            "memberEmail": memberEmail,
            "username": username
        },
        success: function (res) {
            if (res['check']) {
                swal("발송 완료!", "입력하신 이메일로 임시비밀번호가 발송되었습니다.", "success").then((OK) => {
                    if(OK) {
                        $.ajax({
                            type: "POST",
                            url: "/check/findPw/sendEmail",
                            data: {
                                "memberEmail": memberEmail,
                                "username": username
                            }
                        })
                        window.location = "/loginForm";
                    }


                }
            )
                $('#checkMsg').html('<p style="color:darkblue"></p>');
            } else {
                $('#checkMsg').html('<p style="color:red">일치하는 정보가 없습니다.</p>');
            }
        }
    })
})

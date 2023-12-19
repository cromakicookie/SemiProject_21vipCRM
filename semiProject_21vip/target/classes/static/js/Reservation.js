

// 검색 함수
function SearchCustomerNum() {
	document.searchForm.submit();
}

// 수정취소 함수
function UpdateCancel() {
	history.back();
}

// 등록취소 함수
function InsertCancel() {
	location.href = "./reservation";
}



// 수정폼 유효성 검사
function UpdateMessage() {

	var minDate = new Date().toISOString().split('T')[0];

	document.getElementById("luxuryDate").min = minDate;

	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
		.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거
	var minLength = 11;

	if (document.ReservationUpdateForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationUpdateForm.customerNum.focus();
	} else if (document.ReservationUpdateForm.customerName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationUpdateForm.customerName.focus();
	} else if (document.ReservationUpdateForm.luxuryBrandName.value == "") {
		alert("브랜드를 선택해주세요.");
		document.ReservationUpdateForm.luxuryBrandName.focus();
	} else if (document.ReservationUpdateForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationUpdateForm.luxuryDate.focus();
	} else if (document.ReservationUpdateForm.luxuryDate.value < minDate) {
		alert("금일 이전의 날짜를 선택할 수 없습니다.")
		document.ReservationUpdateForm.luxuryDate.focus();
	} else if (document.ReservationUpdateForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationUpdateForm.luxuryTime.focus();
	} else if (document.ReservationUpdateForm.luxuryHeadCount.value == "" || document.ReservationUpdateForm.luxuryHeadCount.value == 0) {
		alert("참여인원을 입력해주세요.");
		document.ReservationUpdateForm.luxuryHeadCount.focus();
	} else if (document.ReservationUpdateForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationUpdateForm.luxuryPhone.focus();
	} else if (luxuryPhoneValue.length < minLength) {
		alert("최소 11자리를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else {
		alert("예약수정이 완료되었습니다.")
		document.ReservationUpdateForm.submit();
		
	} 
		
}



// 전화번호 입력 패턴
function oninputPhone(target) {

	/*    target.value = target.value.replace(/[^0-9]/g, '')
			.replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");*/

	target.value = target.value.replace(/[^0-9]/g, '')
		.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/g, "$1-$2-$3");


}

// 고객번호로 조회해서 Customer 값 불러오기(등록폼)
function findCustomerNum() {


	var searchcustomerNum = $("#searchcustomerNum").val();
	$.ajax({
		type: "GET",
		url: "/findCustomerNum",
		data: { "customerNum": searchcustomerNum },
		success: function(data) {
			console.log(data);

			var Num = data.customerNum;
			var Name = data.customerName;
			var Grade = data.customerGrade;
			var FavoriteStore = data.favoriteStore;
			var CustomerBirth = data.customerBirth;
			
			customerNumText(Num,Name,Grade,FavoriteStore,CustomerBirth);

			$(function() {
				$('.push').off('click').on('click', function() {
					var Num2 = $("#num").text();
					var Name2 = $("#name").text();
					
					if (Num2 == "") {
						alert("없는 고객번호 입니다.");
						$('input[name=customerNum]').val("");
						$('input[name=customerName]').val("");
					} else {
						$('input[name=customerNum]').val(Num2);
						$('input[name=customerName]').val(Name2);
					}
				});
			});



		},
		error: function(error) {
			console.error("Ajax 요청 에러:", error);
		}
	});
}

function DuplicateTest() {
	var customerNum = document.getElementById("searchcustomerNum").value;
	
	$.ajax({
		url: '/Duplicate',
		type: 'POST',
		data: {"customerNum":customerNum},
		success: function(check) {
			if(check==1){
				$('#okmsg').css("display", "inline-block");
				$('#nomsg').css("display", "none");
			} else {
				$('#okmsg').css("display", "none");
				$('#nomsg').css("display", "inline-block");
			}
		}
	})
	
}


function customerNumText(Num,Name,Grade,FavoriteStore,CustomerBirth) {
	
	if (Num) {
		$("#num").text(Num || "");
		$("#name").text(Name || "");
		$("#grade").text(Grade || "");
		$("#favoriteStore").text(FavoriteStore || "");
		$("#customerBirth").text(CustomerBirth || "");
		$("#messageRow").hide();  // 데이터가 있으면 메시지 행을 숨김
		$("#customerTable").show();
	} else {
		$("#messageRow").show();  // 데이터가 없으면 메시지 행을 표시
		$("#customerTable").hide();
		$("#num").text("")

	}
}




function confirmLuxuryDelete() {
	var Num = document.getElementById('customer').textContent;
	var result = confirm("고객번호 - " + Num + " 삭제하시겠습니까?");
	var deleteSeq = document.getElementById('getSeq').textContent;
	if (result) {
		$.ajax({
			type: "GET",
			url: "/luxuryDelete",
			data: { "luxurySeq": deleteSeq },
			success: function() {
				alert("삭제되었습니다.");
				location.href = "./reservation";
			},
			error: function(error) {
				console.error("삭제 요청 에러:", error);
			}
		});
	} else {
		alert("취소되었습니다.");
	}
}

// 등록 폼 유효성검사
function insertMessage() {

	var minDate = new Date().toISOString().split('T')[0];

	document.getElementById("luxuryDate").min = minDate;


	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
	.replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거*/

	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
		.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거
	var minLength = 11;



	if (document.ReservationForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationForm.customerNum.focus();
	} else if (document.ReservationForm.customerName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationForm.customerName.focus();
	} else if (document.ReservationForm.luxuryBrandName.value == "none") {
		alert("브랜드를 선택해주세요.");
		document.ReservationForm.luxuryBrandName.focus();
	} else if (document.ReservationForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationForm.luxuryDate.focus();
	} else if (document.ReservationForm.luxuryDate.value < minDate) {
		alert("금일 이전의 날짜를 선택할 수 없습니다.")
		document.ReservationForm.luxuryDate.focus();
	} else if (document.ReservationForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationForm.luxuryTime.focus();
	} else if (document.ReservationForm.luxuryHeadCount.value == "" || document.ReservationForm.luxuryHeadCount.value == 0) {
		alert("참여인원을 입력해주세요.");
		document.ReservationForm.luxuryHeadCount.focus();
	} else if (document.ReservationForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else if (luxuryPhoneValue.length < minLength) {
		alert("최소 11자리를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else {
		alert("에약등록이 완료되었습니다.")
		document.ReservationForm.submit();
	}
		
		
}

   function sendLinkCustom() {
        Kakao.init("c4d73e82a26e246155c3246c9a8d45ec");
        Kakao.Link.sendCustom({
            templateId: 101944
        });
    }
    
    
/*
try {
  function sendCommerceMessage() {*/
	  
/*          Kakao.init('c4d73e82a26e246155c3246c9a8d45ec');

    function kakaoLogin() {
        Kakao.Auth.login({
            success: function(authObj) {
                console.log('Authorization successful:', authObj);
                // 여기서 authObj.access_token을 사용하여 사용자 정보 요청 등 다양한 작업 수행 가능
                getUserInfo(authObj.access_token);
            },
            fail: function(err) {
                console.error('Authorization failed:', err);
            }
        });
    }

    function getUserInfo(accessToken) {
        Kakao.API.request({
            url: '/v2/user/me',
            success: function(response) {
                console.log('User info:', response);
                // 여기서 사용자 정보를 처리하면 됨
                sendMessage(response.id, 'Hello, Kakao Message!');
            },
            fail: function(error) {
                console.error('Failed to get user info:', error);
            }
        });
    }
	  
function sendMessage(userId, message) {
    Kakao.API.request({
        url: '/v1/api/talk/friends/message/default/send',
        data: {
            receiver_uuids: JSON.stringify([userId]), // 배열로 감싸서 전달
            template_object: JSON.stringify({
                object_type: 'text',
                text: message,
                link: {
                    web_url: 'http://localhost:8080/',
                },
            }),
        },
        success: function(response) {
            console.log('Message sent successfully:', response);
        },
        fail: function(error) {
            console.error('Failed to send message:', error);
        }
    });
}*/







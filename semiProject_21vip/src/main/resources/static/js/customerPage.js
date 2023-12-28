//sms전송
function sendSMS(button) {

	// 현재 클릭된 버튼의 부모 행을 찾습니다.
	let row = button.closest('tr');

	// 행에서 CheckedVoucherCode의 값을 가져옵니다.
	let checkedVoucherCode = row.querySelector('.vCode').textContent;
	let voucherType2 = row.querySelector('.vType').textContent;
	let voucherService2 = row.querySelector('.vTheme').textContent;
	let voucherServiceName2 = row.querySelector('.vServiceName').textContent;
	let voucherIssuedDate = row.querySelector('.vIssuedDate').textContent;
	let voucherExdate = row.querySelector('.vExdate').textContent;

	console.log(checkedVoucherCode);

	if (confirm("SMS 전송할 바우처 코드를 확인해주세요\n" + checkedVoucherCode)) {
		// 사용자가 확인을 선택한 경우에 수행할 동작
		let phoneNumber = prompt("SMS 전송할 휴대폰 번호를 입력하세요.", "");
		// 입력이 취소되거나 빈 문자열인 경우 아무 작업도 하지 않음
        if (phoneNumber === null) {
            alert("SMS 전송이 취소되었습니다.");
            return;
        }else if(phoneNumber === "" || phoneNumber.length !== 11){
			alert("올바른 연락처를 입력해 주세요");
			return;
		}

		$.ajax({
			url: 'sendVoucherSMS',
			type: 'POST',
			data: {
				voucherType2: voucherType2,
				voucherService2: voucherService2,
				voucherServiceName2: voucherServiceName2,
				checkedVoucherCode: checkedVoucherCode,
				voucherIssuedDate: voucherIssuedDate,
				voucherExdate: voucherExdate,
				phoneNumber: phoneNumber
			},
			success: function(result) {
				if (!result) {
					alert("메세지 전송을 실패하였습니다.");

				} else {
					alert("메세지 전송 완료")
					window.location.reload(); // 부모 창 리로드
				}
			}
		})//ajax
		console.log("사용자가 확인을 선택했습니다.");
	} else {
		// 사용자가 취소를 선택한 경우에 수행할 동작
		alert("SMS 전송을 취소합니다.");
	}



}


//고객번호로 검색
function searchCustomerNum() {
	var inputNumber = document.getElementById('customerNum');

	if (!inputNumber.value.trim()) {
		alert("고객번호를 입력해 주세요");
		inputNumber.focus();
	} else {

		document.getElementById('searchCustomerNum').submit();
	}
}


//바우처 등록
function insertVoucher() {
	window.open("customerVoucherForm", "_blank", "width=800,height=400");
}


//바우처 삭제
function deleteVoucher(button) {

	// 현재 클릭된 버튼의 부모 행을 찾습니다.
	var row = button.closest('tr');
	// 행에서 CheckedVoucherCode의 값을 가져옵니다.
	var checkedVoucherCode = row.querySelector('.vCode').textContent;

	console.log('삭제할 Voucher Code:', checkedVoucherCode);

	var result = confirm("정말 삭제하시겠습니까?");
	if (result) {

		alert("Delete voucherCode: " + checkedVoucherCode)

		$.ajax({
			url: 'deleteVoucher/' + checkedVoucherCode,
			type: "GET",
			success: function() {
				console.log("삭제 성공");
				alert("삭제되었습니다.");
				location.reload();

			},
			error: function(error) {
				console.error('Error:', error);
				alert("삭제 실패");
			}
		});

	}
}


//메모삭제
function deleteMemo() {
	if (!confirm("정말 삭제하시겠습니까?")) {

	} else {
		alert("확인(예)을 누르셨습니다.");
		//메모순번
		var memoRN = $("#memoNum").val();

		//alert("Delete memo with ID: " + memoRN)

		$.ajax({
			url: "deleteMemo/" + memoRN,
			type: "GET",
			success: function() {
				console.log("삭제 성공");
				alert("삭제되었습니다.");
				location.reload();

			},
			error: function(error) {
				console.error('Error:', error);
				alert("삭제 실패");

			}
		});
	}
}



// 제이쿼리
$(document).ready(function() {

	$('#mSubmit').on("click", function() {

		let memoContent = $('#memo_content');

		if (!memoContent.val().trim()) {
			alert("내용을 입력해 주세요");
			memoContent.focus();
			event.preventDefault();
		} else {
			alert("메모 등록 성공")
			$("#memoInsert").submit();
			$('#memoContent').val('');
		}

	})//mSubmit


	$(".table_memo tbody tr").each(function(index) {
		$(this).data("originalIndex", index);
	});


	// 메모 중요 체크박스 이벤트
	$(".check_important").change(function() {
		var row = $(this).closest("tr");
		var row1 = row.next("tr");


		// 체크된 행이 있는 경우
		if (this.checked) {
			row.toggleClass("selected-row", this.checked);
			$(".table_memo tbody").prepend(row);

		} else {
			row.removeClass("selected-row");
			row.before(row1);

			// 이전에 저장해둔 원래 위치로 삽입
			var originalIndex = row.data("originalIndex");
			if (originalIndex !== undefined) {
				$(".table_memo tbody").children("tr").eq(originalIndex).after(row);
			}

		}
	});

})

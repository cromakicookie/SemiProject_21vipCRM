//고객번호로 검색
function searchCustomerNum(){
	var inputNumber = document.getElementById('customerNum');
	
	if(!inputNumber.value.trim()){
		alert("고객번호를 입력해 주세요");
		inputNumber.focus();
	}else{
		document.getElementById('searchCustomerNum').submit();
	}
}


/*
    var formData = new FormData(this);

    fetch('/searchCustomer', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.check === 0) {
            // 데이터가 없는 경우
            alert('데이터가 없습니다.');
        } else {
            // 데이터가 있는 경우
            // 여기에서 필요한 다른 동작을 수행할 수 있습니다.
            this.submit(); // 폼 제출
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});*/



//바우처 등록
function insertVoucher() {
  window.open("voucherForm", "_blank", "width=800,height=400");
}

//바우처 수정
function updateVoucher() {
  // 폼 제출 로직 추가
  window.open("voucherUpdateForm", "_blank", "width=800,height=400");
}

//바우처 삭제
function deleteVoucher() {
  // 폼 제출 로직 추가
  var result = confirm("정말 삭제하시겠습니까?");
  if (result) {
    //delete
    alert("삭제되었습니다.");
  } else {
    alert("취소되었습니다.");
  }
}

// 제이쿼리
$(document).ready(function () {
	
	
	
	/*  $.ajax({
        url: 'searchCustomer',
        method: 'POST',
        success: function(data) {
            // 데이터가 있을 때
            if (data.length > 0) {
                // 테이블 보이기
                $('.customer_info').show();
  				// 데이터를 테이블에 추가하는 로직

                // 데이터를 반복하여 테이블에 추가
              
            } else {
                // 데이터가 없을 때 테이블 숨기기
                $('.customer_info').hide();
            }
        },
        error: function(error) {
            console.error('AJAX 요청 에러:', error);
        }
    });
*/
	

  // 클래스명이 'customer_info'인 테이블 숨기기 보이기
  //$(".customer_info").show();
  //$(".customer_info").hide();

  // 체크박스가 변경될 때 이벤트 리스너 추가
  $(".check_important").change(function () {
    var row = $(this).closest("tr");

    // 체크된 행이 있는 경우
    if (this.checked) {
      // 체크박스의 상태에 따라서 해당 행 토글
      $(this).closest("tr").toggleClass("selected-row", this.checked);
      // 체크된 행을 테이블의 첫 번째 행으로 이동
      $(".table_memo tbody").prepend(row);
    } else {
      row.removeClass("selected-row");
      $(".table_memo tbody").append(row);
    }
  });
});
//고객번호로 검색
function searchCustomerNum(){
	document.getElementById('searchCustomerNum').submit();
}

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

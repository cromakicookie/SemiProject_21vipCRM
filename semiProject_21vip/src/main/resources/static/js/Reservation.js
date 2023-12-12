document.addEventListener("DOMContentLoaded", function() {
	
	var minDate = new Date().toISOString().split('T')[0];
	
	document.getElementById("luxuryDate").min = minDate;
});

// 삭제 함수
function luxurydelete() {
	alert("삭제되었습니다.");
}

// 검색 함수
function SearchCustomerNum() {
    document.searchForm.submit();
    document.searchForm.searchKeyword.value.focus();
}

// 수정취소 함수
function UpdateCancel() {
	history.back();
}

// 등록취소 함수
function InsertCancel() {
	location.href="./reservation";
}


		
// 등록 폼 유효성검사
function insertMessage() {
	
    var minDate = new Date().toISOString().split('T')[0];
	
	document.getElementById("luxuryDate").min = minDate;
	
	
/*	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
	.replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거*/
	
	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
	.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거
    var minLength = 11;
    

    
	if(document.ReservationForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationForm.customerNum.focus();
	} else if(document.ReservationForm.luxuryName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationForm.luxuryName.focus();
	} else if(document.ReservationForm.luxuryBrandName.value == "none") {
		alert("브랜드를 선택해주세요.");
		document.ReservationForm.luxuryBrandName.focus();
	} else if(document.ReservationForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationForm.luxuryDate.focus();
	} else if(document.ReservationForm.luxuryDate.value < minDate) {
		alert("금일 이전의 날짜를 선택할 수 없습니다.")
		document.ReservationForm.luxuryDate.focus();
	} else if(document.ReservationForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationForm.luxuryTime.focus();
	} else if(document.ReservationForm.luxuryHeadCount.value == "" || document.ReservationForm.luxuryHeadCount.value == 0) {
		alert("참여인원을 입력해주세요.");
		document.ReservationForm.luxuryHeadCount.focus();
	} else if(document.ReservationForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else if(luxuryPhoneValue.length < minLength) {
		alert("최소 11자리를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else {
		alert("에약등록이 완료되었습니다.")
    	document.ReservationForm.submit();
	}
    
}


// 수정폼 유효성 검사
function UpdateMessage() {
	
	var minDate = new Date().toISOString().split('T')[0];
	
	document.getElementById("luxuryDate").min = minDate;
	
	var luxuryPhoneValue = document.getElementById('luxuryPhone').value.replace(/[^0-9]/g, '')
	.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/g, "$1-$2-$3"); // 숫자 이외의 문자 제거
    var minLength = 11;
	
	if(document.ReservationUpdateForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationUpdateForm.customerNum.focus();
	} else if(document.ReservationUpdateForm.luxuryName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationUpdateForm.luxuryName.focus();
	} else if(document.ReservationUpdateForm.luxuryBrandName.value == "") {
		alert("브랜드를 선택해주세요.");
		document.ReservationUpdateForm.luxuryBrandName.focus();
	} else if(document.ReservationUpdateForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationUpdateForm.luxuryDate.focus();
	} else if(document.ReservationUpdateForm.luxuryDate.value < minDate) {
		alert("금일 이전의 날짜를 선택할 수 없습니다.")
		document.ReservationUpdateForm.luxuryDate.focus();
	} else if(document.ReservationUpdateForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationUpdateForm.luxuryTime.focus();
	} else if(document.ReservationUpdateForm.luxuryHeadCount.value == "" || document.ReservationUpdateForm.luxuryHeadCount.value == 0) {
		alert("참여인원을 입력해주세요.");
		document.ReservationUpdateForm.luxuryHeadCount.focus();
	} else if(document.ReservationUpdateForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationUpdateForm.luxuryPhone.focus();
	} else if(luxuryPhoneValue.length < minLength) {
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

		




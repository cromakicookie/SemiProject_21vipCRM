// Reservation
function luxurydelete() {
	alert("삭제되었습니다.");
}

// Reservation
function SearchCustomerNum() {
    document.searchForm.submit();
}

function UpdateCancel() {
	history.back();
}

function InsertCancel() {
	location.href="./reservation";
}


// 등록 폼 유효성검사
function insertMessage() {
	if(document.ReservationForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationForm.customerNum.focus();
	} else if(document.ReservationForm.luxuryBrandName.value == "none") {
		alert("브랜드를 선택해주세요.");
		document.ReservationForm.luxuryBrandName.focus();
	} else if(document.ReservationForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationForm.luxuryDate.focus();
	} else if(document.ReservationForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationForm.luxuryTime.focus();
	} else if(document.ReservationForm.luxuryName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationForm.luxuryName.focus();
	} else if(document.ReservationForm.luxuryHeadCount.value == "") {
		alert("참여인원을 입력해주세요.");
		document.ReservationForm.luxuryHeadCount.focus();
	} else if(document.ReservationForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationForm.luxuryPhone.focus();
	} else {
		alert("에약등록이 완료되었습니다.")
    	document.ReservationForm.submit();
	}
    
}


// 수정폼 유효성 검사
function UpdateMessage() {
	if(document.ReservationUpdateForm.customerNum.value == "") {
		alert("고객번호를 입력해주세요.");
		document.ReservationUpdateForm.customerNum.focus();
	} else if(document.ReservationUpdateForm.luxuryBrandName.value == "") {
		alert("브랜드를 선택해주세요.");
		document.ReservationUpdateForm.luxuryBrandName.focus();
	} else if(document.ReservationUpdateForm.luxuryDate.value == "") {
		alert("예약일자를 입력해주세요.");
		document.ReservationUpdateForm.luxuryDate.focus();
	} else if(document.ReservationUpdateForm.luxuryTime.value == "") {
		alert("예약시간을 입력해주세요.");
		document.ReservationUpdateForm.luxuryTime.focus();
	} else if(document.ReservationUpdateForm.luxuryName.value == "") {
		alert("고객명을 입력해주세요.");
		document.ReservationUpdateForm.luxuryName.focus();
	} else if(document.ReservationUpdateForm.luxuryHeadCount.value == "") {
		alert("참여인원을 입력해주세요.");
		document.ReservationUpdateForm.luxuryHeadCount.focus();
	} else if(document.ReservationUpdateForm.luxuryPhone.value == "") {
		alert("전화번호를 입력해주세요.");
		document.ReservationUpdateForm.luxuryPhone.focus();
	} else {
		alert("에약등록이 완료되었습니다.")
    	document.ReservationUpdateForm.submit();
    	
	}
	window.reload();
}


function oninputPhone(target) {
    target.value = target.value.replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}



function customerInsertCheck(){
	let customerNum = $('#customer_num').val();	

	if(customerNum == ""){
		alert("고객번호를 생성해 주세요.");
		document.customerForm.customerNum.focus();
	}else if(document.customerForm.customerName.value ==""){
		alert("고객명을 입력해주세요.")
		document.customerForm.customerName.focus();
	}else if(document.customerForm.customerHP.value ==""){
		alert("연락처를 입력해주세요.")
		document.customerForm.customerHP.focus();
	}else if($(':radio[name="customerGender"]:checked').length < 1){
		alert("성별을 체크해주세요.")
		document.customerForm.male.focus();
	}else if(document.customerForm.customerBirth.value ==""){
		alert("생년월일을 입력해주세요.")
		document.customerForm.customerBirth.focus();
	}else{
		document.customerForm.submit();
	}
	
}


function customerInsertReset(){
	location.reload();
}

// 오늘의 날짜를 구하는 함수
function getCurrentDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더하고 2자리로 만듭니다.
    const day = today.getDate().toString().padStart(2, '0');
    
    return `${year}-${month}-${day}`;
}


/*insertCustomer.js*/
document.addEventListener('DOMContentLoaded', function() {
    checkCustomerNum();
});


function checkCustomerNum() {
	var customerNum = document.getElementById("customer_num").value;
	console.log(customerNum)
	
	function displayNone(){
		$('.cn_ok').css("display", "none");
		$('.cn_already').css("display", "none");
	}

	//서버에서 고객번호 중복체크 진행 : 중복이면 1 없으면 0 
	$.ajax({
		url:'haveCustomer',
		type: 'POST',
		data: {customerNum:customerNum},
		success:function(check){
			console.log(check)
			displayNone()
			if(check==1){
				$('.cn_already').css("display", "inline-block");
			}else{
				$('.cn_ok').css("display", "inline-block");
			}
		}
	})//ajax
	
}//checkCustomerNum()


function reGenerateCustomerNum(){
	var customerNum = document.getElementById("customer_num").value;
	
	$.ajax({
		url:'generateCustomerNum',
		type: 'GET',
		success:function(re){
			console.log(re)
			alert("랜덤 고객번호를 재생성합니다.")
			$('#customer_num').val(re);
		
		}
	})
		
}//reGenerateCustomerNum()


function customerInsertCheck(){
	var username = document.joinForm.username.value;
	
	if(customer_name == ""){
		alert("고객명을 입력해주세요.");
		document.customerForm.customer_name.focus();
	}else if(document.customerForm.customer_HP.value ==""){
		alert("연락처를 입력해주세요.")
		document.customerForm.customer_HP.focus();
	}else if(!(maleRadio.checked || femaleRadio.checked)){
		alert("성별을 선택해주세요")
		document.customerForm.customer_HP.focus();
	}else if(document.customerForm.customer_Birth.value ==""){
		alert("생년월일을 입력해주세요.")
		document.customerForm.customer_Birth.focus();
	}else if(document.customerForm.favorite_Store.value ==""){
		alert("주 이용점을 선택해주세요.")
		document.customerForm.favorite_Store.focus();	
	}else{
		document.customerForm.submit();
	}
	
}


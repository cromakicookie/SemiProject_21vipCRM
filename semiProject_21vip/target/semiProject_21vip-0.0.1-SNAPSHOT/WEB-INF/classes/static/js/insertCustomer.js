/*insertCustomer.js*/
document.addEventListener('DOMContentLoaded', function() {
    checkCustomerNum();
    $('#customer_Birth').prop('max', getCurrentDate());
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
				$('.cn_already').css({
									    "display": "inline-block",
									    "color": "red"
									});
			$('#customerForm').submit(function(event) {
                event.preventDefault();
                alert("사용할 수 없는 고객번호 입니다.")
            });
            
			}else{
				$('.cn_ok').css({
									  "display": "inline-block",
									  "color": "green"
								});
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
			checkCustomerNum();
		
		},
		error: function(error) {
		console.error('Error:', error);
		 alert("고객번호 생성 실패");
		}
	
	})
		
}//reGenerateCustomerNum()


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


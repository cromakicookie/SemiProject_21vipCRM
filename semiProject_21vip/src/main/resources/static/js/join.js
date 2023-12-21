/**
 * 
 */



function checkId() {
	var username = document.getElementById("username").value;
	const idStartNumRegex = /^[A-Za-z][A-Za-z0-9]*$/;
	const idlengthRegex = /^[A-Za-z0-9]{4,20}$/;
	
	function displayNone(){
		$('.id_ok').css("display", "none");
		$('.id_already').css("display", "none");
		$('.id_startnum').css("display", "none");
		$('.id_shortnum').css("display", "none");
	}
	
	$.ajax({
		url:'idValid',
		type: 'POST',
		data: {username:username},
		success:function(haveId){
			if(!haveId){
				displayNone()
				$('.idError').val('ok');
				if(!idStartNumRegex.test(username)){
					$('.idError').val('error');
					$('.id_startnum').css("display", "inline-block");
				}else if(!idlengthRegex.test(username)){
					$('.idError').val('error');
					$('.id_shortnum').css("display", "inline-block");
				}else{
					$('.id_ok').css("display", "inline-block");
				}
			}else{
				displayNone()
				$('.idError').val('doubleId');
				$('.id_already').css("display", "inline-block");
			}
		}
		
	})
}

function checkPw(){
	const pwRegex = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d]{8,}$/;
	var pw = document.joinForm.password.value;
	
	function displayNone(){
		$('.pw_error').css("display", "none");
		$('.pw_ok').css("display", "none");
	}
	
	if(!pwRegex.test(pw)){
		displayNone();
		$('.pw_error').css("display", "inline-block");
		$('.pwError').val('error');
	}else{
		displayNone();
		$('.pw_ok').css("display", "inline-block");
		$('.pwError').val('ok');
		
	}
}


function checkOk(){
	var pw = document.joinForm.password.value;
	var pwCk = document.joinForm.passwordCk.value;
	
	function displayNone(){
		$('.pwCk_error').css("display", "none");
		$('.pwCk_ok').css("display", "none");
	}
	
	if(pw!=pwCk){
		displayNone();
		$('.pwCk_error').css("display", "inline-block");
		$('.pwCkError').val('error');
	}else{
		displayNone();
		$('.pwCk_ok').css("display", "inline-block");
		$('.pwCkError').val('ok');
		
	}
}

function joinCheck(){
	const emailRegex = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/;
	var username = document.joinForm.username.value;
	
	if(username == ""){
		alert("아이디를 입력해주세요.");
		document.joinForm.username.focus();
	}else if(document.joinForm.idError.value =="error"){
		alert("아이디 형식이 맞지 않습니다.")
		document.joinForm.username.focus();
	}else if(document.joinForm.idError.value =="doubleId"){
		alert("아이디가 중복되어 있습니다.")
		document.joinForm.username.focus();
	}else if(document.joinForm.password.value ==""){
		alert("비밀번호를 입력해주세요.")
		document.joinForm.password.focus();
	}else if(document.joinForm.pwError.value =="error"){
		alert("비밀번호 조건을 충족하지 못했습니다.")
		document.joinForm.password.focus();
	}else if(document.joinForm.pwCkError.value =="error"){
		alert("비밀번호가 일치하지 않습니다.")
		document.joinForm.password.focus();
	}else if(document.joinForm.memberName.value ==""){
		alert("이름을 입력해주세요.")
	}else if(document.joinForm.memberEmail.value ==""){
		alert("이메일을 입력해주세요.")
		document.joinForm.memberEmail.focus();
	}else if(!emailRegex.test(document.joinForm.memberEmail.value)){
		alert("이메일 형식이 맞지 않습니다.")
		document.joinForm.memberEmail.focus();
	}else if(document.joinForm.memberBirth.value ==""){
		alert("생년월일을 입력해주세요.")
		document.joinForm.memberBirth.focus();
	}else if(document.joinForm.memberDept.value ==""){
		alert("부서를 선택해주세요.")
		document.joinForm.memberDept.focus();
	}else if(document.joinForm.memberRole.value ==""){
		alert("직급을 선택해주세요.")
		document.joinForm.memberRole.focus();
	}else{
		document.joinForm.submit();
	}
	
}
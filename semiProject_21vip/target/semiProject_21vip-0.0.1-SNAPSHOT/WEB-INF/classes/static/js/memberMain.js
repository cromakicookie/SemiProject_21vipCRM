/**
 * 
 */

 function openModal(username, memberName, memberEmail, memberBirth, memberDept, memberRole) {
        // 모달 열기 코드
       	$("#exampleModal").modal('show');
       	
        $('#username').val(username);
        $('#memberName').val(memberName);
        $('#memberEmail').val(memberEmail);
        $('#memberBirth').val(memberBirth);
        $('#memberDept').val(memberDept);
        $('#memberRole').val(memberRole);
        
		
 
    }

//모달 - 사원수정
function updateMember(){
	var username = $('#username').val();
	var memberEmail = $('#memberEmail').val();
	var memberBirth = $('#memberBirth').val();
	var memberDept = $('#memberDept').val();
	var memberRole = $('#memberRole').val();
	
	$.ajax({
		url:'member/updateMember',
		type:'POST',
		data: {username:username, 
			   memberEmail:memberEmail,
			   memberBirth:memberBirth,
			   memberDept:memberDept,
			   memberRole:memberRole
			   },
		success:function(){
			$("#exampleModal").modal('hide');
			location.replace(location.href);
		}
	})
	
        
}


//모달 - 사원 삭제
function deleteMember(username){
	 if (confirm('정말로 삭제하시겠습니까?')) {
        alert('삭제 완료되었습니다.');
        location.href = 'deleteMember?username=' + username;
    }
}

//사원 검색
function findMember(){
	var memberName = $('.memberName').val();
	var memberDept = $('.memberDept').val();
	var memberRole = $('.memberRole').val();
	
	$.ajax({
		url:'findByOption',
		type:'POST',
		data: {memberName:memberName, 
			   memberDept:memberDept,
			   memberRole:memberRole
			   },
		success:function(){
			
		}
	})
}


//엑셀 받기
function excelDownload(){
	var memberName = $('#excelButton').data('memberName');
	var memberDept = $('#excelButton').data('memberDept');
	var memberRole = $('#excelButton').data('memberRole');
	console.log(memberName, memberDept, memberRole);

	// AJAX 요청
	$.ajax({
 		type: 'GET',
		url: 'excel/download',
		data: {
			memberName: memberName,
			memberDept: memberDept,
			memberRole: memberRole
		},
		success: function(response) {
		// 성공 시 처리
			console.log(response);
		},
		error: function(error) {
		// 에러 시 처리
			console.log(error);
		}
	});
}


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




function updateMemo(){
	
}

function deleteMemo(){
	if (!confirm("정말 삭제하시겠습니까?")){
			
		}else{
			alert("확인(예)을 누르셨습니다.");
            //메모순번
            var memoRN = $("#memoNum").val();

            alert("Delete memo with ID: " + memoRN)
			
			$.ajax({
			url: "/deleteMemo/" + memoRN,
			type: "GET",
			success: function() {
				console.log("삭제 성공");
				location.reload();
				
			},
			error: function(error) {
				console.error('Error:', error);
			}
			});
		}
}



// 제이쿼리
$(document).ready(function () {
	
	$('#mSubmit').on("click",function(){
		
		var memoContent = document.getElementById('memo_content');
		
		if(!memoContent.value.trim()){
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
	 $(".check_important").change(function () {
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
	
	 $(".check_important").change(function () {
		 
		 var ck = 0;
		 
		 if(this.checked){
			 ck = 1;
			 console
		 }else{
			 ck = 0;
		 }
		 
		 
		 
		 
	 })
	  

})


            
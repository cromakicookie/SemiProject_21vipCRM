$(document).ready(function() {
	voucherUpdate(voucherCode);

})


function voucherUpdate() {
 	
  var voucherCode = $("#voucherCode_view").text();
  
  $.ajax({
	url: "/getIssuedVoucher/" + voucherCode,
	type: "Post",
	success: function(data) {
		
		$('.issued_voucherType').text(data.voucherType);
		$('.issued_voucherTheme').text(data.voucherService);
		$('.issued_voucherServiceName').text(data.voucherServiceName);
		$('.issued_voucherCode').text(data.checkedVoucherCode);
		$('.v_exdate').text(data.voucherExdate);
		
		
	},
	error: function(error) {
		console.error('Error:', error);
		 alert("수정 실패");
	}
	}); 
  
  alert("수정되었습니다.");
  window.close();
}

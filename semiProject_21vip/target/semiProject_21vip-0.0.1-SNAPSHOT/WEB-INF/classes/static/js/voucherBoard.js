function searchingVoucher(){

	$('#search_Voucher').submit();
	
}


function openModal() {
        // 모달 열기 코드
    
       	$("#vouchermodal").modal('show');
       	
        $('#voucher_type').val();
        $('#voucher_service').val();
        $('#voucher_serviceName').val();
        $('#voucher_count').val();

    }

function createVoucher(){
	var voucherType = $('#voucher_type').val();
	var voucherService = $('#voucher_service').val();
	var voucherServiceName = $('#voucher_serviceName').val();
	var voucherServiceCode = $('#voucher_serviceCode').val();
	var voucherCount = $('#voucher_count').val();

	
	$.ajax({
		url:'createVoucher',
		type:'POST',
		data: {voucherType:voucherType, 
			   voucherService:voucherService,
			   voucherServiceName:voucherServiceName,
			   voucherServiceCode:voucherServiceCode,
			   voucherCount:voucherCount
			   },
		success:function(){
			alert("바우처 생성 완료")
			$("#vouchermodal").modal('hide');
			location.replace(location.href);
		}
	})
	
        
}




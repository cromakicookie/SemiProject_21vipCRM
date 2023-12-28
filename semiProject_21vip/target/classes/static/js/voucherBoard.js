function searchingVoucher() {

	$('#search_Voucher').submit();

}


function openModal() {
	let voucherType = $('#voucher_type');

	// 모달 열기 코드
	$("#vouchermodal").modal('show');

	voucherType.on('change', function() {
		$("select#voucher_serviceName option").css('display', 'none');

		if (voucherType.val() === 'S') {
			$('.yacht').css('display', 'inline-block');
			$('.golf').css('display', 'inline-block');
			$('.hotel').css('display', 'inline-block');

		} else if (voucherType.val() === 'A') {
			$('.dining10').css('display', 'inline-block');
			$('.culture').css('display', 'inline-block');
			$('.spa').css('display', 'inline-block');
		} else if (voucherType.val() === 'B') {
			$('.dining7').css('display', 'inline-block');
			$('.cosmetic').css('display', 'inline-block');
			$('.exhibition').css('display', 'inline-block');
		} else if (voucherType.val() === 'G') {
			$('.dining10').css('display', 'inline-block');
			$('.cosmetic').css('display', 'inline-block');
			$('.exhibition').css('display', 'inline-block');
		} else {
			$("select#voucher_serviceName option").css('display', 'none');
		}

	})

}//모달오픈


function createVoucher() {
	let voucherType = $('#voucher_type').val();
	//테마명
	let voucherService = $('#voucher_service').val();
	let vs_userInput = $('#vs_userInput').val();
	//서비스명
	let voucherServiceName = $('#voucher_serviceName').val();
	let vsn_userInput = $('#vsn_userInput').val();
	//코드
	let voucherServiceCode = $('#voucher_serviceCode').val();
	//수량
	let voucherCount = $('#voucher_count').val();

	if (voucherType == "") {
		alert("타입을 지정해 주세요.");
		voucherType.focus();
	} else if (voucherService == "" && vs_userInput == "") {
		alert("테마명을 지정해 주세요.")
		voucherService.focus();
	} else if (voucherServiceName == "" && vsn_userInput == "") {
		alert("서비스 명을 지정해 주세요.")
		voucherServiceName.focus();
	} else if (voucherServiceCode == "") {
		alert("코드를 입력해 주세요")
		voucherServiceCode.focus();
	} else if (voucherCount == "") {
		alert("생성할 바우처의 개수를 입력해 주세요")
		voucherCount.focus();
	} else {

		$.ajax({
			url: 'createVoucher',
			type: 'POST',
			data: {
				voucherType: voucherType,
				voucherService: voucherService,
				vs_userInput: vs_userInput,
				voucherServiceName: voucherServiceName,
				vsn_userInput: vsn_userInput,
				voucherServiceCode: voucherServiceCode,
				voucherCount: voucherCount
			},
			success: function() {
				alert("바우처 생성 완료")
				$("#vouchermodal").modal('hide');
				location.replace(location.href);
			}
		})


	}



}




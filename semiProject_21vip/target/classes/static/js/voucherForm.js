/*voucherForm.js*/
//제이쿼리
$(document).ready(function() {
	updateSelectOptions();
})


function updateSelectOptions() {
	var typeSelect = document.getElementById('voucher_type');
	var themeSelect = document.getElementById('voucher_theme');
	var serviceSelect = document.getElementById('voucher_serviceName');

	// 선택된 voucher_type 값 가져오기
	var selectedType = typeSelect.value;



	// voucher_theme select 초기화
	themeSelect.innerHTML = '';

	// 선택된 voucher_type에 따라 옵션 추가
	if (selectedType === 'S') {
		$('#voucherCode1').val('S');
		addOption(themeSelect, '블랙서비스S', '블랙서비스S');

		if (themeSelect.value === '블랙서비스S') {
			addOption(serviceSelect, '프라이빗요트투어', '프라이빗요트투어');
			addOption(serviceSelect, '프라이빗골프레슨', '프라이빗요트투어');
			addOption(serviceSelect, '호텔패키지', '호텔패키지');

			if (serviceSelect.value === '프라이빗요트투어') {
				$('#voucherCode2').val('S');

			}

		}

		// 다른 옵션들 추가

	} else if (selectedType === 'A') {
		addOption(themeSelect, '블랙서비스A', '블랙서비스A');


		// 다른 옵션들 추가

	} else if (selectedType === 'B') {
		addOption(themeSelect, '블루서비스B', '블루서비스B');
		addOption(themeSelect, '자스민서비스B', '자스민서비스B');
		// 다른 옵션들 추가

	} else if (selectedType === 'G') {
		addOption(themeSelect, '생일바우처', '생일바우처');
		$('#voucherCode1').val('G')


	}
}

function addOption(select, value, text) {
	var option = document.createElement('option');
	option.value = value;
	option.text = text;
	select.add(option);
}


//사용버튼 누를시
function voucherSearch() {
	let voucherCode1 = $('#voucherCode1').val();
	let voucherCode2 = $('#voucherCode2').val();
	let voucherCode3 = $('#voucherCode3').val();

	var voucherCode = voucherCode1 + voucherCode2 + voucherCode3;
	console.log(voucherCode);

	/*G170b916ebc40001
	*/
	$.ajax({
		url: 'getVoucher',
		type: 'POST',
		data: { voucherCode: voucherCode },
		success: function(data) {
			if (!data) {
				alert("이미 발행된 바우처 입니다.");

			} else {
				alert("사용가능한 바우처 입니다.");
				$('.issued_voucherType').text(data.voucherType);
				$('.issued_voucherTheme').text(data.voucherService);
				$('.issued_voucherServiceName').text(data.voucherServiceName);
				$('.issued_voucherCode').text(data.voucherCode);
				$('.v_exdate').text(data.voucherEndDate);
				var voucherSeq = data.voucherSeq;
				var voucherCode = data.voucherCode;
				var voucherType = data.voucherType;
				var voucherService = data.voucherService;

				//등록버튼을 누를 시
				$(function() {
					$('.push').off('click').on('click', function() {

						$.ajax({
							url: 'issueVoucher',
							type: 'POST',
							data: { 
									voucherCode: voucherCode
									},
							success: function(result) {
								if (!result) {
									alert("바우처 등록을 실패하였습니다.");

								} else {
									alert("바우처가 등록되었습니다.")
									window.opener.location.reload(); // 부모 창 리로드
       								window.close(); // 현재 창 닫기

								}
							}
						})//ajax*/

					})
				})//function
			}
		}
	})//ajax

}




/*$.each(list,function(){})*/



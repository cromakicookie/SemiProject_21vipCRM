/*voucherForm.js*/

$(document).ready(function() {
	

})

function addServiceB(){
}


// 선택된 voucher 정보에 따라 옵션 추가 및 바우처코드 출력
function updateServiceOptions() {
		
	let typeSelect = $('#voucher_type');
	let themeSelect = $('#voucher_theme');
	let serviceSelect = $('#voucher_serviceName');
	
	let dining10 = "dbee6bc64e8";
	let dining7 = "0258685ad09";
	let cosmetic = "e9827b74700";
	let exhibition = "f99377de092";
	let spa = "cc91e646ffe";
	let culture = "3f7039a836c";
	let yacht = "423fa120a21";
	let golf = "c6cf642b8f1";
	let hotel = "e919c49d5f0";
	
	
	 themeSelect.empty();
	 serviceSelect.empty();

		if(typeSelect.val() === 'S') {
			
		    addOption(themeSelect, 'S', '블랙서비스S');
		    $('#voucherCode1').val('S');
		    
		    addOption(serviceSelect, '', '-서비스선택-');
		    addOption(serviceSelect, '프라이빗요트투어', '프라이빗요트투어');
			addOption(serviceSelect, '프라이빗골프레슨', '프라이빗골프레슨');
			addOption(serviceSelect, '호텔패키지', '호텔패키지');
			
			serviceSelect.on('change', function() {
 			// 실행 로직
				if (serviceSelect.val() === '프라이빗요트투어'){
					$('#voucherCode2').val(yacht) 
				}else if (serviceSelect.val() === '프라이빗골프레슨'){
					$('#voucherCode2').val(golf) 
				}else if (serviceSelect.val() === '호텔패키지'){
					$('#voucherCode2').val(hotel) 
				}
					
			});	
		    
		        
		}else if(typeSelect.val() === 'A') {
			addOption(themeSelect, '블루서비스A', '블루서비스A');
			$('#voucherCode1').val('A');
			
			addOption(serviceSelect, '', '-서비스선택-');
		    addOption(serviceSelect, '다이닝이용권 : 10만원', '다이닝이용권 : 10만원');
			addOption(serviceSelect, '문화센터이용권', '문화센터이용권');
			addOption(serviceSelect, '스파이용권', '스파이용권');
			
			serviceSelect.on('change', function() {
 			// 실행 로직
				if (serviceSelect.val() === '다이닝이용권 : 10만원'){
					$('#voucherCode2').val(dining10) 
				}else if (serviceSelect.val() === '문화센터이용권'){
					$('#voucherCode2').val(culture) 
				}else if (serviceSelect.val() === '스파이용권'){
					$('#voucherCode2').val(spa) 
				}
			});	
			
		}else if(typeSelect.val() === 'B') {		
			addOption(themeSelect, '레드서비스B', '레드서비스B');
			$('#voucherCode1').val('B');
			
			addOption(serviceSelect, '', '-서비스선택-');
		    addOption(serviceSelect, '다이닝이용권', '다이닝이용권');
			addOption(serviceSelect, '코스메틱상품권', '코스메틱상품권');
			addOption(serviceSelect, '전시회관람권', '전시회관람권');
			
			serviceSelect.on('change', function() {
 			// 실행 로직
				if (serviceSelect.val() === '다이닝이용권 : 7만원'){
					$('#voucherCode2').val(dining7) 
				}else if (serviceSelect.val() === '코스메틱상품권'){
					$('#voucherCode2').val(cosmetic) 
				}else if (serviceSelect.val() === '전시회관람권'){
					$('#voucherCode2').val(exhibition) 
				}
			});	
			

			
		}else if(typeSelect.val() === 'G') {
			
			addOption(themeSelect, '생일바우처', '생일바우처');
			$('#voucherCode1').val('G');
			
			addOption(serviceSelect, '', '-서비스선택-');
		    addOption(serviceSelect, '다이닝이용권 : 10만원', '다이닝이용권 : 10만원');
			addOption(serviceSelect, '코스메틱상품권', '코스메틱상품권');
			addOption(serviceSelect, '전시회관람권', '전시회관람권');
			
			serviceSelect.on('change', function() {
 			// 실행 로직
				if (serviceSelect.val() === '다이닝이용권 : 10만원'){
					$('#voucherCode2').val(dining10) 
				}else if (serviceSelect.val() === '코스메틱상품권'){
					$('#voucherCode2').val(cosmetic) 
				}else if (serviceSelect.val() === '전시회관람권'){
					$('#voucherCode2').val(culture) 
				}
			});	
			
		}else{
			console.log("아무것도 선택되지 않음")
		}
		
}//updateSelectOptions()

function addOption(select, value, text) {
    let option = $('<option>', {
        value: value,
        text: text
    });

    select.append(option);
}


function addServiceB(){

}


//사용버튼 누를시
function voucherSearch() {
	let voucherCode1 = $('#voucherCode1').val();
	let voucherCode2 = $('#voucherCode2').val();
	let voucherCode3 = $('#voucherCode3').val();

	var voucherCode = voucherCode1 + voucherCode2 + voucherCode3;
	console.log(voucherCode);

	$.ajax({
		url: 'getVoucher',
		type: 'POST',
		data: { voucherCode: voucherCode },
		success: function(data) {
			if (!data) {
				alert(data);
				alert("바우처 정보가 없습니다.");

			} else if(data.status == 1) {
				alert(data);
				alert("이미 발행된 바우처 입니다.");
				
			} else{
				alert("사용가능한 바우처 입니다.");
				$('.issued_voucherType').text(data.voucherType);
				$('.issued_voucherTheme').text(data.voucherService);
				$('.issued_voucherServiceName').text(data.voucherServiceName);
				$('.issued_voucherCode').text(data.voucherCode);
				$('.v_exdate').text(data.voucherEndDate);
				let voucherSeq = data.voucherSeq;
				let voucherCode = data.voucherCode;
				let voucherType = data.voucherType;
				let voucherService = data.voucherService;
				let voucherServiceName = data.voucherServiceName;

				//등록버튼을 누를 시
				$(function() {
					$('.push').off('click').on('click', function() {

						$.ajax({
							url: 'issueVoucher',
							type: 'POST',
							data: { 
									voucherCode: voucherCode,
									voucherType: voucherType,
									voucherService: voucherService,
									voucherServiceName: voucherServiceName
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
		
		},
		error: function(error) {
			alert("바우처 정보가 없습니다.");
			console.error('Error:', error);
		}
	})//ajax

}




/*$.each(list,function(){})*/



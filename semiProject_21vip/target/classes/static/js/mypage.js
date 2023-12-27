// 파일 넘버 
var dataNumber;
// 절대경로 확인
var serverPath = document.currentScript.getAttribute('data-custom');
/* # fullcalendar api */
var calendar; // 전역변수 선언.
$(document).ready(function() {
	var calendarEl = document.getElementById('calendar');

	calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
			left: 'prevYear,prev,next,nextYear today',
			center: 'title',
			right: 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		initialDate: '2023-12-06',
		navLinks: true,
		editable: true,
		dayMaxEvents: true,
		events: 'calendar/event/user',
		selectable: true,
		selectMirror: true,
		eventStartEditable: true,
		eventDurationEditable: true,
		select: function(arg) {
			$("#start").val(moment(arg.start).format('YYYY-MM-DD'));
			$("#end").val(moment(arg.end).format('YYYY-MM-DD'));
			$("#myModal").modal('show');
			calendar.unselect()
		},
		eventClick: function(arg) {
			console.log(arg.event.id);
			$.ajax({
				type: "GET",
				url: "calendar/event/" + arg.event.id,
				success: function(data) {
					console.log(data);
					let str = "";
					str += "<ul>";
					str += "<li>제목 : " + data.title + "</li>";
					str += "<li>번호 : <span id='dataId'>" + data.id + "</span></li>";
					str += "<li>일시 : " + data.start + " ~ " + data.end + "</li>";
					str += "<li>내용 : " + "</li>";
					str += "<li>" + data.eventContent + "</li>";
					str += "</ul>"

					$("#modal-body").html(str);
					let eventType = data.eventType;
					console.log(eventType);
					console.log(eventType.startsWith('U'));
					if (!eventType.startsWith('U')) {
						$('#changeButton').attr("style", "display:none;");
						$('#chaegeButtonC').attr("style", "display:none;");
						$('#chaegeButtonB').attr("style", "display:none;");
					} else {
						$('#changeButton').removeAttr("style");
						$('#chaegeButtonC').removeAttr("style");
						$('#chaegeButtonB').attr("style", "display:none;");
					}
				},
				error: function(error) {
					console.error('Error:', error);
				}
			})
			$("#myModal2").modal('show');
		}
	});
	calendar.render();
	$("#password").on('input', function() {
		checkPw();
	})

	var clickCount = 0;

	// 대상 요소에 클릭 이벤트 리스너를 등록합니다.
	$("#colorButton").on("click", function() {
		init();
		// 클릭 횟수를 증가시킵니다.
		clickCount++;

		// 두 번째 클릭일 경우 스타일 변경 코드를 실행합니다.
		if (clickCount === 2) {
			// 스타일 변경 코드
			$("#box").hide();
			clickCount = 0;
		}
	});
});


function submitForm() {
	$("#modalForm").submit();
}



function changeModal() {
	let number = $("#dataId").text();
	console.log(number);
	$("#modal-title").html("일정 수정");
	$.get('calendar/event/' + number, function(data) {
		let str = "";
		str += "<form id='modalForm2'>"
		str += "<ul>";
		str += "<li>제목 : <input type='text' name='title' value='" + data.title + "'/></li>";
		str += "<li>번호 : <input type='text' name='id' value='" + data.id + "' readonly/></li>";
		str += "<li>일시 : <input type='date' name='start' value='" + data.start + "' pattern='\d{4}-\d{2}-\d{2}'/> ~ <input type='date' name='end' value='" + data.end + "' pattern='\d{4}-\d{2}-\d{2}'/> </li>";
		str += "<li>내용 : </li>";
		str += "<li style='display: none;'><input type='text' value='" + data.eventType + "' name='eventType'/></li>";
		str += "<li><textarea cols='55' rows='5' name='eventContent'>" + data.eventContent + "</textarea></li>";
		str += "<li style='display: none;'><input type='text' id='colorId2' name='color' value=" + data.color + "></li>"
		str += "<li><button type='button' id='colorButton2' class='btn btn-primary' style='background-color:" + data.color + "'>색 변경</button></li>"
		str += "</ul>"
		str += "</form>"
		str += "<div id='box2' class='box' style='display: none;'>"
		str += "<div id='palletBox2' class='pallet'>"
		str += "</div>"
		str += "</div>"
		$("#modal-body").html(str);


		$('#changeButton').attr("style", "display:none;");
		$('#chaegeButtonB').removeAttr("style");
		var clickCount = 0;
		$("#colorButton2").on("click", function() {
			init2();
			// 클릭 횟수를 증가시킵니다.
			clickCount++;
			console.log(clickCount);
			// 두 번째 클릭일 경우 스타일 변경 코드를 실행합니다.
			if (clickCount === 2) {
				// 스타일 변경 코드
				$("#box2").hide();
				clickCount = 0;
			}
		});
	});

}

function modalSubmit() {
	var form = {};
	$("#modalForm2").serializeArray().forEach(function(item) {
		form[item.name] = item.value;
	});
	console.log(form);

	$.ajax({
		url: "calendar/event/update",
		type: "PUT",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(form),
		success: function() {
			console.log("수정 성공");
			$("#myModal2").modal('hide');
			calendar.refetchEvents();
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
}


function deleteCal() {
	let number = $("#dataId").text();
	console.log(number);
	$.ajax({
		url: "calendar/event/" + number,
		type: "DELETE",
		success: function() {
			console.log("삭제 성공");
			$("#myModal2").modal('hide');
			calendar.refetchEvents();
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
}


function handleFileUpload(files) {
	/* 선택된 파일들(files)을 서버로 업로드하거나 다른 작업을 수행할 수 있음 */
	console.log(files);
	var formData = new FormData($('#uploadForm')[0]);

	/* 여기서는 예제로 선택된 첫 번째 파일의 정보를 출력 */
	if (files.length > 0) {
		console.log('Selected File:', files[0].name);
		console.log('File Type:', files[0].type);
		console.log('File Size:', files[0].size, 'bytes');
		$.ajax({
			url: "file/upload",
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				console.log("업로드 성공");
				dataNumber = data;
				console.log(dataNumber);

				getFile();
			},
			error: function(error) {
				console.error('Error:', error);
			}
		});
	}


}

function getFile() {
	console.log("파일 가져오기");
	$.ajax({
		type: "GET",
		url: "file/" + dataNumber,
		success: function(data) {
			console.log(data);
			$("#uploadImage").attr("src", serverPath+data.fileRoot + data.fileName);
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
};

function checkPw() {
	const pwRegex = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d]{8,}$/;
	var pw = $("#password").val();

	function displayNone() {
		$('#pwError').css("display", "none");
		$('#pwOk').css("display", "none");
	}

	if (!pwRegex.test(pw)) {
		displayNone();
		$('#pwError').css("display", "block");
		$("#password").focus();
	} else {
		displayNone();
		$('#pwOk').css("display", "block");
	}
}

function mySubmit() {
	var password = $("#password").val();
	var email = $("#memberEmail").val();
	if (password.trim() === '') {
		alert('비밀번호를 입력해주세요');
		$("#password").focus();
	} else if (email.trim() === '') {
		alert("이메일을 입력해주세요");
		$("#memberEmail").focus();
	} else {
		var formData = new FormData($('#userUpdateForm')[0]);
		$.ajax({
			type: "POST",
			url: "file/upload/user",
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				alert("수정되었습니다.");
				$("#memberEmail").attr("value", data.memberEmail);
				$('#pwOk').css("display", "none");
			},
			error: function(error) {
				console.error('Error:', error);
			}
		})
	}
}


function init() {
	//2차원 배열 파레트 데이터
	var pallet = [["#FF0000", "#FF5E00", "#FFBB00", "#FFE400", "#ABF200", "#1DDB16", "#00D8FF", "#0054FF", "#0100FF", "#5F00FF", "#FF00DD", "#FF007F", "#000000", "#FFFFFF"],
	["#FFD8D8", "#FAE0D4", "#FAECC5", "#FAF4C0", "#E4F7BA", "#CEFBC9", "#D4F4FA", "#D9E5FF", "#DAD9FF", "#E8D9FF", "#FFD9FA", "#FFD9EC", "#F6F6F6", "#EAEAEA"],
	["#FFA7A7", "#FFC19E", "#FFE08C", "#FAED7D", "#CEF279", "#B7F0B1", "#B2EBF4", "#B2CCFF", "#B5B2FF", "#D1B2FF", "#FFB2F5", "#FFB2D9", "#D5D5D5", "#BDBDBD"],
	["#F15F5F", "#F29661", "#F2CB61", "#E5D85C", "#BCE55C", "#86E57F", "#5CD1E5", "#6799FF", "#6B66FF", "#A566FF", "#F361DC", "#F361A6", "#A6A6A6", "#8C8C8C"],
	["#CC3D3D", "#CC723D", "#CCA63D", "#C4B73B", "#9FC93C", "#47C83E", "#3DB7CC", "#4374D9", "#4641D9", "#8041D9", "#D941C5", "#D9418C", "#747474", "#5D5D5D"],
	["#980000", "#993800", "#997000", "#998A00", "#6B9900", "#2F9D27", "#008299", "#003399", "#050099", "#3F0099", "#990085", "#99004C", "#4C4C4C", "#353535"],
	["#670000", "#662500", "#664B00", "#665C00", "#476600", "#22741C", "#005766", "#002266", "#030066", "#2A0066", "#660058", "#660033", "#212121", "#191919"]];
	var tag = "";
	for (i = 0; i < pallet.length; i++) {
		for (j = 0; j < pallet[i].length; j++) {
			tag += "<div id=" + pallet[i][j] + " class='colorBox' onclick='colorSet(this)'></div>";
		}
	}
	//파레트 파싱
	document.getElementById("palletBox").innerHTML = tag;
	$("#box").attr("style", "block");
	//색상 입히기
	var colorBox = document.getElementsByClassName("colorBox");
	for (i = 0; i < colorBox.length; i++) {
		colorBox[i].style.background = colorBox[i].id;
	}

}
function init2() {
	//2차원 배열 파레트 데이터
	var pallet = [["#FF0000", "#FF5E00", "#FFBB00", "#FFE400", "#ABF200", "#1DDB16", "#00D8FF", "#0054FF", "#0100FF", "#5F00FF", "#FF00DD", "#FF007F", "#000000", "#FFFFFF"],
	["#FFD8D8", "#FAE0D4", "#FAECC5", "#FAF4C0", "#E4F7BA", "#CEFBC9", "#D4F4FA", "#D9E5FF", "#DAD9FF", "#E8D9FF", "#FFD9FA", "#FFD9EC", "#F6F6F6", "#EAEAEA"],
	["#FFA7A7", "#FFC19E", "#FFE08C", "#FAED7D", "#CEF279", "#B7F0B1", "#B2EBF4", "#B2CCFF", "#B5B2FF", "#D1B2FF", "#FFB2F5", "#FFB2D9", "#D5D5D5", "#BDBDBD"],
	["#F15F5F", "#F29661", "#F2CB61", "#E5D85C", "#BCE55C", "#86E57F", "#5CD1E5", "#6799FF", "#6B66FF", "#A566FF", "#F361DC", "#F361A6", "#A6A6A6", "#8C8C8C"],
	["#CC3D3D", "#CC723D", "#CCA63D", "#C4B73B", "#9FC93C", "#47C83E", "#3DB7CC", "#4374D9", "#4641D9", "#8041D9", "#D941C5", "#D9418C", "#747474", "#5D5D5D"],
	["#980000", "#993800", "#997000", "#998A00", "#6B9900", "#2F9D27", "#008299", "#003399", "#050099", "#3F0099", "#990085", "#99004C", "#4C4C4C", "#353535"],
	["#670000", "#662500", "#664B00", "#665C00", "#476600", "#22741C", "#005766", "#002266", "#030066", "#2A0066", "#660058", "#660033", "#212121", "#191919"]];
	var tag = "";
	for (i = 0; i < pallet.length; i++) {
		for (j = 0; j < pallet[i].length; j++) {
			tag += "<div id=" + pallet[i][j] + " class='colorBox' onclick='colorSet2(this)'></div>";
		}
	}
	//파레트 파싱
	document.getElementById("palletBox2").innerHTML = tag;
	$("#box2").attr("style", "block");
	//색상 입히기
	var colorBox = document.getElementsByClassName("colorBox");
	for (i = 0; i < colorBox.length; i++) {
		colorBox[i].style.background = colorBox[i].id;
	}

}

// onclick event
function colorSet(target) {
	$("#colorButton").attr("style", "background-color:" + target.id + ";");
	$("#colorId").attr("value", target.id);
	if (beforeColor != undefined && beforeColor != null) {
		document.getElementById(beforeColor).className = document.getElementById(beforeColor).className.replace(" active", "");
	}
	document.getElementById(target.id).className += " active";
	beforeColor = target.id;
}
// onclick event
function colorSet2(target) {
	$("#colorButton2").attr("style", "background-color:" + target.id + ";");
	$("#colorId2").attr("value", target.id);
	if (beforeColor != undefined && beforeColor != null) {
		document.getElementById(beforeColor).className = document.getElementById(beforeColor).className.replace(" active", "");
	}
	document.getElementById(target.id).className += " active";
	beforeColor = target.id;
}



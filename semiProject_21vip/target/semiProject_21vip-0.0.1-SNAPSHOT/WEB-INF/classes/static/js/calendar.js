/*
 * 캘린더.js
 */

// 유저 권한 객체
var userRole = document.currentScript.getAttribute('data-custom1');
// 절대경로 확인
var serverPath = document.currentScript.getAttribute('data-custom2');
// 컬러 선택 객체 
var beforeColor;
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
		events: 'calendar/event/all',
		selectable: true,
		selectMirror: true,
		eventStartEditable: true,
		eventDurationEditable: true,
		select: function(arg) {
			if (userRole != 'ROLE_MEMBER') {
				$("#start").val(moment(arg.start).format('YYYY-MM-DD'));
				$("#end").val(moment(arg.end).format('YYYY-MM-DD'));
				$("#myModal").modal('show');
				calendar.unselect()
			} else {
				alert("일정 추가가 불가합니다.");
			}
		},
		eventClick: function(arg) {
			console.log(arg.event.id);
			$.ajax({
				type: "GET",
				url: "calendar/event/" + arg.event.id,
				success: function(data) {
					console.log(data);
					let str = "";
					if (data.file != null) {
						str += "<img src='"+ data.file.fileRoot + data.file.fileName + "' >";
					}
					str += "<ul>";
					str += "<li>제목 : " + data.title + "</li>";
					str += "<li>번호 : <span id='dataId'>" + data.id + "</span></li>";
					str += "<li>일시 : " + data.start + " ~ " + data.end + "</li>";
					str += "<li>타입 : " + data.eventType + "</li>";
					str += "<li>내용 : " + "</li>";
					str += "<li>" + data.eventContent + "</li>";
					str += "</ul>"


					$("#card-text").html(str);
				},
				error: function(error) {
					console.error('Error:', error);
				}
			})
		}
	});
	calendar.render();

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
	var form = $("#modalForm");
	form.submit();
}



function showModal() {
	let number = $("#dataId").text();
	console.log(number);
	if (number == "") {
		alert("선택된 일정이 없습니다.");
	} else {
		console.log(number);
		$("#modal-title").html("일정 수정");
		$.get('calendar/event/' + number, function(data) {
			let str = "";
			str += "<form id='modalForm2' enctype='multipart/form-data'>"
			str += "<ul>";
			str += "<li>제목 : <input type='text' name='title' value='" + data.title + "'/></li>";
			str += "<li>번호 : <input type='text' name='id' value='" + data.id + "' readonly/></li>";
			str += "<li>일시 : <input type='date' name='start' value='" + data.start + "' pattern='\d{4}-\d{2}-\d{2}'/> ~ <input type='date' name='end' value='" + data.end + "' pattern='\d{4}-\d{2}-\d{2}'/> </li>";
			str += "<li>내용 : </li>";
			str += "<li style='display: none;'><input type='text' value='" + data.eventType + "' name='eventType'/></li>";
			str += "<li><textarea cols='55' rows='5' name='eventContent'>" + data.eventContent + "</textarea></li>";
			str += "<li><input type='file' id='uploadFiles' name='uploadFiles'></li>"
			str += "<li style='display: none;'><input type='text' id='colorId2' name='color' value=" + data.color + "></li>"
			str += "<li><button type='button' id='colorButton2' class='btn btn-primary' style='background-color:" + data.color + "'>색 변경</button></li>"
			str += "</ul>"
			str += "</form>"
			str += "<div id='box2' class='box' style='display: none;'>"
			str += "<div id='palletBox2' class='pallet'>"
			str += "</div>"
			str += "</div>"

			$("#modal-body").html(str);
			$("#myModal2").modal('show');
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
}

function modalSubmit() {
	var formData = new FormData($("#modalForm2")[0]);
	console.log(formData);

	$.ajax({
		url: "calendar/event",
		type: "PUT",
		data: formData,
		contentType: false,
		processData: false,
		success: function() {
			console.log("수정 성공");
			$("#myModal2").modal('hide');
			calendar.refetchEvents();
			var str = "지정된 데이터가 없습니다.";
			$("#card-text").html(str);
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});

}


function deleteCal() {
	let number = $("#dataId").text();
	if (number == "") {
		alert("선택된 일정이 없습니다.");
	} else {
		var confirmDelete = confirm("삭제하시겠습니까?");

		console.log(number);
		if (confirmDelete) {
			$.ajax({
				url: "calendar/event/" + number,
				type: "DELETE",
				success: function() {
					console.log("삭제 성공");
					$("#myModal2").modal('hide');
					calendar.refetchEvents();
					var str = "지정된 데이터가 없습니다.";
					$("#card-text").html(str);
				},
				error: function(error) {
					console.error('Error:', error);
				}
			});
		}
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


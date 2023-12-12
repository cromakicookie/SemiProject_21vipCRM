// 파일 넘버 
var dataNumber;
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
		events: '/calendar/event/user',
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
				url: "/calendar/event/" + arg.event.id,
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
					if (data.file != null) {
						str += "<img src='" + data.file.fileRoot + "' >";
					}

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


	/*	// 이미지를 로드할 때 호버 이미지 경로로 변경
		document.getElementById('uploadImage').addEventListener('mouseover', function() {
			this.src = '/img/mypage_icon.png';
		});
	
		// 이미지에서 마우스가 벗어날 때 기본 이미지 경로로 변경
		document.getElementById('uploadImage').addEventListener('mouseout', function() {
	
			this.src = '';
		});
	*/
});


function submitForm() {
	$("#modalForm").submit();
}



function changeModal() {
	let number = $("#dataId").text();
	console.log(number);
	$("#modal-title").html("일정 수정");
	$.get('/calendar/event/' + number, function(data) {
		let str = "";
		str += "<form id='modalForm2'>"
		str += "<ul>";
		str += "<li>제목 : <input type='text' name='title' value='" + data.title + "'/></li>";
		str += "<li>번호 : <input type='text' name='id' value='" + data.id + "' readonly/></li>";
		str += "<li>일시 : <input type='date' name='start' value='" + data.start + "' pattern='\d{4}-\d{2}-\d{2}'/> ~ <input type='date' name='end' value='" + data.end + "' pattern='\d{4}-\d{2}-\d{2}'/> </li>";
		str += "<li>내용 : </li>";
		str += "<li style='display: none;'><input type='text' value='" + data.eventType + "' name='eventType'/></li>";
		str += "<li><textarea cols='55' rows='5' name='eventContent'>" + data.eventContent + "</textarea></li>";
		str += "</ul>"
		str += "</form>"
		if (data.file != null) {
			str += "<img src='" + data.file.fileRoot + "' >";
		}
		$("#modal-body").html(str);


		$('#changeButton').attr("style", "display:none;");
		$('#chaegeButtonB').removeAttr("style");

	});

}

function modalSubmit() {
	var form = {};
	$("#modalForm2").serializeArray().forEach(function(item) {
		form[item.name] = item.value;
	});
	console.log(form);

	$.ajax({
		url: "/calendar/event",
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
		url: "/calendar/event/" + number,
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


function handleFileUpload(files, callback) {
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
				console.log(data);
				
				if (typeof callback === 'function') {
					console.log("콜백")
					callback();
				}
			},
			error: function(error) {
				console.error('Error:', error);
			}
		});
	}


}

handleFileUpload(myFiles, function() {
	console.log("파일 가져오기");
	$.ajax({
		type: "GET",
		url: "/file/" + dataNumber,
		success: function(data) {
			console.log(data);
			$("#uploadImage").attr("src", data.fileRoot + "\\" + data.fileName);
		},
		error: function(error) {
			console.error('Error:', error);
		}
	});
});


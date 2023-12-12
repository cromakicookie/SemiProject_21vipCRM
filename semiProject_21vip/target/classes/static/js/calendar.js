/**
 * 
 */
// 유저 권한 객체
var userRole = document.currentScript.getAttribute('data-custom');

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
		events: '/calendar/event/all',
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
				alert("일정 수정이 불가합니다.");
			}
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
					str += "<li>타입 : " + data.eventType + "</li>";
					str += "<li>내용 : " + "</li>";
					str += "<li>" + data.eventContent + "</li>";
					str += "</ul>"
					if (data.file != null) {
						str += "<img src='" + data.file.fileRoot + "' >";
					}

					$("#card-text").html(str);
				},
				error: function(error) {
					console.error('Error:', error);
				}
			})
		}
	});
	calendar.render();
});


function submitForm() {
	$("#modalForm").submit();
}



function showModal() {
	let number = $("#dataId").text();
	console.log(number);
	if (number == "") {
		alert("선택된 일정이 없습니다.");
	} else {
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
			$("#myModal2").modal('show');

		});
	}
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
	var confirmDelete = confirm("삭제하시겠습니까?");
	let number = $("#dataId").text();
	console.log(number);
	if (confirmDelete) {
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

}


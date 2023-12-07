/**
 * 
 */
/* # fullcalendar api */
$(document).ready(function() {
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
			left: 'prevYear,prev,next,nextYear today',
			center: 'title',
			right: 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		initialDate: '2023-12-06',
		navLinks: true, // can click day/week names to navigate views
		editable: true,
		dayMaxEvents: true, // allow "more" link when too many events
		events: '/calendar/event',
		selectable: true,
		selectMirror: true,
		eventStartEditable: true,
		eventDurationEditable: true,
		eventDataTransform: function(data) {

		},
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
					str += "<li>번호 : " + data.id + "</li>";
					str += "<li>일시 : " + data.start + " ~ " + data.end + "</li>";
					str += "<li>타입 : " + data.eventType + "</li>";
					str += "<li>내용 : " + "</li>";
					str += "<li>" + data.eventContent + "</li>";
					str += "</ul>"
					if(data.file != null){
						str+="<img src='"+data.file.fileRoot+"' >";
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



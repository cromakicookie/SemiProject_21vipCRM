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
		eventDataTransform: function(data){
			
		},
		select: function(arg) {
			$("#start").val(arg.start);
			$("#end").val(arg.end);
			$("#myModal").modal('show');
			calendar.unselect()
		},
		eventClick: function(arg) {
			console.log(arg.event.id);
			$.ajax({
				type: "GET",
				url: "/calendar/event/" + arg.event.id
			})
		}
	});
	calendar.render();
});


function submitForm() {
	$("#modalForm").submit();
}

// 서버에서 받은 데이터를 JavaScript 변수로 가져오기
    var calendarData = /*[(${selectCal})]*/ null;

    // 데이터를 이용하여 필요한 작업 수행
    if (calendarData !== null) {
        // 예제: 콘솔에 데이터 출력
        console.log('Received data:', calendarData);
    }


var startIndex = 0; // 시작 인덱스 초기화
var itemsPerPage = 3; // 페이지당 표시할 항목 수

document.addEventListener('DOMContentLoaded', function() {
    showMemoRange(startIndex, itemsPerPage);
});

function showMemoRange(start, count) {
    var table = document.getElementById('tableMemo');
    var tbody = table.getElementsByTagName('tbody')[0];
    var rows = tbody.getElementsByTagName('tr');

    // 표시할 항목의 범위 설정
    for (var i = 0; i < rows.length; i++) {
        if (i >= start && i < start + count) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }

    // 더 이상 표시할 행이 없으면 "더보기" 링크 숨기기
    if (start + count >= rows.length) {
        document.getElementById('moreList').style.display = 'none';
    }
}

//더보기 버튼 누르면 표시할 항목 수 늘어남
function moreMemo() {
        itemsPerPage += itemsPerPage;
        showMemoRange(startIndex, itemsPerPage);
    }




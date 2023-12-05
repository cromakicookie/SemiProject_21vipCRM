$(".voucherForm").submit(function () {
  if (!$("user_id").val()) {
    $(".msg_1").css("display", "block");
    $("user_id").focus();
    return false;
  } else {
    $(".msg_1").css("display", "none");
  }

  if (!$("user_pw").val()) {
    $(".msg_2").css("display", "block");
    $("user_pw").focus();
    return false;
  }
});

function voucherInsert() {
  // 폼 제출 로직 추가
  alert("등록되었습니다.");
  window.close();
}

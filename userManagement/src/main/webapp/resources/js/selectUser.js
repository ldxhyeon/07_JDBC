const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const goToList = document.querySelector("#goToList");

goToList.addEventListener("click", () => {
  location.href = "/selectAll"; // 목록페이지 요청
});

deleteBtn.addEventListener("click", () => {

  // confrim을 이용해서 삭제할지 확인
  if( !confirm("삭제하시겠습니까?")) { // 취소 클릭시
    return;
  }

  // form태그, input태그 생성 후 body 제일 밑에 추가해 submit하기

  // from 요소 생성
  const deleteForm = document.createElement("form");

  // 요청 주소 설정
  deleteForm.action = "/deleteUser";

  // 데이터 전달 방식 설정
  deleteForm.method = "POST";

  // input 요소 생성
  const input = document.createElement("input");
  
  // input을 form에 자식으로 추가
  deleteForm.append(input);

  // input type, name, value 설정
  input.type = "hidden";
  input.name = "userNo";

  const userNoTo = document.querySelector("#userNoTd");
  input.value = userNoTo.innerText;

  // body 태그 제일 마지막에 form 추가
  document.querySelector("body").append(deleteForm);

  // deleteForm 제출하기
  deleteForm.submit();

});

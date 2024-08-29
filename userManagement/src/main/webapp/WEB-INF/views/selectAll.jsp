<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>html문서 제목</title>
</head>
<body>

  <table border="1">
    <thead>
      <th>회원번호</th>
      <th>아이디</th>
      <th>비밀번호</th>
      <th>이름</th>
      <th>등록일</th>
    </thead>

    <%-- DTO에 있는 필드명 그대로 사용해야한다. --%>
    <c:forEach items="${userList}" var="num">
      <tbody>
        <th>${num.userNo}</th>
        <th>${num.userId}</th>
        <th>${num.userPw}</th>
        <th>${num.userName}</th>
        <th>${num.enrollDate}</th>
      </tbody>
    </c:forEach>
    
  </table>


</body>
</html>
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

  <c:forEach items="${userList}" var="user">
    <tbody>
      <th>${user.userNo}</th>
      <th>${user.userId}</th>
      <th>${user.userPw}</th>
      <th>${user.userName}</th>
      <th>${user.enrollDate}</th>
    </tbody>
    </c:forEach>

  </table>
</body>
</html>
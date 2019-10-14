<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Страница выписанных пациентов</title>
</head>

<style>
   .b1 {
    border: 1px solid green;
    margin: auto; 
   }
   td {
    text-align: center; 
   }
   }
  </style>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"	media="screen" />
</head>
<body>
	<table class="b1">
		<thead>
		</thead>
		<caption>Выписанные пациенты</caption>
		<thead>
			<tr>
				<th>Имя пациента</th>
				<th>Дата рождения</th>
				<th>Место жительства</th>
				<th>Телефон</th>
			</tr>
		</thead>
		<c:forEach items="${PatientDischanged}" var="plist">
			<tr>
				<td>${plist.name}</td>
				<td>${plist.data}</td>
				<td>${plist.adress}</td>
				<td>${plist.telephone}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
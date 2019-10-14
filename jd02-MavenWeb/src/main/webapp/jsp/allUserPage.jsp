<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Персонал</title>
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
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"
	media="screen" />
</head>

<body>
<table class="b1">
		<thead>
		</thead>
		<caption>Персонал</caption>
		<tr>
			<th>Имя</th>
			<th>Специализация</th>
			<th>Должность</th>
		</tr>
		<c:forEach items="${DoctorList}" var="dlist">
			<tr>
				<td>${dlist.name}</td>
				<td>${dlist.specialization}</td>
				<td>${dlist.position}</td>
			</tr>
		</c:forEach>
	</table>
	<table class="b1">
	<tr>
				<c:if test="${UserSession.position eq 'заведующий' && requestScope.DoctorDissmisList == null}">
					<td colspan="2" style="text-align: center">
							<form action="controller" method="post">
								<input type="hidden" name="command"
									value="user_dissmisal_list"> 
									<input type="submit" value="Уволенные сотрудники">
							</form>				
						</td>

				</c:if>
				<c:if test="${UserSession.position eq 'заведующий' && requestScope.DoctorDissmisList != null }">
				<caption>Бывшие сотрудники</caption>
				<tr>
					<th>Имя</th>
					<th>Специализация</th>
					<th>Должность</th>
				</tr>
				<c:forEach items="${DoctorDissmisList}" var="dissmislist">
					<tr>
						<td>${dissmislist.name}</td>
						<td>${dissmislist.specialization}</td>
						<td>${dissmislist.position}</td>
					</tr>
				</c:forEach>

			</c:if>
			</tr>
			</table>
</body>
</html>
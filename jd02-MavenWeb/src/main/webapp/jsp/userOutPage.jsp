<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="list" type="java.util.List<by.htp.entity.Doctor>"
	scope="request" />
<body>

	<table border="1">
		<caption>Кадровые изминения</caption>
		<thead>
			<tr>
				<th>ФИО</th>
				<th>Специализация</th>
				<th>Увольнение</th>
			</tr>
		</thead>

		<c:forEach items="${list}" var="currentDoctorList">
			<tr>
				<c:if
					test="${not fn:containsIgnoreCase(currentDoctorList.getPosition(), 'медсестра')  }">
					<td><c:out value="${currentDoctorList.getName()}" /></td>
					<td><c:out value="${currentDoctorList.getSpecialization()}" /></td>
					<td> <form name="command" action="controller" method="post">
					<input type="hidden" name="command" value="dismissal" />	
						<input type="hidden" name="userId" value="${currentDoctorList.id}" /> <input
							type="submit" value="Уволить">
							</form></td>
				</c:if>
			</tr>
		</c:forEach>
</body>
</html>
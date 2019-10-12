<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Личная карточка пациента</title>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css" media="screen" />
</head>
<jsp:useBean id="Medications" beanName="by.htp.entity.Medication"
	type="by.htp.entity.Medication" scope="request" />

<body>
<jsp:include page="patientTable.jsp"></jsp:include>

	<table border="1">
		<caption>Назначенные процедуры/лекарства/операции</caption>

		<tr>
			<th>Процедуры</th>
	
		
			<c:set var="map" value="${Medications.getProcedurs().values()} " />
			<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
				var="procedurs">
				<td><c:out value="${procedurs}" /></td>
			</c:forEach>
			</tr>
		</table>
		<table>
		<tr>
			<th>Лекарства</th>
		
			<c:set var="map" value="${Medications.getMedications().values()} " />
			<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
				var="medications">
				<td><c:out value="${medications}" /></td>
			</c:forEach>
		</tr>
		</table>
		<table>
		<tr>
			<th>Операции</th>
	
			<c:set var="map" value="${Medications.getOperations().values()} " />
			<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
				var="operations">
				<td><c:out value="${operations}" /></td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locLine.patient.new.entering" var="entering_new" />	
<fmt:message bundle="${loc}" key="local.locLine.patient.page.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.locLine.done" var="done" />	
<fmt:message bundle="${loc}" key="local.locLine.user.page.fio" var="fio" />
<fmt:message bundle="${loc}" key="local.locLine.patient.date" var="birth_date" />
<fmt:message bundle="${loc}" key="local.locLine.patient.location" var="location" />
<fmt:message bundle="${loc}" key="local.locLine.patient.telephone" var="telephone" />
<fmt:message bundle="${loc}" key="local.locLine.patient.new.passport" var="passport" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${entering_new}</title>
<link rel="stylesheet" type="text/css" href="../css/styleAccaunt.css"
	media="screen" />
</head>
<body>
<%-- <form action="../controller" method="post">
		<input type="hidden" name="language" value="add_new_patient" /> <input
			type="hidden" name="local" value="ru" /> <input type="submit"
			value="${ru_button}">
	</form>
	<form action="../controller" method="post">
		<input type="hidden" name="language" value="add_new_patient" /> <input
			type="hidden" name="local" value="en" /> <input type="submit"
			value="${en_button}">
	</form> --%>
	<table border="1">
		<caption>${entering_new}</caption>
		<thead>
			<tr>
				
				<th>${photo}</th>
			</tr>
		</thead>
		<tr>
			
		  
			<c:if test="${requestScope.base64Image == null }">
			<td>
			<form action="../controller" method="post" enctype="multipart/form-data">
			<input type="file" name="add_photo" />
			<input type="hidden" name="command" value="add_Photo"/>
			<input	type="submit" value="${done}">
			</form>
			</td>
			</c:if>
			<c:if test="${requestScope.base64Image != null }">
			<td><img src="data:image/jpg;base64,${requestScope.base64Image}" width="140" height="200" />
			 
			</td>
			</c:if>
			</tr>

	</table>
	<form action="controller" method="post" >
	<%-- <form action="../controller" method="post" > --%>
	<table border="1">
		<caption>${entering_new}</caption>
		<thead>
			<tr>
				<th>${fio}</th>
				<th>${passport}</th>
				<th>${birth_date}</th>
				<th>${location}</th>
				<th>${telephone}</th>
			</tr>
		</thead>
		<tr>
			<td>			
			<input type="text" name="bigName" value=""></td>
			<td><input type="text" name="passportNumber" value=""></td>
			<td><input type="text" name="dataOfBirth" value=""></td>
			<td><input type="text" name="plase" value=""></td>
			<td><input type="text" name="telephone" value="">
			</td>
			
			</table>
			<input type="hidden" name="command" value="add_new_patient"/>
		<input	type="submit" value="${done}">
		
	</form>
	
</body>
</html>
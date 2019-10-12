<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Пациент добавлен</title>
</head>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"
	media="screen" />
<body>
<table border="1">
	<caption>Пациент успецно зарегистрирован с данными:</caption>
		<thead>
			<tr>
				<th>Имя пациента</th>
				<th>Дата рождения</th>
				<th>Место жительства</th>
				<th>Телефон</th>
			</tr>
		</thead>
		<tr>
			<jsp:useBean id="Patient" class="by.htp.entity.Patient"
				type="java.lang.Object" scope="request" />
		<td>	<jsp:getProperty property="name" name="Patient" /> </td>
		<td>	<jsp:getProperty property="data" name="Patient" /></td>
		<td>	<jsp:getProperty property="adress" name="Patient" /></td>
		<td>	<jsp:getProperty property="telephone" name="Patient" /></td>
		</tr>
	</table>
	<form action="controller" method="get">
	<input size=300	type="submit" value="Назад" >
	</form>
</body>
</html>
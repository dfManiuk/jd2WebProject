<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Страница добавления пациента</title>
<link rel="stylesheet" type="text/css" href="../css/styleAccaunt.css"
	media="screen" />
</head>
<body>

	<table border="1">
		<caption>Ввод данных нового пациента</caption>
		<thead>
			<tr>
				
				<th>Фото</th>
			</tr>
		</thead>
		<tr>
			
		  
			<c:if test="${requestScope.base64Image == null }">
			<td>
			<form action="../controller" method="post" enctype="multipart/form-data">
			<input type="file" name="add_photo" />
			<input type="hidden" name="command" value="add_Photo"/>
			<input	type="submit" value="Применить">
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
	<table border="1">
		<caption>Ввод данных нового пациента</caption>
		<thead>
			<tr>
				<th>Фамилия Имя Отчество</th>
				<th>Номер паспорта</th>
				<th>Дата рождения</th>
				<th>Место жительства</th>
				<th>Контактный телефон</th>
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
		<input	type="submit" value="Применить">
		
	</form>
	
</body>
</html>
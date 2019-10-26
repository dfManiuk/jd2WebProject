<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Выписка пациента</title>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"
	media="screen" />
</head>
<body>
<table border="1">
	<caption>Персональные данные пациента</caption>
		<thead>
			<tr>
				<th>Имя пациента</th>
				<th>Дата рождения</th>
				<th>Место жительства</th>
				<th>Телефон</th>
				<th>Фото</th>
			</tr>
		</thead>
		<tr>
			<jsp:useBean id="Patient" class="by.htp.entity.Patient"
				type="java.lang.Object" scope="request" />
		<td>	<jsp:getProperty property="name" name="Patient" /> </td>
		<td>	<jsp:getProperty property="data" name="Patient" /></td>
		<td>	<jsp:getProperty property="adress" name="Patient" /></td>
		<td>	<jsp:getProperty property="telephone" name="Patient" /></td>
		<td><img
				src="data:image/jpg;base64,<%String nameA = (String) request.getAttribute("base64Image");
			out.println(nameA);%>"
				width="140" height="200" /></td>
		</tr>
	</table>

	<table border="1">
	<jsp:useBean id="Card" class="by.htp.entity.Card"	type="java.lang.Object" scope="request" />
		<caption>Данные учетной карты</caption>
		<thead>
			<tr>
				<th>Дата выдачи карты</th>
				<th>Эпикриз</th>
				<th>Выписать</th>
			</tr>
		</thead>
			<tr>
				<td>	<jsp:getProperty property="date" name="Card" /> </td>
				<td>	<jsp:getProperty property="epicris" name="Card" /></td>
				<td><form name="command" action="controller" method="post">	
				<input type="hidden" name="command" value="out_off" />	
						<input type="hidden" name="out_off" value="${Patient.idPatient}" /> <input
							type="submit" value="Выписать">

					</form></td>
				<td>
	<!-- 	<form name="command" action="controller" method = "post" enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "upload_file" />
      </form> -->				
      </td>	
			</tr>
	</table>
	<c:set var="map1" value="${Medications.getProcedurs().values()} " />
	<c:set var="map2" value="${Medications.getMedications().values()} " />
	<c:set var="map3" value="${Medications.getOperations().values()} " />
	<table border="1">
		<caption>Предыдущие и текущие назначения</caption>
		<thead>
			<tr>
				<th>Процедуры</th>
				<th>Лекарства</th>
				<th>Операции</th>
			</tr>
		</thead>

	<tr>
	<td>
		 <table>
    
			<c:forEach
					items="${map1.toString().replace('[','').replace(']', '')}"
					var="procedurs">
					
					<tr><td>
					<c:out value="${procedurs}" />
					</td></tr>
				</c:forEach>
				
	</table>
	</td>		
	<td>
		 <table>
    
			<c:forEach
					items="${map2.toString().replace('[','').replace(']', '')}"
					var="medications">
					
					<tr><td>
					<c:out value="${medications}" />
					</td></tr>
				</c:forEach>
				
	</table>
	</td>	
	<td>
		 <table > 
			<c:forEach
					items="${map3.toString().replace('[','').replace(']', '')}"
					var="operations">				
					<tr><td>
					<c:out value="${operations}" />
					</td></tr>
				</c:forEach>				
	</table>
	</td>
	</tr>
	</table>
</body>
</html>
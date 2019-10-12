<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
<title>Личная страница пациента</title>
<style>
   .b1 {
    background: navy;
    color: white;  
    font-size: 10pt; 
   }
   table {
    width: 800px;
    border: 1px solid green; 
    margin: auto;
   }
   td {
    text-align: center;
   }
  </style>
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
	<table style="width: 100px">
	<caption>Фото</caption>
		<thead>
			<tr>
				<th>Фото</th>
			</tr>
		</thead>
		<tr>
			<td><img
				src="data:image/jpg;base64,<%String nameA = (String) request.getAttribute("base64Image");
			out.println(nameA);%>"
				width="140" height="200" /></td>
		</tr>
	</table>
	<jsp:useBean id="Medications" beanName="by.htp.entity.Medication"
		type="by.htp.entity.Medication" scope="request" />
	<!--  Создание таблицы с назначениями -->
	<table border="1">
	<caption >Назначенные процедуры</caption>
		<thead>
			<tr>
				<th>Процедуры</th>
				<th>Операции с процедурами</th>
			</tr>
		</thead>
		<c:set var="map" value="${Medications.getProcedurs().values()} " />
		<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
			var="procedurs">
			<c:if test="${not fn:containsIgnoreCase(procedurs, 'выполненно') }">
				<tr>			
					<td>
							<c:out value="${procedurs}" />
					
					</td>
					<td>
						<form action="controller" method="post">
						<div class="options">
							<select name="command">
							
								<option value="edit_medication">Выполненно</option>
								
							
							</select> <input type="hidden" name="procedure" value="${procedurs}" />
							<input type="submit" value="Применить">
						</div>
						</form>
						</c:if>	
						
		</c:forEach>
	</table>
	
	<!--  Создание таблицы с лекарствами -->
	<table border="1">
	<caption >Назначенные лекарства</caption>
		<thead>
				<tr>
					<th>Лекарства</th>
					<th>Операции с Лекарствами</th>
				</tr>
		</thead>
					<c:set var="map" value="${Medications.getMedications().values()} " />
			<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
				var="medications">
				<c:if test="${not fn:containsIgnoreCase(medications, 'выполненно')  }">
		<tr>
			<td>

						<c:out value="${medications}" />
				
			</td>
			<td>
				<form action="controller" method="post">

					<select name="command">
						<option value="edit_medication">Выполненно</option>
						
					</select> <input type="hidden" name="medication" value="${medications}" /> <input
						type="submit" value="Применить">

				</form>
				</c:if>
		</c:forEach>
		<tr>
				<th>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="back_user_page" /> <input
							type="submit" value="Вернутся на персональную страницу" style="height:50px; width:250px" class="b1">
							
					</form>
				</th>
			</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Эпикриз</title>
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
		<caption>"Эпикриз"</caption>
		<thead>
			<tr>
				<th>Выбрать заболевание из списка</th>
				<th>Заболевание</th>
				<c:if test="${requestScope.Flag == true}">
				<th>Подготовить выписку</th>
				</c:if>
			</tr>
		</thead>
		<tr>
			<td>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="diagnosis" /> <select
						name="diagnos">
						<c:forEach var="currentDiagnos" items="${requestScope.List}">
							<option>
								<c:out value="${currentDiagnos} " />
							</option>
						</c:forEach>
					</select> <input type="hidden" value="${currentDiagnos}" /> 
					<input type="hidden" name="passport" value="${Patient.passport}" />
					<input	type="submit" value="Применить">
				</form>
			</td>
			<td>
			<form action="controller" method="post">
						<input type="hidden" name="command" value="diagnosis" />
						<input type="hidden" name="passport" value="${Patient.passport}" />
						<input name="diagnos" name=""> <input type="submit"
							value="Применить">
					</form>
			</td>
			<c:if test="${requestScope.Flag == true}">
			<td>	
				диагноз: ${requestScope.Diagnos}		
			<form action="controller" method="post">
						<input type="hidden" name="command" value="right_out" />
						<input type="hidden" name="passport" value="${Patient.passport}" />
						 <input type="submit" value="Подготовить">
					</form>
			</td>
			</c:if>
		</tr>
		<tr>
				<th>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="back_user_page" /> <input
							type="submit" value="Вернутся на персональную страницу" style="height:50px; width:250px" class="b1">
							
					</form>
				</th>
			</tr>
	</table>



	<table border="1">
		<caption>Предыдущие и текущие назначения</caption>
		<thead>
			<tr>
				<th>Процедуры</th>
				<th>Лекарства</th>
				<th>Операции</th>
			</tr>
		</thead>

		<c:set var="map1" value="${Medications.getProcedurs().values()} " />
		<c:set var="map2" value="${Medications.getMedications().values()} " />
		<c:set var="map3" value="${Medications.getOperations().values()} " />

		<tr>
			<td><c:forEach
					items="${map1.toString().replace('[','').replace(']', '')}"
					var="procedurs">
					<c:out value="${procedurs}" />
				</c:forEach></td>
			<td><c:forEach
					items="${map2.toString().replace('[','').replace(']', '')}"
					var="medications">
					<c:out value="${medications}" />
				</c:forEach></td>
			<td><c:forEach
					items="${map3.toString().replace('[','').replace(']', '')}"
					var="operations">
					<c:out value="${operations}" />
				</c:forEach></td>
		</tr>

	</table>
</body>
</html>
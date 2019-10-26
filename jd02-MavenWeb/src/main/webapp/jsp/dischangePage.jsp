<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.personal" var="personal" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.fio" var="fio" />
<fmt:message bundle="${loc}" key="local.locLine.patient.date" var="birth_date" />
<fmt:message bundle="${loc}" key="local.locLine.patient.location" var="location" />
<fmt:message bundle="${loc}" key="local.locLine.patient.telephone" var="telephone" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.locLine.user.epicrisis" var="epicrisis" />
<fmt:message bundle="${loc}" key="local.locLine.user.epicrisis.select" var="epicrisis_list" />
<fmt:message bundle="${loc}" key="local.locLine.user.epicrisis.disease" var="disease" />
<fmt:message bundle="${loc}" key="local.locLine.user.epicrisis.extract" var="extract" />
<fmt:message bundle="${loc}" key="local.locbutton.ok" var="ok" />
<fmt:message bundle="${loc}" key="local.locLine.patient.epicrisis.extract" var="prepare" />
<fmt:message bundle="${loc}" key="local.locLine.patient.diagnosis" var="diagnosis" />
<fmt:message bundle="${loc}" key="local.locLine.patient.return" var="return_page" />
<fmt:message bundle="${loc}" key="local.locLine.patient.appointments.all" var="appointments_all" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.procedures" var="procedures" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications" var="medication" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.operation" var="operation" />
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
<jsp:useBean id="Patient" class="by.htp.entity.Patient"
				type="java.lang.Object" scope="request" />
<form action="controller" method="post">
		<input type="hidden" name="language" value="live" /> <input
			type="hidden" name="local" value="ru" />
			<input type="hidden" name="name" value="${Patient.passport}">
			 <input type="submit"
			value="${ru_button}">
	</form>
	<form action="controller" method="post">
		<input type="hidden" name="language" value="live" />
		<input type="hidden" name="name" value="${Patient.passport}">
		 <input	type="hidden" name="local" value="en" /> <input type="submit"
			value="${en_button}">
	</form>
<table border="1">
	<caption>${personal}</caption>
		<thead>
			<tr>
				<th>${fio}</th>
				<th>${birth_date}</th>
				<th>${location}</th>
				<th>${telephone}</th>
				<th>${photo}</th>
			</tr>
		</thead>
		<tr>
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
		<caption>"${epicrisis}"</caption>
		<thead>
			<tr>
				<th>${epicrisis_list}</th>
				<th>${disease}</th>
				<c:if test="${requestScope.Flag == true}">
				<th>${extract}</th>
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
					<input	type="submit" value="${ok}">
				</form>
			</td>
			<td>
			<form action="controller" method="post">
						<input type="hidden" name="command" value="diagnosis" />
						<input type="hidden" name="passport" value="${Patient.passport}" />
						<input name="diagnos" name=""> <input type="submit"
							value="${ok}">
					</form>
			</td>
			<c:if test="${requestScope.Flag == true}">
			<td>	
				${diagnosis} ${requestScope.Diagnos}		
			<form action="controller" method="post">
						<input type="hidden" name="command" value="right_out" />
						<input type="hidden" name="passport" value="${Patient.passport}" />
						 <input type="submit" value="${prepare}">
					</form>
			</td>
			</c:if>
		</tr>
		<tr>
				<th>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="back_user_page" /> <input
							type="submit" value="${return_page}" style="height:50px; width:250px" class="b1">
							
					</form>
				</th>
			</tr>
	</table>



	<table border="1">
		<caption>${appointments_all}</caption>
		<thead>
			<tr>
				<th>${procedures}</th>
				<th>${medication}</th>
				<th>${operation}</th>
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
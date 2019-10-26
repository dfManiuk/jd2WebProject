<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locLine.user.page.fio" var="fio" />
<fmt:message bundle="${loc}" key="local.locLine.patient.date" var="birth_date" />
<fmt:message bundle="${loc}" key="local.locLine.patient.location" var="location" />
<fmt:message bundle="${loc}" key="local.locLine.patient.telephone" var="telephone" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.personal" var="personal" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.photo" var="photo" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.procedures" var="procedures" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.procedure" var="procedure" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.procedure.actions" var="actions" />
<fmt:message bundle="${loc}" key="local.locLine.done" var="done" />
<fmt:message bundle="${loc}" key="local.locLine.cancel" var="cancel" />
<fmt:message bundle="${loc}" key="local.locbutton.ok" var="ok" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications" var="medications" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medication" var="medication" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications.actions" var="actions_medication" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications.reception" var="last_reception" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications.stop" var="stop_taking" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.operations" var="assigned_operations" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.operation" var="operation" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.operation.actions" var="operation_actions" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.operation.destinations" var="destinations" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medicines.once" var="medicine_once" />
<fmt:message bundle="${loc}" key="local.locLine.name" var="locLine_name" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medications.actions.periodically" var="periodicall" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medicins" var="prescribed_medicine" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.medicins.frequency" var="frequency_of_admission" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.appointments" var="all_appointments" />
<fmt:message bundle="${loc}" key="local.locLine.patient.page.card" var="card" />
<fmt:message bundle="${loc}" key="local.locbutton.get" var="get" />
<fmt:message bundle="${loc}" key="local.locLine.patient.return" var="return_page" />
<fmt:message bundle="${loc}" key="local.locLine.patient.appointments.all" var="appointments_all" />

<!DOCTYPE html>
<html>
<head>
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
<form action="controller" method="post">
		<input type="hidden" name="language" value="medication" /> <input
			type="hidden" name="local" value="ru" /> <input type="submit"
			value="${ru_button}">
	</form>
	<form action="controller" method="post">
		<input type="hidden" name="language" value="medication" /> <input
			type="hidden" name="local" value="en" /> <input type="submit"
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
	<table  style="width: 100px;">
	<caption>${photo}</caption>
		<thead>
			<tr>
				<th>${photo}</th>
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
		<caption>${procedures}</caption>
		<thead>
			<tr>
				<th>${procedure}</th>
				<th>${actions}</th>
			</tr>
		</thead>
		<c:set var="map" value="${Medications.getProcedurs().values()} " />
		<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
			var="procedurs">
			<c:if test="${not fn:containsIgnoreCase(procedurs, 'выполненно') }">
				<tr>
					<td><c:out value="${procedurs}" /></td>
					<td>
						<form action="controller" method="post">
							<div class="options">
								<select name="command">

									<option value="edit_medication">${done}</option>
									<option value="del_medication">${cancel }</option>

								</select> <input type="hidden" name="procedure" value="${procedurs}" />
								<input type="submit" value="${ok}">
							</div>
						</form>
			</c:if>
		</c:forEach>
	</table>

	<!--  Создание таблицы с лекарствами -->
	<table border="1">
		<caption>${medications}</caption>
		<thead>
			<tr>
				<th>${medication}</th>
				<th>${actions_medication}</th>
			</tr>
		</thead>
		<c:set var="map" value="${Medications.getMedications().values()} " />
		<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
			var="medications">
			<c:if
				test="${not fn:containsIgnoreCase(medications, 'выполненно')  }">
				<tr>
					<td>
					<c:out value="${medications}" />
					<c:if test= "${PeriosityChange eq medications}">
					<c:out value="${last_reception}" />
					</c:if>
					</td>
					<td>
					<c:if test= "${ Medications.periodicity ne 1 or Medications.periodicity ne null }">
					<form action="controller" name="command" method="post">
					 <input type="hidden" name="command" value="medication_pereosity">
							<input  type="hidden" name="medication" value="${medications}" />
							<input type="hidden" name="patient" value="${Patient.passport}" />
							<input type="submit" value="${stop_taking}">
						</form>
						</c:if>
						<form action="controller" method="post">
							<select name="command">
								<option value="edit_medication">${done}</option>
								<option value="del_medication">${cancel}</option>
							</select> <input type="hidden" name="medication" value="${medications}" />
							<input type="submit" value="${ok}">
						</form>
			</c:if>
		</c:forEach>
	</table>

	<!--  Создание таблицы с операциями -->

	<table border="1">
		<caption>${assigned_operations}</caption>
		<thead>
			<tr>
				<th>${operation}</th>
				<th>${operation_actions }</th>
			</tr>
		</thead>
		<c:set var="map" value="${Medications.getOperations().values()} " />
		<c:forEach items="${map.toString().replace('[','').replace(']', '')}"
			var="operations">
			<tr>
				<td><c:if
						test="${not fn:containsIgnoreCase(operations, 'выполненно') }">
						<c:out value="${operations}" />
					</c:if>
					</td>
				<td>
					<form action="controller" method="post">
						<select name="command">
							<option value="edit_medication">${done}</option>
							<option value="del_medication">${cancel}</option>
						</select> <input type="hidden" name="operation" value="${operations}" /> <input
							type="submit" value="${ok}">
					</form>				
				</td>
			</tr>
		</c:forEach>
	</table>

	<!--  Создание формы с назначениями -->
	<jsp:useBean id="MedicationRequest" class="by.htp.entity.Medication"
		type="java.lang.Object" scope="request" />
	<table>
		<caption>${destinations }</caption>
		<thead>
			<tr >
				<th style="border-width:10px">${destinations}</th>
				<th style="border-width:10px">
					<form action="controller" method="post">
						<select name="options">
							<option value="procedure">${procedure}</option>
							<option value="medication">${medicine_once}</option>
							<option value="operation">${operation}</option>
						</select> <input type="hidden" name="command" value="add_medication" /> <input
							type="text" name="med" placeholder="${locLine_name}"  value=""> <input type="submit"
							value="${ok}"> <input type="submit" value="+">
					</form>
				</th>
			</tr>
		</thead>
	</table>
	<table>
		<caption>${periodicall}</caption>
		<thead>
			<tr >
				<th style="border-width:10px" >${prescribed_medicine}</th>
				<th style="border-width:10px">
					<form action="controller" method="post">
						 <input type="hidden" name="command" value="add_medication_period" /> 
						 <input	type="text" name="med" placeholder="${locLine_name}" value="">
							<input 	type="text" name="per" placeholder="${frequency_of_admission }" size="20" min="1" value="">
							 <input type="submit" value="${ok}"> 
					</form>
				</th>
			</tr>
		</thead>
	</table>
	<!--  Вызов формы с всеми назначениями -->
	<table>
		<caption>${all_appointments}</caption>
		<thead>
			<tr>
				<th>${card}</th>
				<th>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="all_medication" /> <input
							type="submit" value="${get }">
					</form>
				</th>
			</tr>
			<tr>
				<th>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="back_user_page" /> <input
							type="submit" value="${return_page}" style="height:50px; width:250px" class="b1">
							
					</form>
				</th>
			</tr>
		</thead>
	</table>
	<!--  Создание формы с всеми назначениями -->
	<table border="1">
		<caption>${appointments_all }</caption>
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
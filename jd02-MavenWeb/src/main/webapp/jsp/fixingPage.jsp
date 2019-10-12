<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Закрепление специалистов за пациентами</title>
<link rel="stylesheet" type="text/css" href="css/styleAccaunt.css"
	media="screen" />
</head>
<%-- 
<jsp:useBean id="doctor" beanName="by.htp.entity.Doctor"
		type="by.htp.entity.Doctor" scope="request" /> --%>


<jsp:useBean id="list" type="java.util.List<by.htp.entity.Doctor>"
	scope="request" />
<jsp:useBean id="patients" type="java.util.List<by.htp.entity.Patient>"
	scope="request" />
	
	<!--  Создание таблицы с врачами -->
<body>
<table border="1">
	<caption>Закрепление пациентов</caption>
	<thead>
		<tr>
			<th>ФИО</th>
			<th>Специализация</th>
			<th> Количество пациентов</th>
			<th> Закрепить пациента</th>
			<th> Открепить пациента</th>
		</tr>
	</thead>
	
	<c:forEach items="${list}" var="currentDoctorList">
		<tr>
				<c:if
					test="${not fn:containsIgnoreCase(currentDoctorList.getPosition(), 'медсестра')  }">
					<td><c:out value="${currentDoctorList.getName()}" /></td>
					<td><c:out value="${currentDoctorList.getSpecialization()}" /></td>
					<td><c:out value="${currentDoctorList.getPatients().size()}" /></td>
					
					<%--  Закрепление пациентов за врачем --%>
				
					<td>
					<form action="controller" method="post">
					<input type="hidden" name="command" value="fixing" />
					<div class="options">
					<select name="fixPatient" >
							<c:forEach items="${patients}" var="currentPatientsList">
								<c:if
									test="${not fn:containsIgnoreCase(currentDoctorList.getPatients(), currentPatientsList.getName())  }">
									<option >
										<c:out value="${currentPatientsList.getName()}" />
									</option>
								</c:if>
					</c:forEach>			
					</select>
					<input type="hidden" name="fixUser" value="${currentDoctorList.getName()}" />
					<input type="submit" value="Применить">	
					</div>
					</form>
					</td>
					
					<%--  Открепление пациентов от врачей --%>
					
					<td>
					<form action="controller" method="post">
					<input type="hidden" name="command" value="unFixing" />
					<div class="options">
					<select name="unFixPatient" >
							<c:forEach items="${patients}" var="currentPatientsList">
								<c:if
									test="${ fn:containsIgnoreCase(currentDoctorList.getPatients(), currentPatientsList.getName())  }">
									<option >
										<c:out value="${currentPatientsList.getName()}" />
									</option>
								</c:if>
					</c:forEach>			
					</select>
					<input type="hidden" name="unFixUser" value="${currentDoctorList.getName()}" />
					<input type="submit" value="Применить">	
					</div>
					</form>
					</td>
					
				</c:if>
			</tr>
	</c:forEach>

</table>
<button type="button" name="back" onclick="history.back()">back</button>
</body>
</html>
<%-- 회원별 알람 목록 조회 --%>
<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 [
  	
  	<c:forEach var="alarm" items="${requestScope.alarmList}" varStatus="status">
		{
			"no": ${alarm.no},
			"alarmType": "${alarm.alarmType}",
			"alarmRead": "${alarm.alarmRead}",
			"alarmContent": "${alarm.alarmContent}",
			"alarmUrl": "${alarm.alarmUrl}"
		}
		
		<c:if test="${fn:length(requestScope.alarmList) - status.index > 1}">
			,
		</c:if>
	</c:forEach>
	
 ]






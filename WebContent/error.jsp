<%-- error.jsp --%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Error : </h3>
<c:forEach var="stack" items="${requestScope.exception.stackTrace}"> <!-- getStackTrace 호출 -->
	${stack}
</c:forEach>
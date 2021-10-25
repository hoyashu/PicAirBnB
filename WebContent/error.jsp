<%-- error.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h3>Error : </h3>
<c:forEach var="stack" items="${requestScope.exception.stackTrace}"> 
	${stack} 
</c:forEach>

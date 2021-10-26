<%-- 알림 발송 결과 리턴 0:정상 1:회원 없음 --%>
<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

[ { 
"result": ${requestScope.result},
"code": ${requestScope.code}
 } ]
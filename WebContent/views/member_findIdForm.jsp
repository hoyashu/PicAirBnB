<%-- member_findIdForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="findIdform" onsubmit="return false;">
    <input type="text" name="name" id="name" placeholder="이름"> <input type="text" name="birth" id="birth"
        placeholder="00000000">
    <p>생년월일은 8자로 입력할 것</p>
    <p>2002년 9월 10일 생인 경우 : 20020910 작성</p>

    <div>
        <input id="submit_value" type="button" name="submit_value" value="아이디찾기">
    </div>
</form>
<script type="text/javascript">
    document.getElementById("submit_value").onclick = submitAction;
    //생년월일 유효성 체크
    function isValidDate(dateStr) {

        if (dateStr.length != 8) {
            alert("생년월일을 8자로 입력하시오");
            return false;
        }

        var year = Number(dateStr.substr(0, 4));
        var month = Number(dateStr.substr(4, 2));
        var day = Number(dateStr.substr(6, 2));
        var today = new Date(); // 날자 변수 선언
        var yearNow = today.getFullYear();
        var adultYear = yearNow - 20;


        if (year < 1900 || year > adultYear) {
            alert("년도를 확인하세요. " + adultYear + "년생 이전 출생자만 등록 가능합니다.");
            return false;
        }
        if (month < 1 || month > 12) {
            alert("달은 1월부터 12월까지 입력 가능합니다.");
            return false;
        }
        if (day < 1 || day > 31) {
            alert("일은 1일부터 31일까지 입력가능합니다.");
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            alert(month + "월은 31일이 존재하지 않습니다.");
            return false;
        }
        if (month == 2) {
            var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
            if (day > 29 || (day == 29 && !isleap)) {
                alert(year + "년 2월은  " + day + "일이 없습니다.");
                return false;
            }
        }
        return true;
    }

    function submitAction() {

        const submit = document.getElementById("submit");
        const form = document.getElementById("findIdform");
        const name = document.getElementById("name");
        const birth = document.getElementById("birth");

        if (name.value.trim() == "" || birth.value.trim() == "") {
            alert("이름과 생년월일을 입력하십시오.");
            return false;
        }

        //생년월일 유효성 체크
        isValidDate(birth.value.trim());

        form.action = "${pageContext.request.contextPath}/member_findId.do";
        form.mothod = "POST";
        form.submit();


    }
</script>
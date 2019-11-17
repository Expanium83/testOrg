<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/material-design-lite.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Добавление организации</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="mdl-card">
    <form:form method="post" modelAttribute="organization" action="">
        <div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <label class="mdl-textfield__label">Полное наименование организации</label>
                <input type="text" name="name" class="mdl-textfield__input" value="${organization.name}" pattern="^[^\s]+.*$" required>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <label class="mdl-textfield__label">Адрес организации</label>
                <input type="text" name="address" class="mdl-textfield__input" value="${organization.address}" pattern="^[^\s]+.*$" required>
            </div>
        </div>
        <div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <label class="mdl-textfield__label">ИНН</label>
                <input type="text" name="inn" class="mdl-textfield__input" value="${organization.inn}" pattern="[0-9](.{9}|.{11})"
                       title="10 или 12 цифр" required>
                <span class="mdl-textfield__error">Длина ИНН 10 или 12 цифр</span>
            </div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <label class="mdl-textfield__label">ОГРН</label>
                <input type="text" name="ogrn" class="mdl-textfield__input" value="${organization.ogrn}" pattern="[0-9](.{12}|.{14})"
                       title="13 или 15 цифр" required>
                <span class="mdl-textfield__error">Длина ОГРН 13 или 15цифр</span>
            </div>
        </div>
        <div class="right-position">
            <input type="submit" value="Добавить"
                   class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"/>
        </div>
    </form:form>
    <c:if test="${ORGANIZATION_ALREADY_EXIST}">
        <div class="error">
            <span>Организация с такими данными уже существует!</span>
        </div>
    </c:if>
    <c:if test="${INN_OR_OGRN_IS_ERROR}">
        <div class="error">
            <span>Ошибка при вводе ИНН или ОГРН!</span>
        </div>
    </c:if>
</div>
</body>
</html>

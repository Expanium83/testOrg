<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/material-design-lite.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <title>Список организаций</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<div class="demo-card-wide mdl-card mdl-shadow--2dp w-30">
    <div class="mdl-card__title">
        <h2 class="mdl-card__title-text">Поиск организации</h2>
    </div>
    <div class="mdl-card__supporting-text">
        Для поиска организации заполните одно из следующих полей
        <form:form method="post" modelAttribute="findOrganization" action="">
            <div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label">Полное наименование организации</label>
                    <input type="text" name="name" class="mdl-textfield__input">
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label">Адрес организации</label>
                    <input type="text" name="address" class="mdl-textfield__input ">
                </div>
            </div>
            <div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label">ИНН</label>
                    <input type="text" name="inn" class="mdl-textfield__input" pattern="[0-9]*"
                           >
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label">ОГРН</label>
                    <input type="text" name="ogrn" class="mdl-textfield__input" pattern="[0-9]*"
                           title="13 или 15 цифр">
                </div>
            </div>
            <input type="reset" value="Очистить" class="mdl-button mdl-js-button mdl-button--primary mdl-butt"/>
            <div class="right-position">
                <input type="submit" value="Найти" class="mdl-button mdl-js-button mdl-button--primary mdl-butt"/>
            </div>
        </form:form>
    </div>
</div>
<c:choose>
    <c:when test="${getOrganizations != null or (not empty getOrganizations)}">
        <div>
            <h5>Список организаций</h5>
        </div>
        <table border="1" cellpadding="5"
               class="mdl-data-table mdl-js-data-table mdl-data-table mdl-shadow--2dp">
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Полное наименование организации</th>
                <th class="mdl-data-table__cell--non-numeric">ИНН</th>
                <th class="mdl-data-table__cell--non-numeric">ОГРН</th>
                <th class="mdl-data-table__cell--non-numeric">Адрес организации</th>
            </tr>
            <c:forEach var="getOrg" items="${getOrganizations}">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">${getOrg.name}</td>
                    <td class="mdl-data-table__cell--non-numeric">${getOrg.inn}</td>
                    <td class="mdl-data-table__cell--non-numeric">${getOrg.ogrn}</td>
                    <td class="mdl-data-table__cell--non-numeric">${getOrg.address}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:if test="${ORGANIZATION_NOT_FOUND}">
            <div class="error">
                <span>Организация с заданными параметрами не найдена!</span>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty getOrganizations and not empty organizationList}">
        <div>
            <div>
                <h5>Список организаций</h5>
            </div>
            <table border="1" cellpadding="5" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp w-30">
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Полное наименование организации</th>
                    <th class="mdl-data-table__cell--non-numeric">ИНН</th>
                    <th class="mdl-data-table__cell--non-numeric">ОГРН</th>
                    <th class="mdl-data-table__cell--non-numeric">Адрес организации</th>
                </tr>
                <c:forEach var="org" items="${organizationList}">
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric">${org.name}</td>
                        <td class="mdl-data-table__cell--non-numeric">${org.inn}</td>
                        <td class="mdl-data-table__cell--non-numeric">${org.ogrn}</td>
                        <td class="mdl-data-table__cell--non-numeric">${org.address}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <c:if test="${NO_ORGANIZATION}">
            <div class="error">
                <span>Не найдено ни одной организации!</span>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>
</body>
</html>

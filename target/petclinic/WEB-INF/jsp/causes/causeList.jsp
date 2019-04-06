<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="causes/list">
    <h2>
    Causas
    </h2>

    <table id="causesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Objetivo</th>
            <th>Organizacion</th>
            <th>Detalles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            <tr>
                <td>
                    <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.budgetTarget}"/>
                </td>
                 <td>
                    <c:out value="${cause.organizationName}"/>
                </td>
                 <td>
                  <spring:url value="/causes/${cause.id}/details" var="details"/>
                  <a href="<spring:url value='${details}' htmlEscape='true'/>">Mostrar</a> 
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>

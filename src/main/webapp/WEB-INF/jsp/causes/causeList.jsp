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
            <th>Target</th>
            <th>Organizacion</th>
            <th>Detalles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes.causeList}" var="vet">
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
                    <%-- <spring:url value="/causes/{causeId}/show" var="showUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url> --%>
                    <%-- <a href="<spring:url value="/causes/{causeId}/detalles" htmlEscape="true" />">Mostrar</a> --%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/vets.json" htmlEscape="true" />">View as JSON</a>
            </td>
        </tr>
    </table>
    
   <%--  <br/>
    <a class="btn btn-default" href='<spring:url value="/vets/new" htmlEscape="true"/>'>A&ntildeadir veterinario</a> --%>
</petclinic:layout>

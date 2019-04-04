<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes/list">

    <h2>Informacion de la causa</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripcion</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Meta economica</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Organizacion</th>
            <td><c:out value="${cause.organizationName}"/></td>
        </tr>
    </table>
    
    <h5>
    	Donaciones
    </h5>
      <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Cliente</th>
            <th>Cantidad Donada</th>
            <th>Fecha Donacion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr>
                <td>
                    <c:out value="${donation.client}"/>
                </td>
                <td>
                    <c:out value="${donation.amountDonation}"/>
                </td>
                <td>
                    <c:out value="${donation.donationDate}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br/>
    <a class="btn btn-default" href='<spring:url value="/causes/${cause.id}/donations/new" htmlEscape="true"/>'>A&ntildeadir donacion</a>

</petclinic:layout>
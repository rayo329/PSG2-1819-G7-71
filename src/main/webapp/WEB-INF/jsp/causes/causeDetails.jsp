<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

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
    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Cantidad Donada</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr>
                <td>
                    <c:out value="${donation.amountDonation}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>
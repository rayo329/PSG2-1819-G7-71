<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="donations">
    <h2>Donaciones</h2>

    <table id="donationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Cliente</th>
            <th>Cantidad Donada</th>
            <th>Fecha Donación</th>
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
</petclinic:layout>

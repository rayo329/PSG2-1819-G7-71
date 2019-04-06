<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes/list">
    <h2>
        Nueva Donacion
    </h2>
    
    <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <form:hidden path="donationDate"/>
        <input type="hidden" name="causeId" value="${causeId}"/>

        <div class="form-group has-feedback">
        
            <petclinic:inputField label="Cantidad" name="amountDonation"/>
            <petclinic:inputField label="Cliente" name="client"/>
            
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Crear Donacion</button>
            </div>
        </div>
    </form:form>
    
</petclinic:layout>

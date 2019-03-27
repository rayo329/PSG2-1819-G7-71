<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations/new">
    <h2>
        Nueva Donación
    </h2>
    
    <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <input type="hidden" name="id" value="${donation.id}"/>
        <input type="hidden" name="cause" value="${donation.cause}"/>
        <input type="hidden" name="date" value="${donation.date}"/>
        <div class="form-group has-feedback">
        
            <petclinic:inputField label="Cantidad" name="amount"/>
            
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Crear Donacion</button>
            </div>
        </div>
    </form:form>
    
</petclinic:layout>

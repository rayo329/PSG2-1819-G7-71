<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
        <c:if test="${veterinarian['new']}">New </c:if> Vet
    </h2>
    <form:form modelAttribute="veterinarian" class="form-horizontal" id="add-veterinarian-form">
        <input type="hidden" name="id" value="${veterinarian.id}"/>
        <div class="form-group has-feedback">
        
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            
            <!-- funcionan los dos de abajo, pero con el none  -->
            <!-- El multiple no lo reconoce como atributo  -->
            <div class="control-group">
                    <petclinic:selectField name="specialties"  label="Specialties" names="${specialties}"  size="5"/>
            </div>
            
            <%-- <form:select multiple="true" path="specialties">
   				 <form:options items="${specialties}" itemValue="name" itemLabel="name"/>
			</form:select> --%>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${veterinarian['new']}">
                        <button class="btn btn-default" type="submit">Add Veterinarian</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Veterinarian</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    
</petclinic:layout>

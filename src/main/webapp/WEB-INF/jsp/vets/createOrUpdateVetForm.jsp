<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
        <c:if test="${vet['new']}">New </c:if> Vet
    </h2>
    <form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <%-- quitando la  linea de abajo  se puede editar nombre y apellido--%>
            <petclinic:inputField label="Specialties" name="specialties"/>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
        		<table id="vetsTable" class="table table-striped">
            		<thead>
            			<tr>
            				<th>Specialty</th>
            				<th>Options</th>
            			</tr>
            		</thead>
            		<tbody>
            			<c:forEach var="specialty" items="${vet.specialties}">
            				<tr>
            					<th>
                        			<c:out value="${specialty.name} "/>
                        		</th>
                        		<th>
                        			<spring:url value="/vets/{vetId}/specialties/{spname}.html" var="vetUrl">
                        				<spring:param name="vetId" value="${vet.id}"/>
                        				<spring:param name="spname" value="${specialty.name}"/>
                    				</spring:url>
                    				<a href="${fn:escapeXml(vetUrl)}"><c:out value="Delete"/></a>
                      			</th>
                      		</tr>
                    	</c:forEach>
            		</tbody>
            	</table>
           </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vet['new']}">
                        <button class="btn btn-default" type="submit">Add Vet</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Vet</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

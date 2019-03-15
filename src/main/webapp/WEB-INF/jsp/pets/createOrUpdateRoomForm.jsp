<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <h2><c:if test="${room['new']}">Nueva habitacion</c:if></h2>

        <b>Mascota</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Fecha de nacimiento</th>
                <th>Tipo</th>
                <th>Due&ntildeo</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${pet.name}"/></td>
                <td><petclinic:localDate date="${pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${pet.type.name}"/></td>
                <td><c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="room" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Comienzo" name="start"/>
                <petclinic:inputField label="Fin" name="end"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${petId}"/>
                    <button class="btn btn-default" type="submit">A&ntildeadir habitacion</button>
                </div>
            </div>
        </form:form>
        
         <br/>
        
        <b>Habitaciones anteriores</b>
        <table class="table table-striped">
        	<tr>
        		<th>Comienzo</th>
        		<th>Fin</th>
        	</tr>
        	
        	<c:forEach var="roomPet" items="${roomsPet}">
        		<tr>
        		
        			<td>
        			<petclinic:localDate date="${roomPet.start}" pattern="yyyy/MM/dd"/>
        			</td>
        			<td>
        			<petclinic:localDate date="${roomPet.end}" pattern="yyyy/MM/dd"/>
        			</td>
        			
        		</tr>
        	</c:forEach>
        </table>

    </jsp:body>

</petclinic:layout>

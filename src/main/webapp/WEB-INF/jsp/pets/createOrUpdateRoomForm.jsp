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
        <h2><c:if test="${room['new']}">New Room</c:if></h2>

        <b>Pet</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Type</th>
                <th>Owner</th>
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
                <petclinic:inputField label="Start date" name="start"/>
                <petclinic:inputField label="End date" name="end"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${petId}"/>
                    <button class="btn btn-default" type="submit">Add Room</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>

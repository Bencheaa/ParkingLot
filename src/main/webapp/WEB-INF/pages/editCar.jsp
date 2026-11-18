<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit Car">
    <h1>Edit Car</h1>
    <form method="POST" action="${pageContext.request.contextPath}/EditCar" class="needs-validation" novalidate>
        <input type="hidden" name="id" value="${car.id}">
        <div class="mb-3">
            <label for="license_plate" class="form-label">License plate</label>
            <input type="text" class="form-control" id="license_plate" name="license_plate"
                   value="<c:out value='${car.licensePlate}'/>" aria-describedby="license_plate_feedback" required>
            <div class="invalid-feedback" id="license_plate_feedback">
                Please enter a license plate.
            </div>
        </div>
        <div class="mb-3">
            <label for="parking_spot" class="form-label">Parking spot</label>
            <input type="text" class="form-control" id="parking_spot" name="parking_spot"
                   value="<c:out value='${car.parkingSpot}'/>" aria-describedby="parking_spot_feedback" required>
            <div class="invalid-feedback" id="parking_spot_feedback">
                Please enter a parking spot.
            </div>
        </div>
        <div class="mb-3">
            <label for="owner_id" class="form-label">Owner</label>
            <select class="form-select" id="owner_id" name="owner_id" aria-describedby="owner_id_feedback" required>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}" ${user.id eq car.ownerId ? 'selected' : ''}>
                        <c:out value="${user.username}"/>
                    </option>
                </c:forEach>
            </select>
            <div class="invalid-feedback" id="owner_id_feedback">
                Please choose an owner.
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="${pageContext.request.contextPath}/Cars" class="btn btn-secondary">Cancel</a>
    </form>
</t:pageTemplate>

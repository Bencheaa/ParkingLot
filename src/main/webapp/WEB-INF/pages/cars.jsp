<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
        <button type="submit" class="btn btn-danger my-3">Delete selected cars</button>
        <div class="container text-center">
            <c:forEach var="car" items="${cars}">
                <div class="row align-items-center mb-2">
                    <div class="col-auto">
                        <input type="checkbox" class="form-check-input" name="car_ids"
                               id="car_${car.id}" value="${car.id}">
                        <label class="visually-hidden" for="car_${car.id}">Select <c:out value="${car.licensePlate}"/></label>
                    </div>
                    <div class="col">
                        <c:out value="${car.licensePlate}"/>
                    </div>
                    <div class="col">
                        <c:out value="${car.parkingSpot}"/>
                    </div>
                    <div class="col">
                        <c:out value="${car.ownerName}"/>
                    </div>
                    <div class="col">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCar?id=${car.id}">Edit Car</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>

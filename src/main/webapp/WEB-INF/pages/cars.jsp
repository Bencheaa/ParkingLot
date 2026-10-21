<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate
        pageTitle="Cars">
    <h1> Cars!!</h1>

    <div>

        <div>
            <div>License Plate: B-123-ABC</div>
            <div>Parking Spot: A-01</div>
            <div>Owner: John Doe</div>
        </div>


        <div>
            <div>License Plate: CJ-45-XYZ</div>
            <div>Parking Spot: B-14</div>
            <div>Owner: Jane Smith</div>
        </div>


        <div>
            <div>License Plate: TM-99-PAR</div>
            <div>Parking Spot: C-05</div>
            <div>Owner: Alex Johnson</div>
        </div>
    </div>

    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>
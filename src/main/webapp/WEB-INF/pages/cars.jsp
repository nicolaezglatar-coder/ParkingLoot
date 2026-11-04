<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:pageTemplate pageTitle="Cars">
    <div class="d-flex flex-wrap justify-content-between align-items-end gap-3 mb-4">
        <div>
            <p class="text-uppercase text-primary fw-semibold small mb-2">Evidența parcării</p>
            <h1 class="display-6 fw-bold mb-0">Cars</h1>
        </div>
        <div class="spot-counter rounded-3 px-4 py-3">
            <span class="d-block small text-body-secondary">Free parking spots</span>
            <strong class="fs-3">${numberOfFreeParkingSpots}</strong>
        </div>
    </div>
    <div class="card border-0 shadow-sm">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                    <tr>
                        <th scope="col" class="ps-4">License plate</th>
                        <th scope="col">Parking spot</th>
                        <th scope="col" class="pe-4">Owner</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="car" items="${cars}">
                        <tr>
                            <td class="ps-4 fw-medium"><c:out value="${car.licensePlate}" /></td>
                            <td><c:out value="${car.parkingSpot}" /></td>
                            <td class="pe-4"><c:out value="${car.ownerName}" /></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty cars}">
                        <tr><td colspan="3" class="text-center text-body-secondary py-4">Nu există mașini în baza de date.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:pageTemplate>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<t:pageTemplate pageTitle="Add Car">
    <div class="row justify-content-center">
        <div class="col-lg-7 col-xl-6">
            <p class="text-uppercase text-primary fw-semibold small mb-2">Evidența parcării</p>
            <h1 class="display-6 fw-bold mb-4">Add Car</h1>
            <c:if test="${not empty formError}">
                <div class="alert alert-danger" role="alert"><c:out value="${formError}" /></div>
            </c:if>
            <div class="card border-0 shadow-sm">
                <div class="card-body p-4">
                    <form class="needs-validation" novalidate method="POST"
                          action="${pageContext.request.contextPath}/AddCar">
                        <div class="mb-3">
                            <label class="form-label" for="license_plate">License plate</label>
                            <input class="form-control" type="text" id="license_plate" name="license_plate"
                                   value="${fn:escapeXml(formLicensePlate)}" maxlength="100" required>
                            <div class="invalid-feedback">Enter a license plate.</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="parking_spot">Parking spot</label>
                            <input class="form-control" type="text" id="parking_spot" name="parking_spot"
                                   value="${fn:escapeXml(formParkingSpot)}" maxlength="100" required>
                            <div class="invalid-feedback">Enter a parking spot.</div>
                        </div>
                        <div class="mb-4">
                            <label class="form-label" for="owner_id">Owner</label>
                            <select class="form-select" id="owner_id" name="owner_id" required>
                                <option value="">Choose...</option>
                                <c:forEach var="user" items="${users}">
                                    <option value="${user.id}" ${user.id eq formOwnerId ? 'selected' : ''}>
                                        <c:out value="${user.username}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Choose an owner.</div>
                        </div>
                        <div class="d-flex gap-2">
                            <button class="btn btn-primary btn-lg" type="submit">Save</button>
                            <a class="btn btn-outline-secondary btn-lg" href="${pageContext.request.contextPath}/Cars">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</t:pageTemplate>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:pageTemplate pageTitle="Users">
    <div class="mb-4">
        <p class="text-uppercase text-primary fw-semibold small mb-2">Conturi înregistrate</p>
        <h1 class="display-6 fw-bold mb-0">Users</h1>
    </div>
    <div class="card border-0 shadow-sm">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                    <tr>
                        <th scope="col" class="ps-4">Username</th>
                        <th scope="col" class="pe-4">Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td class="ps-4 fw-medium"><c:out value="${user.username}" /></td>
                            <td class="pe-4"><c:out value="${user.email}" /></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty users}">
                        <tr><td colspan="2" class="text-center text-body-secondary py-4">Nu există utilizatori în baza de date.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:pageTemplate>

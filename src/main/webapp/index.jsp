<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pageTemplate pageTitle="Home">
    <section class="hero rounded-4 p-4 p-md-5">
        <div class="row align-items-center g-4">
            <div class="col-lg-8">
                <p class="text-uppercase fw-semibold small mb-3">Bine ai venit</p>
                <h1 class="display-5 fw-bold mb-3">Welcome to the Parking Lot</h1>
                <p class="lead mb-4">O aplicație simplă pentru evidența locurilor de parcare, a mașinilor și a proprietarilor lor.</p>
                <a class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/Cars">Vezi mașinile</a>
            </div>
        </div>
    </section>
</t:pageTemplate>

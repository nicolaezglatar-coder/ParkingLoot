<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Navigare principală">
        <div class="container">
            <a class="navbar-brand fw-semibold" href="${pageContext.request.contextPath}/">Parking Lot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavigation" aria-controls="mainNavigation" aria-expanded="false" aria-label="Deschide meniul">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mainNavigation">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link${pageContext.request.requestURI.endsWith('/about.jsp') ? ' active' : ''}" href="${pageContext.request.contextPath}/about.jsp">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link${activePage eq 'Cars' ? ' active' : ''}" href="${pageContext.request.contextPath}/Cars">Cars</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link${activePage eq 'Users' ? ' active' : ''}" href="${pageContext.request.contextPath}/Users">Users</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

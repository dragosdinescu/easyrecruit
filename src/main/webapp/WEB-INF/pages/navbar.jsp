<%--
    Document   : navbar
    Created on : Nov 8, 2020, 11:49:12 AM
    Author     : andrei
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("forwardPath", request.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH));%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${contextPath}"> EasyRecruit </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${forwardPath == '/Positions' ? ' active' : ''}">
                <a class="nav-link" href="${contextPath}/Positions">Positions</a>
            </li>
            <c:if test="${pageContext.request.isUserInRole('ClientRole')}">
                <li class="nav-item ${forwardPath == '/Applications' ? ' active' : ''}">
                    <a class="nav-link" href="${contextPath}/Applications">My Applications</a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
                <li class="nav-item ${forwardPath == '/Users' ? ' active' : ''}">
                    <a class="nav-link" href="${contextPath}/Users">Users</a>
                </li>
            </c:if>
                <!-- c:if test="${pageContext.request.isUserInRole('AdminRole')}">' -->
                <li class="nav-item ${forwardPath == '/Positions/Add' ? ' active' : ''}">
                    <a class="nav-link" href="${contextPath}/Positions/Add">Add position</a>
                </li>
            <!--/c:if>-->
        </ul>

        <ul class="navbar-nav ml-auto">
            <c:if test="${pageContext.request.getRemoteUser() == null}">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/Users/Register">Register</a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.getRemoteUser() != null}">
                <li class="nav-item navbar-text">
                    Welcome back ${pageContext.request.getRemoteUser()}
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/Profile">Profile</a>
                </li>
            </c:if> 
            <li class="nav-item">
                <c:choose>
                    <c:when test="${pageContext.request.getRemoteUser() == null}"> 
                        <a class="nav-link" href="${contextPath}/Login">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${contextPath}/Logout">Logout</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>

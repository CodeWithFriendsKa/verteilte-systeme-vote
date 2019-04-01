<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@attribute name="title"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="menu" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />

        <title>${title}</title>

        
        <!-- Bootstrap -->
        <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />         
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        
        <!-- Custom CSS -->
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" /> 
        
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

        
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <%-- Kopfbereich --%>
        <header>

            <%-- Menü --%>
            <nav id="menubar" class="navbar sticky-top navbar-dark" style="background-color: white;">
                
                <a class="navbar-brand p-0 d-flex">
                    <i class="fas fa-fire fa-2x mr-2"></i>
                    <h3>Hot or Not</h3>
                </a>
                
                <jsp:invoke fragment="menu"/>

                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <div class="menuitem">
                        <a href="<c:url value="/logout/"/>" class="icon-logout">Logout ${pageContext.request.userPrincipal.name}</a>
                    </div>
                </c:if>
                
                <div class="menuitem">
                    <a href="<c:url value="/dashboard/"/>" class="icon-logout">Dashboard</a>
                </div>
                <div class="menuitem">
                    <a href="<c:url value="/createvote/"/>" class="icon-logout">Create Vote</a>
                </div>
                <div class="menuitem">
                    <a href="<c:url value="/voteit/"/>" class="icon-logout">Vote It</a>
                </div>
            </nav>
        </header>

        <%-- Hauptinhalt der Seite --%>
        <main class="hintergrund">
            <div class="container" style="min-height: 100%">
                 <jsp:invoke fragment="content"/>
            </div>
        </main>
    </body>
</html>
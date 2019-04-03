<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>


    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/signup/"/>">Registrieren</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="row">
            <div class="col-md-6 login rounded mt-5 p-3">
                <div class="continer">
                    
                    <form method="post" class="stacked">
                        <div class="column">
                            <%-- CSRF-Token --%>
                            <input type="hidden" name="csrf_token" value="${csrf_token}">

                            <%-- Eingabefelder --%>
                            <label for="login_username">
                                Benutzername:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="text" name="login_username">
                            </div>

                            <label for="login_password1">
                                Passwort:
                                <span class="required">*</span>
                            </label>
                            <div class="side-by-side">
                                <input type="password" name="login_password">
                            </div>

                            <%-- Button zum Abschicken --%>
                            <div class="side-by-side">
                                <button class="icon-pencil" type="submit">
                                    Einloggen
                                </button>
                            </div>
                        </div>

                        <%-- Fehlermeldungen --%>
                        <c:if test="${!empty signup_form.errors}">
                            <ul class="errors">
                                <c:forEach items="${signup_form.errors}" var="error">
                                    <li>${error}</li>
                                    </c:forEach>
                            </ul>
                        </c:if>
                    </form>
                </div>
            </div>        
        </div>
    </jsp:attribute>
</template:base>
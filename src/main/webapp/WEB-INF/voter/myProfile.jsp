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
        mein Profil
    </jsp:attribute>

    <jsp:attribute name="head">
    </jsp:attribute>

    <jsp:attribute name="menu">

    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="pt-5">
            <form method="POST">
                <div class="rounded p-3" style="background-color: white;">
                    <h4 class="mt-4 mb-4">Mein Profil</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="username">Username: </label>
                            <input id="username" name="username" class="form-control form-control-sm" type="text" readonly value="${voter.username}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="mail">E-Mail: </label>
                            <input id="mail" name="mail" class="form-control form-control-sm" type="text" value="${voter.mail}" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="prename">Vorname: </label>
                            <input id="prename" name="prename" class="form-control form-control-sm" type="text" value="${voter.prename}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="name">Name: </label>
                            <input id="name" name="name" class="form-control form-control-sm" type="text" value="${voter.name}" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="age">Alter: </label>
                            <input id="age" name="age" class="form-control form-control-sm" type="number" value="${voter.age}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="sex">Geschlecht: </label>
                            <select id="sex" name="sex" class="form-control form-control-sm" required>
                                <c:forEach items="${sexList}" var="sexValue">
                                    <option value="${sexValue}" ${sexValue == voter.sex ? 'selected' : ''}>
                                        ${sexValue}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success btn-sm mt-4 mb-2">Speichern</button>
                </div>
            </form>
        </div>
    </jsp:attribute>
</template:base>
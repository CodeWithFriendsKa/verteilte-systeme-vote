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
        Vote It
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="../css/voting.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">

    </jsp:attribute>

    <jsp:attribute name="content">

        <form method="POST">
            <div id="div" class="p-2" style="padding-top: 5em !important;">
                <div class="rounded m-2" style="background-color: white">
                    <div class="text-center">
                        <h2 class="mb-2 p-3">${vote.getCategory()}</h2>
                        <img id="picture" src="https://biowikis.com/wp-content/uploads/2018/07/Tina-Halada-Bio-Wiki-Net-Worth.jpg">
                        <p class="rounded p-4">${vote.getDescription()}</p>
                    </div>
                    <c:if test="${alreadyVoted == false}">
                        <div class="text-center">
                            <div class="btn-group">
                                <button id="notButton" name="not" value="not" type="submit" class="btn btn-primary btn-lg">NOT</button>
                                <button id="hotButton" name="hot" value="hot" type="submit" class="btn btn-danger btn-lg">HOT</button>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${alreadyVoted == true}">
                        <div class="text-center">
                            <span style="color:red;">
                                Up Votes
                                <i class="fab fa-hotjar ml-1"></i>
                                ${vote.getUpSize()}
                            </span>
                            <span class="ml-5" style="color:blue;">
                                Down Votes
                                <i class="fas fa-snowflake ml-1"></i>
                                ${vote.getDownSize()}
                            </span>
                        </div>
                    </c:if>
                </div>    
            </div>
        </form>

    </jsp:attribute>
</template:base>


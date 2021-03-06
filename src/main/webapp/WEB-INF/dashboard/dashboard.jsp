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

<!-- Google Graphics Dounat Chart -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load("current", {packages: ["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Typ', 'Votes'],
            ['Up Votes', ${myUpVotes}],
            ['Down Votes', ${myDownVotes}]
        ]);

        var options = {
            colors: ['red', 'blue'],
            pieHole: 0.3,
            backgroundColor: {fill: 'transparent'},
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
    }

    function buttonChanger(el) {
        var i;
        for (i = 0; i < 4; i++) {
            document.getElementById('button' + i).disabled = false;
        }
        document.getElementById(el.id).disabled = true;

        var j;
        for (j = 0; j < 4; j++) {
            document.getElementById('result' + j).hidden = true;
        }
        var text = 'result' + el.id.charAt(el.id.length - 1);
        document.getElementById(text).hidden = false;
    }

    function bChanger(ele) {
        document.getElementById('b0').disabled = false;
        document.getElementById('b1').disabled = false;
        document.getElementById('r0').hidden = true;
        document.getElementById('r1').hidden = true;
        if (ele.id.charAt(ele.id.length - 1) < 2) {
            document.getElementById(ele.id).disabled = true;
            document.getElementById('r' + ele.id.charAt(ele.id.length - 1)).hidden = false;
        }
    }

</script>



<template:base>
    <jsp:attribute name="title">
        Dashboard
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="../../css/dashboard.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">

    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="row">
            <div class="col-md-6 mt-5 p-2">
                <div class="dashBereich rounded p-2">
                    <h4>Meine Votings</h4>
                    <p>Erstellte Fragen: ${myVotes.size()}</p>
                    <c:if test="${myUpVotes != 0 || myDownVotes != 0}">
                        <div id="donutchart" style="width: 100%; height: 15em;"></div>
                    </c:if>

                    <c:if test="${myUpVotes == 0 && myDownVotes == 0}">
                        <div class="alert-danger p-2">
                            <p>Du hast bisher noch keine Votings.</p>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-md-6 mt-5 p-2">
                <div class="dashBereich rounded p-2">
                    <h4>Bestes / Schlechtestes Voting</h4>
                    <span>
                        <button id="button0" type="button" class="btn btn-success btn-sm" style="width:8em;" disabled onclick="buttonChanger(this)">insgesamt</button>
                        <button id="button1" type="button" class="btn btn-success btn-sm" style="width:8em;" onclick="buttonChanger(this)">diesen Monat</button>
                        <button id="button2" type="button" class="btn btn-success btn-sm" style="width:8em;" onclick="buttonChanger(this)">diese Woche</button>
                        <button id="button3" type="button" class="btn btn-success btn-sm" style="width:8em;" onclick="buttonChanger(this)">heute</button>
                    </span>
                    <div id="result0" >
                        <div class="row">
                            <div class="col-md-5 m-2">
                                <p class="text-danger">Gewinner gesamt:</p>
                                <a href="<c:url value="/app/voteit/${BestAllTimes.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${BestAllTimes.getDescription()}</p>
                                        <p>von: ${BestAllTimes.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:red;">
                                                Up Votes
                                                <i class="fab fa-hotjar ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:red;">
                                                ${BestAllTimes.getUpVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-5 m-2">
                                <p class="text-primary">Verlierer gesamt:</p>
                                <a href="<c:url value="/app/voteit/${WorstAllTimes.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${WorstAllTimes.getDescription()}</p>
                                        <p>von: ${WorstAllTimes.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:blue;">
                                                Down Votes
                                                <i class="fas fa-snowflake ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:blue;">
                                                ${WorstAllTimes.getDownVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div id="result1" hidden>
                        <div class="row">
                            <div class="col-md-5 m-2">
                                <p class="text-danger">Monatsgewinner:</p>
                                <a href="<c:url value="/app/voteit/${BestMonth.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${BestMonth.getDescription()}</p>
                                        <p>von: ${BestMonth.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:red;">
                                                Up Votes
                                                <i class="fab fa-hotjar ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:red;">
                                                ${BestMonth.getUpVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-5 m-2">
                                <p class="text-primary">Monatsverlierer:</p>
                                <a href="<c:url value="/app/voteit/${WorstMonth.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${WorstMonth.getDescription()}</p>
                                        <p>von: ${WorstMonth.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:blue;">
                                                Down Votes
                                                <i class="fas fa-snowflake ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:blue;">
                                                ${WorstMonth.getDownVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div id="result2" hidden>
                        <div class="row">
                            <div class="col-md-5 m-2">
                                <p class="text-danger">Wochengewinner:</p>
                                <a href="<c:url value="/app/voteit/${BestWeek.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${BestWeek.getDescription()}</p>
                                        <p>von: ${BestWeek.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:red;">
                                                Up Votes
                                                <i class="fab fa-hotjar ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:red;">
                                                ${BestWeek.getUpVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-5 m-2">
                                <p class="text-primary">Wochenverlierer:</p>
                                <a href="<c:url value="/app/voteit/${WorstWeek.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${WorstWeek.getDescription()}</p>
                                        <p>von: ${WorstWeek.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:blue;">
                                                Down Votes
                                                <i class="fas fa-snowflake ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:blue;">
                                                ${WorstWeek.getDownVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div id="result3" hidden>
                        <div class="row">
                            <div class="col-md-5 m-2">
                                <p class="text-danger">Tagesgewinner:</p>
                                <a href="<c:url value="/app/voteit/${BestDay.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${BestDay.getDescription()}</p>
                                        <p>von: ${BestDay.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:red;">
                                                Up Votes
                                                <i class="fab fa-hotjar ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:red;">
                                                ${BestDay.getUpVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-5 m-2">
                                <p class="text-primary">Tagesverlierer:</p>
                                <a href="<c:url value="/app/voteit/${WorstDay.getId()}"/>">
                                    <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                        <p>${WorstDay.getDescription()}</p>
                                        <p>von: ${WorstDay.getCreator().getUsername()}</p>
                                        <div class="row">
                                            <div class="md-6 ml-1" style="color:blue;">
                                                Down Votes
                                                <i class="fas fa-snowflake ml-1"></i>
                                            </div>
                                            <div class="md-6 ml-1" style="color:blue;">
                                                ${WorstDay.getDownVotes().size()}
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 mt-2 p-2">
                <div class="dashBereich rounded p-2" >

                    <h4 class='mr-2 d-inline'>Votings von: </h4>
                    <button id="b0" type="button" class="btn btn-success btn-sm d-inline" style="width:8em;" disabled onclick="bChanger(this)">mir</button>
                    <button id="b1" type="button" class="btn btn-success btn-sm d-inline" style="width:8em;" onclick="bChanger(this)">allen</button>

                    <div id="r0">
                        <div class="row" style="overflow-y: scroll; max-height: 16em; max-width: 100%;">
                            <c:forEach items="${myVotes}" var="vote">
                                <div class="col-md-4 p-3">
                                    <a href="<c:url value="/app/voteit/${vote.getId()}"/>">
                                        <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                            <h4>${vote.getDescription()}</h4>
                                            <p>von: ${vote.getCreator().getUsername()}</p>
                                            <div class="row">
                                                <div class="md-3 ml-1" style="color:red;">
                                                    Up Votes
                                                    <i class="fab fa-hotjar ml-1"></i>
                                                </div>
                                                <div class="md-3 ml-1" style="color:red;">
                                                    ${vote.getUpVotes().size()}
                                                </div>
                                                <div class="md-3 ml-5" style="color:blue;">
                                                    Down Votes
                                                    <i class="fas fa-snowflake ml-1"></i>
                                                </div>
                                                <div class="md-3 ml-1" style="color:blue;">
                                                    ${vote.getDownVotes().size()}
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>    
                        </div>
                    </div>
                    <div id="r1" hidden>
                        <div class="row" style="overflow-y: scroll; max-height: 16em; max-width: 100%;">
                            <c:forEach items="${allVotes}" var="vote">
                                <div class="col-md-4 p-3">
                                    <a href="<c:url value="/app/voteit/${vote.getId()}"/>">
                                        <div class="rounded p-3" style="border: 1px solid grey; background-color: rgb(255, 246, 242);">
                                            <h4>${vote.getDescription()}</h4>
                                            <p>von: ${vote.getCreator().getUsername()}</p>
                                            <c:if test="${!vote.userHasAlreadyVoted(currentVoter)}">
                                                <span style="color:green;">
                                                    <p style="margin:0;">Stimme jetzt ab!</p>
                                                </span>
                                            </c:if>
                                            <c:if test="${vote.userHasAlreadyVoted(currentVoter)}">
                                                <div class="row">
                                                    <div class="md-3 ml-1" style="color:red;">
                                                        Up Votes
                                                        <i class="fab fa-hotjar ml-1"></i>
                                                    </div>
                                                    <div class="md-3 ml-1" style="color:red;">
                                                        ${vote.getUpVotes().size()}
                                                    </div>
                                                    <div class="md-3 ml-5" style="color:blue;">
                                                        Down Votes
                                                        <i class="fas fa-snowflake ml-1"></i>
                                                    </div>
                                                    <div class="md-3 ml-1" style="color:blue;">
                                                        ${vote.getDownVotes().size()}
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>    
                        </div>
                    </div>


                </div>
            </div>
        </div>

    </jsp:attribute>
</template:base>
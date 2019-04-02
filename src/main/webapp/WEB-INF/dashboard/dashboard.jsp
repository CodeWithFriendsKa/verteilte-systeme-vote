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
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Typ', 'Votes'],
          ['Up Votes',     ${myUpVotes}],
          ['Down Votes', ${myDownVotes}]
        ]);

        var options = {
          colors: ['red', 'blue'],
          pieHole: 0.4,
          backgroundColor: { fill:'transparent' },
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    </script>
    
    <script  type="text/javascript">
        
            function buttonChanger(id){
                for(int i = 0; i < 4; i++){
                    var text = 'button' + i;
                    var e = document.getElementById(text);
                    e.disabled = false;
                }
            }

    </script>


<template:base>
    <jsp:attribute name="title">
        Dashboard
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="../css/dashboard.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="row">
            <div class="col-md-6 mt-5 p-2">
                <div class="dashBereich rounded p-2">
                    <h4>Meine Votings</h4>
                    <p>Erstellte Fragen: ${myVotes.size()}</p>
                    <div id="donutchart" style="width: 100%; height: 15em;"></div>
                </div>
            </div>
            <div class="col-md-6 mt-5 p-2">
                <div class="dashBereich rounded p-2">
                     <h4>Bestes / Schlechtestes Voting</h4>
                     <span>
                         <button id="button0" type="button" class="btn btn-primary btn-sm" style="width:8em;" disabled onclick="buttonChanger('bestWorstVotings0')">insgesamt</button>
                         <button id="button1" type="button" class="btn btn-primary btn-sm" style="width:8em;" onclick="buttonChanger('bestWorstVotings1')">diesen Monat</button>
                         <button id="button2" type="button" class="btn btn-primary btn-sm" style="width:8em;" onclick="buttonChanger('bestWorstVotings2')">diese Woche</button>
                         <button id="button3" type="button" class="btn btn-primary btn-sm" style="width:8em;" onclick="buttonChanger('bestWorstVotings3')">heute</button>
                     </span>
                     <div id="result0" >
                         <div class="row">
                             <div class="col-md-5 m-2">
                                 <p class="text-danger">Bestes Vote:</p>
                                 <div class="rounded p-3" style="border: 1px solid grey;">
                                    <p>${BestAllTimes.getDescription()}</p>
                                    <p>von: ${BestAllTimes.getCreator().getUsername()}</p>
                                    <div class="row">
                                        <div class="md-6 ml-1" style="color:red;">
                                            Up Votes
                                            <i class="fab fa-hotjar ml-1"></i>
                                        </div>
                                        <div class="md-6 ml-1" style="color:red;">
                                            ${BestAllTimes.getUpSize()}
                                        </div>
                                    </div>
                                </div>
                             </div>
                             <div class="col-md-5 m-2">
                                 <p class="text-primary">Schlechtestes Vote:</p>
                                 <div class="rounded p-3" style="border: 1px solid grey;">
                                    <p>${WorstAllTimes.getDescription()}</p>
                                    <p>von: ${WorstAllTimes.getCreator().getUsername()}</p>
                                    <div class="row">
                                        <div class="md-6 ml-1" style="color:blue;">
                                            Down Votes
                                            <i class="fas fa-snowflake ml-1"></i>
                                        </div>
                                        <div class="md-6 ml-1" style="color:blue;">
                                            ${WorstAllTimes.getDownSize()}
                                        </div>
                                    </div>
                                </div>
                             </div>
                         </div>
                     </div>
                     <div id="result1" style="display:none">Result1</div>
                     <div id="result2" style="display:none">Result2</div>
                     <div id="result3" style="display:none">Result3</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 mt-2 p-2">
                <div class="dashBereich rounded p-2" >
                    <h4>Neue Votings</h4>
                    
                    <div class="row" style="overflow-y: scroll; max-height: 16em; max-width: 100%;">
                        <c:forEach items="${myVotes}" var="vote">
                            <div class="col-md-4 p-3">
                                <div class="rounded p-3" style="border: 1px solid grey;">
                                    <h4>${vote.getDescription()}</h4>
                                    <p>von: ${vote.getCreator().getUsername()}</p>
                                    <div class="row">
                                        <div class="md-3 ml-1" style="color:red;">
                                            Up Votes
                                            <i class="fab fa-hotjar ml-1"></i>
                                        </div>
                                        <div class="md-3 ml-1" style="color:red;">
                                            ${vote.getUpSize()}
                                        </div>
                                        <div class="md-3 ml-5" style="color:blue;">
                                            Down Votes
                                            <i class="fas fa-snowflake ml-1"></i>
                                        </div>
                                        <div class="md-3 ml-1" style="color:blue;">
                                            ${vote.getDownSize()}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>    
                    </div>
                    
                    
                </div>
            </div>
        </div>
        
    </jsp:attribute>
</template:base>
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
        Create Vote
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="../css/voting.css"/>" />
        <script>
            /**
        function addimage() {
          var img = document.createElement("img");
          img.src = "http://bricksplayground.webs.com/brick.PNG";
          img.height = 50;
          img.width = 100;

          var class_name = "foo";
          img.setAttribute("class", class_name);

          document.getElementById("myDiagramDiv").appendChild(img);
           $(img).draggable();*/
        }
        </script>   
    </jsp:attribute>
        
    <jsp:attribute name="menu">

    </jsp:attribute>

    <jsp:attribute name="content">
            
    <div id="div" class="p-2" style="padding-top: 5em !important;">
        <form method="POST" enctype="multipart/form-data">
            <div class="rounded m-2" style="background-color: white">
                <div class="text-center">
                    <div class="mb-2 p-3">
                        <label for="category-select"><h2>Wähle eine Kategorie:</h2></label>
                              <select id="category" name="category" class="form-control form-control-sm" value="${upDownVote.category}" required>       
                                 <c:forEach items="${categoryList}" var="categoryValue">
                                      <option value="${categoryValue}" ${value == upDownVote.category ? 'selected' : ''}>
                                      ${categoryValue}
                                      </option>
                                 </c:forEach>
                              </select> 
                     </div>
                     <div class="text-center">
                        <input type="file" name="myFile" />
                        <p class="rounded p-4"></p>
                        <input type="text" id="description" name="description" placeholder="Beschreibung einfügen:" value="${upDownVote.description}">
                        <button id="submit-button" type="submit" class="btn btn-success">Speichern</button>                         
                     </div>
                </div> 
            </div> 
         </form>                
    </div>
        
    </jsp:attribute>
</template:base>
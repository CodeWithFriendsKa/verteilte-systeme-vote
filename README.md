# verteilte-systeme-vote
# Tripple Programming
# author Rouven Brost
# author Christopher Pschibila
# author codekeks (Tamino Fischer)

Die Anwendung in diesem Repository wurde für die Vorlesung „Verteilte Systeme“ erstellt. 
Es stellt die Serveranwendung dar. Hierbei handelt es sich um eine Multy-Page-Applikation, 
die in allen gängigen Webbrowsern läuft. Da sie aber in knapp einer Woche geschrieben wurde, 
haben wir sie nur für Firefox optimiert. Die Anwendung stellt eine Voting-Applikation dar, 
in die registrierte Benutzer Votings einstellen können und über andere Votings abstimmen können. 
Für den vollen Funktionsumfang ist die Datei „Testdokumentation“ zu lesen.

Um die Anwendung ausführen zu können wird Folgendes benötigt:
-	Apache TomEE
-	Derby DB
-	Java Version 8 oder höher
-	Netbeans, der auf Port 8080 läuft

Diese Anwendung ist auf folgendem Repository zu finden:
-	https://github.com/CodeWithFriendsKa/verteilte-systeme-vote

Die dafür geschriebene Client-Konsolen-Anwendung zum Abrufen des dafür geschriebenen 
REST-Webservice ist in folgendem Repository zu finden:
-	https://github.com/CodeWithFriendsKa/verteilte-systeme-vote-console-client

Um die Anwendung zu starten sind folgende Schritte auszuführen
1.	Repository clonen
2.	Server und DB in Netbeans einbinden
3.	DB starten
4.	Server starten / Applikation starten
5.	Index.html
6.	https:\\localhost:8443\vote   (am besten in Firefox öffnen)


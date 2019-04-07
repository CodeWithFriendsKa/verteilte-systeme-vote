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
4.  die Variable USER_PATH in der Datei Createdemo.java anpassen:
4.1  Die Variable ist ein String und enthält den absoluten Pfad, bis einschließlich zu dem Ordner, indem die Anwendung liegt
4.2  Bitte dort den absoluten Pfad einfügen
4.3  Wenn der Pfad richtig angegeben wurde, können die Testdaten richtig angelegt werden, sodass auch auch initial Voter und UpDownVotes angelegt werden können. Dies sollte das Testen und Durch die Anwendung klicken erleichtern
5.	Server starten / Applikation starten
6.	Index.html
7.	https:\\localhost:8443\vote   (am besten in Firefox öffnen)

Einloggen oder Registrieren
Um die Anwendung zu benutzen muss man ein registierter Nutzer sein. Wir haben schon 10 Dummy-Benutzer angelegt. Diese haben die  durchnummerierten Benutzernamen von "MaMu0" bis "MaMu9" und haben alle dasselbe Passwort "123". Entweder man verwendet einen dieser Benutzer oder man Registiert sich selbst. Der Vorteil bei den schon angelegten Benutzern ist, dass diese per Zufallsvariable zugeteilte Votings haben. Wenn man sich neu registriert, dann sieht man auf seinem Dashboard noch nicht so viel. Aber man kann ja neue Votings anlegen und auch abstimmen.

Viel Spaß beim voten!


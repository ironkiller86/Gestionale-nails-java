"# Gestionale-nails-java" 
Sto sviluppando un gestionale crm. Nello specifico si tratta di un'applicazione Web, per il settore estetico,
che permette la gestione e lo storico degli appuntamenti, con relative foto e altri dati, e la tracciatura della contabilità.Essendo pensata per essere usata da più utenti contemporanemanete, ho creato un sistema che permetta per ogni user loggato di vedere ovviamente solo i sui dati.Inoltre sto sviluppando una dashboard dove l'utentre admin, possa gestire tutti gli utenti che utilizzano l'applicazione e dove si possa avere una tracciatura delle varie sessioni attive e non.
L'applicazione è realizzata usando alcune delle tecnologie più conosciute che rientrano nelle specifiche di Java
Enterprise Edition, utilizzando il famosissimo Pattern MVC. Nello specifico la parte view è stata
realizzata usando pagine JSP, tag JSTL, il framework Bootstrap 4 ,Javascript/Jquery, Fullcalendar (un calendario fatto in Jquery).
Per realizzare la parte di Controller (dispatcher, logout, login ecc) ho utilizzato le Servlet, e per la parte
di logica di business ho utilizzato gli EJB (Enterprise JavaBeans), i quali fornisco alcune interfacce per
gestire la persistenza dei dati. A tal fine ho utilizzato il database Mysql interfacciandomi con esso
tramite il framework JPA.Fullcalendar e altri servizi lato server comunicano tra loro tramite chiamate Ajax che permetto lo scambio di dei file json , inoltre è possibilie esportare/stampare i vari report in formato pdf (per fare cio ho usato una libreria java per la creazione/gestione dei pdf chiamata Itext.)

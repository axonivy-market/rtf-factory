# RTF Factory

Die RTF Factory ist ein einfach zu bedienendes Dokumentengenerierungstool, das Dokumentvorlagen im RTF-Format mit Prozessdaten und Ivy-Makrofunktionen erweitert. 
RTF-Dokumentvorlagen können mit MS Word erstellt werden. Verwende einfach Ivy-Makros als Platzhalter im Dokument.

Ein Prozessdatenattribut kann folgendermaßen eingefügt werden:
```
<%=in.customer.name%>
```
Inhalte aus dem CMS können mit einem Makro, das die Funktion cms.co aufruft, erweitert werden:
```
<%=ivy.cms.co("/labels/greeting")%>
```

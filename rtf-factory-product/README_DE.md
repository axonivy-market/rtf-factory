<!--
Dear developer!     

When you create your very valuable documentation, please be aware that this Readme.md is not only published on github. This documentation is also processed automatically and published on our website. For this to work, the two headings "Demo" and "Setup" must not be changed
-->

# RTF Fabrik

Das RTF Fabrik ist ein leichtes Dokument Erzeuger expandiert dokumentiert jener
Vorlagen herein rft #formatieren mit verarbeiten #Daten und Efeu #Makro
Aufgaben. Rtf Dokumentieren Vorlagen können sein geschafft benutzen
beispielsweise FRAU Wort. Einfach benutzen Efeu #Makro da Platzhalter in dem
Dokument.

#Ein Arbeitsgang #Daten misst bei kann sein #einfügen folgendermaßen:
```
<%=in.customer.name%>
```
#Einverstanden Objekte von die CMS können sein expandiert mit einem #Makro jene
Anrufe die cms.co Funktionieren
```
<%=ivy.cms.co("/labels/greeting")%>     
```


## Demo

Das RTF Fabrik versieht eine ledige Methode zu generieren und herunterladen ein
Dokument. Du willst gewöhnlich diese Methode in einen Nutzer Task rufen. Die
Dokument Vorlagen können sein fertiggebracht herein die CMS oder irgendwo in das
Dateien System.

![Demo-Zwiegespräch](images/DemoDialog.png)

Das vervollständigen Skript Scherbe jene Ladungen eine Vorlage und ruft den
#Amplitudendehner

```
import ch.ivyteam.ivy.RtfFactory.ExportFromCms;
import ch.ivyteam.ivy.RtfFactory.RtfExpander;
RtfExpander.sendExpandedRtfFile(ExportFromCms.export("my-document-template", "rtf"), in);
```

Das resultierend #sampeln dokumentieren

![Generiert-Dokumentieren](images/GeneratedDocument.png)





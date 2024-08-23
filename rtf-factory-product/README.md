<!--
Dear developer!     

When you create your very valuable documentation, please be aware that this Readme.md is not only published on github. This documentation is also processed automatically and published on our website. For this to work, the two headings "Demo" and "Setup" must not be changed
-->

# RTF Factory

The RTF Factory is a very easy document generator that expands document templates in rft format with process data and ivy macro functions.
Rtf document templates can be created using MS Word for example. Simply use ivy macros as placeholders in the document.

To insert a process data attribute the macro will look like this 
```
<%=in.customer.name%>
```
Content objects from the CMS can be expanded with a macro that calls the cms.co function  
```
<%=ivy.cms.co("/labels/greeting")%>     
```


## Demo

The RTF Factory provides a single method to generate an download a document. You will usually call this method in a user task.
The document templates can be managed in the CMS or somewhere else in the files system. 

![Demo-Dialog](images/DemoDialog.png)

The complete script fragment to load a template and call the expander may look like this

```
import ch.ivyteam.ivy.RtfFactory.ExportFromCms;
import ch.ivyteam.ivy.RtfFactory.RtfExpander;
RtfExpander.sendExpandedRtfFile(ExportFromCms.export("my-document-template", "rtf"), in);
```

The resulting expanded sample document

![Generated-Document](images/GeneratedDocument.png)





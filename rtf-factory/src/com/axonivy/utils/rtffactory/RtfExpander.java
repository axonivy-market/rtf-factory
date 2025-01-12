package com.axonivy.utils.rtffactory;

import static com.axonivy.utils.rtffactory.Constants.ATTACHMENT_AND_FILENAME;
import static com.axonivy.utils.rtffactory.Constants.CONTENT_DISPOSITION;
import static com.axonivy.utils.rtffactory.Constants.CONTENT_TYPE;
import static com.axonivy.utils.rtffactory.Constants.TXT_AND_RTF_TYPE;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.exceptions.IvyScriptException;
import ch.ivyteam.ivy.scripting.exceptions.compiler.IvyScriptParserException;
import ch.ivyteam.ivy.scripting.expander.EmbeddedScriptExpanderFactory;
import ch.ivyteam.ivy.scripting.expander.IEmbeddedScriptExpander;
import ch.ivyteam.ivy.scripting.language.IIvyScriptContext;
import ch.ivyteam.ivy.scripting.language.IIvyScriptEngine;
import ch.ivyteam.ivy.scripting.types.IIvyClass;
import ch.ivyteam.ivy.scripting.util.IvyScriptProcessVariables;

public class RtfExpander {

	// send rtf file as response to the web browser
	public static void sendExpandedRtfFile(java.io.File file, Object in)
			throws IOException, IvyScriptParserException, IvyScriptException {
		if (file == null) {
			Ivy.log().error("Rtf template file is null");
			return;
		}

		String str = new String(Files.readAllBytes(file.toPath()));
		String expandedStr = expand(str, in);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.reset();
		response.setHeader(CONTENT_TYPE, TXT_AND_RTF_TYPE);
		response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_AND_FILENAME.concat(file.getName()));
		response.setContentLength(expandedStr.length());
		OutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(expandedStr.getBytes());
		responseOutputStream.flush();
		responseOutputStream.close();
		facesContext.responseComplete();
	}

	public static String expand(String text, Object in) throws IvyScriptParserException, IvyScriptException {
		var request = Ivy.request();
		if (request == null) {
			throw new NullPointerException("Request is null.");
		}
		IEmbeddedScriptExpander macroExpander = EmbeddedScriptExpanderFactory.createExpander();
		IIvyScriptContext context = request.getContext(null);
		IIvyScriptEngine engine = request.getScriptEngine();
		IIvyClass<?> data = engine.getClassRepository().getIvyClassOf(in);
		context.declareVariable("in", data);
		context.setObject(IvyScriptProcessVariables.IN.getVariableName(), in);

		return macroExpander.expandEmbeddedScripts(text, context, engine, 50);
	}
}

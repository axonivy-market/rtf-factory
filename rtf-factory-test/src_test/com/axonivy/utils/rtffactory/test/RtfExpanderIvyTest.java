package com.axonivy.utils.rtffactory.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import com.axonivy.utils.rtffactory.RtfExpander;

import ch.ivyteam.ivy.environment.IvyTest;
import com.axonivy.utils.rtffactory.Data;

/**
 * Test expander method
 */
@IvyTest
public class RtfExpanderIvyTest {

	@Test
	public void testExpandRtf() {
		String rtfcontent = "Hallo Rtf <%=in.firstname%> <%=in.name%>  \\par<%=ivy.cms.co(\"/greeting\")%>! ";
		Data in = new Data();
		in.setName("Lewis");
		in.setFirstname("Carl");
		String expanded = Strings.EMPTY;
		try {
			expanded = RtfExpander.expand(rtfcontent, in);
		} catch (Exception ex) {
		}

		assertThat(expanded.contains("Carl Lewis")).isTrue();
		assertThat(expanded.contains("Thank you for coming Carl!")).isTrue();
	}
}

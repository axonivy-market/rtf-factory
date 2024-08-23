package rtf.factory.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.RtfFactory.RtfExpander;
import ch.ivyteam.ivy.environment.IvyTest;

/**
 * Test expander method
 */
@IvyTest
public class RtfExpanderIvyTest{

  @Test
  public void testExpandRtf() {
    String rtfcontent = "Hallo Rtf <%=in.firstname%> <%=in.name%>  \\par<%=ivy.cms.co(\"/greeting\")%>! ";
    Data in = new Data();
    in.setName("Lewis");
    in.setFirstname("Carl");
    String expanded = "";
    try {
      expanded = RtfExpander.expand(rtfcontent, in);
    } catch (Exception ex) {}

    assertThat(expanded.contains("Carl Lewis")).isTrue();
    assertThat(expanded.contains("Thank you for coming Carl!")).isTrue();
  }
}

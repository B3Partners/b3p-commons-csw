/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.server;

import java.io.IOException;

import nl.b3p.csw.client.CswRequestCreator;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author Erik van de Pol. Most of this class by Chris van Lith.
 */
public class GeoNetworkCswServer implements CswServable {

  //public final static String DEFAULT_LOGIN_URL = "";
  protected static Log log = null;
  protected static String cswUrl = null;
  protected static String loginUrl = null;
  protected static String cswUser = null;
  protected static String cswPassword = null;
  protected static String xslPath = null;
  protected static String defaultXsl = null;
  protected static final String host = AuthScope.ANY_HOST;
  protected static final int port = AuthScope.ANY_PORT;
  protected static final int RTIMEOUT = 20000;
  protected static Cookie[] cookies = null;

  protected CswRequestCreator cswRequestCreator = null;

  public GeoNetworkCswServer() {
    log = LogFactory.getLog(this.getClass());
    log.info("Initializing " + this.getClass().getSimpleName());

//    xslPath = config.getServletContext().getRealPath("/xml");
//
//    defaultXsl = getConfigValue(config, "defaultXsl", null);
//    cswUrl = getConfigValue(config, "csw", null);
//    loginUrl = getConfigValue(config, "login", null);
//    cswUser = getConfigValue(config, "username", null);
//    cswPassword = getConfigValue(config, "password", null);

    cswRequestCreator = new CswRequestCreator();
  }

//
//    static String getConfigValue(ServletConfig config, String parameter, String defaultValue) {
//        String tmpval = config.getInitParameter(parameter);
//        String retval = tmpval;
//        if (tmpval == null || tmpval.trim().length() == 0) {
//            retval = defaultValue;
//        }
//        log.info("config init: " + parameter + " = " + retval);
//        return retval;
//    }
//
  public boolean login(String url, String username, String password) throws Exception {
    if (url == null || username == null || password == null) {
      return false;
    }

    StringBuffer loginMessage = new StringBuffer();
    loginMessage.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    loginMessage.append("<request>");
    loginMessage.append("<username>");
    loginMessage.append(username);
    loginMessage.append("</username>");
    loginMessage.append("<password>");
    loginMessage.append(password);
    loginMessage.append("</password>");
    loginMessage.append("</request>");

    String responseMessage = httpPostCswRequest(loginMessage.toString(), url);

    if (responseMessage != null) {
      return true;
    }
    return false;
  }

  public String search(String q, String p, String o, String s, String t, String xsl) throws IOException {
    String xml = cswRequestCreator.createCswRequest(q, p, o, s, t);

    //String xsl = request.getParameter("x");
    if (xsl == null || xsl.trim().length() == 0) {
      xsl = defaultXsl;
    }

    String sr = null;

    try {
      log.debug("sending xml:");
      log.debug(xml);
      if (login(loginUrl, cswUser, cswPassword)) {
        sr = httpPostCswRequest(xml, cswUrl);
      } else {
        sr = httpPostCswRequest(xml, cswUrl, cswUser, cswPassword);
      }
    } catch (Exception e) {
      log.error("Exception bij ophalen csw: " + cswUrl, e);
    }

    return sr;
  }

  private String httpPostCswRequest(String request, String url) throws IOException {
    return httpPostCswRequest(request, url, null, null);
  }

  private String httpPostCswRequest(String request, String url, String username, String password) throws IOException {
    HttpState initialState = new HttpState();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        initialState.addCookie(cookies[i]);
      }
    }

    HttpClient client = new HttpClient();
    client.getHttpConnectionManager().getParams().setConnectionTimeout(RTIMEOUT);
    client.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
    //client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

    if (username != null && password != null) {
      client.getParams().setAuthenticationPreemptive(true);
      Credentials defaultcreds = new UsernamePasswordCredentials(username, password);
      AuthScope authScope = new AuthScope(host, port);
      initialState.setCredentials(authScope, defaultcreds);
    }

    client.setState(initialState);

    // Create a method instance.
    PostMethod method = new PostMethod(url);
    method.setRequestHeader("Accept", "text/xml");
    method.setRequestEntity(new StringRequestEntity(request, "text/xml", "UTF-8"));

    try {
      int statusCode = client.executeMethod(method);
      if (statusCode != HttpStatus.SC_OK) {
        log.error("Host: " + url + " error: " + method.getStatusLine().getReasonPhrase());
        return null;
      }

      cookies = client.getState().getCookies();

      // Display the cookies
      log.debug("Present cookies: ");
      for (int i = 0; i < cookies.length; i++) {
        log.debug(" - " + cookies[i].toExternalForm());
      }

      return method.getResponseBodyAsString();
    } finally {
      // Release the connection.
      method.releaseConnection();
    }

  }


}

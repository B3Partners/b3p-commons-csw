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

    protected static Log log;
    protected static final String host = AuthScope.ANY_HOST;
    protected static final int port = AuthScope.ANY_PORT;
    protected static final int RTIMEOUT = 20000;
    protected static Cookie[] cookies = null;
    protected String cswUrl;
    protected String loginUrl;
    protected String cswUser;
    protected String cswPassword;
    protected CswRequestCreator cswRequestCreator = null;

    public GeoNetworkCswServer(String loginUrl, String cswUrl, String cswUser, String cswPassword) {
        log = LogFactory.getLog(this.getClass());
        log.info("Initializing " + this.getClass().getSimpleName());

        this.loginUrl = loginUrl;
        this.cswUrl = cswUrl;
        this.cswUser = cswUser;
        this.cswPassword = cswPassword;

        cswRequestCreator = new CswRequestCreator();
    }

    public String search(String cswRequestXml) throws IOException {
        try {
            if (login(loginUrl, cswUser, cswPassword)) {
                return httpPostCswRequest(cswRequestXml, cswUrl);
            } else {
                return httpPostCswRequest(cswRequestXml, cswUrl, cswUser, cswPassword);
            }
        } catch (IOException e) {
            throw new IOException("Exception bij ophalen csw: " + cswUrl, e);
        }
    }

    protected boolean login(String url, String username, String password) throws IOException {
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

        String responseMessage = null;
        try {
            responseMessage = httpPostCswRequest(loginMessage.toString(), url);
        } catch (IOException ex) {
            log.error("Error logging in.", ex);
        }

        boolean loginSuccess = responseMessage != null;

        return loginSuccess;
    }

    protected String httpPostCswRequest(String request, String url) throws IOException {
        return httpPostCswRequest(request, url, null, null);
    }

    protected String httpPostCswRequest(String request, String url, String username, String password) throws IOException {
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

        String responseString = null;
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                throw new IOException("HttpStatus: " + statusCode + "; Host: " + url + "; Reason: " + method.getStatusLine().getReasonPhrase());
            }

            cookies = client.getState().getCookies();

            // Display the cookies
            log.debug("Present cookies: ");
            for (int i = 0; i < cookies.length; i++) {
                log.debug(" - " + cookies[i].toExternalForm());
            }

            responseString = method.getResponseBodyAsString();

        } finally {
            // Release the connection.
            method.releaseConnection();
            return responseString;
        }

    }
}

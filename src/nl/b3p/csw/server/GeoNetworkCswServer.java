/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.server;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import nl.b3p.csw.client.JDOMResponseListener;
import nl.b3p.csw.client.OwsException;

import nl.b3p.csw.client.ResponseListenable;
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
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol. Most of this class by Chris van Lith.
 */
public class GeoNetworkCswServer implements CswServable<Document> {

    protected static Log log;
    protected static final String host = AuthScope.ANY_HOST;
    protected static final int port = AuthScope.ANY_PORT;
    protected static final int RTIMEOUT = 20000;
    protected static Cookie[] cookies = null;
    protected String cswUrl;
    protected String loginUrl;
    protected String cswUser;
    protected String cswPassword;

    protected ResponseListenable<Document> responseListenable = null;

    public ResponseListenable getResponseListenable() {
        return responseListenable;
    }

    public void setResponseListenable(ResponseListenable responseListenable) {
        this.responseListenable = responseListenable;
    }

    public GeoNetworkCswServer(String loginUrl, String cswUrl, String cswUser, String cswPassword) {
        this(loginUrl, cswUrl, cswUser, cswPassword, new JDOMResponseListener());
    }

    public GeoNetworkCswServer(String loginUrl, String cswUrl, String cswUser, String cswPassword, ResponseListenable responseListenable) {
        log = LogFactory.getLog(this.getClass());
        log.info("Initializing " + this.getClass().getSimpleName());

        this.loginUrl = loginUrl;
        this.cswUrl = cswUrl;
        this.cswUser = cswUser;
        this.cswPassword = cswPassword;

        this.responseListenable = responseListenable;
    }

    public Document doRequest(String cswRequestXml) throws IOException, JDOMException, JAXBException, OwsException {
        if (login(loginUrl, cswUser, cswPassword)) {
            // standard Geonetwork login failed. Exception has probably been thrown in method above.
            return httpPostCswRequest(cswRequestXml, cswUrl);
        } else {
            // generic UsernamePasswordCredentials login try:
            return httpPostCswRequest(cswRequestXml, cswUrl, cswUser, cswPassword);
        }
    }

    protected boolean login(String url, String username, String password) throws IOException, JDOMException, JAXBException, OwsException {
        // Geonetwork uses a separate login message that must be sent up front.
        if (url == null || username == null || password == null) {
            return false;
        }

        StringBuilder loginMessage = new StringBuilder();
        loginMessage.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        loginMessage.append("<request>");
        loginMessage.append("<username>");
        loginMessage.append(username);
        loginMessage.append("</username>");
        loginMessage.append("<password>");
        loginMessage.append(password);
        loginMessage.append("</password>");
        loginMessage.append("</request>");

        Document responseMessage = null;
        try {
            responseMessage = httpPostCswRequest(loginMessage.toString(), url);
        } catch (IOException ex) {
            throw new IOException("Error logging in on csw-server: " + ex.getLocalizedMessage());
        }

        boolean loginSuccess = responseMessage != null;

        return loginSuccess;
    }

    protected Document httpPostCswRequest(String request, String url) throws IOException, JDOMException, JAXBException, OwsException {
        return httpPostCswRequest(request, url, null, null);
    }

    protected Document httpPostCswRequest(String request, String url, String username, String password) throws IOException, JDOMException, JAXBException, OwsException {
        HttpState initialState = new HttpState();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                initialState.addCookie(cookie);
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

        InputStream responseStream = null;
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                throw new IOException("Url: " + url + ". Reason: " + method.getStatusLine());
            }

            cookies = client.getState().getCookies();

            // Display the cookies
            log.debug("Present cookies: ");
            for (Cookie cookie : cookies) {
                log.debug(" - " + cookie.toExternalForm());
            }

            responseStream = method.getResponseBodyAsStream();

            return responseListenable.handleResponse(responseStream);
        } finally {
            // Release the connection.
            method.releaseConnection();
            //return responseStream;
        }

    }
}

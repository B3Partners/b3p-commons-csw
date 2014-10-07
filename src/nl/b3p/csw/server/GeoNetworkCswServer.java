/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.server;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URL;
import java.util.Arrays;
import javax.xml.bind.JAXBException;
import nl.b3p.commons.services.B3PCredentials;
import nl.b3p.commons.services.HttpClientConfigured;
import nl.b3p.csw.client.JDOMResponseListener;
import nl.b3p.csw.client.OwsException;
import nl.b3p.csw.client.ResponseListenable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol. Most of this class by Chris van Lith.
 */
public class GeoNetworkCswServer implements CswServable<Document> {

    protected static Log log;
    protected static final int RTIMEOUT = 20000;
    protected CookieStore cookieStore = null;
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
        B3PCredentials credentials = new B3PCredentials();
        credentials.setUserName(username);
        credentials.setPassword(password);
        credentials.setUrl(url);
        credentials.setPreemptive(true);
        
        HttpClientConfigured client = new HttpClientConfigured(credentials, RTIMEOUT);
        HttpClientContext context = client.getContext();

        // cookies
        if (cookieStore != null) {
            context.setCookieStore(cookieStore);
        }
        
        HttpPost post = new HttpPost(url);
        post.addHeader("Accept", "text/xml");
        post.setEntity(new StringEntity(request, ContentType.TEXT_XML));

        HttpResponse response = client.execute(post);

        try {

            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            
            if (statusCode != 200) {
                throw new IOException("Url: " + url + ". Reason: " + response.getStatusLine());
            }

            cookieStore = context.getCookieStore();
            // Display the cookies
            log.debug("Present cookies: ");
            if (cookieStore != null) {
                for (Cookie cookie : cookieStore.getCookies()) {
                    log.debug(" - " + cookie.toString());
                }
            }
            
            return responseListenable.handleResponse(entity.getContent());
            
        } finally {
            if (response instanceof CloseableHttpResponse) {
                ((CloseableHttpResponse)response).close();
            }
        }

    }
}

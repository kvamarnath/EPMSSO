/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epmsso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hyperion.css.CSSSecurityAgentIF;
import com.hyperion.css.log.CSSLog;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pablo Bryan
 */
public class EPMSSO implements CSSSecurityAgentIF {

    /**
     * 
     * @param req
     * @param res
     * @return
     * @throws Exception 
     */
    public String getUserName(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        
        HttpSession session = req.getSession();
        
        PersonDTO person = null;
        
        Cookie authCookie = getCookie(req, EPMCommonConstants.AUTH_COOKIE_NAME);

        try {

            if (authCookie == null) {
                CSSLog.log(this.getClass().toString(), "getUserName", "No EPMSSO Cookie Found");                
                return null;
            } else {
                // cookie is present
                CSSLog.log(this.getClass().toString(), "getUserName", "EPMSSO Cookie present...authorizing");
                
                person = doAuthorization(req, res);
				
                		
                if (person.getPersonId() != null || person.getEmployeeId() != null || person != null) {
                    CSSLog.log(this.getClass().toString(), "getUserName", "returning successfully with authenticated user from Cookie");            				
                    session.setAttribute("personObject", person);
		
                    return person.getEmailAddress();
                }
                return null;
            }
        } catch (Exception ex) {
            CSSLog.log(this.getClass().toString(), "getUserName", ex.getMessage());
            return null;
        }
    }

    /**
     * 
     * @param req
     * @param res
     * @return
     * @throws Exception 
     */
    public String getPassword(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        return null;
    }

    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException 
     */
    private PersonDTO doAuthorization(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        long timeFilterStart = System.currentTimeMillis();

        CSSLog.log(this.getClass().toString(), "doAuthorization", "START - enter doAuthorization");
        
        PersonDTO person = new PersonDTO();

        try {
            CSSLog.log(this.getClass().toString(), "doAuthorization", "START try...");

            Cookie authCookie = getCookie(request, EPMCommonConstants.AUTH_COOKIE_NAME);
            
            String clientIP = request.getHeader("X-Forwarded-For");

            CSSLog.log(this.getClass().toString(), "doAuthorization", "IP Address received is " + clientIP);

            if (clientIP == null || clientIP.equals("")) {
                clientIP = request.getRemoteAddr();
                CSSLog.log(this.getClass().toString(), "doAuthorization", "IP Address blank, resolving from request.getRemoteAddr(): " + clientIP);
            }

            String url = "Build URL to custom SSO solution passing any required parameters to validate cookie";

            CSSLog.log(this.getClass().toString(), "doAuthorization", "url: " + url);

            CSSLog.log(this.getClass().toString(), "doAuthorization", "Sending url authorization request...");

            URLConnection urlConnect = null;

            URL urlConn = null;
            urlConn = new URL(null, url, new sun.net.www.protocol.https.Handler());

            CSSLog.log(this.getClass().toString(), "doAuthorization", "host: " + urlConn.getHost());

            urlConnect = urlConn.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));

            CSSLog.log(this.getClass().toString(), "doAuthorization", "URL input " + (in != null ? "has value" : "has no vlaue"));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine: " + inputLine);

                if (inputLine.startsWith("status")) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine status: " + cutString(inputLine));
                    person.setStatus(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.FIRST_NAME)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine first name: " + cutString(inputLine));
                    person.setFirstName(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.LAST_NAME)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine LAST_NAME: " + cutString(inputLine));
                    person.setLastName(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.PERSON_ID)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine PERSON_ID: " + cutString(inputLine));
                    person.setPersonId(Long.parseLong(cutString(inputLine)));

                } else if (inputLine.startsWith(EPMCommonConstants.PERSON_TYPE_CODE)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine PERSON_TYPE_CODE: " + cutString(inputLine));
                    person.setPersonType(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.EMP_ID)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine EMP_ID: " + cutString(inputLine));
                    person.setEmployeeId(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.NICK_NAME)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine NICK_NAME: " + cutString(inputLine));
                    person.setNickName(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.DIVISION_NUM)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine DIVISION_NUM: " + cutString(inputLine));
                    person.setDivisionNum(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.DEPT_NUM)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine DEPT_NUM: " + cutString(inputLine));
                    person.setDepartmentNum(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.IS_MANAGER)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine IS_MANAGER: " + cutString(inputLine));
                    person.setIsManager(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.EMAIL_ADDR)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine EMAIL_ADDR: " + cutString(inputLine));
                    person.setEmailAddress(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.SESSION_ID)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine SESSION_ID: " + cutString(inputLine));
                    person.setSessionId(cutString(inputLine));

                } else if (inputLine.startsWith(EPMCommonConstants.REASON)) {
                    CSSLog.log(this.getClass().toString(), "doAuthorization", "inputLine REASON: " + cutString(inputLine));
                    person.setReason(cutString(inputLine));
                }
            }

            if (!person.getStatus().equals("0")) {
                CSSLog.log(this.getClass().toString(), "doAuthorization", "There is an issue with DS Authentication");
            }

            CSSLog.log(this.getClass().toString(), "doAuthorization", "Status: " + person.getStatus());
            CSSLog.log(this.getClass().toString(), "doAuthorization", "Reason: " + person.getReason());

        } catch (Exception ex) {
            CSSLog.log(this.getClass().toString(), "doAuthorization", ex.getMessage());
//			throw new ServletException("Exception while authorization " + ex, ex);
        }

        CSSLog.log(this.getClass().toString(), "doAuthorization", "Finished in " + (System.currentTimeMillis() - timeFilterStart) + " ms");

        return person;
    }

    /**
     * getCookie: returns a cookie object based on the provided name
     *
     * @param req
     * @param ckName
     * @return
     */
    public Cookie getCookie(HttpServletRequest req, String ckName) {
        CSSLog.log(this.getClass().toString(), "getCookie", "Attempting to retrieve cookie...");

        Cookie[] cookieArray = req.getCookies();
        String name = null;
        Cookie ckData = null;
        if (cookieArray != null) {
            for (int i = 0; i < cookieArray.length; i++) {
                name = cookieArray[i].getName();
                if (ckName.equals(name)) {
                    ckData = cookieArray[i];
                    CSSLog.log(this.getClass().toString(), "getCookie", "Cookie found");
                    break;
                }
            }
        }
       
        return ckData;
    }


    /**
     * Parse string from input response.
     * 
     * @param inputLine
     * @return 
     */
    private String cutString(String inputLine) {
        return inputLine.substring(inputLine.indexOf("=") + 1, inputLine.length());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}

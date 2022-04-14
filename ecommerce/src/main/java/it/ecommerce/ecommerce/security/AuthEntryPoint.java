package it.ecommerce.ecommerce.security;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

//Classe di gestione degli errori sulla autenticazione. Attraverso il "reame" riesco ad avere le eccezioni su specifiche  identificazioni.
public class AuthEntryPoint extends BasicAuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPoint.class);

    private static String REALM = "REAME";

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException
    {
        String ErrMsg = "Userid e/o Password non corrette!";

        logger.warn("Errore Sicurezza: " + authException.getMessage());

        // Authentication failed, send error response.
        //response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        PrintWriter writer = response.getWriter();
        writer.println(ErrMsg);
    }

    @Override
    public void afterPropertiesSet()
    {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }
}


package it.ecommerce.ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurotyConfiguration  extends WebSecurityConfigurerAdapter {

    private static String REALM = "REAME";
    @Autowired
    private DataSource dataSource;

    private static String [] USER = {"/utente/inserisci/**","/utente/find/**","/utente/listaOrdini/**",
            "/utente/listaArticoli/**","/articoli/listaArticoli","articoli/cerca/descrizione/**",
            "/articoli/cerca/prezzo/**","/articoli/cerca/prezzoUnder/**","/articoli/cerca/prezzoAfter/**",
    "/articoli/cerca/prezzoBetween/**","/articoli/cerca/**"};

    private static String [] ALL = {"/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT email, password, enabled from utente where email = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.email, a.role " +
                                "FROM role a, utente u " +
                                "WHERE u.email = ? " +
                                "AND u.email = a.email_user"
                );
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http.csrf().disable().
        authorizeRequests().antMatchers(ALL).permitAll()
                .anyRequest().authenticated().and().httpBasic().realmName(REALM).
             authenticationEntryPoint(getBasicAuthEntryPoint()).and().sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public AuthEntryPoint getBasicAuthEntryPoint()
    {
        return new AuthEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}


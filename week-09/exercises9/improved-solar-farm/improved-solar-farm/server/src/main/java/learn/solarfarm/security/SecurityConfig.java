package learn.solarfarm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtConverter jwtConverter;

    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    // This method allows configuring web based security for specific http requests.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we're not using HTML forms in our app
        //so disable CSRF (Cross Site Request Forgery)
        http.csrf().disable();

        // this configures Spring Security to allow
        //CORS related requests (such as preflight checks)
        http.cors();

        // the order of the antMatchers() method calls is important
        // as they're evaluated in the order that they're added
        http.authorizeRequests()
                // this is a fluent API (e.g. Streams API)
                .antMatchers(HttpMethod.POST, "/api/authenticate").permitAll() // anonymous
                .antMatchers(HttpMethod.POST, "/api/appuser").permitAll() // anonymous
                .antMatchers(HttpMethod.GET, "/api/solarpanel/user").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/api/solarpanel", "/api/solarpanel/*", "/api/solarpanel/section/*").permitAll() // anonymous... no authentication required
                .antMatchers(HttpMethod.POST,
                        "/api/solarpanel").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/api/solarpanel/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/api/solarpanel/*").hasAnyRole("ADMIN")
                // if we get to this point, let's deny all requests
                .antMatchers("/**").denyAll()
                .and()
                // Add the JWT request filter to Apache Tomcat
                .addFilter(new JwtRequestFilter(authenticationManager(), jwtConverter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Autowired
//    private PasswordEncoder encoder;

    // This is temporary... we'll remove this once we have the database update with user data
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userBuilder = User.withUsername("user")
//                .password("user").passwordEncoder(password -> encoder.encode(password))
//                .roles("USER");
//
//        var adminBuilder = User.withUsername("admin")
//                .password("admin").passwordEncoder(password -> encoder.encode(password))
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder)
//                .withUser(adminBuilder);
//    }
}

### Exercise Code Along

#### Concepts

##### Authentication vs authorization

* Authentication is about proving that the user is in fact who they say they are... "username" is "this is who I claim to be"... "password" iis "it's a secret that proves that I'm who I say that I am"
* Two-factor authentication... 2FA... typically implementation is text message through a phone
* Biometrics... "this is something that I have in my possession"
* Authorization is about determine what I can and can't do after I've authenticated

##### Session-based security vs token-based security

* What is a "session"?
* The period of time from when a user starts using an application to when they're done
* APIs typically are "session-less"... this will, generally speaking, improve the performance of the API as the server doesn't need to track a session for each collection of requests... it also allows requests to more easily be processed by a collection of servers

##### HTTPS and SSL

* Over the wire encryption
* We need to use this as our user passwords and tokens are passed in clear text otherwise

##### Stolen tokens

* It's possible for a hacker to steal a user's token
* Using HTTPS helps with this
* We also generally want our tokens to be as short lived as possible

##### JWTs (jots)

* Will they vary in length? Yes
* Other token formats... SAML (samel like camel)

##### Encoding vs Hashing vs Encryption

* Encoding - mapping a character set to a different character set
* Hashing - map a given string to another fixed length string... this process is one way... it's deterministic
* Encryption - it's two way

**The order of the exercise tasks is... questionable... definitely makes it more difficult to write and test our code incrementally**

#### 2. Configure Spring Security

Configure the project to utilize the `spring-boot-starter-security` dependency.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

_Demo that all of our endpoints are now unreachable... what changed???_

#### 7. Create Security Config

Create the `SecurityConfig` class connecting the above steps.

---

_Use antMatchers to configure access to endpoints..._

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
                .antMatchers(HttpMethod.GET,
                   "/api/solarpanel", "/api/solarpanel/*").permitAll()
                .antMatchers(HttpMethod.POST,
                   "/api/solarpanel").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                   "/api/solarpanel/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                   "/api/solarpanel/*").hasAnyRole("ADMIN")
                .antMatchers("/**").denyAll()
                // if we get to this point, let's deny all requests
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
```

#### 8. Create AuthController

Create the `AuthController` in the `controllers` package to implement the `/api/authenticate` and `/api/create_account` endpoints. Update the security configuration to allow these endpoints to be accessed without credentials.

---

_First pass..._

Stub out our controller and method.

```java
@RestController
public class AuthController {
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
```

```java
.antMatchers("/authenticate").permitAll()
```

_Test... make sure that we can hit our endpoint method... show how the username and password flow into the map..._

```
POST http://localhost:8080/authenticate HTTP/1.1
Content-Type: application/json

{
    "username": "your-username",
    "password": "your-password"
}
```

_Second pass..._

Now update the AuthController to perform the check of the credentials. We will be using the credentials and the authentication manager to validate the credentials. Configure the AuthController to accept an AuthenticationManager. We will register this later as a @Bean in SecurityConfig.

```java
@RestController
public class AuthController {
    // The `AuthenticationManager` interface defines a single method `authenticate()`
    // that processes an Authentication request.
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        // The `UsernamePasswordAuthenticationToken` class is an `Authentication` implementation
        // that is designed for simple presentation of a username and password.
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            // The `Authentication` interface Represents the token for an authentication request
            // or for an authenticated principal once the request has been processed by the //
            // `AuthenticationManager.authenticate(Authentication)` method.
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                HashMap<String, String> map = new HashMap<>();
                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
```

Next, create a couple of in-memory users to manage the test scenario. Complete implementations will use a database or security service to manage the users and configuration. We are hard coding the user and admin users to focus on the JWT configuration. Update the SecurityConfig class to create our in-memory users:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Snip! (leave the implementation of this method as it is)
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userBuilder = User.withUsername("user")
                .password("user").passwordEncoder(password -> encoder.encode(password))
                .roles("USER");

        var adminBuilder = User.withUsername("admin")
                .password("admin").passwordEncoder(password -> encoder.encode(password))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser(userBuilder)
                .withUser(adminBuilder);
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

_Test..._

```
POST http://localhost:8080/authenticate HTTP/1.1
Content-Type: application/json

{
    "username": "user",
    "password": "user"
}
```

```
POST http://localhost:8080/authenticate HTTP/1.1
Content-Type: application/json

{
    "username": "admin",
    "password": "admin"
}
```

_How to prevent the circular reference error_

* AppConfig class with @Configuration annotation
* Move these beans to that class...
    * PasswordEncoder
    * WebMvcConfigurer
  
#### 6. Add JWT to project, creating JWT Converter and JWT Filter

Continue the security implementation to include the JWT dependencies, and then create in the `security` package the `JwtConverter` and `JwtRequestFilter` classes

---

_Let's work on generating a token to return to the client..._

```xml
<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.2</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.2</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.2</version>
    <scope>runtime</scope>
</dependency>
```

_Add the JwtConverter class..._

```java
@Component
public class JwtConverter {

    // 1. Signing key
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 2. "Configurable" constants
    private final String ISSUER = "solar-farm-api";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(User user) {

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        // 3. Use JJWT classes to build a token.
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public User getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            // 4. Use JJWT classes to read a token.
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            String authStr = (String) jws.getBody().get("authorities");
            List<GrantedAuthority> authorities = Arrays.stream(authStr.split(","))
                    .map(i -> new SimpleGrantedAuthority(i))
                    .collect(Collectors.toList());

            return new User(username, username, authorities);

        } catch (JwtException e) {
            // 5. JWT failures are modeled as exceptions.
            System.out.println(e);
        }

        return null;
    }
}
```

_Update the AuthController... add JwtConverter as a dependency... call the method to get the token and add it to the map..._

```java
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody Map<String, String> credentials) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.getTokenFromUser((User) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
```

_Retest!!! Set breakpoints and highlight the flow through the methods..._

_Now we need to support the ability for the client to pass a token with a request..._

```java
public class JwtRequestFilter extends BasicAuthenticationFilter {

    private final JwtConverter converter;

    public JwtRequestFilter(AuthenticationManager authenticationManager, JwtConverter converter) {
        super(authenticationManager); // 1. Must satisfy the super class.
        this.converter = converter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        // 2. Read the Authorization value from the request.
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {

            // 3. The value looks okay, confirm it with JwtConverter.
            User user = converter.getUserFromToken(authorization);
            if (user == null) {
                response.setStatus(403); // Forbidden
            } else {

                // 4. Confirmed. Set auth for this single request.
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        user.getUsername(), null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        // 5. Keep the chain going.
        chain.doFilter(request, response);
    }
}
```

_Now we can update our security configuration to add the JwtRequestFilter filter..._

```java
package learn.safari.security;

// imports

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // new...
    // Add JwtConverter as a dependency

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                // snip!
                .antMatchers("/**").denyAll()
                .and()
                // new ...
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // snip!
}
```

_Retest!!! Show that we can get a token and then pass that token with a request..._

```
POST http://localhost:8080/solarpanel HTTP/1.1
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJidWctc2FmYXJpIiwic3ViIjoidXNlciIsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjAzODE4MTEyfQ.OfhZvOXOYGN6mtpYyc_kRdMbdO4Wilr4iUEo26FXp1Y

{
  ...
}
```

#### CORS global configuration

It's important to configure CORS globally, otherwise your server errors might result in a 403 being returned to the client... when this happens, you'll misinterpret the source of the problem as it'll seem like it's an authentication issue when it's really another problem

```java
@Bean
public WebMvcConfigurer corsConfigurer() {

    // Configure CORS globally versus
    // controller-by-controller.
    // Can be combined with @CrossOrigin.
    return new WebMvcConfigurer() {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("*");
        }
    };
}
```

#### Spring Security and Unit Testing

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MyServiceTest {
    // snip!
}
```

```java
@SpringBootTest
class MyServiceTest {
    // snip!
}
```

#### 1. Create Schema to add Users and Roles to database

Add `app_user`, `app_role`, `app_user_role` and appropriate data to the schema.

---

```sql
use solar_farm;

drop table if exists app_user_role;
drop table if exists app_role;
drop table if exists app_user;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    disabled bit not null default(0)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, disabled)
    values
    ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

insert into app_user_role
    values
    (1, 2),
    (2, 1);
```

#### 3. Create AppUser

Add the `AppUser` to the `models` package, extending `User` and mapping to the created fields.

---

```java
public class AppUser extends User {

    private static final String AUTHORITY_PREFIX = "ROLE_";

    private int appUserId;

    public AppUser(int appUserId, String username, String password,
                   boolean disabled, List<String> roles) {
        super(username, password, !disabled,
                true, true, true,
                convertRolesToAuthorities(roles));
        this.appUserId = appUserId;
    }

    private List<String> roles = new ArrayList<>();

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }


    public static List<GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (String role : roles) {
            Assert.isTrue(!role.startsWith(AUTHORITY_PREFIX),
                    () ->
                            String.
                                    format("%s cannot start with %s (it is automatically added)",
                                            role, AUTHORITY_PREFIX));
            authorities.add(new SimpleGrantedAuthority(AUTHORITY_PREFIX + role));
        }
        return authorities;
    }

    public static List<String> convertAuthoritiesToRoles(Collection<GrantedAuthority> authorities) {
        return authorities.stream()
                .map(a -> a.getAuthority().substring(AUTHORITY_PREFIX.length()))
                .collect(Collectors.toList());
    }
}
```

#### 4. Create AppUserRepository

Create the interface and repository to add `AppUserRepository` interface and `AppUserJdbcTemplateRepository` class.

---

_Add a mapper as a class or to the repository as a field..._

```java
public class AppUserMapper implements RowMapper<AppUser> {
    private final List<String> roles;

    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        return new AppUser(
                rs.getInt("app_user_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getBoolean("disabled"),
                roles);
    }
}
```

_Now the repository..._

```java
@Repository
public class AppUserJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select app_user_id, username, password_hash, disabled "
                + "from app_user "
                + "where username = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "insert into app_user (username, password_hash) values (?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setAppUserId(keyHolder.getKey().intValue());

        updateRoles(user);

        return user;
    }

    @Transactional
    public void update(AppUser user) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "disabled = ? "
                + "where app_user_id = ?";

        jdbcTemplate.update(sql,
                user.getUsername(), !user.isEnabled(), user.getAppUserId());

        updateRoles(user);
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where `name` = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role);
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.name "
                + "from app_user_role ur "
                + "inner join app_role r on ur.app_role_id = r.app_role_id "
                + "inner join app_user au on ur.app_user_id = au.app_user_id "
                + "where au.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }
}
```

_Extract an interface for the repository..._

```java
public interface AppUserRepository {
  @Transactional
  AppUser findByUsername(String username);

  @Transactional
  AppUser create(AppUser user);

  @Transactional
  void update(AppUser user);
}
```

#### 5. Create AppUserService

* Create the `AppUserService` which implements `org.springframework.security.core.userdetails.UserDetailsService`
* The `UserDetailsService` interface loads user-specific data.
* The interface requires only one read-only method, which simplifies support for new data-access strategies.

---

_Add the following dependency (this is necessary to get access to the ValidationException type)..._

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

```java
@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository repository,
                          PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return appUser;
    }

    public AppUser create(String username, String password) {
        validate(username);
        validatePassword(password);

        password = encoder.encode(password);

        AppUser appUser = new AppUser(0, username, password, false, List.of("User"));

        return repository.create(appUser);
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }

        if (username.length() > 50) {
            throw new ValidationException("username must be less than 50 characters");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("password must be at least 8 characters");
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        if (digits == 0 || letters == 0 || others == 0) {
            throw new ValidationException("password must contain a digit, a letter, and a non-digit/non-letter");
        }
    }
}
```

_Then optionally update the controller with an endpoint to create a user..._

```java
@RestController
public class AuthController {

    // new... add AppUserService as a dependency

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final AppUserService appUserService;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.appUserService = appUserService;
    }

    // snip!

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {
        AppUser appUser = null;

        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            appUser = appUserService.create(username, password);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }

        // happy path...

        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", appUser.getAppUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
```

We no longer want users configured in memory via AuthenticationManagerBuilder. Remove the configure(AuthenticationManagerBuilder auth) method and PasswordEncoder encoder field from SecurityConfig. We don't need them anymore.

Leave the configure(HttpSecurity http) method. It's still an entirely valid security configuration regardless of where the users come from. Update the antMatchers to allow any user access to the /create_account path.

```java
package learn.safari.security;

// imports

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/create_account").permitAll()
                // snip!
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
```

_Testing!!! Make sure we can get a token and pass a token with a request... test that we can create a new account..._

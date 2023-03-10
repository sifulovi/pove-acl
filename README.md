## A feature based ACL by spring security 6 and jwt

### Techs:
- > java 17
- > Spring boot 3
- > Spring Security 6
- > PostgreSQL  
- > Docker latest

PostgreSQL DB client is coming from docker images/container, need to run the docker compose file for run this project.

Two user will be loaded while application startup , see the ***DataLoader.java***.


### Swagger URL 
- > http://localhost:8080/swagger-ui/index.html#/

#### For Authentication:

POST localhost:8080/api/auth/token

```agsl
{
    "username": "staff",
    "password": "1234"
}

```

RESPONSE :

```agsl
{
    "uid": "10001",
    "username": "staff",
    "fullName": "Staff",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGFmZiIsImlhdCI6MTY3NjY3MTQyOCwiZXhwIjoxNjc2NjczMjI4fQ.dvLxIadKDzVsc7428gFDcODNxo3ojfhDfjH8wyl_faE"
}
```

GET localhost:8080/api/users/list
AUTHORIZATION : `Bearer {token}`


RESPONSE : 
```agsl
User List
```

### Feature configuration:

1. Every api endpoint url should come from ResourceUrl class, as like

```agsl
    public static final String CREATE_USER = "/api/users/create-user";

```

2. To append the urls into spring security every api end point should have a record, as like

```agsl

    public static final AuthRecord CREATE_USER_RECORD = new AuthRecord("CREATE_USER", CREATE_USER, "Create User");

```

3. Now we can add some roles accordingly of our project requirement, as like

```agsl

 public static List<AuthRecord> getUserFeatureList() {
        return List.of(
                CREATE_USER_RECORD,
                USER_LIST_RECORD
        );
    }
```

Here we see a user has two authorities.

4. Lets append those authorities to authorize the request via spring security

```agsl

 void userPrivileges(HttpSecurity httpSecurity) throws Exception {
        for (var authRecord : ResourceUrl.getUserFeatureList()) {
            httpSecurity.authorizeHttpRequests(http -> http.requestMatchers(authRecord.url()).hasAuthority(authRecord.name()));
        }
    }
```

5. Lastly add this method to **securityFilterChain** as like

```agsl

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin();
        httpSecurity.logout().logoutUrl("/logout");

        this.ignoredPrivileges(httpSecurity);
        this.userPrivileges(httpSecurity); // User roles/authorities

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(new JwtAuthorizationFilter(jwtService, authUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeHttpRequests(http -> http.anyRequest().authenticated());


        return httpSecurity.build();
    }
```

@@ Yet to implement
1. EMAIL service configuration
2. OTP verification via email
3. Events persist / Activity tracking
4. Web client / react js / angular
5. Socket.io

@isiful.ovi@gmail.com 
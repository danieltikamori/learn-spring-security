# Using Spring Security

At first run without any change, it will generate a password displayed at console for the "user" user.

One alternative to use a fixed password for demonstration purposes, put parameters to "application.properties":

```
spring.security.user.name=user
spring.security.user.password=user123
spring.security.user.roles=USERS
```

Not recommended for production environments.



package com.github.florian_renfer.regulars_table.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String UI_LOCALHOST_ORIGIN = "http://localhost:5173";
  private static final String UI_LOOPBACK_ORIGIN = "http://127.0.0.1:5173";

  /** Configures the security filter chain for authenticated requests and UI login redirects. */
  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
        .exceptionHandling(
            (exceptions) ->
                exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .oauth2Login((oauth) -> oauth.defaultSuccessUrl("http://localhost:5173/dashboard", true))
        .logout((logout) -> logout.logoutSuccessUrl("http://localhost:5173"));
    return http.build();
  }

  /** Exposes the CORS policy used by Spring Security for browser requests from the UI. */
  @Bean
  public UrlBasedCorsConfigurationSource apiConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(UI_LOCALHOST_ORIGIN, UI_LOOPBACK_ORIGIN));
    configuration.setAllowedMethods(
        Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

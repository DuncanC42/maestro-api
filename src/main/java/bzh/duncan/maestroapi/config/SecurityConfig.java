package bzh.duncan.maestroapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    // Allow Cross-Origin Resource Sharing (CORS)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors()
//            .and()
//            .csrf().disable()
//            .authorizeHttpRequests((authz) -> authz
//                .requestMatchers("/ws/**").permitAll()
//                .requestMatchers("/app/**").permitAll()
//                .requestMatchers("/topic/**").permitAll()
//                .anyRequest().authenticated()
//            );
//
//        return http.build();
//    }
}
package com.SpringFirst.springboot.config.auth;

import com.SpringFirst.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security설정들 활성화.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() //URL 별 권환 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMatchers옵션 사용 가능.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //antMathcer : 권한 관리 대상을 지정하는 옵션.
                    .anyRequest().authenticated() //설정된 값들 이외 나머지 URL을 나타냄.
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공시 /주소로 이동.
                .and()
                    .oauth2Login() //OAuth 2 로그인 기능에 대한 여러 설정의 진입점.
                        .userInfoEndpoint() //OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당.
                            .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
    }
}

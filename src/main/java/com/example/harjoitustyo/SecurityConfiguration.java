package com.example.harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ei päästetä käyttäjää mihinkään sovelluksen resurssiin ilman
        // kirjautumista. Tarjotaan kuitenkin lomake kirjautumiseen, mihin
        // pääsee vapaasti. Tämän lisäksi uloskirjautumiseen tarjotaan
        // mahdollisuus kaikille.
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/kuvat/*").permitAll()
                .antMatchers("/artikkeli/*").permitAll()
                .antMatchers("/uusimmat").permitAll()
                .antMatchers("/luetuimmat").permitAll()
                .antMatchers("/kategoria/*").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll().and()
                .logout().permitAll();
    }

}
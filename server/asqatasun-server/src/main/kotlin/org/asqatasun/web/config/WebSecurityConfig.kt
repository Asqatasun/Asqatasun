package org.asqatasun.web.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var users: Users

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(users.auth.map { it.toUserDetails(passwordEncoder()) })
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

@ConfigurationProperties(prefix = "app.users")
@Configuration
class Users {
    var auth: List<UserAuth> = emptyList()
}

class UserAuth {
    var name: String? = null
    var password: String? = null

    fun toUserDetails(passwordEncoder: PasswordEncoder): UserDetails {
        return User
            .withUsername(name)
            .password(passwordEncoder.encode(password))
            .roles()
            .build()
    }
}


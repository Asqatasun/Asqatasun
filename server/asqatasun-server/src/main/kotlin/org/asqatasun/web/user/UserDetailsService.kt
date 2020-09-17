package org.asqatasun.web.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Service
class UserDetailsService(private val asqaDataSource: DataSource) : JdbcDaoImpl() {

    @PostConstruct
    fun init() {
        setDataSource(asqaDataSource)
        usersByUsernameQuery = USERS_BY_USERNAME_QUERY
        authoritiesByUsernameQuery = AUTHORITIES_BY_USERNAME_QUERY
    }

    override fun createUserDetails(username: String, userFromUserQuery: UserDetails,
                                   combinedAuthorities: List<GrantedAuthority>): UserDetails {
        return User(username, userFromUserQuery.password, combinedAuthorities)
    }

    companion object {
        private const val USERS_BY_USERNAME_QUERY = "SELECT Email1, Password, Activated as enabled FROM USER WHERE Email1=?"
        private const val AUTHORITIES_BY_USERNAME_QUERY = ("SELECT USER.Email1, ROLE.Role_Name as authorities FROM USER, ROLE "
            + "WHERE USER.Email1 = ? AND USER.ROLE_Id_Role=ROLE.Id_Role")
    }
}

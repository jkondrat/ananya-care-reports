package org.motechproject.carereporting.service;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.carereporting.domain.RoleEntity;
import org.motechproject.carereporting.domain.UserEntity;
import org.motechproject.carereporting.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class UserServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setupAuthentication() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("CAN_MANAGE_SYSTEM_USERS"));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("principal", "credentials", authorities));
    }

    @Test
    public void testRegisterUser() throws Exception {
        String username = "username";
        String password = "password";
        userService.register(username, password, new HashSet<RoleEntity>());
        UserEntity user = userService.login(username, password);
        assertNotNull(user);
    }

    @Test
    public void testAddRoles() throws Exception {
        addRoles("ROLE1", "ROLE2");
        assertEquals(2, userService.findAllRoles().size());
    }

    @Test
    public void testRegisterUserWithRoles() throws Exception {
        addRoles("TEST1", "TEST1", "TEST1");
        RoleEntity[] roleEntities = {};
        roleEntities = userService.findAllRoles().toArray(roleEntities);
        RoleEntity role = roleEntities[0];
        String username = "username2";
        String password = "password2";
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        userService.register(username, password, roles);
        UserEntity user = userService.login(username, password);
        assertEquals(1, user.getRoles().size());
    }

    private void addRoles(String... roles) {
        for (String role: roles) {
            userService.addRole(role);
        }
    }

    @Test(expected = UserException.class)
    public void testLoginFailed() throws Exception {
        String username = "bad-usernamepg";
        String password = "bad-password";
        userService.login(username, password);
    }

    @Test
    public void testUserCreationDate() {
        String username = "username";
        String password = "password";
        userService.register(username, password, new HashSet<RoleEntity>());
        UserEntity userEntity = userService.login(username, password);

        assertEquals(true, DateUtils.isSameDay(userEntity.getCreationDate(), new Date()));
    }

    @Test
    public void testUserModificationDate() {
        String username = "username";
        String password = "password";
        String newusername = "new user";
        UserEntity userEntity = new UserEntity(username, password);
        userService.register(userEntity);
        userEntity.setUsername(newusername);
        userEntity.setPassword(password);
        userService.updateUser(userEntity);
        userEntity = userService.login(newusername, password);

        assertNotNull(userEntity.getModificationDate());
    }

}
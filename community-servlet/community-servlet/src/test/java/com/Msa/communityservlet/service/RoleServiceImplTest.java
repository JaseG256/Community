package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Role;
import com.Msa.communityservlet.model.RoleName;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RoleServiceImplTest {

    @TestConfiguration
    static class RoleServiceImplTestContextConfiguration {

        @Bean
        RoleService roleService() {
            return new RoleServiceImpl();
        }
    }

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    private Role role;
    private Long id;
    private List<Role> foundRoleList;

    @Before
    public void setUp() {
        role = new Role();
        role.setName(RoleName.ROLE_USER);
        id = 1L;
        role.setId(id);
        foundRoleList = Collections.singletonList(role);
    }

    @Test
    public void listAll() {
        Mockito.when(roleRepository.findAll())
                .thenReturn(foundRoleList);
        List<Role> roleServiceList = roleService.listAll();
        assertThat(roleServiceList, equalTo(foundRoleList));
    }

    @Test
     public void getById() {
        role.setId(id);
        Mockito.when(roleRepository.findById(id))
                .thenReturn(Optional.of(role));
        Role foundRole = roleService.getById(id).get();
        assertThat(foundRole.getId(), equalTo(id));

    }

    @Test
    public void saveOrUpdate() {
        Mockito.when(roleService.saveOrUpdate(role))
                .thenReturn(role);
        Role foundRole = roleService.saveOrUpdate(role);
        assertThat(foundRole, equalTo(role));
    }

    @Test
    public void delete() {
        Mockito.when(roleRepository.findById(id))
                .thenReturn(Optional.of(role));
        roleService.delete(id);
        assertThat(roleRepository.findById(id), not(equalTo(role)));
    }

    @Test
    public void findByNameTestMatch() {
        Mockito.when(roleRepository.findByName(role.getName()))
                .thenReturn(Optional.of(role));
        RoleName userRoleName = RoleName.valueOf("ROLE_USER");
        Role foundRole = roleService.findByName(userRoleName).get();
        assertThat(foundRole.getName(), equalTo(userRoleName));
    }

//    @Test
//    public void findByNameTestNoMatch() {
//        Mockito.when(roleRepository.findByName(role.getName()))
//                .thenReturn(Optional.of(role));
//        RoleName userRoleName = RoleName.valueOf("ROLE_ADMIN");
//        Role foundRole = roleService.findByName(userRoleName).get();
////        assertThat(foundRole.getName(), not(equalTo(userRoleName)));
//        assertNotSame(userRoleName, foundRole.getName());
//    }
}
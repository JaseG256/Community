package com.Msa.communityservlet.config;

import com.Msa.communityservlet.repository.*;
import com.Msa.communityservlet.security.CustomUserDetailsService;
import com.Msa.communityservlet.service.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ServiceUnitTestConfig {

    @Bean
    @Primary
    public PlaceRepository placeRepository() {
        return Mockito.mock(PlaceRepository.class);
    }

    @Bean
    @Primary
    public PlaceService placeService() {
        return new PlaceServiceImpl(placeRepository());
    }

    @Bean
    @Primary
    public RoleRepository roleRepository() {
        return Mockito.mock(RoleRepository.class);
    }

    @Bean
    @Primary
    public RoleService roleService() {
        return new RoleServiceImpl(roleRepository());
    }

    @Bean
    @Primary
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    @Primary
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    @Primary
    public CourseRepository courseRepository() {
        return Mockito.mock(CourseRepository.class);
    }

    @Bean
    @Primary
    public CourseService courseService() {
        return new CourseServiceImpl(courseRepository(), userRepository());
    }

    @Bean
    @Primary
    public DBFileRepository dbFileRepository() {
        return Mockito.mock(DBFileRepository.class);
    }

    @Bean
    @Primary
    public DBFileStorageService dbFileStorageService() {
        return new DBFileStorageServiceImpl(dbFileRepository(), userService());
    }

    @Bean
    @Primary
    public CustomUserDetailsService userDetailsService() {
        return Mockito.mock(CustomUserDetailsService.class);
    }

    @Bean
    @Primary
    public FamilyEventRepository familyEventRepository() {
        return Mockito.mock(FamilyEventRepository.class);
    }

    @Bean
    @Primary
    public FamilyEventService eventService() {
        return new FamilyEventServiceImpl(familyEventRepository());
    }
}

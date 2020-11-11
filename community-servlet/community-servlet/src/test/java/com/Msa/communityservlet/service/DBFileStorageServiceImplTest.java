package com.Msa.communityservlet.service;

import com.Msa.communityservlet.config.ServiceUnitTestConfig;
import com.Msa.communityservlet.model.DBFile;
import com.Msa.communityservlet.model.User;
import com.Msa.communityservlet.repository.DBFileRepository;
import com.Msa.communityservlet.security.CustomUserDetailsService;
import com.Msa.communityservlet.security.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceUnitTestConfig.class)
public class DBFileStorageServiceImplTest {


    @Autowired
    DBFileStorageService dbFileStorageService;

    @Autowired
    DBFileRepository fileRepository;

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService userDetailsService;

    String fileName;
    Long userId;
    String fileId;
    byte[] filedata;
    String fileContentType;
    private MultipartFile file;
    private DBFile dbFile;
    private User jason;
    private UserPrincipal currentUser;

    @Before
    public void setUp() {
        fileName  = "Jason's pic.jpg";
        filedata = new byte[5];
        fileContentType = "image/jpg";
        file = new MockMultipartFile(fileName, filedata);
        userId = 1L;
        try {
            dbFile = new DBFile(file.getName(), fileContentType ,file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbFile.setId(fileId);
        jason = new User("jason", "jason@yahoo.com", "jumpshot");
        jason.setId(userId);
        currentUser = (UserPrincipal) userDetailsService.loadUserByUsername("jason");
    }



    @Test
    public void storeFile() {
        Mockito.when(fileRepository.findById(fileId))
                .thenReturn(Optional.of(dbFile));
        assertThat(dbFileStorageService.getFile(fileId)).isNotNull();
    }

    @Test
    public void getFile() {
        Mockito.when(fileRepository.findById(fileId))
                .thenReturn(Optional.of(dbFile));
        assertThat(dbFileStorageService.getFile(fileId)).isNotNull();
    }
}
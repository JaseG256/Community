package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.DBFile;
import com.Msa.communityservlet.security.UserPrincipal;
import org.springframework.web.multipart.MultipartFile;

public interface DBFileStorageService {

    DBFile storeFile(MultipartFile file, UserPrincipal currentUser);

    DBFile getFile(String fileId);
}

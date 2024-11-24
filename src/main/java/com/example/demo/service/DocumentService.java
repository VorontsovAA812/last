package com.example.demo.service;

import com.example.demo.domain.Document;
import com.example.demo.domain.User;
import com.example.demo.rest.dto.DocumentDtos.NewDocumentRequest;

public interface DocumentService {
    Long addNewDocument(NewDocumentRequest request,Long userId);
    void addNewUserToDocument(Long userId, Long documentId);

}

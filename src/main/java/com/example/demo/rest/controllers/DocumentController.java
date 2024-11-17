package com.example.demo.rest.controllers;

import com.example.demo.rest.dto.DocumentDtos.NewDocumentRequest;
import com.example.demo.rest.dto.UserDtos.NewUserRequest;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/document")
public class DocumentController {
    private DocumentService documentService;
    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewDocumentRequest request) // Long - возвращаем индекс добавленного пользователя
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.addNewDocument(request));
    }
    @PostMapping("/{documentId}/user/{userId}")
    public ResponseEntity<Void> addUserToDocument(@PathVariable Long documentId, @PathVariable Long userId) {
        documentService.addNewUserToDocument(userId, documentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

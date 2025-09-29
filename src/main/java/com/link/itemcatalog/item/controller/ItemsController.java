package com.link.itemcatalog.item.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Tag(name = "Items Management", description = "Operations related to managing items")
@Slf4j
public class ItemsController {


    public ResponseEntity<String> getById() {
        log.debug("Get customer by id={}", "Done");
        return ResponseEntity.ok("Done");
    }
}

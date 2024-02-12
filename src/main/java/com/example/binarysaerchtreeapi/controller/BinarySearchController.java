package com.example.binarysaerchtreeapi.controller;


import com.example.binarysaerchtreeapi.model.SearchDtoModel;
import com.example.binarysaerchtreeapi.model.TreeNode;
import com.example.binarysaerchtreeapi.service.BinarySearchService;
import jakarta.websocket.server.PathParam;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.apache.logging.log4j.LogManager.getLogger;

@Controller
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class BinarySearchController {

    Logger logger = getLogger(BinarySearchController.class);

    @Autowired
    private BinarySearchService binarySearchService;



    @PostMapping("/add")
    public ResponseEntity<TreeNode> addNode(@RequestBody TreeNode nodeRequestBody) {
        try {
            TreeNode savedNode = binarySearchService.save(nodeRequestBody);
            return ResponseEntity.ok(savedNode);
        } catch (Exception e) {
            logger.error("error while saving ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/add/{id}")
    public ResponseEntity<TreeNode> fetchNodeById(@PathParam("id") Long id) {
        try {
            TreeNode savedNode = binarySearchService.findTreeNodeById(id);
            return ResponseEntity.ok(savedNode);
        } catch (Exception e) {
            logger.error("error while fetching by Id ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Boolean> search(@RequestBody SearchDtoModel searchDtoModel) {
        try {
            TreeNode rootNode = binarySearchService.findTreeNodeById(1L);
            boolean b = binarySearchService.searchValue(rootNode, searchDtoModel.getValue());
            return ResponseEntity.ok(b);
        } catch (Exception e) {
            logger.error("Error occurred while searching for the node ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/add-node")
    public ResponseEntity<TreeNode> addNodeWithValue(@RequestParam("value") int value) {
        try {

            TreeNode rootNode = binarySearchService.findTreeNodeById(1L);
            rootNode = binarySearchService.addNode(rootNode, value);
            binarySearchService.save(rootNode);
            return ResponseEntity.ok(rootNode);
        } catch (Exception e) {
            logger.error("Error occurred while adding new node ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

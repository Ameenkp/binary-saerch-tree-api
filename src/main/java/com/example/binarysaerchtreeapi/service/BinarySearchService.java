package com.example.binarysaerchtreeapi.service;


import com.example.binarysaerchtreeapi.model.TreeNode;
import com.example.binarysaerchtreeapi.repo.BinarySearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BinarySearchService {


    @Autowired
    private BinarySearchRepo binarySearchRepo;



    public TreeNode save(TreeNode node){
        return binarySearchRepo.save(node);
    }

    public TreeNode findTreeNodeById(Long id){
        Optional<TreeNode> byId = binarySearchRepo.findById(id);
        return byId.orElse(null);
    }

    public boolean searchValue(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (value == root.getValue()) {
            return true;
        } else if (value < root.getValue()) {
            return searchValue(root.getLeftChild(), value);
        } else {
            return searchValue(root.getRightChild(), value);
        }
    }

    public TreeNode addNode(TreeNode root, int value) {
        if (root == null) {
            TreeNode treeNode = new TreeNode(value);
            return save(treeNode);
        }
        if (value < root.getValue()) {
            root.setLeftChild(addNode(root.getLeftChild(), value));
        } else if (value > root.getValue()) {
            root.setRightChild(addNode(root.getRightChild(), value));
        }

        return root;
    }
}

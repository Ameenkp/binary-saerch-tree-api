package com.example.binarysaerchtreeapi.repo;

import com.example.binarysaerchtreeapi.model.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinarySearchRepo extends JpaRepository<TreeNode, Long> {

}

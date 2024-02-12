package com.example.binarysaerchtreeapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "tree_node")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TreeNode {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    @JoinColumn(name = "left_child_node")
    private TreeNode leftChild;

    @ManyToOne
    @JoinColumn(name = "right_child_node")
    private TreeNode rightChild;


    public TreeNode(int value) {
        this.value = value;
    }
}

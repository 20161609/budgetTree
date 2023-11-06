import javax.swing.*;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ManageTree extends JFrame {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JButton addButton, editButton, deleteButton, saveButton;

    public ManageTree() {
        // Initialize the tree and tree model
        try {
            treeModel = TreePersistence.loadTreeModel("treeModel.ser");
        } catch (IOException | ClassNotFoundException e) {
            // If there was an error loading the model, start with a default one
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
            treeModel = new DefaultTreeModel(root);
        }
        tree = new JTree(treeModel);

        // Set up the UI components for tree manipulation
        addButton = new JButton("Add Node");
        editButton = new JButton("Edit Node");
        deleteButton = new JButton("Delete Node");
        saveButton = new JButton("Save Changes");

        // Layout the components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tree), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Set up the action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add new node logic
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Node");
                    treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
                    // Automatically select the new node and scroll to it
                    TreeNode[] nodes = treeModel.getPathToRoot(newNode);
                    TreePath path = new TreePath(nodes);
                    tree.scrollPathToVisible(path);
                    tree.setSelectionPath(path);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Edit node logic
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    // Simple dialog to enter new name
                    String newName = JOptionPane.showInputDialog("Enter new name");
                    if (newName != null && !newName.isEmpty()) {
                        selectedNode.setUserObject(newName);
                        treeModel.nodeChanged(selectedNode);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete node logic
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.getParent() != null) {
                    treeModel.removeNodeFromParent(selectedNode);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TreePersistence.saveTreeModel(treeModel, "treeModel.ser");
                    JOptionPane.showMessageDialog(ManageTree.this, "Tree structure saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ManageTree.this, "Failed to save the tree structure.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Finalize and display the frame
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Manage Tree Structure");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageTree());
    }
}
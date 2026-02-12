package edu.unl.raikes.BinarySearchTreeLab;

import java.util.ArrayList;
import java.util.List;

/**
 * BinarySearchTree.
 */
public class BinarySearchTree {
	boolean verbose = true;
	private BinarySearchNode root = null;
	private int size = 0;

	/**
	 * Insert data into the binary tree.
	 * @param data Data.
	 */
	public void insert(Person data) {
		boolean inserted = false;
		// Found position if root is null.
		if (root == null) {
			root = new BinarySearchNode(data);
			inserted = true;
		} // Continue searching
		else {
			inserted = root.insert(data);
		} // If added, increase the size
		if (inserted) {
			size++;
		}
	}

	/**
	 * Using key to find value in binary tree.
	 * @param key Key.
	 * @return Value.
	 */
	public Person search(int key) {
		// If data cannot be found.
		if (root == null) {
			return null;
		}
		// Recursion
		BinarySearchNode found = root.search(key);
		// Data is found
		if (found != null) {
			return found.person;
		} else {
			return null;
		}

	}

	/**
	 * Delete data from tree.
	 * @param key Key.
	 * @return Deleted data.
	 */
	public Person delete(int key) {
		Person deleted = null;

		// Data to be deleted was not found
		if (root == null) {
			return null;
		} // Data exists in tree
		else {
			// Found the data
			if (root.person.key == key) {
				// add fake root in case the element to be removed is the root.
				// (simplifies pointer logic)
				BinarySearchNode auxRoot = new BinarySearchNode(null);
				auxRoot.setLeftChild(root);
				// Delete the data
				deleted = root.delete(key);
				// retrieve the root from the fake root (in case it changed)
				root = auxRoot.leftChild;
				// No children
				if (root != null)
					root.parent = null;
			} // Continuse searching
			else {
				deleted = root.delete(key);
			} // Decrease size of tree
			if (deleted != null)
				size--;
			return deleted;
		}
	}

	/**
	 * Returns BinarySearchTree as String.
	 */
	public String toString() {
		String toReturn = "Binary Search Tree of Size: " + size + "\n";
	
		// Get ArrayList of nodes
		List<BinarySearchNode> nodes = new ArrayList<>();
		this.toStringHelper(nodes, this.root);

		// Addd ArrayList of nodes to return!
		for (BinarySearchNode node : nodes) {
			toReturn += node;
		}

		return toReturn;
	}

	/**
	 * Helper function.
	 * @param nodes ArrayList of nodes.
	 * @param node Current node.
	 */
	public void toStringHelper(List<BinarySearchNode> nodes, BinarySearchNode node) {
		if (node ==  null) {
			return;
		}

		this.toStringHelper(nodes, node.leftChild);
		nodes.add(node);
		this.toStringHelper(nodes, node.rightChild);
	}

}

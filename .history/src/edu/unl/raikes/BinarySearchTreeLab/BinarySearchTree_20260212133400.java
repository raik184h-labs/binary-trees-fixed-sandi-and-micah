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

	// TODO: ADD JAVADOC COMMENT
	public Person delete(int key) {
		Person deleted = null;

		// TODO: ADD COMMENT
		if (root == null) {
			return null;
		} // TODO: ADD COMMENT
		else {
			// TODO: ADD COMMENT
			if (root.person.key == key) {
				// add fake root in case the element to be removed is the root.
				// (simplifies pointer logic)
				BinarySearchNode auxRoot = new BinarySearchNode(null);
				auxRoot.setLeftChild(root);
				// TODO: ADD COMMENT
				deleted = root.delete(key);
				// retrieve the root from the fake root (in case it changed)
				root = auxRoot.leftChild;
				// TODO: ADD COMMENT
				if (root != null)
					root.parent = null;
			} // TODO: ADD COMMENT
			else {
				deleted = root.delete(key);
			} // TODO: ADD COMMENT
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

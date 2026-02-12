package edu.unl.raikes.BinarySearchTreeLab;

/**
 * BinarySearchNode.
 */
class BinarySearchNode {
	protected BinarySearchNode parent;
	protected BinarySearchNode leftChild;
	protected BinarySearchNode rightChild;
	protected Person person;

	/**
	 * Constructor.
	 * @param person Data.
	 */
	BinarySearchNode(Person person) {
		this.person = person;
	}

	/**
	 * Insert data to children.
	 * @param data Data.
	 * @return Data.
	 */
	boolean insert(Person data) {
		// Cannot add if data to be added is the root!
		if (data == this.person) {
			return false;
		}
		// Compare the branches
		else if (Integer.compare(data.key, person.key) < 0) {
			// If left child is empty!
			if (leftChild == null) {
				setLeftChild(new BinarySearchNode(data));
				return true;
			} // If left child is not empty!
			else {
				return leftChild.insert(data);
			}
		}
		// Right side
		else if (Integer.compare(data.key, person.key) > 0) {
			// If right child is empty
			if (rightChild == null) {
				setRightChild(new BinarySearchNode(data));
				return true;
			} // If right child is not empty
			else {
				return rightChild.insert(data);
			}
		}
		return false;
	}

	/**
	 * Search for data.
	 * @param key Key.
	 * @return Data.
	 */
	BinarySearchNode search(int key) {
		// If key is smaller than root
		if (leftChild != null && Integer.compare(key, person.key) < 0) {
			return leftChild.search(key);
		}
		// If key is lareger than root
		else if (rightChild != null && Integer.compare(key, person.key) > 0) {
			return rightChild.search(key);
		}
		// If key is equal to root
		else if (this.person.key == key) {
			return this;
		}
		// If key is not found
		else {
			return null;
		}
	}

	/**
	 * Delete data.
	 * @param key Key.
	 * @return Data.
	 */
	Person delete(int key) {
		// Find data
		BinarySearchNode node = search(key);
		if (node == null)
			return null;
		Person deleted = node.person;

		// If node does not have any children
		if (node.leftChild == null && node.rightChild == null) {
			if (node.parent.leftChild == node)
				node.parent.setLeftChild(null);
			else if (node.parent.rightChild == node)
				node.parent.setRightChild(null);
		}
		// If node has children
		else if (node.leftChild != null && node.rightChild != null) {
			BinarySearchNode min = node.rightChild.getNodeWithMinValue();
			node.person = min.person;
			int minKey = min.person.key;
			min.delete(minKey);
		}

		else if (node.parent.leftChild == node) {
			BinarySearchNode newLeftChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
			node.parent.setLeftChild(newLeftChild);
		}

		else if (node.parent.rightChild == node) {
			BinarySearchNode newRightChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
			node.parent.setRightChild(newRightChild);
		}

		return deleted;
	}

	/**
	 * Find minimum value.
	 * @return Minimum value.
	 */
	BinarySearchNode getNodeWithMinValue() {
		if (leftChild == null)
			return this;
		else
			return leftChild.getNodeWithMinValue();
	}

	/**
	 * Set the left child.
	 * @param child Left child.
	 */
	void setLeftChild(BinarySearchNode child) {
		this.leftChild = child;
		if (child != null)
			child.parent = this;
	}

	/**
	 * Set right child.
	 * @param child Right child.
	 */
	void setRightChild(BinarySearchNode child) {
		this.rightChild = child;
		if (child != null)
			child.parent = this;
	}

	/**
	 * Convert node to String!
	 */
	public String toString() {
		String toReturn = "";

		if (this.leftChild != null) {
			toReturn += this.leftChild.toString();
		}
		
		// Return node as string!
		toReturn += "  " + person.toString() + "\n";
		
		if (this.rightChild != null) {
			toReturn += this.rightChild.toString();
		}

		return toReturn;
	}

}
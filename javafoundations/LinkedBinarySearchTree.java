//*******************************************************************
//LinkedBinarySearchTree.java       Java Foundations
//
//Implements the binary tree using a linked representation.
//*******************************************************************

package javafoundations;

import javafoundations.*;
import javafoundations.exceptions.*;

public class LinkedBinarySearchTree<T extends Comparable<T>>
extends LinkedBinaryTree<T> implements BinarySearchTree<T>
{
	//-----------------------------------------------------------------
	//Creates an empty binary search tree.
	//-----------------------------------------------------------------
	public LinkedBinarySearchTree()
	{
		super();
	}

	//-----------------------------------------------------------------
	//Creates a binary search tree with the specified element at its
	//root.
	//-----------------------------------------------------------------
	public LinkedBinarySearchTree (T element)
	{
		root = new BSTNode<T>(element);
	}

	//-----------------------------------------------------------------
	//Adds the specified element to this binary search tree.
	//-----------------------------------------------------------------
	public void add (T item)
	{
		if (root == null)
			root = new BSTNode<T>(item);
		else
			((BSTNode)root).add(item);
	}

	//-----------------------------------------------------------------
	//Finds and returns the smallest element in the binary tree.
	//-----------------------------------------------------------------
	public T findMin ()
	{
		if (root == null)
			throw new EmptyCollectionException ("Find min operation "
				+ "failed. The tree is empty.");
		
		BTNode<T> node2 = root;

		while ((node2.getLeft()) != null)
		{
			node2 = node2.getLeft();
		}
		
		return node2.getElement();
	}
	
	//-----------------------------------------------------------------
	//Finds and returns the largest element in the binary tree.
	//-----------------------------------------------------------------
	public T findMax ()
	{
		if (root == null)
			throw new EmptyCollectionException ("Find min operation "
				+ "failed. The tree is empty.");
		
		BTNode<T> node2 = root;
		
		while ((node2.getRight()) != null)
		{
			node2 = node2.getRight();
		}
	
		return node2.getElement();
	}

	//-----------------------------------------------------------------
	//Removes and returns the element matching the specified target
	//from this binary search tree. Throws an ElementNotFoundException
	//if the target is not found.
	//-----------------------------------------------------------------
	public T remove (T target)
	{
		BSTNode<T> node = null;

		if (root != null)
			node = ((BSTNode)root).find(target);

		if (node == null)
			throw new ElementNotFoundException ("Remove operation failed. "
				+ "No such element in tree.");

		root = ((BSTNode)root).remove(target);

		return node.getElement();
	}
}

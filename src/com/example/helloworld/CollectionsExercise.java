/******************************************************************************
 * Copyright (c) 2020 Objective
 * All rights reserved
 *
 * This software is the confidential and proprietary information of Objective.
 * You shall not disclose this confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * Objective.
 *******************************************************************************/
package com.example.helloworld;

import java.util.LinkedList;
import java.util.List;

import dce.util.Collections;

public class CollectionsExercise
{
  public static void main(String[] args)
  {
    // create a list to play with
    List<Item> itemList = new LinkedList<>();
    for (int i = 0; i < 10; i++) itemList.add(new Item(i));

    // create a new list of items with category "even"
    printList(Collections.getByAttributePath(itemList, "category", "even"));
    // using Collections.getByValue
    printList(Collections.getByValue(itemList, Item::getCategory, "even"));
    // using Collections.filter
    printList(Collections.filter(itemList, item -> "even".equals(item.getCategory())));
    
    // create a new list of item names
    printList(Collections.getAssociatedObjectList(itemList, "name"));
    // using Collections.concat
    printList((Collections.concat(itemList, Item::getName)));

    // create a unique list of item categories
    printList(Collections.getUniqueAssociatedObjectList(itemList, "category"));
    // using Collections.merge
    printList(Collections.merge(itemList, Item::getCategory));
    
  }

  private static void printList(List pList)
  {
    System.out.println("count = " + Collections.size(pList));
    if (pList != null) pList.forEach(System.out::println);
    System.out.println("---");
  }

  private static class Item
  {
    private String name;
    private String category;

    public Item(int pNumber)
    {
      name = "Item " + pNumber;
      category = (pNumber % 2 == 0) ? "even" : "odd";
    }

    public String getName()
    {
      return name;
    }

    public String getCategory()
    {
      return category;
    }

    @Override
    public String toString()
    {
      return "Item [name=" + getName() + "]";
    }
  }
}

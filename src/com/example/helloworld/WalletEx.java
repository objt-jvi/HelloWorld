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

import java.util.*;
import java.util.stream.Collectors;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class WalletEx
{
  public static void main(String[] args)
  {
    Wallet wallet = new Wallet();
    Coin coin1 = new Coin("2 cent", .02);
    Coin coin2 = new Coin("5 cent", .05);
    Note note1 = new Note("10 eur", 10);
    wallet.addCurrency(coin1, 5);
    wallet.addCurrency(coin2, 10);
    wallet.addCurrency(note1, 2);
    wallet.print();
    wallet.removeCurrency(note1, 1);
    wallet.removeCurrency(note1, 5);
    wallet.print();

    double totalAmount = wallet.calculateTotalAmount();
    String[] arr = String.valueOf(totalAmount).split("\\.");
    System.out.println("Total amount: " + arr[0] + " EUR " + arr[1] + " cent");

    wallet.writeDifferentCurrenciesSorted();

    double foreignAmount = wallet.calculateForeignContent(0.78);
    System.out.println(foreignAmount);
  }

  public static class Wallet
  {
    private Map<AbstractCurrency, Integer> pCurrencyMap;

    public Wallet()
    {
      pCurrencyMap = new HashMap<>();
    }

    public void addCurrency(AbstractCurrency pAbstractCurrency, int amount)
    {
      int newAmount;
      if (pCurrencyMap.get(pAbstractCurrency) != null) newAmount = pCurrencyMap.get(pAbstractCurrency) + amount;
      else newAmount = amount;

      pCurrencyMap.put(pAbstractCurrency, newAmount);
    }

    public void removeCurrency(AbstractCurrency pAbstractCurrency, int amount)
    {
      int newAmount;
      if (pCurrencyMap.get(pAbstractCurrency) != null)
      {
        newAmount = pCurrencyMap.get(pAbstractCurrency) - amount;
        if (newAmount < 0)
        {
          System.out.println("Current amount in wallet is to low.");
          return;
        }
        pCurrencyMap.put(pAbstractCurrency, newAmount);
      }
      else
      {
        System.out.println("No currency of this type in wallet.");
        return;
      }
    }

    public double calculateTotalAmount()
    {
      double totalAmount = 0;
      for (Map.Entry<AbstractCurrency, Integer> currency : pCurrencyMap.entrySet())
      {
        totalAmount += currency.getKey().getpValue() * currency.getValue();
      }
      return totalAmount;
    }

    public void print()
    {
      for (Map.Entry<AbstractCurrency, Integer> currency : pCurrencyMap.entrySet())
      {
        System.out.println(currency.toString());
      }
    }

    public void writeDifferentCurrenciesSorted()
    {
      Set<AbstractCurrency> currencies = pCurrencyMap.keySet();
      List<AbstractCurrency> sortedSet = currencies.stream().sorted(new Comparator<AbstractCurrency>()
      {
        @Override
        public int compare(AbstractCurrency o1, AbstractCurrency o2)
        {
          if (o1.getpValue() > o2.getpValue()) return 1;
          else if (o1.getpValue() == o2.getpValue()) return 0;
          else return -1;
        }
      }).collect(Collectors.toList());

      for (AbstractCurrency abstractCurrency : sortedSet)
      {
        System.out.println(abstractCurrency.toString());
      }
    }

    public Double calculateForeignContent(Double pExchangeFactor)
    {
      return calculateTotalAmount()*pExchangeFactor;
    }
  }

  public static class AbstractCurrency
  {
    private String pID;
    private double pValue;

    public AbstractCurrency(String ID, double value)
    {
      pID = ID;
      pValue = value;
    }

    @Override
    public boolean equals(Object pO)
    {
      if (this == pO) return true;
      if (pO == null || getClass() != pO.getClass()) return false;
      AbstractCurrency that = (AbstractCurrency) pO;
      return pID.equals(that.pID);
    }

    public String getpID()
    {
      return pID;
    }

    public double getpValue()
    {
      return pValue;
    }

    @Override
    public String toString()
    {
      return getClass().getName() + "{" + "ID='" + pID + '\'' + ", Value=" + pValue + '}';
    }
  }

  public static class Coin extends AbstractCurrency
  {
    public Coin(String ID, double value)
    {
      super(ID, value);
    }
  }

  public static class Note extends AbstractCurrency
  {
    public Note(String ID, double value)
    {
      super(ID, value);
    }
  }
}



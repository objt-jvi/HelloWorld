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

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageLoggerEx
{

  public static void main(String[] args) throws IOException
  {
    // MessageLogger
    /*MessageLogger messageLogger = new MessageLogger(2);

    // Show method
    messageLogger.show("First method");

    messageLogger.log("log1", true);
    messageLogger.log("log2", true);
    messageLogger.log("log3", false);
    messageLogger.showAll();
    messageLogger.log("log4", true);
    messageLogger.showAll();     */

    //MultiLogger
    /*List<String> stringList = new ArrayList<>(Arrays.asList("String1", "String2", "String3"));
    MultiLogger multiLogger = new MultiLogger(3);
    multiLogger.log(stringList);
    multiLogger.showAll();
    multiLogger.log("String4", true);
    multiLogger.showAll(); */

    // DateLogger
    /*DateLogger dateLogger = new DateLogger(3, "yyyy-MM-dd HH:mm:ss");
    dateLogger.log("String 1");
    dateLogger.log("String 2");
    dateLogger.log("String 3");
    dateLogger.showAll();
    dateLogger.log("String 4");
    dateLogger.showAll(); */

    // FileLogger
    /*List<String> logList = new ArrayList<>(Arrays.asList("String1", "String2", "String3", "String4"));
    FileLogger fileLogger = new FileLogger(3,"testFile1.txt");
    for(String log : logList)
    {
      fileLogger.log(log, true);
    }
    fileLogger.writeAll();
    fileLogger.stop();*/
  }

  // MessageLogger
  public static class MessageLogger
  {
    // size of list for last loggings
    private int logSize;
    // list for last loggings
    List<String> lastLogs;

    public MessageLogger(int pLogSize)
    {
      logSize = pLogSize;
      lastLogs = new ArrayList<>();
    }

    public void show(String pText)
    {
      System.out.println(pText);
    }

    public void checkAndAdd(String pText)
    {
      if (lastLogs.size() == logSize) lastLogs.remove(0);
      lastLogs.add(pText);
    }

    public void log(String pText, boolean pAddToList)
    {
      if (pAddToList) checkAndAdd(pText);
      else show(pText);
    }

    public void showAll()
    {
      for (String log : lastLogs)
      {
        show(log);
      }
      System.out.println("\n");
    }
  }

  // MultiLogger
  public static class MultiLogger extends MessageLogger
  {

    public MultiLogger(int pLogSize)
    {
      super(pLogSize);
    }

    public void log(List<String> pStrings)
    {
      for (String logString : pStrings)
      {
        log(logString, true);
      }
    }
  }

  // DateLogger
  public static class DateLogger extends MessageLogger
  {
    DateFormat dateFormat;

    public DateLogger(int pLogSize, String pDateFormat)
    {
      super(pLogSize);
      dateFormat = new SimpleDateFormat(pDateFormat);
    }

    public void log(String pString)
    {
      String date = dateFormat.format(new Date());
      log(date + " " + pString, true);
    }
  }

  // FileLogger
  public static class FileLogger extends MessageLogger
  {
    FileWriter fileWriter;

    public FileLogger(int pLogSize, String fileName) throws IOException
    {
      super(pLogSize);
      fileWriter = new FileWriter(fileName);
    }

    public void writeAll() throws IOException
    {
      for(String log : lastLogs)
      {
        System.out.println(log);
        fileWriter.write(log);
      }
    }

    public void stop() throws IOException
    {
      fileWriter.close();
    }
  }
}

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

import dce.util.eventlog.EventLog;
import dce.util.text.StringUtil;

public class EventLogEx
{
  private static String pHello = "Hello, World. Greetings from %1";

  public static void sayHello(String pString)
  {
    // init the eventlog
    System.setProperty("EVENTLOG.FILEPATH", ".");
    new EventLog(EventLog.LEVEL_DEBUG, "eventlogging%g.txt", true);

    String log = StringUtil.replacePlaceholders(pHello, pString);
    System.out.println(log);
    // log message
    EventLog.debug(EventLogEx.class.getName(), "methodName", log);

    // shutdown the eventlog
    EventLog.get().shutdown();
  }

  public static void main(String[] args)
  {
    sayHello(args[0]);
  }
}

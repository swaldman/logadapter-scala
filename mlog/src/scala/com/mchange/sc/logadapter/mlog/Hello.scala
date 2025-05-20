package com.mchange.sc.logadapter.mlog

import com.mchange.sc.logadapter.*, Level.*


object Hello extends SelfLogging:
  def main(args : Array[String]) : Unit =
    //given MLogAdapter = com.mchange.sc.logadapter.mlog.logAdapterByFilename
    INFO.logDebug("hello!")
    INFO.logDebug("goodbye!")





package logadapter.test

//import com.mchange.sc.logadapter.*, Level.*

import logadapter.scribe.Api.*

object Hello extends SelfLogging:
  def main(args : Array[String]) : Unit =
    //given MLogAdapter = com.mchange.sc.logadapter.mlog.logAdapterByFilename
    INFO.log("hello!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!")




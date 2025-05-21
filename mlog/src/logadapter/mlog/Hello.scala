package logadapter.mlog

//import com.mchange.sc.logadapter.*, Level.*

import logadapter.stderr.Api.*


object Hello extends SelfLogging:
  def main(args : Array[String]) : Unit =
    //given MLogAdapter = com.mchange.sc.logadapter.mlog.logAdapterByFilename
    INFO.log("hello!")
    INFO.log("goodbye!")
    SEVERE.log("Ouch!")




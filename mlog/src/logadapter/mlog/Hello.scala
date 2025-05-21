package logadapter.mlog

//import com.mchange.sc.logadapter.*, Level.*

import Api.*


object Hello extends SelfLogging:
  def main(args : Array[String]) : Unit =
    //given MLogAdapter = com.mchange.sc.logadapter.mlog.logAdapterByFilename
    INFO.log("hello!")
    INFO.log("goodbye!")





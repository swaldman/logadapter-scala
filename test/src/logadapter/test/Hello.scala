package logadapter.test

// I'd like to be able to elegantly abstract over the Api objects.
// But it's tricky, and I'm hitting this bug, so I'm going to go with inelegant repetition for now!
//
// Bug: https://github.com/scala/scala3/issues/23245

object Hello:
  def helloScribe() : Unit =
    println( s"------------- hello scribe -------------" )
    import logadapter.scribe.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello scribe!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello scribe!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def helloStderr() : Unit =
    println( s"------------- hello stderr -------------" )
    import logadapter.stderr.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello stderr!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello stderr!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def helloMlog() : Unit =
    println( s"------------- hello mlog -------------" )
    import logadapter.mlog.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello mlog!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello mlog!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def helloJul() : Unit =
    println( s"------------- hello jul -------------" )
    import logadapter.jul.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello jul!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello jul!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def helloLog4j2() : Unit =
    println( s"------------- hello log4j2 -------------" )
    import logadapter.log4j2.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello log4j2!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello log4j2!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def helloSlf4j() : Unit =
    println( s"------------- hello slf4j- -------------" )
    import logadapter.slf4j.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello slf4j!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!", new Exception())
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello slf4j!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!", new Exception())
  def main(args : Array[String]) : Unit =
    helloScribe()
    Thread.sleep(100)
    helloStderr()
    Thread.sleep(100)
    helloMlog()
    Thread.sleep(100)
    helloJul()
    Thread.sleep(100)
    helloLog4j2()
    Thread.sleep(100)
    helloSlf4j()





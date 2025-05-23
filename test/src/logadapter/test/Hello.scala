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
        SEVERE.logDebug("Ouch!")
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello scribe!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!")
  def helloStderr() : Unit =
    println( s"------------- hello stderr -------------" )
    import logadapter.stderr.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello stderr!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!")
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello stderr!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!")
  def helloMlog() : Unit =
    println( s"------------- hello mlog -------------" )
    import logadapter.mlog.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello mlog!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!")
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello mlog!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!")
  def helloJul() : Unit =
    println( s"------------- hello jul -------------" )
    import logadapter.jul.Api.*
    object HelloObject extends SelfLogging:
      def inside() : Unit =
        INFO.log(s"Hello jul!")
        INFO.log("goodbye!")
        SEVERE.logDebug("Ouch!")
    HelloObject.inside()    
    given LogAdapter = logAdapterByFilename  
    INFO.log(s"Hello jul!")
    INFO.log("goodbye!")
    SEVERE.logDebug("Ouch!")
  def main(args : Array[String]) : Unit =
    helloScribe()
    helloStderr()
    helloMlog()
    helloJul()






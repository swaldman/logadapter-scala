package logadapter.scribe

import scribe.Logger

class LogAdapter( loggerName : String ) extends AbstractLogAdapter:
  val immutableLogger = Logger( loggerName )
  inline def logger : scribe.Logger = immutableLogger
end LogAdapter


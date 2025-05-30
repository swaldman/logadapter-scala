package logadapter.jul

import java.util.logging.{Level as JLevel, Logger as JLogger}

object LogAdapter:
  inline def DebugLevel = JLevel.FINE
  inline def ErrorLevel = JLevel.WARNING
  inline def FatalLevel = JLevel.SEVERE
  inline def TraceLevel = JLevel.FINEST
class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  import LogAdapter.*

  // we don't inline def this. we prefer the field lookup to re-looking up the logger
  val jlogger = JLogger.getLogger( loggerName )

  private inline def log( inline jlevel : JLevel, message : =>String ) : Unit =
    val jlgr = jlogger // cache to avoid double field lookups
    val jlvl = jlevel  // cache to avoid double field lookups
    if jlgr.isLoggable(jlvl) then jlgr.log( jlvl, message )

  private inline def log( inline jlevel : JLevel, message : =>String, t : Throwable ) : Unit =
    val jlgr = jlogger // cache to avoid double field lookups
    val jlvl = jlevel  // cache to avoid double field lookups
    if jlgr.isLoggable(jlvl) then jlgr.log( jlvl, message, t )

  inline def config  ( message : =>String )                : Unit = log(JLevel.CONFIG,  message)
  inline def config  ( message : =>String, t : Throwable ) : Unit = log(JLevel.CONFIG,  message, t)
  inline def debug   ( message : =>String )                : Unit = log(DebugLevel,     message)
  inline def debug   ( message : =>String, t : Throwable ) : Unit = log(DebugLevel,     message, t)
  inline def error   ( message : =>String )                : Unit = log(ErrorLevel,     message)
  inline def error   ( message : =>String, t : Throwable ) : Unit = log(ErrorLevel,     message, t)
  inline def fatal   ( message : =>String )                : Unit = log(FatalLevel,     message)
  inline def fatal   ( message : =>String, t : Throwable ) : Unit = log(FatalLevel,     message, t)
  inline def fine    ( message : =>String )                : Unit = log(JLevel.FINE,    message)
  inline def fine    ( message : =>String, t : Throwable ) : Unit = log(JLevel.FINE,    message, t)
  inline def finer   ( message : =>String )                : Unit = log(JLevel.FINER,   message)
  inline def finer   ( message : =>String, t : Throwable ) : Unit = log(JLevel.FINER,   message, t)
  inline def finest  ( message : =>String )                : Unit = log(JLevel.FINEST,  message)
  inline def finest  ( message : =>String, t : Throwable ) : Unit = log(JLevel.FINEST,  message, t)
  inline def info    ( message : =>String )                : Unit = log(JLevel.INFO,    message)
  inline def info    ( message : =>String, t : Throwable ) : Unit = log(JLevel.INFO,    message, t)
  inline def severe  ( message : =>String )                : Unit = log(JLevel.SEVERE,  message)
  inline def severe  ( message : =>String, t : Throwable ) : Unit = log(JLevel.SEVERE,  message, t)
  inline def trace   ( message : =>String )                : Unit = log(TraceLevel,     message)
  inline def trace   ( message : =>String, t : Throwable ) : Unit = log(TraceLevel,     message, t)
  inline def warning ( message : =>String )                : Unit = log(JLevel.WARNING, message)
  inline def warning ( message : =>String, t : Throwable ) : Unit = log(JLevel.WARNING, message, t)
end LogAdapter

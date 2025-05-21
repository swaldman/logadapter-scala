package logadapter.jul

import java.util.logging.{Level as JLevel, Logger as JLogger}

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:

  val logger = JLogger.getLogger( loggerName )

  val DebugLevel = JLevel.FINE
  val TraceLevel = JLevel.FINEST

  private inline def log( inline jlevel : JLevel, inline message : =>String ) : Unit =
    if logger.isLoggable(jlevel) then logger.log( jlevel, message )

  private inline def log( inline jlevel : JLevel, inline message : =>String, inline t : Throwable ) : Unit =
    if logger.isLoggable(jlevel) then logger.log( jlevel, message, t )

  inline def config  ( inline message : =>String )                       : Unit = log(JLevel.CONFIG,  message)
  inline def config  ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.CONFIG,  message, t)
  inline def debug   ( inline message : =>String )                       : Unit = log(DebugLevel,   message)
  inline def debug   ( inline message : =>String, inline t : Throwable ) : Unit = log(DebugLevel,   message, t)
  inline def fine    ( inline message : =>String )                       : Unit = log(JLevel.FINE,    message)
  inline def fine    ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.FINE,    message, t)
  inline def finer   ( inline message : =>String )                       : Unit = log(JLevel.FINER,   message)
  inline def finer   ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.FINER,   message, t)
  inline def finest  ( inline message : =>String )                       : Unit = log(JLevel.FINEST,  message)
  inline def finest  ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.FINEST,  message, t)
  inline def info    ( inline message : =>String )                       : Unit = log(JLevel.INFO,    message)
  inline def info    ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.INFO,    message, t)
  inline def severe  ( inline message : =>String )                       : Unit = log(JLevel.SEVERE,  message)
  inline def severe  ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.SEVERE,  message, t)
  inline def trace   ( inline message : =>String )                       : Unit = log(TraceLevel,   message)
  inline def trace   ( inline message : =>String, inline t : Throwable ) : Unit = log(TraceLevel,   message, t)
  inline def warning ( inline message : =>String )                       : Unit = log(JLevel.WARNING, message)
  inline def warning ( inline message : =>String, inline t : Throwable ) : Unit = log(JLevel.WARNING, message, t)
end LogAdapter

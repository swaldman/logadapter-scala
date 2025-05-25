package logadapter.log4j2

import org.apache.logging.log4j.*

object LogAdapter:
  inline def ConfigLevel = Level.INFO
  inline def FineLevel   = Level.DEBUG
  inline def FinerLevel  = Level.DEBUG
  inline def FinestLevel = Level.TRACE
  inline def SevereLevel = Level.ERROR
class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  import LogAdapter.*

  // we don't inline def this. we prefer the field lookup to relooking up the logger.
  val logger = LogManager.getLogger( loggerName )

  inline def log( inline level : Level, message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    val lvl = level  // cache to avoid double field lookups
    if lgr.isEnabled( lvl ) then lgr.log( lvl, message )

  inline def log( inline level : Level, message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    val lvl = level  // cache to avoid double field lookups
    if lgr.isEnabled( lvl ) then lgr.log( lvl, message )

  inline def config( message : =>String )                 : Unit = log( ConfigLevel, message )
  inline def config( message : =>String, t : Throwable )  : Unit = log( ConfigLevel, message, t )
  inline def debug( message : =>String )                  : Unit = log( Level.DEBUG, message )
  inline def debug( message : =>String, t : Throwable )   : Unit = log( Level.DEBUG, message, t )
  inline def error( message : =>String )                  : Unit = log( Level.ERROR, message )
  inline def error( message : =>String, t : Throwable )   : Unit = log( Level.ERROR, message, t )
  inline def fatal( message : =>String )                  : Unit = log( Level.FATAL, message )
  inline def fatal( message : =>String, t : Throwable )   : Unit = log( Level.FATAL, message, t )
  inline def fine( message : =>String )                   : Unit = log( FineLevel, message )
  inline def fine( message : =>String, t : Throwable )    : Unit = log( FineLevel, message, t )
  inline def finer( message : =>String )                  : Unit = log( FinerLevel, message )
  inline def finer( message : =>String, t : Throwable )   : Unit = log( FinerLevel, message, t )
  inline def finest( message : =>String )                 : Unit = log( FinestLevel, message )
  inline def finest( message : =>String, t : Throwable )  : Unit = log( FinestLevel, message, t )
  inline def info( message : =>String )                   : Unit = log( Level.INFO, message )
  inline def info( message : =>String, t : Throwable )    : Unit = log( Level.INFO, message, t )
  inline def severe( message : =>String )                 : Unit = log( SevereLevel, message )
  inline def severe( message : =>String, t : Throwable )  : Unit = log( SevereLevel, message, t )
  inline def trace( message : =>String )                  : Unit = log( Level.TRACE, message )
  inline def trace( message : =>String, t : Throwable )   : Unit = log( Level.TRACE, message, t )
  inline def warning( message : =>String )                : Unit = log( Level.WARN, message )
  inline def warning( message : =>String, t : Throwable ) : Unit = log( Level.WARN, message, t )
end LogAdapter

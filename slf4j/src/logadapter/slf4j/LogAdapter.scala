package logadapter.slf4j

import org.slf4j.*;

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:

  // we don't inline def this, we'd rather the field lookup than relookup the logger
  val logger = LoggerFactory.getLogger( loggerName )

  inline def debug( message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isDebugEnabled then lgr.debug( message )
  inline def debug( message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isDebugEnabled then lgr.debug( message, t )
  inline def error( message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isErrorEnabled then lgr.error( message )
  inline def error( message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isErrorEnabled then lgr.error( message, t )
  inline def info( message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isInfoEnabled  then lgr.info( message )
  inline def info( message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isInfoEnabled  then lgr.info( message, t )
  inline def trace( message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isTraceEnabled then lgr.trace( message )
  inline def trace( message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isTraceEnabled then lgr.trace( message, t )
  inline def warning( message : =>String ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isWarnEnabled  then lgr.warn( message )
  inline def warning( message : =>String, t : Throwable ) : Unit =
    val lgr = logger // cache to avoid double field lookups
    if lgr.isWarnEnabled  then lgr.warn( message, t )

  inline def config( message : =>String )                 : Unit = info ( message )
  inline def config( message : =>String, t : Throwable )  : Unit = info ( message, t )
  inline def fatal ( message : =>String )                 : Unit = error( message )
  inline def fatal ( message : =>String, t : Throwable )  : Unit = error( message, t )
  inline def fine  ( message : =>String )                 : Unit = debug( message )
  inline def fine  ( message : =>String, t : Throwable )  : Unit = debug( message, t )
  inline def finer ( message : =>String )                 : Unit = debug( message )
  inline def finer ( message : =>String, t : Throwable )  : Unit = debug( message, t )
  inline def finest( message : =>String )                 : Unit = trace( message )
  inline def finest( message : =>String, t : Throwable )  : Unit = trace( message, t )
  inline def severe( message : =>String )                 : Unit = error( message )
  inline def severe( message : =>String, t : Throwable )  : Unit = error( message, t )

end LogAdapter

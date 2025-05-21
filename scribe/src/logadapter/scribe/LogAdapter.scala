package logadapter.scribe

import scribe.Logger
import com.mchange.conveniences.throwable.*

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:

  private val LineSep = scala.util.Properties.lineSeparator

  val logger = Logger( loggerName )

  inline def config  ( inline message : =>String )                       : Unit = logger.info( message )
  inline def config  ( inline message : =>String, inline t : Throwable ) : Unit = logger.info( message + LineSep + t.fullStackTrace )
  inline def debug   ( inline message : =>String )                       : Unit = logger.debug( message )
  inline def debug   ( inline message : =>String, inline t : Throwable ) : Unit = logger.debug( message + LineSep + t.fullStackTrace )
  inline def fine    ( inline message : =>String )                       : Unit = logger.debug( message )
  inline def fine    ( inline message : =>String, inline t : Throwable ) : Unit = logger.debug( message + LineSep + t.fullStackTrace )
  inline def finer   ( inline message : =>String )                       : Unit = logger.trace( message )
  inline def finer   ( inline message : =>String, inline t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def finest  ( inline message : =>String )                       : Unit = logger.trace( message )
  inline def finest  ( inline message : =>String, inline t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def info    ( inline message : =>String )                       : Unit = logger.info( message )
  inline def info    ( inline message : =>String, inline t : Throwable ) : Unit = logger.info( message + LineSep + t.fullStackTrace )
  inline def severe  ( inline message : =>String )                       : Unit = logger.fatal( message )
  inline def severe  ( inline message : =>String, inline t : Throwable ) : Unit = logger.fatal( message + LineSep + t.fullStackTrace )
  inline def trace   ( inline message : =>String )                       : Unit = logger.trace( message )
  inline def trace   ( inline message : =>String, inline t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def warning ( inline message : =>String )                       : Unit = logger.warn( message )
  inline def warning ( inline message : =>String, inline t : Throwable ) : Unit = logger.warn( message + LineSep + t.fullStackTrace )
  
end LogAdapter


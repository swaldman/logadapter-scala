package logadapter.scribe

import scribe.Logger
import com.mchange.conveniences.throwable.*


/*
 * We have an AbstractLogAdapter because we wanted to try a "dynamic" version in which Logger( loggerName ),
 * rather than a constant, would be inlined as the logger. We thought that would be required to dynamically
 * update config.
 *
 * But the constant version is much faster (~5x), and it seems (somehow?) to support dynamic config updating
 * anyway, so there is no upside to it?
 *
 * I'll leave the "dynamic" implementation around in the logadapter.scribe.dynamic subpackage,
 * but I won't document it unless I can reproduce the not-updating-config I swear I did originally
 * observe, that inspired the exercise.
 */
abstract class AbstractLogAdapter extends logadapter.LogAdapter:

  private val LineSep = scala.util.Properties.lineSeparator

  inline def logger : scribe.Logger //= Logger( loggerName ) // we use a def to refetch by name from global state, so it's reconfigurable

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
  
end AbstractLogAdapter


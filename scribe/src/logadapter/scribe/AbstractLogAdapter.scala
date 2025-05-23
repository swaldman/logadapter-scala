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

  inline def logger : scribe.Logger

  inline def config  ( message : =>String )                : Unit = logger.info( message )
  inline def config  ( message : =>String, t : Throwable ) : Unit = logger.info( message + LineSep + t.fullStackTrace )
  inline def debug   ( message : =>String )                : Unit = logger.debug( message )
  inline def debug   ( message : =>String, t : Throwable ) : Unit = logger.debug( message + LineSep + t.fullStackTrace )
  inline def error   ( message : =>String )                : Unit = logger.error( message )
  inline def error   ( message : =>String, t : Throwable ) : Unit = logger.error( message + LineSep + t.fullStackTrace )
  inline def fatal   ( message : =>String )                : Unit = logger.fatal( message )
  inline def fatal   ( message : =>String, t : Throwable ) : Unit = logger.fatal( message + LineSep + t.fullStackTrace )
  inline def fine    ( message : =>String )                : Unit = logger.debug( message )
  inline def fine    ( message : =>String, t : Throwable ) : Unit = logger.debug( message + LineSep + t.fullStackTrace )
  inline def finer   ( message : =>String )                : Unit = logger.trace( message )
  inline def finer   ( message : =>String, t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def finest  ( message : =>String )                : Unit = logger.trace( message )
  inline def finest  ( message : =>String, t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def info    ( message : =>String )                : Unit = logger.info( message )
  inline def info    ( message : =>String, t : Throwable ) : Unit = logger.info( message + LineSep + t.fullStackTrace )
  inline def severe  ( message : =>String )                : Unit = logger.error( message )
  inline def severe  ( message : =>String, t : Throwable ) : Unit = logger.error( message + LineSep + t.fullStackTrace )
  inline def trace   ( message : =>String )                : Unit = logger.trace( message )
  inline def trace   ( message : =>String, t : Throwable ) : Unit = logger.trace( message + LineSep + t.fullStackTrace )
  inline def warning ( message : =>String )                : Unit = logger.warn( message )
  inline def warning ( message : =>String, t : Throwable ) : Unit = logger.warn( message + LineSep + t.fullStackTrace )
  
end AbstractLogAdapter


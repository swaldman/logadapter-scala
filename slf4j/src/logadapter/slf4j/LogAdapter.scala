package logadapter.slf4j


import org.slf4j.*;

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:

  val logger = LoggerFactory.getLogger( loggerName )

  inline def debug  ( message : =>String )                : Unit = if logger.isDebugEnabled then logger.debug( message )
  inline def debug  ( message : =>String, t : Throwable ) : Unit = if logger.isDebugEnabled then logger.debug( message, t )
  inline def error  ( message : =>String )                : Unit = if logger.isErrorEnabled then logger.error( message )
  inline def error  ( message : =>String, t : Throwable ) : Unit = if logger.isErrorEnabled then logger.error( message, t )
  inline def info   ( message : =>String )                : Unit = if logger.isInfoEnabled  then logger.info( message )
  inline def info   ( message : =>String, t : Throwable ) : Unit = if logger.isInfoEnabled  then logger.info( message, t )
  inline def trace  ( message : =>String )                : Unit = if logger.isTraceEnabled then logger.trace( message )
  inline def trace  ( message : =>String, t : Throwable ) : Unit = if logger.isTraceEnabled then logger.trace( message, t )
  inline def warning( message : =>String )                : Unit = if logger.isWarnEnabled  then logger.warn( message )
  inline def warning( message : =>String, t : Throwable ) : Unit = if logger.isWarnEnabled  then logger.warn( message, t )

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

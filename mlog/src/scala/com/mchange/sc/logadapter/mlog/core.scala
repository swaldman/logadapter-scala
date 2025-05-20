package com.mchange.sc.logadapter.mlog

import com.mchange.sc.logadapter.*, Level.*

class LogAdapter( loggerName : String ) extends AbstractLogAdapter:
  import com.mchange.sc.v1.log.*
  inline given logger : MLogger = MLogger( loggerName )
  inline def all( inline message : =>String )                             : Unit = MLevel.ALL.log(message)
  inline def all( inline message : =>String, inline t : =>Throwable )     : Unit = MLevel.ALL.log(message,t)
  inline def config( inline message : =>String )                          : Unit = MLevel.CONFIG.log(message)
  inline def config( inline message : =>String, inline t : =>Throwable )  : Unit = MLevel.CONFIG.log(message,t)
  inline def debug( inline message : =>String )                           : Unit = MLevel.DEBUG.log(message)
  inline def debug( inline message : =>String, inline t : =>Throwable )   : Unit = MLevel.DEBUG.log(message,t)
  inline def fine( inline message : =>String )                            : Unit = MLevel.FINE.log(message)
  inline def fine( inline message : =>String, inline t : =>Throwable )    : Unit = MLevel.FINE.log(message,t)
  inline def finer( inline message : =>String )                           : Unit = MLevel.FINER.log(message)
  inline def finer( inline message : =>String, inline t : =>Throwable )   : Unit = MLevel.FINER.log(message,t)
  inline def finest( inline message : =>String )                          : Unit = MLevel.FINEST.log(message)
  inline def finest( inline message : =>String, inline t : =>Throwable )  : Unit = MLevel.FINEST.log(message,t)
  inline def info( inline message : =>String )                            : Unit = MLevel.INFO.log(message)
  inline def info( inline message : =>String, inline t : =>Throwable )    : Unit = MLevel.INFO.log(message,t)
  inline def severe( inline message : =>String )                          : Unit = MLevel.SEVERE.log(message)
  inline def severe( inline message : =>String, inline t : =>Throwable )  : Unit = MLevel.SEVERE.log(message,t)
  inline def trace( inline message : =>String )                           : Unit = MLevel.TRACE.log(message)
  inline def trace( inline message : =>String, inline t : =>Throwable )   : Unit = MLevel.TRACE.log(message,t)
  inline def warning( inline message : =>String )                         : Unit = MLevel.WARNING.log(message)
  inline def warning( inline message : =>String, inline t : =>Throwable ) : Unit = MLevel.WARNING.log(message,t)
end LogAdapter

extension ( level : Level )
  inline def log( message : =>String )( using la : LogAdapter ) : Unit =
    inline level match
      case ALL     => la.all(message)
      case CONFIG  => la.config(message)
      case DEBUG   => la.debug(message)
      case FINE    => la.fine(message)
      case FINER   => la.finer(message)
      case FINEST  => la.finest(message)
      case INFO    => la.info(message)
      case OFF     => la.off(message)
      case SEVERE  => la.severe(message)
      case TRACE   => la.trace(message)
      case WARNING => la.warning(message)
  end log

  inline def log( message : =>String, t : =>Throwable )( using la : LogAdapter ) : Unit =
    inline level match
      case ALL     => la.all(message, t)
      case CONFIG  => la.config(message, t)
      case DEBUG   => la.debug(message, t)
      case FINE    => la.fine(message, t)
      case FINER   => la.finer(message, t)
      case FINEST  => la.finest(message, t)
      case INFO    => la.info(message, t)
      case OFF     => la.off(message, t)
      case SEVERE  => la.severe(message, t)
      case TRACE   => la.trace(message, t)
      case WARNING => la.warning(message, t)
  end log

  inline def logDebug( message : =>String )( using la : LogAdapter, enclosing : sourcecode.Enclosing, line : sourcecode.Line ) : Unit =
    log( message + s" [${enclosing.value}:${line.value}]" )
    
  inline def logDebug( message : =>String, t : =>Throwable )( using la : LogAdapter, enclosing : sourcecode.Enclosing, line : sourcecode.Line ) : Unit =
    log( message + s" [${enclosing.value}:${line.value}]", t )

  inline def logEval[T]( label : =>String )( expression : =>T )( using la : LogAdapter ) : T =
    val expressionValue = expression;
    log( label + ": " + expressionValue )
    expressionValue
  end logEval

  inline def logEval[T]( expression : =>T )( using la : LogAdapter ) : T =
    val expressionValue = expression;
    log( expressionValue.toString )
    expressionValue
  end logEval

  inline def apply[T]( expression : =>T )( using la : LogAdapter ) : T = logEval( expression )
end extension

inline def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
inline def logAdapterFor( clz : Class[_] )      : LogAdapter = logAdapterFor(classNameToLoggerName(clz.getName))
inline def logAdapterFor( obj : Any )           : LogAdapter = logAdapterFor(obj.getClass)

inline def logAdapterByFilename( using fn : sourcecode.FileName ) : LogAdapter = logAdapterFor( fn.value )

trait SelfLogging:
  given LogAdapter = logAdapterFor(this)




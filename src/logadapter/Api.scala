package logadapter

trait Api[T <: LogAdapter]:

  type LogAdapter = T

  enum Level:
    case CONFIG  extends Level
    case DEBUG   extends Level
    case FINE    extends Level
    case FINER   extends Level
    case FINEST  extends Level
    case INFO    extends Level
    case SEVERE  extends Level
    case TRACE   extends Level
    case WARNING extends Level

    inline def log( message : =>String )( using la : T ) : Unit =
      inline this match
        case CONFIG  => la.config(message)
        case DEBUG   => la.debug(message)
        case FINE    => la.fine(message)
        case FINER   => la.finer(message)
        case FINEST  => la.finest(message)
        case INFO    => la.info(message)
        case SEVERE  => la.severe(message)
        case TRACE   => la.trace(message)
        case WARNING => la.warning(message)
    end log

    inline def log( message : =>String, t : =>Throwable )( using la : T ) : Unit =
      inline this match
        case CONFIG  => la.config(message, t)
        case DEBUG   => la.debug(message, t)
        case FINE    => la.fine(message, t)
        case FINER   => la.finer(message, t)
        case FINEST  => la.finest(message, t)
        case INFO    => la.info(message, t)
        case SEVERE  => la.severe(message, t)
        case TRACE   => la.trace(message, t)
        case WARNING => la.warning(message, t)
    end log

    inline def logDebug( message : =>String )( using la : T, enclosing : sourcecode.Enclosing, line : sourcecode.Line ) : Unit =
      log( message + s" [${enclosing.value}:${line.value}]" )

    inline def logDebug( message : =>String, t : =>Throwable )( using la : T, enclosing : sourcecode.Enclosing, line : sourcecode.Line ) : Unit =
      log( message + s" [${enclosing.value}:${line.value}]", t )

    inline def logEval[X]( label : =>String )( expression : =>X )( using la : T ) : X =
      val expressionValue = expression;
      log( label + ": " + expressionValue )
      expressionValue
    end logEval

    inline def logEval[X]( expression : =>X )( using la : T ) : X =
      val expressionValue = expression;
      log( expressionValue.toString )
      expressionValue
    end logEval

    inline def apply[X]( expression : =>X )( using la : T ) : X = logEval( expression )
  end Level

  export Level.*

  def classNameToLoggerName( clzName : String ) : String = clzName.reverse.dropWhile( _ == '$' ).map( c => if ( c == '$' ) '.' else c ).reverse

  def logAdapterFor( loggerName : String ) : T
  def logAdapterFor( clz : Class[_] )      : T = logAdapterFor(classNameToLoggerName(clz.getName))
  def logAdapterFor( obj : Any )           : T = logAdapterFor(obj.getClass)

  def logAdapterByFilename( using fn : sourcecode.FileName ) : T = logAdapterFor( fn.value )
end Api

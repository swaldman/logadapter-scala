package com.mchange.sc.logadapter

enum Level:
  case ALL     extends Level
  case CONFIG  extends Level
  case DEBUG   extends Level
  case FINE    extends Level
  case FINER   extends Level
  case FINEST  extends Level
  case INFO    extends Level
  case OFF     extends Level
  case SEVERE  extends Level
  case TRACE   extends Level
  case WARNING extends Level

  inline def log( message : =>String )( using la : LogAdapter ) =
    inline this match
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

  inline def log( message : =>String, t : Throwable )( using la : LogAdapter ) =
    inline this match
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
end Level


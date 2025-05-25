package logadapter.mlog

import com.mchange.sc.v1.log.*

object LogAdapter:
  inline def ErrorMLevel = MLevel.WARNING
  inline def FatalMLevel = MLevel.SEVERE
class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  import LogAdapter.*

  // cache don't inline
  // not sure why given logger : MLogger = MLogger( loggerName ) seems not to cache,
  // is given x : X = whatevev() equivalent to implicit def rather than implicit val?
  val cached = MLogger( loggerName )

  given logger : MLogger = cached

  inline def config( message : =>String )                 : Unit = MLevel.CONFIG.log(message)
  inline def config( message : =>String, t : Throwable )  : Unit = MLevel.CONFIG.log(message,t)
  inline def debug( message : =>String )                  : Unit = MLevel.DEBUG.log(message)
  inline def debug( message : =>String, t : Throwable )   : Unit = MLevel.DEBUG.log(message,t)
  inline def error( message : =>String )                  : Unit = ErrorMLevel.log(message)
  inline def error( message : =>String, t : Throwable )   : Unit = ErrorMLevel.log(message,t)
  inline def fatal( message : =>String )                  : Unit = FatalMLevel.log(message)
  inline def fatal( message : =>String, t : Throwable )   : Unit = FatalMLevel.log(message,t)
  inline def fine( message : =>String )                   : Unit = MLevel.FINE.log(message)
  inline def fine( message : =>String, t : Throwable )    : Unit = MLevel.FINE.log(message,t)
  inline def finer( message : =>String )                  : Unit = MLevel.FINER.log(message)
  inline def finer( message : =>String, t : Throwable )   : Unit = MLevel.FINER.log(message,t)
  inline def finest( message : =>String )                 : Unit = MLevel.FINEST.log(message)
  inline def finest( message : =>String, t : Throwable )  : Unit = MLevel.FINEST.log(message,t)
  inline def info( message : =>String )                   : Unit = MLevel.INFO.log(message)
  inline def info( message : =>String, t : Throwable )    : Unit = MLevel.INFO.log(message,t)
  inline def severe( message : =>String )                 : Unit = MLevel.SEVERE.log(message)
  inline def severe( message : =>String, t : Throwable )  : Unit = MLevel.SEVERE.log(message,t)
  inline def trace( message : =>String )                  : Unit = MLevel.TRACE.log(message)
  inline def trace( message : =>String, t : Throwable )   : Unit = MLevel.TRACE.log(message,t)
  inline def warning( message : =>String )                : Unit = MLevel.WARNING.log(message)
  inline def warning( message : =>String, t : Throwable ) : Unit = MLevel.WARNING.log(message,t)
end LogAdapter

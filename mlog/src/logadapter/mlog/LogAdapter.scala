package logadapter.mlog

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  import com.mchange.sc.v1.log.*
  inline given logger : MLogger = MLogger( loggerName )
  inline def config( inline message : =>String )                        : Unit = MLevel.CONFIG.log(message)
  inline def config( inline message : =>String, inline t : Throwable )  : Unit = MLevel.CONFIG.log(message,t)
  inline def debug( inline message : =>String )                         : Unit = MLevel.DEBUG.log(message)
  inline def debug( inline message : =>String, inline t : Throwable )   : Unit = MLevel.DEBUG.log(message,t)
  inline def fine( inline message : =>String )                          : Unit = MLevel.FINE.log(message)
  inline def fine( inline message : =>String, inline t : Throwable )    : Unit = MLevel.FINE.log(message,t)
  inline def finer( inline message : =>String )                         : Unit = MLevel.FINER.log(message)
  inline def finer( inline message : =>String, inline t : Throwable )   : Unit = MLevel.FINER.log(message,t)
  inline def finest( inline message : =>String )                        : Unit = MLevel.FINEST.log(message)
  inline def finest( inline message : =>String, inline t : Throwable )  : Unit = MLevel.FINEST.log(message,t)
  inline def info( inline message : =>String )                          : Unit = MLevel.INFO.log(message)
  inline def info( inline message : =>String, inline t : Throwable )    : Unit = MLevel.INFO.log(message,t)
  inline def severe( inline message : =>String )                        : Unit = MLevel.SEVERE.log(message)
  inline def severe( inline message : =>String, inline t : Throwable )  : Unit = MLevel.SEVERE.log(message,t)
  inline def trace( inline message : =>String )                         : Unit = MLevel.TRACE.log(message)
  inline def trace( inline message : =>String, inline t : Throwable )   : Unit = MLevel.TRACE.log(message,t)
  inline def warning( inline message : =>String )                       : Unit = MLevel.WARNING.log(message)
  inline def warning( inline message : =>String, inline t : Throwable ) : Unit = MLevel.WARNING.log(message,t)
end LogAdapter

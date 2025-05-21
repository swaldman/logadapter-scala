package logadapter

trait LogAdapter:
  inline def config( inline message : =>String )                        : Unit
  inline def config( inline message : =>String, inline t : Throwable )  : Unit
  inline def debug( inline message : =>String )                         : Unit
  inline def debug( inline message : =>String, inline t : Throwable )   : Unit
  inline def fine( inline message : =>String )                          : Unit
  inline def fine( inline message : =>String, inline t : Throwable )    : Unit
  inline def finer( inline message : =>String )                         : Unit
  inline def finer( inline message : =>String, inline t : Throwable )   : Unit
  inline def finest( inline message : =>String )                        : Unit
  inline def finest( inline message : =>String, inline t : Throwable )  : Unit
  inline def info( inline message : =>String )                          : Unit
  inline def info( inline message : =>String, inline t : Throwable )    : Unit
  inline def severe( inline message : =>String )                        : Unit
  inline def severe( inline message : =>String, inline t : Throwable )  : Unit
  inline def trace( inline message : =>String )                         : Unit
  inline def trace( inline message : =>String, inline t : Throwable )   : Unit
  inline def warning( inline message : =>String )                       : Unit
  inline def warning( inline message : =>String, inline t : Throwable ) : Unit


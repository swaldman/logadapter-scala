package logadapter

trait LogAdapter:
  inline def config( message : =>String )                 : Unit
  inline def config( message : =>String, t : Throwable )  : Unit
  inline def debug( message : =>String )                  : Unit
  inline def debug( message : =>String, t : Throwable )   : Unit
  inline def error( message : =>String )                  : Unit
  inline def error( message : =>String, t : Throwable )   : Unit
  inline def fatal( message : =>String )                  : Unit
  inline def fatal( message : =>String, t : Throwable )   : Unit
  inline def fine( message : =>String )                   : Unit
  inline def fine( message : =>String, t : Throwable )    : Unit
  inline def finer( message : =>String )                  : Unit
  inline def finer( message : =>String, t : Throwable )   : Unit
  inline def finest( message : =>String )                 : Unit
  inline def finest( message : =>String, t : Throwable )  : Unit
  inline def info( message : =>String )                   : Unit
  inline def info( message : =>String, t : Throwable )    : Unit
  inline def severe( message : =>String )                 : Unit
  inline def severe( message : =>String, t : Throwable )  : Unit
  inline def trace( message : =>String )                  : Unit
  inline def trace( message : =>String, t : Throwable )   : Unit
  inline def warning( message : =>String )                : Unit
  inline def warning( message : =>String, t : Throwable ) : Unit


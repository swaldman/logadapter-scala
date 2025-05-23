package logadapter.zio

import zio.{UIO,ZIO}

class ZApi[T <: logadapter.LogAdapter]( val inner : logadapter.Api[T] ):
  import inner.*
  export inner.*

  extension ( level : logadapter.Level )
    inline def zlog( message : =>String )( using la : T ) : UIO[Unit]                    = ZIO.succeed( level.log( message ) )
    inline def zlog( message : =>String, error : Throwable )( using la : T ) : UIO[Unit] = ZIO.succeed( level.log( message, error ) )

  extension[R,E,A] ( effect : ZIO[R,E,A] )( using la : T )
    inline def zlogError( level : logadapter.Level, what : => String = "Effect" ) : ZIO[R,E,A] =
      effect
        .tapError { e =>
          e match
            case t : Throwable => level.zlog(s"${what} failed within error channel, on Throwable.", t)
            case other         => level.zlog(s"${what} failed within error channel. Failure: " + other)
        }
    inline def zlogDefect( level : logadapter.Level, what : => String = "Effect" ) : ZIO[R,E,A] =
      effect
        .tapDefect { cause =>
          level.zlog(s"${what} failed outside of error channel with cause: " + cause)
        }
    inline def zlogErrorDefect( level : logadapter.Level, what : => String = "Effect" ) : ZIO[R,E,A] =
      effect
        .zlogError( level, what )
        .zlogDefect( level, what )

package com.mchange.sc.logadapter.mlog

object Api extends com.mchange.sc.logadapter.Api[LogAdapter]:
  inline def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
  inline def logAdapterFor( clz : Class[_] )      : LogAdapter = logAdapterFor(classNameToLoggerName(clz.getName))
  inline def logAdapterFor( obj : Any )           : LogAdapter = logAdapterFor(obj.getClass)

  inline def logAdapterByFilename( using fn : sourcecode.FileName ) : LogAdapter = logAdapterFor( fn.value )

  trait SelfLogging:
    given adapter : LogAdapter = logAdapterFor(this)
end Api

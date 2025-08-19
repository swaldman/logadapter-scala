package logadapter.slf4j

object Api extends logadapter.Api[LogAdapter]:
  def logAdapterFor( loggerName : String ) : LogAdapter = new LogAdapter( loggerName )
end Api

package logadapter.stderr

import java.time.*, format.DateTimeFormatter

object LogAdapter:
  val TimestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'@'HH':'mm':'ss")

class LogAdapter( loggerName : String ) extends logadapter.LogAdapter:
  
  private inline def log( inline tag : String, inline message : =>String ) : Unit =
    val timestamp = LogAdapter.TimestampFormatter.format( LocalDateTime.now )
    System.err.println( s"${timestamp} ${tag} [${loggerName}] ${message}" )
  private inline def log( inline tag : String, inline message : =>String, inline t : Throwable ) : Unit =
    log( tag, message )
    System.err.print("  -> ")
    t.printStackTrace(System.err)

  inline def config  ( inline message : =>String )                       : Unit = log("CONFIG",  message)
  inline def config  ( inline message : =>String, inline t : Throwable ) : Unit = log("CONFIG",  message, t)
  inline def debug   ( inline message : =>String )                       : Unit = log("DEBUG",   message)
  inline def debug   ( inline message : =>String, inline t : Throwable ) : Unit = log("DEBUG",   message, t)
  inline def fine    ( inline message : =>String )                       : Unit = log("FINE",    message)
  inline def fine    ( inline message : =>String, inline t : Throwable ) : Unit = log("FINE",    message, t)
  inline def finer   ( inline message : =>String )                       : Unit = log("FINER",   message)
  inline def finer   ( inline message : =>String, inline t : Throwable ) : Unit = log("FINER",   message, t)
  inline def finest  ( inline message : =>String )                       : Unit = log("FINEST",  message)
  inline def finest  ( inline message : =>String, inline t : Throwable ) : Unit = log("FINEST",  message, t)
  inline def info    ( inline message : =>String )                       : Unit = log("INFO",    message)
  inline def info    ( inline message : =>String, inline t : Throwable ) : Unit = log("INFO",    message, t)
  inline def severe  ( inline message : =>String )                       : Unit = log("SEVERE",  message)
  inline def severe  ( inline message : =>String, inline t : Throwable ) : Unit = log("SEVERE",  message, t)
  inline def trace   ( inline message : =>String )                       : Unit = log("TRACE",   message)
  inline def trace   ( inline message : =>String, inline t : Throwable ) : Unit = log("TRACE",   message, t)
  inline def warning ( inline message : =>String )                       : Unit = log("WARNING", message)
  inline def warning ( inline message : =>String, inline t : Throwable ) : Unit = log("WARNING", message, t)
end LogAdapter


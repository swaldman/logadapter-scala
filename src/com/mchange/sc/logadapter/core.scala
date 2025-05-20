package com.mchange.sc.logadapter

def classNameToLoggerName( clzName : String ) : String = clzName.reverse.dropWhile( _ == '$' ).map( c => if ( c == '$' ) '.' else c ).reverse


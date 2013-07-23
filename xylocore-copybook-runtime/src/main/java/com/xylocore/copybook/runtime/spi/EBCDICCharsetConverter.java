//
//   Copyright 2013 The Palantir Corporation
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//


package com.xylocore.copybook.runtime.spi;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;


public class EBCDICCharsetConverter
{
    public static void main( String[] args )
    {
        try
        {
            ByteBuffer myByteBuffer = ByteBuffer.allocate( 256 );
            CharBuffer myCharBuffer = CharBuffer.allocate( 256 );
            
            for ( int i = 0 ; i < 256 ; i++ )
            {
                myByteBuffer.put( i, (byte) i );
                myCharBuffer.put( i, (char) i );
            }
            
            myByteBuffer.rewind();
            myCharBuffer.rewind();

//            Charset myAsciiCharset  = Charset.forName( "ISO-8859-1" );
            Charset myEbcdicCharset = Charset.forName( "IBM037" );
            
            ByteBuffer myToEbcdicBuffer = myEbcdicCharset.encode( myCharBuffer );
            CharBuffer myToAsciiBuffer  = myEbcdicCharset.decode( myByteBuffer );
            
            myByteBuffer.rewind();
            myCharBuffer.rewind();
            
            Set<Byte> myEbcdicSet = new HashSet<Byte>();
            
            System.out.println( "ASCII -> EBCDIC" );
            for ( int i = 0 ; i < 256 ; i++ )
            {
                char myAsciiChar  = myCharBuffer    .get( i );
                byte myEbcdicByte = myToEbcdicBuffer.get( i );
                
                boolean myDup = ! myEbcdicSet.add( new Byte( myEbcdicByte ) );
                
                System.out.println( "   " + hex( myAsciiChar ) + " - " + hex( myEbcdicByte ) + "  " + (myDup ? "*" : "") );
            }
            System.out.println();
            
            System.out.println( "EBCDIC -> ASCII" );
            for ( int i = 0 ; i < 256 ; i++ )
            {
                byte myEbcdicByte = myByteBuffer   .get( i );
                char myAsciiChar  = myToAsciiBuffer.get( i );
                
                System.out.println( "   " + hex( myEbcdicByte ) + " - " + hex( myAsciiChar ) );
            }
            System.out.println();
        }
        catch ( Exception myException )
        {
            myException.printStackTrace();
        }
    }
    
    static String hex( byte aValue )
    {
        return hex( aValue & 0xff );
    }
    
    static String hex( char aValue )
    {
        return hex( aValue & 0xff );
    }
    
    static String hex( int aValue )
    {
        String myString = "00" + Integer.toString( aValue, 16 );
        return myString.substring( myString.length()-2 );
    }
}

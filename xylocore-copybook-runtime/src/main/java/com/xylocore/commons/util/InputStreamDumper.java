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


package com.xylocore.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class InputStreamDumper
{
    //
    // Members
    //
    
    
    private char[] buffer;

    
    
    
    //
    // Class implementation
    //
    

    /**
     * Default constructor.
     */
    public InputStreamDumper()
    {
        initializeLogMessageBuffer();
    }

    
    /**
     * FILLIN
     * 
     * @param       aData
     * @param       aWriter
     */
    public void dump( byte[]   aData,
                      Writer   aWriter )
            throws IOException
    {
        if ( aData == null )
        {
            throw new IllegalArgumentException( "aData" );
        }
        if ( aWriter == null )
        {
            throw new IllegalArgumentException( "aWriter" );
        }
        
        int myDataLength = aData.length;
        
        for ( int myOffset = 0 ; myOffset < myDataLength ; myOffset += 16 )
        {
            int myValue = myOffset % 0x00010000;
            for ( int i = 0 ; i < 4 ; i++ )
            {
                buffer[3-i] = Character.forDigit( myValue % 16, 16 );
                myValue /= 16;
            }
            
            int myByteCount = Math.min( 16, myDataLength-myOffset );
            
            for ( int i = 0 ; i < myByteCount ; i++ )
            {
                myValue = aData[myOffset+i] & 0x000000ff;
                
                buffer[7+i*3] = Character.forDigit( myValue / 16, 16 );
                buffer[8+i*3] = Character.forDigit( myValue % 16, 16 );
                buffer[57+i]  = ( myValue >= 20 && myValue < 127 ) ? (char) myValue : '.';
            }
            
            for ( int i = myByteCount ; i < 16 ; i++ )
            {
                buffer[7+i*3] = ' ';
                buffer[8+i*3] = ' ';
                buffer[57+i]  = ' ';
            }
            
            aWriter.write( buffer );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInputStream
     * @param       aWriter
     * 
     * @exception   IOException
     */
    public void dump( InputStream   aInputStream,
                      Writer        aWriter       )
            throws IOException
    {
        if ( aInputStream == null )
        {
            throw new IllegalArgumentException( "aInputStream" );
        }
        if ( aWriter == null )
        {
            throw new IllegalArgumentException( "aWriter" );
        }
        
        ByteArrayOutputStream myOutputStream = new ByteArrayOutputStream();
        int                   myByte;
        
        while ( (myByte = aInputStream.read()) != -1 )
        {
            myOutputStream.write( myByte );
        }
        
        dump( myOutputStream.toByteArray(), aWriter );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aByteBuffer
     * @param       aWriter
     * 
     * @return
     */
    public void dump( ByteBuffer   aByteBuffer,
                      Writer       aWriter      )
            throws IOException
    {
        int    myPosition = aByteBuffer.position();
        int    mySize     = aByteBuffer.limit() - aByteBuffer.position();
        byte[] myData     = new byte[mySize];
        
        for ( int i = 0 ; i < mySize ; i++ )
        {
            myData[i] = aByteBuffer.get( myPosition + i );
        }
        
        dump( myData, aWriter );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aData
     * 
     * @return
     */
    public String dumpAsString( byte[] aData )
    {
        StringWriter myWriter = new StringWriter();
        
        try
        {
            dump( aData, myWriter );
        }
        catch ( IOException myIOException )
        {
            // Ignore - will not happen
        }
        
        return myWriter.toString();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInputStream
     * 
     * @return
     * 
     * @exception   IOException
     */
    public String dumpAsString( InputStream aInputStream )
            throws IOException
    {
        StringWriter myWriter = new StringWriter();
        dump( aInputStream, myWriter );
        return myWriter.toString();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aByteBuffer
     * 
     * @return
     */
    public String dumpAsString( ByteBuffer aByteBuffer )
    {
        int    myPosition = aByteBuffer.position();
        int    mySize     = aByteBuffer.limit() - aByteBuffer.position();
        byte[] myData     = new byte[mySize];
        
        for ( int i = 0 ; i < mySize ; i++ )
        {
            myData[i] = aByteBuffer.get( myPosition + i );
        }
        
        return dumpAsString( myData );
    }
    
    
    /**
     * FILLIN
     */
    private void initializeLogMessageBuffer()
    {
        buffer = new char[74];
        buffer[ 4] = ':';
        buffer[ 5] = ' ';
        buffer[ 6] = ' ';
        buffer[55] = ' ';
        buffer[56] = ' ';
        buffer[73] = '\n';
        
        for ( int i = 9 ; i < 55 ; i += 3 )
        {
            buffer[i] = ' ';
        }
    }
}

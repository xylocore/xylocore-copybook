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


package com.xylocore.copybook.runtime;

import java.nio.ByteBuffer;

import org.junit.Before;

import com.xylocore.commons.util.InputStreamDumper;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.spi.PlatformDataBehaviorFactory;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractPICMarshallerUnitTest
{
    //
    // Members
    //
    

    protected static final byte[]   filler          = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
    
    protected CopybookContext       context;
    protected ByteBuffer            byteBuffer;

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public AbstractPICMarshallerUnitTest()
    {
        context    = new CopybookContext();
        byteBuffer = ByteBuffer.allocate( 100 );
        
        context.setDataBehavior( PlatformDataBehaviorFactory.forName( "IBM-MAINFRAME" ) );
    }
    

    @Before
    public void setUp()
            throws Exception
    {
        context.setBuffer( byteBuffer );
        
        prepareByteBuffer( byteBuffer );
    }
    
    
    /**
     * FILLIN
     */
    protected void prepareByteBuffer()
    {
        prepareByteBuffer( byteBuffer );
    }
    

    /**
     * FILLIN
     * 
     * @param       aBuffer
     */
    protected void prepareByteBuffer( ByteBuffer aBuffer )
    {
        aBuffer.clear();
        aBuffer.limit( byteBuffer.capacity() );
        aBuffer.position( 0 );
        for ( int i = 0, ci = aBuffer.capacity() ; i < ci ; i++ )
        {
            aBuffer.put( i, filler[i % filler.length] );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aStart
     * @param       aEnd
     * @param       aHexArray
     * 
     * @return
     */
    protected boolean verifyBuffer( ByteBuffer   aBuffer,
                                    int          aStart,
                                    int          aEnd,
                                    String       aHexArray )
    {
        if ( aBuffer == null )
        {
            throw new IllegalArgumentException( "aBuffer" );
        }
        if ( aHexArray.length() % 2 != 0 )
        {
            throw new IllegalArgumentException( "hex array length must be a multiple of two" );
        }
        if ( aStart < 0 || aStart > aEnd || aEnd > aBuffer.capacity() )
        {
            throw new IllegalArgumentException( "0 <= aStart <= aEnd <= aBuffer.capacity()" );
        }
        
        for ( int i = 0, ci = aEnd-aStart; i < ci ; i++ )
        {
            byte myCompareByte = (byte) Integer.parseInt( aHexArray.substring( i*2, (i+1)*2 ), 16 );
            if ( myCompareByte != aBuffer.get( aStart + i ) )
            {
                return false;
            }
        }
        
        for ( int i = 0, ci = aBuffer.limit() ; i < ci ; i++ )
        {
            if ( i < aStart || i >= aEnd )
            {
                if ( aBuffer.get( i ) != filler[i % filler.length] )
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aTitle
     * @param       aBuffer
     */
    protected void dumpBuffer( String       aTitle,
                               ByteBuffer   aBuffer )
    {
        assert aBuffer != null;

        if ( aTitle != null && aTitle.length() != 0 )
        {
            System.out.println( aTitle );
        }
        
        byte[] myData = new byte[aBuffer.limit()];
        for ( int i = 0, ci = aBuffer.limit() ; i < ci ; i++ )
        {
            myData[i] = aBuffer.get( i );
        }
        
        InputStreamDumper myDumper = new InputStreamDumper();
        System.out.println( myDumper.dumpAsString( myData ) );
    }
}

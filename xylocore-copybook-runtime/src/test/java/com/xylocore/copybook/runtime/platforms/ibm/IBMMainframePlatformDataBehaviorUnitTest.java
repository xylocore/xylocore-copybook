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


package com.xylocore.copybook.runtime.platforms.ibm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.junit.Before;
import org.junit.Test;

import com.xylocore.commons.util.InputStreamDumper;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.platforms.ibm.IBMMainframePlatformDataBehavior;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class IBMMainframePlatformDataBehaviorUnitTest
{
    //
    // Members
    //
    
    
    private static final byte[]                 filler              = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
    private static final boolean                dumpBufferEnabled   = false;
    
    private CopybookContext                     context;
    private IBMMainframePlatformDataBehavior    dataBehavior;
    private ByteBuffer                          byteBuffer;
    private CharBuffer                          charBuffer;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public IBMMainframePlatformDataBehaviorUnitTest()
    {
        context      = new CopybookContext();
        dataBehavior = new IBMMainframePlatformDataBehavior();
        byteBuffer   = ByteBuffer.allocate( 100 );
        charBuffer   = CharBuffer.allocate( 100 );
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
     * 
     * @param       aBuffer
     */
    protected void prepareByteBuffer( ByteBuffer aBuffer )
    {
        byteBuffer.clear();
        byteBuffer.limit( byteBuffer.capacity() );
        byteBuffer.position( 0 );
        for ( int i = 0, ci = byteBuffer.capacity() ; i < ci ; i++ )
        {
            byteBuffer.put( i, filler[i % filler.length] );
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
        if ( dumpBufferEnabled )
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
    
    
    /**
     * FILLIN
     */
    @Test
    public void zeroPositionAndClipping()
    {
    }
    

    @Test
    public void displayChars()
    {
        String mySourceData   = "AbCdEf";
        String myConvertedHex = "C182C384C586";
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeDisplayChars( context, 0, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeDisplayChars( context, 0, 6, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeDisplayChars( context, 10, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 10, 16, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeDisplayChars( context, 10, 6, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
    }
    
    
    /**
     * FILLIN
     */
    @Test
    public void dbcsChars()
    {
        String mySourceData   = "AbCdEf";
        String myConvertedHex = "C182C384C586";
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeDisplayChars( context, 0, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeDisplayChars( context, 0, 6, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeDisplayChars( context, 10, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 10, 16, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeDisplayChars( context, 10, 6, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
    }
    
    
    /**
     * FILLIN
     */
    @Test
    public void nationalChars()
    {
        String mySourceData   = "AbCdEf";
        String myConvertedHex = "004100620043006400450066";
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNationalChars( context, 0, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 12, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeNationalChars( context, 0, 12, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNationalChars( context, 10, 6, mySourceData );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 10, 22, myConvertedHex ) );
        
        charBuffer.clear();
        dataBehavior.decodeNationalChars( context, 10, 12, charBuffer );
        charBuffer.flip();
        assertEquals( mySourceData, charBuffer.subSequence( 0, 6 ).toString() );
    }
    
    
    /**
     * FILLIN
     */
    @Test
    public void numericDisplay()
    {
        long myValue;
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, 1234, 6, SignType.None, null );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "F0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.None, null );
        assertEquals( 1234, myValue );

        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, -1234, 6, SignType.None, null );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "F0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.None, null );
        assertEquals( 1234, myValue );

        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, 1234, 6, SignType.NotSeparate, SignPosition.Trailing );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "F0F0F1F2F3C4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.NotSeparate, SignPosition.Trailing );
        assertEquals( 1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, -1234, 6, SignType.NotSeparate, SignPosition.Trailing );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "F0F0F1F2F3D4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.NotSeparate, SignPosition.Trailing );
        assertEquals( -1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, 1234, 6, SignType.NotSeparate, SignPosition.Leading );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "C0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.NotSeparate, SignPosition.Leading );
        assertEquals( 1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, -1234, 6, SignType.NotSeparate, SignPosition.Leading );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 6, "D0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.NotSeparate, SignPosition.Leading );
        assertEquals( -1234, myValue );

        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, 1234, 6, SignType.Separate, SignPosition.Trailing );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 7, "F0F0F1F2F3F44E" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.Separate, SignPosition.Trailing );
        assertEquals( 1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, -1234, 6, SignType.Separate, SignPosition.Trailing );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 7, "F0F0F1F2F3F460" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.Separate, SignPosition.Trailing );
        assertEquals( -1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, 1234, 6, SignType.Separate, SignPosition.Leading );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 7, "4EF0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.Separate, SignPosition.Leading );
        assertEquals( 1234, myValue );
        
        
        prepareByteBuffer( byteBuffer );
        dumpBuffer( "After prepare", byteBuffer );
        dataBehavior.encodeNumericDisplay( context, 0, -1234, 6, SignType.Separate, SignPosition.Leading );
        dumpBuffer( "After encode", byteBuffer );
        assertTrue( verifyBuffer( byteBuffer, 0, 7, "60F0F0F1F2F3F4" ) );
        
        myValue = dataBehavior.decodeNumericDisplay( context, 0, 6, SignType.Separate, SignPosition.Leading );
        assertEquals( -1234, myValue );
    }
    
    
    /**
     * FILLIN
     */
    @Test
    public void comp3()
    {
        // Exact digits, no sign, positive value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, 123456, 6, false );
        assertTrue( verifyBuffer( byteBuffer, 0, 8, "01230123456FCDEF" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 6 ) );

        // Exact digits, no sign, negative value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, -123456, 6, false );
        assertTrue( verifyBuffer( byteBuffer, 0, 8, "01230123456FCDEF" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 6 ) );

        // Exact digits, sign, positive value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, 123456, 6, true );
        assertTrue( verifyBuffer( byteBuffer, 0, 8, "01230123456CCDEF" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 6 ) );

        // Exact digits, sign, negative value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, -123456, 6, true );
        assertTrue( verifyBuffer( byteBuffer, 0, 8, "01230123456DCDEF" ) );
        assertEquals( -123456, dataBehavior.decodeComp3( context, 2, 6 ) );

        // Value smaller then digits, no sign, positive value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, 123456, 10, false );
        assertTrue( verifyBuffer( byteBuffer, 0, 10, "012300000123456F0123" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 10 ) );

        // Value smaller then digits, no sign, negative value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, -123456, 10, false );
        assertTrue( verifyBuffer( byteBuffer, 0, 10, "012300000123456F0123" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 10 ) );

        // Value smaller then digits, sign, positive value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, 123456, 10, true );
        assertTrue( verifyBuffer( byteBuffer, 0, 10, "012300000123456C0123" ) );
        assertEquals( 123456, dataBehavior.decodeComp3( context, 2, 10 ) );

        // Value smaller then digits, sign, negative value specified
        prepareByteBuffer( byteBuffer );
        dataBehavior.encodeComp3( context, 2, -123456, 10, true );
        assertTrue( verifyBuffer( byteBuffer, 0, 10, "012300000123456D0123" ) );
        assertEquals( -123456, dataBehavior.decodeComp3( context, 2, 10 ) );
    }
}

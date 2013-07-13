package com.xylocore.commons.data.copybook.runtime;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

import com.xylocore.commons.data.copybook.spi.PlatformDataBehaviorFactory;
import com.xylocore.commons.util.InputStreamDumper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractPICMarshallerUnitTest
    extends
        TestCase
{
    //
    // Members
    //
    

    protected static final byte[]   filler          = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
    
    protected CopybookContext       context;
    protected ByteBuffer    byteBuffer;

    
    
    
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
    
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp()
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

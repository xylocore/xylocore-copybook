/*
 * Context.java
 *
 * Copyright 2005 The Palantir Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xylocore.commons.data.copybook.runtime;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import com.xylocore.commons.data.copybook.spi.PlatformDataBehavior;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class CopybookContext
{
    //
    // Members
    //
    
    
    private PlatformDataBehavior    dataBehavior;
    private ByteBuffer              buffer;
    private int                     zeroPositionOffset;
    private int                     clipRegionStartOffset;
    private int                     clipRegionEndOffset;
    private CharBuffer              workBuffer;
    private int                     workBufferStartOffset;
    private int                     workBufferEndOffset;
    private StringBuilder           workStringBuffer;
    private CopybookError           standardError;
    private Object                  platformError;
    
    

    
    //
    // Class implementation
    //
    
    
    /**
     * Default constructor.
     */
    public CopybookContext()
    {
        standardError = CopybookError.None;
        platformError = null;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public PlatformDataBehavior getDataBehavior()
    {
        return dataBehavior;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String myString = null;
        
        if ( buffer != null )
        {
            byte[] myBytes = new byte[buffer.capacity()];
            buffer.get( myBytes );
            myString = new String( myBytes );
        }
        
        return myString;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataBehavior
     */
    public void setDataBehavior( PlatformDataBehavior aDataBehavior )
    {
        dataBehavior = aDataBehavior;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public ByteBuffer getBuffer()
    {
        return buffer;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     */
    public void setBuffer( ByteBuffer aBuffer )
    {
        setBuffer( aBuffer, 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aZeroPositionOffset
     */
    public void setBuffer( ByteBuffer   aBuffer,
                           int          aZeroPositionOffset )
    {
        if ( aBuffer == null )
        {
            throw new IllegalArgumentException( "aBuffer" );
        }
        if ( aZeroPositionOffset < 0 || aZeroPositionOffset > aBuffer.capacity() )
        {
            throw new IllegalArgumentException( "aZeroPositionOffset" );
        }
        
        buffer                = aBuffer;
        zeroPositionOffset    = aZeroPositionOffset;
        clipRegionStartOffset = 0;
        clipRegionEndOffset   = aBuffer.capacity() - aZeroPositionOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public void setBuffer( String aValue )
    {
        setBuffer( ByteBuffer.wrap( aValue.getBytes() ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMinimumCapacity
     * 
     * @return
     */
    public CharBuffer getWorkBuffer( int aMinimumCapacity )
    {
        if ( workBuffer == null || workBuffer.capacity() < aMinimumCapacity )
        {
            aMinimumCapacity = Math.max( 256, (aMinimumCapacity/256+1)*256 );
            
            workBuffer = CharBuffer.allocate( aMinimumCapacity );
        }
        else
        {
            workBuffer.clear();
        }
        
        return workBuffer;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getWorkBufferStartOffset()
    {
        return workBufferStartOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aStartOffset
     */
    public void setWorkBufferStartOffset( int aStartOffset )
    {
        workBufferStartOffset = aStartOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getWorkBufferEndOffset()
    {
        return workBufferEndOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEndOffset
     */
    public void setWorkBufferEndOffset( int aEndOffset )
    {
        workBufferEndOffset = aEndOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aStartOffset
     * @param       aEndOffset
     */
    public void setWorkBufferOffsets( int   aStartOffset,
                                      int   aEndOffset    )
    {
        workBufferStartOffset = aStartOffset;
        workBufferEndOffset   = aEndOffset;
    }
    
    
    /**
     * FILLIN
     */
    public void clearWorkBufferOffsets()
    {
        setWorkBufferOffsets( -1, -1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isWorkBufferOffsetsSet()
    {
        return ( workBufferStartOffset != -1 && workBufferEndOffset != -1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMinimumCapacity
     * 
     * @return
     */
    public StringBuilder getWorkStringBuilder( int aMinimumCapacity )
    {
        aMinimumCapacity = Math.max( 256, (aMinimumCapacity/256+1)*2 );
        
        if ( workStringBuffer == null )
        {
            workStringBuffer = new StringBuilder( aMinimumCapacity );
        }
        else
        {
            workStringBuffer.ensureCapacity( aMinimumCapacity );
            workStringBuffer.setLength( 0 );
        }
        
        return workStringBuffer;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getZeroPositionOffset()
    {
        return zeroPositionOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aZeroPositionOffset
     */
    public void setZeroPositionOffset( int aZeroPositionOffset )
    {
        if ( buffer == null )
        {
            throw new IllegalStateException( "the context does not have a buffer associated with it" );
        }
        if ( aZeroPositionOffset < 0 || aZeroPositionOffset > buffer.capacity() )
        {
            throw new IllegalArgumentException( "aZeroPositionOffset" );
        }
        
        zeroPositionOffset  = aZeroPositionOffset;
        clipRegionEndOffset = Math.min( clipRegionEndOffset, buffer.capacity() - aZeroPositionOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getClipRegionStartOffset()
    {
        return clipRegionStartOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getClipRegionEndOffset()
    {
        return clipRegionEndOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aStartOffset
     * @param       aEndOffset
     */
    public void setClipRegion( int   aStartOffset,
                               int   aEndOffset    )
    {
        if
        (
            aStartOffset < 0                                      ||
            aEndOffset   < 0                                      ||
            aStartOffset > aEndOffset                             ||
            aEndOffset   > buffer.capacity() - zeroPositionOffset
        )
        {
            throw new IllegalArgumentException( "0 <= aStartOffset <= aEndOffset <= buffer.capacity() - zeroPositionOffset" );
        }
        
        clipRegionStartOffset = aStartOffset;
        clipRegionEndOffset   = aEndOffset;
    }
    

    /**
     * FILLIN
     * 
     * @param       aOffset
     * 
     * @return
     */
    public byte getByte( int aOffset )
    {
        validateOffset( aOffset );
        
        if ( aOffset < clipRegionStartOffset || aOffset >= clipRegionEndOffset )
        {
            // TODO: throw appropriate exception
            throw new RuntimeException();
        }
        
        return buffer.get( zeroPositionOffset + aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDestinationBuffer
     * @param       aOffset
     * @param       aLength
     */
    public void getBytes( ByteBuffer   aDestinationBuffer,
                          int          aOffset,
                          int          aLength             )
    {
        assert aDestinationBuffer != null;
        assert aOffset            >= 0;
        assert aLength            >= 0;
        assert aLength            <= aDestinationBuffer.remaining();
        
        validateOffsetRange( aOffset, aOffset + aLength );
        
        if ( aOffset < clipRegionStartOffset || aOffset + aLength > clipRegionEndOffset )
        {
            // TODO: throw appropriate exception
            throw new RuntimeException();
        }
        
        int myStartOffset = zeroPositionOffset + aOffset;
        for ( int i = 0 ; i < aLength ; i++ )
        {
            aDestinationBuffer.put( buffer.get( myStartOffset + i ) );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aValue
     */
    public void putByte( int    aOffset,
                         byte   aValue   )
    {
        validateOffset( aOffset );
        
        if ( aOffset < clipRegionStartOffset || aOffset >= clipRegionEndOffset )
        {
            // TODO: throw appropriate exception
            throw new RuntimeException();
        }
        
        buffer.put( zeroPositionOffset + aOffset, aValue );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSourceBuffer
     * @param       aOffset
     * @param       aLength
     */
    public void putBytes( ByteBuffer   aSourceBuffer,
                          int          aOffset,
                          int          aLength        )
    {
        assert aSourceBuffer != null;
        assert aOffset       >= 0;
        assert aLength       >= 0;
        assert aLength       <= aSourceBuffer.remaining();
        
        validateOffsetRange( aOffset, aOffset + aLength );
        
        if ( aOffset < clipRegionStartOffset || aOffset + aLength > clipRegionEndOffset )
        {
            // TODO: throw appropriate exception
            throw new RuntimeException();
        }
        
        int myStartOffset = zeroPositionOffset + aOffset;
        for ( int i = 0 ; i < aLength ; i++ )
        {
            buffer.put( myStartOffset + i, aSourceBuffer.get() );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aStart
     * @param       aEnd
     * @param       aTargetData
     */
    public void decode( CharsetDecoder   aDecoder,
                        int              aStart,
                        int              aEnd,
                        CharBuffer       aTargetData )
    {
        assert aDecoder    != null;
        assert aTargetData != null;
        assert aStart >= 0 && aStart <= aEnd && aEnd <= buffer.limit()-zeroPositionOffset;
        
        ByteBuffer myByteBuffer  = buffer;
        int        myOldPosition = myByteBuffer.position();
        int        myOldLimit    = myByteBuffer.limit();
        int        myRealStart   = aStart + zeroPositionOffset;
        int        myRealEnd     = aEnd + zeroPositionOffset;
        
        myByteBuffer.position( myRealStart );
        myByteBuffer.limit   ( myRealEnd   );
        
        try
        {
            aDecoder.reset();
            aDecoder.decode( myByteBuffer, aTargetData, true );
        }
        finally
        {
            myByteBuffer.limit   ( myOldLimit    );
            myByteBuffer.position( myOldPosition );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aSourceData
     */
    public void encode( CharsetEncoder   aEncoder,
                        int              aBytesPerCharacter,
                        int              aOffset,
                        CharBuffer       aSourceData         )
    {
        assert aEncoder    != null;
        assert aSourceData != null;
        assert aOffset     >= 0 && aOffset < buffer.limit()-zeroPositionOffset;
        
        ByteBuffer myByteBuffer  = buffer;
        int        myOldPosition = myByteBuffer.position();
        int        myOldLimit    = myByteBuffer.limit();
        int        myRealOffset  = aOffset + zeroPositionOffset;
        
        myByteBuffer.position( myRealOffset                                              );
        myByteBuffer.limit   ( myRealOffset + aBytesPerCharacter*aSourceData.remaining() );
        
        try
        {
            aEncoder.reset();
            aEncoder.encode( aSourceData, myByteBuffer, true );
        }
        finally
        {
            myByteBuffer.limit   ( myOldLimit    );
            myByteBuffer.position( myOldPosition );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     */
    public void validateOffset( int aOffset )
    {
        if ( zeroPositionOffset + aOffset >= buffer.limit() )
        {
            // TODO: throw appropriate exception
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aStartOffset
     * @param       aEndOffset
     */
    public void validateOffsetRange( int   aStartOffset,
                                     int   aEndOffset    )
    {
        assert aStartOffset >= 0 && aStartOffset <= aEndOffset;
        
        int myLimit = buffer.limit();
        if ( zeroPositionOffset + aStartOffset >= myLimit || zeroPositionOffset + aEndOffset > myLimit )
        {
            // TODO: throw appropriate exception
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isError()
    {
        return ( standardError != null && standardError != CopybookError.None );
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public CopybookError getStandardError()
    {
        return standardError;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Object getPlatformError()
    {
        return platformError;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aStandardError
     * @param       aPlatformError
     */
    public void setError( CopybookError   aStandardError,
                          Object          aPlatformError )
    {
        standardError = aStandardError;
        platformError = aPlatformError;
    }
    
    
    /**
     * FILLIN
     */
    public void throwExceptionForError()
    {
        if ( standardError != CopybookError.None )
        {
            throw new CopybookException( standardError, platformError );
        }
    }
    
    
    /**
     * FILLIN
     */
    public void clearError()
    {
        standardError = CopybookError.None;
        platformError = null;
    }
}

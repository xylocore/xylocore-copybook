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

import java.nio.CharBuffer;
import java.text.DateFormat;
import java.util.Date;

import com.xylocore.copybook.runtime.CopybookContext;



/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class AbstractPlatformDataBehavior
    implements
        PlatformDataBehavior
{
    //
    // Members
    //
    
    
    private static final long[] defaultBinaryMaxValues  = { 0L,
                                                            9L,
                                                            99L,
                                                            999L,
                                                            9999L,
                                                            99999L,
                                                            999999L,
                                                            9999999L,
                                                            99999999L,
                                                            999999999L,
                                                            9999999999L,
                                                            99999999999L,
                                                            999999999999L,
                                                            9999999999999L,
                                                            99999999999999L,
                                                            999999999999999L,
                                                            9999999999999999L,
                                                            99999999999999999L,
                                                            999999999999999999L };
    
    private String  behaviorName;
    private long[]  binaryMaxValues;

    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aBehaviorName
     */
    public AbstractPlatformDataBehavior( String aBehaviorName )
    {
        if ( aBehaviorName == null || aBehaviorName.length() == 0 )
        {
            throw new IllegalArgumentException( "aBehaviorName" );
        }
        
        behaviorName    = aBehaviorName;
        binaryMaxValues = getBinaryMaxValues();
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected long[] getBinaryMaxValues()
    {
        return defaultBinaryMaxValues;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aSourceData
     */
    protected void append( CharBuffer     aBuffer,
                           CharSequence   aSourceData )
    {
        assert aBuffer     != null;
        assert aSourceData != null;
        
        append( aBuffer, aSourceData, 0, aSourceData.length() );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aSourceData
     * @param       aOffset
     * @param       aLength
     */
    protected void append( CharBuffer     aBuffer,
                           CharSequence   aSourceData,
                           int            aOffset,
                           int            aLength      )
    {
        assert aBuffer           != null;
        assert aSourceData       != null;
        assert aOffset           >= 0;
        assert aLength           >= 0;
        assert aLength           <= aSourceData.length();
        assert aOffset + aLength <= aSourceData.length();
        
        int myEnd = aOffset + aLength;
        while ( aOffset < myEnd )
        {
            aBuffer.put( aSourceData.charAt( aOffset++ ) );
        }
    }
    
    
    
    
    //
    // PlatformDataBehavior interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#getBehaviorName()
     */
    public String getBehaviorName()
    {
        return behaviorName;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.common.data.copybook.spi.PlatformDataBehavior#decodeBinary(com.xylocore.common.data.copybook.spi.Context, int, int, boolean)
     */
    public long decodeBinary( CopybookContext   aContext,
                              int       aOffset,
                              int       aDigits,
                              boolean   aIsSigned )
    {
        long myValue;
        
        switch ( aDigits )
        {
            case 1:
            case 2:
            case 3:
            case 4:
                
                myValue = decode2ByteComp5( aContext, aOffset );
                break;
                
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                
                myValue = decode4ByteComp5( aContext, aOffset );
                break;
                
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                
                myValue = decode8ByteComp5( aContext, aOffset );
                break;
                
            default:
                
                throw new IllegalStateException( "illegal number of digits specified: " + aDigits );
        }
        
        long myMaxValue = binaryMaxValues[aDigits];
        
        if ( aIsSigned )
        {
            if ( myValue < -myMaxValue || myValue > myMaxValue )
            {
                // TODO: implement error handling
            }
        }
        else
        {
            if ( myValue < 0 || myValue > myMaxValue )
            {
                // TODO: implement error handling
            }
        }
        
        return myValue;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.common.data.copybook.spi.PlatformDataBehavior#encodeBinary(com.xylocore.common.data.copybook.spi.Context, int, long, int, boolean)
     */
    public void encodeBinary( CopybookContext   aContext,
                              int       aOffset,
                              long      aValue,
                              int       aDigits,
                              boolean   aIsSigned )
    {
        long myMaxValue = binaryMaxValues[aDigits];
        
        if ( aIsSigned )
        {
            if ( aValue < -myMaxValue || aValue > myMaxValue )
            {
                // TODO: implement error handling
            }
        }
        else
        {
            if ( aValue < 0 || aValue > myMaxValue )
            {
                // TODO: implement error handling
            }
        }
        
        switch ( aDigits )
        {
            case 1:
            case 2:
            case 3:
            case 4:
                
                encode2ByteComp5( aContext, aOffset, aValue );
                break;
                
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                
                encode4ByteComp5( aContext, aOffset, aValue );
                break;
                
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                
                encode8ByteComp5( aContext, aOffset, aValue );
                break;
                
            default:
                
                throw new IllegalStateException( "illegal number of digits specified: " + aDigits );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeDate(com.xylocore.commons.data.copybook.spi.Context, int, java.util.Date, java.text.DateFormat)
     */
    public void encodeDate( CopybookContext      aContext,
                            int          aOffset,
                            Date         aValue,
                            DateFormat   aFormat   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not implemented yet" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeDate(com.xylocore.commons.data.copybook.spi.Context, int, java.text.DateFormat)
     */
    public Date decodeDate( CopybookContext      aContext,
                            int          aOffset,
                            DateFormat   aFormat   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not implemented yet" );
    }
}

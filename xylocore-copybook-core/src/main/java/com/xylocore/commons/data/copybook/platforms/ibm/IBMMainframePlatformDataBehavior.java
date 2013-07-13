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


package com.xylocore.commons.data.copybook.platforms.ibm;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.spi.AbstractCharsetBasedPlatformDataBehavior;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class IBMMainframePlatformDataBehavior
    extends
        AbstractCharsetBasedPlatformDataBehavior
{
    //
    // Members
    //
    

    private static final byte SIGN_NOT_SEPARATE_MASK    = (byte) 0xF0;
    private static final byte SIGN_NOT_SEPARATE_PLUS    = (byte) 0xC0;
    private static final byte SIGN_NOT_SEPARATE_MINUS   = (byte) 0xD0;
    private static final byte SIGN_SEPARATE_PLUS        = (byte) 0x4E;
    private static final byte SIGN_SEPARATE_MINUS       = (byte) 0x60;

    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     */
    public IBMMainframePlatformDataBehavior()
    {
        // TODO: use correct DBCS character set
        super( "IBM-MAINFRAME", "IBM037", "US-ASCII", "UTF-16BE" );
//        super( "IBM-MAINFRAME", "IBM037", "Cp838", "UTF-16BE" );
//        super( "IBM-MAINFRAME", "IBM037", "IBM424", "UTF-16BE" );
    }




    //
    // PlatformDataBehavior interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#isDisplayCharsBlank(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int)
     */
    public boolean isDisplayCharsBlank( CopybookContext   aContext,
                                        int               aOffset,
                                        int               aLength   )
    {
        for ( int i = 0 ; i < aLength ; i++ )
        {
            if ( aContext.getByte( aOffset++ ) != (byte) 0x40 )
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeNumericDisplay(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
    public long decodeNumericDisplay( CopybookContext   aContext,
                                      int               aOffset,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition )
    {
        long mySign;
        
        if ( aSignType == SignType.None )
        {
            mySign = 1;
        }
        else
        {
            byte mySignByte;
            
            if ( aSignType == SignType.NotSeparate )
            {
                if ( aSignPosition == SignPosition.Leading )
                {
                    mySignByte = (byte) (aContext.getByte( aOffset ) & SIGN_NOT_SEPARATE_MASK);
                    
                }
                else
                {
                    assert aSignPosition == SignPosition.Trailing;
                    
                    mySignByte = (byte) (aContext.getByte( aOffset + aDigits - 1 ) & SIGN_NOT_SEPARATE_MASK);
                }
                
                mySign = ( mySignByte == SIGN_NOT_SEPARATE_PLUS ) ? 1 : -1;
            }
            else
            {
                assert aSignType == SignType.Separate;
                
                if ( aSignPosition == SignPosition.Leading )
                {
                    mySignByte = aContext.getByte( aOffset++ );
                }
                else
                {
                    assert aSignPosition == SignPosition.Trailing;
                    
                    mySignByte = aContext.getByte( aOffset + aDigits );
                }
                
                mySign = ( mySignByte == SIGN_SEPARATE_PLUS ) ? 1 : -1;
            }
        }
        
        long myValue = 0;
        for ( int myEnd = aOffset + aDigits ; aOffset < myEnd ; aOffset++ )
        {
            myValue = 10*myValue + (((long) aContext.getByte( aOffset )) & 0x0f);
        }
        
        return myValue*mySign;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeNumericDisplay(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
    public void encodeNumericDisplay( CopybookContext   aContext,
                                      int               aOffset,
                                      long              aValue,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition )
    {
        boolean myIsNegative = ( aValue < 0 );
        if ( myIsNegative )
        {
            aValue = -aValue;
        }
        
        int myDigitStart = aOffset;
        if ( aSignType == SignType.Separate && aSignPosition == SignPosition.Leading )
        {
            myDigitStart++;
        }
        
        for ( int i = aDigits ; i != 0 ; )
        {
            i--;
            
            aContext.putByte( myDigitStart+i, (byte) ((aValue % 10) | SIGN_NOT_SEPARATE_MASK) );
            aValue /= 10;
        }
        
        int mySignOffset = aOffset;
        
        if ( aSignType == SignType.Separate )
        {
            if ( aSignPosition == SignPosition.Trailing )
            {
                mySignOffset += aDigits;
            }
            
            aContext.putByte( mySignOffset, myIsNegative ? SIGN_SEPARATE_MINUS : SIGN_SEPARATE_PLUS );
        }
        else if ( aSignType == SignType.NotSeparate )
        {
            if ( aSignPosition == SignPosition.Trailing )
            {
                mySignOffset += aDigits - 1;
            }
            
            byte mySignByte = aContext.getByte( mySignOffset );
            mySignByte = (byte) (( mySignByte & 0x0f ) | ( myIsNegative ? SIGN_NOT_SEPARATE_MINUS : SIGN_NOT_SEPARATE_PLUS ));
            aContext.putByte( mySignOffset, mySignByte );
        }
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#isDbcsCharsBlank(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int)
     */
    public boolean isDbcsCharsBlank( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#isNationalCharsBlank(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int)
     */
    public boolean isNationalCharsBlank( CopybookContext   aContext,
                                         int               aOffset,
                                         int               aLength   )
    {
        for ( int i = 0, ci = 2*aLength ; i < ci ; i += 2 )
        {
            if
            (
                aContext.getByte( aOffset++ ) != (byte) 0x00 ||
                aContext.getByte( aOffset++ ) != (byte) 0x20
            )
            {
                return false;
            }
        }
        
        return true;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeNumericNational(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
    public long decodeNumericNational( CopybookContext   aContext,
                                       int               aOffset,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition )
    {
        // TODO: implement
        return 0;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeNumericNational(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
    public void encodeNumericNational( CopybookContext   aContext,
                                       int               aOffset,
                                       long              aValue,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition )
    {
        // TODO: implement
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeComp1(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public float decodeComp1( CopybookContext   aContext,
                              int               aOffset   )
    {
        // TODO: implement z/Series hexidecimal floating point conversion
        throw new UnsupportedOperationException( "COMP-1 is currently not supported" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeComp1(com.xylocore.commons.data.copybook.spi.CopybookContext, int, float)
     */
    public void encodeComp1( CopybookContext   aContext,
                             int               aOffset,
                             float             aValue    )
    {
        // TODO: implement z/Series hexidecimal floating point conversion
        throw new UnsupportedOperationException( "COMP-1 is currently not supported" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeComp2(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public double decodeComp2( CopybookContext   aContext,
                               int               aOffset   )
    {
        // TODO: implement z/Series hexidecimal floating point conversion
        throw new UnsupportedOperationException( "COMP-2 is currently not supported" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeComp2(com.xylocore.commons.data.copybook.spi.CopybookContext, int, double)
     */
    public void encodeComp2( CopybookContext   aContext,
                             int               aOffset,
                             double            aValue    )
    {
        // TODO: implement z/Series hexidecimal floating point conversion
        throw new UnsupportedOperationException( "COMP-2 is currently not supported" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeComp3(com.xylocore.commons.data.copybook.spi.CopybookContext, int, int)
     */
    public long decodeComp3( CopybookContext   aContext,
                             int               aOffset,
                             int               aDigits   )
    {
        long    myValue       = 0;
        boolean myIsHighDigit = ( aDigits % 2 == 1 );
        int     myPosition    = 0;
        int     myByte        = aContext.getByte( aOffset + myPosition ) & 0x000000ff;
        int     myDigit;
        
        while ( aDigits != 0 )
        {
            if ( myIsHighDigit )
            {
                myDigit = myByte / 16;
            }
            else
            {
                myDigit = myByte & 0x0f;
                
                myPosition++;
                myByte = aContext.getByte( aOffset + myPosition ) & 0x000000ff;
            }
            
            if ( myDigit > 9 )
            {
                // TODO: throw appropriate exception
                throw new RuntimeException();
            }
            
            myValue       = myValue * 10 + myDigit;
            myIsHighDigit = ! myIsHighDigit;

            aDigits--;
        }
        
        int mySign = myByte & 0x0f;
        if ( mySign == 0x0d )
        {
            myValue = -myValue;
        }
        
        return myValue;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeComp3(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long, int, boolean)
     */
    public void encodeComp3( CopybookContext   aContext,
                             int               aOffset,
                             long              aValue,
                             int               aDigits,
                             boolean           aIsSigned )
    {
        int mySize = (aDigits + 2) / 2 - 1;
        
        int mySign;
        if ( aIsSigned )
        {
            if ( aValue < 0 )
            {
                aValue = -aValue;
                mySign = 0x0d;
            }
            else
            {
                mySign = 0x0c;
            }
        }
        else
        {
            mySign = 0x0f;
            if ( aValue < 0 )
            {
                aValue = -aValue;
            }
        }
        
        aContext.putByte( aOffset+mySize, (byte) ((aValue % 10) << 4 | mySign) );
        aValue /= 10;
        
        while ( mySize != 0 )
        {
            mySize--;

            long myLowDigit = aValue % 10;
            aValue /= 10;
            
            long myHighDigit = aValue % 10;
            aValue /= 10;
            
            aContext.putByte( aOffset+mySize, (byte) (myHighDigit << 4 | myLowDigit) );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decode2ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public long decode2ByteComp5( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return (aContext.getByte( aOffset   ) << 8) |
                aContext.getByte( aOffset+1 ); 
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encode2ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long)
     */
    public void encode2ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    )
    {
        if ( (aValue & 0xffffffffffff0000L) != 0 )
        {
            // TODO: implement error handling
        }
        
        aValue &= 0xffffL;
        
        aContext.putByte( aOffset,   (byte) (aValue >> 8)   );
        aContext.putByte( aOffset+1, (byte) (aValue & 0xff) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decode4ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public long decode4ByteComp5( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return (aContext.getByte( aOffset   ) << 24) |
               (aContext.getByte( aOffset+1 ) << 16) |
               (aContext.getByte( aOffset+2 ) <<  8) |
                aContext.getByte( aOffset+3 ); 
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encode4ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long)
     */
    public void encode4ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    )
    {
        if ( (aValue & 0xffffffff00000000L) != 0 )
        {
            // TODO: implement error handling
        }
        
        aValue &= 0xffffffffL;
        
        aContext.putByte( aOffset  , (byte) ((aValue >> 24) & 0xff) );
        aContext.putByte( aOffset+1, (byte) ((aValue >> 16) & 0xff) );
        aContext.putByte( aOffset+2, (byte) ((aValue >>  8) & 0xff) );
        aContext.putByte( aOffset+3, (byte) (aValue         & 0xff) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decode8ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public long decode8ByteComp5( CopybookContext   aContext,
                                  int               aOffset   )
    {
        return (aContext.getByte( aOffset   ) << 56) |
               (aContext.getByte( aOffset+1 ) << 48) |
               (aContext.getByte( aOffset+2 ) << 40) |
               (aContext.getByte( aOffset+3 ) << 32) |
               (aContext.getByte( aOffset+4 ) << 24) |
               (aContext.getByte( aOffset+5 ) << 16) |
               (aContext.getByte( aOffset+6 ) <<  8) |
                aContext.getByte( aOffset+7 );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encode8ByteComp5(com.xylocore.commons.data.copybook.spi.CopybookContext, int, long)
     */
    public void encode8ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    )
    {
        aContext.putByte( aOffset, (byte) ((aValue >> 56) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >> 48) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >> 40) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >> 32) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >> 24) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >> 16) & 0xff) );
        aContext.putByte( aOffset, (byte) ((aValue >>  8) & 0xff) );
        aContext.putByte( aOffset, (byte) (aValue         & 0xff) );
    }
}

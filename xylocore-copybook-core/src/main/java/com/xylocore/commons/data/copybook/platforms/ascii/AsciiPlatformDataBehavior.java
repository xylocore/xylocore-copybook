/*
 * AsciiPlatformDataBehavior.java
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

package com.xylocore.commons.data.copybook.platforms.ascii;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.spi.AbstractCharsetBasedPlatformDataBehavior;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AsciiPlatformDataBehavior
    extends
        AbstractCharsetBasedPlatformDataBehavior
{
    //
    // Members
    //
    

    private static final byte   SIGN_SEPARATE_PLUS          = (byte) '+';
    private static final byte   SIGN_SEPARATE_MINUS         = (byte) '-';
    private static final byte[] ENCODED_DIGITS_WITHOUT_SIGN = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private static final byte[] ENCODED_DIGITS_WITH_SIGN    = { '}', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R' };
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     */
    public AsciiPlatformDataBehavior()
    {
        super( "ASCII", "US-ASCII", "US-ASCII", "UTF-16BE" );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     * @param       aSign
     * 
     * @return
     */
    private byte getEncodedDigit( int   aValue,
                                  int   aSign   )
    {
        assert aValue >= 0 && aValue <= 9;
        
        byte[] myEncodingDigits = ( aSign < 0 ) ? ENCODED_DIGITS_WITH_SIGN : ENCODED_DIGITS_WITHOUT_SIGN;
        
        return myEncodingDigits[aValue];
    }
    
    
    /**
     * FILLIN
     *
     * @param       aSignByte
     * 
     * @return
     */
    private int getDecodedSign( byte aSignByte )
    {
        int myChar  = aSignByte & 0xff;
        int mySign;
        
        switch ( myChar )
        {
            case 'J':  case 'K':  case 'L':  case 'M':  case 'N':  case 'O':  case 'P':  case 'Q':  case 'R':  case '}':
                
                mySign = -1;
                break;
                
            case 'S':  case 'T':  case 'U':  case 'V':  case 'W':  case 'X':  case 'Y':  case 'Z':  case '[':  case '\\':
            case ']':  case '^':  case '_':  case '`':  case 'a':  case 'b':  case 'c':  case 'd':  case 'e':  case 'f':
            case 'g':  case 'h':  case 'i':  case 'j':  case 'k':  case 'l':  case 'm':  case 'n':  case 'o':  case 'p':
            case 'q':  case 'r':  case 's':  case 't':  case 'u':  case 'v':  case 'w':  case 'x':  case 'y':  case 'z':
            case '{':  case '|':
            default:
                
                mySign = 1;
                break;
        }
        
        return mySign;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aSignByte
     * 
     * @return
     */
    private int getDecodedDigitValue( byte      aSignByte,
                                      boolean   aAllowSign )
    {
        int myChar   = aSignByte & 0xff;
        int myValue;
        
        switch ( myChar )
        {
            case '0':  case '1':  case '2':  case '3':  case '4':  case '5':  case '6':  case '7':  case '8':  case '9':
                
                myValue = myChar - 0x30;
                break;
                
            case 'J':  case 'K':  case 'L':  case 'M':  case 'N':  case 'O':  case 'P':  case 'Q':  case 'R':
                
                myValue = ( aAllowSign ) ? myChar - 'I' : -1;
                break;
                
            case '}':
                
                myValue = ( aAllowSign ) ? 0 : -1;
                break;
                
            case ':':  case ';':  case '<':  case '=':  case '>':  case '?':  case '@':  case 'A':  case 'B':  case 'C':
            case 'D':  case 'E':  case 'F':  case 'G':  case 'H':  case 'I':  case 'S':  case 'T':  case 'U':  case 'V':
            case 'W':  case 'X':  case 'Y':  case 'Z':  case '[':  case '\\': case ']':  case '^':  case '_':  case '`':
            case 'a':  case 'b':  case 'c':  case 'd':  case 'e':  case 'f':  case 'g':  case 'h':  case 'i':  case 'j':
            case 'k':  case 'l':  case 'm':  case 'n':  case 'o':  case 'p':  case 'q':  case 'r':  case 's':  case 't':
            case 'u':  case 'v':  case 'w':  case 'x':  case 'y':  case 'z':  case '{':  case '|':
            default:
                
                myValue = -1;
                break;
        }
        
        return myValue;
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
            if ( aContext.getByte( aOffset++ ) != (byte) 0x20 )
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
        long mySign = 1;
        
        if ( aSignType != SignType.None )
        {
            byte mySignByte;
            
            if ( aSignType != SignType.NotSeparate )
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
        
        int mySignOffset = -1;
        if ( aSignType == SignType.NotSeparate )
        {
            mySignOffset =
                    ( aSignPosition == SignPosition.Leading )
                            ? aOffset
                            : aOffset + aDigits - 1;
        }
        
        for ( int myEnd = aOffset + aDigits ; aOffset < myEnd ; aOffset++ )
        {
            byte myByte = aContext.getByte( aOffset );
            
            if ( aOffset == mySignOffset )
            {
                mySign = getDecodedSign( myByte );
            }
            
            int myDigit = getDecodedDigitValue( myByte, ( aOffset == mySignOffset ) );
            if ( myDigit < 0 )
            {
                // TODO: implement error handling
            }
            
            myValue = 10*myValue + myDigit;
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
        int mySign = ( aValue >= 0 ) ? 1 : -1;
        aValue *= mySign;
        
        int myDigitStart = aOffset;
        if ( aSignType == SignType.Separate && aSignPosition == SignPosition.Leading )
        {
            myDigitStart++;
        }
        
        int mySignOffset = -1;
        if ( aSignType == SignType.NotSeparate )
        {
            mySignOffset =
                    ( aSignPosition == SignPosition.Leading )
                            ? 0
                            : aDigits - 1;
        }
        
        for ( int i = aDigits ; i != 0 ; )
        {
            i--;
            
            byte myByte = getEncodedDigit( (int) (aValue % 10), ( i == mySignOffset ) ? mySign : 1 );
            
            aContext.putByte( myDigitStart+i, myByte );
            aValue /= 10;
        }
        
        if ( aSignType == SignType.Separate )
        {
            mySignOffset = aOffset;
            if ( aSignPosition == SignPosition.Trailing )
            {
                mySignOffset += aDigits;
            }
            
            aContext.putByte( mySignOffset, mySign < 0 ? SIGN_SEPARATE_MINUS : SIGN_SEPARATE_PLUS );
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
                aContext.isError()                           ||
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
        return Float.intBitsToFloat( (int) decode4ByteComp5( aContext, aOffset ) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeComp1(com.xylocore.commons.data.copybook.spi.CopybookContext, int, float)
     */
    public void encodeComp1( CopybookContext   aContext,
                             int               aOffset,
                             float             aValue    )
    {
        encode4ByteComp5( aContext, aOffset, Float.floatToRawIntBits( aValue ) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#decodeComp2(com.xylocore.commons.data.copybook.spi.CopybookContext, int)
     */
    public double decodeComp2( CopybookContext   aContext,
                               int               aOffset   )
    {
        return Double.longBitsToDouble( decode8ByteComp5( aContext, aOffset ) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehavior#encodeComp2(com.xylocore.commons.data.copybook.spi.CopybookContext, int, double)
     */
    public void encodeComp2( CopybookContext   aContext,
                             int               aOffset,
                             double            aValue    )
    {
        encode8ByteComp5( aContext, aOffset, Double.doubleToRawLongBits( aValue ) );
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

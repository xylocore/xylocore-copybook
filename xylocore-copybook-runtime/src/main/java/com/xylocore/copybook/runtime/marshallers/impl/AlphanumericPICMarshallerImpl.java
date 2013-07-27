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


package com.xylocore.copybook.runtime.marshallers.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.xylocore.copybook.runtime.AlphanumericPICFlags;
import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.CopybookError;
import com.xylocore.copybook.runtime.converters.Converter;
import com.xylocore.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphanumericPICMarshallerImpl
    extends
        AbstractPICMarshaller
    implements
        AlphanumericPICMarshaller
{
    //
    // Members
    //
    
    
    private static final AlphanumericPICMarshallerImpl instance = new AlphanumericPICMarshallerImpl();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private AlphanumericPICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static AlphanumericPICMarshallerImpl getInstance()
    {
        return instance;
    }

    
    @Override
    public void decodeStringIntoWorkBuffer( CopybookContext   aContext,
                                            int               aOffset,
                                            int               aSize,
                                            int               aFlags    )
    {
        CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
        int        myStart  = 0;
        int        myEnd    = aSize;
        
        // Decode the character data using the correct decoder technique
        if ( AlphanumericPICFlags.isDisplay( aFlags ) )
        {
            aContext.getDataBehavior().decodeDisplayChars( aContext, aOffset, aSize, myBuffer );
        }
        else if ( AlphanumericPICFlags.isNational( aFlags ) )
        {
            aContext.getDataBehavior().decodeNationalChars( aContext, aOffset, aSize, myBuffer );
        }
        else // AlphanumericPICFlags.isDbcs( aFlags )
        {
            aContext.getDataBehavior().decodeDbcsChars( aContext, aOffset, aSize, myBuffer );
        }
        
        if ( ! aContext.isError() )
        {
            if ( AlphanumericPICFlags.isRightJustify( aFlags ) )
            {
                if ( AlphanumericPICFlags.isTrimWhitespace( aFlags ) )
                {
                    while ( myStart < myEnd && myBuffer.get( myStart ) == ' ' )
                    {
                        myStart++;
                    }
                }
            }
            else
            {
                if ( AlphanumericPICFlags.isTrimWhitespace( aFlags ) )
                {
                    while ( myEnd > myStart && myBuffer.get( myEnd-1 ) == ' ' )
                    {
                        myEnd--;
                    }
                }
            }
            
            aContext.setWorkBufferOffsets( myStart, myEnd );
        }
        else
        {
            aContext.setWorkBufferOffsets( 0, 0 );
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private boolean isValidLong( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aSize,
                                 int               aFlags,
                                 long              aMinimumValue,
                                 long              aMaximumValue  )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            getLongValue( aContext, aOffset, aSize, aFlags, aMinimumValue, aMaximumValue );
            return ! aContext.isError();
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private long getLongValue( CopybookContext   aContext,
                               int               aOffset,
                               int               aSize,
                               int               aFlags,
                               long              aMinimumValue,
                               long              aMaximumValue  )
    {
        long myValue  = 0;
        
        decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );

        CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
        int        myStart  = aContext.getWorkBufferStartOffset();
        int        myEnd    = aContext.getWorkBufferEndOffset();
        
        if ( ! aContext.isError() )
        {
            long mySign       = 0;
            int  myDigitCount = 0;
            
            while ( myStart < myEnd && ! aContext.isError() )
            {
                char myChar = myBuffer.get( myStart );
                
                switch ( myChar )
                {
                    case '-':
                        
                        if ( myDigitCount == 0 && mySign == 0 )
                        {
                            mySign = -1;
                        }
                        else
                        {
                            aContext.setError( CopybookError.InvalidNumberFormat, null );
                        }
                        break;
                        
                    case '+':
                    
                        if ( myDigitCount == 0 && mySign == 0 )
                        {
                            mySign = 1;
                        }
                        else
                        {
                            aContext.setError( CopybookError.InvalidNumberFormat, null );
                        }
                        break;
                        
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        
                        long myDigit = Character.digit( myChar, 10 );
                        long myNewValue = myValue * 10 + myDigit;
                        if ( myValue != (myNewValue - myDigit) / 10 )
                        {
                            // Overflow occurred
                            aContext.setError( CopybookError.NumericOverflow, null );
                        }
                        
                        myValue = myNewValue;
                        myDigitCount++;
                        
                        break;
                        
                    default:
                        
                        aContext.setError( CopybookError.InvalidNumberFormat, null );
                        break;
                }
                
                myStart++;
            }
            
            if ( ! aContext.isError() )
            {
                if ( myDigitCount == 0 )
                {
                    aContext.setError( CopybookError.InvalidNumberFormat, null );
                }
                else
                {
                    myValue = myValue * ( mySign < 0 ? -1 : 1 );
                    
                    if ( myValue < aMinimumValue || myValue > aMaximumValue )
                    {
                        aContext.setError( CopybookError.NumberOutsideOfConstraints, null );
                    }
                }
            }
        }
        
        return ( ! aContext.isError() ? myValue : 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private void setLongValue( CopybookContext   aContext,
                               int               aOffset,
                               long              aValue,
                               int               aSize,
                               int               aFlags,
                               long              aMinimumValue,
                               long              aMaximumValue  )
    {
        if ( aValue < aMinimumValue || aValue > aMaximumValue )
        {
            aContext.setError( CopybookError.NumberOutsideOfConstraints, null );
        }
        else
        {
            encodeAsString( aContext, aOffset, Long.toString( aValue ), aSize, aFlags, null );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean checkDecodeNullEquivalents( CopybookContext            aContext,
                                                int                        aOffset,
                                                int                        aSize,
                                                int                        aFlags,
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myIsNull = false;
        
        if ( aNullEquivalentStrategies != null )
        {
            for ( int i = 0, ci = aNullEquivalentStrategies.length ; i < ci && ! myIsNull && ! aContext.isError() ; i++ )
            {
                NullEquivalentStrategy myNullEquivalentStrategy = aNullEquivalentStrategies[i];
                
                if ( ! myNullEquivalentStrategy.isDirect() && ! aContext.isWorkBufferOffsetsSet() )
                {
                    decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );
                }
                
                myIsNull = myNullEquivalentStrategy.isAlphanumericNull( this, aContext, aOffset, aSize, aFlags );
            }
        }
        
        return myIsNull;
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean encodeUsingNullEquivalent( CopybookContext            aContext,
                                               int                        aOffset,
                                               int                        aSize,
                                               int                        aFlags,
                                               NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myStrategyAvailable = ( aNullEquivalentStrategies != null && aNullEquivalentStrategies.length != 0 );
        
        if ( myStrategyAvailable )
        {
            aNullEquivalentStrategies[0].setAlphanumericNull( this, aContext, aOffset, aSize, aFlags );
        }
        
        return myStrategyAvailable;
    }
    
    
    
    
    //
    // AlphanumericPICMarshaller interface implementation
    //
    
    
    @Override
    public boolean isBlank( CopybookContext   aContext,
                            int               aOffset,
                            int               aSize,
                            int               aFlags    )
    {
        boolean blank;
        
        // Decode the character data using the correct decoder technique
        if ( AlphanumericPICFlags.isDisplay( aFlags ) )
        {
            blank = aContext.getDataBehavior().isDisplayCharsBlank( aContext, aOffset, aSize );
        }
        else if ( AlphanumericPICFlags.isNational( aFlags ) )
        {
            blank = aContext.getDataBehavior().isNationalCharsBlank( aContext, aOffset, aSize );
        }
        else // AlphanumericPICFlags.isDbcs( aFlags )
        {
            blank = aContext.getDataBehavior().isDbcsCharsBlank( aContext, aOffset, aSize );
        }
        
        return blank;
    }


    @Override
    public boolean isValidAsByte( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        return isValidLong( aContext,
                            aOffset,
                            aSize,
                            aFlags,
                            Byte.MIN_VALUE,
                            Byte.MAX_VALUE  );
    }
    

    @Override
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        long myValue =
                getLongValue( aContext,
                              aOffset,
                              aSize,
                              aFlags,
                              Byte.MIN_VALUE,
                              Byte.MAX_VALUE  );
        
        return (byte) myValue;
    }
    

    @Override
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        setLongValue( aContext,
                      aOffset,
                      aValue,
                      aSize,
                      aFlags,
                      Byte.MIN_VALUE,
                      Byte.MAX_VALUE  );
    }


    @Override
    public boolean isValidAsChar( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            decodeAsString( aContext, aOffset, aSize, aFlags, null );
            return ! aContext.isError();
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    

    @Override
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        String myValue =
                decodeAsString( aContext,
                                aOffset,
                                aSize,
                                aFlags,
                                null      );

        return myValue.charAt( 0 );
    }
    

    @Override
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        String myString = Character.toString( aValue );
        
        encodeAsString( aContext,
                        aOffset,
                        myString,
                        aSize,
                        aFlags,
                        null      );
    }


    @Override
    public boolean isValidAsShort( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    )
    {
        return isValidLong( aContext,
                            aOffset,
                            aSize,
                            aFlags,
                            Short.MIN_VALUE,
                            Short.MAX_VALUE  );
    }

    
    @Override
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        long myValue =
                getLongValue( aContext,
                              aOffset,
                              aSize,
                              aFlags,
                              Short.MIN_VALUE,
                              Short.MAX_VALUE  );
        
        return (short) myValue;
    }
    
    
    @Override
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue,
                               int               aSize,
                               int               aFlags    )
    {
        setLongValue( aContext,
                      aOffset,
                      aValue,
                      aSize,
                      aFlags,
                      Short.MIN_VALUE,
                      Short.MAX_VALUE  );
    }


    @Override
    public boolean isValidAsInteger( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aSize,
                                     int               aFlags    )
    {
        return isValidLong( aContext,
                            aOffset,
                            aSize,
                            aFlags,
                            Integer.MIN_VALUE,
                            Integer.MAX_VALUE  );
    }

    
    @Override
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        long myValue =
                getLongValue( aContext,
                              aOffset,
                              aSize,
                              aFlags,
                              Integer.MIN_VALUE,
                              Integer.MAX_VALUE  );
        
        return (int) myValue;
    }
    
    
    @Override
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue,
                                 int               aSize,
                                 int               aFlags    )
    {
        setLongValue( aContext,
                      aOffset,
                      aValue,
                      aSize,
                      aFlags,
                      Integer.MIN_VALUE,
                      Integer.MAX_VALUE  );
    }


    @Override
    public boolean isValidAsLong( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        return isValidLong( aContext,
                            aOffset,
                            aSize,
                            aFlags,
                            Long.MIN_VALUE,
                            Long.MAX_VALUE  );
    }

    
    @Override
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    )
    {
        return getLongValue( aContext,
                             aOffset,
                             aSize,
                             aFlags,
                             Long.MIN_VALUE,
                             Long.MAX_VALUE  );
    }
    
 
    @Override
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aSize,
                              int               aFlags    )
    {
        setLongValue( aContext,
                      aOffset,
                      aValue,
                      aSize,
                      aFlags,
                      Long.MIN_VALUE,
                      Long.MAX_VALUE  );
    }


    @Override
    public boolean isValidAsFloat( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    )
    {
        String myValue =
                decodeAsString( aContext,
                                aOffset,
                                aSize,
                                aFlags,
                                null      );

        return Float.parseFloat( myValue );
    }
    
    
    @Override
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue,
                               int               aSize,
                               int               aFlags    )
    {
        encodeAsDouble( aContext, aOffset, aValue, aSize, aFlags );
    }


    @Override
    public boolean isValidAsDouble( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aSize,
                                    int               aFlags    )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    )
    {
        String myValue =
                decodeAsString( aContext,
                                aOffset,
                                aSize,
                                aFlags,
                                null      );

        return Double.parseDouble( myValue );
    }
    
    
    @Override
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue,
                                int               aSize,
                                int               aFlags    )
    {
        String myString = Double.toString( aValue );
        
        encodeAsString( aContext,
                        aOffset,
                        myString,
                        aSize,
                        aFlags,
                        null      );
    }


    @Override
    public boolean isValidAsBigInteger( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public BigInteger decodeAsBigInteger( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    @Override
    public void encodeAsBigInteger( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigInteger                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    @Override
    public boolean isValidAsBigDecimal( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    @Override
    public BigDecimal decodeAsBigDecimal( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    @Override
    public void encodeAsBigDecimal( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigDecimal                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    @Override
    public boolean isValidAsString( CopybookContext            aContext,
                                    int                        aOffset,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            boolean myValid = false;
            
            if
            (
                checkDecodeNullEquivalents( aContext,
                                            aOffset,
                                            aSize,
                                            aFlags,
                                            aNullEquivalentStrategies )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                CharBuffer myBuffer = aContext.getWorkBuffer( aSize );
                
                // Decode the character data using the correct decoder technique
                if ( AlphanumericPICFlags.isDisplay( aFlags ) )
                {
                    aContext.getDataBehavior().decodeDisplayChars( aContext, aOffset, aSize, myBuffer );
                }
                else if ( AlphanumericPICFlags.isNational( aFlags ) )
                {
                    aContext.getDataBehavior().decodeNationalChars( aContext, aOffset, aSize, myBuffer );
                }
                else // AlphanumericPICFlags.isDbcs( aFlags )
                {
                    aContext.getDataBehavior().decodeDbcsChars( aContext, aOffset, aSize, myBuffer );
                }
                
                myValid = ( ! aContext.isError() );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }

    
    @Override
    public String decodeAsString( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        String myString = null;
        
        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aSize,
                                          aFlags,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            CharBuffer myBuffer  = aContext.getWorkBuffer( aSize );
            
            decodeStringIntoWorkBuffer( aContext, aOffset, aSize, aFlags );
            
            if ( ! aContext.isError() )
            {
                int myStart = aContext.getWorkBufferStartOffset();
                int myEnd   = aContext.getWorkBufferEndOffset();
                
                StringBuilder myTempBuffer = aContext.getWorkStringBuilder( myEnd - myStart );
        
                while ( myStart < myEnd )
                {
                    myTempBuffer.append( myBuffer.get( myStart++ ) );
                }
                
                myString = myTempBuffer.toString();
            }
            else
            {
                myString = null;
            }
        }
        
        return myString;
    }
    
    
    @Override
    public void encodeAsString( CopybookContext            aContext,
                                int                        aOffset,
                                String                     aValue,
                                int                        aSize,
                                int                        aFlags,
                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;
        
        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aSize,
                                               aFlags,
                                               aNullEquivalentStrategies );
        }
        
        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            if ( aValue == null )
            {
                if ( AlphanumericPICFlags.isNullAllowed( aFlags ) )
                {
                    aValue = "";
                }
                else
                {
                    aContext.setError( CopybookError.NullNotAllowed, null );
                }
            }

            if ( ! aContext.isError() )
            {
                CharBuffer myBuffer       = aContext.getWorkBuffer( aSize );
                int        myValueLength  = aValue.length();
                int        myBufferOffset = 0;
                int        myStart;
                int        myEnd;
        
                if ( AlphanumericPICFlags.isRightJustify( aFlags ) )
                {
                    if ( myValueLength < aSize )
                    {
                        myBufferOffset = aSize - myValueLength;
                        
                        for ( int i = 0, ci = myBufferOffset ; i < ci ; i++ )
                        {
                            myBuffer.put( i, ' ' );
                        }
        
                        myStart = 0;
                    }
                    else
                    {
                        myStart = myValueLength - aSize;
                    }
        
                    myEnd = myValueLength;
                }
                else
                {
                    if ( myValueLength < aSize )
                    {
                        for ( int i = myValueLength ; i < aSize ; i++ )
                        {
                            myBuffer.put( i, ' ' );
                        }
                        
                        myEnd = myValueLength;
                    }
                    else
                    {
                        myEnd = aSize;
                    }
                    
                    myStart = 0;
                }
                
                while ( myStart < myEnd )
                {
                    myBuffer.put( myBufferOffset++, aValue.charAt( myStart++ ) );
                }
                
                myBuffer.limit   ( aSize );
                myBuffer.position( aSize );
                myBuffer.flip();
                
                // Encode the character data using the correct encoder technique
                if ( AlphanumericPICFlags.isDisplay( aFlags ) )
                {
                    aContext.getDataBehavior().encodeDisplayChars( aContext, aOffset, aSize, myBuffer );
                }
                else if ( AlphanumericPICFlags.isNational( aFlags ) )
                {
                    aContext.getDataBehavior().encodeNationalChars( aContext, aOffset, aSize, myBuffer );
                }
                else // AlphanumericPICFlags.isDbcs( aFlags )
                {
                    aContext.getDataBehavior().encodeDbcsChars( aContext, aOffset, aSize, myBuffer );
                }
            }
        }
    }

    
    @Override
    public boolean isValidAsDate( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  Converter                  aConverter,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();
        
        try
        {
            boolean myValid = false;
            
            if ( checkDecodeNullEquivalents( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies ) )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                myValid = aConverter.isAlphanumericValid( this, aContext, aOffset, aSize, aFlags );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }
    

    @Override
    public Date decodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        Date myDate = null;

        aContext.clearWorkBufferOffsets();
        
        if
        (
            ! checkDecodeNullEquivalents( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            myDate = (Date) aConverter.decodeAlphanumeric( this, aContext, aOffset, aSize, aFlags );
        }
        
        return myDate;
    }
    
    
    @Override
    public void encodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              Date                       aValue,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;
        
        if ( aValue == null )
        {
            myNullEquivalentUsed = encodeUsingNullEquivalent( aContext, aOffset, aSize, aFlags, aNullEquivalentStrategies );
        }
        
        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            aConverter.encodeAlphanumeric( this, aContext, aOffset, aValue, aSize, aFlags );
        }
    }
    
    
    @Override
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                         int                           aSize,                      
                                         int                           aFlags                       )
    {
        boolean myValid = false;
        
        String myValue = decodeAsString( aContext, aOffset, aSize, aFlags, null );
        if ( ! aContext.isError() )
        {
            ConstantValue[] myConstantValues = aConditionNameValueMappings.get( aConditionName );
            if ( myConstantValues != null )
            {
                myValid = ( Arrays.binarySearch( myConstantValues, myValue ) >= 0 );
            }
        }
        
        return myValid;
    }
}

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


package com.xylocore.commons.data.copybook.runtime.marshallers.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.CopybookError;
import com.xylocore.commons.data.copybook.runtime.NumericPICMarshallerHelper;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;
import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author Eric R. Medley
 */

public abstract class AbstractExternalNumericPICMarshaller
    extends
        AbstractPICMarshaller
    implements
        ExternalNumericPICMarshaller
{
    //
    // Class implementation
    //

    
    /**
     * FILLIN
     * 
     * @param       aValue
     * @param       aMinValue
     * @param       aMaxValue
     */
    protected abstract void validateRange( long   aValue,
                                           long   aMinValue,
                                           long   aMaxValue );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aMinimumValue
     * @param       aMaximumValue
     * 
     * @return
     */
    private boolean isValidLong( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aDigits,
                                 SignType          aSignType,
                                 SignPosition      aSignPosition,
                                 int               aPrecision,
                                 int               aScalingPosition,
                                 long              aMinimumValue,
                                 long              aMaximumValue     )
    {
        CopybookError myStandardError = aContext.getStandardError();
        Object        myPlatformError = aContext.getPlatformError();

        try
        {
            boolean myValid = false;

            long myValue =
                    decodeAsLong( aContext,
                                  aOffset,
                                  aDigits,
                                  aSignType,
                                  aSignPosition,
                                  aPrecision,
                                  aScalingPosition );
            
            if ( ! aContext.isError() )
            {
                if ( myValue >= aMinimumValue && myValue <= aMaximumValue )
                {
                    myValid = true;
                }
            }

            return myValid;
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
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     */
    protected abstract boolean validateUsingConverter( CopybookContext   aContext,
                                                       int               aOffset,
                                                       int               aDigits,
                                                       SignType          aSignType,
                                                       SignPosition      aSignPosition,
                                                       int               aPrecision,
                                                       int               aScalingPosition,
                                                       Converter         aConverter        );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     */
    protected abstract Object decodeUsingConverter( CopybookContext   aContext,
                                                    int               aOffset,
                                                    int               aDigits,
                                                    SignType          aSignType,
                                                    SignPosition      aSignPosition,
                                                    int               aPrecision,
                                                    int               aScalingPosition,
                                                    Converter         aConverter        );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     */
    protected abstract void encodeUsingConverter( CopybookContext   aContext,
                                                  int               aOffset,
                                                  Object            aValue,
                                                  int               aDigits,
                                                  SignType          aSignType,
                                                  SignPosition      aSignPosition,
                                                  int               aPrecision,
                                                  int               aScalingPosition,
                                                  Converter         aConverter        );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategy
     * 
     * @return
     */
    protected abstract boolean checkUsingNullEquivalent( CopybookContext          aContext,
                                                         int                      aOffset,
                                                         int                      aDigits,
                                                         SignType                 aSignType,
                                                         SignPosition             aSignPosition,
                                                         int                      aPrecision,
                                                         int                      aScalingPosition,
                                                         NullEquivalentStrategy   aNullEquivalentStrategy );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategy
     */
    protected abstract void encodeUsingNullEquivalent( CopybookContext          aContext,
                                                       int                      aOffset,
                                                       int                      aDigits,
                                                       SignType                 aSignType,
                                                       SignPosition             aSignPosition,
                                                       int                      aPrecision,
                                                       int                      aScalingPosition,
                                                       NullEquivalentStrategy   aNullEquivalentStrategy );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean checkDecodeNullEquivalents( CopybookContext            aContext,
                                                int                        aOffset,
                                                int                        aDigits,
                                                SignType                   aSignType,
                                                SignPosition               aSignPosition,
                                                int                        aPrecision,
                                                int                        aScalingPosition,
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myIsNull = false;

        if ( aNullEquivalentStrategies != null )
        {
            for ( int i = 0, ci = aNullEquivalentStrategies.length ; i < ci && !myIsNull && !aContext.isError() ; i++ )
            {
                NullEquivalentStrategy myNullEquivalentStrategy = aNullEquivalentStrategies[i];

                myIsNull = checkUsingNullEquivalent( aContext,
                                                     aOffset,
                                                     aDigits,
                                                     aSignType,
                                                     aSignPosition,
                                                     aPrecision,
                                                     aScalingPosition,
                                                     myNullEquivalentStrategy );
            }
        }

        return myIsNull;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    private boolean encodeUsingNullEquivalent( CopybookContext            aContext,
                                               int                        aOffset,
                                               int                        aDigits,
                                               SignType                   aSignType,
                                               SignPosition               aSignPosition,
                                               int                        aPrecision,
                                               int                        aScalingPosition,
                                               NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myStrategyAvailable = ( aNullEquivalentStrategies != null && aNullEquivalentStrategies.length != 0 );

        if ( myStrategyAvailable )
        {
            encodeUsingNullEquivalent( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aSignPosition,
                                       aPrecision,
                                       aScalingPosition,
                                       aNullEquivalentStrategies[0] );
        }

        return myStrategyAvailable;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aValue
     * @param       aDigits
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    protected long convertToLong( CopybookContext   aContext,
                                  BigDecimal        aValue,
                                  int               aDigits,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        assert aContext != null;
        assert aValue   != null;
        assert aDigits  != 0;
        assert aPrecision == 0 || aScalingPosition == 0;

        StringBuilder myBuffer                  = aContext.getWorkStringBuilder( aDigits );
        String        myUnscaledValue           = aValue.unscaledValue().toString();
        int           myUnscaledValueLength     = myUnscaledValue.length();
        int           mySign                    = myUnscaledValue.charAt( 0 ) == '-' ? -1 : 1;
        int           myUnscaledValueSkipLength = (mySign == -1) ? 1 : 0;
        int           myValueScale              = aValue.scale();
        boolean       myDataLost                = false;
        int           myValueStartOffset        = 0;
        int           myImpliedDecimalPosition;
        int           myLeadingZeroesCount;
        int           myTrailingZeroesCount;

        if ( mySign == -1 )
        {
            myUnscaledValueLength--;
        }

        myValueScale += aScalingPosition;

        if ( myValueScale < 0 )
        {
            myImpliedDecimalPosition = myUnscaledValueLength + (-myValueScale);
        }
        else
        {
            myImpliedDecimalPosition = myUnscaledValueLength - myValueScale;
            if ( myImpliedDecimalPosition < 0 )
            {
                myValueStartOffset       += -myImpliedDecimalPosition;
                myImpliedDecimalPosition  = 0;
            }
        }

        int myEnd                   = myImpliedDecimalPosition + aPrecision;
        int myStart                 = myEnd - aDigits;
        int myValueEndOffset        = myValueStartOffset + myUnscaledValueLength;
        int myLossCheckStartOffset;
        int myLossCheckEndOffset;

        if ( myValueStartOffset >= myEnd || myValueEndOffset <= myStart )
        {
            myLeadingZeroesCount   = aDigits;
            myTrailingZeroesCount  = 0;
            myValueStartOffset     = 0;
            myValueEndOffset       = 0;
            myLossCheckStartOffset = 0;
            myLossCheckEndOffset   = myUnscaledValueLength;
        }
        else
        {
            if ( myValueStartOffset < myStart )
            {
                myLossCheckStartOffset = 0;
                myLossCheckEndOffset   = myStart - myValueEndOffset;
            }
            else if ( myValueEndOffset > myEnd )
            {
                myLossCheckStartOffset = myEnd - myValueStartOffset;
                myLossCheckEndOffset   = myValueEndOffset - myValueStartOffset;
            }
            else
            {
                myLossCheckStartOffset = 0;
                myLossCheckEndOffset   = 0;
            }

            myValueStartOffset    = Math.max( myValueStartOffset, myStart );
            myValueEndOffset      = Math.min( myValueEndOffset, myEnd );
            myLeadingZeroesCount  = myValueStartOffset - myStart;
            myTrailingZeroesCount = myEnd - myValueEndOffset;
        }

        myImpliedDecimalPosition -= myStart;

        FormatHelper.stringOfCharacters( myBuffer, '0', myLeadingZeroesCount );
        myBuffer.append( myUnscaledValue,
                         myUnscaledValueSkipLength,
                         myUnscaledValueSkipLength + myValueEndOffset - myValueStartOffset );
        FormatHelper.stringOfCharacters( myBuffer, '0', myTrailingZeroesCount );

        while ( myLossCheckStartOffset < myLossCheckEndOffset )
        {
            if ( myUnscaledValue.charAt( myUnscaledValueSkipLength + myLossCheckStartOffset++ ) != '0' )
            {
                myDataLost = true;
                break;
            }
        }

        assert myBuffer.length() == aDigits;

        long myValue = 0;
        for ( int i = 0, ci = myBuffer.length() ; i < ci ; i++ )
        {
            myValue = 10*myValue + Character.digit( myBuffer.charAt( i ), 10 );
        }
        myValue *= mySign;
        
        if ( myDataLost )
        {
            // TODO: implement error indication
        }
        
        return myValue;
    }
    
    


    //
    // ExternalNumericPICMarshaller interface implementation
    //


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsByte( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        return isValidLong( aContext,
                            aOffset,
                            aDigits,
                            aSignType,
                            aSignPosition,
                            aPrecision,
                            aScalingPosition,
                            ((long) Byte.MIN_VALUE) & 0xff,
                            ((long) Byte.MAX_VALUE) & 0xff  );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );

        validateRange( myValue, Byte.MIN_VALUE, Byte.MAX_VALUE );

        return (byte) myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsByte(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, byte, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xff,
                      aDigits,
                      aSignType,
                      aSignPosition,
                      aPrecision,
                      aScalingPosition        );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsChar( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        return isValidLong( aContext,
                            aOffset,
                            aDigits,
                            aSignType,
                            aSignPosition,
                            aPrecision,
                            aScalingPosition,
                            ((long) Character.MIN_VALUE) & 0xffff,
                            ((long) Character.MAX_VALUE) & 0xffff  );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );

        validateRange( myValue, Character.MIN_VALUE, Character.MAX_VALUE );

        return (char) myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsChar(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, char, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffff,
                      aDigits,
                      aSignType,
                      aSignPosition,
                      aPrecision,
                      aScalingPosition          );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsShort( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aDigits,
                                   SignType          aSignType,
                                   SignPosition      aSignPosition,
                                   int               aPrecision,
                                   int               aScalingPosition )
    {
        return isValidLong( aContext,
                            aOffset,
                            aDigits,
                            aSignType,
                            aSignPosition,
                            aPrecision,
                            aScalingPosition,
                            Short.MIN_VALUE,
                            Short.MAX_VALUE   );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );

        validateRange( myValue, Short.MIN_VALUE, Short.MAX_VALUE );

        return (short) myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsShort(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, short, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue,
                               int               aDigits,
                               SignType          aSignType,
                               SignPosition      aSignPosition,
                               int               aPrecision,
                               int               aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffff,
                      aDigits,
                      aSignType,
                      aSignPosition,
                      aPrecision,
                      aScalingPosition          );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsInteger( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aDigits,
                                     SignType          aSignType,
                                     SignPosition      aSignPosition,
                                     int               aPrecision,
                                     int               aScalingPosition )
    {
        return isValidLong( aContext,
                            aOffset,
                            aDigits,
                            aSignType,
                            aSignPosition,
                            aPrecision,
                            aScalingPosition,
                            Integer.MIN_VALUE,
                            Integer.MAX_VALUE  );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );

        validateRange( myValue, Integer.MIN_VALUE, Integer.MAX_VALUE );

        return (int) myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue,
                                 int               aDigits,
                                 SignType          aSignType,
                                 SignPosition      aSignPosition,
                                 int               aPrecision,
                                 int               aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffffffff,
                      aDigits,
                      aSignType,
                      aSignPosition,
                      aPrecision,
                      aScalingPosition              );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsLong( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        return isValidLong( aContext,
                            aOffset,
                            aDigits,
                            aSignType,
                            aSignPosition,
                            aPrecision,
                            aScalingPosition,
                            Long.MIN_VALUE,
                            Long.MAX_VALUE    );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public abstract long decodeAsLong( CopybookContext   aContext,
                                       int               aOffset,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition,
                                       int               aPrecision,
                                       int               aScalingPosition );


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public abstract void encodeAsLong( CopybookContext   aContext,
                                       int               aOffset,
                                       long              aValue,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition,
                                       int               aPrecision,
                                       int               aScalingPosition );


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsFloat( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aDigits,
                                   SignType          aSignType,
                                   SignPosition      aSignPosition,
                                   int               aPrecision,
                                   int               aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition )
    {
        return (float) decodeAsDouble( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aSignPosition,
                                       aPrecision,
                                       aScalingPosition );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsFloat(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, float, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue,
                               int               aDigits,
                               SignType          aSignType,
                               SignPosition      aSignPosition,
                               int               aPrecision,
                               int               aScalingPosition )
    {
        encodeAsDouble( aContext,
                        aOffset,
                        aValue,
                        aDigits,
                        aSignType,
                        aSignPosition,
                        aPrecision,
                        aScalingPosition );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isValidAsDouble( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aDigits,
                                    SignType          aSignType,
                                    SignPosition      aSignPosition,
                                    int               aPrecision,
                                    int               aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        double myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );

        return NumericPICMarshallerHelper.scaleValue( myValue,
                                                      aPrecision,
                                                      aScalingPosition,
                                                      false );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsDouble(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, double, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition )
    {
        long myScaleValue =
                (long) NumericPICMarshallerHelper.scaleValue( aValue,
                                                              aPrecision,
                                                              aScalingPosition,
                                                              true              );

        encodeAsLong( aContext,
                      aOffset,
                      myScaleValue,
                      aDigits,
                      aSignType,
                      aSignPosition,
                      aPrecision,
                      aScalingPosition );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsBigInteger( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aDigits,
                                        SignType                   aSignType,
                                        SignPosition               aSignPosition,
                                        int                        aPrecision,
                                        int                        aScalingPosition,
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
                                            aDigits,
                                            aSignType,
                                            aSignPosition,
                                            aPrecision,
                                            aScalingPosition,
                                            aNullEquivalentStrategies
                )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                // TODO: implement
                throw new UnsupportedOperationException( "not yet implemented" );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public BigInteger decodeAsBigInteger( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aDigits,
                                          SignType                   aSignType,
                                          SignPosition               aSignPosition,
                                          int                        aPrecision,
                                          int                        aScalingPosition,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        BigInteger myValue = null;

        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aDigits,
                                          aSignType,
                                          aSignPosition,
                                          aPrecision,
                                          aScalingPosition,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            long myLongValue =
                    decodeAsLong( aContext,
                                  aOffset,
                                  aDigits,
                                  aSignType,
                                  aSignPosition,
                                  aPrecision,
                                  aScalingPosition );

            myValue = BigInteger.valueOf( myLongValue );
        }

        return myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsBigInteger(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.math.BigInteger, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsBigInteger( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigInteger                 aValue,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;

        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aDigits,
                                               aSignType,
                                               aSignPosition,
                                               aPrecision,
                                               aScalingPosition,
                                               aNullEquivalentStrategies );
        }

        if ( ! myNullEquivalentUsed )
        {
            if ( aValue.bitLength() > 63 )
            {
                // TODO: implement exception
            }

            encodeAsLong( aContext,
                          aOffset,
                          aValue.longValue(),
                          aDigits,
                          aSignType,
                          aSignPosition,
                          aPrecision,
                          aScalingPosition    );
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsBigDecimal( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aDigits,
                                        SignType                   aSignType,
                                        SignPosition               aSignPosition,
                                        int                        aPrecision,
                                        int                        aScalingPosition,
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
                                            aDigits,
                                            aSignType,
                                            aSignPosition,
                                            aPrecision,
                                            aScalingPosition,
                                            aNullEquivalentStrategies )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                myValid =
                        isValidAsLong( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aSignPosition,
                                       aPrecision,
                                       aScalingPosition );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public BigDecimal decodeAsBigDecimal( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aDigits,
                                          SignType                   aSignType,
                                          SignPosition               aSignPosition,
                                          int                        aPrecision,
                                          int                        aScalingPosition,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        BigDecimal myValue = null;

        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aDigits,
                                          aSignType,
                                          aSignPosition,
                                          aPrecision,
                                          aScalingPosition,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            BigInteger myIntValue =
                    decodeAsBigInteger( aContext,
                                        aOffset,
                                        aDigits,
                                        aSignType,
                                        aSignPosition,
                                        aPrecision,
                                        aScalingPosition,
                                        null              );

            if ( myIntValue != null && !aContext.isError() )
            {
                myValue = new BigDecimal( myIntValue,
                                          -NumericPICMarshallerHelper.calculateScale( aPrecision, aScalingPosition ) );
            }
        }

        return myValue;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsBigDecimal(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.math.BigDecimal, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsBigDecimal( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigDecimal                 aValue,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;

        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aDigits,
                                               aSignType,
                                               aSignPosition,
                                               aPrecision,
                                               aScalingPosition,
                                               aNullEquivalentStrategies );
        }

        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            long myLongValue = convertToLong( aContext, aValue, aDigits, aPrecision, aScalingPosition );
            
            if ( ! aContext.isError() )
            {
                encodeAsLong( aContext,
                              aOffset,
                              myLongValue,
                              aDigits,
                              aSignType,
                              aSignPosition,
                              aPrecision,
                              aScalingPosition );
            }
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsString( CopybookContext            aContext,
                                    int                        aOffset,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
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
                                            aDigits,
                                            aSignType,
                                            aSignPosition,
                                            aPrecision,
                                            aScalingPosition,
                                            aNullEquivalentStrategies )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                // TODO: implement
                throw new UnsupportedOperationException( "not yet implemented" );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public String decodeAsString( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aDigits,
                                  SignType                   aSignType,
                                  SignPosition               aSignPosition,
                                  int                        aPrecision,
                                  int                        aScalingPosition,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        String myString = null;

        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aDigits,
                                          aSignType,
                                          aSignPosition,
                                          aPrecision,
                                          aScalingPosition,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            long myValue = decodeAsLong( aContext,
                                         aOffset,
                                         aDigits,
                                         aSignType,
                                         aSignPosition,
                                         aPrecision,
                                         aScalingPosition );

            if ( myValue == 0 )
            {
                myString = "0";
            }
            else
            {
                // TODO: calculate minimum capacity
                StringBuilder myBuffer = aContext.getWorkStringBuilder( 0 );

                if ( myValue < 0 )
                {
                    myBuffer.append( '-' );
                    myValue = -myValue;
                }

                if ( aScalingPosition != 0 )
                {
                    if ( aScalingPosition < 0 )
                    {
                        myBuffer.append( "0." );
                        FormatHelper.stringOfCharacters( myBuffer, '0', -aScalingPosition                             );
                        FormatHelper.formatNumber      ( myBuffer, myValue, aDigits, true, FormatHelper.Justify.Right );
                    }
                    else
                    {
                        FormatHelper.formatNumber      ( myBuffer, myValue, aDigits, false, FormatHelper.Justify.Left );
                        FormatHelper.stringOfCharacters( myBuffer, '0', aScalingPosition                              );
                    }
                }
                else
                {
                    myBuffer.append( myValue );

                    if ( aPrecision != 0 )
                    {
                        myBuffer.insert( myBuffer.length() - aPrecision, '.' );
                    }
                }

                myString = myBuffer.toString();
            }
        }

        return myString;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsString(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.String, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsString( CopybookContext            aContext,
                                int                        aOffset,
                                String                     aValue,
                                int                        aDigits,
                                SignType                   aSignType,
                                SignPosition               aSignPosition,
                                int                        aPrecision,
                                int                        aScalingPosition,
                                NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;

        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aDigits,
                                               aSignType,
                                               aSignPosition,
                                               aPrecision,
                                               aScalingPosition,
                                               aNullEquivalentStrategies );
        }

        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            if ( aValue.length() > 18 )
            {
                encodeAsBigDecimal( aContext,
                                    aOffset,
                                    new BigDecimal( aValue ),
                                    aDigits,
                                    aSignType,
                                    aSignPosition,
                                    aPrecision,
                                    aScalingPosition,
                                    null                     );
            }
            else
            {
                encodeAsDouble( aContext,
                                aOffset,
                                Double.parseDouble( aValue ),
                                aDigits,
                                aSignType,
                                aSignPosition,
                                aPrecision,
                                aScalingPosition              );
            }
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isValidAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public boolean isValidAsDate( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aDigits,
                                  SignType                   aSignType,
                                  SignPosition               aSignPosition,
                                  int                        aPrecision,
                                  int                        aScalingPosition,
                                  Converter                  aConverter,
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
                                            aDigits,
                                            aSignType,
                                            aSignPosition,
                                            aPrecision,
                                            aScalingPosition,
                                            aNullEquivalentStrategies )
            )
            {
                myValid = true;
            }
            else if ( ! aContext.isError() )
            {
                myValid = validateUsingConverter( aContext,
                                                  aOffset,
                                                  aDigits,
                                                  aSignType,
                                                  aSignPosition,
                                                  aPrecision,
                                                  aScalingPosition,
                                                  aConverter        );
            }

            return myValid;
        }
        finally
        {
            aContext.setError( myStandardError, myPlatformError );
        }
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#decodeAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public Date decodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              int                        aDigits,
                              SignType                   aSignType,
                              SignPosition               aSignPosition,
                              int                        aPrecision,
                              int                        aScalingPosition,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        Date myDate = null;

        if
        (
            ! checkDecodeNullEquivalents( aContext,
                                          aOffset,
                                          aDigits,
                                          aSignType,
                                          aSignPosition,
                                          aPrecision,
                                          aScalingPosition,
                                          aNullEquivalentStrategies ) &&
            ! aContext.isError()
        )
        {
            myDate = (Date) decodeUsingConverter( aContext,
                                                  aOffset,
                                                  aDigits,
                                                  aSignType,
                                                  aSignPosition,
                                                  aPrecision,
                                                  aScalingPosition,
                                                  aConverter        );
        }

        return myDate;
    }



    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#encodeAsDate(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.util.Date, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    public void encodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              Date                       aValue,
                              int                        aDigits,
                              SignType                   aSignType,
                              SignPosition               aSignPosition,
                              int                        aPrecision,
                              int                        aScalingPosition,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        boolean myNullEquivalentUsed = false;

        if ( aValue == null )
        {
            myNullEquivalentUsed =
                    encodeUsingNullEquivalent( aContext,
                                               aOffset,
                                               aDigits,
                                               aSignType,
                                               aSignPosition,
                                               aPrecision,
                                               aScalingPosition,
                                               aNullEquivalentStrategies );
        }

        if ( ! myNullEquivalentUsed && ! aContext.isError() )
        {
            encodeUsingConverter( aContext,
                                  aOffset,
                                  aValue,
                                  aDigits,
                                  aSignType,
                                  aSignPosition,
                                  aPrecision,
                                  aScalingPosition,
                                  aConverter        );
        }
    }

    
    /**
     * FILLIN
     *
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                         int                           aDigits,                    
                                         SignType                      aSignType,                  
                                         SignPosition                  aSignPosition,              
                                         int                           aPrecision,                 
                                         int                           aScalingPosition             )
    {
        boolean myValid = false;
        
        Comparable<? extends Object> myValue;
        if ( aPrecision != 0 || aScalingPosition != 0 )
        {
            myValue = decodeAsBigDecimal( aContext, aOffset, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition, null );
        }
        else
        {
            myValue = new Long( decodeAsLong( aContext, aOffset, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition ) );
        }
        
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

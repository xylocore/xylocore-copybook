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
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.xylocore.commons.util.FormatHelper;
import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.NumericPICMarshallerHelper;
import com.xylocore.copybook.runtime.SignType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractNumericPICMarshaller
    extends
        AbstractPICMarshaller
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
                                           long   aMaxValue  );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsByte( CopybookContext    aContext,
                                  int        aOffset,
                                  int        aDigits,
                                  SignType   aSignType,
                                  int        aPrecision,
                                  int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeAsByte( CopybookContext    aContext,
                              int        aOffset,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );
        
        validateRange( myValue, Byte.MIN_VALUE, Byte.MAX_VALUE );

        return (byte) myValue;
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsByte( CopybookContext    aContext,
                              int        aOffset,
                              byte       aValue,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xff,
                      aDigits,
                      aSignType,
                      aPrecision,
                      aScalingPosition        );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsChar( CopybookContext    aContext,
                                  int        aOffset,
                                  int        aDigits,
                                  SignType   aSignType,
                                  int        aPrecision,
                                  int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeAsChar( CopybookContext    aContext,
                              int        aOffset,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );
        
        validateRange( myValue, Character.MIN_VALUE, Character.MAX_VALUE );

        return (char) myValue;
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsChar( CopybookContext    aContext,
                              int        aOffset,
                              char       aValue,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffff,
                      aDigits,
                      aSignType,
                      aPrecision,
                      aScalingPosition          );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsShort( CopybookContext    aContext,
                                   int        aOffset,
                                   int        aDigits,
                                   SignType   aSignType,
                                   int        aPrecision,
                                   int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeAsShort( CopybookContext    aContext,
                                int        aOffset,
                                int        aDigits,
                                SignType   aSignType,
                                int        aPrecision,
                                int        aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );
        
        validateRange( myValue, Short.MIN_VALUE, Short.MAX_VALUE );

        return (short) myValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsShort( CopybookContext    aContext,
                               int        aOffset,
                               short      aValue,
                               int        aDigits,
                               SignType   aSignType,
                               int        aPrecision,
                               int        aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffff,
                      aDigits,
                      aSignType,
                      aPrecision,
                      aScalingPosition          );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsInteger( CopybookContext    aContext,
                                     int        aOffset,
                                     int        aDigits,
                                     SignType   aSignType,
                                     int        aPrecision,
                                     int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeAsInteger( CopybookContext    aContext,
                                int        aOffset,
                                int        aDigits,
                                SignType   aSignType,
                                int        aPrecision,
                                int        aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );
        
        validateRange( myValue, Integer.MIN_VALUE, Integer.MAX_VALUE );

        return (int) myValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsInteger( CopybookContext    aContext,
                                 int        aOffset,
                                 int        aValue,
                                 int        aDigits,
                                 SignType   aSignType,
                                 int        aPrecision,
                                 int        aScalingPosition )
    {
        encodeAsLong( aContext,
                      aOffset,
                      ((long) aValue) & 0xffffffff,
                      aDigits,
                      aSignType,
                      aPrecision,
                      aScalingPosition              );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsLong( CopybookContext    aContext,
                                  int        aOffset,
                                  int        aDigits,
                                  SignType   aSignType,
                                  int        aPrecision,
                                  int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public abstract long decodeAsLong( CopybookContext    aContext,
                                       int        aOffset,
                                       int        aDigits,
                                       SignType   aSignType,
                                       int        aPrecision,
                                       int        aScalingPosition );
    
 
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public abstract void encodeAsLong( CopybookContext    aContext,
                                       int        aOffset,
                                       long       aValue,
                                       int        aDigits,
                                       SignType   aSignType,
                                       int        aPrecision,
                                       int        aScalingPosition );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsFloat( CopybookContext    aContext,
                                   int        aOffset,
                                   int        aDigits,
                                   SignType   aSignType,
                                   int        aPrecision,
                                   int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeAsFloat( CopybookContext    aContext,
                                int        aOffset,
                                int        aDigits,
                                SignType   aSignType,
                                int        aPrecision,
                                int        aScalingPosition )
    {
        return (float) decodeAsDouble( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aPrecision,
                                       aScalingPosition );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsFloat( CopybookContext    aContext,
                               int        aOffset,
                               float      aValue,
                               int        aDigits,
                               SignType   aSignType,
                               int        aPrecision,
                               int        aScalingPosition )
    {
        encodeAsDouble( aContext,
                        aOffset,
                        aValue,
                        aDigits,
                        aSignType,
                        aPrecision,
                        aScalingPosition );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsDouble( CopybookContext    aContext,
                                    int        aOffset,
                                    int        aDigits,
                                    SignType   aSignType,
                                    int        aPrecision,
                                    int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeAsDouble( CopybookContext    aContext,
                                  int        aOffset,
                                  int        aDigits,
                                  SignType   aSignType,
                                  int        aPrecision,
                                  int        aScalingPosition )
    {
        double myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );

        return NumericPICMarshallerHelper.scaleValue( myValue, aPrecision, aScalingPosition, false );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsDouble( CopybookContext    aContext,
                                int        aOffset,
                                double     aValue,
                                int        aDigits,
                                SignType   aSignType,
                                int        aPrecision,
                                int        aScalingPosition )
    {
        long myScaleValue = (long) NumericPICMarshallerHelper.scaleValue( aValue, aPrecision, aScalingPosition, true );
        
        encodeAsLong( aContext,
                      aOffset,
                      myScaleValue,
                      aDigits,
                      aSignType,
                      aPrecision,
                      aScalingPosition );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsBigInteger( CopybookContext    aContext,
                                        int        aOffset,
                                        int        aDigits,
                                        SignType   aSignType,
                                        int        aPrecision,
                                        int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigInteger decodeAsBigInteger( CopybookContext    aContext,
                                          int        aOffset,
                                          int        aDigits,
                                          SignType   aSignType,
                                          int        aPrecision,
                                          int        aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition );
        
        return BigInteger.valueOf( myValue );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsBigInteger( CopybookContext      aContext,
                                    int          aOffset,
                                    BigInteger   aValue,
                                    int          aDigits,
                                    SignType     aSignType,
                                    int          aPrecision,
                                    int          aScalingPosition )
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
                      aPrecision,
                      aScalingPosition    );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsBigDecimal( CopybookContext    aContext,
                                        int        aOffset,
                                        int        aDigits,
                                        SignType   aSignType,
                                        int        aPrecision,
                                        int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigDecimal decodeAsBigDecimal( CopybookContext    aContext,
                                          int        aOffset,
                                          int        aDigits,
                                          SignType   aSignType,
                                          int        aPrecision,
                                          int        aScalingPosition )
    {
        BigInteger myValue =
                decodeAsBigInteger( aContext,
                                    aOffset,
                                    aDigits,
                                    aSignType,
                                    aPrecision,
                                    aScalingPosition );
        
        return new BigDecimal( myValue, -NumericPICMarshallerHelper.calculateScale( aPrecision, aScalingPosition ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsBigDecimal( CopybookContext      aContext,
                                    int          aOffset,
                                    BigDecimal   aValue,
                                    int          aDigits,
                                    SignType     aSignType,
                                    int          aPrecision,
                                    int          aScalingPosition )
    {
        // TODO: implement
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsString( CopybookContext    aContext,
                                    int        aOffset,
                                    int        aDigits,
                                    SignType   aSignType,
                                    int        aPrecision,
                                    int        aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public String decodeAsString( CopybookContext    aContext,
                                  int                aOffset,
                                  int                aDigits,
                                  SignType           aSignType,
                                  int                aPrecision,
                                  int                aScalingPosition )
    {
        long myValue =
                decodeAsLong( aContext,
                              aOffset,
                              aDigits,
                              aSignType,
                              aPrecision,
                              aScalingPosition  );

        // TODO: calculate minimum capacity
        StringBuilder myBuffer = aContext.getWorkStringBuilder( 0 );
        
        if ( myValue == 0 )
        {
            myBuffer.append( "0" );
        }
        else
        {
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
                    
                }
            }
            else
            {
                myBuffer.append( myValue );
                
                if ( aPrecision != 0 )
                {
                    myBuffer.insert( myBuffer.length()-aPrecision, '.' );
                }
            }
        }
        
        return myBuffer.toString();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsString( CopybookContext    aContext,
                                int        aOffset,
                                String     aValue,
                                int        aDigits,
                                SignType   aSignType,
                                int        aPrecision,
                                int        aScalingPosition )
    {
        encodeAsDouble( aContext,
                        aOffset,
                        Double.parseDouble( aValue ),
                        aDigits,
                        aSignType,
                        aPrecision,
                        aScalingPosition              );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isValidAsDate( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  int               aPrecision,
                                  int               aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public Date decodeAsDate( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeAsDate( CopybookContext   aContext,
                              int               aOffset,
                              Date              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        throw new UnsupportedOperationException( "not yet implemented" );
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
                                         int                           aPrecision,                 
                                         int                           aScalingPosition             )
    {
        boolean myValid = false;
        
        Comparable<? extends Object> myValue;
        if ( aPrecision != 0 || aScalingPosition != 0 )
        {
            myValue = decodeAsBigDecimal( aContext, aOffset, aDigits, aSignType, aPrecision, aScalingPosition );
        }
        else
        {
            myValue = new Long( decodeAsLong( aContext, aOffset, aDigits, aSignType, aPrecision, aScalingPosition ) );
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

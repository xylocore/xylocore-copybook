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


package com.xylocore.commons.data.copybook.runtime.marshallers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface ExternalNumericPICMarshaller
    extends
        PICMarshaller
{
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
     * 
     * @return
     */
    public boolean isBlank( CopybookContext   aContext,
                            int               aOffset,
                            int               aDigits,
                            SignType          aSignType,
                            SignPosition      aSignPosition );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void blank( CopybookContext   aContext,
                       int               aOffset,
                       int               aDigits,
                       SignType          aSignType,
                       SignPosition      aSignPosition );

    
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
     * 
     * @return
     */
    public boolean isValidAsByte( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition );

    
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
     * 
     * @return
     */
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );
    

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
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsChar( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition );
    

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
     * 
     * @return
     */
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );
    

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
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsShort( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aDigits,
                                   SignType          aSignType,
                                   SignPosition      aSignPosition,
                                   int               aPrecision,
                                   int               aScalingPosition );

    
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
     * 
     * @return
     */
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition );
    
    
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
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue,
                               int               aDigits,
                               SignType          aSignType,
                               SignPosition      aSignPosition,
                               int               aPrecision,
                               int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsInteger( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aDigits,
                                     SignType          aSignType,
                                     SignPosition      aSignPosition,
                                     int               aPrecision,
                                     int               aScalingPosition );

    
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
     * 
     * @return
     */
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition );
    
    
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
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue,
                                 int               aDigits,
                                 SignType          aSignType,
                                 SignPosition      aSignPosition,
                                 int               aPrecision,
                                 int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsLong( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition );

    
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
     * 
     * @return
     */
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );
    
 
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
     */
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsFloat( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aDigits,
                                   SignType          aSignType,
                                   SignPosition      aSignPosition,
                                   int               aPrecision,
                                   int               aScalingPosition );

    
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
     * 
     * @return
     */
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition );
    
    
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
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue,
                               int               aDigits,
                               SignType          aSignType,
                               SignPosition      aSignPosition,
                               int               aPrecision,
                               int               aScalingPosition );


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
     * 
     * @return
     */
    public boolean isValidAsDouble( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aDigits,
                                    SignType          aSignType,
                                    SignPosition      aSignPosition,
                                    int               aPrecision,
                                    int               aScalingPosition );

    
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
     * 
     * @return
     */
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition,
                                  int               aPrecision,
                                  int               aScalingPosition );
    
    
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
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue,
                                int               aDigits,
                                SignType          aSignType,
                                SignPosition      aSignPosition,
                                int               aPrecision,
                                int               aScalingPosition );


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
    public boolean isValidAsBigInteger( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aDigits,
                                        SignType                   aSignType,
                                        SignPosition               aSignPosition,
                                        int                        aPrecision,
                                        int                        aScalingPosition,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public BigInteger decodeAsBigInteger( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aDigits,
                                          SignType                   aSignType,
                                          SignPosition               aSignPosition,
                                          int                        aPrecision,
                                          int                        aScalingPosition,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
     * @param       aNullEquivalentStrategies
     */
    public void encodeAsBigInteger( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigInteger                 aValue,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );


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
    public boolean isValidAsBigDecimal( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aDigits,
                                        SignType                   aSignType,
                                        SignPosition               aSignPosition,
                                        int                        aPrecision,
                                        int                        aScalingPosition,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public BigDecimal decodeAsBigDecimal( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aDigits,
                                          SignType                   aSignType,
                                          SignPosition               aSignPosition,
                                          int                        aPrecision,
                                          int                        aScalingPosition,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
     * @param       aNullEquivalentStrategies
     */
    public void encodeAsBigDecimal( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigDecimal                 aValue,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );


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
    public boolean isValidAsString( CopybookContext            aContext,
                                    int                        aOffset,
                                    int                        aDigits,
                                    SignType                   aSignType,
                                    SignPosition               aSignPosition,
                                    int                        aPrecision,
                                    int                        aScalingPosition,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public String decodeAsString( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aDigits,
                                  SignType                   aSignType,
                                  SignPosition               aSignPosition,
                                  int                        aPrecision,
                                  int                        aScalingPosition,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
     * @param       aNullEquivalentStrategies
     */
    public void encodeAsString( CopybookContext            aContext,
                                int                        aOffset,
                                String                     aValue,
                                int                        aDigits,
                                SignType                   aSignType,
                                SignPosition               aSignPosition,
                                int                        aPrecision,
                                int                        aScalingPosition,
                                NullEquivalentStrategy[]   aNullEquivalentStrategies  );
    

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
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isValidAsDate( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aDigits,
                                  SignType                   aSignType,
                                  SignPosition               aSignPosition,
                                  int                        aPrecision,
                                  int                        aScalingPosition,
                                  Converter                  aConverter,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public Date decodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              int                        aDigits,
                              SignType                   aSignType,
                              SignPosition               aSignPosition,
                              int                        aPrecision,
                              int                        aScalingPosition,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
     * @param       aNullEquivalentStrategies
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
                              NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
                                         int                           aScalingPosition             );
}

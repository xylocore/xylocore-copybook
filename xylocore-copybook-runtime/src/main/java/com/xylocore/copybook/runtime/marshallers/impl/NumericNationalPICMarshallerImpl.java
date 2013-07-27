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

import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.converters.Converter;
import com.xylocore.copybook.runtime.marshallers.NumericNationalPICMarshaller;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericNationalPICMarshallerImpl
    extends
        AbstractExternalNumericPICMarshaller
    implements
        NumericNationalPICMarshaller
{
    //
    // Members
    //
    
    
    private static final NumericNationalPICMarshallerImpl instance = new NumericNationalPICMarshallerImpl();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private NumericNationalPICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static NumericNationalPICMarshallerImpl getInstance()
    {
        return instance;
    }
    
    
    @Override
    protected void validateRange( long   aValue,
                                  long   aMinValue,
                                  long   aMaxValue  )
    {
        // TODO - implement
    }
    

    @Override
    protected boolean validateUsingConverter( CopybookContext   aContext,
                                              int               aOffset,
                                              int               aDigits,
                                              SignType          aSignType,
                                              SignPosition      aSignPosition,
                                              int               aPrecision,
                                              int               aScalingPosition,
                                              Converter         aConverter        )
    {
        return aConverter.isNumericNationalValid( this,
                                                  aContext,
                                                  aOffset,
                                                  aDigits,
                                                  aSignType,
                                                  aSignPosition,
                                                  aPrecision,
                                                  aScalingPosition );
    }
    

    @Override
    protected Object decodeUsingConverter( CopybookContext   aContext,
                                           int               aOffset,
                                           int               aDigits,
                                           SignType          aSignType,
                                           SignPosition      aSignPosition,
                                           int               aPrecision,
                                           int               aScalingPosition,
                                           Converter         aConverter        )
    {
        return aConverter.decodeNumericNational( this,
                                                 aContext,
                                                 aOffset,
                                                 aDigits,
                                                 aSignType,
                                                 aSignPosition,
                                                 aPrecision,
                                                 aScalingPosition );
    }
    
    
    @Override
    protected void encodeUsingConverter( CopybookContext   aContext,
                                         int               aOffset,
                                         Object            aValue,
                                         int               aDigits,
                                         SignType          aSignType,
                                         SignPosition      aSignPosition,
                                         int               aPrecision,
                                         int               aScalingPosition,
                                         Converter         aConverter        )
    {
            aConverter.encodeNumericNational( this,
                                              aContext,
                                              aOffset,
                                              aValue,
                                              aDigits,
                                              aSignType,
                                              aSignPosition,
                                              aPrecision,
                                              aScalingPosition );
    }
    
    
    @Override
    protected boolean checkUsingNullEquivalent( CopybookContext          aContext,
                                                int                      aOffset,
                                                int                      aDigits,
                                                SignType                 aSignType,
                                                SignPosition             aSignPosition,
                                                int                      aPrecision,
                                                int                      aScalingPosition,
                                                NullEquivalentStrategy   aNullEquivalentStrategy )
    {
        return aNullEquivalentStrategy.isNumericNationalNull( this,
                                                              aContext,
                                                              aOffset,
                                                              aDigits,
                                                              aSignType,
                                                              aSignPosition,
                                                              aPrecision,
                                                              aScalingPosition );
    }
    
    
    @Override
    protected void encodeUsingNullEquivalent( CopybookContext          aContext,
                                              int                      aOffset,
                                              int                      aDigits,
                                              SignType                 aSignType,
                                              SignPosition             aSignPosition,
                                              int                      aPrecision,
                                              int                      aScalingPosition,
                                              NullEquivalentStrategy   aNullEquivalentStrategy )
    {
        aNullEquivalentStrategy.setNumericNationalNull( this,
                                                        aContext,
                                                        aOffset,
                                                        aDigits,
                                                        aSignType,
                                                        aSignPosition,
                                                        aPrecision,
                                                        aScalingPosition );
    }
    
    
    
    
    //
    // ExternalNumericPICMarshaller interface implementation
    //
    

    @Override
    public boolean isBlank( CopybookContext   aContext,
                            int               aOffset,
                            int               aDigits,
                            SignType          aSignType,
                            SignPosition      aSignPosition )
    {
        return aContext.getDataBehavior()
                       .isNumericNationalBlank( aContext,
                                                aOffset,
                                                aDigits,
                                                aSignType,
                                                aSignPosition );
    }
    
    
    @Override
    public void blank( CopybookContext   aContext,
                       int               aOffset,
                       int               aDigits,
                       SignType          aSignType,
                       SignPosition      aSignPosition )
    {
        aContext.getDataBehavior()
                .blankNumericNational( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aSignPosition );
    }

    
    @Override
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        long myValue =
                aContext.getDataBehavior()
                        .decodeNumericNational( aContext,
                                                aOffset,
                                                aDigits,
                                                aSignType,
                                                aSignPosition );
        
        // TODO: do range check against PIC info
        
        return myValue;
    }
    
 
    @Override
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              SignPosition      aSignPosition,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        aContext.getDataBehavior()
                .encodeNumericNational( aContext,
                                        aOffset,
                                        aValue,
                                        aDigits,
                                        aSignType,
                                        aSignPosition );
    }
}

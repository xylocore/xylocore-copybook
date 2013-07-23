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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#validateRange(long, long, long)
     */
    protected void validateRange( long   aValue,
                                  long   aMinValue,
                                  long   aMaxValue  )
    {
        // TODO - implement
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#validateUsingConverter(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter)
     */
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
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#decodeUsingConverter(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter)
     */
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#encodeUsingConverter(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.converters.Converter)
     */
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#checkUsingNullEquivalent(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy)
     */
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#encodeUsingNullEquivalent(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy)
     */
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
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#isBlank(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller#blank(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition)
     */
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

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#decodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
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
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.impl.AbstractExternalNumericPICMarshaller#encodeAsLong(com.xylocore.commons.data.copybook.runtime.CopybookContext, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
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

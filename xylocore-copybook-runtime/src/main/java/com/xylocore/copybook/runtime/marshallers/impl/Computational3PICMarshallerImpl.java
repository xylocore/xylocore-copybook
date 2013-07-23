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
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.marshallers.Computational3PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational3PICMarshallerImpl
    extends
        AbstractNumericPICMarshaller
    implements
        Computational3PICMarshaller
{
    //
    // Members
    //


    private static final Computational3PICMarshallerImpl instance = new Computational3PICMarshallerImpl();




    //
    // Class implementation
    //


    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational3PICMarshallerImpl()
    {
    }


    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational3PICMarshallerImpl getInstance()
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
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#decodeAsLong(com.xylocore.commons.data.copybook.spi.Context, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public long decodeAsLong( CopybookContext    aContext,
                              int        aOffset,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        long myValue =
                aContext.getDataBehavior()
                        .decodeComp3( aContext, aOffset, aDigits );
        
        // TODO: do range check against PIC info
        
        return myValue;
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.marshallers.AbstractNumericPICMarshaller#encodeAsLong(com.xylocore.commons.data.copybook.spi.Context, int, long, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public void encodeAsLong( CopybookContext    aContext,
                              int        aOffset,
                              long       aValue,
                              int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition )
    {
        aContext.getDataBehavior()
                .encodeComp3( aContext,
                              aOffset,
                              aValue,
                              aDigits,
                              aSignType != SignType.None );
    }
}

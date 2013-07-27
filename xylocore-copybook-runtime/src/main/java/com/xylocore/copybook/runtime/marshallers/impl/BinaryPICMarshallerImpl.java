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
import com.xylocore.copybook.runtime.marshallers.BinaryPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BinaryPICMarshallerImpl
    extends
        AbstractNumericPICMarshaller
    implements
        BinaryPICMarshaller
{
    //
    // Members
    //
    
    
    private static final BinaryPICMarshallerImpl instance = new BinaryPICMarshallerImpl();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private BinaryPICMarshallerImpl()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static BinaryPICMarshallerImpl getInstance()
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
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              SignType          aSignType,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        long myValue =
                aContext.getDataBehavior()
                        .decodeBinary( aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType != SignType.None );
        
        // TODO: do range check against PIC info
        
        return myValue;
    }
    
 
    @Override
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aDigits,
                              SignType          aSignType,
                              int               aPrecision,
                              int               aScalingPosition )
    {
        aContext.getDataBehavior()
                .encodeBinary( aContext,
                               aOffset,
                               aValue,
                               aDigits,
                               aSignType != SignType.None );
    }
}

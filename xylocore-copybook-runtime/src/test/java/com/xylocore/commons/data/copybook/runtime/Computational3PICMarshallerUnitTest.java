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


package com.xylocore.commons.data.copybook.runtime;

import com.xylocore.commons.data.copybook.runtime.marshallers.impl.Computational3PICMarshallerImpl;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational3PICMarshallerUnitTest
    extends
        AbstractPICMarshallerUnitTest
{
    //
    // Members
    //
    

    private static final Computational3PICMarshallerImpl marshaller = Computational3PICMarshallerImpl.getInstance();

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public void testLongMarshalling()
    {
        // Exact digits (odd), no sign, positive value specified
        performTest( 5, SignType.None, 0, 0, 12345, 12345 );

        // Exact digits (odd), no sign, negative value specified
        performTest( 5, SignType.None, 0, 0, -12345, 12345 );

        // Exact digits (odd), sign, positive value specified
        performTest( 5, SignType.NotSeparate, 0, 0, 12345, 12345 );

        // Exact digits (odd), sign, negative value specified
        performTest( 10, SignType.NotSeparate, 0, 0, -12345, -12345 );

        // Exact digits (even), no sign, positive value specified
        performTest( 6, SignType.None, 0, 0, 123456, 123456 );

        // Exact digits (even), no sign, negative value specified
        performTest( 6, SignType.None, 0, 0, -123456, 123456 );

        // Exact digits (even), sign, positive value specified
        performTest( 6, SignType.NotSeparate, 0, 0, 123456, 123456 );

        // Exact digits (even), sign, negative value specified
        performTest( 6, SignType.NotSeparate, 0, 0, -123456, -123456 );

        // Value smaller then digits (odd), no sign, positive value specified
        performTest( 9, SignType.None, 0, 0, 12345, 12345 );

        // Value smaller then digits (odd), no sign, negative value specified
        performTest( 9, SignType.None, 0, 0, -12345, 12345 );

        // Value smaller then digits (odd), sign, positive value specified
        performTest( 9, SignType.NotSeparate, 0, 0, 12345, 12345 );

        // Value smaller then digits (odd), sign, negative value specified
        performTest( 9, SignType.NotSeparate, 0, 0, -12345, -12345 );

        // Value smaller then digits (even), no sign, positive value specified
        performTest( 10, SignType.None, 0, 0, 12345, 12345 );

        // Value smaller then digits (even), no sign, negative value specified
        performTest( 10, SignType.None, 0, 0, -12345, 12345 );

        // Value smaller then digits (even), sign, positive value specified
        performTest( 10, SignType.NotSeparate, 0, 0, 12345, 12345 );

        // Value smaller then digits (even), sign, negative value specified
        performTest( 10, SignType.NotSeparate, 0, 0, -12345, -12345 );
        
//        performTest( 10, SignType.NotSeparate, 0, 2, 12345, 12300 );
    }
    

    /**
     * FILLIN
     * 
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aEncodeValue
     * @param       aDecodeValue
     */
    private void performTest( int        aDigits,
                              SignType   aSignType,
                              int        aPrecision,
                              int        aScalingPosition,
                              long       aEncodeValue,
                              long       aDecodeValue      )
    {
        marshaller.encodeAsLong( context, 0, aEncodeValue, aDigits, aSignType, aPrecision, aScalingPosition );
        assertEquals( marshaller.decodeAsLong( context, 0, aDigits, aSignType, aPrecision, aScalingPosition ), aDecodeValue );
    }
}

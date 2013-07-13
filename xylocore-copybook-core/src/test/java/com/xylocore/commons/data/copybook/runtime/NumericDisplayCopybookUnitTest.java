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

import java.util.Date;

import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.nulleq.ConstantNullEquivalentStrategy;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NumericDisplayCopybookUnitTest
    extends
        AbstractCopybookUnitTest
{
    //
    // Class implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp()
    {
        super.setUp();
    }
    
    
    /**
     * FILLIN
     */
    public void testIsBigDecimalValidNoNullEquivalents()
    {
//        CopybookContext myContext = getContext();

        setupBuffer( "1234567890" );
        assertTrue( isValidAsBigDecimal( 0, 10, SignType.None, null, 0, 0, null ) );
        
//        myContext.setBuffer( "0000000020060701" );
//        myContext.setError( null, null );
//        
//        assertTrue( isValidAsString( 0, 8, EXACT_FLAGS, null ) );
//        assertNull( myContext.getStandardError()               );
//        assertNull( myContext.getPlatformError()               );
//        
//        assertTrue( isValidAsString( 8, 8, EXACT_FLAGS, null ) );
//        assertNull( myContext.getStandardError()               );
//        assertNull( myContext.getPlatformError()               );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#createConstantNullEquivalentStrategy()
     */
    protected ConstantNullEquivalentStrategy createConstantNullEquivalentStrategy()
    {
        return new ConstantNullEquivalentStrategy( DataUsageCategory.NumericDisplay, null, "0" );
    }
    

    /**
     * FILLIN
     *
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
    protected boolean isValidAsBigDecimal( int                        aOffset,
                                           int                        aDigits,
                                           SignType                   aSignType,
                                           SignPosition               aSignPosition,
                                           int                        aPrecision,
                                           int                        aScalingPosition,
                                           NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().isNumericDisplayAsBigDecimalValid( getContext(),
                                                                aOffset,
                                                                aDigits,
                                                                aSignType,
                                                                aSignPosition,
                                                                aPrecision,
                                                                aScalingPosition,
                                                                aNullEquivalentStrategies );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#isValidAsDate(int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    protected boolean isValidAsDate( int                        aOffset,
                                     Converter                  aConverter,
                                     NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().isNumericDisplayAsDateValid( getContext(), aOffset, 8, SignType.None, null, 0, 0, aConverter, aNullEquivalentStrategies );
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#decodeAsDate(int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    protected Date decodeAsDate( int                        aOffset,
                                 Converter                  aConverter,
                                 NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().decodeNumericDisplayAsDate( getContext(), aOffset, 8, SignType.None, null, 0, 0, aConverter, aNullEquivalentStrategies );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#encodeAsDate(int, java.util.Date, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    protected void encodeAsDate( int                        aOffset,
                                 Date                       aValue,
                                 Converter                  aConverter,
                                 NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        getCopybook().encodeNumericDisplayAsDate( getContext(), aOffset, aValue, 8, SignType.None, null, 0, 0, aConverter, aNullEquivalentStrategies );
    }
}

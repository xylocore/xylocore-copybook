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

public class AlphanumericCopybookUnitTest
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#createConstantNullEquivalentStrategy()
     */
    protected ConstantNullEquivalentStrategy createConstantNullEquivalentStrategy()
    {
        return new ConstantNullEquivalentStrategy( DataUsageCategory.Alphanumeric, null, "00000000" );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#isValidAsDate(int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    protected boolean isValidAsDate( int                        aOffset,
                                     Converter                  aConverter,
                                     NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().isAlphanumericAsDateValid( getContext(), aOffset, 8, FLAGS, aConverter, aNullEquivalentStrategies );
    }
    
 
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookUnitTest#decodeAsDate(int, com.xylocore.commons.data.copybook.runtime.converters.Converter, com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy[])
     */
    protected Date decodeAsDate( int                        aOffset,
                                 Converter                  aConverter,
                                 NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().decodeAlphanumericAsDate( getContext(), aOffset, 8, FLAGS, aConverter, aNullEquivalentStrategies );
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
        getCopybook().encodeAlphanumericAsDate( getContext(), aOffset, aValue, 8, FLAGS, aConverter, aNullEquivalentStrategies );
    }
}

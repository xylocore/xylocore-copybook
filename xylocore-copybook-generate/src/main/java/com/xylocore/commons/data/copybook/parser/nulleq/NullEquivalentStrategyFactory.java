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


package com.xylocore.commons.data.copybook.parser.nulleq;

import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NullEquivalentStrategyFactory
{
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aDataElement
     * @param       aNullEquivalentConfig
     * 
     * @return
     */
    public NullEquivalentStrategy create( DataElement            aDataElement,
                                          NullEquivalentConfig   aNullEquivalentConfig )
    {
        assert aDataElement          != null;
        assert aNullEquivalentConfig != null;

        NullEquivalentResolver myResolver = new NullEquivalentResolver();

        return myResolver.resolve( aDataElement, aNullEquivalentConfig );
    }
}

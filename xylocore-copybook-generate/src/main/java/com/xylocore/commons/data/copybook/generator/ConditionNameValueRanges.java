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


package com.xylocore.commons.data.copybook.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xylocore.commons.data.copybook.domain.Level88Element;
import com.xylocore.commons.data.copybook.domain.ValueRange;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConditionNameValueRanges
{
    //
    // Members
    //
    
    
    private Level88Element      conditionNameElement;
    private List<ValueRange>    valueRanges;
    
    
    
    
    //
    // Classes
    //
    

    /**
     * FILLIN
     *
     * @param       aConditionNameElement
     * @param       aValueRanges
     */
    public ConditionNameValueRanges( Level88Element   aConditionNameElement,
                                     ValueRange[]     aValueRanges           )
    {
        assert aConditionNameElement != null;
        assert aValueRanges          != null;
        
        List<ValueRange> myValueRanges = new ArrayList<ValueRange>();
        for ( ValueRange myValueRange : aValueRanges )
        {
            myValueRanges.add( myValueRange );
        }
        
        conditionNameElement = aConditionNameElement;
        valueRanges          = Collections.unmodifiableList( myValueRanges );
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Level88Element getConditionNameElement()
    {
        return conditionNameElement;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public List<ValueRange> getValueRanges()
    {
        return valueRanges;
    }
}

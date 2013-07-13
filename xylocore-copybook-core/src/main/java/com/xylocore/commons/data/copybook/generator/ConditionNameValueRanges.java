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

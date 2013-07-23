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


package com.xylocore.copybook.generator.visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.xylocore.copybook.generator.domain.ConditionNameValueRanges;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Level88Element;
import com.xylocore.copybook.generator.domain.ValueRange;
import com.xylocore.copybook.generator.domain.Visitor;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConditionNameValueMapVariableCollectionVisitor
    extends
        Visitor
{
    //
    // Members
    //
    

    private static final Comparator<ValueRange> valueRangeComparator;
    
    private List<ConditionNameValueRanges>      conditionNameValueRangesList;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        valueRangeComparator =
                new Comparator<ValueRange>()
                    {
                        public int compare( ValueRange   aLhs,
                                            ValueRange   aRhs  )
                        {
                            int myCmp = aLhs.getValue1().compareTo( aRhs.getValue1() );
                            if ( myCmp == 0 )
                            {
                                myCmp = aLhs.getValue2().compareTo( aRhs.getValue2() );
                            }
                            
                            return myCmp;
                        }
                    };
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public ConditionNameValueMapVariableCollectionVisitor()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElements
     * 
     * @return
     */
    public List<ConditionNameValueRanges> collect( List<Element> aElements )
    {
        try
        {
            conditionNameValueRangesList = new ArrayList<ConditionNameValueRanges>();
            
            for ( Element myElement : aElements )
            {
                myElement.accept( this );
            }
            
            return conditionNameValueRangesList;
        }
        finally
        {
            conditionNameValueRangesList = null;
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
    public void visitDataElement( DataElement aElement )
    {
        assert aElement != null;
        
        for ( Level88Element myLevel88Element : aElement.getLevel88Elements() )
        {
            ValueRange[] myValueRanges = compressRange( myLevel88Element.getValueRanges() );
            
            ConditionNameValueRanges myConditionNameValueRanges = new ConditionNameValueRanges( myLevel88Element, myValueRanges );
            
            conditionNameValueRangesList.add( myConditionNameValueRanges );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValueRangeList
     * 
     * @return
     */
    ValueRange[] compressRange( List<ValueRange> aValueRangeList )
    {
        assert aValueRangeList != null && ! aValueRangeList.isEmpty();
        
        ValueRange[] myValueRangeArray = aValueRangeList.toArray( new ValueRange[aValueRangeList.size()] );
        
        Arrays.sort( myValueRangeArray, valueRangeComparator );
        
        List<ValueRange> myCompressedValueRanges = new ArrayList<ValueRange>();
        ValueRange       myLastRange             = myValueRangeArray[0];
        
        for ( int i = 1, ci = myValueRangeArray.length ; i < ci ; i++ )
        {
            ValueRange myCurrentRange = myValueRangeArray[i];
            if ( myLastRange.getValue2().compareTo( myCurrentRange.getValue1() ) >= 0 )
            {
                myLastRange = new ValueRange( myLastRange.getValue1(), myCurrentRange.getValue2() );
            }
            else
            {
                myCompressedValueRanges.add( myLastRange );
                myLastRange = myCurrentRange;
            }
        }
        
        myCompressedValueRanges.add( myLastRange );
        
        return myCompressedValueRanges.toArray( new ValueRange[myCompressedValueRanges.size()] );
    }
}

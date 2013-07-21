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


package com.xylocore.commons.data.copybook.generator.visitor;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Visitor;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ConverterCollectionVisitor
    extends
        Visitor
{
    //
    // Members
    //
    
    
    private static Comparator<List<NullEquivalentStrategy>> nullEquivalentStrategySetComparator;
    
    private Set<Converter>                                  converters;
    private Set<List<NullEquivalentStrategy>>               nullEquivalentStrategySets;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        nullEquivalentStrategySetComparator =
                new Comparator<List<NullEquivalentStrategy>>()
                    {
                        public int compare( List<NullEquivalentStrategy>   aLhs,
                                            List<NullEquivalentStrategy>   aRhs  )
                        {
                            Iterator<NullEquivalentStrategy> myLhsIterator = aLhs.iterator();
                            Iterator<NullEquivalentStrategy> myRhsIterator = aRhs.iterator();
                            int                              myCmp         = 0;

                            while ( myLhsIterator.hasNext() && myRhsIterator.hasNext() && myCmp == 0 )
                            {
                                NullEquivalentStrategy myLhsStrategy = myLhsIterator.next();
                                NullEquivalentStrategy myRhsStrategy = myRhsIterator.next();
                                
                                myCmp = myLhsStrategy.compareTo( myRhsStrategy );
                            }
                            
                            if ( myCmp == 0 )
                            {
                                if ( myLhsIterator.hasNext() )
                                {
                                    myCmp = -1;
                                }
                                else if ( myRhsIterator.hasNext() )
                                {
                                    myCmp = 1;
                                }
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
     * 
     * @param       aElements
     * 
     * @return
     */
    public void collect( List<Element> aElements )
    {
        converters                 = new TreeSet<>();
        nullEquivalentStrategySets = new TreeSet<>( nullEquivalentStrategySetComparator );

        for ( Element myElement : aElements )
        {
            myElement.accept( this );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Set<Converter> getConverters()
    {
        return converters;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Set<List<NullEquivalentStrategy>> getNullEquivalentStrategySets()
    {
        return nullEquivalentStrategySets;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
    public void visitDataElement( DataElement aDataElement )
    {
        assert aDataElement != null;
        
        List<NullEquivalentStrategy> myNullEquivalentStrategies = aDataElement.getNullEquivalentStrategies();
        if ( ! myNullEquivalentStrategies.isEmpty() )
        {
            if ( ! nullEquivalentStrategySets.contains( myNullEquivalentStrategies ) )
            {
                nullEquivalentStrategySets.add( myNullEquivalentStrategies );
            }
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitAccessorMethodInfo(com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    public void visitAccessorMethodInfo( AccessorMethodInfo aMethodInfo )
    {
        assert aMethodInfo != null;
        
        Converter myConverter = aMethodInfo.getConverter();
        if ( myConverter != null )
        {
            // If the same converter has already been used, reuse that converter
            if ( ! converters.contains( myConverter ) )
            {
                converters.add( myConverter );
            }
        }
    }
}

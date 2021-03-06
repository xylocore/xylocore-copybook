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


package com.xylocore.copybook.runtime.nulleq;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.DataUsageCategory;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.ExternalNumericPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BlankNullEquivalentStrategy
    extends
        AbstractNullEquivalentStrategy
{
    //
    // Members
    //
    
    
    private static final BlankNullEquivalentStrategy    instance                        = new BlankNullEquivalentStrategy();
    private static final Set<DataUsageCategory>         supportedDataUsageCategories;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        Set<DataUsageCategory> mySet = new HashSet<>();
        mySet.add( DataUsageCategory.Alphanumeric    );
        mySet.add( DataUsageCategory.NumericDisplay  );
        mySet.add( DataUsageCategory.NumericNational );
        
        supportedDataUsageCategories = Collections.unmodifiableSet( mySet );
    }
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private BlankNullEquivalentStrategy()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static BlankNullEquivalentStrategy getInstance()
    {
        return instance;
    }
    
    
    @Override
    protected Set<DataUsageCategory> getSupportedDataUsageCategories()
    {
        return supportedDataUsageCategories;
    }
    
    
    @Override
    protected boolean isExternalNumericNull( DataUsageCategory              aDataUsageCategory,
                                             ExternalNumericPICMarshaller   aPICMarshaller,
                                             CopybookContext                aContext,
                                             int                            aOffset,
                                             int                            aDigits,
                                             SignType                       aSignType,
                                             SignPosition                   aSignPosition,
                                             int                            aPrecision,
                                             int                            aScalingPosition )
    {
        return aPICMarshaller.isBlank( aContext, aOffset, aDigits, aSignType, aSignPosition );
    }
    

    @Override
    protected void setExternalNumericNull( DataUsageCategory              aDataUsageCategory,
                                           ExternalNumericPICMarshaller   aPICMarshaller,
                                           CopybookContext                aContext,
                                           int                            aOffset,
                                           int                            aDigits,
                                           SignType                       aSignType,
                                           SignPosition                   aSignPosition,
                                           int                            aPrecision,
                                           int                            aScalingPosition )
    {
        aPICMarshaller.blank( aContext, aOffset, aDigits, aSignType, aSignPosition );
    }
    
    
    @Override
    protected boolean isExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                  CopybookContext             aContext,
                                                  int                         aOffset,
                                                  int                         aSize,
                                                  int                         aFlags          )
    {
        return aPICMarshaller.isBlank( aContext, aOffset, aSize, aFlags );
    }
    
    
    @Override
    protected void setExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                CopybookContext             aContext,
                                                int                         aOffset,
                                                int                         aSize,
                                                int                         aFlags          )
    {
        aPICMarshaller.encodeAsString( aContext, aOffset, "", aSize, aFlags, null );
    }
    
    

    
    //
    // NullEquivalentStrategy interface implementation
    //

    
    @Override
    public boolean isDirect()
    {
        return true;
    }
}

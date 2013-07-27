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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

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

public class ConstantNullEquivalentStrategy
    extends
        AbstractNullEquivalentStrategy
{
    //
    // Members
    //

    
    private static final Set<DataUsageCategory> supportedDataUsageCategories;
    
    private DataUsageCategory                   dataUsageCategory;
    private Comparable<?>                       value;
    
    
    
    
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
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aValue
     */
    public ConstantNullEquivalentStrategy( DataUsageCategory   aDataUsageCategory,
                                           String              aValue              )
    {
        this( aDataUsageCategory, null, aValue );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElementDataUsageCategory
     * @param       aDataUsageCategoryOverride
     * @param       aValue
     */
    public ConstantNullEquivalentStrategy( DataUsageCategory   aDataElementDataUsageCategory,
                                           DataUsageCategory   aDataUsageCategoryOverride,
                                           String              aValue                         )
    {
        assert isDataUsageCategorySupported( aDataElementDataUsageCategory );
        assert aDataUsageCategoryOverride == null || isDataUsageCategorySupported( aDataUsageCategoryOverride );
        assert StringUtils.isNotEmpty( aValue );
        
        if ( aDataUsageCategoryOverride != null )
        {
            dataUsageCategory = aDataUsageCategoryOverride;
        }
        else
        {
            dataUsageCategory = aDataElementDataUsageCategory;
        }
        
        if ( dataUsageCategory == DataUsageCategory.Alphanumeric )
        {
            value = aValue;
        }
        else
        {
            try
            {
                value = Long.valueOf( aValue );
            }
            catch ( NumberFormatException myNumberFormatException )
            {
                try
                {
                    value = new BigDecimal( aValue );
                }
                catch ( NumberFormatException myNumberFormatException2 )
                {
                    // TODO: throw an appropriate exception
                    throw new RuntimeException( "constant for null equivalent strategy (" + aValue + ") is not a value number or cannot be accurately represented" );
                }
            }
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataUsageCategory getDataUsageCategory()
    {
        return dataUsageCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Comparable<?> getValue()
    {
        return value;
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
                                             int                            aScalingPosition    )
    {
        boolean myIsNull;
        
        if ( value instanceof Long )
        {
            long myValue =
                    aPICMarshaller.decodeAsLong( aContext,
                                                 aOffset,
                                                 aDigits,
                                                 aSignType,
                                                 aSignPosition,
                                                 aPrecision,
                                                 aScalingPosition );
            myIsNull = ( myValue == ((Long) value).longValue() );
        }
        else
        {
            BigDecimal myValue =
                    aPICMarshaller.decodeAsBigDecimal( aContext,
                                                       aOffset,
                                                       aDigits,
                                                       aSignType,
                                                       aSignPosition,
                                                       aPrecision,
                                                       aScalingPosition,
                                                       null              );
            myIsNull = ( myValue.equals( value ) );
        }
        
        return myIsNull;
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
        if ( value instanceof Long )
        {
            aPICMarshaller.encodeAsLong( aContext,
                                         aOffset,
                                         ((Long) value).longValue(),
                                         aDigits,
                                         aSignType,
                                         aSignPosition,
                                         aPrecision,
                                         aScalingPosition            );
        }
        else
        {
            aPICMarshaller.encodeAsBigDecimal( aContext,
                                               aOffset,
                                               (BigDecimal) value,
                                               aDigits,
                                               aSignType,
                                               aSignPosition,
                                               aPrecision,
                                               aScalingPosition,
                                               null                );
        }
    }
    
    
    @Override
    protected boolean isExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                  CopybookContext             aContext,
                                                  int                         aOffset,
                                                  int                         aSize,
                                                  int                         aFlags          )
    {
        String myValue = aPICMarshaller.decodeAsString( aContext, aOffset, aSize, aFlags, null );
        
        return value.equals( myValue );
    }
    
    
    @Override
    protected void setExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                CopybookContext             aContext,
                                                int                         aOffset,
                                                int                         aSize,
                                                int                         aFlags          )
    {
        aPICMarshaller.encodeAsString( aContext, aOffset, (String) value, aSize, aFlags, null );
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    @Override
    @SuppressWarnings( "unchecked" )
    public int compareTo( NullEquivalentStrategy aOpaqueRhs )
    {
        int myCmp = super.compareTo( aOpaqueRhs );
        if ( myCmp == 0 )
        {
            ConstantNullEquivalentStrategy myRhs = (ConstantNullEquivalentStrategy) aOpaqueRhs;
            myCmp = dataUsageCategory.compareTo( myRhs.dataUsageCategory );
            if ( myCmp == 0 )
            {
                myCmp = ((Comparable<Object>) value).compareTo( myRhs.value );
            }
        }
        
        return myCmp;
    }
}

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


package com.xylocore.copybook.runtime.converters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.DataUsageCategory;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.ExternalNumericPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.InternalFloatingPointPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.InternalNonFloatingPointPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigDecimalConverter
    extends
        AbstractConverter
{
    //
    // Members
    //

    
    private static final Set<DataUsageCategory> supportedDataUsageCategories;
    
    private int                                 scale;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        Set<DataUsageCategory> mySet = new HashSet<DataUsageCategory>();
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
     * @param       aScale
     */
    public BigDecimalConverter( int aScale )
    {
        scale = aScale;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getScale()
    {
        return scale;
    }
    
    
    @Override
    protected Set<DataUsageCategory> getSupportedDataUsageCategories()
    {
        return supportedDataUsageCategories;
    }


    @Override
    protected boolean isExternalNumericValid( DataUsageCategory              aDataUsageCategory, 
                                              ExternalNumericPICMarshaller   aPICMarshaller,
                                              CopybookContext                aContext,
                                              int                            aOffset,
                                              int                            aDigits,
                                              SignType                       aSignType,
                                              SignPosition                   aSignPosition,
                                              int                            aPrecision,
                                              int                            aScalingPosition )
    {
        // TODO: implement
        return super.isExternalNumericValid( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition );
    }
    
    
    @Override
    protected Object decodeExternalNumeric( DataUsageCategory              aDataUsageCategory, 
                                            ExternalNumericPICMarshaller   aPICMarshaller,
                                            CopybookContext                aContext,
                                            int                            aOffset,
                                            int                            aDigits,
                                            SignType                       aSignType,
                                            SignPosition                   aSignPosition,
                                            int                            aPrecision,
                                            int                            aScalingPosition )
    {
        // TODO: implement
        return super.decodeExternalNumeric( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition );
    }
    
    
    @Override
    protected void encodeExternalNumeric( DataUsageCategory              aDataUsageCategory, 
                                          ExternalNumericPICMarshaller   aPICMarshaller,
                                          CopybookContext                aContext,
                                          int                            aOffset,
                                          Object                         aValue,
                                          int                            aDigits,
                                          SignType                       aSignType,
                                          SignPosition                   aSignPosition,
                                          int                            aPrecision,
                                          int                            aScalingPosition )
    {
        // TODO: implement
        super.encodeExternalNumeric( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aValue, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition );
    }
    

    @Override
    protected boolean isInternalFloatingPointValid( DataUsageCategory                    aDataUsageCategory, 
                                                    InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                    CopybookContext                      aContext,
                                                    int                                  aOffset             )
    {
        // TODO: implement
        return super.isInternalFloatingPointValid( aDataUsageCategory, aPICMarshaller, aContext, aOffset );
    }
    

    @Override
    protected Object decodeInternalFloatingPoint( DataUsageCategory                    aDataUsageCategory, 
                                                  InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                  CopybookContext                      aContext,
                                                  int                                  aOffset             )
    {
        // TODO: implement
        return super.decodeInternalFloatingPoint( aDataUsageCategory, aPICMarshaller, aContext, aOffset );
    }
    

    @Override
    protected void encodeInternalFloatingPoint( DataUsageCategory                    aDataUsageCategory, 
                                                InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                CopybookContext                      aContext,
                                                int                                  aOffset,
                                                Object                               aValue              )
    {
        // TODO: implement
        super.encodeInternalFloatingPoint( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aValue );
    }
    

    @Override
    protected boolean isInternalNonFloatingPointValid( DataUsageCategory                       aDataUsageCategory, 
                                                       InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                       CopybookContext                         aContext,
                                                       int                                     aOffset,
                                                       int                                     aDigits,
                                                       SignType                                aSignType,
                                                       int                                     aPrecision,
                                                       int                                     aScalingPosition )
    {
        // TODO: implement
        return super.isInternalNonFloatingPointValid( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aDigits, aSignType, aPrecision, aScalingPosition );
    }
    

    @Override
    protected Object decodeInternalNonFloatingPoint( DataUsageCategory                       aDataUsageCategory, 
                                                     InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                     CopybookContext                         aContext,
                                                     int                                     aOffset,
                                                     int                                     aDigits,
                                                     SignType                                aSignType,
                                                     int                                     aPrecision,
                                                     int                                     aScalingPosition )
    {
        // TODO: implement
        return super.decodeInternalNonFloatingPoint( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aDigits, aSignType, aPrecision, aScalingPosition );
    }
    
    
    @Override
    protected void encodeInternalNonFloatingPoint( DataUsageCategory                       aDataUsageCategory, 
                                                   InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                   CopybookContext                         aContext,
                                                   int                                     aOffset,
                                                   Object                                  aValue,
                                                   int                                     aDigits,
                                                   SignType                                aSignType,
                                                   int                                     aPrecision,
                                                   int                                     aScalingPosition )
    {
        // TODO: implement
        super.encodeInternalNonFloatingPoint( aDataUsageCategory, aPICMarshaller, aContext, aOffset, aValue, aDigits, aSignType, aPrecision, aScalingPosition );
    }
    

    @Override
    protected boolean isExternalAlphanumericValid( AlphanumericPICMarshaller   aPICMarshaller,
                                                   CopybookContext             aContext,
                                                   int                         aOffset,
                                                   int                         aSize,
                                                   int                         aFlags          )
    {
        // TODO: implement
        return super.isExternalAlphanumericValid( aPICMarshaller, aContext, aOffset, aSize, aFlags );
    }
    

    @Override
    protected Object decodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                                 CopybookContext             aContext,
                                                 int                         aOffset,
                                                 int                         aSize,
                                                 int                         aFlags          )
    {
        // TODO: implement
        return super.decodeExternalAlphanumeric( aPICMarshaller, aContext, aOffset, aSize, aFlags );
    }
    

    @Override
    protected void encodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                               CopybookContext             aContext,
                                               int                         aOffset,
                                               Object                      aValue,
                                               int                         aSize,
                                               int                         aFlags          )
    {
        // TODO: implement
        super.encodeExternalAlphanumeric( aPICMarshaller, aContext, aOffset, aValue, aSize, aFlags );
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    @Override
    public int compareTo( Converter aOpaqueRhs )
    {
        int myCmp = super.compareTo( aOpaqueRhs );
        if ( myCmp == 0 )
        {
            BigDecimalConverter myRhs = (BigDecimalConverter) aOpaqueRhs;
            
            myCmp = ( scale < myRhs.scale ) ? -1 : ( scale > myRhs.scale ? 1 : 0 );
        }
        
        return myCmp;
    }
}

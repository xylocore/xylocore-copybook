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

import java.util.Set;

import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.DataUsageCategory;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.BinaryPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.Computational1PICMarshaller;
import com.xylocore.copybook.runtime.marshallers.Computational2PICMarshaller;
import com.xylocore.copybook.runtime.marshallers.Computational3PICMarshaller;
import com.xylocore.copybook.runtime.marshallers.Computational5PICMarshaller;
import com.xylocore.copybook.runtime.marshallers.ExternalNumericPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.InternalFloatingPointPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.InternalNonFloatingPointPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.NumericDisplayPICMarshaller;
import com.xylocore.copybook.runtime.marshallers.NumericNationalPICMarshaller;



/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractNullEquivalentStrategy
    implements
        NullEquivalentStrategy
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected abstract Set<DataUsageCategory> getSupportedDataUsageCategories();
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     */
    protected void ensureDataUsageCategoryIsSupported( DataUsageCategory aDataUsageCategory )
    {
        if ( ! isDataUsageCategorySupported( aDataUsageCategory ) )
        {
            throw new UnsupportedOperationException( "data usage category "  +
                                                     aDataUsageCategory      +
                                                     " is not supported by " +
                                                     getClass().getName()      );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     */
    protected UnsupportedOperationException createNotImplementedException( DataUsageCategory aDataUsageCategory )
    {
        return new UnsupportedOperationException( "data usage category "                               +
                                                  aDataUsageCategory                                   +
                                                  " is supported but has not been implemented within " +
                                                  getClass().getName()                                   );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
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
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
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
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    protected boolean isInternalFloatingPointNull( DataUsageCategory                    aDataUsageCategory,
                                                   InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                   CopybookContext                      aContext,
                                                   int                                  aOffset             )
    {
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    protected void setInternalFloatingPointNull( DataUsageCategory                    aDataUsageCategory,
                                                 InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                 CopybookContext                      aContext,
                                                 int                                  aOffset             )
    {
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    protected boolean isInternalNonFloatingPointNull( DataUsageCategory                       aDataUsageCategory,
                                                      InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                      CopybookContext                         aContext,
                                                      int                                     aOffset,
                                                      int                                     aDigits,
                                                      SignType                                aSignType,
                                                      int                                     aPrecision,
                                                      int                                     aScalingPosition     )
    {
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    protected void setInternalNonFloatingPointNull( DataUsageCategory                       aDataUsageCategory,
                                                    InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                    CopybookContext                         aContext,
                                                    int                                     aOffset,
                                                    int                                     aDigits,
                                                    SignType                                aSignType,
                                                    int                                     aPrecision,
                                                    int                                     aScalingPosition    )
    {
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    protected boolean isExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                  CopybookContext             aContext,
                                                  int                         aOffset,
                                                  int                         aSize,
                                                  int                         aFlags          )
    {
        throw createNotImplementedException( DataUsageCategory.Alphanumeric );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     */
    protected void setExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                CopybookContext             aContext,
                                                int                         aOffset,
                                                int                         aSize,
                                                int                         aFlags          )
    {
        throw createNotImplementedException( DataUsageCategory.Alphanumeric );
    }
    
    
    
    
    //
    // NullEquivalentStrategy interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isDirect()
     */
    public boolean isDirect()
    {
        return false;
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isDataUsageCategorySupported(com.xylocore.commons.data.copybook.runtime.DataUsageCategory)
     */
    public boolean isDataUsageCategorySupported( DataUsageCategory aDataUsageCategory )
    {
        return getSupportedDataUsageCategories().contains( aDataUsageCategory );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isNumericDisplayNull(com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public final boolean isNumericDisplayNull( NumericDisplayPICMarshaller   aPICMarshaller,
                                               CopybookContext               aContext,
                                               int                           aOffset,
                                               int                           aDigits,
                                               SignType                      aSignType,
                                               SignPosition                  aSignPosition,
                                               int                           aPrecision,
                                               int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericDisplay );
        return isExternalNumericNull( DataUsageCategory.NumericDisplay,
                                      aPICMarshaller,
                                      aContext,
                                      aOffset,
                                      aDigits,
                                      aSignType,
                                      aSignPosition,
                                      aPrecision,
                                      aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setNumericDisplayNull(com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public final void setNumericDisplayNull( NumericDisplayPICMarshaller   aPICMarshaller,
                                             CopybookContext               aContext,
                                             int                           aOffset,
                                             int                           aDigits,
                                             SignType                      aSignType,
                                             SignPosition                  aSignPosition,
                                             int                           aPrecision,
                                             int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericDisplay );
        setExternalNumericNull( DataUsageCategory.NumericDisplay,
                                aPICMarshaller,
                                aContext,
                                aOffset,
                                aDigits,
                                aSignType,
                                aSignPosition,
                                aPrecision,
                                aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isNumericNationalNull(com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public final boolean isNumericNationalNull( NumericNationalPICMarshaller   aPICMarshaller,
                                                CopybookContext                aContext,
                                                int                            aOffset,
                                                int                            aDigits,
                                                SignType                       aSignType,
                                                SignPosition                   aSignPosition,
                                                int                            aPrecision,
                                                int                            aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericNational );
        return isExternalNumericNull( DataUsageCategory.NumericNational,
                                      aPICMarshaller,
                                      aContext,
                                      aOffset,
                                      aDigits,
                                      aSignType,
                                      aSignPosition,
                                      aPrecision,
                                      aScalingPosition                   );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setNumericNationalNull(com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public final void setNumericNationalNull( NumericNationalPICMarshaller   aPICMarshaller,
                                              CopybookContext                aContext,
                                              int                            aOffset,
                                              int                            aDigits,
                                              SignType                       aSignType,
                                              SignPosition                   aSignPosition,
                                              int                            aPrecision,
                                              int                            aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericNational );
        setExternalNumericNull( DataUsageCategory.NumericNational,
                                aPICMarshaller,
                                aContext,
                                aOffset,
                                aDigits,
                                aSignType,
                                aSignPosition,
                                aPrecision,
                                aScalingPosition                   );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isBinaryNull(com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public final boolean isBinaryNull( BinaryPICMarshaller   aPICMarshaller,
                                       CopybookContext       aContext,
                                       int                   aOffset,
                                       int                   aDigits,
                                       SignType              aSignType,
                                       int                   aPrecision,
                                       int                   aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Binary );
        return isInternalNonFloatingPointNull( DataUsageCategory.Binary,
                                               aPICMarshaller,
                                               aContext,
                                               aOffset,
                                               aDigits,  
                                               aSignType,
                                               aPrecision,
                                               aScalingPosition          );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public final void setBinaryNull( BinaryPICMarshaller   aPICMarshaller,
                                     CopybookContext       aContext,
                                     int                   aOffset,
                                     int                   aDigits,
                                     SignType              aSignType,
                                     int                   aPrecision,
                                     int                   aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Binary );
        setInternalNonFloatingPointNull( DataUsageCategory.Binary,
                                         aPICMarshaller,
                                         aContext,
                                         aOffset,
                                         aDigits,  
                                         aSignType,
                                         aPrecision,
                                         aScalingPosition          );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isComputational1Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public final boolean isComputational1Null( Computational1PICMarshaller   aPICMarshaller,
                                               CopybookContext               aContext,
                                               int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational1 );
        return isInternalFloatingPointNull( DataUsageCategory.Computational1,
                                            aPICMarshaller,
                                            aContext,
                                            aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setComputational1Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public final void setComputational1Null( Computational1PICMarshaller   aPICMarshaller,
                                             CopybookContext               aContext,
                                             int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational1 );
        setInternalFloatingPointNull( DataUsageCategory.Computational1,
                                      aPICMarshaller,
                                      aContext,
                                      aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isComputational2Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public final boolean isComputational2Null( Computational2PICMarshaller   aPICMarshaller,
                                               CopybookContext               aContext,
                                               int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational2 );
        return isInternalFloatingPointNull( DataUsageCategory.Computational2,
                                            aPICMarshaller,
                                            aContext,
                                            aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setComputational2Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public final void setComputational2Null( Computational2PICMarshaller   aPICMarshaller,
                                             CopybookContext               aContext,
                                             int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational2 );
        setInternalFloatingPointNull( DataUsageCategory.Computational2,
                                      aPICMarshaller,
                                      aContext,
                                      aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isComputational3Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public final boolean isComputational3Null( Computational3PICMarshaller   aPICMarshaller,
                                               CopybookContext               aContext,
                                               int                           aOffset,
                                               int                           aDigits,
                                               SignType                      aSignType,
                                               int                           aPrecision,
                                               int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational3 );
        return isInternalNonFloatingPointNull( DataUsageCategory.Computational3,
                                               aPICMarshaller,
                                               aContext,
                                               aOffset,
                                               aDigits,  
                                               aSignType,
                                               aPrecision,
                                               aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setComputational3Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public final void setComputational3Null( Computational3PICMarshaller   aPICMarshaller,
                                             CopybookContext               aContext,
                                             int                           aOffset,
                                             int                           aDigits,
                                             SignType                      aSignType,
                                             int                           aPrecision,
                                             int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational3 );
        setInternalNonFloatingPointNull( DataUsageCategory.Computational3,
                                         aPICMarshaller,
                                         aContext,
                                         aOffset,
                                         aDigits,  
                                         aSignType,
                                         aPrecision,
                                         aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isComputational5Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public final boolean isComputational5Null( Computational5PICMarshaller   aPICMarshaller,
                                               CopybookContext               aContext,
                                               int                           aOffset,
                                               int                           aDigits,
                                               SignType                      aSignType,
                                               int                           aPrecision,
                                               int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational5 );
        return isInternalNonFloatingPointNull( DataUsageCategory.Computational5,
                                               aPICMarshaller,
                                               aContext,
                                               aOffset,
                                               aDigits,  
                                               aSignType,
                                               aPrecision,
                                               aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setComputational5Null(com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public final void setComputational5Null( Computational5PICMarshaller   aPICMarshaller,
                                             CopybookContext               aContext,
                                             int                           aOffset,
                                             int                           aDigits,
                                             SignType                      aSignType,
                                             int                           aPrecision,
                                             int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational5 );
        setInternalNonFloatingPointNull( DataUsageCategory.Computational5,
                                         aPICMarshaller,
                                         aContext,
                                         aOffset,
                                         aDigits,  
                                         aSignType,
                                         aPrecision,
                                         aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#isAlphanumericNull(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public final boolean isAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                             CopybookContext             aContext,
                                             int                         aOffset,
                                             int                         aSize,
                                             int                         aFlags          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Alphanumeric );
        return isExternalAlphanumericNull( aPICMarshaller,
                                           aContext,
                                           aOffset,
                                           aSize,
                                           aFlags          );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy#setAlphanumericNull(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public final void setAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                           CopybookContext             aContext,
                                           int                         aOffset,
                                           int                         aSize,
                                           int                         aFlags          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Alphanumeric );
        setExternalAlphanumericNull( aPICMarshaller,
                                     aContext,
                                     aOffset,
                                     aSize,
                                     aFlags          );
    }
    
    

    
    //
    // Comparable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(NullEquivalentStrategy)
     */
    public int compareTo( NullEquivalentStrategy aRhs )
    {
        return getClass().getName().compareTo( aRhs.getClass().getName() );
    }
}

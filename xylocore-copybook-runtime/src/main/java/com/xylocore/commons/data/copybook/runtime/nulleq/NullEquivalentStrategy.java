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


package com.xylocore.commons.data.copybook.runtime.nulleq;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface NullEquivalentStrategy
    extends
        Comparable<NullEquivalentStrategy>
{
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isDirect();
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     */
    public boolean isDataUsageCategorySupported( DataUsageCategory aDataUsageCategory );
    
    
    /**
     * FILLIN
     * 
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
    public boolean isNumericDisplayNull( NumericDisplayPICMarshaller   aPICMarshaller,
                                         CopybookContext               aContext,
                                         int                           aOffset,
                                         int                           aDigits,
                                         SignType                      aSignType,
                                         SignPosition                  aSignPosition,
                                         int                           aPrecision,
                                         int                           aScalingPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void setNumericDisplayNull( NumericDisplayPICMarshaller   aPICMarshaller,
                                       CopybookContext               aContext,
                                       int                           aOffset,
                                       int                           aDigits,
                                       SignType                      aSignType,
                                       SignPosition                  aSignPosition,
                                       int                           aPrecision,
                                       int                           aScalingPosition );
    
    
    /**
     * FILLIN
     * 
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
    public boolean isNumericNationalNull( NumericNationalPICMarshaller   aPICMarshaller,
                                          CopybookContext                aContext,
                                          int                            aOffset,
                                          int                            aDigits,
                                          SignType                       aSignType,
                                          SignPosition                   aSignPosition,
                                          int                            aPrecision,
                                          int                            aScalingPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void setNumericNationalNull( NumericNationalPICMarshaller   aPICMarshaller,
                                        CopybookContext                aContext,
                                        int                            aOffset,
                                        int                            aDigits,
                                        SignType                       aSignType,
                                        SignPosition                   aSignPosition,
                                        int                            aPrecision,
                                        int                            aScalingPosition );
    
    
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
     * 
     * @return
     */
    public boolean isBinaryNull( BinaryPICMarshaller   aPICMarshaller,
                                 CopybookContext       aContext,
                                 int                   aOffset,
                                 int                   aDigits,
                                 SignType              aSignType,
                                 int                   aPrecision,
                                 int                   aScalingPosition );
    
    
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
    public void setBinaryNull( BinaryPICMarshaller   aPICMarshaller,
                               CopybookContext       aContext,
                               int                   aOffset,
                               int                   aDigits,
                               SignType              aSignType,
                               int                   aPrecision,
                               int                   aScalingPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isComputational1Null( Computational1PICMarshaller   aPICMarshaller,
                                         CopybookContext               aContext,
                                         int                           aOffset         );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     */
    public void setComputational1Null( Computational1PICMarshaller   aPICMarshaller,
                                       CopybookContext               aContext,
                                       int                           aOffset         );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isComputational2Null( Computational2PICMarshaller   aPICMarshaller,
                                         CopybookContext               aContext,
                                         int                           aOffset         );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     */
    public void setComputational2Null( Computational2PICMarshaller   aPICMarshaller,
                                       CopybookContext               aContext,
                                       int                           aOffset         );
    
    
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
     * 
     * @return
     */
    public boolean isComputational3Null( Computational3PICMarshaller   aPICMarshaller,
                                         CopybookContext               aContext,
                                         int                           aOffset,
                                         int                           aDigits,
                                         SignType                      aSignType,
                                         int                           aPrecision,
                                         int                           aScalingPosition );
    
    
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
    public void setComputational3Null( Computational3PICMarshaller   aPICMarshaller,
                                       CopybookContext               aContext,
                                       int                           aOffset,
                                       int                           aDigits,
                                       SignType                      aSignType,
                                       int                           aPrecision,
                                       int                           aScalingPosition );
    
    
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
     * 
     * @return
     */
    public boolean isComputational5Null( Computational5PICMarshaller   aPICMarshaller,
                                         CopybookContext               aContext,
                                         int                           aOffset,
                                         int                           aDigits,
                                         SignType                      aSignType,
                                         int                           aPrecision,
                                         int                           aScalingPosition );
    
    
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
    public void setComputational5Null( Computational5PICMarshaller   aPICMarshaller,
                                       CopybookContext               aContext,
                                       int                           aOffset,
                                       int                           aDigits,
                                       SignType                      aSignType,
                                       int                           aPrecision,
                                       int                           aScalingPosition );
    
    
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
    public boolean isAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                       CopybookContext             aContext,
                                       int                         aOffset,
                                       int                         aSize,
                                       int                         aFlags          );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     */
    public void setAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                     CopybookContext             aContext,
                                     int                         aOffset,
                                     int                         aSize,
                                     int                         aFlags          );
}

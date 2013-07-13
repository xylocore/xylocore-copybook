package com.xylocore.commons.data.copybook.runtime.converters;

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

public interface Converter
    extends
        Comparable<Converter>
{
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aConverter
     */
    public void emitDeclaration( StringBuilder aBuffer );

    
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
    public boolean isNumericDisplayValid( NumericDisplayPICMarshaller   aPICMarshaller,
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
    public Object decodeNumericDisplay( NumericDisplayPICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplay( NumericDisplayPICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
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
    public boolean isNumericNationalValid( NumericNationalPICMarshaller   aPICMarshaller,
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
     * 
     * @return
     */
    public Object decodeNumericNational( NumericNationalPICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNational( NumericNationalPICMarshaller   aPICMarshaller,
                                       CopybookContext                aContext,
                                       int                            aOffset,
                                       Object                         aValue,
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
    public boolean isBinaryValid( BinaryPICMarshaller   aPICMarshaller,
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
     * 
     * @return
     */
    public Object decodeBinary( BinaryPICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinary( BinaryPICMarshaller   aPICMarshaller,
                              CopybookContext       aContext,
                              int                   aOffset,
                              Object                aValue,
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
    public boolean isComputational1Valid( Computational1PICMarshaller   aPICMarshaller,
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
    public Object decodeComputational1( Computational1PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset         );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComputational1( Computational1PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue          );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isComputational2Valid( Computational2PICMarshaller   aPICMarshaller,
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
    public Object decodeComputational2( Computational2PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset         );
    
    
    /**
     * FILLIN
     * 
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComputational2( Computational2PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue          );
    
    
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
    public boolean isComputational3Valid( Computational3PICMarshaller   aPICMarshaller,
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
    public Object decodeComputational3( Computational3PICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComputational3( Computational3PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
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
    public boolean isComputational5Valid( Computational5PICMarshaller   aPICMarshaller,
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
    public Object decodeComputational5( Computational5PICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComputational5( Computational5PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
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
    public boolean isAlphanumericValid( AlphanumericPICMarshaller   aPICMarshaller,
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
     * 
     * @return
     */
    public Object decodeAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                    CopybookContext             aContext,
                                    int                         aOffset,
                                    Object                      aValue,
                                    int                         aSize,
                                    int                         aFlags          );
}

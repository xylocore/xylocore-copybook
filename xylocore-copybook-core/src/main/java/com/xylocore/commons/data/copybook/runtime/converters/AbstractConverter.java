package com.xylocore.commons.data.copybook.runtime.converters;

import java.util.Set;

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
import com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.InternalFloatingPointPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.InternalNonFloatingPointPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractConverter
    implements
        Converter
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
        throw createNotImplementedException( aDataUsageCategory );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     * @param       aPICMarshaller
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
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
    protected boolean isInternalFloatingPointValid( DataUsageCategory                    aDataUsageCategory, 
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
    protected Object decodeInternalFloatingPoint( DataUsageCategory                    aDataUsageCategory, 
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
     * @param       aValue
     * 
     * @return
     */
    protected void encodeInternalFloatingPoint( DataUsageCategory                    aDataUsageCategory, 
                                                InternalFloatingPointPICMarshaller   aPICMarshaller,
                                                CopybookContext                      aContext,
                                                int                                  aOffset,
                                                Object                               aValue              )
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
    protected boolean isInternalNonFloatingPointValid( DataUsageCategory                       aDataUsageCategory, 
                                                       InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                       CopybookContext                         aContext,
                                                       int                                     aOffset,
                                                       int                                     aDigits,
                                                       SignType                                aSignType,
                                                       int                                     aPrecision,
                                                       int                                     aScalingPosition )
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
    protected Object decodeInternalNonFloatingPoint( DataUsageCategory                       aDataUsageCategory, 
                                                     InternalNonFloatingPointPICMarshaller   aPICMarshaller,
                                                     CopybookContext                         aContext,
                                                     int                                     aOffset,
                                                     int                                     aDigits,
                                                     SignType                                aSignType,
                                                     int                                     aPrecision,
                                                     int                                     aScalingPosition )
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
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
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
    protected boolean isExternalAlphanumericValid( AlphanumericPICMarshaller   aPICMarshaller,
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
     * 
     * @return
     */
    protected Object decodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
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
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    protected void encodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                               CopybookContext             aContext,
                                               int                         aOffset,
                                               Object                      aValue,
                                               int                         aSize,
                                               int                         aFlags          )
    {
        throw createNotImplementedException( DataUsageCategory.Alphanumeric );
    }
    
    
    
    
    //
    // Converter interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isDataUsageCategorySupported(com.xylocore.commons.data.copybook.runtime.DataUsageCategory)
     */
    public boolean isDataUsageCategorySupported( DataUsageCategory aDataUsageCategory )
    {
        return getSupportedDataUsageCategories().contains( aDataUsageCategory );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isNumericDisplayValid(com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isNumericDisplayValid( NumericDisplayPICMarshaller   aPICMarshaller,
                                          CopybookContext               aContext,
                                          int                           aOffset,
                                          int                           aDigits,
                                          SignType                      aSignType,
                                          SignPosition                  aSignPosition,
                                          int                           aPrecision,
                                          int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericDisplay );
        return isExternalNumericValid( DataUsageCategory.NumericDisplay, 
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeNumericDisplay(com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public Object decodeNumericDisplay( NumericDisplayPICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset,
                                        int                           aDigits,
                                        SignType                      aSignType,
                                        SignPosition                  aSignPosition,
                                        int                           aPrecision,
                                        int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericDisplay );
        return decodeExternalNumeric( DataUsageCategory.NumericDisplay, 
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeNumericDisplay(com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeNumericDisplay( NumericDisplayPICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
                                      int                           aDigits,
                                      SignType                      aSignType,
                                      SignPosition                  aSignPosition,
                                      int                           aPrecision,
                                      int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericDisplay );
        encodeExternalNumeric( DataUsageCategory.NumericDisplay, 
                               aPICMarshaller,
                               aContext,
                               aOffset,
                               aValue,
                               aDigits,
                               aSignType,
                               aSignPosition,
                               aPrecision,
                               aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isNumericNationalValid(com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isNumericNationalValid( NumericNationalPICMarshaller   aPICMarshaller,
                                           CopybookContext                aContext,
                                           int                            aOffset,
                                           int                            aDigits,
                                           SignType                       aSignType,
                                           SignPosition                   aSignPosition,
                                           int                            aPrecision,
                                           int                            aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericNational );
        return isExternalNumericValid( DataUsageCategory.NumericNational, 
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeNumericNational(com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public Object decodeNumericNational( NumericNationalPICMarshaller   aPICMarshaller,
                                         CopybookContext                aContext,
                                         int                            aOffset,
                                         int                            aDigits,
                                         SignType                       aSignType,
                                         SignPosition                   aSignPosition,
                                         int                            aPrecision,
                                         int                            aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericNational );
        return decodeExternalNumeric( DataUsageCategory.NumericNational, 
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeNumericNational(com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public void encodeNumericNational( NumericNationalPICMarshaller   aPICMarshaller,
                                       CopybookContext                aContext,
                                       int                            aOffset,
                                       Object                         aValue,
                                       int                            aDigits,
                                       SignType                       aSignType,
                                       SignPosition                   aSignPosition,
                                       int                            aPrecision,
                                       int                            aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.NumericNational );
        encodeExternalNumeric( DataUsageCategory.NumericNational, 
                               aPICMarshaller,
                               aContext,
                               aOffset,
                               aValue,
                               aDigits,
                               aSignType,
                               aSignPosition,
                               aPrecision,
                               aScalingPosition                   );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isBinaryValid(com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public boolean isBinaryValid( BinaryPICMarshaller   aPICMarshaller,
                                  CopybookContext       aContext,
                                  int                   aOffset,
                                  int                   aDigits,
                                  SignType              aSignType,
                                  int                   aPrecision,
                                  int                   aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Binary );
        return isInternalNonFloatingPointValid( DataUsageCategory.Binary,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeBinary(com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public Object decodeBinary( BinaryPICMarshaller   aPICMarshaller,
                                CopybookContext       aContext,
                                int                   aOffset,
                                int                   aDigits,
                                SignType              aSignType,
                                int                   aPrecision,
                                int                   aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Binary );
        return decodeInternalNonFloatingPoint( DataUsageCategory.Binary,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeBinary(com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public void encodeBinary( BinaryPICMarshaller   aPICMarshaller,
                              CopybookContext       aContext,
                              int                   aOffset,
                              Object                aValue,
                              int                   aDigits,
                              SignType              aSignType,
                              int                   aPrecision,
                              int                   aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Binary );
        encodeInternalNonFloatingPoint( DataUsageCategory.Binary,
                                        aPICMarshaller,
                                        aContext,
                                        aOffset,
                                        aValue,
                                        aDigits,
                                        aSignType,
                                        aPrecision,
                                        aScalingPosition          );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isComputational1Valid(com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public boolean isComputational1Valid( Computational1PICMarshaller   aPICMarshaller,
                                          CopybookContext               aContext,
                                          int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational1 );
        return isInternalFloatingPointValid( DataUsageCategory.Computational1,
                                             aPICMarshaller,
                                             aContext,
                                             aOffset                           );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeComputational1(com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public Object decodeComputational1( Computational1PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational1 );
        return decodeInternalFloatingPoint( DataUsageCategory.Computational1,
                                            aPICMarshaller,
                                            aContext,
                                            aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeComputational1(com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object)
     */
    public void encodeComputational1( Computational1PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational1 );
        encodeInternalFloatingPoint( DataUsageCategory.Computational1,
                                     aPICMarshaller,
                                     aContext,
                                     aOffset,
                                     aValue                            );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isComputational2Valid(com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public boolean isComputational2Valid( Computational2PICMarshaller   aPICMarshaller,
                                          CopybookContext               aContext,
                                          int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational2 );
        return isInternalFloatingPointValid( DataUsageCategory.Computational2,
                                             aPICMarshaller,
                                             aContext,
                                             aOffset                           );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeComputational2(com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int)
     */
    public Object decodeComputational2( Computational2PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset         )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational2 );
        return decodeInternalFloatingPoint( DataUsageCategory.Computational2,
                                            aPICMarshaller,
                                            aContext,
                                            aOffset                           );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeComputational2(com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object)
     */
    public void encodeComputational2( Computational2PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational2 );
        encodeInternalFloatingPoint( DataUsageCategory.Computational2,
                                     aPICMarshaller,
                                     aContext,
                                     aOffset,
                                     aValue                            );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isComputational3Valid(com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public boolean isComputational3Valid( Computational3PICMarshaller   aPICMarshaller,
                                          CopybookContext               aContext,
                                          int                           aOffset,
                                          int                           aDigits,
                                          SignType                      aSignType,
                                          int                           aPrecision,
                                          int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational3 );
        return isInternalNonFloatingPointValid( DataUsageCategory.Computational3,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeComputational3(com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public Object decodeComputational3( Computational3PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset,
                                        int                           aDigits,
                                        SignType                      aSignType,
                                        int                           aPrecision,
                                        int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational3 );
        return decodeInternalNonFloatingPoint( DataUsageCategory.Computational3,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeComputational3(com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public void encodeComputational3( Computational3PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
                                      int                           aDigits,
                                      SignType                      aSignType,
                                      int                           aPrecision,
                                      int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational3 );
        encodeInternalNonFloatingPoint( DataUsageCategory.Computational3,
                                        aPICMarshaller,
                                        aContext,
                                        aOffset,
                                        aValue,
                                        aDigits,
                                        aSignType,
                                        aPrecision,
                                        aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isComputational5Valid(com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public boolean isComputational5Valid( Computational5PICMarshaller   aPICMarshaller,
                                          CopybookContext               aContext,
                                          int                           aOffset,
                                          int                           aDigits,
                                          SignType                      aSignType,
                                          int                           aPrecision,
                                          int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational5 );
        return isInternalNonFloatingPointValid( DataUsageCategory.Computational5,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeComputational5(com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public Object decodeComputational5( Computational5PICMarshaller   aPICMarshaller,
                                        CopybookContext               aContext,
                                        int                           aOffset,
                                        int                           aDigits,
                                        SignType                      aSignType,
                                        int                           aPrecision,
                                        int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational5 );
        return decodeInternalNonFloatingPoint( DataUsageCategory.Computational5,
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
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeComputational5(com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, int, int)
     */
    public void encodeComputational5( Computational5PICMarshaller   aPICMarshaller,
                                      CopybookContext               aContext,
                                      int                           aOffset,
                                      Object                        aValue,
                                      int                           aDigits,
                                      SignType                      aSignType,
                                      int                           aPrecision,
                                      int                           aScalingPosition )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Computational5 );
        encodeInternalNonFloatingPoint( DataUsageCategory.Computational5,
                                        aPICMarshaller,
                                        aContext,
                                        aOffset,
                                        aValue,
                                        aDigits,
                                        aSignType,
                                        aPrecision,
                                        aScalingPosition                  );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#isAlphanumericValid(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public boolean isAlphanumericValid( AlphanumericPICMarshaller   aPICMarshaller,
                                        CopybookContext             aContext,
                                        int                         aOffset,
                                        int                         aSize,
                                        int                         aFlags          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Alphanumeric );
        return isExternalAlphanumericValid( aPICMarshaller,
                                            aContext,
                                            aOffset,
                                            aSize,
                                            aFlags          );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#decodeAlphanumeric(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    public Object decodeAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                      CopybookContext             aContext,
                                      int                         aOffset,
                                      int                         aSize,
                                      int                         aFlags          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Alphanumeric );
        return decodeExternalAlphanumeric( aPICMarshaller,
                                           aContext,
                                           aOffset,
                                           aSize,
                                           aFlags          );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#encodeAlphanumeric(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, int)
     */
    public void encodeAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                    CopybookContext             aContext,
                                    int                         aOffset,
                                    Object                      aValue,
                                    int                         aSize,
                                    int                         aFlags          )
    {
        ensureDataUsageCategoryIsSupported( DataUsageCategory.Alphanumeric );
        encodeExternalAlphanumeric( aPICMarshaller,
                                    aContext,
                                    aOffset,
                                    aValue,
                                    aSize,
                                    aFlags          );
    }
    

    
    
    //
    // Comparable interface implementation
    //
    
    
    public int compareTo( Converter aRhs )
    {
        return getClass().getName().compareTo( aRhs.getClass().getName() );
    }
}

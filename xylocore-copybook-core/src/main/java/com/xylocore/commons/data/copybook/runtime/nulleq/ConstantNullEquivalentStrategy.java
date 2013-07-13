package com.xylocore.commons.data.copybook.runtime.nulleq;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller;


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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#getSupportedDataUsageCategories()
     */
    protected Set<DataUsageCategory> getSupportedDataUsageCategories()
    {
        return supportedDataUsageCategories;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#isExternalNumericNull(com.xylocore.commons.data.copybook.runtime.DataUsageCategory, com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
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
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#setExternalNumericNull(com.xylocore.commons.data.copybook.runtime.DataUsageCategory, com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#isExternalAlphanumericNull(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    protected boolean isExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                  CopybookContext             aContext,
                                                  int                         aOffset,
                                                  int                         aSize,
                                                  int                         aFlags          )
    {
        String myValue = aPICMarshaller.decodeAsString( aContext, aOffset, aSize, aFlags, null );
        
        return value.equals( myValue );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#setExternalAlphanumericNull(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    protected void setExternalAlphanumericNull( AlphanumericPICMarshaller   aPICMarshaller,
                                                CopybookContext             aContext,
                                                int                         aOffset,
                                                int                         aSize,
                                                int                         aFlags          )
    {
        aPICMarshaller.encodeAsString( aContext, aOffset, (String) value, aSize, aFlags, null );
    }
    
    

    
    //
    // NullEquivalentStrategy interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.NullEquivalentStrategy#emitDeclaration(com.xylocore.commons.util.XyStringBuffer)
     */
    public void emitDeclaration( StringBuilder aBuffer )
    {
        aBuffer.append( "new "                                 )
               .append( getClass().getName()                   )
               .append( "( "                                   )
               .append( dataUsageCategory.getClass().getName() )
               .append( "."                                    )
               .append( dataUsageCategory.toString()           )
               .append( ", \""                                 )
               .append( value.toString()                       )
               .append( "\" )"                                 )
               ;
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#compareTo(java.lang.Object)
     */
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
                myCmp = ((Comparable<Object>) value).compareTo( ((Comparable<Object>) myRhs.value) );
            }
        }
        
        return myCmp;
    }
}

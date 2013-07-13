package com.xylocore.commons.data.copybook.runtime.nulleq;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
                                             int                            aScalingPosition )
    {
        return aPICMarshaller.isBlank( aContext, aOffset, aDigits, aSignType, aSignPosition );
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
        aPICMarshaller.blank( aContext, aOffset, aDigits, aSignType, aSignPosition );
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
        return aPICMarshaller.isBlank( aContext, aOffset, aSize, aFlags );
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
        aPICMarshaller.encodeAsString( aContext, aOffset, "", aSize, aFlags, null );
    }
    
    

    
    //
    // NullEquivalentStrategy interface implementation
    //

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#isDirect()
     */
    public boolean isDirect()
    {
        return true;
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.NullEquivalentStrategy#emitDeclaration(com.xylocore.commons.util.XyStringBuffer)
     */
    public void emitDeclaration( StringBuilder aBuffer )
    {
        aBuffer.append( getClass().getName() )
               .append( ".getInstance()"     )
               ;
    }
}

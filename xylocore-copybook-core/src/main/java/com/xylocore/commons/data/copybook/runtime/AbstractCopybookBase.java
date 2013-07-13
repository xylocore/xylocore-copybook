package com.xylocore.commons.data.copybook.runtime;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.BinaryPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational1PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational2PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational3PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.Computational5PICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericDisplayPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.NumericNationalPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.AlphanumericPICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.BinaryPICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.Computational1PICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.Computational2PICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.Computational3PICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.Computational5PICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.NumericDisplayPICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.marshallers.impl.NumericNationalPICMarshallerImpl;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;
import com.xylocore.commons.data.copybook.spi.PlatformDataBehavior;
import com.xylocore.commons.data.copybook.spi.PlatformDataBehaviorFactory;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractCopybookBase
{
    //
    // Members
    //
    
    
    protected static final BinaryPICMarshaller          binaryMarshaller            = BinaryPICMarshallerImpl.getInstance();
    protected static final Computational1PICMarshaller  comp1Marshaller             = Computational1PICMarshallerImpl.getInstance();
    protected static final Computational2PICMarshaller  comp2Marshaller             = Computational2PICMarshallerImpl.getInstance();
    protected static final Computational3PICMarshaller  comp3Marshaller             = Computational3PICMarshallerImpl.getInstance();
    protected static final Computational5PICMarshaller  comp5Marshaller             = Computational5PICMarshallerImpl.getInstance();
    protected static final AlphanumericPICMarshaller    alphanumericMarshaller      = AlphanumericPICMarshallerImpl.getInstance();
    protected static final NumericDisplayPICMarshaller  numericDisplayMarshaller    = NumericDisplayPICMarshallerImpl.getInstance();
    protected static final NumericNationalPICMarshaller numericNationalMarshaller   = NumericNationalPICMarshallerImpl.getInstance();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public abstract int getMaximumRecordLength();

    
    /**
     * FILLIN
     * 
     * @param       aBehaviorName
     * 
     * @return
     */
    public CopybookContext createContext( String aBehaviorName )
    {
        if ( StringUtils.isEmpty( aBehaviorName ) )
        {
            throw new IllegalArgumentException( "the behavior name cannot be null or empty" );
        }

        ByteBuffer           myBuffer   = ByteBuffer.allocate( getMaximumRecordLength() );
        PlatformDataBehavior myBehavior = PlatformDataBehaviorFactory.forName( aBehaviorName );

        CopybookContext myContext = new CopybookContext();
        myContext.setBuffer      ( myBuffer   );
        myContext.setDataBehavior( myBehavior );

        return myContext;
    }
    

    /**
     * FILLIN
     * 
     * @param       aContext
     */
    public void clearBuffer( CopybookContext aContext )
    {
        aContext.getBuffer().clear();
        encodeAlphanumericAsString( aContext,
                                    0,
                                    "",
                                    getMaximumRecordLength(),
                                    AlphanumericPICFlags.LEFT_JUSTIFY    |
                                    AlphanumericPICFlags.DISPLAY         |
                                    AlphanumericPICFlags.TRIM_WHITESPACE,
                                    null                                   );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aMappings
     * @param       aConditionName
     * @param       aConstantValues
     */
    protected static void addConditionNameValueMapping( Map<String,ConstantValue[]>   aMappings,
                                                        String                        aConditionName,
                                                        ConstantValue[]               aConstantValues )
    {
        assert aMappings != null;
        assert StringUtils.isNotBlank( aConditionName );
        assert aConstantValues != null && aConstantValues.length != 0;
        
        aMappings.put( aConditionName, aConstantValues );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     */
    protected void checkForError( CopybookContext aContext )
    {
        
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     */
    public abstract void encodeAlphanumericAsString( CopybookContext            aContext,
                                                     int                        aOffset,
                                                     String                     aValue,
                                                     int                        aSize,
                                                     int                        aFlags,
                                                     NullEquivalentStrategy[]   aNullEquivalentStrategies );
}

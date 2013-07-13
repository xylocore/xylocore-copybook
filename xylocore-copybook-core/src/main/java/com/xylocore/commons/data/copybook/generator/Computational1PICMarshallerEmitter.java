package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.NumericValue;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ZeroValue;
import com.xylocore.commons.data.copybook.runtime.DataType;



/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational1PICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational1PICMarshallerEmitter instance = new Computational1PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational1PICMarshallerEmitter()
    {
        super( "Comp1" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational1PICMarshallerEmitter getInstance()
    {
        return instance;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#getDelegateIsValidMethodName(com.xylocore.commons.data.copybook.runtime.DataType)
     */
    protected String getDelegateIsValidMethodName( DataType aDataType )
    {
        return "isValid";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableConditionNameMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableConditionNameMethodParts( StringBuilder   aBuffer,
                                                         Element         aConditionalVariableElement )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aValue
     * 
     * @return
     */
    protected Comparable<?> convertValue( Element   aElement,
                                          Value     aValue    )
    {
        float myFloat;
        
        if ( aValue instanceof NumericValue )
        {
            myFloat = ((NumericValue) aValue).getValue().floatValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myFloat = 0.0f;
        }
        else
        {
            throw new IllegalStateException();
        }
        
        return new Float( myFloat );
    }
}

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

public class Computational2PICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational2PICMarshallerEmitter instance = new Computational2PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational2PICMarshallerEmitter()
    {
        super( "Comp2" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational2PICMarshallerEmitter getInstance()
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
        double myDouble;
        
        if ( aValue instanceof NumericValue )
        {
            myDouble = ((NumericValue) aValue).getValue().doubleValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myDouble = 0.0d;
        }
        else
        {
            throw new IllegalStateException();
        }
        
        return new Double( myDouble );
    }
}

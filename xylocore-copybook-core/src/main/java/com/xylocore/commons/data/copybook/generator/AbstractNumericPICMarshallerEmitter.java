package com.xylocore.commons.data.copybook.generator;

import java.math.BigDecimal;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.NumericValue;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ZeroValue;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractNumericPICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Class implementation
    //
    
    
    /**
     * Constructor.
     */
    public AbstractNumericPICMarshallerEmitter( String aNativeType )
    {
        super( aNativeType );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableCommonMethodParts( StringBuilder   aBuffer,
                                                  Element         aElement )
    {
        assert aBuffer  != null;
        assert aElement != null;
        
        aBuffer.append( ", "                              )
               .append( aElement.getDigits()              )
               .append( ", SignType."                     )
               .append( aElement.getSignType().toString() )
               .append( ", "                              )
               .append( aElement.getPrecision()           )
               .append( ", "                              )
               .append( aElement.getScalingPosition()     )
               ;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableMethodParts( StringBuilder        aBuffer,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableCommonMethodParts( aBuffer, aElement );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableIsValidMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableIsValidMethodParts( StringBuilder        aBuffer,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        emitVariableMethodParts( aBuffer, aElement, aAccessorMethodInfo );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableConditionNameMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableConditionNameMethodParts( StringBuilder   aBuffer,
                                                         Element         aConditionalVariableElement )
    {
        emitVariableCommonMethodParts( aBuffer, aConditionalVariableElement );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#convertValue(com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.Value)
     */
    protected Comparable<?> convertValue( Element   aElement,
                                          Value     aValue    )
    {
        BigDecimal myConvertedValue;
        
        if ( aValue instanceof NumericValue )
        {
            myConvertedValue = ((NumericValue) aValue).getValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myConvertedValue = BigDecimal.valueOf( 0 );
        }
        else
        {
            throw new IllegalStateException();
        }

        return myConvertedValue;
    }
}

package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.runtime.ConstantCharacterString;
import com.xylocore.commons.data.copybook.runtime.ConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class StringConstantValueEmitter
    extends
        AbstractConstantValueEmitter
{
    //
    // Class implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractConstantValueEmitter#emitInstantiationParameters(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.runtime.ConstantValue)
     */
    protected void emitInstantiationParameters( StringBuilder   aBuffer,
                                                ConstantValue   aValue   )
    {
        if ( aValue.getValue1() instanceof ConstantCharacterString )
        {
            ConstantCharacterString myConstantCharacterString = (ConstantCharacterString) aValue.getValue1();
            
            aBuffer.append( " '" );
            
            appendEscapedChar( aBuffer, myConstantCharacterString.getConstantChar() );

            aBuffer.append( "', "                              )
                   .append( myConstantCharacterString.length() )
                   .append( " "                                )
                   ;
        }
        else
        {
            super.emitInstantiationParameters( aBuffer, aValue );
        }
    }
}

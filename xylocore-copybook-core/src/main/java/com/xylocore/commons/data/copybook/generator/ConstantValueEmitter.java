package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public interface ConstantValueEmitter
{
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aValue
     */
    public void emit( StringBuilder   aBuffer,
                      ConstantValue   aValue   );
}

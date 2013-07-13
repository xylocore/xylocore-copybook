package com.xylocore.commons.data.copybook.parser.nulleq;

import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NullEquivalentStrategyFactory
{
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aDataElement
     * @param       aNullEquivalentConfig
     * 
     * @return
     */
    public NullEquivalentStrategy create( DataElement            aDataElement,
                                          NullEquivalentConfig   aNullEquivalentConfig )
    {
        assert aDataElement          != null;
        assert aNullEquivalentConfig != null;

        NullEquivalentResolver myResolver = new NullEquivalentResolver();

        return myResolver.resolve( aDataElement, aNullEquivalentConfig );
    }
}

package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface ConfigEntityDescribable
{
    /**
     * FILLIN
     * 
     * @param       aLabelValueMap
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap );
    
    
    /**
     * FILLIN
     * 
     * @param       aCollectionsMap
     */
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap );
}

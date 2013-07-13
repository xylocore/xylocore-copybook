package com.xylocore.commons.data.copybook.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface ConfigVisitable
{
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    public void accept( ConfigVisitor aVisitor );
}

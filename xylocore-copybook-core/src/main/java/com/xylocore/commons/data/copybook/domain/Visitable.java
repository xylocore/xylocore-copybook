package com.xylocore.commons.data.copybook.domain;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface Visitable
{
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    public void accept( Visitor aVisitor );
}

package com.xylocore.commons.data.copybook.domain;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookDomainException
    extends
        RuntimeException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = 6415907529287572838L;
    
    
    
    
    //
    // Class implementationm
    //


    /**
     * FILLIN
     */
    public CopybookDomainException()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     */
    public CopybookDomainException( String aMessage )
    {
        super( aMessage );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCause
     */
    public CopybookDomainException( Throwable aCause )
    {
        super( aCause );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aCause
     */
    public CopybookDomainException( String      aMessage,
                                    Throwable   aCause    )
    {
        super( aMessage, aCause );
    }
}

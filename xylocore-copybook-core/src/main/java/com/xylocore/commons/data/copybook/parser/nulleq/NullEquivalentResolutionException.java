package com.xylocore.commons.data.copybook.parser.nulleq;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class NullEquivalentResolutionException
    extends
        RuntimeException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = 5479281141710286516L;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public NullEquivalentResolutionException()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     */
    public NullEquivalentResolutionException( String aMessage )
    {
        super( aMessage );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCause
     */
    public NullEquivalentResolutionException( Throwable aCause )
    {
        super( aCause );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aCause
     */
    public NullEquivalentResolutionException( String      aMessage,
                                              Throwable   aCause    )
    {
        super( aMessage, aCause );
    }
}

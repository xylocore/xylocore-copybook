package com.xylocore.commons.data.copybook.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class EnvironmentConfigurationException
    extends
        RuntimeException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = -3357235490966615464L;


    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public EnvironmentConfigurationException()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     */
    public EnvironmentConfigurationException( String aMessage )
    {
        super( aMessage );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCause
     */
    public EnvironmentConfigurationException( Throwable aCause )
    {
        super( aCause );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aCause
     */
    public EnvironmentConfigurationException( String      aMessage,
                                              Throwable   aCause    )
    {
        super( aMessage, aCause );
    }
}

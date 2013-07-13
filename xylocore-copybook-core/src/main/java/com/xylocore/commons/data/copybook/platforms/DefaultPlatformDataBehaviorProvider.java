package com.xylocore.commons.data.copybook.platforms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xylocore.commons.data.copybook.platforms.ascii.AsciiPlatformDataBehavior;
import com.xylocore.commons.data.copybook.platforms.ibm.IBMMainframePlatformDataBehavior;
import com.xylocore.commons.data.copybook.spi.PlatformDataBehavior;
import com.xylocore.commons.data.copybook.spi.PlatformDataBehaviorProvider;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DefaultPlatformDataBehaviorProvider
    implements
        PlatformDataBehaviorProvider
{
    //
    // Members
    //
    
    
    private static final DefaultPlatformDataBehaviorProvider    instance            = new DefaultPlatformDataBehaviorProvider();
    private final List<PlatformDataBehavior>                     defaultBehaviors;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    private DefaultPlatformDataBehaviorProvider()
    {
        List<PlatformDataBehavior> myList = new ArrayList<PlatformDataBehavior>();
        myList.add( new IBMMainframePlatformDataBehavior() );
        myList.add( new AsciiPlatformDataBehavior()        );
        defaultBehaviors = Collections.unmodifiableList( myList );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static DefaultPlatformDataBehaviorProvider getInstance()
    {
        return instance;
    }
    
    

    
    //
    // PlatformDataBehaviorProvider interface implementation
    //


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.spi.PlatformDataBehaviorProvider#getBehaviors()
     */
    public List<PlatformDataBehavior> getBehaviors()
    {
        return defaultBehaviors;
    }
}

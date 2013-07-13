//
//   Copyright 2013 The Palantir Corporation
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//


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

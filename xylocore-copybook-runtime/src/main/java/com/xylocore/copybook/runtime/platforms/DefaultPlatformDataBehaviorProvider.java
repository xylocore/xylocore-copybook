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


package com.xylocore.copybook.runtime.platforms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xylocore.copybook.runtime.platforms.ascii.AsciiPlatformDataBehavior;
import com.xylocore.copybook.runtime.platforms.ibm.IBMMainframePlatformDataBehavior;
import com.xylocore.copybook.runtime.spi.PlatformDataBehavior;
import com.xylocore.copybook.runtime.spi.PlatformDataBehaviorProvider;


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
    
    private static final List<PlatformDataBehavior>             defaultBehaviors;
    
    
    
    
    //
    // Static initialization
    //
    
    
    static
    {
        List<PlatformDataBehavior> myList = new ArrayList<>();
        myList.add( new IBMMainframePlatformDataBehavior() );
        myList.add( new AsciiPlatformDataBehavior()        );
        
        defaultBehaviors = Collections.unmodifiableList( myList );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private DefaultPlatformDataBehaviorProvider()
    {
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


    @Override
    public List<PlatformDataBehavior> getBehaviors()
    {
        return defaultBehaviors;
    }
}

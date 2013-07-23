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


package com.xylocore.copybook.runtime.spi;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import com.xylocore.copybook.runtime.platforms.DefaultPlatformDataBehaviorProvider;


/**
 * FILLIN
 *
 * @author      Eric Medley
 */

public class PlatformDataBehaviorFactory
{
    //
    // Members
    //


    private static final Class<PlatformDataBehaviorFactory> thisClass       = PlatformDataBehaviorFactory.class;
    private static Map<String,PlatformDataBehavior>         behaviorMap;




    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    private PlatformDataBehaviorFactory()
    {
    }
    
    
    /**
     * FILLIN
     */
    private static void buildBehaviorMap()
    {
        SecurityManager mySecurityManager = System.getSecurityManager();
        if ( mySecurityManager != null )
        {
            mySecurityManager.checkPermission( new RuntimePermission( thisClass.getName() ) );
        }

        Map<String,PlatformDataBehavior> myBehaviorMap = new TreeMap<String,PlatformDataBehavior>( String.CASE_INSENSITIVE_ORDER );
        
        for ( PlatformDataBehavior myBehavior : DefaultPlatformDataBehaviorProvider.getInstance().getBehaviors() )
        {
            String myBehaviorName = myBehavior.getBehaviorName();

            if ( ! myBehaviorMap.containsKey( myBehaviorName ) )
            {
                myBehaviorMap.put( myBehaviorName, myBehavior );
            }
        }
        
        behaviorMap = Collections.unmodifiableMap( myBehaviorMap );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBehaviorName
     * 
     * @return
     */
    public static synchronized PlatformDataBehavior forName( String aBehaviorName )
    {
        if ( aBehaviorName == null || aBehaviorName.length() == 0 )
        {
            throw new IllegalArgumentException( "aBehaviorName" );
        }
        
        if ( behaviorMap == null )
        {
            buildBehaviorMap();
        }
        
        return behaviorMap.get( aBehaviorName );
    }
}

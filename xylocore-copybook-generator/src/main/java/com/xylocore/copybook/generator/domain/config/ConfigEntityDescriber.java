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


package com.xylocore.copybook.generator.domain.config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.xylocore.commons.util.BufferEmitter;
import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ConfigEntityDescriber
{
    //
    // Members
    //
    
    
    private BufferEmitter emitter;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public ConfigEntityDescriber()
    {
        this( 0, 4 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aIndentLevel
     * @param       aIndentWidth
     */
    public ConfigEntityDescriber( int   aIndentLevel,
                                  int   aIndentWidth  )
    {
        emitter = new BufferEmitter( aIndentLevel, aIndentWidth );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     * 
     * @return
     */
    public static String simpleDescribe( ConfigEntityDescribable aDescribable )
    {
        ConfigEntityDescriber myDescriber = new ConfigEntityDescriber();
        return myDescriber.describe( aDescribable );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     * 
     * @return
     */
    public String describe( ConfigEntityDescribable aDescribable )
    {
        if ( aDescribable == null )
        {
            throw new IllegalArgumentException( "a describable object must be specified" );
        }
        
        try
        {
            describeImpl( aDescribable );
            
            return emitter.getBuffer().toString();
        }
        finally
        {
            emitter.clear();
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aDescribable
     * @param       aBuffer
     */
    public void describe( ConfigEntityDescribable   aDescribable,
                          StringBuilder             aBuffer       )
    {
        if ( aDescribable == null )
        {
            throw new IllegalArgumentException( "a describable object must be specified" );
        }
        if ( aBuffer == null )
        {
            throw new IllegalArgumentException( "a buffer must be specified" );
        }

        try
        {
            describeImpl( aDescribable );
            
            aBuffer.append( emitter.getBuffer() );
        }
        finally
        {
            emitter.clear();
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aDescribable
     */
    private void describeImpl( ConfigEntityDescribable aDescribable )
    {
        assert aDescribable != null;
        
        String myClassName = getDescribableClassName( aDescribable );

        emitter.line( myClassName )
               .line( "["         )
               ;
        
        describeElements   ( aDescribable );
        describeCollections( aDescribable );

        emitter.line( "]" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     * 
     * @return
     */
    private String getDescribableClassName( ConfigEntityDescribable aDescribable )
    {
        assert aDescribable != null;
        
        String myClassName = aDescribable.getClass().getName();
        
        return myClassName.substring( myClassName.lastIndexOf( '.' )+1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     */
    private void describeElements( ConfigEntityDescribable aDescribable )
    {
        assert aDescribable != null;
        
        Map<String,String> myLabelValuePairs = new LinkedHashMap<>();
        aDescribable.buildDescribableLabelValuePairs( myLabelValuePairs );
        
        int myMaximumLabelWidth = 0;
        for ( String myKey : myLabelValuePairs.keySet() )
        {
            myMaximumLabelWidth = Math.max( myMaximumLabelWidth, myKey.length() );
        }

        emitter.increment();

        for ( Map.Entry<String,String> myEntry : myLabelValuePairs.entrySet() )
        {
            String myLabel = myEntry.getKey();
            String myValue = myEntry.getValue();

            emitter.indent();
            
            FormatHelper.formatString( emitter.getBuffer(), myLabel, myMaximumLabelWidth, FormatHelper.Justify.Right );

            emitter.append ( ": ", myValue )
                   .newline(               )
                   ;
        }

        emitter.decrement();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     */
    private void describeCollections( ConfigEntityDescribable aDescribable )
    {
        assert aDescribable != null;
        
        Map<String,Object> myCollectionsMap = new LinkedHashMap<>();
        aDescribable.buildDescribableCollections( myCollectionsMap );
        
        if ( ! myCollectionsMap.isEmpty() )
        {
            emitter.increment();
            
            for ( Map.Entry<String,Object> myEntry : myCollectionsMap.entrySet() )
            {
                String myTitle  = myEntry.getKey();
                Object myObject = myEntry.getValue();

                if ( myObject == null || myObject instanceof ConfigEntityDescribable )
                {
                    emitter.line();
                    
                    if ( myObject != null )
                    {
                        describeImpl( (ConfigEntityDescribable) myObject );
                    }
                    else
                    {
                        emitter.line ( myTitle                     )
                               .line1( "***** not available *****" )
                               ;
                    }
                }
                else
                {
                    assert myObject instanceof Collection;
                    
                    @SuppressWarnings( "unchecked" )
                    Collection<ConfigEntityDescribable> myCollection = (Collection<ConfigEntityDescribable>) myObject;
                    
                    describeCollection( myTitle, myCollection );
                }
            }
            
            emitter.decrement();
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aTitle
     * @param       aCollection
     */
    private void describeCollection( String                                aTitle,
                                     Collection<ConfigEntityDescribable>   aCollection )
    {
        assert aTitle      != null;
        assert aCollection != null;
        
        emitter.line     (             )
               .line     ( aTitle, ":" )
               .increment(             )
               ;
        
        if ( ! aCollection.isEmpty() )
        {
            for ( ConfigEntityDescribable myDescribable : aCollection )
            {
                describeImpl( myDescribable );
            }
        }
        else
        {
            emitter.line( "***** not available *****" );
        }
        
        emitter.decrement();
    }
}

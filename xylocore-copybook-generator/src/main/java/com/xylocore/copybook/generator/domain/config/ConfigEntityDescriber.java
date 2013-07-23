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

import com.xylocore.commons.util.FormatHelper;


public class ConfigEntityDescriber
{
    //
    // Members
    //
    
    
    private StringBuilder   buffer;
    private int             indentLevel;
    private int             indentWidth;
    
    
    
    
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
        indentLevel = aIndentLevel;
        indentWidth = aIndentWidth;
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
        StringBuilder myBuffer = new StringBuilder();
        
        describe( aDescribable, myBuffer );
        
        return myBuffer.toString();
    }
    

    /**
     * FILLIN
     * 
     * @param       aDescribable
     * @param       aBuffer
     * @param       aIndentLevel
     */
    public void describe( ConfigEntityDescribable   aDescribable,
                          StringBuilder             aBuffer       )
    {
        if ( aBuffer == null )
        {
            throw new IllegalArgumentException( "aBuffer" );
        }
        
        try
        {
            buffer = aBuffer;

            describeImpl( aDescribable );
        }
        finally
        {
            buffer = null;
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       aDescribable
     * @param       aBuffer
     * @param       aIndentLevel
     */
    private void describeImpl( ConfigEntityDescribable aDescribable )
    {
        int    myOuterIndent = calculateIndent();
        String myClassName   = getDescribableClassName( aDescribable );
        
        FormatHelper.stringOfCharacters( buffer, ' ', myOuterIndent );
        
        buffer.append( myClassName )
              .append( "\n"        )
              ;
        
        FormatHelper.stringOfCharacters( buffer, ' ', myOuterIndent );
        
        buffer.append( "[\n" );
        
        describeElements   ( aDescribable );
        describeCollections( aDescribable );

        FormatHelper.stringOfCharacters( buffer, ' ', myOuterIndent );
        
        buffer.append( "]\n" );
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
        String myClassName = aDescribable.getClass().getName();
        
        return myClassName.substring( myClassName.lastIndexOf( '.' )+1 );
    }
    
    
    /**
     * FILLIN
     */
    private void incrementIndent()
    {
        indentLevel++;
    }
    
    
    /**
     * FILLIN
     */
    private void decrementIndent()
    {
        indentLevel--;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private int calculateIndent()
    {
        return calculateIndent( 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     * 
     * @return
     */
    private int calculateIndent( int aOffset )
    {
        return (indentLevel+aOffset)*indentWidth;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     */
    private void describeElements( ConfigEntityDescribable aDescribable )
    {
        Map<String,String> myLabelValuePairs = new LinkedHashMap<String,String>();
        aDescribable.buildDescribableLabelValuePairs( myLabelValuePairs );
        
        int myMaximumLabelWidth = 0;
        for ( String myKey : myLabelValuePairs.keySet() )
        {
            myMaximumLabelWidth = Math.max( myMaximumLabelWidth, myKey.length() );
        }
        
        incrementIndent();

        for ( Map.Entry<String, String> myEntry : myLabelValuePairs.entrySet() )
        {
            String myLabel = myEntry.getKey();
            String myValue = myEntry.getValue();
            
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent()                                   );
            FormatHelper.formatString      ( buffer, myLabel, myMaximumLabelWidth, FormatHelper.Justify.Right );
            
            buffer.append( ": "    )
                  .append( myValue )
                  .append( "\n"    )
                  ;
        }
        
        decrementIndent();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDescribable
     */
    private void describeCollections( ConfigEntityDescribable aDescribable )
    {
        Map<String,Object> myCollectionsMap = new LinkedHashMap<String,Object>();
        aDescribable.buildDescribableCollections( myCollectionsMap );
        
        if ( ! myCollectionsMap.isEmpty() )
        {
            incrementIndent();
            
            for ( Map.Entry<String,Object> myEntry : myCollectionsMap.entrySet() )
            {
                String myTitle  = myEntry.getKey();
                Object myObject = myEntry.getValue();

                if ( myObject == null || myObject instanceof ConfigEntityDescribable )
                {
                    buffer.append( "\n" );
                    
                    if ( myObject != null )
                    {
                        describeImpl( (ConfigEntityDescribable) myObject );
                    }
                    else
                    {
                        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
                        
                        buffer.append( myTitle )
                              .append( "\n"    )
                              ;
                        
                        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( 1 ) );
                        
                        buffer.append( "***** not available *****\n" );
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
            
            decrementIndent();
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
        buffer.append( "\n" );
        
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
        
        buffer.append( aTitle )
              .append( ":\n"  )
              ;
        
        incrementIndent();
        
        if ( ! aCollection.isEmpty() )
        {
            for ( ConfigEntityDescribable myDescribable : aCollection )
            {
                describeImpl( myDescribable );
            }
        }
        else
        {
            FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent() );
            
            buffer.append( "***** not available *****\n" );
        }
        
        decrementIndent();
    }
}

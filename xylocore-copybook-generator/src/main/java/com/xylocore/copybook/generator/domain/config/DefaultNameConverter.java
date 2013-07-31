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

import com.xylocore.copybook.runtime.DataType;


/**
 * The default name converter implementation for the library.
 *
 * @author      Eric Medley
 * 
 * @see         NameConverter
 */

public class DefaultNameConverter
    implements
        NameConverter
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aPrefix
     * @param       aSource
     * @param       aMethodType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * @param       aDataType
     * 
     * @return
     */
    private String buildMethodName( String     aPrefix,
                                    String     aSource,
                                    String     aMethodType,
                                    boolean    aIsDefault,
                                    boolean    aHasMultipleDatatypes,
                                    DataType   aDataType              )
    {
        StringBuilder myBuffer = new StringBuilder();
        myBuffer.append( aPrefix );
        
        convertToBeanName( myBuffer, aSource );
        
        myBuffer.append( aMethodType );
        
        if ( ! aIsDefault && aHasMultipleDatatypes )
        {
            myBuffer.append( "As"                    )
                    .append( aDataType.getAsSuffix() )
                    ;
        }
        
        return myBuffer.toString();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPrefix
     * @param       aSource
     * @param       aSuffix
     * 
     * @return
     */
    private String generateMethodName( String   aPrefix,
                                       String   aSource,
                                       String   aSuffix  )
    {
        assert aPrefix != null;
        assert aSource != null;
        assert aSuffix != null;
        
        int myMethodNameLength = aPrefix.length() +
                                 aSource.length() +
                                 aSuffix.length();
        
        StringBuilder myBuilder = new StringBuilder( myMethodNameLength );
        myBuilder.append( aPrefix );
        
        convertToBeanName( myBuilder, aSource );
        
        myBuilder.append( aSuffix );
        
        return myBuilder.toString();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aSource
     */
    private void convertToBeanName( StringBuilder   aBuffer,
                                    String          aSource  )
    {
        assert aBuffer != null;
        assert aSource != null;
        
        boolean myNeedCapital = true;
        
        for ( int i = 0, ci = aSource.length() ; i < ci ; i++ )
        {
            char myChar = aSource.charAt( i );
            
            if ( myChar == '-' )
            {
                myNeedCapital = true;
            }
            else if ( myNeedCapital )
            {
                aBuffer.append( Character.toUpperCase( myChar ) );
                myNeedCapital = false;
            }
            else
            {
                aBuffer.append( Character.toLowerCase( myChar ) );
            }
        }
    }
    
    
    
    
    //
    // NameConverter interface implementation
    //
    
    
    @Override
    public String convertToBeanName( String aSource )
    {
        StringBuilder myBuffer = new StringBuilder( aSource.length() );
        
        convertToBeanName( myBuffer, aSource );
        
        return myBuffer.toString();
    }
    
    
    @Override
    public String generateRecordLengthMethodName( String aSource )
    {
        return generateMethodName( "get", aSource, "RecordLength" );
    }
    
    
    @Override
    public String generateOffsetMethodName( String aSource )
    {
        return generateMethodName( "get", aSource, "Offset" );
    }
    
    
    @Override
    public String generateLengthMethodName( String aSource )
    {
        return generateMethodName( "get", aSource, "Length" );
    }
    
    
    @Override
    public String generateTotalLengthMethodName( String aSource )
    {
        return generateMethodName( "get", aSource, "TotalLength" );
    }
    
    
    @Override
    public String generateSingleElementLengthMethodName( String aSource )
    {
        return generateMethodName( "get", aSource, "SingleElementLength" );
    }
    
    
    @Override
    public String generateBlankMethodName( String aSource )
    {
        return generateMethodName( "is", aSource, "Blank" );
    }
    
    
    @Override
    public String generateNullMethodName( String aSource )
    {
        return generateMethodName( "is", aSource, "Null" );
    }
    
    
    @Override
    public String generateValidMethodName( String     aSource,
                                           DataType   aDataType,
                                           boolean    aIsDefault,
                                           boolean    aHasMultipleDatatypes )
    {
        return buildMethodName( "is",
                                aSource,
                                "Valid",
                                aIsDefault,
                                aHasMultipleDatatypes,
                                aDataType              );
    }
    
    
    @Override
    public String generateGetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes )
    {
        return buildMethodName( "get",
                                aSource,
                                "",
                                aIsDefault,
                                aHasMultipleDatatypes,
                                aDataType              );
    }
    

    @Override
    public String generateSetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes )
    {
        return buildMethodName( "set",
                                aSource,
                                "",
                                aIsDefault,
                                aHasMultipleDatatypes,
                                aDataType              );
    }
    

    @Override
    public String generateConditionNameMethodName( String aSource )
    {
        return "is" + convertToBeanName( aSource );
    }
}

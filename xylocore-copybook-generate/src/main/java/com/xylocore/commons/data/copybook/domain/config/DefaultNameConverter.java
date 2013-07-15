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

package com.xylocore.commons.data.copybook.domain.config;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 *
 * @author      Eric Medley
 */

public class DefaultNameConverter
    implements
        NameConverter
{
    //
    // Members
    //
    
    
    private static final NameConverter instance = new DefaultNameConverter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    
    /**
     * Private constructor as per singleton pattern.
     */
    private DefaultNameConverter()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static NameConverter getInstance()
    {
        return instance;
    }
    
    
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
        StringBuffer myBuffer = new StringBuffer();
        myBuffer.append( aPrefix                      )
                .append( convertToBeanName( aSource ) )
                .append( aMethodType                  )
                ;
        
        if ( ! aIsDefault && aHasMultipleDatatypes )
        {
            myBuffer.append( "As"                    )
                    .append( aDataType.getAsSuffix() )
                    ;
        }
        
        return myBuffer.toString();
    }
    
    
    
    
    //
    // NameConverter interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#convertToBeanName(java.lang.String)
     */
    public String convertToBeanName( String aSource )
    {
        StringBuffer myBuffer      = new StringBuffer( aSource.length() );
        boolean      myNeedCapital = true;
        
        for ( int i = 0, ci = aSource.length() ; i < ci ; i++ )
        {
            char myChar = aSource.charAt( i );
            
            if ( myChar == '-' )
            {
                myNeedCapital = true;
            }
            else if ( myNeedCapital )
            {
                myBuffer.append( Character.toUpperCase( myChar ) );
                myNeedCapital = false;
            }
            else
            {
                myBuffer.append( Character.toLowerCase( myChar ) );
            }
        }
        
        return myBuffer.toString();
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateRecordLengthMethodName(java.lang.String)
     */
    public String generateRecordLengthMethodName( String aSource )
    {
        return "get" + convertToBeanName( aSource ) + "RecordLength";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateOffsetMethodName(java.lang.String)
     */
    public String generateOffsetMethodName( String aSource )
    {
        return "get" + convertToBeanName( aSource ) + "Offset";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateRecordLengthMethodName(java.lang.String)
     */
    public String generateLengthMethodName( String aSource )
    {
        return "get" + convertToBeanName( aSource ) + "Length";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateTotalLengthMethodName(java.lang.String)
     */
    public String generateTotalLengthMethodName( String aSource )
    {
        return "get" + convertToBeanName( aSource ) + "TotalLength";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateSingleElementLengthMethodName(java.lang.String)
     */
    public String generateSingleElementLengthMethodName( String aSource )
    {
        return "get" + convertToBeanName( aSource ) + "SingleElementLength";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateBlankMethodName(java.lang.String)
     */
    public String generateBlankMethodName( String aSource )
    {
        return "is" + convertToBeanName( aSource ) + "Blank";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateNullMethodName(java.lang.String)
     */
    public String generateNullMethodName( String aSource )
    {
        return "is" + convertToBeanName( aSource ) + "Null";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateValidMethodName(java.lang.String, com.xylocore.commons.data.copybook.runtime.DataType, boolean, boolean)
     */
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateGetterMethodName(java.lang.String, com.xylocore.commons.data.copybook.runtime.DataType, boolean, boolean)
     */
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
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateSetterMethodName(java.lang.String, com.xylocore.commons.data.copybook.runtime.DataType, boolean, boolean)
     */
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
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.NameConverter#generateConditionNameMethodName(java.lang.String)
     */
    public String generateConditionNameMethodName( String aSource )
    {
        return "is" + convertToBeanName( aSource );
    }
}

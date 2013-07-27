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


package com.xylocore.copybook.generator.visitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.ElementType;
import com.xylocore.copybook.generator.domain.config.AccessorConfig;
import com.xylocore.copybook.generator.domain.config.BigDecimalAccessorConfig;
import com.xylocore.copybook.generator.domain.config.BigIntegerAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ByteAccessorConfig;
import com.xylocore.copybook.generator.domain.config.CharAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.DataElementConfig;
import com.xylocore.copybook.generator.domain.config.DateAccessorConfig;
import com.xylocore.copybook.generator.domain.config.DefaultAccessorConfig;
import com.xylocore.copybook.generator.domain.config.DoubleAccessorConfig;
import com.xylocore.copybook.generator.domain.config.ElementConfig;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.domain.config.FloatAccessorConfig;
import com.xylocore.copybook.generator.domain.config.IntegerAccessorConfig;
import com.xylocore.copybook.generator.domain.config.LongAccessorConfig;
import com.xylocore.copybook.generator.domain.config.NoDefaultAccessorConfig;
import com.xylocore.copybook.generator.domain.config.NullEquivalentConfig;
import com.xylocore.copybook.generator.domain.config.ShortAccessorConfig;
import com.xylocore.copybook.generator.domain.config.StringAccessorConfig;
import com.xylocore.copybook.generator.nulleq.NullEquivalentStrategyFactory;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ApplyMetadataToElementVisitor
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    private static final NullEquivalentStrategyFactory  nullEquivalentStrategyFactory           = new NullEquivalentStrategyFactory();
    private static final Map<DataType,Integer>          integerBasedDataTypes;
    private static final Map<DataType,Integer>          floatingPointBasedDataTypes;
    
    private Environment                                 environment;
    private AccessorMethodInfoCreationVisitor           accessorMethodInfoCreationVisitor;
    private DataElement                                 dataElement;
    private boolean                                     skipAccessor;
    private AccessorMethodInfo                          accessorMethodInfo;
    private DataType                                    minimumIntegerDataType;
    private int                                         minimumIntegerDataTypeOffset;
    private DataType                                    minimumFloatingPointDataType;
    private int                                         minimumFloatingPointDataTypeOffset;

    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        Map<DataType,Integer> myMap;
        
        myMap = new HashMap<>();
        myMap.put( DataType.Byte     , new Integer( 0 ) );
        myMap.put( DataType.Character, new Integer( 1 ) );
        myMap.put( DataType.Short    , new Integer( 2 ) );
        myMap.put( DataType.Integer  , new Integer( 3 ) );
        myMap.put( DataType.Long     , new Integer( 4 ) );
        integerBasedDataTypes = Collections.unmodifiableMap( myMap );
        
        myMap = new HashMap<>();
        myMap.put( DataType.Float , new Integer( 0 ) );
        myMap.put( DataType.Double, new Integer( 1 ) );
        floatingPointBasedDataTypes = Collections.unmodifiableMap( myMap );
    }
    

    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public ApplyMetadataToElementVisitor( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        environment                       = aEnvironment;
        accessorMethodInfoCreationVisitor = new AccessorMethodInfoCreationVisitor();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    public void apply( DataElement aDataElement )
    {
        assert aDataElement != null;
        
        dataElement  = aDataElement;
        skipAccessor = false;

        Integer myDataTypeOffset;
        
        if ( environment.getMinimumIntegerDataType() != null )
        {
            minimumIntegerDataType = environment.getMinimumIntegerDataType();
        }
        else
        {
            minimumIntegerDataType = DataType.Byte;
        }
        myDataTypeOffset             = integerBasedDataTypes.get( minimumIntegerDataType );
        minimumIntegerDataTypeOffset = myDataTypeOffset.intValue();
        
        if ( environment.getMinimumFloatingPointDataType() != null )
        {
            minimumFloatingPointDataType = environment.getMinimumFloatingPointDataType();
        }
        else
        {
            minimumFloatingPointDataType = DataType.Float;
        }
        myDataTypeOffset = floatingPointBasedDataTypes.get( minimumFloatingPointDataType );
        minimumFloatingPointDataTypeOffset = myDataTypeOffset.intValue();
        
        for ( ElementConfig myElementConfig : aDataElement.getMetadata() )
        {
            myElementConfig.accept( this );
        }

        // Should a default accessor be created?
        if
        (
            aDataElement.getElementType() == ElementType.ElementaryItem &&
            ! aDataElement.isNoDefaultAccessor()                        &&
            aDataElement.getAccessorMethodInfos().isEmpty()
        )
        {
            // Create a default accessor
            DefaultAccessorConfig myDefaultAccessorConfig = new DefaultAccessorConfig();
            myDefaultAccessorConfig.accept( this );
        }
    }
    
    
    @Override
    public boolean shouldVisitAccessors( DataElementConfig aElement )
    {
        return true;
    }

    
    @Override
    public void visitDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        accessorMethodInfo = createDefaultAccessor( dataElement, aAccessor );
        dataElement.addAccessorMethodInfo( accessorMethodInfo );

        skipAccessor = true;
    }
    

    @Override
    public void leaveDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    

    @Override
    public void visitNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        dataElement.setNoDefaultAccessor( true );
        
        skipAccessor = true;
    }
    
    
    @Override
    public void leaveNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
    
    @Override
    public void visitAccessor( AccessorConfig aAccessor )
    {
        if ( ! skipAccessor )
        {
            accessorMethodInfo = accessorMethodInfoCreationVisitor.create( aAccessor );
            dataElement.addAccessorMethodInfo( accessorMethodInfo );
        }
    }
    
    
    @Override
    public boolean shouldVisitNullEquivalents( DataElementConfig aElement )
    {
        return true;
    }
    
    
    @Override
    public void visitNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
        NullEquivalentStrategy myNullEquivalentStrategy = nullEquivalentStrategyFactory.create( dataElement, aNullEquivalent );
        dataElement.addNullEquivalentStrategy( myNullEquivalentStrategy );
    }


    /**
     * FILLIN
     * 
     * @param       aAccessor
     * @param       aDataType
     * 
     * @return
     */
    private AccessorMethodInfo createDefaultAccessor( DataElement             aDataElement,
                                                      DefaultAccessorConfig   aAccessor     )
    {
        assert aDataElement != null;
        assert aAccessor    != null;
        
        DataType       myDataType             = aDataElement.getDataType();
        AccessorConfig myProxyAccessorConfig;
        
        if ( integerBasedDataTypes.containsKey( myDataType ) )
        {
            Integer myDataTypeOffset = integerBasedDataTypes.get( myDataType );
            if ( myDataTypeOffset.intValue() < minimumIntegerDataTypeOffset )
            {
                myDataType = minimumIntegerDataType;
            }
        }
        else if ( floatingPointBasedDataTypes.containsKey( myDataType ) )
        {
            Integer myDataTypeOffset = floatingPointBasedDataTypes.get( myDataType );
            if ( myDataTypeOffset.intValue() < minimumFloatingPointDataTypeOffset )
            {
                myDataType = minimumFloatingPointDataType;
            }
        }
        
        switch ( myDataType )
        {
            case Byte:        myProxyAccessorConfig = new ByteAccessorConfig();       break;
            case Character:   myProxyAccessorConfig = new CharAccessorConfig();       break;
            case Short:       myProxyAccessorConfig = new ShortAccessorConfig();      break;
            case Integer:     myProxyAccessorConfig = new IntegerAccessorConfig();    break;
            case Long:        myProxyAccessorConfig = new LongAccessorConfig();       break;
            case Float:       myProxyAccessorConfig = new FloatAccessorConfig();      break;
            case Double:      myProxyAccessorConfig = new DoubleAccessorConfig();     break;
            case BigInteger:  myProxyAccessorConfig = new BigIntegerAccessorConfig(); break;
            case BigDecimal:  myProxyAccessorConfig = new BigDecimalAccessorConfig(); break;
            case String:      myProxyAccessorConfig = new StringAccessorConfig();     break;
            case Date:        myProxyAccessorConfig = new DateAccessorConfig();       break;
    
            default:
    
                throw new IllegalStateException( "data type not mapped: " + myDataType.toString() );
        }
        
        myProxyAccessorConfig.setDefault( true );

        return accessorMethodInfoCreationVisitor.create( myProxyAccessorConfig );
    }
}

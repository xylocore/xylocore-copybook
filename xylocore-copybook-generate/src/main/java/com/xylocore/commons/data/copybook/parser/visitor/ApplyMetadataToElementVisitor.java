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


package com.xylocore.commons.data.copybook.parser.visitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.ElementType;
import com.xylocore.commons.data.copybook.domain.config.AccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.BigDecimalAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.BigIntegerAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.ByteAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.CharAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.ConfigVisitor;
import com.xylocore.commons.data.copybook.domain.config.DataElementConfig;
import com.xylocore.commons.data.copybook.domain.config.DateAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.DefaultAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.DoubleAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.ElementConfig;
import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.domain.config.FloatAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.IntegerAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.LongAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.NoDefaultAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig;
import com.xylocore.commons.data.copybook.domain.config.ShortAccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.StringAccessorConfig;
import com.xylocore.commons.data.copybook.parser.nulleq.NullEquivalentStrategyFactory;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


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
        
        myMap = new HashMap<DataType,Integer>();
        myMap.put( DataType.Byte     , new Integer( 0 ) );
        myMap.put( DataType.Character, new Integer( 1 ) );
        myMap.put( DataType.Short    , new Integer( 2 ) );
        myMap.put( DataType.Integer  , new Integer( 3 ) );
        myMap.put( DataType.Long     , new Integer( 4 ) );
        integerBasedDataTypes = Collections.unmodifiableMap( myMap );
        
        myMap = new HashMap<DataType,Integer>();
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
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#shouldVisitAccessors(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public boolean shouldVisitAccessors( DataElementConfig aElement )
    {
        return true;
    }

    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        accessorMethodInfo = createDefaultAccessor( dataElement, aAccessor );
        dataElement.addAccessorMethodInfo( accessorMethodInfo );

        skipAccessor = true;
    }
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.DefaultAccessorConfig)
     */
    public void leaveDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitNoDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.NoDefaultAccessorConfig)
     */
    public void visitNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        dataElement.setNoDefaultAccessor( true );
        
        skipAccessor = true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveNoDefaultAccessor(com.xylocore.commons.data.copybook.domain.config.NoDefaultAccessorConfig)
     */
    public void leaveNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
        skipAccessor = false;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitAccessor(com.xylocore.commons.data.copybook.domain.config.AccessorConfig)
     */
    public void visitAccessor( AccessorConfig aAccessor )
    {
        if ( ! skipAccessor )
        {
            accessorMethodInfo = accessorMethodInfoCreationVisitor.create( aAccessor );
            dataElement.addAccessorMethodInfo( accessorMethodInfo );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#shouldVisitNullEquivalents(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public boolean shouldVisitNullEquivalents( DataElementConfig aElement )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitBlankNullEquivalent(com.xylocore.commons.data.copybook.domain.config.BlankNullEquivalentConfig)
     */
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
        
        // TODO: implement more efficiently
        if ( myDataType == DataType.Byte )
        {
            ByteAccessorConfig myByteAccessorConfig = new ByteAccessorConfig();
            myProxyAccessorConfig = myByteAccessorConfig;
        }
        else if ( myDataType == DataType.Character )
        {
            CharAccessorConfig myCharAccessorConfig = new CharAccessorConfig();
            myProxyAccessorConfig = myCharAccessorConfig;
        }
        else if ( myDataType == DataType.Short )
        {
            ShortAccessorConfig myShortAccessorConfig = new ShortAccessorConfig();
            myProxyAccessorConfig = myShortAccessorConfig;
        }
        else if ( myDataType == DataType.Integer )
        {
            IntegerAccessorConfig myIntegerAccessorConfig = new IntegerAccessorConfig();
            myProxyAccessorConfig = myIntegerAccessorConfig;
        }
        else if ( myDataType == DataType.Long )
        {
            LongAccessorConfig myLongAccessorConfig = new LongAccessorConfig();
            myProxyAccessorConfig = myLongAccessorConfig;
        }
        else if ( myDataType == DataType.Float )
        {
            FloatAccessorConfig myFloatAccessorConfig = new FloatAccessorConfig();
            myProxyAccessorConfig = myFloatAccessorConfig;
        }
        else if ( myDataType == DataType.Double )
        {
            DoubleAccessorConfig myDoubleAccessorConfig = new DoubleAccessorConfig();
            myProxyAccessorConfig = myDoubleAccessorConfig;
        }
        else if ( myDataType == DataType.BigInteger )
        {
            BigIntegerAccessorConfig myBigIntegerAccessorConfig = new BigIntegerAccessorConfig();
            myProxyAccessorConfig = myBigIntegerAccessorConfig;
        }
        else if ( myDataType == DataType.BigDecimal )
        {
            BigDecimalAccessorConfig myBigDecimalAccessorConfig = new BigDecimalAccessorConfig();
            myProxyAccessorConfig = myBigDecimalAccessorConfig;
        }
        else if ( myDataType == DataType.String )
        {
            StringAccessorConfig myStringAccessorConfig = new StringAccessorConfig();
            myProxyAccessorConfig = myStringAccessorConfig;
        }
        else if ( myDataType == DataType.Date )
        {
            DateAccessorConfig myDateAccessorConfig = new DateAccessorConfig();
            myProxyAccessorConfig = myDateAccessorConfig;
        }
        else
        {
            throw new IllegalStateException( "data type not mapped: " + myDataType.toString() );
        }
        
        myProxyAccessorConfig.setDefault( true );

        return accessorMethodInfoCreationVisitor.create( myProxyAccessorConfig );
    }
}

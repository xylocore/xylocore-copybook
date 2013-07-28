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


package com.xylocore.copybook.generator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.xylocore.copybook.generator.domain.config.DefaultNameConverter;
import com.xylocore.copybook.generator.domain.config.ElementConfig;
import com.xylocore.copybook.runtime.DataCategory;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;
import com.xylocore.copybook.runtime.SyncPosition;
import com.xylocore.copybook.runtime.UsageType;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class Element
    implements
        Visitable
{
    //
    // Members
    //
    

    public  static final int    DISPLAY_PRESENT_FLAG                = 0x00000001;
    public  static final int    DBCS_PRESENT_FLAG                   = 0x00000002;
    public  static final int    NATIONAL_PRESENT_FLAG               = 0x00000004;
    private static final int    GLOBAL_FLAG                         = 0x00000008;
    private static final int    EXTERNAL_FLAG                       = 0x00000010;
    private static final int    BLANK_WHEN_ZERO_FLAG                = 0x00000020;
    private static final int    JUSTIFIED_FLAG                      = 0x00000040;
    private static final int    NATIONAL_GROUP_USAGE_FLAG           = 0x00000080;
    private static final int    MAINTAIN_WHITESPACE_FLAG            = 0x00000100;
    private static final int    OCCURS_FLAG                         = 0x00000200;
    private static final int    NULL_ALLOWED_FLAG                   = 0x00000400;
    
    private static final int    POSSIBLE_STRING_TYPE_PRESENT_MASK   = ( DISPLAY_PRESENT_FLAG | DBCS_PRESENT_FLAG | NATIONAL_PRESENT_FLAG );

    private String              name;
    private Element             parent;
    private Element             firstChild;
    private Element             lastChild;
    private Element             previousSibling;
    private Element             nextSibling;
    private Element             outerIndexedElement;
    private int                 level;
    private Map<String,Element> childrenByName;
    private List<PICSlice>      picSlices;
    private Set<PICSymbolType>  symbolTypes;
    private Set<DataCategory>   possibleDataCategories;
    private DataCategory        dataCategory;
    private int                 offset;
    private int                 size;
    private int                 nonIndexedSize;
    private ElementType         elementType;
    private Element             redefinedElement;
    private int                 occursMinValue;
    private int                 occursMaxValue;
    private Element             occursDependingOnElement;
    private UsageType           usageType;
    private UsageType           effectiveUsageType;
    private Value               value;
    private String              datePattern;
    private SyncPosition        syncPosition;
    private SignPosition        signPosition;
    private SignType            signType;
    private int                 flags;
    private DataType            dataType;
    private int                 precision;
    private int                 scalingPosition;
    private int                 digits;
    private String              recordLengthMethodName;
    private List<ElementConfig> metadataItems;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aLevel
     */
    public Element( int aLevel )
    {
        this( null, aLevel );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     * @param       aLevel
     */
    public Element( String   aName,
                    int      aLevel )
    {
        assert aName == null || aName.length() != 0;
        assert ( aLevel >= 0 && aLevel < 50 ) || aLevel == 66 || aLevel == 77 || aLevel == 88;
        
        name           = ( aName != null ) ? aName.toUpperCase() : null;
        level          = aLevel;
        childrenByName = new TreeMap<>( String.CASE_INSENSITIVE_ORDER );
        size           = 0;
        flags          = 0;
        occursMinValue = 1;
        occursMaxValue = 1;
        metadataItems  = new ArrayList<ElementConfig>();
        
        // TODO: provide mechanism to set this flag via config
        setNullAllowed( true );
    }
    
    
    @Override
    public String toString()
    {
        return name;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlag
     * 
     * @return
     */
    private boolean isFlagSet( int aFlag )
    {
        return ( (flags & aFlag) != 0 );
    }
    

    /**
     * FILLIN
     * 
     * @param       aValue
     * @param       aFlag
     */
    private void setFlag( boolean   aValue,
                          int       aFlag   )
    {
        if ( aValue )
        {
            flags |= aFlag;
        }
        else
        {
            flags &= ~aFlag;
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Element getParent()
    {
        return parent;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aParent
     */
    public void setParent( Element aParent )
    {
        parent = aParent;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public Element getFirstChild()
    {
        return firstChild;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFirstChild
     */
    public void setFirstChild( Element aFirstChild )
    {
        firstChild = aFirstChild;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public Element getLastChild()
    {
        return lastChild;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLastChild
     */
    public void setLastChild( Element aLastChild )
    {
        lastChild = aLastChild;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public Element getPreviousSibling()
    {
        return previousSibling;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPreviousSibling
     */
    public void setPreviousSibling( Element aPreviousSibling )
    {
        previousSibling = aPreviousSibling;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    public Element getNextSibling()
    {
        return nextSibling;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNextSibling
     */
    public void setNextSibling( Element aNextSibling )
    {
        nextSibling = aNextSibling;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Element getOuterIndexedElement()
    {
        return outerIndexedElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOuterIndexedElement
     */
    public void setOuterIndexedElement( Element aOuterIndexedElement )
    {
        outerIndexedElement = aOuterIndexedElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getLevel()
    {
        return level;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     * 
     * @return
     */
    public Element getChildByName( String aName )
    {
        assert aName != null && aName.length() != 0;
        
        return childrenByName.get( aName );
    }
    

    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aIndent
     */
    public static void dump( Element   aElement,
                             int       aIndent   )
    {
        while ( aElement != null )
        {
            for ( int i = 0, ci = aIndent*4 ; i < ci ; i++ )
            {
                System.out.write( ' ' );
            }

            String myName     = "";
            String myBeanName = "";

            if ( aElement instanceof DataElement )
            {
                myName     = ((DataElement) aElement).getName();
                myBeanName = DefaultNameConverter.getInstance().convertToBeanName( myName );
            }
            else if ( aElement instanceof FillerElement )
            {
                myName = "FILLER";
            }

            System.out.println( aElement.getLevel() + " " + myName + "   " + myBeanName );

            dump( aElement.getFirstChild(), aIndent+1 );

            aElement = aElement.getNextSibling();
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aChild
     */
    public void addChild( Element aChild )
    {
        assert aChild != null;
        
        aChild.setParent         ( this      );
        aChild.setPreviousSibling( lastChild );
        
        if ( lastChild != null )
        {
            lastChild.setNextSibling( aChild );
        }
        else
        {
            firstChild = aChild;
        }
        
        lastChild = aChild;
        
        String myChildName = aChild.getName();
        if ( myChildName != null )
        {
            if ( childrenByName.containsKey( myChildName ) )
            {
                throw new DuplicationElementNameException( myChildName );
            }
            
            childrenByName.put( myChildName, aChild );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public ElementType getElementType()
    {
        return elementType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementType
     */
    public void setElementType( ElementType aElementType )
    {
        elementType = aElementType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Element getRedefinedElement()
    {
        return redefinedElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aRedefinedElement
     */
    public void setRedefinedElement( Element aRedefinedElement )
    {
        redefinedElement = aRedefinedElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getOccursMinValue()
    {
        return occursMinValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOccursMinValue
     */
    public void setOccursMinValue( int aOccursMinValue )
    {
        occursMinValue = aOccursMinValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getOccursMaxValue()
    {
        return occursMaxValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOccursMaxValue
     */
    public void setOccursMaxValue( int aOccursMaxValue )
    {
        occursMaxValue = aOccursMaxValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Element getOccursDependingOnElement()
    {
        return occursDependingOnElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOccursDependingOnElement
     */
    public void setOccursDependingOnElement( Element aOccursDependingOnElement )
    {
        occursDependingOnElement = aOccursDependingOnElement;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<PICSlice> getPICSlices()
    {
        return picSlices;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPIC
     */
    public void setPICSlices( List<PICSlice> aPICSlices )
    {
        picSlices = aPICSlices;
        
        size = Integer.MIN_VALUE;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Set<PICSymbolType> getSymbolTypes()
    {
        return symbolTypes;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSymbolTypes
     */
    public void setSymbolTypes( Set<PICSymbolType> aSymbolTypes )
    {
        symbolTypes = aSymbolTypes;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Set<DataCategory> getPossibleDataCategories()
    {
        return possibleDataCategories;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPossibleDataCategories
     */
    public void setPossibleDataCategories( Set<DataCategory> aPossibleDataCategories )
    {
        possibleDataCategories = aPossibleDataCategories;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataCategory getDataCategory()
    {
        return dataCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataCategory
     */
    public void setDataCategory( DataCategory aDataCategory )
    {
        dataCategory = aDataCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getOffset()
    {
        return offset;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     */
    public void setOffset( int aOffset )
    {
        offset = aOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getSize()
    {
        return size;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSize
     */
    public void setSize( int aSize )
    {
        size = aSize;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getNonIndexedSize()
    {
        return nonIndexedSize;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNonIndexedSize
     */
    public void setNonIndexedSize( int aNonIndexedSize )
    {
        nonIndexedSize = aNonIndexedSize;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public UsageType getUsageType()
    {
        return usageType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aUsageType
     */
    public void setUsageType( UsageType aUsageType )
    {
        usageType = aUsageType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public UsageType getEffectiveUsageType()
    {
        return effectiveUsageType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEffectiveUsageType
     */
    public void setEffectiveUsageType( UsageType aEffectiveUsageType )
    {
        effectiveUsageType = aEffectiveUsageType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isGlobal()
    {
        return isFlagSet( GLOBAL_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aGlobal
     */
    public void setGlobal( boolean aGlobal )
    {
        setFlag( aGlobal, GLOBAL_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isExternal()
    {
        return isFlagSet( EXTERNAL_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aExternal
     */
    public void setExternal( boolean aExternal )
    {
        setFlag( aExternal, EXTERNAL_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isBlankWhenZero()
    {
        return isFlagSet( BLANK_WHEN_ZERO_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBlankWhenZero
     */
    public void setBlankWhenZero( boolean aBlankWhenZero )
    {
        setFlag( aBlankWhenZero, BLANK_WHEN_ZERO_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isJustified()
    {
        return isFlagSet( JUSTIFIED_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aJustified
     */
    public void setJustified( boolean aJustified )
    {
        setFlag( aJustified, JUSTIFIED_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isNationalGroupUsage()
    {
        return isFlagSet( NATIONAL_GROUP_USAGE_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNationalGroupUsage
     */
    public void setNationalGroupUsage( boolean aNationalGroupUsage )
    {
        setFlag( aNationalGroupUsage, NATIONAL_GROUP_USAGE_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isMaintainWhitespace()
    {
        return isFlagSet( MAINTAIN_WHITESPACE_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMaintainWhitespace
     */
    public void setMaintainWhitespace( boolean aMaintainWhitespace )
    {
        setFlag( aMaintainWhitespace, MAINTAIN_WHITESPACE_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isOccurs()
    {
        return isFlagSet( OCCURS_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOccurs
     */
    public void setOccurs( boolean aOccurs )
    {
        setFlag( aOccurs, OCCURS_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isOccursOrSubordinate()
    {
        boolean myFlag = isOccurs();
        if ( ! myFlag && parent != null )
        {
            myFlag = parent.isOccursOrSubordinate();
        }
        
        return myFlag;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public boolean isNullAllowed()
    {
        return isFlagSet( NULL_ALLOWED_FLAG );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aNullAllowed
     */
    public void setNullAllowed( boolean aNullAllowed )
    {
        setFlag( aNullAllowed, NULL_ALLOWED_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getPossibleStringTypesPresent()
    {
        return ( flags & POSSIBLE_STRING_TYPE_PRESENT_MASK );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPossibleStringTypesPresent
     */
    public void setPossibleStringTypesPresent( int aPossibleStringTypesPresent )
    {
        flags |= (aPossibleStringTypesPresent & POSSIBLE_STRING_TYPE_PRESENT_MASK);
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getTotalPossibleStringTypesPresent()
    {
        return (( isDisplayPresent()  ) ? 1 : 0) +
               (( isDbcsPresent()     ) ? 1 : 0) +
               (( isNationalPresent() ) ? 1 : 0);
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isDisplayPresent()
    {
        return isFlagSet( DISPLAY_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDisplayPresent
     */
    public void setDisplayPresent( boolean aDisplayPresent )
    {
        setFlag( aDisplayPresent, DISPLAY_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isDbcsPresent()
    {
        return isFlagSet( DBCS_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDbcsPresent
     */
    public void setDbcsPresent( boolean aDbcsPresent )
    {
        setFlag( aDbcsPresent, DBCS_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isNationalPresent()
    {
        return isFlagSet( NATIONAL_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNationalPresent
     */
    public void setNationalPresent( boolean aNationalPresent )
    {
        setFlag( aNationalPresent, NATIONAL_PRESENT_FLAG );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Value getValue()
    {
        return value;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public void setValue( Value aValue )
    {
        value = aValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getDatePattern()
    {
        return datePattern;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDatePattern
     */
    public void setDatePattern( String aDatePattern )
    {
        datePattern = aDatePattern;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public SyncPosition getSyncPosition()
    {
        return syncPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSyncPosition
     */
    public void setSyncPosition( SyncPosition aSyncPosition )
    {
        syncPosition = aSyncPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public SignPosition getSignPosition()
    {
        return signPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSignPosition
     */
    public void setSignPosition( SignPosition aSignPosition )
    {
        signPosition = aSignPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public SignType getSignType()
    {
        return signType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSignType
     */
    public void setSignType( SignType aSignType )
    {
        signType = aSignType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataType getDataType()
    {
        return dataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public void setDataType( DataType aDataType )
    {
        dataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getPrecision()
    {
        return precision;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPrecision
     */
    public void setPrecision( int aPrecision )
    {
        precision = aPrecision;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getScalingPosition()
    {
        return scalingPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aScalingPosition
     */
    public void setScalingPosition( int aScalingPosition )
    {
        scalingPosition = aScalingPosition;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getDigits()
    {
        return digits;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDigits
     */
    public void setDigits( int aDigits )
    {
        digits = aDigits;
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getRecordLengthMethodName()
    {
        return recordLengthMethodName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLengthMethodName
     */
    public void setRecordLengthMethodName( String aLengthMethodName )
    {
        recordLengthMethodName = aLengthMethodName;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<ElementConfig> getMetadata()
    {
        return metadataItems;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementMetadata
     */
    public void addMetadata( ElementConfig aElementMetadata )
    {
        assert aElementMetadata != null;

        metadataItems.add( aElementMetadata );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitElement( this );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( Visitor aVisitor )
    {
        assert aVisitor != null;

        if ( aVisitor.shouldVisitChildren( this ) )
        {
            for ( Element myChild = firstChild ; myChild != null ; myChild = myChild.nextSibling )
            {
                if ( aVisitor.shouldVisitChild( myChild ) )
                {
                    myChild.accept( aVisitor );
                }
            }
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.leaveElement( this );
    }
    
    
    
    
    //
    // Visitable interface implementation
    //
    
    
    @Override
    public void accept( Visitor aVisitor )
    {
        acceptVisit   ( aVisitor );
        acceptChildren( aVisitor );
        acceptLeave   ( aVisitor );
    }
}

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


package com.xylocore.commons.data.copybook.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import com.xylocore.commons.data.copybook.domain.Copybook;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.ElementProxy;
import com.xylocore.commons.data.copybook.domain.FillerElement;
import com.xylocore.commons.data.copybook.domain.HighValue;
import com.xylocore.commons.data.copybook.domain.Level66Element;
import com.xylocore.commons.data.copybook.domain.Level88Element;
import com.xylocore.commons.data.copybook.domain.LowValue;
import com.xylocore.commons.data.copybook.domain.NoNameElement;
import com.xylocore.commons.data.copybook.domain.NullValue;
import com.xylocore.commons.data.copybook.domain.NumericValue;
import com.xylocore.commons.data.copybook.domain.PICSlice;
import com.xylocore.commons.data.copybook.domain.QuoteValue;
import com.xylocore.commons.data.copybook.domain.SpaceValue;
import com.xylocore.commons.data.copybook.domain.StringValue;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ValueRange;
import com.xylocore.commons.data.copybook.domain.ZeroValue;
import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.parser.CopybookParser.BlankWhenZeroClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.CharacterLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.ConditionNameEntryContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.ConditionNameEntryValueContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.CopybookContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.DataDescriptionEntryContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.DateFormatClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.ExternalClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.FloatingPointLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.GlobalClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.GroupUsageClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.HighValueLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.IntegerLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.JustifiedClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.Level66DescriptionEntryContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.LevelNumberContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.LiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.LowValueLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.NullLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.OccursClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.PictureClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.QuoteLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.RedefinesClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.RenamesClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.SignClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.SpaceLiteralContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.SynchronizedClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.UsageClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.ValueClauseContext;
import com.xylocore.commons.data.copybook.parser.CopybookParser.ZeroLiteralContext;
import com.xylocore.commons.data.copybook.parser.pic.PICException;
import com.xylocore.commons.data.copybook.parser.pic.PICLexer;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.SyncPosition;
import com.xylocore.commons.data.copybook.runtime.UsageType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookTransformationListener
    extends
        CopybookParserBaseListener
{
    //
    // Constants
    //
    
    
    private enum ElementClauseType
    {
        GlobalClause,
        OccursClause,
        JustifiedClause,
        UsageClause,
        SignClause,
        SynchronizedClause,
        ExternalClause,
        ValueClause,
        BlankWhenZeroClause,
        DateFormatClause,
        PictureClause,
        GroupUsageClause
    }
    
    
    
    
    //
    // Members
    //
    

    private PICLexer                    picLexer;
    private ParseTreeProperty<Value>    values;
    private int                         currentLevelNumber;
    private Environment                 environment;
    private Copybook                    copybook;
    private Element                     currentElement;
    private Element                     lastElement;
    private Set<ElementClauseType>      existingElementClauses;
    
    
    
    
    //
    // Instance initialization
    //
    
    
    {
        picLexer               = new PICLexer();
        values                 = new ParseTreeProperty<>();
        existingElementClauses = new HashSet<>();
    }
    
    

    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public CopybookTransformationListener( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        environment = aEnvironment;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Copybook getCopybook()
    {
        return copybook;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNode
     * 
     * @return
     */
    @SuppressWarnings( "unchecked" )
    private <T extends Value> T getValue( ParseTree aNode )
    {
        return (T) values.get( aNode );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNode
     * @param       aValue
     */
    private <T extends Value> void setValue( ParseTree   aNode,
                                             T           aValue )
    {
        values.put( aNode, aValue );
    }


    /**
     * FILLIN
     *
     * @param       aElement
     */
    private void addElement( Element aElement )
    {
        assert aElement != null;
        
        // Is this the first element processed?
        if ( lastElement == copybook )
        {
            // Is the first element a non-record level element?
            if ( aElement.getLevel() != 1 && aElement.getLevel() != 77 )
            {
                // Is there an implicit record name available?
                String myImplicitRecordName = environment.getImplicitRecordName();
                if ( myImplicitRecordName == null )
                {
                    // TODO: implicit record information is required whenever the first element is not a record-level element (1 or 77)
                    throw new RuntimeException( "an implicit record name is required but not available" );
                }
                
                addElement( new DataElement( myImplicitRecordName, 1 ) );
            }
        }

        while ( aElement.getLevel() < lastElement.getLevel() )
        {
            lastElement = lastElement.getParent();
        }

        Element myParent =
                ( aElement.getLevel() == lastElement.getLevel() )
                        ? lastElement.getParent()
                        : lastElement;
        myParent.addChild( aElement );

        lastElement = aElement;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     */
    private void addLevel88Element( Level88Element aElement )
    {
        assert aElement != null;
        
        ((DataElement) lastElement).addLevel88Element( aElement );
    }


    /**
     * FILLIN
     *
     * @param       aPattern
     *
     * @return
     */
    private boolean isValidDatePattern( String aPattern )
    {
        boolean myIsValid = false;
        int     myBegin   = -1;
        int     myEnd     = 0;

        if ( aPattern.startsWith( "YYYY" ) )
        {
            myBegin = 4;
            myEnd   = aPattern.length();
        }
        else if ( aPattern.startsWith( "YY" ) )
        {
            myBegin = 2;
            myEnd   = aPattern.length();
        }
        else if ( aPattern.endsWith( "YYYY" ) )
        {
            myBegin = 0;
            myEnd   = aPattern.length()-4;
        }
        else if ( aPattern.endsWith( "YY" ) )
        {
            myBegin = 0;
            myEnd   = aPattern.length()-2;
        }

        if ( myBegin != -1 && myEnd - myBegin <= 4 )
        {
            while ( myBegin < myEnd )
            {
                if ( aPattern.charAt( myBegin ) != 'X' )
                {
                    break;
                }
            }

            if ( myBegin == myEnd )
            {
                myIsValid = true;
            }
        }

        return myIsValid;
    }


    /**
     * FILLIN
     * 
     * @param       aClauseType
     */
    private void checkForExistingElementClause( ElementClauseType aClauseType )
    {
        if ( existingElementClauses.contains( aClauseType ) )
        {
            // TODO: emit error
        }
        
        existingElementClauses.add( aClauseType );
    }
    
    
    
    
    //
    // CopybookParserListener interface implementation
    //
    
    
    @Override
    public void enterCopybook( @NotNull CopybookContext aContext )
    {
        copybook    = new Copybook();
        lastElement = copybook;
    }
    
    
    @Override
    public void exitCopybook( @NotNull CopybookContext aContext )
    {
        CopybookNormalizationVisitor myVisitor = new CopybookNormalizationVisitor( environment );
        myVisitor.normalize( copybook );
    }

    
    @Override
    public void enterLevelNumber( @NotNull LevelNumberContext aContext )
    {
        currentLevelNumber = aContext.value;
    }
    
    
    @Override
    public void enterDataDescriptionEntry( @NotNull DataDescriptionEntryContext aContext )
    {
        currentElement = null;
        existingElementClauses.clear();
        
        if ( aContext.dataName() != null )
        {
            String myDataName = aContext.dataName().getText();
            
            currentElement = new DataElement( myDataName, currentLevelNumber );
        }
        else if ( aContext.FILLER() != null )
        {
            currentElement = new FillerElement( currentLevelNumber );
        }
        else
        {
            currentElement = new NoNameElement( currentLevelNumber );
        }
        
        assert currentElement != null;
        
    }


    @Override
    public void exitDataDescriptionEntry( @NotNull DataDescriptionEntryContext aContext )
    {
        addElement( currentElement );
        
        currentElement = null;
        existingElementClauses.clear();
    }


    @Override
    public void exitLevel66DescriptionEntry( @NotNull Level66DescriptionEntryContext aContext )
    {
//        String         myDataName = aContext.dataName().WORD().getText();
//        Level66Element myElement  = new Level66Element( myDataName );
    }


    @Override
    public void exitConditionNameEntry( @NotNull ConditionNameEntryContext aContext )
    {
        String           myConditionName = aContext.conditionName().WORD().getText();
        List<ValueRange> myValueRanges   = new ArrayList<ValueRange>();
        
        for ( ConditionNameEntryValueContext myValueContext : aContext.values )
        {
            Value myValue1 = getValue( myValueContext.value1 );
            Value myValue2 = myValueContext.value2 != null ? getValue( myValueContext.value2 ) : null;
            
            myValueRanges.add( new ValueRange( myValue1, myValue2 != null ? myValue2 : myValue1 ) );
        }
        
        Level88Element myElement = new Level88Element( myConditionName, myValueRanges );
        
        addLevel88Element( myElement );
    }


    @Override
    public void exitRedefinesClause( @NotNull RedefinesClauseContext aContext )
    {
        String       myDataName     = aContext.dataName().getText();
        ElementProxy myElementProxy = new ElementProxy( myDataName );
        
        currentElement.setRedefinedElement( myElementProxy );
    }


    @Override
    public void exitBlankWhenZeroClause( @NotNull BlankWhenZeroClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.BlankWhenZeroClause );
        
        currentElement.setBlankWhenZero( true );
    }


    @Override
    public void exitExternalClause( @NotNull ExternalClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.ExternalClause );
        
        currentElement.setExternal( true );
    }
    
    
    @Override
    public void exitGlobalClause( @NotNull GlobalClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.GlobalClause );

        currentElement.setGlobal( true );
    }


    @Override
    public void exitGroupUsageClause( @NotNull GroupUsageClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.GroupUsageClause );
        
        currentElement.setNationalGroupUsage( true );
    }


    @Override
    public void exitJustifiedClause( @NotNull JustifiedClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.JustifiedClause );
        
        currentElement.setJustified( true );
    }


    @Override
    public void exitOccursClause( @NotNull OccursClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.OccursClause );

        if ( aContext.dependingName != null )
        {
            String myDependingName = aContext.dependingName.getText();
            
            currentElement.setOccursDependingOnElement( new ElementProxy( myDependingName ) );
        }
        
        NumericValue myNumericValue2 = getValue( aContext.value2 );
        int          myValue2        = myNumericValue2.getValue().intValue();
        
        if ( myValue2 <= 0 )
        {
            // TODO: provide an appropriate error
        }
        
        int myMaxValue = myValue2;
        int myMinValue;
        
        if ( aContext.value1 == null )
        {
            myMinValue = ( currentElement.getOccursDependingOnElement() != null ) ? 1 : myMaxValue;
        }
        else
        {
            NumericValue myNumericValue1 = getValue( aContext.value1 );
            int          myValue1        = myNumericValue1.getValue().intValue();
            
            if ( myValue1 <= 0 )
            {
                // TODO: provide an appropriate error
            }
            
            myMinValue = myValue1;
        }
        
        if ( myMinValue >= myMaxValue )
        {
            // TOOD: provide an appropriate error
        }
        
        currentElement.setOccurs( true );
        currentElement.setOccursMinValue( myMinValue );
        currentElement.setOccursMaxValue( myMaxValue );
    }


    @Override
    public void exitPictureClause( @NotNull PictureClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.PictureClause );
        
        try
        {
            List<PICSlice> myPICSlices = picLexer.parse( aContext.PICTURE_STRING().getText() );
            
            currentElement.setPICSlices( myPICSlices );
        }
        catch ( PICException myPICException )
        {
            // TODO: implement parse error feedback
        }
    }


    @Override
    public void exitSignClause( @NotNull SignClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.SignClause );
        
        if ( aContext.LEADING() != null )
        {
            currentElement.setSignPosition( SignPosition.Leading );
        }
        else if ( aContext.TRAILING() != null )
        {
            currentElement.setSignPosition( SignPosition.Trailing );
        }
        
        if ( aContext.SEPARATE() != null )
        {
            currentElement.setSignType( SignType.Separate );
        }
    }


    @Override
    public void exitSynchronizedClause( @NotNull SynchronizedClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.SynchronizedClause );
        
        if ( aContext.LEFT() != null )
        {
            currentElement.setSyncPosition( SyncPosition.Left );
        }
        else if ( aContext.RIGHT() != null )
        {
            currentElement.setSyncPosition( SyncPosition.Right );
        }
        else
        {
            currentElement.setSyncPosition( SyncPosition.None );
        }
    }


    @Override
    public void exitUsageClause( @NotNull UsageClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.UsageClause );
        
        UsageType myUsageType = null;
        
        if
        (
            aContext.BINARY()          != null ||
            aContext.COMP()            != null ||
            aContext.COMPUTATIONAL()   != null ||
            aContext.COMP_4()          != null ||
            aContext.COMPUTATIONAL_4() != null
        )
        {
            myUsageType = UsageType.Binary;
        }
        else if
        (
            aContext.COMP_1()          != null ||
            aContext.COMPUTATIONAL_1() != null
        )
        {
            myUsageType = UsageType.Computational1;
        }
        else if
        (
            aContext.COMP_2()          != null ||
            aContext.COMPUTATIONAL_2() != null
        )
        {
            myUsageType = UsageType.Computational2;
        }
        else if
        (
            aContext.COMP_3()          != null ||
            aContext.COMPUTATIONAL_3() != null ||
            aContext.PACKED_DECIMAL()  != null
        )
        {
            myUsageType = UsageType.Computational3;
        }
        else if
        (
            aContext.COMP_5()          != null ||
            aContext.COMPUTATIONAL_5() != null
        )
        {
            myUsageType = UsageType.Computational5;
        }
        else if
        (
            aContext.DISPLAY() != null
        )
        {
            myUsageType = UsageType.Display;
        }
        else if
        (
            aContext.DISPLAY_1() != null
        )
        {
            myUsageType = UsageType.Display1;
        }
        else if
        (
            aContext.NATIONAL() != null
        )
        {
            myUsageType = UsageType.National;
        }
        else if
        (
            aContext.INDEX() != null
        )
        {
            myUsageType = UsageType.Index;
        }
        else if
        (
            aContext.POINTER() != null
        )
        {
            myUsageType = UsageType.Pointer;
        }
        else if
        (
            aContext.PROCEDURE_POINTER() != null
        )
        {
            myUsageType = UsageType.ProcedurePointer;
        }
        else if
        (
            aContext.FUNCTION_POINTER() != null
        )
        {
            myUsageType = UsageType.FunctionPointer;
        }
        else if
        (
            aContext.objRefName != null
        )
        {
            myUsageType = UsageType.ObjectReference;
            
            // TODO: warn that object references are not supported
        }

        currentElement.setUsageType( myUsageType );
    }


    @Override
    public void exitValueClause( @NotNull ValueClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.ValueClause );
        
        Value myValue = getValue( aContext.literal() );
        
        currentElement.setValue( myValue );
    }


    @Override
    public void exitDateFormatClause( @NotNull DateFormatClauseContext aContext )
    {
        checkForExistingElementClause( ElementClauseType.DateFormatClause );
        
        String myPattern = aContext.WORD().getText().toUpperCase();
        
        if ( ! isValidDatePattern( myPattern ) )
        {
            // TODO: complain about the format
        }
        
        currentElement.setDatePattern( myPattern );
    }
    
    
    @Override
    public void exitRenamesClause( @NotNull RenamesClauseContext aContext )
    {
    }


    @Override
    public void exitLiteral( @NotNull LiteralContext aContext )
    {
        ParseTree myParseTree = null;
        
        if ( aContext.integerLiteral() != null )
        {
            myParseTree = aContext.integerLiteral();
        }
        else if ( aContext.floatingPointLiteral() != null )
        {
            myParseTree = aContext.floatingPointLiteral();
        }
        else if ( aContext.characterLiteral() != null )
        {
            myParseTree = aContext.characterLiteral();
        }
        else if ( aContext.zeroLiteral() != null )
        {
            myParseTree = aContext.zeroLiteral();
        }
        else if ( aContext.spaceLiteral() != null )
        {
            myParseTree = aContext.spaceLiteral();
        }
        else if ( aContext.highValueLiteral() != null )
        {
            myParseTree = aContext.highValueLiteral();
        }
        else if ( aContext.lowValueLiteral() != null )
        {
            myParseTree = aContext.lowValueLiteral();
        }
        else if ( aContext.quoteLiteral() != null )
        {
            myParseTree = aContext.quoteLiteral();
        }
        else if ( aContext.nullLiteral() != null )
        {
            myParseTree = aContext.nullLiteral();
        }

        assert myParseTree != null;
        
        Value myValue = getValue( myParseTree );
        
        setValue( aContext, myValue );
    }


    @Override
    public void exitIntegerLiteral( @NotNull IntegerLiteralContext aContext )
    {
        Value myValue = new NumericValue( new BigDecimal( aContext.INTEGER_LITERAL().getText() ) );
        setValue( aContext, myValue );
    }


    @Override
    public void exitFloatingPointLiteral( @NotNull FloatingPointLiteralContext aContext )
    {
        Value myValue = new NumericValue( new BigDecimal( aContext.FLOATING_POINT_LITERAL().getText() ) );
        setValue( aContext, myValue );
    }


    @Override
    public void exitCharacterLiteral( @NotNull CharacterLiteralContext aContext )
    {
        Value myValue = new StringValue( aContext.CHARACTER_LITERAL().getText() );
        setValue( aContext, myValue );
    }


    @Override
    public void exitZeroLiteral( @NotNull ZeroLiteralContext aContext )
    {
        setValue( aContext, new ZeroValue() );
    }


    @Override
    public void exitSpaceLiteral( @NotNull SpaceLiteralContext aContext )
    {
        setValue( aContext, new SpaceValue() );
    }


    @Override
    public void exitHighValueLiteral( @NotNull HighValueLiteralContext aContext )
    {
        setValue( aContext, new HighValue() );
    }


    @Override
    public void exitLowValueLiteral( @NotNull LowValueLiteralContext aContext )
    {
        setValue( aContext, new LowValue() );
    }


    @Override
    public void exitQuoteLiteral( @NotNull QuoteLiteralContext aContext )
    {
        setValue( aContext, new QuoteValue() );
    }


    @Override
    public void exitNullLiteral( @NotNull NullLiteralContext aContext )
    {
        setValue( aContext, new NullValue() );
    }
}

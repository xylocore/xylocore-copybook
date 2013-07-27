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


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DebugVisitor
    extends
        Visitor
{
    //
    // Members
    //
    
    
    private int indent      = 0;
    private int indentWidth = 4;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    protected void write( String aValue )
    {
        for ( int i = 0 ; i < indent*indentWidth ; i++ )
        {
            System.err.write( ' ' );
        }
        
        System.err.println( aValue );
    }
    
    
    /**
     * FILLIN
     */
    protected void indent()
    {
        indent( 1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLevel
     */
    protected void indent( int aLevel )
    {
        indent += aLevel;
    }
    
    
    /**
     * FILLIN
     */
    protected void outdent()
    {
        indent( -1 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    protected String getName( Element aElement )
    {
        String myName = aElement.getName();
        return ( myName != null ) ? myName : "";
    }
    

    @Override
    public void visitCopybook( Copybook aCopybook )
    {
        write( "visitCopybook " + getName( aCopybook ) );
    }
    

    @Override
    public void leaveCopybook( Copybook aCopybook )
    {
        write( "leaveCopybook " + getName( aCopybook ) );
    }
    
    
    @Override
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    @Override
    public void visitLevel66Element( Level66Element aElement )
    {
        write( "visitLevel66Element " + getName( aElement ) );
    }
    
    
    @Override
    public void leaveLevel66Element( Level66Element aElement )
    {
        write( "leaveLevel66Element " + getName( aElement ) );
    }
    
    
    @Override
    public boolean shouldVisitLevel88Elements( DataElement aElement )
    {
        return true;
    }
    
    
    @Override
    public void visitLevel88Element( Level88Element aElement )
    {
        write( "visitLevel88Element " + getName( aElement ) );
    }
    
    
    @Override
    public void leaveLevel88Element( Level88Element aElement )
    {
        write( "leaveLevel88Element " + getName( aElement ) );
    }
    
    
    @Override
    public void visitDataElement( DataElement aElement )
    {
        write( "visitDataElement " + getName( aElement ) );
    }
    
    
    @Override
    public void leaveDataElement( DataElement aElement )
    {
        write( "leaveDataElement " + getName( aElement ) );
    }
    
    
    @Override
    public void visitFillerElement( FillerElement aElement )
    {
        write( "visitFillerElement " + getName( aElement ) );
    }
    
    
    @Override
    public void leaveFillerElement( FillerElement aElement )
    {
        write( "leaveFillerElement " + getName( aElement ) );
    }
    
    
    @Override
    public void visitNoNameElement( NoNameElement aElement )
    {
        write( "visitNoNameElement " + getName( aElement ) );
    }
    
    
    @Override
    public void leaveNoNameElement( NoNameElement aElement )
    {
        write( "leaveNoNameElement " + getName( aElement ) );
    }
    
    
    @Override
    public void visitElement( Element aElement )
    {
        describeElement( aElement, "visitElement" );

        indent();
    }
    
    
    @Override
    public void leaveElement( Element aElement )
    {
        outdent();
        
        describeElement( aElement, "leaveElement" );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aPrefix
     */
    private void describeElement( Element   aElement,
                                  String    aPrefix   )
    {
        StringBuilder myBuffer = new StringBuilder();
        
        myBuffer.append( aPrefix ).append( " " ).append( getName( aElement ) )
                .append( ": elementType="    ).append( aElement.getElementType()        )
                .append( ", usageType="      ).append( aElement.getUsageType()          )
                .append( ", effUsageType="   ).append( aElement.getEffectiveUsageType() )
                .append( ", offset="         ).append( aElement.getOffset()             )
                .append( ", size="           ).append( aElement.getSize()               )
                .append( ", nonIndexedSize=" ).append( aElement.getNonIndexedSize()     )
                .append( ", signPos="        ).append( aElement.getSignPosition()       )
                .append( ", signType="       ).append( aElement.getSignType()           )
                .append( ", dataType="       ).append( aElement.getDataType()           )
                .append( ", prec="           ).append( aElement.getPrecision()          )
                .append( ", scalePos="       ).append( aElement.getScalingPosition()    )
                .append( ", digits="         ).append( aElement.getDigits()             )
                ;

        if ( aElement.isGlobal() )
        {
            myBuffer.append( ", GLOBAL" );
        }
        if ( aElement.isExternal() )
        {
            myBuffer.append( ", EXTERNAL" );
        }
        if ( aElement.isBlankWhenZero() )
        {
            myBuffer.append( ", BLANK_WHEN_ZERO" );
        }
        if ( aElement.isJustified() )
        {
            myBuffer.append( ", JUSTIFIED" );
        }
        if ( aElement.isNationalGroupUsage() )
        {
            myBuffer.append( ", NATIONAL_GROUP_USAGE" );
        }
        if ( aElement.isMaintainWhitespace() )
        {
            myBuffer.append( ", MAINTAIN_WHITESPACE" );
        }
        if ( aElement.isOccurs() )
        {
            myBuffer.append( ", OCCURS" );
        }
        if ( aElement.isDisplayPresent() )
        {
            myBuffer.append( ", DISPLAY_PRESENT" );
        }
        if ( aElement.isDbcsPresent() )
        {
            myBuffer.append( ", DBCS_PRESENT" );
        }
        if ( aElement.isNationalPresent() )
        {
            myBuffer.append( ", NATIONAL_PRESENT" );
        }
        
        write( myBuffer.toString() );
    }
    
    
    @Override
    public void visitAccessorMethodInfo( AccessorMethodInfo aInfo )
    {
        indent();
        write( "visitAccessorMethodInfo" );
        outdent();
    }


    @Override
    public void leaveAccessorMethodInfo( AccessorMethodInfo aInfo )
    {
        indent();
        write( "leaveAccessorMethodInfo" );
        outdent();
    }
}

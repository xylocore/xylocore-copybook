package com.xylocore.copybook.generator.emit;

import java.io.IOException;
import java.io.Writer;

import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BufferEmitter
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
     * 
     * @param       aIndentWidth
     */
    public BufferEmitter( int aIndentWidth )
    {
        assert aIndentWidth > 0;
        
        buffer = new StringBuilder( 1024 );
        
        reset( aIndentWidth );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getIndentLevel()
    {
        return indentLevel;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getIndentWidth()
    {
        return indentWidth;
    }
 
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BufferEmitter increment()
    {
        return increment( 1 );
    }
 
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * 
     * @return
     */
    public BufferEmitter increment( int aLevelDelta )
    {
        indentLevel += aLevelDelta;
        
        return this;
    }
 
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BufferEmitter decrement()
    {
        return decrement( 1 );
    }
 
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * 
     * @return
     */
    public BufferEmitter decrement( int aLevelDelta )
    {
        indentLevel -= aLevelDelta;
        
        return this;
    }
    

    /**
     * FILLIN
     * 
     * @param       aString
     * 
     * @return
     */
    public BufferEmitter append( CharSequence aString )
    {
        buffer.append( aString );
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     * 
     * @return
     */
    public BufferEmitter append( int aValue )
    {
        buffer.append( aValue );
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValues
     * 
     * @return
     */
    public BufferEmitter append( Object ... aValues )
    {
        if ( aValues != null && aValues.length != 0 )
        {
            for ( Object myValue : aValues )
            {
                append( myValue != null ? myValue.toString() : null );
            }
        }
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BufferEmitter newline()
    {
        buffer.append( '\n' );
        
        return this;
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    public BufferEmitter indent()
    {
        return indent( 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * 
     * @return
     */
    public BufferEmitter indent( int aLevelDelta )
    {
        FormatHelper.stringOfCharacters( buffer, ' ', calculateIndent( aLevelDelta ) );
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValues
     * 
     * @return
     */
    public BufferEmitter line( Object ... aValues )
    {
        return indentLine( 0, aValues );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValues
     * 
     * @return
     */
    public BufferEmitter line1( Object ... aValues )
    {
        return indentLine( 1, aValues );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValues
     * 
     * @return
     */
    public BufferEmitter line2( Object ... aValues )
    {
        return indentLine( 2, aValues );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * @param       aValues
     * 
     * @return
     */
    public BufferEmitter indentLine( int          aLevelDelta,
                                     Object ...   aValues      )
    {
        if ( aValues != null && aValues.length != 0 )
        {
            indent( aLevelDelta );
            append( aValues );
        }
        
        newline();
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BufferEmitter clear()
    {
        buffer.setLength( 0 );
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aIndentWidth
     * 
     * @return
     */
    public BufferEmitter reset( int aIndentWidth )
    {
        indentLevel = 0;
        indentWidth = aIndentWidth;
        
        clear();
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aWriter
     * 
     * @return
     * 
     * @throws      IOException
     */
    public BufferEmitter write( Writer aWriter )
            throws IOException
    {
        aWriter.append( buffer );
        
        clear();
        
        return this;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public StringBuilder getBuffer()
    {
        return buffer;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLevelDelta
     * 
     * @return
     */
    private int calculateIndent( int aLevelDelta )
    {
        return (indentLevel + aLevelDelta) * indentWidth;
    }
}

package com.xylocore.commons.data.copybook.runtime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractCopybook
    extends
        AbstractCopybookBase
{
    //
    // NumericDisplayPICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public boolean isNumericDisplayBlank( CopybookContext   aContext,     
                                          int               aOffset,      
                                          int               aDigits,      
                                          SignType          aSignType,    
                                          SignPosition      aSignPosition  )
    {
        aContext.clearError();
        boolean myBlank = numericDisplayMarshaller.isBlank( aContext,     
                                                            aOffset,      
                                                            aDigits,      
                                                            aSignType,    
                                                            aSignPosition  );
        checkForError( aContext );
        return myBlank;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsByteValid( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                SignPosition      aSignPosition,   
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsByte( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aSignPosition,   
                                                       aPrecision,      
                                                       aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeNumericDisplayAsByte( CopybookContext   aContext,        
                                            int               aOffset,         
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        byte myValue = numericDisplayMarshaller.decodeAsByte( aContext,        
                                                              aOffset,         
                                                              aDigits,         
                                                              aSignType,       
                                                              aSignPosition,   
                                                              aPrecision,      
                                                              aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsByte( CopybookContext   aContext,        
                                            int               aOffset,         
                                            byte              aValue,          
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsByte( aContext,        
                                               aOffset,         
                                               aValue,          
                                               aDigits,         
                                               aSignType,       
                                               aSignPosition,   
                                               aPrecision,      
                                               aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsCharValid( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                SignPosition      aSignPosition,   
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsChar( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aSignPosition,   
                                                       aPrecision,      
                                                       aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeNumericDisplayAsChar( CopybookContext   aContext,        
                                            int               aOffset,         
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        char myValue = numericDisplayMarshaller.decodeAsChar( aContext,        
                                                              aOffset,         
                                                              aDigits,         
                                                              aSignType,       
                                                              aSignPosition,   
                                                              aPrecision,      
                                                              aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsChar( CopybookContext   aContext,        
                                            int               aOffset,         
                                            char              aValue,          
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsChar( aContext,        
                                               aOffset,         
                                               aValue,          
                                               aDigits,         
                                               aSignType,       
                                               aSignPosition,   
                                               aPrecision,      
                                               aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsShortValid( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsShort( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aSignPosition,   
                                                        aPrecision,      
                                                        aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeNumericDisplayAsShort( CopybookContext   aContext,        
                                              int               aOffset,         
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        short myValue = numericDisplayMarshaller.decodeAsShort( aContext,        
                                                                aOffset,         
                                                                aDigits,         
                                                                aSignType,       
                                                                aSignPosition,   
                                                                aPrecision,      
                                                                aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsShort( CopybookContext   aContext,        
                                             int               aOffset,         
                                             short             aValue,          
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsShort( aContext,        
                                                aOffset,         
                                                aValue,          
                                                aDigits,         
                                                aSignType,       
                                                aSignPosition,   
                                                aPrecision,      
                                                aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsIntegerValid( CopybookContext   aContext,        
                                                   int               aOffset,         
                                                   int               aDigits,         
                                                   SignType          aSignType,       
                                                   SignPosition      aSignPosition,   
                                                   int               aPrecision,      
                                                   int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsInteger( aContext,        
                                                          aOffset,         
                                                          aDigits,         
                                                          aSignType,       
                                                          aSignPosition,   
                                                          aPrecision,      
                                                          aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeNumericDisplayAsInteger( CopybookContext   aContext,        
                                              int               aOffset,         
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        int myValue = numericDisplayMarshaller.decodeAsInteger( aContext,        
                                                                aOffset,         
                                                                aDigits,         
                                                                aSignType,       
                                                                aSignPosition,   
                                                                aPrecision,      
                                                                aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsInteger( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aValue,          
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               SignPosition      aSignPosition,   
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsInteger( aContext,        
                                                  aOffset,         
                                                  aValue,          
                                                  aDigits,         
                                                  aSignType,       
                                                  aSignPosition,   
                                                  aPrecision,      
                                                  aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsLongValid( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                SignPosition      aSignPosition,   
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsLong( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aSignPosition,   
                                                       aPrecision,      
                                                       aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public long decodeNumericDisplayAsLong( CopybookContext   aContext,        
                                            int               aOffset,         
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        long myValue = numericDisplayMarshaller.decodeAsLong( aContext,        
                                                              aOffset,         
                                                              aDigits,         
                                                              aSignType,       
                                                              aSignPosition,   
                                                              aPrecision,      
                                                              aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsLong( CopybookContext   aContext,        
                                            int               aOffset,         
                                            long              aValue,          
                                            int               aDigits,         
                                            SignType          aSignType,       
                                            SignPosition      aSignPosition,   
                                            int               aPrecision,      
                                            int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsLong( aContext,        
                                               aOffset,         
                                               aValue,          
                                               aDigits,         
                                               aSignType,       
                                               aSignPosition,   
                                               aPrecision,      
                                               aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsFloatValid( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsFloat( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aSignPosition,   
                                                        aPrecision,      
                                                        aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeNumericDisplayAsFloat( CopybookContext   aContext,        
                                              int               aOffset,         
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        float myValue = numericDisplayMarshaller.decodeAsFloat( aContext,        
                                                                aOffset,         
                                                                aDigits,         
                                                                aSignType,       
                                                                aSignPosition,   
                                                                aPrecision,      
                                                                aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsFloat( CopybookContext   aContext,        
                                             int               aOffset,         
                                             float             aValue,          
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsFloat( aContext,        
                                                aOffset,         
                                                aValue,          
                                                aDigits,         
                                                aSignType,       
                                                aSignPosition,   
                                                aPrecision,      
                                                aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayAsDoubleValid( CopybookContext   aContext,        
                                                  int               aOffset,         
                                                  int               aDigits,         
                                                  SignType          aSignType,       
                                                  SignPosition      aSignPosition,   
                                                  int               aPrecision,      
                                                  int               aScalingPosition  )
    {
        return numericDisplayMarshaller.isValidAsDouble( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aSignPosition,   
                                                         aPrecision,      
                                                         aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeNumericDisplayAsDouble( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                SignPosition      aSignPosition,   
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        aContext.clearError();
        double myValue = numericDisplayMarshaller.decodeAsDouble( aContext,        
                                                                  aOffset,         
                                                                  aDigits,         
                                                                  aSignType,       
                                                                  aSignPosition,   
                                                                  aPrecision,      
                                                                  aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericDisplayAsDouble( CopybookContext   aContext,        
                                              int               aOffset,         
                                              double            aValue,          
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsDouble( aContext,        
                                                 aOffset,         
                                                 aValue,          
                                                 aDigits,         
                                                 aSignType,       
                                                 aSignPosition,   
                                                 aPrecision,      
                                                 aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericDisplayAsBigIntegerValid( CopybookContext            aContext,                 
                                                      int                        aOffset,                  
                                                      int                        aDigits,                  
                                                      SignType                   aSignType,                
                                                      SignPosition               aSignPosition,            
                                                      int                        aPrecision,               
                                                      int                        aScalingPosition,         
                                                      NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericDisplayMarshaller.isValidAsBigInteger( aContext,                 
                                                             aOffset,                  
                                                             aDigits,                  
                                                             aSignType,                
                                                             aSignPosition,            
                                                             aPrecision,               
                                                             aScalingPosition,         
                                                             aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigInteger decodeNumericDisplayAsBigInteger( CopybookContext            aContext,                 
                                                        int                        aOffset,                  
                                                        int                        aDigits,                  
                                                        SignType                   aSignType,                
                                                        SignPosition               aSignPosition,            
                                                        int                        aPrecision,               
                                                        int                        aScalingPosition,         
                                                        NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigInteger myValue = numericDisplayMarshaller.decodeAsBigInteger( aContext,                 
                                                                          aOffset,                  
                                                                          aDigits,                  
                                                                          aSignType,                
                                                                          aSignPosition,            
                                                                          aPrecision,               
                                                                          aScalingPosition,         
                                                                          aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericDisplayAsBigInteger( CopybookContext            aContext,                 
                                                  int                        aOffset,                  
                                                  BigInteger                 aValue,                   
                                                  int                        aDigits,                  
                                                  SignType                   aSignType,                
                                                  SignPosition               aSignPosition,            
                                                  int                        aPrecision,               
                                                  int                        aScalingPosition,         
                                                  NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsBigInteger( aContext,                 
                                                     aOffset,                  
                                                     aValue,                   
                                                     aDigits,                  
                                                     aSignType,                
                                                     aSignPosition,            
                                                     aPrecision,               
                                                     aScalingPosition,         
                                                     aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericDisplayAsBigDecimalValid( CopybookContext            aContext,                 
                                                      int                        aOffset,                  
                                                      int                        aDigits,                  
                                                      SignType                   aSignType,                
                                                      SignPosition               aSignPosition,            
                                                      int                        aPrecision,               
                                                      int                        aScalingPosition,         
                                                      NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericDisplayMarshaller.isValidAsBigDecimal( aContext,                 
                                                             aOffset,                  
                                                             aDigits,                  
                                                             aSignType,                
                                                             aSignPosition,            
                                                             aPrecision,               
                                                             aScalingPosition,         
                                                             aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigDecimal decodeNumericDisplayAsBigDecimal( CopybookContext            aContext,                 
                                                        int                        aOffset,                  
                                                        int                        aDigits,                  
                                                        SignType                   aSignType,                
                                                        SignPosition               aSignPosition,            
                                                        int                        aPrecision,               
                                                        int                        aScalingPosition,         
                                                        NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigDecimal myValue = numericDisplayMarshaller.decodeAsBigDecimal( aContext,                 
                                                                          aOffset,                  
                                                                          aDigits,                  
                                                                          aSignType,                
                                                                          aSignPosition,            
                                                                          aPrecision,               
                                                                          aScalingPosition,         
                                                                          aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericDisplayAsBigDecimal( CopybookContext            aContext,                 
                                                  int                        aOffset,                  
                                                  BigDecimal                 aValue,                   
                                                  int                        aDigits,                  
                                                  SignType                   aSignType,                
                                                  SignPosition               aSignPosition,            
                                                  int                        aPrecision,               
                                                  int                        aScalingPosition,         
                                                  NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsBigDecimal( aContext,                 
                                                     aOffset,                  
                                                     aValue,                   
                                                     aDigits,                  
                                                     aSignType,                
                                                     aSignPosition,            
                                                     aPrecision,               
                                                     aScalingPosition,         
                                                     aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericDisplayAsStringValid( CopybookContext            aContext,                 
                                                  int                        aOffset,                  
                                                  int                        aDigits,                  
                                                  SignType                   aSignType,                
                                                  SignPosition               aSignPosition,            
                                                  int                        aPrecision,               
                                                  int                        aScalingPosition,         
                                                  NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericDisplayMarshaller.isValidAsString( aContext,                 
                                                         aOffset,                  
                                                         aDigits,                  
                                                         aSignType,                
                                                         aSignPosition,            
                                                         aPrecision,               
                                                         aScalingPosition,         
                                                         aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public String decodeNumericDisplayAsString( CopybookContext            aContext,                 
                                                int                        aOffset,                  
                                                int                        aDigits,                  
                                                SignType                   aSignType,                
                                                SignPosition               aSignPosition,            
                                                int                        aPrecision,               
                                                int                        aScalingPosition,         
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        String myValue = numericDisplayMarshaller.decodeAsString( aContext,                 
                                                                  aOffset,                  
                                                                  aDigits,                  
                                                                  aSignType,                
                                                                  aSignPosition,            
                                                                  aPrecision,               
                                                                  aScalingPosition,         
                                                                  aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericDisplayAsString( CopybookContext            aContext,                 
                                              int                        aOffset,                  
                                              String                     aValue,                   
                                              int                        aDigits,                  
                                              SignType                   aSignType,                
                                              SignPosition               aSignPosition,            
                                              int                        aPrecision,               
                                              int                        aScalingPosition,         
                                              NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsString( aContext,                 
                                                 aOffset,                  
                                                 aValue,                   
                                                 aDigits,                  
                                                 aSignType,                
                                                 aSignPosition,            
                                                 aPrecision,               
                                                 aScalingPosition,         
                                                 aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericDisplayAsDateValid( CopybookContext            aContext,                 
                                                int                        aOffset,                  
                                                int                        aDigits,                  
                                                SignType                   aSignType,                
                                                SignPosition               aSignPosition,            
                                                int                        aPrecision,               
                                                int                        aScalingPosition,         
                                                Converter                  aConverter,               
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericDisplayMarshaller.isValidAsDate( aContext,                 
                                                       aOffset,                  
                                                       aDigits,                  
                                                       aSignType,                
                                                       aSignPosition,            
                                                       aPrecision,               
                                                       aScalingPosition,         
                                                       aConverter,               
                                                       aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public Date decodeNumericDisplayAsDate( CopybookContext            aContext,                 
                                            int                        aOffset,                  
                                            int                        aDigits,                  
                                            SignType                   aSignType,                
                                            SignPosition               aSignPosition,            
                                            int                        aPrecision,               
                                            int                        aScalingPosition,         
                                            Converter                  aConverter,               
                                            NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        Date myValue = numericDisplayMarshaller.decodeAsDate( aContext,                 
                                                              aOffset,                  
                                                              aDigits,                  
                                                              aSignType,                
                                                              aSignPosition,            
                                                              aPrecision,               
                                                              aScalingPosition,         
                                                              aConverter,               
                                                              aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericDisplayAsDate( CopybookContext            aContext,                 
                                            int                        aOffset,                  
                                            Date                       aValue,                   
                                            int                        aDigits,                  
                                            SignType                   aSignType,                
                                            SignPosition               aSignPosition,            
                                            int                        aPrecision,               
                                            int                        aScalingPosition,         
                                            Converter                  aConverter,               
                                            NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericDisplayMarshaller.encodeAsDate( aContext,                 
                                               aOffset,                  
                                               aValue,                   
                                               aDigits,                  
                                               aSignType,                
                                               aSignPosition,            
                                               aPrecision,               
                                               aScalingPosition,         
                                               aConverter,               
                                               aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericDisplayConditionNameValid( CopybookContext                         aContext,                   
                                                       int                                     aOffset,                    
                                                       String                                  aConditionName,             
                                                       java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                                       int                                     aDigits,                    
                                                       SignType                                aSignType,                  
                                                       SignPosition                            aSignPosition,              
                                                       int                                     aPrecision,                 
                                                       int                                     aScalingPosition             )
    {
        return numericDisplayMarshaller.isConditionNameValid( aContext,                   
                                                              aOffset,                    
                                                              aConditionName,             
                                                              aConditionNameValueMappings,
                                                              aDigits,                    
                                                              aSignType,                  
                                                              aSignPosition,              
                                                              aPrecision,                 
                                                              aScalingPosition             );
    }




    //
    // NumericNationalPICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public boolean isNumericNationalBlank( CopybookContext   aContext,     
                                           int               aOffset,      
                                           int               aDigits,      
                                           SignType          aSignType,    
                                           SignPosition      aSignPosition  )
    {
        aContext.clearError();
        boolean myBlank = numericNationalMarshaller.isBlank( aContext,     
                                                             aOffset,      
                                                             aDigits,      
                                                             aSignType,    
                                                             aSignPosition  );
        checkForError( aContext );
        return myBlank;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsByteValid( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsByte( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aSignPosition,   
                                                        aPrecision,      
                                                        aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeNumericNationalAsByte( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        byte myValue = numericNationalMarshaller.decodeAsByte( aContext,        
                                                               aOffset,         
                                                               aDigits,         
                                                               aSignType,       
                                                               aSignPosition,   
                                                               aPrecision,      
                                                               aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsByte( CopybookContext   aContext,        
                                             int               aOffset,         
                                             byte              aValue,          
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsByte( aContext,        
                                                aOffset,         
                                                aValue,          
                                                aDigits,         
                                                aSignType,       
                                                aSignPosition,   
                                                aPrecision,      
                                                aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsCharValid( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsChar( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aSignPosition,   
                                                        aPrecision,      
                                                        aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeNumericNationalAsChar( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        char myValue = numericNationalMarshaller.decodeAsChar( aContext,        
                                                               aOffset,         
                                                               aDigits,         
                                                               aSignType,       
                                                               aSignPosition,   
                                                               aPrecision,      
                                                               aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsChar( CopybookContext   aContext,        
                                             int               aOffset,         
                                             char              aValue,          
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsChar( aContext,        
                                                aOffset,         
                                                aValue,          
                                                aDigits,         
                                                aSignType,       
                                                aSignPosition,   
                                                aPrecision,      
                                                aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsShortValid( CopybookContext   aContext,        
                                                  int               aOffset,         
                                                  int               aDigits,         
                                                  SignType          aSignType,       
                                                  SignPosition      aSignPosition,   
                                                  int               aPrecision,      
                                                  int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsShort( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aSignPosition,   
                                                         aPrecision,      
                                                         aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeNumericNationalAsShort( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               SignPosition      aSignPosition,   
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        short myValue = numericNationalMarshaller.decodeAsShort( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aSignPosition,   
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsShort( CopybookContext   aContext,        
                                              int               aOffset,         
                                              short             aValue,          
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsShort( aContext,        
                                                 aOffset,         
                                                 aValue,          
                                                 aDigits,         
                                                 aSignType,       
                                                 aSignPosition,   
                                                 aPrecision,      
                                                 aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsIntegerValid( CopybookContext   aContext,        
                                                    int               aOffset,         
                                                    int               aDigits,         
                                                    SignType          aSignType,       
                                                    SignPosition      aSignPosition,   
                                                    int               aPrecision,      
                                                    int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsInteger( aContext,        
                                                           aOffset,         
                                                           aDigits,         
                                                           aSignType,       
                                                           aSignPosition,   
                                                           aPrecision,      
                                                           aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeNumericNationalAsInteger( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               SignPosition      aSignPosition,   
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        int myValue = numericNationalMarshaller.decodeAsInteger( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aSignPosition,   
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsInteger( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aValue,          
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                SignPosition      aSignPosition,   
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsInteger( aContext,        
                                                   aOffset,         
                                                   aValue,          
                                                   aDigits,         
                                                   aSignType,       
                                                   aSignPosition,   
                                                   aPrecision,      
                                                   aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsLongValid( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsLong( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aSignPosition,   
                                                        aPrecision,      
                                                        aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public long decodeNumericNationalAsLong( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        long myValue = numericNationalMarshaller.decodeAsLong( aContext,        
                                                               aOffset,         
                                                               aDigits,         
                                                               aSignType,       
                                                               aSignPosition,   
                                                               aPrecision,      
                                                               aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsLong( CopybookContext   aContext,        
                                             int               aOffset,         
                                             long              aValue,          
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             SignPosition      aSignPosition,   
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsLong( aContext,        
                                                aOffset,         
                                                aValue,          
                                                aDigits,         
                                                aSignType,       
                                                aSignPosition,   
                                                aPrecision,      
                                                aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsFloatValid( CopybookContext   aContext,        
                                                  int               aOffset,         
                                                  int               aDigits,         
                                                  SignType          aSignType,       
                                                  SignPosition      aSignPosition,   
                                                  int               aPrecision,      
                                                  int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsFloat( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aSignPosition,   
                                                         aPrecision,      
                                                         aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeNumericNationalAsFloat( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               SignPosition      aSignPosition,   
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        float myValue = numericNationalMarshaller.decodeAsFloat( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aSignPosition,   
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsFloat( CopybookContext   aContext,        
                                              int               aOffset,         
                                              float             aValue,          
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              SignPosition      aSignPosition,   
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsFloat( aContext,        
                                                 aOffset,         
                                                 aValue,          
                                                 aDigits,         
                                                 aSignType,       
                                                 aSignPosition,   
                                                 aPrecision,      
                                                 aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalAsDoubleValid( CopybookContext   aContext,        
                                                   int               aOffset,         
                                                   int               aDigits,         
                                                   SignType          aSignType,       
                                                   SignPosition      aSignPosition,   
                                                   int               aPrecision,      
                                                   int               aScalingPosition  )
    {
        return numericNationalMarshaller.isValidAsDouble( aContext,        
                                                          aOffset,         
                                                          aDigits,         
                                                          aSignType,       
                                                          aSignPosition,   
                                                          aPrecision,      
                                                          aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeNumericNationalAsDouble( CopybookContext   aContext,        
                                                 int               aOffset,         
                                                 int               aDigits,         
                                                 SignType          aSignType,       
                                                 SignPosition      aSignPosition,   
                                                 int               aPrecision,      
                                                 int               aScalingPosition  )
    {
        aContext.clearError();
        double myValue = numericNationalMarshaller.decodeAsDouble( aContext,        
                                                                   aOffset,         
                                                                   aDigits,         
                                                                   aSignType,       
                                                                   aSignPosition,   
                                                                   aPrecision,      
                                                                   aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeNumericNationalAsDouble( CopybookContext   aContext,        
                                               int               aOffset,         
                                               double            aValue,          
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               SignPosition      aSignPosition,   
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsDouble( aContext,        
                                                  aOffset,         
                                                  aValue,          
                                                  aDigits,         
                                                  aSignType,       
                                                  aSignPosition,   
                                                  aPrecision,      
                                                  aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericNationalAsBigIntegerValid( CopybookContext            aContext,                 
                                                       int                        aOffset,                  
                                                       int                        aDigits,                  
                                                       SignType                   aSignType,                
                                                       SignPosition               aSignPosition,            
                                                       int                        aPrecision,               
                                                       int                        aScalingPosition,         
                                                       NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericNationalMarshaller.isValidAsBigInteger( aContext,                 
                                                              aOffset,                  
                                                              aDigits,                  
                                                              aSignType,                
                                                              aSignPosition,            
                                                              aPrecision,               
                                                              aScalingPosition,         
                                                              aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigInteger decodeNumericNationalAsBigInteger( CopybookContext            aContext,                 
                                                         int                        aOffset,                  
                                                         int                        aDigits,                  
                                                         SignType                   aSignType,                
                                                         SignPosition               aSignPosition,            
                                                         int                        aPrecision,               
                                                         int                        aScalingPosition,         
                                                         NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigInteger myValue = numericNationalMarshaller.decodeAsBigInteger( aContext,                 
                                                                           aOffset,                  
                                                                           aDigits,                  
                                                                           aSignType,                
                                                                           aSignPosition,            
                                                                           aPrecision,               
                                                                           aScalingPosition,         
                                                                           aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericNationalAsBigInteger( CopybookContext            aContext,                 
                                                   int                        aOffset,                  
                                                   BigInteger                 aValue,                   
                                                   int                        aDigits,                  
                                                   SignType                   aSignType,                
                                                   SignPosition               aSignPosition,            
                                                   int                        aPrecision,               
                                                   int                        aScalingPosition,         
                                                   NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsBigInteger( aContext,                 
                                                      aOffset,                  
                                                      aValue,                   
                                                      aDigits,                  
                                                      aSignType,                
                                                      aSignPosition,            
                                                      aPrecision,               
                                                      aScalingPosition,         
                                                      aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericNationalAsBigDecimalValid( CopybookContext            aContext,                 
                                                       int                        aOffset,                  
                                                       int                        aDigits,                  
                                                       SignType                   aSignType,                
                                                       SignPosition               aSignPosition,            
                                                       int                        aPrecision,               
                                                       int                        aScalingPosition,         
                                                       NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericNationalMarshaller.isValidAsBigDecimal( aContext,                 
                                                              aOffset,                  
                                                              aDigits,                  
                                                              aSignType,                
                                                              aSignPosition,            
                                                              aPrecision,               
                                                              aScalingPosition,         
                                                              aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigDecimal decodeNumericNationalAsBigDecimal( CopybookContext            aContext,                 
                                                         int                        aOffset,                  
                                                         int                        aDigits,                  
                                                         SignType                   aSignType,                
                                                         SignPosition               aSignPosition,            
                                                         int                        aPrecision,               
                                                         int                        aScalingPosition,         
                                                         NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigDecimal myValue = numericNationalMarshaller.decodeAsBigDecimal( aContext,                 
                                                                           aOffset,                  
                                                                           aDigits,                  
                                                                           aSignType,                
                                                                           aSignPosition,            
                                                                           aPrecision,               
                                                                           aScalingPosition,         
                                                                           aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericNationalAsBigDecimal( CopybookContext            aContext,                 
                                                   int                        aOffset,                  
                                                   BigDecimal                 aValue,                   
                                                   int                        aDigits,                  
                                                   SignType                   aSignType,                
                                                   SignPosition               aSignPosition,            
                                                   int                        aPrecision,               
                                                   int                        aScalingPosition,         
                                                   NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsBigDecimal( aContext,                 
                                                      aOffset,                  
                                                      aValue,                   
                                                      aDigits,                  
                                                      aSignType,                
                                                      aSignPosition,            
                                                      aPrecision,               
                                                      aScalingPosition,         
                                                      aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericNationalAsStringValid( CopybookContext            aContext,                 
                                                   int                        aOffset,                  
                                                   int                        aDigits,                  
                                                   SignType                   aSignType,                
                                                   SignPosition               aSignPosition,            
                                                   int                        aPrecision,               
                                                   int                        aScalingPosition,         
                                                   NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericNationalMarshaller.isValidAsString( aContext,                 
                                                          aOffset,                  
                                                          aDigits,                  
                                                          aSignType,                
                                                          aSignPosition,            
                                                          aPrecision,               
                                                          aScalingPosition,         
                                                          aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public String decodeNumericNationalAsString( CopybookContext            aContext,                 
                                                 int                        aOffset,                  
                                                 int                        aDigits,                  
                                                 SignType                   aSignType,                
                                                 SignPosition               aSignPosition,            
                                                 int                        aPrecision,               
                                                 int                        aScalingPosition,         
                                                 NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        String myValue = numericNationalMarshaller.decodeAsString( aContext,                 
                                                                   aOffset,                  
                                                                   aDigits,                  
                                                                   aSignType,                
                                                                   aSignPosition,            
                                                                   aPrecision,               
                                                                   aScalingPosition,         
                                                                   aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericNationalAsString( CopybookContext            aContext,                 
                                               int                        aOffset,                  
                                               String                     aValue,                   
                                               int                        aDigits,                  
                                               SignType                   aSignType,                
                                               SignPosition               aSignPosition,            
                                               int                        aPrecision,               
                                               int                        aScalingPosition,         
                                               NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsString( aContext,                 
                                                  aOffset,                  
                                                  aValue,                   
                                                  aDigits,                  
                                                  aSignType,                
                                                  aSignPosition,            
                                                  aPrecision,               
                                                  aScalingPosition,         
                                                  aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isNumericNationalAsDateValid( CopybookContext            aContext,                 
                                                 int                        aOffset,                  
                                                 int                        aDigits,                  
                                                 SignType                   aSignType,                
                                                 SignPosition               aSignPosition,            
                                                 int                        aPrecision,               
                                                 int                        aScalingPosition,         
                                                 Converter                  aConverter,               
                                                 NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return numericNationalMarshaller.isValidAsDate( aContext,                 
                                                        aOffset,                  
                                                        aDigits,                  
                                                        aSignType,                
                                                        aSignPosition,            
                                                        aPrecision,               
                                                        aScalingPosition,         
                                                        aConverter,               
                                                        aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public Date decodeNumericNationalAsDate( CopybookContext            aContext,                 
                                             int                        aOffset,                  
                                             int                        aDigits,                  
                                             SignType                   aSignType,                
                                             SignPosition               aSignPosition,            
                                             int                        aPrecision,               
                                             int                        aScalingPosition,         
                                             Converter                  aConverter,               
                                             NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        Date myValue = numericNationalMarshaller.decodeAsDate( aContext,                 
                                                               aOffset,                  
                                                               aDigits,                  
                                                               aSignType,                
                                                               aSignPosition,            
                                                               aPrecision,               
                                                               aScalingPosition,         
                                                               aConverter,               
                                                               aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     */
    public void encodeNumericNationalAsDate( CopybookContext            aContext,                 
                                             int                        aOffset,                  
                                             Date                       aValue,                   
                                             int                        aDigits,                  
                                             SignType                   aSignType,                
                                             SignPosition               aSignPosition,            
                                             int                        aPrecision,               
                                             int                        aScalingPosition,         
                                             Converter                  aConverter,               
                                             NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        numericNationalMarshaller.encodeAsDate( aContext,                 
                                                aOffset,                  
                                                aValue,                   
                                                aDigits,                  
                                                aSignType,                
                                                aSignPosition,            
                                                aPrecision,               
                                                aScalingPosition,         
                                                aConverter,               
                                                aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isNumericNationalConditionNameValid( CopybookContext                         aContext,                   
                                                        int                                     aOffset,                    
                                                        String                                  aConditionName,             
                                                        java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                                        int                                     aDigits,                    
                                                        SignType                                aSignType,                  
                                                        SignPosition                            aSignPosition,              
                                                        int                                     aPrecision,                 
                                                        int                                     aScalingPosition             )
    {
        return numericNationalMarshaller.isConditionNameValid( aContext,                   
                                                               aOffset,                    
                                                               aConditionName,             
                                                               aConditionNameValueMappings,
                                                               aDigits,                    
                                                               aSignType,                  
                                                               aSignPosition,              
                                                               aPrecision,                 
                                                               aScalingPosition             );
    }




    //
    // BinaryPICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsByteValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsByte( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeBinaryAsByte( CopybookContext   aContext,        
                                    int               aOffset,         
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        byte myValue = binaryMarshaller.decodeAsByte( aContext,        
                                                      aOffset,         
                                                      aDigits,         
                                                      aSignType,       
                                                      aPrecision,      
                                                      aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsByte( CopybookContext   aContext,        
                                    int               aOffset,         
                                    byte              aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsByte( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsCharValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsChar( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeBinaryAsChar( CopybookContext   aContext,        
                                    int               aOffset,         
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        char myValue = binaryMarshaller.decodeAsChar( aContext,        
                                                      aOffset,         
                                                      aDigits,         
                                                      aSignType,       
                                                      aPrecision,      
                                                      aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsChar( CopybookContext   aContext,        
                                    int               aOffset,         
                                    char              aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsChar( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsShortValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsShort( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeBinaryAsShort( CopybookContext   aContext,        
                                      int               aOffset,         
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        short myValue = binaryMarshaller.decodeAsShort( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aPrecision,      
                                                        aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsShort( CopybookContext   aContext,        
                                     int               aOffset,         
                                     short             aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsShort( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsIntegerValid( CopybookContext   aContext,        
                                           int               aOffset,         
                                           int               aDigits,         
                                           SignType          aSignType,       
                                           int               aPrecision,      
                                           int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsInteger( aContext,        
                                                  aOffset,         
                                                  aDigits,         
                                                  aSignType,       
                                                  aPrecision,      
                                                  aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeBinaryAsInteger( CopybookContext   aContext,        
                                      int               aOffset,         
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        int myValue = binaryMarshaller.decodeAsInteger( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aPrecision,      
                                                        aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsInteger( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aValue,          
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsInteger( aContext,        
                                          aOffset,         
                                          aValue,          
                                          aDigits,         
                                          aSignType,       
                                          aPrecision,      
                                          aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsLongValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsLong( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public long decodeBinaryAsLong( CopybookContext   aContext,        
                                    int               aOffset,         
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        long myValue = binaryMarshaller.decodeAsLong( aContext,        
                                                      aOffset,         
                                                      aDigits,         
                                                      aSignType,       
                                                      aPrecision,      
                                                      aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsLong( CopybookContext   aContext,        
                                    int               aOffset,         
                                    long              aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsLong( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsFloatValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsFloat( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeBinaryAsFloat( CopybookContext   aContext,        
                                      int               aOffset,         
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        float myValue = binaryMarshaller.decodeAsFloat( aContext,        
                                                        aOffset,         
                                                        aDigits,         
                                                        aSignType,       
                                                        aPrecision,      
                                                        aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsFloat( CopybookContext   aContext,        
                                     int               aOffset,         
                                     float             aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsFloat( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsDoubleValid( CopybookContext   aContext,        
                                          int               aOffset,         
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsDouble( aContext,        
                                                 aOffset,         
                                                 aDigits,         
                                                 aSignType,       
                                                 aPrecision,      
                                                 aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeBinaryAsDouble( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        aContext.clearError();
        double myValue = binaryMarshaller.decodeAsDouble( aContext,        
                                                          aOffset,         
                                                          aDigits,         
                                                          aSignType,       
                                                          aPrecision,      
                                                          aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsDouble( CopybookContext   aContext,        
                                      int               aOffset,         
                                      double            aValue,          
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsDouble( aContext,        
                                         aOffset,         
                                         aValue,          
                                         aDigits,         
                                         aSignType,       
                                         aPrecision,      
                                         aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsBigIntegerValid( CopybookContext   aContext,        
                                              int               aOffset,         
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsBigInteger( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigInteger decodeBinaryAsBigInteger( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        aContext.clearError();
        BigInteger myValue = binaryMarshaller.decodeAsBigInteger( aContext,        
                                                                  aOffset,         
                                                                  aDigits,         
                                                                  aSignType,       
                                                                  aPrecision,      
                                                                  aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsBigInteger( CopybookContext   aContext,        
                                          int               aOffset,         
                                          BigInteger        aValue,          
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsBigInteger( aContext,        
                                             aOffset,         
                                             aValue,          
                                             aDigits,         
                                             aSignType,       
                                             aPrecision,      
                                             aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsBigDecimalValid( CopybookContext   aContext,        
                                              int               aOffset,         
                                              int               aDigits,         
                                              SignType          aSignType,       
                                              int               aPrecision,      
                                              int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsBigDecimal( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigDecimal decodeBinaryAsBigDecimal( CopybookContext   aContext,        
                                                int               aOffset,         
                                                int               aDigits,         
                                                SignType          aSignType,       
                                                int               aPrecision,      
                                                int               aScalingPosition  )
    {
        aContext.clearError();
        BigDecimal myValue = binaryMarshaller.decodeAsBigDecimal( aContext,        
                                                                  aOffset,         
                                                                  aDigits,         
                                                                  aSignType,       
                                                                  aPrecision,      
                                                                  aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsBigDecimal( CopybookContext   aContext,        
                                          int               aOffset,         
                                          BigDecimal        aValue,          
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsBigDecimal( aContext,        
                                             aOffset,         
                                             aValue,          
                                             aDigits,         
                                             aSignType,       
                                             aPrecision,      
                                             aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsStringValid( CopybookContext   aContext,        
                                          int               aOffset,         
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsString( aContext,        
                                                 aOffset,         
                                                 aDigits,         
                                                 aSignType,       
                                                 aPrecision,      
                                                 aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public String decodeBinaryAsString( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        aContext.clearError();
        String myValue = binaryMarshaller.decodeAsString( aContext,        
                                                          aOffset,         
                                                          aDigits,         
                                                          aSignType,       
                                                          aPrecision,      
                                                          aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsString( CopybookContext   aContext,        
                                      int               aOffset,         
                                      String            aValue,          
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsString( aContext,        
                                         aOffset,         
                                         aValue,          
                                         aDigits,         
                                         aSignType,       
                                         aPrecision,      
                                         aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryAsDateValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return binaryMarshaller.isValidAsDate( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public Date decodeBinaryAsDate( CopybookContext   aContext,        
                                    int               aOffset,         
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        Date myValue = binaryMarshaller.decodeAsDate( aContext,        
                                                      aOffset,         
                                                      aDigits,         
                                                      aSignType,       
                                                      aPrecision,      
                                                      aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeBinaryAsDate( CopybookContext   aContext,        
                                    int               aOffset,         
                                    Date              aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        binaryMarshaller.encodeAsDate( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isBinaryConditionNameValid( CopybookContext                         aContext,                   
                                               int                                     aOffset,                    
                                               String                                  aConditionName,             
                                               java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                               int                                     aDigits,                    
                                               SignType                                aSignType,                  
                                               int                                     aPrecision,                 
                                               int                                     aScalingPosition             )
    {
        return binaryMarshaller.isConditionNameValid( aContext,                   
                                                      aOffset,                    
                                                      aConditionName,             
                                                      aConditionNameValueMappings,
                                                      aDigits,                    
                                                      aSignType,                  
                                                      aPrecision,                 
                                                      aScalingPosition             );
    }




    //
    // Computational1PICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isComp1Valid( CopybookContext   aContext,
                                 int               aOffset   )
    {
        return comp1Marshaller.isValid( aContext,
                                        aOffset   );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public byte decodeComp1AsByte( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        byte myValue = comp1Marshaller.decodeAsByte( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsByte( CopybookContext   aContext,
                                   int               aOffset, 
                                   byte              aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsByte( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public char decodeComp1AsChar( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        char myValue = comp1Marshaller.decodeAsChar( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsChar( CopybookContext   aContext,
                                   int               aOffset, 
                                   char              aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsChar( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public short decodeComp1AsShort( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        short myValue = comp1Marshaller.decodeAsShort( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsShort( CopybookContext   aContext,
                                    int               aOffset, 
                                    short             aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsShort( aContext,
                                       aOffset, 
                                       aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public int decodeComp1AsInteger( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        int myValue = comp1Marshaller.decodeAsInteger( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsInteger( CopybookContext   aContext,
                                      int               aOffset, 
                                      int               aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsInteger( aContext,
                                         aOffset, 
                                         aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decodeComp1AsLong( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        long myValue = comp1Marshaller.decodeAsLong( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsLong( CopybookContext   aContext,
                                   int               aOffset, 
                                   long              aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsLong( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public float decodeComp1AsFloat( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        float myValue = comp1Marshaller.decodeAsFloat( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsFloat( CopybookContext   aContext,
                                    int               aOffset, 
                                    float             aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsFloat( aContext,
                                       aOffset, 
                                       aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public double decodeComp1AsDouble( CopybookContext   aContext,
                                       int               aOffset   )
    {
        aContext.clearError();
        double myValue = comp1Marshaller.decodeAsDouble( aContext,
                                                         aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsDouble( CopybookContext   aContext,
                                     int               aOffset, 
                                     double            aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsDouble( aContext,
                                        aOffset, 
                                        aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigInteger decodeComp1AsBigInteger( CopybookContext   aContext,
                                               int               aOffset   )
    {
        aContext.clearError();
        BigInteger myValue = comp1Marshaller.decodeAsBigInteger( aContext,
                                                                 aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsBigInteger( CopybookContext   aContext,
                                         int               aOffset, 
                                         BigInteger        aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsBigInteger( aContext,
                                            aOffset, 
                                            aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigDecimal decodeComp1AsBigDecimal( CopybookContext   aContext,
                                               int               aOffset   )
    {
        aContext.clearError();
        BigDecimal myValue = comp1Marshaller.decodeAsBigDecimal( aContext,
                                                                 aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsBigDecimal( CopybookContext   aContext,
                                         int               aOffset, 
                                         BigDecimal        aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsBigDecimal( aContext,
                                            aOffset, 
                                            aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public String decodeComp1AsString( CopybookContext   aContext,
                                       int               aOffset   )
    {
        aContext.clearError();
        String myValue = comp1Marshaller.decodeAsString( aContext,
                                                         aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1AsString( CopybookContext   aContext,
                                     int               aOffset, 
                                     String            aValue    )
    {
        aContext.clearError();
        comp1Marshaller.encodeAsString( aContext,
                                        aOffset, 
                                        aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * 
     * @return
     */
    public boolean isComp1ConditionNameValid( CopybookContext                         aContext,                   
                                              int                                     aOffset,                    
                                              String                                  aConditionName,             
                                              java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings  )
    {
        return comp1Marshaller.isConditionNameValid( aContext,                   
                                                     aOffset,                    
                                                     aConditionName,             
                                                     aConditionNameValueMappings  );
    }




    //
    // Computational2PICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public boolean isComp2Valid( CopybookContext   aContext,
                                 int               aOffset   )
    {
        return comp2Marshaller.isValid( aContext,
                                        aOffset   );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public byte decodeComp2AsByte( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        byte myValue = comp2Marshaller.decodeAsByte( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsByte( CopybookContext   aContext,
                                   int               aOffset, 
                                   byte              aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsByte( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public char decodeComp2AsChar( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        char myValue = comp2Marshaller.decodeAsChar( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsChar( CopybookContext   aContext,
                                   int               aOffset, 
                                   char              aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsChar( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public short decodeComp2AsShort( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        short myValue = comp2Marshaller.decodeAsShort( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsShort( CopybookContext   aContext,
                                    int               aOffset, 
                                    short             aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsShort( aContext,
                                       aOffset, 
                                       aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public int decodeComp2AsInteger( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        int myValue = comp2Marshaller.decodeAsInteger( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsInteger( CopybookContext   aContext,
                                      int               aOffset, 
                                      int               aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsInteger( aContext,
                                         aOffset, 
                                         aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decodeComp2AsLong( CopybookContext   aContext,
                                   int               aOffset   )
    {
        aContext.clearError();
        long myValue = comp2Marshaller.decodeAsLong( aContext,
                                                     aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsLong( CopybookContext   aContext,
                                   int               aOffset, 
                                   long              aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsLong( aContext,
                                      aOffset, 
                                      aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public float decodeComp2AsFloat( CopybookContext   aContext,
                                     int               aOffset   )
    {
        aContext.clearError();
        float myValue = comp2Marshaller.decodeAsFloat( aContext,
                                                       aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsFloat( CopybookContext   aContext,
                                    int               aOffset, 
                                    float             aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsFloat( aContext,
                                       aOffset, 
                                       aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public double decodeComp2AsDouble( CopybookContext   aContext,
                                       int               aOffset   )
    {
        aContext.clearError();
        double myValue = comp2Marshaller.decodeAsDouble( aContext,
                                                         aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsDouble( CopybookContext   aContext,
                                     int               aOffset, 
                                     double            aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsDouble( aContext,
                                        aOffset, 
                                        aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigInteger decodeComp2AsBigInteger( CopybookContext   aContext,
                                               int               aOffset   )
    {
        aContext.clearError();
        BigInteger myValue = comp2Marshaller.decodeAsBigInteger( aContext,
                                                                 aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsBigInteger( CopybookContext   aContext,
                                         int               aOffset, 
                                         BigInteger        aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsBigInteger( aContext,
                                            aOffset, 
                                            aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public BigDecimal decodeComp2AsBigDecimal( CopybookContext   aContext,
                                               int               aOffset   )
    {
        aContext.clearError();
        BigDecimal myValue = comp2Marshaller.decodeAsBigDecimal( aContext,
                                                                 aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsBigDecimal( CopybookContext   aContext,
                                         int               aOffset, 
                                         BigDecimal        aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsBigDecimal( aContext,
                                            aOffset, 
                                            aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public String decodeComp2AsString( CopybookContext   aContext,
                                       int               aOffset   )
    {
        aContext.clearError();
        String myValue = comp2Marshaller.decodeAsString( aContext,
                                                         aOffset   );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2AsString( CopybookContext   aContext,
                                     int               aOffset, 
                                     String            aValue    )
    {
        aContext.clearError();
        comp2Marshaller.encodeAsString( aContext,
                                        aOffset, 
                                        aValue    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * 
     * @return
     */
    public boolean isComp2ConditionNameValid( CopybookContext                         aContext,                   
                                              int                                     aOffset,                    
                                              String                                  aConditionName,             
                                              java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings  )
    {
        return comp2Marshaller.isConditionNameValid( aContext,                   
                                                     aOffset,                    
                                                     aConditionName,             
                                                     aConditionNameValueMappings  );
    }




    //
    // Computational3PICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsByteValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsByte( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeComp3AsByte( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        byte myValue = comp3Marshaller.decodeAsByte( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsByte( CopybookContext   aContext,        
                                   int               aOffset,         
                                   byte              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsByte( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsCharValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsChar( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeComp3AsChar( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        char myValue = comp3Marshaller.decodeAsChar( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsChar( CopybookContext   aContext,        
                                   int               aOffset,         
                                   char              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsChar( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsShortValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsShort( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeComp3AsShort( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        short myValue = comp3Marshaller.decodeAsShort( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsShort( CopybookContext   aContext,        
                                    int               aOffset,         
                                    short             aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsShort( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsIntegerValid( CopybookContext   aContext,        
                                          int               aOffset,         
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsInteger( aContext,        
                                                 aOffset,         
                                                 aDigits,         
                                                 aSignType,       
                                                 aPrecision,      
                                                 aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeComp3AsInteger( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        int myValue = comp3Marshaller.decodeAsInteger( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsInteger( CopybookContext   aContext,        
                                      int               aOffset,         
                                      int               aValue,          
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsInteger( aContext,        
                                         aOffset,         
                                         aValue,          
                                         aDigits,         
                                         aSignType,       
                                         aPrecision,      
                                         aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsLongValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsLong( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public long decodeComp3AsLong( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        long myValue = comp3Marshaller.decodeAsLong( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsLong( CopybookContext   aContext,        
                                   int               aOffset,         
                                   long              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsLong( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsFloatValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsFloat( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeComp3AsFloat( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        float myValue = comp3Marshaller.decodeAsFloat( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsFloat( CopybookContext   aContext,        
                                    int               aOffset,         
                                    float             aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsFloat( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsDoubleValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsDouble( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeComp3AsDouble( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        aContext.clearError();
        double myValue = comp3Marshaller.decodeAsDouble( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aPrecision,      
                                                         aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsDouble( CopybookContext   aContext,        
                                     int               aOffset,         
                                     double            aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsDouble( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsBigIntegerValid( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsBigInteger( aContext,        
                                                    aOffset,         
                                                    aDigits,         
                                                    aSignType,       
                                                    aPrecision,      
                                                    aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigInteger decodeComp3AsBigInteger( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        BigInteger myValue = comp3Marshaller.decodeAsBigInteger( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsBigInteger( CopybookContext   aContext,        
                                         int               aOffset,         
                                         BigInteger        aValue,          
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsBigInteger( aContext,        
                                            aOffset,         
                                            aValue,          
                                            aDigits,         
                                            aSignType,       
                                            aPrecision,      
                                            aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsBigDecimalValid( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsBigDecimal( aContext,        
                                                    aOffset,         
                                                    aDigits,         
                                                    aSignType,       
                                                    aPrecision,      
                                                    aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigDecimal decodeComp3AsBigDecimal( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        BigDecimal myValue = comp3Marshaller.decodeAsBigDecimal( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsBigDecimal( CopybookContext   aContext,        
                                         int               aOffset,         
                                         BigDecimal        aValue,          
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsBigDecimal( aContext,        
                                            aOffset,         
                                            aValue,          
                                            aDigits,         
                                            aSignType,       
                                            aPrecision,      
                                            aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsStringValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsString( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public String decodeComp3AsString( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        aContext.clearError();
        String myValue = comp3Marshaller.decodeAsString( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aPrecision,      
                                                         aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsString( CopybookContext   aContext,        
                                     int               aOffset,         
                                     String            aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsString( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3AsDateValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp3Marshaller.isValidAsDate( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public Date decodeComp3AsDate( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        Date myValue = comp3Marshaller.decodeAsDate( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp3AsDate( CopybookContext   aContext,        
                                   int               aOffset,         
                                   Date              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp3Marshaller.encodeAsDate( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp3ConditionNameValid( CopybookContext                         aContext,                   
                                              int                                     aOffset,                    
                                              String                                  aConditionName,             
                                              java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                              int                                     aDigits,                    
                                              SignType                                aSignType,                  
                                              int                                     aPrecision,                 
                                              int                                     aScalingPosition             )
    {
        return comp3Marshaller.isConditionNameValid( aContext,                   
                                                     aOffset,                    
                                                     aConditionName,             
                                                     aConditionNameValueMappings,
                                                     aDigits,                    
                                                     aSignType,                  
                                                     aPrecision,                 
                                                     aScalingPosition             );
    }




    //
    // Computational5PICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsByteValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsByte( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public byte decodeComp5AsByte( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        byte myValue = comp5Marshaller.decodeAsByte( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsByte( CopybookContext   aContext,        
                                   int               aOffset,         
                                   byte              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsByte( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsCharValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsChar( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public char decodeComp5AsChar( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        char myValue = comp5Marshaller.decodeAsChar( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsChar( CopybookContext   aContext,        
                                   int               aOffset,         
                                   char              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsChar( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsShortValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsShort( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public short decodeComp5AsShort( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        short myValue = comp5Marshaller.decodeAsShort( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsShort( CopybookContext   aContext,        
                                    int               aOffset,         
                                    short             aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsShort( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsIntegerValid( CopybookContext   aContext,        
                                          int               aOffset,         
                                          int               aDigits,         
                                          SignType          aSignType,       
                                          int               aPrecision,      
                                          int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsInteger( aContext,        
                                                 aOffset,         
                                                 aDigits,         
                                                 aSignType,       
                                                 aPrecision,      
                                                 aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public int decodeComp5AsInteger( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        int myValue = comp5Marshaller.decodeAsInteger( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsInteger( CopybookContext   aContext,        
                                      int               aOffset,         
                                      int               aValue,          
                                      int               aDigits,         
                                      SignType          aSignType,       
                                      int               aPrecision,      
                                      int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsInteger( aContext,        
                                         aOffset,         
                                         aValue,          
                                         aDigits,         
                                         aSignType,       
                                         aPrecision,      
                                         aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsLongValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsLong( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public long decodeComp5AsLong( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        long myValue = comp5Marshaller.decodeAsLong( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsLong( CopybookContext   aContext,        
                                   int               aOffset,         
                                   long              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsLong( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsFloatValid( CopybookContext   aContext,        
                                        int               aOffset,         
                                        int               aDigits,         
                                        SignType          aSignType,       
                                        int               aPrecision,      
                                        int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsFloat( aContext,        
                                               aOffset,         
                                               aDigits,         
                                               aSignType,       
                                               aPrecision,      
                                               aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public float decodeComp5AsFloat( CopybookContext   aContext,        
                                     int               aOffset,         
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        float myValue = comp5Marshaller.decodeAsFloat( aContext,        
                                                       aOffset,         
                                                       aDigits,         
                                                       aSignType,       
                                                       aPrecision,      
                                                       aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsFloat( CopybookContext   aContext,        
                                    int               aOffset,         
                                    float             aValue,          
                                    int               aDigits,         
                                    SignType          aSignType,       
                                    int               aPrecision,      
                                    int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsFloat( aContext,        
                                       aOffset,         
                                       aValue,          
                                       aDigits,         
                                       aSignType,       
                                       aPrecision,      
                                       aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsDoubleValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsDouble( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public double decodeComp5AsDouble( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        aContext.clearError();
        double myValue = comp5Marshaller.decodeAsDouble( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aPrecision,      
                                                         aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsDouble( CopybookContext   aContext,        
                                     int               aOffset,         
                                     double            aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsDouble( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsBigIntegerValid( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsBigInteger( aContext,        
                                                    aOffset,         
                                                    aDigits,         
                                                    aSignType,       
                                                    aPrecision,      
                                                    aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigInteger decodeComp5AsBigInteger( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        BigInteger myValue = comp5Marshaller.decodeAsBigInteger( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsBigInteger( CopybookContext   aContext,        
                                         int               aOffset,         
                                         BigInteger        aValue,          
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsBigInteger( aContext,        
                                            aOffset,         
                                            aValue,          
                                            aDigits,         
                                            aSignType,       
                                            aPrecision,      
                                            aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsBigDecimalValid( CopybookContext   aContext,        
                                             int               aOffset,         
                                             int               aDigits,         
                                             SignType          aSignType,       
                                             int               aPrecision,      
                                             int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsBigDecimal( aContext,        
                                                    aOffset,         
                                                    aDigits,         
                                                    aSignType,       
                                                    aPrecision,      
                                                    aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public BigDecimal decodeComp5AsBigDecimal( CopybookContext   aContext,        
                                               int               aOffset,         
                                               int               aDigits,         
                                               SignType          aSignType,       
                                               int               aPrecision,      
                                               int               aScalingPosition  )
    {
        aContext.clearError();
        BigDecimal myValue = comp5Marshaller.decodeAsBigDecimal( aContext,        
                                                                 aOffset,         
                                                                 aDigits,         
                                                                 aSignType,       
                                                                 aPrecision,      
                                                                 aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsBigDecimal( CopybookContext   aContext,        
                                         int               aOffset,         
                                         BigDecimal        aValue,          
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsBigDecimal( aContext,        
                                            aOffset,         
                                            aValue,          
                                            aDigits,         
                                            aSignType,       
                                            aPrecision,      
                                            aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsStringValid( CopybookContext   aContext,        
                                         int               aOffset,         
                                         int               aDigits,         
                                         SignType          aSignType,       
                                         int               aPrecision,      
                                         int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsString( aContext,        
                                                aOffset,         
                                                aDigits,         
                                                aSignType,       
                                                aPrecision,      
                                                aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public String decodeComp5AsString( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        aContext.clearError();
        String myValue = comp5Marshaller.decodeAsString( aContext,        
                                                         aOffset,         
                                                         aDigits,         
                                                         aSignType,       
                                                         aPrecision,      
                                                         aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsString( CopybookContext   aContext,        
                                     int               aOffset,         
                                     String            aValue,          
                                     int               aDigits,         
                                     SignType          aSignType,       
                                     int               aPrecision,      
                                     int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsString( aContext,        
                                        aOffset,         
                                        aValue,          
                                        aDigits,         
                                        aSignType,       
                                        aPrecision,      
                                        aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5AsDateValid( CopybookContext   aContext,        
                                       int               aOffset,         
                                       int               aDigits,         
                                       SignType          aSignType,       
                                       int               aPrecision,      
                                       int               aScalingPosition  )
    {
        return comp5Marshaller.isValidAsDate( aContext,        
                                              aOffset,         
                                              aDigits,         
                                              aSignType,       
                                              aPrecision,      
                                              aScalingPosition  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public Date decodeComp5AsDate( CopybookContext   aContext,        
                                   int               aOffset,         
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        Date myValue = comp5Marshaller.decodeAsDate( aContext,        
                                                     aOffset,         
                                                     aDigits,         
                                                     aSignType,       
                                                     aPrecision,      
                                                     aScalingPosition  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     */
    public void encodeComp5AsDate( CopybookContext   aContext,        
                                   int               aOffset,         
                                   Date              aValue,          
                                   int               aDigits,         
                                   SignType          aSignType,       
                                   int               aPrecision,      
                                   int               aScalingPosition  )
    {
        aContext.clearError();
        comp5Marshaller.encodeAsDate( aContext,        
                                      aOffset,         
                                      aValue,          
                                      aDigits,         
                                      aSignType,       
                                      aPrecision,      
                                      aScalingPosition  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * 
     * @return
     */
    public boolean isComp5ConditionNameValid( CopybookContext                         aContext,                   
                                              int                                     aOffset,                    
                                              String                                  aConditionName,             
                                              java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                              int                                     aDigits,                    
                                              SignType                                aSignType,                  
                                              int                                     aPrecision,                 
                                              int                                     aScalingPosition             )
    {
        return comp5Marshaller.isConditionNameValid( aContext,                   
                                                     aOffset,                    
                                                     aConditionName,             
                                                     aConditionNameValueMappings,
                                                     aDigits,                    
                                                     aSignType,                  
                                                     aPrecision,                 
                                                     aScalingPosition             );
    }




    //
    // AlphanumericPICMarshaller delegation
    //


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericBlank( CopybookContext   aContext,
                                        int               aOffset, 
                                        int               aSize,   
                                        int               aFlags    )
    {
        aContext.clearError();
        boolean myBlank = alphanumericMarshaller.isBlank( aContext,
                                                          aOffset, 
                                                          aSize,   
                                                          aFlags    );
        checkForError( aContext );
        return myBlank;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsByteValid( CopybookContext   aContext,
                                              int               aOffset, 
                                              int               aSize,   
                                              int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsByte( aContext,
                                                     aOffset, 
                                                     aSize,   
                                                     aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public byte decodeAlphanumericAsByte( CopybookContext   aContext,
                                          int               aOffset, 
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        byte myValue = alphanumericMarshaller.decodeAsByte( aContext,
                                                            aOffset, 
                                                            aSize,   
                                                            aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsByte( CopybookContext   aContext,
                                          int               aOffset, 
                                          byte              aValue,  
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsByte( aContext,
                                             aOffset, 
                                             aValue,  
                                             aSize,   
                                             aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsCharValid( CopybookContext   aContext,
                                              int               aOffset, 
                                              int               aSize,   
                                              int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsChar( aContext,
                                                     aOffset, 
                                                     aSize,   
                                                     aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public char decodeAlphanumericAsChar( CopybookContext   aContext,
                                          int               aOffset, 
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        char myValue = alphanumericMarshaller.decodeAsChar( aContext,
                                                            aOffset, 
                                                            aSize,   
                                                            aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsChar( CopybookContext   aContext,
                                          int               aOffset, 
                                          char              aValue,  
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsChar( aContext,
                                             aOffset, 
                                             aValue,  
                                             aSize,   
                                             aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsShortValid( CopybookContext   aContext,
                                               int               aOffset, 
                                               int               aSize,   
                                               int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsShort( aContext,
                                                      aOffset, 
                                                      aSize,   
                                                      aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public short decodeAlphanumericAsShort( CopybookContext   aContext,
                                            int               aOffset, 
                                            int               aSize,   
                                            int               aFlags    )
    {
        aContext.clearError();
        short myValue = alphanumericMarshaller.decodeAsShort( aContext,
                                                              aOffset, 
                                                              aSize,   
                                                              aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsShort( CopybookContext   aContext,
                                           int               aOffset, 
                                           short             aValue,  
                                           int               aSize,   
                                           int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsShort( aContext,
                                              aOffset, 
                                              aValue,  
                                              aSize,   
                                              aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsIntegerValid( CopybookContext   aContext,
                                                 int               aOffset, 
                                                 int               aSize,   
                                                 int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsInteger( aContext,
                                                        aOffset, 
                                                        aSize,   
                                                        aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public int decodeAlphanumericAsInteger( CopybookContext   aContext,
                                            int               aOffset, 
                                            int               aSize,   
                                            int               aFlags    )
    {
        aContext.clearError();
        int myValue = alphanumericMarshaller.decodeAsInteger( aContext,
                                                              aOffset, 
                                                              aSize,   
                                                              aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsInteger( CopybookContext   aContext,
                                             int               aOffset, 
                                             int               aValue,  
                                             int               aSize,   
                                             int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsInteger( aContext,
                                                aOffset, 
                                                aValue,  
                                                aSize,   
                                                aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsLongValid( CopybookContext   aContext,
                                              int               aOffset, 
                                              int               aSize,   
                                              int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsLong( aContext,
                                                     aOffset, 
                                                     aSize,   
                                                     aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public long decodeAlphanumericAsLong( CopybookContext   aContext,
                                          int               aOffset, 
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        long myValue = alphanumericMarshaller.decodeAsLong( aContext,
                                                            aOffset, 
                                                            aSize,   
                                                            aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsLong( CopybookContext   aContext,
                                          int               aOffset, 
                                          long              aValue,  
                                          int               aSize,   
                                          int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsLong( aContext,
                                             aOffset, 
                                             aValue,  
                                             aSize,   
                                             aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsFloatValid( CopybookContext   aContext,
                                               int               aOffset, 
                                               int               aSize,   
                                               int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsFloat( aContext,
                                                      aOffset, 
                                                      aSize,   
                                                      aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public float decodeAlphanumericAsFloat( CopybookContext   aContext,
                                            int               aOffset, 
                                            int               aSize,   
                                            int               aFlags    )
    {
        aContext.clearError();
        float myValue = alphanumericMarshaller.decodeAsFloat( aContext,
                                                              aOffset, 
                                                              aSize,   
                                                              aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsFloat( CopybookContext   aContext,
                                           int               aOffset, 
                                           float             aValue,  
                                           int               aSize,   
                                           int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsFloat( aContext,
                                              aOffset, 
                                              aValue,  
                                              aSize,   
                                              aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericAsDoubleValid( CopybookContext   aContext,
                                                int               aOffset, 
                                                int               aSize,   
                                                int               aFlags    )
    {
        return alphanumericMarshaller.isValidAsDouble( aContext,
                                                       aOffset, 
                                                       aSize,   
                                                       aFlags    );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public double decodeAlphanumericAsDouble( CopybookContext   aContext,
                                              int               aOffset, 
                                              int               aSize,   
                                              int               aFlags    )
    {
        aContext.clearError();
        double myValue = alphanumericMarshaller.decodeAsDouble( aContext,
                                                                aOffset, 
                                                                aSize,   
                                                                aFlags    );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAlphanumericAsDouble( CopybookContext   aContext,
                                            int               aOffset, 
                                            double            aValue,  
                                            int               aSize,   
                                            int               aFlags    )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsDouble( aContext,
                                               aOffset, 
                                               aValue,  
                                               aSize,   
                                               aFlags    );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isAlphanumericAsBigIntegerValid( CopybookContext            aContext,                 
                                                    int                        aOffset,                  
                                                    int                        aSize,                    
                                                    int                        aFlags,                   
                                                    NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return alphanumericMarshaller.isValidAsBigInteger( aContext,                 
                                                           aOffset,                  
                                                           aSize,                    
                                                           aFlags,                   
                                                           aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigInteger decodeAlphanumericAsBigInteger( CopybookContext            aContext,                 
                                                      int                        aOffset,                  
                                                      int                        aSize,                    
                                                      int                        aFlags,                   
                                                      NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigInteger myValue = alphanumericMarshaller.decodeAsBigInteger( aContext,                 
                                                                        aOffset,                  
                                                                        aSize,                    
                                                                        aFlags,                   
                                                                        aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     */
    public void encodeAlphanumericAsBigInteger( CopybookContext            aContext,                 
                                                int                        aOffset,                  
                                                BigInteger                 aValue,                   
                                                int                        aSize,                    
                                                int                        aFlags,                   
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsBigInteger( aContext,                 
                                                   aOffset,                  
                                                   aValue,                   
                                                   aSize,                    
                                                   aFlags,                   
                                                   aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isAlphanumericAsBigDecimalValid( CopybookContext            aContext,                 
                                                    int                        aOffset,                  
                                                    int                        aSize,                    
                                                    int                        aFlags,                   
                                                    NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return alphanumericMarshaller.isValidAsBigDecimal( aContext,                 
                                                           aOffset,                  
                                                           aSize,                    
                                                           aFlags,                   
                                                           aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public BigDecimal decodeAlphanumericAsBigDecimal( CopybookContext            aContext,                 
                                                      int                        aOffset,                  
                                                      int                        aSize,                    
                                                      int                        aFlags,                   
                                                      NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        BigDecimal myValue = alphanumericMarshaller.decodeAsBigDecimal( aContext,                 
                                                                        aOffset,                  
                                                                        aSize,                    
                                                                        aFlags,                   
                                                                        aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     */
    public void encodeAlphanumericAsBigDecimal( CopybookContext            aContext,                 
                                                int                        aOffset,                  
                                                BigDecimal                 aValue,                   
                                                int                        aSize,                    
                                                int                        aFlags,                   
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsBigDecimal( aContext,                 
                                                   aOffset,                  
                                                   aValue,                   
                                                   aSize,                    
                                                   aFlags,                   
                                                   aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isAlphanumericAsStringValid( CopybookContext            aContext,                 
                                                int                        aOffset,                  
                                                int                        aSize,                    
                                                int                        aFlags,                   
                                                NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return alphanumericMarshaller.isValidAsString( aContext,                 
                                                       aOffset,                  
                                                       aSize,                    
                                                       aFlags,                   
                                                       aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public String decodeAlphanumericAsString( CopybookContext            aContext,                 
                                              int                        aOffset,                  
                                              int                        aSize,                    
                                              int                        aFlags,                   
                                              NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        String myValue = alphanumericMarshaller.decodeAsString( aContext,                 
                                                                aOffset,                  
                                                                aSize,                    
                                                                aFlags,                   
                                                                aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     */
    public void encodeAlphanumericAsString( CopybookContext            aContext,                 
                                            int                        aOffset,                  
                                            String                     aValue,                   
                                            int                        aSize,                    
                                            int                        aFlags,                   
                                            NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsString( aContext,                 
                                               aOffset,                  
                                               aValue,                   
                                               aSize,                    
                                               aFlags,                   
                                               aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public boolean isAlphanumericAsDateValid( CopybookContext            aContext,                 
                                              int                        aOffset,                  
                                              int                        aSize,                    
                                              int                        aFlags,                   
                                              Converter                  aConverter,               
                                              NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        return alphanumericMarshaller.isValidAsDate( aContext,                 
                                                     aOffset,                  
                                                     aSize,                    
                                                     aFlags,                   
                                                     aConverter,               
                                                     aNullEquivalentStrategies  );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    public Date decodeAlphanumericAsDate( CopybookContext            aContext,                 
                                          int                        aOffset,                  
                                          int                        aSize,                    
                                          int                        aFlags,                   
                                          Converter                  aConverter,               
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        Date myValue = alphanumericMarshaller.decodeAsDate( aContext,                 
                                                            aOffset,                  
                                                            aSize,                    
                                                            aFlags,                   
                                                            aConverter,               
                                                            aNullEquivalentStrategies  );
        checkForError( aContext );
        return myValue;
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     */
    public void encodeAlphanumericAsDate( CopybookContext            aContext,                 
                                          int                        aOffset,                  
                                          Date                       aValue,                   
                                          int                        aSize,                    
                                          int                        aFlags,                   
                                          Converter                  aConverter,               
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies  )
    {
        aContext.clearError();
        alphanumericMarshaller.encodeAsDate( aContext,                 
                                             aOffset,                  
                                             aValue,                   
                                             aSize,                    
                                             aFlags,                   
                                             aConverter,               
                                             aNullEquivalentStrategies  );
        checkForError( aContext );
    }


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aConditionName
     * @param       aConditionNameValueMappings
     * @param       aSize
     * @param       aFlags
     * 
     * @return
     */
    public boolean isAlphanumericConditionNameValid( CopybookContext                         aContext,                   
                                                     int                                     aOffset,                    
                                                     String                                  aConditionName,             
                                                     java.util.Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                                     int                                     aSize,                      
                                                     int                                     aFlags                       )
    {
        return alphanumericMarshaller.isConditionNameValid( aContext,                   
                                                            aOffset,                    
                                                            aConditionName,             
                                                            aConditionNameValueMappings,
                                                            aSize,                      
                                                            aFlags                       );
    }
}

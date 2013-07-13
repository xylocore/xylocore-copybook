package com.xylocore.commons.data.copybook.runtime.marshallers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.converters.Converter;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface AlphanumericPICMarshaller
    extends
        PICMarshaller
{
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
    public boolean isBlank( CopybookContext   aContext,
                            int               aOffset,
                            int               aSize,
                            int               aFlags    );


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
    public boolean isValidAsByte( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    );
    

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
    public byte decodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsByte( CopybookContext   aContext,
                              int               aOffset,
                              byte              aValue,
                              int               aSize,
                              int               aFlags    );


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
    public boolean isValidAsChar( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    );
    

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
    public char decodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsChar( CopybookContext   aContext,
                              int               aOffset,
                              char              aValue,
                              int               aSize,
                              int               aFlags    );


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
    public boolean isValidAsShort( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    );

    
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
    public short decodeAsShort( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsShort( CopybookContext   aContext,
                               int               aOffset,
                               short             aValue,
                               int               aSize,
                               int               aFlags    );


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
    public boolean isValidAsInteger( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aSize,
                                     int               aFlags    );

    
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
    public int decodeAsInteger( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsInteger( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aValue,
                                 int               aSize,
                                 int               aFlags    );


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
    public boolean isValidAsLong( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    );

    
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
    public long decodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              int               aSize,
                              int               aFlags    );
    
 
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsLong( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aSize,
                              int               aFlags    );


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
    public boolean isValidAsFloat( CopybookContext   aContext,
                                   int               aOffset,
                                   int               aSize,
                                   int               aFlags    );

    
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
    public float decodeAsFloat( CopybookContext   aContext,
                                int               aOffset,
                                int               aSize,
                                int               aFlags    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsFloat( CopybookContext   aContext,
                               int               aOffset,
                               float             aValue,
                               int               aSize,
                               int               aFlags    );


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
    public boolean isValidAsDouble( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aSize,
                                    int               aFlags    );

    
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
    public double decodeAsDouble( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aSize,
                                  int               aFlags    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     */
    public void encodeAsDouble( CopybookContext   aContext,
                                int               aOffset,
                                double            aValue,
                                int               aSize,
                                int               aFlags    );


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
    public boolean isValidAsBigInteger( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public BigInteger decodeAsBigInteger( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
    public void encodeAsBigInteger( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigInteger                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );


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
    public boolean isValidAsBigDecimal( CopybookContext            aContext,
                                        int                        aOffset,
                                        int                        aSize,
                                        int                        aFlags,
                                        NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public BigDecimal decodeAsBigDecimal( CopybookContext            aContext,
                                          int                        aOffset,
                                          int                        aSize,
                                          int                        aFlags,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
    public void encodeAsBigDecimal( CopybookContext            aContext,
                                    int                        aOffset,
                                    BigDecimal                 aValue,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );


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
    public boolean isValidAsString( CopybookContext            aContext,
                                    int                        aOffset,
                                    int                        aSize,
                                    int                        aFlags,
                                    NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public String decodeAsString( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
    public void encodeAsString( CopybookContext            aContext,
                                int                        aOffset,
                                String                     aValue,
                                int                        aSize,
                                int                        aFlags,
                                NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public boolean isValidAsDate( CopybookContext            aContext,
                                  int                        aOffset,
                                  int                        aSize,
                                  int                        aFlags,
                                  Converter                  aConverter,
                                  NullEquivalentStrategy[]   aNullEquivalentStrategies );
    

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
    public Date decodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
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
    public void encodeAsDate( CopybookContext            aContext,
                              int                        aOffset,
                              Date                       aValue,
                              int                        aSize,
                              int                        aFlags,
                              Converter                  aConverter,
                              NullEquivalentStrategy[]   aNullEquivalentStrategies );

    
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
    public void decodeStringIntoWorkBuffer( CopybookContext   aContext,
                                            int               aOffset,
                                            int               aSize,
                                            int               aFlags    );


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
    public boolean isConditionNameValid( CopybookContext               aContext,                   
                                         int                           aOffset,                    
                                         String                        aConditionName,             
                                         Map<String,ConstantValue[]>   aConditionNameValueMappings,
                                         int                           aSize,                      
                                         int                           aFlags                       );
}

package com.xylocore.commons.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractApplication
{
    //
    // Members
    //
    
    
    private final Options commandLineOptions;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     */
    public AbstractApplication()
    {
        commandLineOptions = new Options();
        buildOptions( commandLineOptions );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       args
     */
    protected void run( String[] args )
    {
        try
        {
            parseArguments( args );
    
            runImpl();
        }
        catch ( ParseException myParseException )
        {
            System.err.println( myParseException.getMessage() );
            
            // TODO: display proper usage
            HelpFormatter myFormatter = new HelpFormatter();
            myFormatter.printHelp( "*** app ***", commandLineOptions, true );
            System.exit( 1 );
        }
        catch ( Exception myException )
        {
            myException.printStackTrace();
            System.exit( 1 );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       args
     * 
     * @throws      ParseException
     */
    protected void parseArguments( String [] args )
            throws ParseException
    {
        Options myOptions = new Options();
        buildOptions( myOptions );
        
        CommandLineParser myParser      = new GnuParser();
        CommandLine       myCommandLine = myParser.parse( myOptions, args );
        
        processArguments( myCommandLine );
    }
    

    /**
     * FILLIN
     * 
     * @param       aOptions
     */
    protected void buildOptions( Options aOptions )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCommandLine
     * 
     * @throws      ParseException
     */
    protected void processArguments( CommandLine aCommandLine )
            throws ParseException
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @throws      Exception
     */
    protected abstract void runImpl()
            throws Exception;
}

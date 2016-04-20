package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Parameters 
{
	private static HashMap<String, String> m_map;
	
	public static void readValues()
	{
		BufferedReader reader = null;
		m_map = new HashMap<String,String>();
		try
		{
			reader = new BufferedReader(new FileReader("/home/lvuser/Parameters.txt"));
			String line;
			int line_no = 0;
			while ( (line = reader.readLine()) != null ) 
			{
				++line_no;
				line = line.trim();
				if ( line.length() == 0 ) continue; // skip blank lines
				if ( line.charAt(0) == '#' ) continue; // skip comments
				String[] tokens = line.split("=");
				if(tokens.length != 2)
				{
					System.out.println("E R R O R !!"
						 + " In /home/lvuser/Parameters.txt, line "+line_no
						 + ": Only found "+tokens.length+" token(s)!"
					);
					continue;
				}
				String name = tokens[0].trim();
				String value = tokens[1].trim();
				m_map.put(name, value);
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if ( reader != null ) 
			{
				try 
				{ 
					reader.close(); 
				} 
				catch ( Exception ex ) 
				{
				}
			}
		}
	}
	
	public static String getString(String key, String def)
	{
		if ( m_map == null ) return def;
		String s = m_map.get(key);
		if( s == null ) return def;
		return s;
	}

	public static double getDouble(String key, double def)
	{
		if ( m_map == null ) return def;
		String st = m_map.get(key);
		if(st == null) return def;
		return Double.valueOf(st);
	}

	public static int getInt(String key, int def)
	{
		if ( m_map == null ) return def;
		String str = m_map.get(key);
		if(str == null) return def;
		return Integer.valueOf(str);
	}

	public static boolean getBoolean(String key, boolean def)
	{
		if ( m_map == null ) return def;
		String str = m_map.get(key);
		if(str == null) return def;
		return Boolean.valueOf(str);
	}



	// 2016-03-13: Methods after here should eventually be deleted...

    public static void displayDouble ( String title, double value, StringBuffer sb )
    {
        SmartDashboard.putNumber ( title+": ", value );
        sb.append(",").append(value);
    }

    public static void displayInt ( String title, int value, StringBuffer sb )
    {
        SmartDashboard.putNumber ( title+": ", value );
        sb.append(",").append(value);
    }

    public static void displayBoolean ( String title, boolean value, StringBuffer sb )
    {
        SmartDashboard.putBoolean ( title+": ", value );
        sb.append(",").append(value?"true":"false");
    }
}

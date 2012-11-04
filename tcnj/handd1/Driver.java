//******************************************************************* 
//David Hand
//December 9, 2011
//CSC 250-01: Accelerated Computer Science I & II
//Peter DePasquale
//******************************************************************* 

package tcnj.handd1;

import java.util.*;
import java.net.*;
import java.io.*;
import java.text.*;
import javafoundations.*;

//*******************************************************************
//Driver.java
//Creates a Linked Binary Search Tree using an online access log to
//store ApacheLog objects.  Populates the tree until the access log
//is fully scanned.  Then, writes various properties to an output
//text file.  Uses a HashMap in order to determine various
//properties.
//*******************************************************************
public class Driver
{
	//---------------------------------------------------------------
	//main: Creates a scanner to read from the online input file.
	//Scanner scans through entire file, one line at a time.  A
	//second scanner scans each line, scanning the request type, 
	//base url, and number of bytes.  The data is manipulated and 
	//assigned to variables.  The data is used to create ApacheLog
	//objects, and the created objects are placed in a
	//LinkedBinarySearchTree.  Once finished scanning the file,
	//uses the tree to answer certain questions regarding the input
	//file.  The answers to the questions are output to a
	//created text file.  A HashMap is used to answer some questions.
	//
	//@throws	IOException	if error with file or Internet occurs
	//---------------------------------------------------------------
	public static void main (String [] args)
	{
		try
		{
		
		//creates URL object containing Apache Log records
		URL input = new URL ("http://s3.amazonaws.com/depasquale/access_log");

		//creates Scanner to read from URL
		Scanner logScan = new Scanner (input.openStream());
		
		//creates Number Formatter to format output file
		NumberFormat fmt = NumberFormat.getInstance();
		fmt.setGroupingUsed(true);
		
		//creates counter for use in program
		int count = 1;
		
		//creates Linked Binary Search Tree to store Apache Log objects
		LinkedBinarySearchTree<ApacheLog> aLogTree = new LinkedBinarySearchTree<ApacheLog>();
		
		//Scan through entire file
		while (logScan.hasNextLine())
		{
			//Scans in next line from URL
			String line = logScan.nextLine();
			
			//Creates Scanner to scan line from URL
			Scanner lineScan = new Scanner (line);
			
			while (count == 1)
			{
				//Scans in next token from current line
				String url = lineScan.next();
				
				//if quotation marks are found, begins building Apache Log object
				if (url.charAt(0) == '"')
				{
					//assigns request type
					String requestType = url.substring(1, url.length());
				
					//scan in full url
					String url2 = lineScan.next();
					
					//finish scanning until end of quotation marks
					while (url.charAt(url.length()-1) != '"')
					{	
						url += lineScan.next();
					}
					
					//make base url from full url, removing data from question mark onward
					String[] array;// = new String[2];
					array = url2.split("\\?");
					String baseURL = array[0];

					//Remove unwanted data
					lineScan.next();
					
					//Get number of bytes
					String bytes = lineScan.next();
			
					if (bytes.equals("-"))
						bytes = "0";
			
					long numBytes = Long.parseLong(bytes);
			
					//create ApacheLog object with data
					ApacheLog aLog = new ApacheLog (requestType, baseURL, numBytes);
					
					//Put ApacheLog object into BinarySearchTree
					//if already exists, increments counter in existing object in tree
					if (aLogTree.contains(aLog))
					{
						ApacheLog aLog2 = aLogTree.find(aLog);
						int accessCount = aLog2.getAccessCount();
						aLog2.setAccessCount(accessCount + 1);
					}
					else
						aLogTree.add(aLog);
					
					//increment count
					count++;
				}
			}
			//reset count for next time through outer loop
			count = 1;
		}
		
		
		//Create output text file
		FileWriter writer = new FileWriter ("treeoutput.txt");
		
		//Prints number of unique pages served
		writer.write ("Total number of pages served: " + fmt.format(aLogTree.size()) +
						" unique pages were served.");
						
		writer.write ("\n\n\n");
		
		
		//Prints each directory, and number of files printed from each directory
		writer.write ("Unique Sections of Web Server and Number Times Served:\n");
		
		//uses HashMap to count number of times each directory served
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		for (ApacheLog log : aLogTree)
		{
			String logURL = log.getBaseURL();
			logURL = logURL.substring(1, logURL.length());
			
			if (logURL.contains("/"))
			{
				String [] array2;
				array2 = logURL.split("/");
				logURL = "/" + array2[0];
			}
			else
			{
				logURL = "/home";
			}
			
			if (hashMap.containsKey(logURL))
			{
				int value = hashMap.get(logURL);
				value = value + 1;
				hashMap.remove(logURL);
				hashMap.put(logURL, value);
			}
			else
			{
				hashMap.put(logURL, 1);
			}
		}
		
		//converts HashMap keys to array and sorts array
		Set<String> set = hashMap.keySet();
		Object [] urlArray;
		urlArray = set.toArray();
		Arrays.sort(urlArray);
		
		//Prints each key (directory) and number of times it was called
		for (Object url : urlArray)
		{
			int counter = hashMap.get(url);
			writer.write (url + "\t" + fmt.format(counter) + "\n");
		}
		
		writer.write ("\n\n");
		
		
		//Prints each unique page served, along with access count and number of bytes
		writer.write ("Each Unique Page, Times Served, and Number of Bytes:\n");
 
		for (ApacheLog log : aLogTree)
		{
			writer.write (log.getBaseURL() + "\t" + fmt.format(log.getAccessCount()) + "\t" +
							fmt.format(log.getNumBytes()) + "\n");
		}
		
		writer.write ("\n\n");
		
		
		//Prints total number of bytes
		long numBytes = 0;
		for (ApacheLog log : aLogTree)
		{
			numBytes += log.getNumBytes();
		}
		
		writer.write ("The total number of bytes of data served by all pages is: " + 
							fmt.format(numBytes) + " bytes.");
		
		
		//end writing to file
		writer.close();
		
		//catch exception and terminate gracefully
		}
		catch (IOException exception)
		{
			System.out.println ("A problem was encountered.  Please try again.");
			System.exit(1);
		}
	}
}

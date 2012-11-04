//******************************************************************* 
//David Hand
//December 9, 2011
//CSC 250-01: Accelerated Computer Science I & II
//Peter DePasquale
//******************************************************************* 

package tcnj.handd1;

//*******************************************************************
//ApacheLog.java
//Represents the request type, base URL, number of bytes, and the
//number of times the URL was served recorded by the Apache Log.
//*******************************************************************
public class ApacheLog implements Comparable<ApacheLog>
{
	//request type and base URL of the Apache Log data
	private String requestType, baseURL;
	
	//number of bytes of the Apache Log data
	private long numBytes;
	
	//number of times page accessed
	private int accessCount;
	
	//---------------------------------------------------------------
	//Constructor: Creates Apache Log object with request type, 
	//base URL, and the number of bytes passed in.  Access count is 
	//set to one.
	//
	//@param	requestType	String of Apache Log's request type
	//@param	baseURL		String of Apache Log's base URL
	//@param	numBytes	Long of Apache Log's number of bytes
	//---------------------------------------------------------------	
	public ApacheLog (String requestType, String baseURL, long numBytes)
	{
	this.requestType = requestType;
	this.baseURL = baseURL;
	this.numBytes = numBytes;
	accessCount = 1;
	}
	
	//---------------------------------------------------------------
	//compareTo: Overwrites compareTo method in Comparable.  
	//Compares two Apache Log objects through the two object's
	//base URLs.  If equal, returns 0.
	//
	//@param	object	Object to be cast as an ApacheLog object
	//@return 	1 if greater than, -1 if less than, 0 if equal
	//---------------------------------------------------------------
	public int compareTo (ApacheLog object)
	{
		ApacheLog compared = (ApacheLog) object;
		
		if (baseURL.compareTo(compared.getBaseURL()) > 0)
			return 1;
		if (baseURL.compareTo(compared.getBaseURL()) < 0)
			return -1;
		else
			return 0;
	}
	
	//---------------------------------------------------------------
	//Accessor for request type.
	//
	//@return the request type
	//---------------------------------------------------------------
	public String getRequestType ()
	{
		return requestType;
	}

	//---------------------------------------------------------------
	//Accessor for base URL.
	//
	//@return the base URL
	//---------------------------------------------------------------	
	public String getBaseURL ()
	{
		return baseURL;
	}

	//---------------------------------------------------------------
	//Accessor for number of bytes.
	//
	//@return the number of bytes
	//---------------------------------------------------------------	
	public long getNumBytes ()
	{
		return numBytes;
	}
	
	//---------------------------------------------------------------
	//Accessor for access count.
	//
	//@return the count
	//---------------------------------------------------------------	
	public int getAccessCount ()
	{
		return accessCount;
	}

	//---------------------------------------------------------------
	//Mutator for request type.
	//
	//@param	requestType2	String of the new request type
	//---------------------------------------------------------------	
	public void setRequestType (String requestType2)
	{
		requestType = requestType2;
	}

	//---------------------------------------------------------------
	//Mutator for base URL.
	//
	//@param	baseURL2	String of the new base URL
	//---------------------------------------------------------------	
	public void setBaseURL (String baseURL2)
	{
		baseURL = baseURL2;
	}

	//---------------------------------------------------------------
	//Mutator for number of bytes.  Number of bytes is not modified 
	//if value is not valid.
	//
	//@param	numBytes2	Long of the new number of bytes
	//---------------------------------------------------------------	
	public void setNumBytes (long numBytes2)
	{
		if (numBytes2 >= 0)
			numBytes = numBytes2;
	}
	
	//---------------------------------------------------------------
	//Mutator for access count.  Access count is not modified if 
	//value is not valid.
	//
	//@param	accessCount2	Int of the new access count
	//---------------------------------------------------------------	
	public void setAccessCount (int accessCount2)
	{
		if (accessCount2 > accessCount)
			accessCount = accessCount2;
	}

	//---------------------------------------------------------------
	//Returns a string representation of the Apache Log URL.
	//Formats number of bytes to have a grouping.
	//
	//@return	String representation of Apache Log URL
	//---------------------------------------------------------------
	public String toString ()
	{
		return ("Request Type: " + requestType + "\nBase URL: " + baseURL + "\nNumber of Bytes: " 
					+ numBytes + "\nAccess Count: " + accessCount);
	}
}
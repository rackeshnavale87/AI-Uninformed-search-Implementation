import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Run 
{
	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis = null;
	    BufferedReader reader = null;
	    BufferedWriter writer = null;
		try
		{
	    fis = new FileInputStream("input.txt");
	    reader = new BufferedReader(new InputStreamReader(fis));
	    Agent a = new Agent();
	        
        String SearchChoice = reader.readLine();
        String StartPoint = reader.readLine();
        String EndPoint = reader.readLine();
        String TotalNode = reader.readLine();
        int Tn = Integer.parseInt(TotalNode);
        
//        System.out.println(SearchChoice+ " \n"+StartPoint + " \n" + EndPoint + " \n" + TotalNode);
/* -------------------------------------------------------------------------------------------------------   */         
            if(SearchChoice.contentEquals("1"))
            {
//            	System.out.println("##########  Choice BFS  ##########");
            	a.bfs(StartPoint,EndPoint,Tn);
            }
        	else if(SearchChoice.contentEquals("2"))
        	{	 
//        		System.out.println("##########  Choice DFS  ##########");
        		a.dfs(StartPoint, EndPoint, Tn);
        	}
        	else if (SearchChoice.contentEquals("3"))
        	{
//        		 System.out.println("##########  Choice UCS  ##########");
            	 a.ucs(StartPoint,EndPoint,Tn);
        	} 
            else 
            {
        		try
        		{
//        			System.out.println("NoPathAvailable");
        			writer = new BufferedWriter(new FileWriter("outputt.txt"));
        			writer.write("NoPathAvailable");
        			writer.close();
        		}
        		catch (IOException ex) 
        	    {} 
        	 }
        }	 
        catch (FileNotFoundException ex) 
        {} 
        finally 
        {
        	try
        	{
        		reader.close();
        		fis.close();
        	} 
        	catch (IOException ex) 
        	{}		
        }
 /* -------------------------------------------------------------------------------------------------------  */            

	}
}

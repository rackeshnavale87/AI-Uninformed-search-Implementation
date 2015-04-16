import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Agent 
{
	String[][] matrix = new String[15][15];
    String[][] child = new String[10][10];
    String[][] cost = new String[10][10];
	LinkedList<String> MainList = new LinkedList<String>();
    LinkedList<Integer> temppath = new LinkedList<Integer>();
    LinkedList<Integer> depth = new LinkedList<Integer>();
    LinkedList<Integer> statelist = new LinkedList<Integer>();
    LinkedList<Integer> PathCost = new LinkedList<Integer>();
	LinkedList<Integer> PathCosttemp = new LinkedList<Integer>();
    LinkedList<String> parent = new LinkedList<String>();
    LinkedList<String> Operate = new LinkedList<String>();
    LinkedList<String> OperateDummy = new LinkedList<String>();    
    LinkedList<String> pathlist = new LinkedList<String>();    
    LinkedList<String> log = new LinkedList<String>();    
	LinkedList<String> temp1 = new LinkedList<String>();


   
// ************************** Sort States Search ************************************ 
	private int getSortedIndex(String string, LinkedList<String> mainList2)
	{
		for (int i=0; i < mainList2.size(); i++) 
		{
	        if (string.compareTo(mainList2.get(i)) < 0) 
	        {return i;}
		}
		return mainList2.size();
	}
	
	
	
//==============================================================================================
//==============================================================================================	
// ************************** Breadth-First Search ************************************ 
//==============================================================================================
//==============================================================================================
	
	
	public void bfs(String StartPoint, String EndPoint, int Tn) throws IOException 
	{
		FileInputStream fis = null;
	    BufferedReader reader = null;
	    BufferedWriter writer = null;

		try
		{
		    fis = new FileInputStream("input.txt");
		    reader = new BufferedReader(new InputStreamReader(fis));
		   
		    for( int i = 0; i < 4; i++ )
		    {reader.readLine();}
		    for( int i = 0; i < Tn; i++ )  
		    {  
		    	try 
		    	{
		    		String temp = reader.readLine();
		    		MainList.addLast(temp);
		    	} 
		    	catch (Exception e) 
				{
					e.printStackTrace();
				}
		    }
		    //System.out.println(MainList);
		    //System.out.println("---------------------");
	    }
	    finally {
	    	try{
	       		reader.close();
	       		fis.close();
	       	} 
	      	catch (IOException e)
	      	{
	    		e.printStackTrace();
	    	}
	     }

        FileInputStream fis1 = null;
	    BufferedReader reader1 = null;
	    fis1 = new FileInputStream("input.txt");
	    reader1 = new BufferedReader(new InputStreamReader(fis1));
		
		String line,nextpop;
        int row = 0, size = 0;
        for( int i = 0; i < Tn + 4; i++ )
	    {reader1.readLine();}
        while ((line = reader1.readLine()) != null) 
        {
            String[] vals = line.trim().split("\\s+");
            if (matrix == null) 
            {
                size = vals.length;
                matrix = new String[size][size];
            }
            for (int col = 0; col < Tn; col++) 
            {
                matrix[row][col] = (vals[col]);
                if(Integer.parseInt(matrix[row][col])!=0)
                {
                	child[row][col]= MainList.get(col);
                }
//                System.out.print(matrix[row][col] + " ");
            }
            row++;
//            System.out.println("\n");
        }    
//        System.out.println("---------------------");
       
        for (int row1 = 0; row1 < Tn; row1++) 
        {
	        for (int col1 = 0; col1 < Tn; col1++) 
	        {
//	        	System.out.print(child[row1][col1]+ " ");
	        }
//	        System.out.println();
        }
//        System.out.println("---------------------");
        
        int count1=0, count2=0, row2=0, col1=0, flag[]={1,0,0,0,0,0,0,0,0,0}; 
        Operate.add(MainList.get(0));
        //log.add(StartPoint);
        OperateDummy.add(MainList.get(0));
        temppath.addFirst(0);
        depth.addFirst(0);
        parent.addFirst("Root Node");
//      System.out.println("Operation List : "+Operate);
//    	System.out.println("All States Nodes : "+OperateDummy);
//      System.out.println(Operate);
//      System.out.println(temppath);
//      System.out.println("---------------------");
        outerloop:while((!Operate.isEmpty()) && (Operate.peekFirst()!=EndPoint))
        {
	        	while(row2<Tn)
	        	{
	        		row2 = count1;
	        		LinkedList<String> temp1 = new LinkedList<String>();
	        		if((Operate.isEmpty()) || Operate.peekFirst().equals(EndPoint))
    	        		{break outerloop;}
		        	
	        		log.add(Operate.peekFirst());
	        		
	        		Operate.pollFirst();
//	        		System.out.println("popping out "+Operate.pollFirst());
	        		
	        		
		        	for (col1 = row2+1; col1 < Tn; col1++) 
	    	        {
    	        	if (child[row2][col1]!=null && flag[col1]!=1)
	    	        	{
	    	        		if((!Operate.isEmpty()) && Operate.peekFirst()==EndPoint)
		    	        		break outerloop;
	    	        		parent.addLast(MainList.get(row2));
			        		temppath.addLast(row2+1);
			        		flag[col1]=1;
	    	        		temp1.add(getSortedIndex((child[row2][col1]), temp1), (child[row2][col1]));	    	  
	    	        	}
	    	        }
    	        	for (int i = 0; i < temp1.size(); i++) 
	    	        {	    	        	
	    	        	Operate.addLast(temp1.get(i));
		        		OperateDummy.addLast(temp1.get(i));
//		        		System.out.println("Pushing: "+temp1.get(i));
		        	}
		        	
//		        	System.out.println("Operation List : "+Operate);
//		        	System.out.println("All States Nodes : "+OperateDummy);
//		        	System.out.println("Temp path: " +temppath);
		        	nextpop = Operate.peekFirst();
		        	countfor:for(count2=1; count2<Tn; count2++)
			        {
			        	if (MainList.get(count2).equals(nextpop))
			        	{
			        		count1=count2;
			        		break countfor;
			        	}
			        }
//	        		System.out.println("---------------------");
//	        		System.out.println("Parent list----> " + parent);
	        	}
        }
        
//		System.out.println("Temp path: " +temppath);
//		System.out.println("Path to reach End point is:- ");

		int j=0;
		j = temppath.getLast();
		pathlist.addLast(StartPoint); 
//		System.out.println(StartPoint);
		while (j>1)
		{
			pathlist.addLast(OperateDummy.get(j-1)); 
//			System.out.println(OperateDummy.get(j-1));
			j = temppath.get(OperateDummy.indexOf(OperateDummy.get(j-1)));
			
		}
		pathlist.addLast(EndPoint);
//    	System.out.println(EndPoint);
//		System.out.println(pathlist);
		
		if(Operate.isEmpty())
        {
//			System.out.println("BFS finished");
//			System.out.println("No such a path exist.");
        	return;
        }
		else
		{
			//System.out.println(pathlist);
			//System.out.println(log);
			//System.out.println(pathlist.size()-1);
			
			try
			{
				int pw,lw;
				writer = new BufferedWriter(new FileWriter("output.txt"));
				for(pw = 0; pw < pathlist.size()-1;pw++)
				{
					writer.write(pathlist.get(pw));
					writer.write("-");
				}
				writer.write(pathlist.get(pw));
				writer.newLine();
				for(lw = 0; lw < log.size()-1;lw++)
				{
					writer.write(log.get(lw));
					writer.write("-");
				}
				writer.write(EndPoint);
				writer.newLine();
				writer.write(Integer.toString(pathlist.size()-1));
				writer.close();
			}
			catch (IOException ex) 
		    {} 
//			System.out.println("Total path cost for BFS traversal is : "+ (pathlist.size()-1));
		}
	}
//==============================================================================================
//==============================================================================================	
// ************************** Depth-First Search ************************************ 
//==============================================================================================
//==============================================================================================
	
	
	public void dfs(String StartPoint, String EndPoint, int Tn) throws IOException 
	{
		FileInputStream fis = null;
	    BufferedReader reader = null;
	    BufferedWriter writer = null;

		try
		{
		    fis = new FileInputStream("input.txt");
		    reader = new BufferedReader(new InputStreamReader(fis));
		    for( int i = 0; i < 4; i++ )
		    {reader.readLine();}
		    for( int i = 0; i < Tn; i++ )  
		    {  
		    	try 
		    	{
		    		String temp = reader.readLine();
		    		MainList.addLast(temp);
		    	} 
		    	catch (Exception e) 
				{
					e.printStackTrace();
				}
//		    	System.out.println(MainList);
		    }
//		    	System.out.println("---------------------");
	    }
	    finally {
	    	try{
	       		reader.close();
	       		fis.close();
	       	} 
	      	catch (IOException e)
	      	{
	    		e.printStackTrace();
	    	}
	     }

        FileInputStream fis1 = null;
	    BufferedReader reader1 = null;
	    fis1 = new FileInputStream("input.txt");
	    reader1 = new BufferedReader(new InputStreamReader(fis1));
		
		String line,nextpop="Start";     
        int row = 0, size = 0;
        for( int i = 0; i < Tn + 4; i++ )
	    {reader1.readLine();}
        while ((line = reader1.readLine()) != null) 
        {
            String[] vals = line.trim().split("\\s+");
            if (matrix == null) 
            {
                size = vals.length;
                matrix = new String[size][size];
            }
            for (int col = 0; col < Tn; col++) 
            {
                matrix[row][col] = (vals[col]);
                if(Integer.parseInt(matrix[row][col])!=0)
                {
                	child[row][col]= MainList.get(col);
                }
 //               System.out.print(matrix[row][col] + " ");
            }
            row++;
//            System.out.println("\n");
        }    
//       System.out.println("---------------------");
       
        for (int row1 = 0; row1 < Tn; row1++) 
        {
	        for (int col1 = 0; col1 < Tn; col1++) 
	        {
//	        	System.out.print(child[row1][col1]+ " ");
	        }
//	        System.out.println();
        }
//        System.out.println("---------------------");
        int row2=0, count1=0, count2=0, temp1c = 0,col1=0, flag[]={1,0,0,0,0,0,0,0,0,0}; 
        Operate.push(MainList.get(0));
        log.add(StartPoint);
        OperateDummy.push(MainList.get(0));
        temppath.push(0);
        statelist.push(1);
        parent.push("Root Node");
//        System.out.println("Operation List : "+Operate);
//    	System.out.println("All States Nodes : "+OperateDummy);
//        System.out.println(Operate);
//        System.out.println(temppath);
//        System.out.println("---------------------");

        outerloop:while((!Operate.isEmpty()) && (Operate.peekFirst()!=EndPoint))
        {

	        	while(row2<Tn)
	        	{
	        		row2=count1;
	        		LinkedList<String> temp1 = new LinkedList<String>();
	        		if((Operate.isEmpty()) || Operate.peekFirst().equals(EndPoint))
    	        		{break outerloop;}
	        
	        			Operate.pop();
//	        		System.out.println("popping out "+Operate.pop());

		        	for (col1 = row2+1; col1 < Tn; col1++) 
	    	        {
    	        	if (child[row2][col1]!=null && flag[col1]!=1)
	    	        	{
	    	        		if(Operate.peekFirst()==EndPoint)
		    	        		break outerloop;
	    	        		parent.push(MainList.get(row2));
			        		temppath.push(row2+1);
			        		flag[col1]=1;
			        		temp1.add(getSortedIndex((child[row2][col1]), temp1), (child[row2][col1]));
	    	        	}
	    	        }	        	
		        	temp1c = temp1.size();
    	        	for (int i = 0; i <= temp1c && !temp1.isEmpty(); i++)
	    	        {	    	        	
	    	        	Operate.push(temp1.getLast());
	    	        	statelist.push((MainList.indexOf(temp1.getLast())+1));
		        		OperateDummy.push(temp1.getLast());
//		        		System.out.println("Pushing: "+temp1.getLast());
		        		temp1.pollLast();
		        	}
		        	
//		        	System.out.println("Operation List : "+Operate);
//		        	System.out.println("All States Nodes : "+OperateDummy);
//		        	System.out.println("Temp path: " +temppath);
		        	nextpop = Operate.peekFirst();
		        	log.add(nextpop);
//		        	System.out.println("Next pop, so row will be of : "+nextpop);/////////////////
		        	countfor:for(count2=1; count2<Tn; count2++)
			        {
			        	if (MainList.get(count2).equals(nextpop))
			        	{
			        		count1=count2;
			        		break countfor;
			        	}
			        }
//	        		System.out.println("---------------------");
//	        		System.out.println("Parent list----> " + parent);
	        	}
        }
//		System.out.println("Temp path: " +temppath);
//		System.out.println("Path to reach End point is:- ");

//		System.out.println("Statelist is :" +statelist);
		
		
		int j=0;
		j = temppath.getFirst();
		pathlist.addLast(StartPoint); 
//		System.out.println(StartPoint);
		while (j>1)
		{
			pathlist.addLast(MainList.get((j)-1)); 
//			System.out.println(MainList.get(statelist.get(j)-1));//(j)-1
			j =(statelist.indexOf(MainList.get(statelist.get(j)-1)));
		}
		pathlist.addLast(EndPoint);
//    	System.out.println(EndPoint);
//		System.out.println(pathlist);    	
		System.out.println(pathlist);  
		if(Operate.isEmpty() && !nextpop.equals(EndPoint))
        {
			System.out.println("BFS finished");
			System.out.println("No path Exists from" + StartPoint + " to "+ EndPoint);
			writer = new BufferedWriter(new FileWriter("Output.txt"));
			writer.write("No path Exists from" + StartPoint + " to "+ EndPoint);
        	return;
        }
		else
		{
			try
			{
				int pw,lw;
				writer = new BufferedWriter(new FileWriter("output.txt"));
				for(pw = 0; pw < pathlist.size()-1;pw++)
				{
					writer.write(pathlist.get(pw));
					writer.write("-");
				}
				writer.write(pathlist.get(pw));
				writer.newLine();
				for(lw = 0; lw < log.size()-1; lw++)
				{
					writer.write(log.get(lw));
					writer.write("-");
				}
				writer.write(EndPoint);
				writer.newLine();
				writer.write(Integer.toString(pathlist.size()-1));
				writer.close();
			}
			catch (IOException ex) 
		    {} 
			//System.out.println("Total path cost for DFS traversal is : "+ (pathlist.size()-1));
		}
	}
//==============================================================================================
//==============================================================================================
// ************************** Uniformed-Cost Search ************************************ 
//==============================================================================================
//==============================================================================================
	
	
	private int getSortedIndexPC(int i, LinkedList<Integer> PathCost2) 
	{
		for (int m=0; m < PathCost2.size(); m++) 
		{
	        if (i >= (PathCost2.get(m)))
	        {return m;}
		}
		return PathCost2.size();
	}

	
	@SuppressWarnings("unused")
	public void ucs(String StartPoint, String EndPoint, int Tn) throws IOException 
	{
		FileInputStream fis = null;
	    BufferedReader reader = null;
	    BufferedWriter writer = null;

		try
		{
		    fis = new FileInputStream("input.txt");
		    reader = new BufferedReader(new InputStreamReader(fis));
		    for( int i = 0; i < 4; i++ )
		    {reader.readLine();}
		    for( int i = 0; i < Tn; i++ )  
		    {  
		    	try 
		    	{
		    		String temp = reader.readLine();
		    		MainList.addLast(temp);
		    	} 
		    	catch (Exception e) 
				{
					e.printStackTrace();
				}
		    }
//		    System.out.println(MainList);
//		    System.out.println("---------------------");
	    }
	    finally {
	    	try{
	       		reader.close();
	       		fis.close();
	       	} 
	      	catch (IOException e)
	      	{
	    		e.printStackTrace();
	    	}
	     }

        FileInputStream fis1 = null;
	    BufferedReader reader1 = null;
	    fis1 = new FileInputStream("input.txt");
	    reader1 = new BufferedReader(new InputStreamReader(fis1));
		
		String line,nextpop="";
        int row = 0, size = 0;
        for( int i = 0; i < Tn + 4; i++ )
	    {reader1.readLine();}
        while ((line = reader1.readLine()) != null) 
        {
            String[] vals = line.trim().split("\\s+");
            if (matrix == null) 
            {
                size = vals.length;
                matrix = new String[size][size];
            }
            for (int col = 0; col < Tn; col++) 
            {
                matrix[row][col] = (vals[col]);
                if(Integer.parseInt(matrix[row][col])!=0)
                {
                	child[row][col]= MainList.get(col);
                }
//               System.out.print(matrix[row][col] + " ");
            }
            row++;
//            System.out.println("\n");
        }    
//        System.out.println("---------------------");
       
        for (int row1 = 0; row1 < Tn; row1++) 
        {
	        for (int col1 = 0; col1 < Tn; col1++) 
	        {
//	        	System.out.print(child[row1][col1]+ " ");
	        }
//	        System.out.println();
        }
//        System.out.println("---------------------");
        
        int pc = 0, count1=0, count2=0, row2=0, col1=0, flag[]={1,0,0,0,0,0,0,0,0,0}; 
        Operate.add(MainList.get(0));
        log.add(StartPoint);
        OperateDummy.add(MainList.get(0));
        PathCost.add(0);
        temppath.addFirst(0);
        depth.addFirst(0);
        parent.addFirst("Root Node");
//        System.out.println("Operation List : "+Operate);
//    	System.out.println("All States Nodes : "+OperateDummy);
//        System.out.println(Operate);
//        System.out.println(temppath);
//        System.out.println("---------------------");
        outerloop:while((!Operate.isEmpty()) && (Operate.peekLast()!=EndPoint))//////end could be above in sorted list
        {
        	innerloop:while(row2<Tn)
	        	{
	        		row2 = count1;

	        		if(Operate.peekLast().equals(EndPoint))
    	        		{break outerloop;}

	        		pc = PathCost.get(Operate.indexOf((Operate.getLast())));
	        		
	        		//flag[MainList.indexOf(Operate.peekLast())]=1;
	        		
	        		
	        		Operate.pollLast();
//	        		System.out.println("popping out "+Operate.pollLast());
	        		
		        	for (col1 = row2+1; col1 < Tn; col1++) 
	    	        {
    	        	if (child[row2][col1]!=null && flag[col1]!=1)
	    	        	{
	    	        		if(Operate.peekLast()==EndPoint)
		    	        		break outerloop;
	    	        		parent.addLast(MainList.get(row2));
			        		temppath.addLast(row2+1);
			        		flag[col1]=1;
			        		int putat =getSortedIndex((child[row2][col1]), temp1);
			        		
	    	        		temp1.add(putat, (child[row2][col1]));

	    	        		PathCosttemp.add(putat, pc+Integer.parseInt(matrix[row2][col1]));
	    	        		
	    	        		//temp2.add(getSortedIndexPC((Integer.parseInt(matrix[row2][col1])), temp2), (Integer.parseInt(matrix[row2][col1])));
	    	        	}
	    	        }
    	        	for (int i = 0; i < temp1.size(); i++) 
	    	        {
    	        		int putat = getSortedIndexPC((PathCosttemp.get(i)), PathCost);
    	        		PathCost.add(putat,(PathCosttemp.get(i)));
    	        		//Operate.addLast(temp1.get(i));
    	        		Operate.add(putat,temp1.get(i));
    	        		//OperateDummy.addLast(temp1.get(i));
    	        		OperateDummy.add(putat,temp1.get(i));
//		        		System.out.println("Pushing: "+temp1.get(i));

		        	}
    	        	temp1.clear();
	        		PathCosttemp.clear();
	        		
//		        	System.out.println("Operation List : "+Operate);
//		        	System.out.println("All States Nodes : "+OperateDummy);
//		        	System.out.println("@@@ Path cost -->> "+ PathCost);
//		        	System.out.println("Temp path: " +temppath);
		        	nextpop = Operate.peekLast();
		        	log.add(nextpop);
		        	countfor:for(count2=1; count2<Tn; count2++)
			        {
			        	if (MainList.get(count2).equals(nextpop))
			        	{
			        		count1=count2;
			        		row2 = count1;
			        		break countfor;
			        	}
			        }
//	        		System.out.println("---------------------");
//	        		System.out.println("Parent list----> " + parent);
	        	}
        }
//		System.out.println("Temp path: " +temppath);
//		System.out.println("Path to reach End point is:- ");

		int j=0;
		j = temppath.getLast();
		pathlist.addLast(StartPoint); 
//		System.out.println(StartPoint);
		while (j>1)
		{
			pathlist.addLast(OperateDummy.get(j-1)); 
//			System.out.println(OperateDummy.get(j-1));
			j = temppath.get(OperateDummy.indexOf(OperateDummy.get(j-1)));
			
		}
		pathlist.addLast(EndPoint);
//    	System.out.println(EndPoint);
//		System.out.println(pathlist);    
//		System.out.println("Log : " +log);
		
		
		if(Operate.isEmpty())
        {
//			System.out.println("BFS finished");
//			System.out.println("No such a path exist.");
        	return;
        }
		else
		{
			try
			{
				int pw,lw;
				writer = new BufferedWriter(new FileWriter("output.txt"));
				for(pw = 0; pw < pathlist.size()-1;pw++)
				{
					writer.write(pathlist.get(pw));
					writer.write("-");
				}
				writer.write(pathlist.get(pw));
				writer.newLine();
				for(lw = 0; lw < log.size()-1;lw++)
				{
					writer.write(log.get(lw));
					writer.write("-");
				}
				writer.write(EndPoint);
				writer.newLine();
				writer.write(Integer.toString((PathCost.get((OperateDummy.indexOf(EndPoint))))));
				writer.close();
			}
			catch (IOException ex) 
		    {} 
//			System.out.println("Total path cost for BFS traversal is : "+ (PathCost.get((OperateDummy.indexOf(EndPoint)))));
		}

	}



//-------------------------------------- END of "AGENT" class --------------------------------------
}
		


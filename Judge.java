import java.util.*;

public class Judge {
    static void addEdge(ArrayList<ArrayList<Integer> > adj, //adds edges between verts
                        int u, int v)
    {
        adj.get(u).add(v); //creates directed edge
    }
    //My testing function
    public static void main(String[] args)
    {
        // Creating a graph with x vertices

        int V = 4;
        int[][] trust = {{1,3},{1,4},{2,3},{2,4},{4,3}};

        System.out.print(findJudge(V,trust));
    }
    //function to find judge
    static public int findJudge (int N, int [][] trust) {
        int V = N; //number of vertices
        int foundJudge = -1; //int found judge to -1
        int numberOfFinds = 0; //Keeps track if we're finding mult judges

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(V); //constructor for adj list

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>()); //populating it
        }
        for (int i = 0; i < trust.length; i++)  //creates edges from trust array
        {
            addEdge(adj, trust[i][0] - 1, trust[i][1] - 1);
        }

        for (int i = 0; i < adj.size(); i++) //function that searchs for item in list with no connections to other nodes.
        {
            if (adj.get(i).size() == 0) {
                foundJudge = i + 1;
                numberOfFinds++;

            }
        }

        if (numberOfFinds > 1) //if we found more than one judge cand, just go ahead and return -1
        {
            return -1;
        }

        if(foundJudge > 0)  //this check makes sure that each other vertex has a path to the judge found previously, if not, returns -1
        {
            for (int i = 0; i < adj.size(); i++)
            {
                if(i != foundJudge -1)
                {
                    if (!adj.get(i).contains(foundJudge - 1))
                    {
                        return -1;
                    }
                }
            }
        }
        return foundJudge; //returns -1 if no judge was found, or the number cor to the judge.
    }
}

<%@ page import="edu.gatech.cs2340.todo.model.Player" %>
<%@ page import="edu.gatech.cs2340.todo.model.Territory" %>
<%@ page import="edu.gatech.cs2340.todo.model.Resource" %>
<%@ page import="edu.gatech.cs2340.todo.model.Unit" %>
<%@ page import="java.util.*" %>


<% //some initializations
int id = 1000;
ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("players"); 
Unit ACUnit = new Unit("Alpha-Centaurian Space Frigate",5,3,1,null);
Unit PolarisUnit = new Unit("Polarian Manta",4,5,2,null);
Unit CharUnit = new Unit("Char Swarmling",3,2,0,null);
Unit BorgUnit = new Unit("Borg Assimilator",8,2,2,null);
Unit HALUnit = new Unit("HSS Probe",5,3,1,null);
Unit MidiUnit = new Unit("Midichlorian Force",6,4,1,null);
int armysize = (10-players.size()); 
Collections.sort(players);
 ArrayList<String> countries = new ArrayList<String>(); 
for (Player player: players) { 

	 countries.add(player.getCountry()); 
	 if(player.getCountry().equals("Alpha-Centauri")){ACUnit.setOwner(player);}
	 else if(player.getCountry().equals("Polaris")){PolarisUnit.setOwner(player);}
	 else if(player.getCountry().equals("Char")){CharUnit.setOwner(player);}
	 else if(player.getCountry().equals("Borg")){BorgUnit.setOwner(player);}
	 else if(player.getCountry().equals("HAL Space Station")){HALUnit.setOwner(player);}
	 else if(player.getCountry().equals("Midichloria")){MidiUnit.setOwner(player);}
}

%>
<html>
<head>
<title>Pregame!</title>



<%//instantiating the map 
Territory[][] map = new Territory[9][15];

  for(int a = 0; a <9; a++){
	for(int b = 0; b < 15; b++)	{
			int[] coord = new int[2];
			coord[0] = a;
			coord[1] = b; 
			map[a][b] = new Territory("Territory ["+a+","+b+"]",coord,"New Foundland"); 
			
 } 
}%>

<%//The map is the same each time, so hard coded locations
 for(int a = 1; a< 15;a+=2){ 
 map[4][a].addResource(new Resource("Asteroid",map[4][a]));} 

 //absolutely disgusting pre-built game board unit placement
 //upper left is Alpha-Centauri
 if(countries.contains("Alpha-Centauri")){
 map[0][1].makeHomeBase(players.get(countries.indexOf("Alpha-Centauri"))); 
 int[] coords ={0,0,1,0,1,1,1,2,0,2};
 id = spawn(map, ACUnit,armysize,coords,id); 
 }
 
 //upper middle is Polaris
 if(countries.contains("Polaris")){
	map[0][7].makeHomeBase(players.get(countries.indexOf("Polaris"))); 
	int[] coords = {0,6,1,6,1,7,1,8,0,8};
 	spawn(map, PolarisUnit,armysize,coords,id);  id+=coords.length/2*armysize;
 }
 //upper right is Midichloria
 if(countries.contains("Midichloria")){
	map[0][13].makeHomeBase(players.get(countries.indexOf("Midichloria")));
	int[] coords = {0,12,1,12,1,13,1,14,0,14};
	id=spawn(map, MidiUnit,armysize,coords,id); 
}
 
 //bottom left Char
 if(countries.contains("Char")){
	map[8][1].makeHomeBase(players.get(countries.indexOf("Char")));
	int[] coords = {8,0,7,0,7,1,7,2,8,2};
	id=spawn(map, CharUnit,armysize,coords,id); 
 }

 //bottom middle is HAL Space Station
 if(countries.contains("HAL Space Station")){
	 map[8][7].makeHomeBase(players.get(countries.indexOf("HAL Space Station"))); 
	 int[] coords = {8,6,7,6,7,7,7,8,8,8};
	id= spawn(map, HALUnit,armysize,coords,id); 
}
 
 //bottom right is Borg
 if(countries.contains("Borg")){
	 map[8][13].makeHomeBase(players.get(countries.indexOf("Borg"))); 
	 int[] coords = {8,14,7,14,7,13,7,12,8,12};
	id= spawn(map, BorgUnit,armysize,coords,id); 
}%>


<% for (Player player: players) { %>
<span style ="color:<%= colorCode(player) %>"> <%= player %></span><br>
<% } %>
<br>
Let's make a map! <br><br>
The "O"s represent each player's home base!<br>
The "X"s represent impassable asteroids!<br>
The "#"s in each territory represent the number of Units in each territory!<br>
<br>
<br>



<font face="courier">
<%//printing the map 
  for(int a = 0; a <9; a++){
	for(int b = 0; b < 15; b++)	{
 if(map[a][b].hasResources()) { %>[x]<% }   
 if(map[a][b].isHomeBase()) {%>[<span style="color:<%=colorCode(map[a][b].getPlayer())%>">O</span>]<%} 
 if(map[a][b].isOccupied()) { %>[<span style="color:<%=colorCode(map[a][b].getPlayer())%>"><%=map[a][b].getOccupants().size()%></span>]<%} 
if (!map[a][b].hasResources() && !map[a][b].isHomeBase() && !map[a][b].isOccupied()) {%>[ ]<%}  

 }%>
<br>	
<%}%>

</font>
<%//color string can be probably be stored in player instead of making a method to calculate it
//returns the color associated with each country %>
<%! public String colorCode(Player player){ 
if (player==null) {return "pink";}
else if (player.getCountry().equals("Polaris")) { return "purple";}
else if (player.getCountry().equals("Alpha-Centauri")) { return "green";}
else if (player.getCountry().equals("Char")) { return "red";} 
else if (player.getCountry().equals("Midichloria")) { return "blue";}
else if (player.getCountry().equals("Borg")) { return "gray";} 
else if (player.getCountry().equals("HAL Space Station")) { return "orange";}
 return "";} %>
 
 <%! public int spawn(Territory[][] maps, Unit unit, int amount, int[] coords,int idn) 
 {
	
	for(int i = 0; i < coords.length; i+=2)
	{
		for(int a = 1; a <= amount; a++)
		{
		Unit toBeAdded = new Unit(unit.getName(),unit.getHealth(),unit.getStrength(),unit.getDefense(),unit.getOwner());
		toBeAdded.setID(idn++);
		toBeAdded.setTerritory(maps[coords[i]][coords[i+1]]);
		toBeAdded.getOwner().addUnit(toBeAdded);
		maps[coords[i]][coords[i+1]].addUnit(toBeAdded);
		}
		System.out.println("Spawning "+amount+" "+unit.getName()+"(s) at ["+coords[i]+","+coords[i+1]+"]");
 	}
	return idn;
 }

 %>
 


<% for(Player player: players) 
{
	System.out.println(player.getArmy().keySet());
	TreeMap<Integer,Unit> army = player.getArmy();
	for(Integer idnum: army.keySet())
		System.out.println(army.get(idnum).getTerritory());

System.out.println("~~~~");

}
%>

<%/*  for(Territory[] a: map) 
	{
	for(Territory b: a)
		{
			System.out.println(b);
			System.out.println(b.getOccupants());
		}
	System.out.println("=====");

	}
System.out.println("~~~~");
 */
%>


</head>
</html>
Changelog
============================
6/21/2013
-Confirmation.jsp now displays the Map, along with Home Territories, Asteroids and 	Units in ascii form
-Made several changes to Player, Unit and Territory classes
-Added ColorCode and spawn methods to Confirmation.jsp
-M2 finished

6/19/2013
-Finished transition from Todo to Player class
-Updated Player, Territory, Resource and Unit classes
-Added some Territory and Resource testing to Confirmation.jsp

6/18/2013
-Started changelog
-Fixed player limits, can't go to confirmation screen with less than 3 players or more than 6, clicking confirm does nothing.
-"Fixed" assigned number of troops. The idea is to not directly copy Risk, so this may be changed in the future.
-Started Player and Territory classes, still using Todo so as to note break dependancies
-Changed TreepMap of Todo (soon to be Players) to ArrayList
-Added seperateCountries method to TodoServlet; ensures each player is in a different country
-M1 for sure finished
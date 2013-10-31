// Agent player1 in project prj_MAS_Bingo

/* Initial beliefs and rules */

sizeCard(math.random(9)+1).
maxNumberToSort(math.random(99)+1).

/* Initial goals */

!create.

/* Plans */

+!create : true <- 
  ?setupBingo (ID).
	
+?setupBingo (C) : true <-
  makeArtifact("b0", "tablet.Bingo", [], C);
  focus(C).
	
-?setuBingo(C) : true <-
	wait(10);
	!create.	
	
//Perceptions
	
//Signal status
+status(S) : 	S == "ready" <- 
	start.		

+!kqml_received(Sender, askOne, buildCard, Response) : sizeCard(Size)  & maxNumberToSort(Max)  <-		
	ias.buildCard(Size, Max, Card);    	
	.send(Sender, tell, Card, Response).	
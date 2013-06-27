// Agent player1 in project prj_MAS_Bingo

/* Initial beliefs and rules */

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
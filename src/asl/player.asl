// Agent player in project prj_MAS_Bingo

/* Initial beliefs and rules */

/* Initial goals */

!participate.

/* Plans */

+!participate: true<- 
	?myArtifact (ID);	
	focus(ID);
	sell;   //buy	- adquire uma carteça
	println("Comprei uma cartela ...no bingo:", ID);
	ias.buildCard(5, 40, Card);	    
	.concat("Cartela:", Card,S);	
     println(S);
     +myCard(Card);                                   //adiciona uma nova crença com a cartela do Bingo
     Hit = 0;
     +myHits(Hit).                                      //adiciona uma nova crença com o total de acertos
	
+?myArtifact(C) : true <-
	lookupArtifact("b0", C).	
	
-?myArtifact(Art) : true <-
	.wait(1000);
	println("Esperando por um bingo.");
	!participate.	
   
//Perceptions of the signals
	
+status(S) : S == "sorted" & myCard(Card) & myHits(Hit) <-	
	?numSorted(V);
	println("Opa, percebi um numero sorteado ... ", V);
	if (.member(V, Card) ) {		
		+myHits(Hit+1);
		println("acertei:", V, " Ate agora acertei ", Hit+1, " numero(s) em um total de ", .length(Card));
	}.
	
+myHits(Hit) : myCard(Card) <-
	if (Hit == .length(Card)){
		 print("Gaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnhhhhhhhhhhheeeeeeeeiiiiiii!!");
		 winner;
	}.
	

+status(S) : 	S == "started" <-
	println("Legal! Bingo inciado!").
	
+status(S) : 	S == "stoped" <-
   println("Ahhhh .. já acabou.").   	
	
//Percepctions of the Observable Properties	
+numSorted(V).
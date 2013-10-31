package ias;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.Term;
import jason.functions.Random;

import java.util.HashSet;
import java.util.Set;

public class buildCard extends DefaultInternalAction{
	
	private Set <Integer> box = new HashSet <Integer>();
	
	@Override
	public Object execute (TransitionSystem ts, Unifier un, Term[] args) throws Exception{
				
		NumberTerm size = (NumberTerm) args[0];           //tamanho da cartela
		NumberTerm max= (NumberTerm) args[1];		     // TETO para um numero sorteado
		
		Term[] terms = new Term[1];
		terms[0] = args[1];
		
		box.clear();
		
		Random r = new Random();
		while (box.size() < size.solve())
			box.add((int)r.evaluate(ts, terms)+1);
			//box.add(r.nextInt((int)max.solve()) + 1);     //garantir a não repetição dos números da cartela
		
		ListTerm result = new ListTermImpl();
		for (Integer i : box){
			Term t = new NumberTermImpl(i);
			result.add(t);																//converter a cartela em um feedback para o agente
		}
		
		return un.unifies(result, args[2]);
		
	}

}

package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	private ListNode tempListNode;
	//private ListNode lastListNode;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		String prevWord = null;
		String w = null;
		List<String> tokens = getTokens("[a-zA-Z.,'!?]+", sourceText);
		try {
			//System.out.println(tokens);
			starter = tokens.get(0);
			//System.out.println(starter.toString());
			if (starter != null) {
				prevWord = starter;
			}
			for (int i = 0; i < (tokens.size() - 1); i++) {
				w = tokens.get(i + 1);
				if ( isNode( prevWord.toString() ) ) {
					tempListNode.addNextWord(w.toString());
					//System.out.println("train isNode true\n");
				}
				else {
					//System.out.println("Create new ListNode " + prevWord.data.toString());
					tempListNode = new ListNode(prevWord.toString());
					wordList.add(tempListNode);
					tempListNode.addNextWord(w.toString());
					//System.out.println("train isNode FALSE\n");
				}
				prevWord = w;
			}
				if ( isNode( prevWord.toString() ) ) {
					tempListNode.addNextWord(starter);
				}
				else {
					tempListNode = new ListNode(prevWord.toString());
					wordList.add(tempListNode);
					tempListNode.addNextWord(starter);
				}
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String currWord = starter;
		String output = "";
		String w = "";
		if (starter != null) {
			output = output + currWord + " ";
		}
		//System.out.println(starter);
		for (int i = 1; i < numWords; i++) {
			tempListNode = nodeCorresp(currWord);
			try {
				w = tempListNode.getRandomNextWord(rnGenerator);
				output += w + " ";
				currWord = w;
				//numWords --;
			}
			catch (NullPointerException e) {
				//System.out.println("Error during runtime: " + e);
				//return null;
			}
			
		}
		
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		tempListNode = null;
		starter = null;
		train(sourceText);
		
	}
	
	// TODO: Add any private helper methods you need here.
	protected ListNode nodeCorresp (String currWord) {
		//wordList = new LinkedList<ListNode>();
		try {
			for (ListNode n : wordList) {
				//System.out.println(n);
				if ( n.getWord().equals(currWord) ){
					return n;
				}
			}
		}
		catch (NullPointerException e) {
			return null;
		}
		return null;
	}
	
	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}

	
	protected boolean isNode(String word) {
		for (ListNode n : wordList) {
			//System.out.println("n.getWord " + n.getWord() + " " + word);
			if ( n.getWord().equals(word) ){
				//System.out.println("isNode true");
				tempListNode = n;
				return true;
			}
		}
		tempListNode = null;
		//System.out.println("isNode false");
		return false;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		
		String textEmpty = "";
		System.out.println(textEmpty);
		gen.retrain(textEmpty);
		System.out.println(gen);
		System.out.println(gen.generateText(10));
		
		
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		//Random index = generator;
		//System.out.println(word);
		//System.out.println(nextWords);
		try {
			String randomWord = nextWords.get( generator.nextInt(getListSize()) );
			return randomWord;
		}
		catch (NullPointerException e) {
			return "The nextWord list is empty";
		}
		//System.out.println("randomWord " + randomWord);
	}
	
	public int getListSize() {
		return this.nextWords.size();
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



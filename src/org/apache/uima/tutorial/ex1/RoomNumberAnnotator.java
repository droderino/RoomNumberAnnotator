package org.apache.uima.tutorial.ex1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tutorial.RoomNumber;

public class RoomNumberAnnotator extends JCasAnnotator_ImplBase {

	private Pattern mYorktownPattern = Pattern.compile("\\b[0-4]\\d-[0-2]\\d\\d\\b");
	private Pattern mHawthornePattern = Pattern.compile("\\b[G1-4][NS]-[A-Z]\\d\\d\\b");
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		String docText = aJCas.getDocumentText();
		
		Matcher matcher = mYorktownPattern.matcher(docText);
		int pos = 0;
		while( matcher.find(pos) )
		{
			RoomNumber annotation = new RoomNumber(aJCas);
			annotation.setBegin(matcher.start());
			annotation.setEnd(matcher.end());
			annotation.setBuilding("Yorktown");
			annotation.addToIndexes();
			pos = matcher.end();
		}
		
		matcher = mHawthornePattern.matcher(docText);
		pos = 0;
		while( matcher.find(pos) )
		{
			RoomNumber annotation = new RoomNumber(aJCas);
			annotation.setBegin(matcher.start());
			annotation.setEnd(matcher.end());
			annotation.setBuilding("Hawthorne");
			annotation.addToIndexes();
			pos = matcher.end();
		}
	}

}

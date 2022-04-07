package SeleniumTest;

public class xPathLearning {

	
	/*XPath - XML path (Xtensible Markup Language)
	 *HTML and XML both have same parent - SGML(standard genelised ML)
	 *so XML path has the capabality to handle html.
	 *Absolute and Relative
	 *Absolute - starts from root node - /
	 *Relative - using relatives elements - //
	 * 
	 *<input name="txtUsername" >
	 *input tag is element
	 *name is attribute
	 *txt is value 
	 */
	
	
	//Syntax - //Element[@Attribute='Value test']
	
	public static void main(String[] args) {
		
		/* 1. Locating elements with known attributes
		 * Syntax: //*[@name='txtUsername']
		 * 
		 * 2. Locating Elements with Known Elements and Attributes
		 * Syntax: //input[@name='txtUsername']
		 * 
		 * 3.Locating Elements with Known Visible Text (exact match)
		 * Syntax:  //*[text()='OrangeHRM, Inc']   //Elementname[text()='exact text']
		 * 
		 * 4.Locating Elements in partial text
		 * Syntax:  //a[contains(text(),'OrangeHRM')]  //EleName[contains(text(),'part of terxt')
		 * 
		 * 5. Locating  Elements with Multiple Attributes
		 * Syntax: //*[@name='txtUsername'][@type='text']    //*[attribute1='value][attribute2='value']
		 * 
		 * 6.Locating Elements when starting visible text is known
		 * Syntax: //a[starts-with(text(),'OrangeHRM')]   //eelNAme[starts-with(text(),'starting text')]
		 * 
		 * 7.Locating Elements with Dynamic Attributes
		 * Syntax: //elementNAme[contains(@attributeName,'part of the value')]
		 *         //elementNAme[starts-with(@attributeName,'fixed prefix of the value')]
		 *         
		 *   //a[contains(@href,'orange')]
		 *   //a[starts-with(@href,'http://www.ora')]
		 *   
		 *   Xpath can do bidirectional navigation - forward and backward
		 *   Locatin emelemnts relative to known element.
		 *   
		 * 8. Locating a parent element
		 *   //<knownXpath>/parent::elementName
		 *   //*[@id='divUsername']/parent::form --if dont know the element use *
		 * 
		 * 9. Locating a child element
		 *		//<knownxpath>/child::<element name>
		 *		//*[@id='frmLogin']/child::input
		 *		//*[@id='frmLogin']/input
		 *
		 * 10. Locating ancestor -- parent's parent
		 *      //<xpathKnownele>/ancestor::<elementNAme>
		 *      //<xpathKnownele>/ancestor::*
		 *      
		 *      //*[@name='txtUsername']/ancestor::div
		 *      //*[@name='txtUsername']/ancestor::*
		 *      
		 * 11.  Following elements
		 * 		//<knownxp>/following::element   
		 *      //*[@name='txtUsername']/following::input (does not goes to immediate child class)
		 *      //*[@name='txtUsername']/following::input[2]
		 *      //*[@name='txtUsername']/following::input[last()]
		 *       
		 * 12.  preceding element   
		 * 		//<knownxp>/preceding::element   
		 *      //*[@name='txtUsername']/preceding::input (does not goes to immediate child class)
		 *      //*[@name='txtUsername']/preceding::input[2]
		 *      //*[@name='txtUsername']/preceding::input[last()]  
		 *      
		 * 13.  Following - sibling  -- in same hierarchy    
		 *      //<knownxp>/following-sibling::element
		 *      //*[@name='txtUsername']/following-sibling::span
		 *      
		 * 14.  preceding - sibling  -- in same hierarchy    
		 *      //<knownxp>/preceding-sibling::element
		 *      //*[@class='form-hint']/preceding-sibling::input
		 *      
		 *      
		 */
		
		
		

	}

}

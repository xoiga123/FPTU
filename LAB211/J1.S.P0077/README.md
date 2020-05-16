
LAB211 Assignment	Type:	Short Assignment
	Code:	J1.S.P0077

	LOC:	100
	Slot(s):	2

Title 
  	Writing module to list and search file by content (CBDT project).
 Background 
NA
Program Specifications
Write the functions to list and search file by content including the following requirements:
1.	Count number of occurrences of a word in a file.
2.	Search file have content which contain inputted word.
3.	 Exit.
When the user chooses 1:
Require user enter a txt file include multiple rows. Enter a word and then count number of occurrences of this word in a file.
When the user chooses 2:
Require user enter in a folder’s path. Then, enter a word and then list all file name which contain this word


Function details: 
Function 1: Display a menu and ask users to select an option.
•	Users run the program. The program prompts users to select an option.
•	Users select an option, perform Function 2.
Function 2: Perform function based on the selected option..
•	Option 1: Counting words in a file.
o	Enter path of .text file
o	Enter a word to count
o	Output number of occurrences of a word in a file
•	Option 2: Search files which contain entered word.
o	Enter the folder path.
o	Enter a word to search
o	Output name of files which contain entered word.
•	Option 3: Exit the program.

Expectation of User interface: 
 




 


Guidelines

Student must implement methods
	countWordInFile
	getFileNameContainsWordInDirectory
in startup code.


Function 1: Counting words in file
o	Implement function: public int countWordInFile(String fileSource, String word) throws Exception
•	Input:
	fileSource: The folder’s path.
	word: Key words need search.
•	Output values:
	number of occurrences of a word in a file.
	List of exception.

Function 2: Search file follow the input
o	Implement function: public List<String> getFileNameContainsWordInDirectory(String source, String word) throws Exception
•	Input:
	Source: Folder’s path.
	Word: Key words need search.
•	Output value: 
	The list of found Files.
	List of exception.



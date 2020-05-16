
LAB211 Assignment	Type:	Short Assignment
	Code:	J1.S.P0079

	LOC:	130
	Slot(s):	2

Title 
  	Create a program to zip and unzip file (project CBDT)
 Background 
NA
Program Specifications
Create a program zip and unzip file with following menu
1)	Zip file.
2)	Unzip file.
3)	Exit.
-	When user chooses 1
o	Prompt user to input path to files need to be zipped
o	Prompt user to input zip file name
o	Perform zipping file in folder
-	When user chooses 2
o	Prompt user to input path to ne to be unzipped file
o	Prompt user to input path to destination unzipped files
o	Perform unzip task
-	When user chooses 3: exit program
 
Function details: 
Function 1: Display a menu and ask users to select an option.
•	Users run the program. The program prompts users to select an option.
•	Users select an option, perform Function 2.
Function 2: Perform function based on the selected option..
•	Option 1: zip file
o	Input path to need to be zipped files
o	Named zip file
o	Perform zip task
•	Option 2: Unzip file
o	Input path to be unzipped file
o	Input path to destination unzip files
o	Perform unzip task
•	Option 3: Exit program

Expectation of User interface: 

 

 

Guidelines

Student must implement methods
-	compressTo
-	extractTo
in startup code.
Example:
Student uses package java.util.zip to perform zip and unzip	
Function 1: zip file
o	Implement function: public static boolean compressTo(String pathSrc, String fileZipName, String pathCompress) 
•	Input:
	pathSrc: path to folder contain need to be zipped files.
	fileZipName: zip file name.
	pathCompress: Path to zip file.
•	Return: zipping file status.
Function 2: Unzip.
o	Implement function: public static boolean extractTo(String pathZipFile, String pathExtract)
•	Input:
	pathZipFile: Path to zipped file.
	pathExtract: path to destination folder contains unzipped file
•	Return value: unzip status.


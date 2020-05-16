
LAB211 Assignment	Type:	Short Assignment
	Code:	J1.S.P0070

	LOC:	150
	Slot(s):	3

Title 
  	Login system of the Tien Phong Bank’s Ebank
Background 
(Module extracted from TienPhong Bank, ebank project)
Program Specifications
Login function of Ebank system includes:
1. Vietnamese
2. English
3. Exit
If the user selects 1: Switch the interface language to Vietnamese and perform “check login” function.
If the user selects 2: Keep the English interface and perform “check login” function.
Check the Login function:
1.	Require to input an account number :
-	Check the account number must be a number 
-	Each account number must be 10 digits.
2.	Input a password: 
-	Check length of the password which is enough length or not
-	Check the password which includes alphanumeric or not.
3.	Require to input one character or multiple characters in the captcha:
-	Create a random captcha code in each login.
-	Check the input characters of captcha are correct or not.
 
Function details: 

Function 1: Display a menu and ask users to select an option.
•	User runs the program. The program prompts users to select an option.
•	User selects an option, perform Function 2.
Function 2: Perform language based on the selected option.
Function 1: Change language
•	Use the resourceBundle to get the value of the language according to the key in 2 files as En.properties and Vi.properties which are corresponding English and Vietnamese
.Function 2: Checking account number
•	Using Regular Expression to check the validity of the account number was input at the request of the assignment.
•	If the account number is invalid, it returns an error message corresponding to the language you chose.
Function 3: Check the password.
•	Use a RegularExpression to check the validation of the inputted password. 
•	If the password is invalid, then return an error message corresponding to the language you have just selected.
Function 4: Generate a random captcha code.
•	Use the Random function to generate a random sequence, and then converser to characters (type char) .
Function 5: Check the captcha code
•	Use the function contains () to check the captcha characters input exists in the captcha string was born at first did not.
•	If the captcha characters are invalid, then returns the error message that corresponds to the language you have selected.
Function 6: Login
•	Allows the user to input the account number, password and captcha from the keyboard.
•	If a valid account number, then allows the user to input a password and if the account number entered is not valid, then print out the error screen and allows the user to input again.
•	If valid password then allows users to input captcha. Otherwise then print out the error screen and allows the user to input again.
•	If the captcha is not valid then print out the error screen.





Expectation of User interface:

 
Guidelines

Student must implement methods
setLocate 
checkAccountNumber
checkPassword
in startup code.
- Create a Main class to display the selected language in the Menu.
- Create the Ebank class including the following functions: 
Function 1: Convert the languages.
o	The program will switches from this language to another one, so that the result is the elements is defined in the Language.properties file will convert to another one.
o	Implement the function:  void setLocate(Locate locate).
•	Input :
	locate: name of properties file which needs to be converted.
Function 2: Check the account number.
o	The program checks an account number, so that it must be one number as the defined requirements.
o	Implement the function: String checkAccountNumber (String accountNumber)
•	Input :
	accountNumber: account number needs to be checked.
•	Return value: messages about the value of the account number.
Function 3: Check the password.
o	The program checks the password, so that it must assure the defined requirements.
o	 Implement the function: checkPassword String (String password)
•	Input :
	password: the password needs to be checked .
•	The value returns: messages about the value of the password.
Functions 4: Generate a random captcha code.
o	The program generates a random captcha code, so that the it must assure the defined requirements.
o	Implement the function: String generateCaptcha () 
•	Return value: a random captcha string.
Function 5: Check the captcha code.
o	The program checks the captcha code, so that the result is a captcha string which must assure the defined requirements.
•	Implement the function: String checkCaptcha (String captchaInput, String captchaGenerate)
•	Input :
	captchaInput: Captcha is inputted
	captchaGenerate: Captcha is generated
•	The value returns: The message about the value of the captcha.


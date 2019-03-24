# KeaRatingApp - Mandatory Assignment 1 
_Courses: Android App._  
_Semester: 2019_  
This is my description of how i have followed the requirements for the mandatory assignment
## Requirements
* I have made a design that is very linear and should be easy to understand. I would have wanted to make the teachers change depending on what subject was chosen in the above spinner, but did not get the time. 
* I have made three activities. MainActivity was the non authorized section where the user should log in. Second is SelectActivity which is where the user will be chosing his semester, subject and teacher. Third is Subject which is where the rating of the teacher will happen.
* In my app it is possible to rate all courses on 1 to 4th semester. A listener will make sure that only specific subjects can be chosen depending on what semester the user selects in the semester spinner.
* At the end of the rating there will be a button that calls a method from the SendEmail class, which takes care of sending the message to the mail. I have not made it so that each teacher has an email adress. If a database was implemented the teachers could have an email adress
* On all three activities the data will be validated, but only on the first activity it makes sense to validate since the user can write a wrong username or password. Then a toast will tell the user that either was incorrect.
* I have used Parceable to get the ParseClass object that is created in the SelectActivity and transferred via Intent to the next activity.
* My new feature was to make listener for spinners to make them more dynamic.


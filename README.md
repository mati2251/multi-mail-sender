# Multi mail sender
## About app 
**Multi mail sender** is simple app to send many mails to many addresses.  
App need two file csv and txt. In first **csv** necessary column is **mail**. In this column you can define address mail to which you send a message  
You can define another column. If you do it app get column name and search it on your content mail. If app found that key words App will replace it with an element from the column  
Second necessary file is **content** file. You should inside this file place your email content  
App need yet **title** mail  
Properties that app:  
**-h**   display help  
**-p**   path to properties file. You can define your properties file with sender data for example: " mail.smtp.host "  
**-v**   path to csv file with variable  
****-t**   title your mail  
-c**   path to content file  
## Download
[Download Link](https://github.com/mati2251/multi-mail-sender/releases)
## Compile
**1. Clone project**  
`https://github.com/mati2251/simple-authentication`  
**2. Run Gradle**  
`gradle`
**3. Define file src/main/resources/mail.properties example below**
**4. Run Gradle Jar**  
`gradle jar`
**5.  Jar file is in build/libs**  
**6. Use and have fun :) example of usage below**
## CSV and TXT file example
**If Your variables look like**

mail | product | name 
--- | --- | --- 
example1@example.com | apple | Martin
example2@example.com | orange | Nick
example3@example.com | banana | Mark
**Your CSV file should look**
```csv
mail,product,name
example1@example.com,orange,Ncik
example2@example.com,apple,Martin
example3@example.com,pie,Nick
```
**Example content txt file**
```text
Hello
I will buy product
Your 
name
```
## Example of Properties File
```properties
mail = your email
password = your password
mail.smtp.host = your mail host
mail.smtp.port = smtp port
mail.smtp.auth = true
mail.smtp.socketFactory.port = smtp port
mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
```
## Example of usage
If you use example files from above you can run app with command
```
java -jar email-bot.jar -v pathToCSVFile -c pathToTXTFile -t "Title" -p pathToPropertiesFile
```
App send 3 mails:
First mail will send to **example1@example.com** and looks like
```text
Hello
I will buy apple 
Your 
Martin
```
Second mail will send to **example2@example.com** and looks like
```text
Hello
I will buy orange 
Your 
Nick
```
Third mail will send to **example3@example.com** and looks like
```text
Hello
I will buy banana 
Your 
Mark
```
## License
GNU General Public License v3.0
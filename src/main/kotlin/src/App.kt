package src

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File


fun main(args: Array<String>) {
    var help = false
    var contentFileName: String? = null
    var title: String? = null
    var csvFileName: String? = null
    var propertiesFileName: String = "src/main/resources/mail.properties"
    for (i in 0 until (args.size)) {
        when {
            args[i] == "-h" -> help = true
            args[i] == "-p" -> propertiesFileName = args[i + 1]
            args[i] == "-v" -> csvFileName = args[i + 1]
            args[i] == "-t" -> title = args[i + 1]
            args[i] == "-c" -> contentFileName = args[i + 1]
        }
    }
    if (help || (csvFileName.isNullOrEmpty() && title.isNullOrEmpty() && contentFileName.isNullOrEmpty())) {
        printHelp()
    } else{
        val content = File(contentFileName).readText()
        csvReader().open(csvFileName!!){
            readAllWithHeader().forEach{ row ->
                var mail = ""
                var tmpContent = content
                row.forEach{ item ->
                    if(item.key == "mail"){
                        mail = item.value
                    }
                    else{
                        tmpContent = tmpContent.replace(item.key, item.value)
                    }
                }
                if(mail == ""){
                    println("Column Mail is not define or item in column is empty")
                }
                else{
                    MailOperation.sendMail(mail, title!!, tmpContent, propertiesFileName)
                }
            }
        }
    }
}

fun printHelp() {
    println("Multi mail sender is simple app to send many mails to many addresses.")
    println("App need two file csv and txt. In first csv necessary column is mail. In this column you can define address mail to which you send a message")
    println("You can define another column. If you do it app get column name and search it on your content mail. If app found that key words App will replace it with an element from the column")
    println("Second necessary file is content file. You should inside this file place your email content")
    println("App need yet title mail")
    println("Properties that app:")
    println("-h   display help")
    println("-p   path to properties file. You can define your properties file with sender data for example: \" mail.smtp.host \". Details: https://github.com/mati2251/multi-mail-sender")
    println("-v   path to csv file with variable")
    println("-t   title your mail")
    println("-c   path to content file")
}

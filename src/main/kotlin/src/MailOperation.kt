package src

import java.io.FileInputStream
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


object MailOperation {

    fun sendMail(to: String, title: String, text: String) {
        // You must create src/main/resources/mail.properties
        sendMail(to, title, text, "src/main/resources/mail.properties")
    }

    fun sendMail(to: String, title: String, text: String, propsFileName: String) {
        val props = Properties()
        FileInputStream(propsFileName).use { input ->
            props.load(input)
        }
        val username = props["mail"].toString()
        val password = props["password"].toString()
        val session = Session.getInstance(props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(username, password)
                }
            })
        try {
            val message: Message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
            )
            message.subject = title
            message.setText(text)
            Transport.send(message)
            println("MAIL SEND TO $to")
        } catch (e: MessagingException) {
            throw e
        }
    }
}
package com.mortazacorp.secretly;

import com.mortazacorp.secretly.databaseUtil.SecretMessageRepository;
import com.mortazacorp.secretly.databaseUtil.UserRepository;
import com.mortazacorp.secretly.models.SecretMessage;
import com.mortazacorp.secretly.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SecretlyApplication {

    String str = "সময়ের প্রেক্ষিতে কিছু বিশেষ চরণ আর্বিভূত হয় হৃদয় গহীনে। +\n" +
            "    'ক্ষুদ্র প্রয়াসে ব্যাকুল হৃদয়ে উচ্ছ্বাস ওঠে ক্ষুদ্র সৃষ্টিতে। \n" +
            "    ' যেখানে নগন্য-তুচ্ছু আমি- ওই হৃদয়ে অবস্থান সাধ্যাতীত। তবু স্বপ্ন নিয়ে এসেছি|";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecretMessageRepository messageRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecretlyApplication.class, args);
    }
}

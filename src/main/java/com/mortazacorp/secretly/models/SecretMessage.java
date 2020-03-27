package com.mortazacorp.secretly.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "secret_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecretMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String toUserName;

    @Column(length = 50000)
    private String message;

    @Column(updatable = false)
    private int backgroundType;

    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Date timestamp;
}

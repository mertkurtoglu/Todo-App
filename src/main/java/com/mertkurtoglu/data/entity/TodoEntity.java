package com.mertkurtoglu.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "todo")
public class TodoEntity extends BaseEntity implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date systemDate;

    @Column(name = "task", columnDefinition = "varchar(255) default 'task is empty'")
    private String task;
}

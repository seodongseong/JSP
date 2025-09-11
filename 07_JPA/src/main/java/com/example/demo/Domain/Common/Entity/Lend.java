package com.example.demo.Domain.Common.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="lend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동증가
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="username",
            foreignKey = @ForeignKey(
                    name="FK_LEND_USER",
                    foreignKeyDefinition = "FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE ON UPDATE CASCADE"

            )
    )
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name="bookCode",
            foreignKey = @ForeignKey(
                    name="FK_LEND_BOOKCODE",
                    foreignKeyDefinition = "FOREIGN KEY (bookCode) REFERENCES book(bookCode) ON DELETE CASCADE ON UPDATE CASCADE"
            )

    )
    private Book book;


}

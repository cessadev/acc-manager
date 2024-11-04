package com.cessadev.technical_test_java_spring.model;

import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private String ownerName;

    @Enumerated(EnumType.STRING)
    private EStatusAccount status = EStatusAccount.ACTIVE;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountModel that = (AccountModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", ownerName='" + ownerName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

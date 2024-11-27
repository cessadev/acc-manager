package com.cessadev.technical_test_java_spring.model;

import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
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
@Table(name = "transaction")
public class TransactionModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Enumerated(EnumType.STRING)
  private ETypeTransaction typeTransaction;

  @Column(nullable = false, precision = 19, scale = 4)
  private BigDecimal amount;

  /* References to the AccountModel entity to maintain referential integrity */
  @ManyToOne
  @JoinColumn(name = "account_origin_id", referencedColumnName = "id")
  private AccountModel accountOrigin;

  /* References to the AccountModel entity to maintain referential integrity */
  @ManyToOne
  @JoinColumn(name = "account_destination_id", referencedColumnName = "id")
  private AccountModel accountDestination;

  @Column(updatable = false)
  private LocalDateTime date;

  @PrePersist
  protected void onCreate() {
    date = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TransactionModel that)) return false;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "TransactionModel{" +
            "id=" + id +
            ", typeTransaction=" + typeTransaction +
            ", amount=" + amount +
            ", accountOrigin='" + accountOrigin + '\'' +
            ", accountDestination='" + accountDestination + '\'' +
            ", date=" + date +
            '}';
  }
}

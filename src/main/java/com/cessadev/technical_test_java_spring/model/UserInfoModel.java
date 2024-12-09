package com.cessadev.technical_test_java_spring.model;

import com.cessadev.technical_test_java_spring.model.enums.EEmploymentStatus;
import com.cessadev.technical_test_java_spring.model.enums.EGender;
import com.cessadev.technical_test_java_spring.model.enums.EMaritalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  private String middleName;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  private EGender gender;

  @Column(nullable = false)
  private String phoneNumber;

  private String alternatePhoneNumber;

  // private String email;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String postalCode;

  @Column(nullable = false)
  private String country;

  @Enumerated(EnumType.STRING)
  private EEmploymentStatus employmentStatus;

  private String jobTitle;

  private String companyName;

  private String industry;

  private BigDecimal annualIncome;

  private Integer creditScore;

  private String preferredCurrency;

  @Enumerated(EnumType.STRING)
  private EMaritalStatus maritalStatus;

  private Integer numberOfDependents;

  private String profilePictureUrl;

  private Boolean verified = true; // Future functionality

  @Column(updatable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
  }

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserInfoModel that = (UserInfoModel) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(middleName, that.middleName) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            gender == that.gender &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(alternatePhoneNumber, that.alternatePhoneNumber) &&
            Objects.equals(address, that.address) &&
            Objects.equals(city, that.city) &&
            Objects.equals(state, that.state) &&
            Objects.equals(postalCode, that.postalCode) &&
            Objects.equals(country, that.country) &&
            employmentStatus == that.employmentStatus &&
            Objects.equals(jobTitle, that.jobTitle) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(industry, that.industry) &&
            Objects.equals(annualIncome, that.annualIncome) &&
            Objects.equals(creditScore, that.creditScore) &&
            Objects.equals(preferredCurrency, that.preferredCurrency) &&
            maritalStatus == that.maritalStatus &&
            Objects.equals(numberOfDependents, that.numberOfDependents) &&
            Objects.equals(profilePictureUrl, that.profilePictureUrl) &&
            Objects.equals(user, that.user) &&
            Objects.equals(verified, that.verified) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            id,
            firstName,
            lastName,
            middleName,
            dateOfBirth,
            gender,
            phoneNumber,
            alternatePhoneNumber,
            address,
            city,
            state,
            postalCode,
            country,
            employmentStatus,
            jobTitle,
            companyName,
            industry,
            annualIncome,
            creditScore,
            preferredCurrency,
            maritalStatus,
            numberOfDependents,
            profilePictureUrl,
            user,
            verified,
            createdAt,
            updatedAt);
  }

  @Override
  public String toString() {
    return "UserInfoModel{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", gender=" + gender +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", alternatePhoneNumber='" + alternatePhoneNumber + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", postalCode='" + postalCode + '\'' +
            ", country='" + country + '\'' +
            ", employmentStatus=" + employmentStatus +
            ", jobTitle='" + jobTitle + '\'' +
            ", companyName='" + companyName + '\'' +
            ", industry='" + industry + '\'' +
            ", annualIncome=" + annualIncome +
            ", creditScore=" + creditScore +
            ", preferredCurrency='" + preferredCurrency + '\'' +
            ", maritalStatus=" + maritalStatus +
            ", numberOfDependents=" + numberOfDependents +
            ", profilePictureUrl='" + profilePictureUrl + '\'' +
            ", user=" + user +
            ", verified=" + verified +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
  }
}

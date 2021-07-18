package com.yapikredi.annualleave.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = Employee.FIND_BY_IDENTITY, query = "SELECT e FROM Employee e WHERE e.identity=:identity")
})
public class Employee {

    public static final String FIND_BY_IDENTITY = "Employee.FIND_BY_IDENTITY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTITY", referencedColumnName = "IDENTITY")
    private AnnualLeave identity;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime started;

}

package com.yapikredi.annualleave.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = AnnualLeave.FIND_BY_IDENTITY, query = "SELECT a FROM AnnualLeave a WHERE a.identity=:identity")
})
public class AnnualLeave implements Serializable {

    public static final String FIND_BY_IDENTITY = "AnnualLeave.FIND_BY_IDENTITY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long identity;

    private Long annualLeaveUsed;

    private Long totalLeaveUsed;

}

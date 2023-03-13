package com.ey.cl.springboot.rest.usercreation.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "EY_SCHEMA")
public final class UserInfo
        implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    private List<UserPhone> phones = new ArrayList<>();

    @Builder.Default
    @Column
    private Date created = new Date();
    @Column
    private Date modified;
    @Builder.Default
    @Column
    private Date last_login = new Date();
    @Builder.Default
    @Column
    private String token = UUID.randomUUID().toString();
    @Builder.Default
    @Column
    private Boolean isactive = true;
}

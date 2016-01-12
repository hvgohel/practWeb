package com.dw.practWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.dw.practWeb.listener.RegistrationListener;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Entity
@Table(name = "registration")
@JsonSerialize(include = Inclusion.NON_NULL)
@EntityListeners(RegistrationListener.class)
public class Registration extends BaseModel
{
    @Column(name = "`username`")
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public static enum Role
    {
        ADMIN, USER;

        public String getAuthority()
        {
            return "ROLE_" + name();
        }
    }
}

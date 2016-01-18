package com.dw.practWeb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public class Base<K extends Comparable> implements Serializable, Comparable<Object>
{
    @Id
    private K    id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public K getId()
    {
        return id;
    }

    public void setId(K id)
    {
        this.id = id;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    @PrePersist
    public void setEntityId()
    {
        this.created = new Date();
        this.id = (K) UUID.randomUUID().toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Base<?> other = (Base<?>) obj;

        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(Object o)
    {
        Base<K> baseModel = (Base<K>) o;

        return this.getId().compareTo(baseModel.getId());
    }
}

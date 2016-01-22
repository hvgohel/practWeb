package com.dw.practWeb.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;

import com.dw.practWeb.paging.PagedResult;

public interface BeanMapper
{
    public <T> T map(Object source, Class<T> destinationClass);

    public <T> T map(Object source, Class<T> destinationClass, String contextId);

    public void map(Object source, Object destination);

    public void map(Object source, Object destination, String contextId);

    public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName);

    public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName, String mapId);

    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName);

    public <T, D> PagedResult<D> mapCollection(Page<T> page, Class<D> classDTOName, String mapId);
}

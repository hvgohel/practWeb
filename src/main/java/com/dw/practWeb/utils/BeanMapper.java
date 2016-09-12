package com.dw.practWeb.utils;

import com.dw.practWeb.paging.PagedResult;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface BeanMapper {
  public <T> T map(Object source, Class<T> destinationClass);

  public <T> T map(Object source, Class<T> destinationClass, String contextId);

  public void map(Object source, Object destination);

  public void map(Object source, Object destination, String contextId);

  public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName);

  public <T, D> PagedResult<T> mapCollection(Page<T> page, Class<T> class1);

  public <T, D> List<D> mapCollection(Collection<T> list, Class<D> classDTOName, String mapId);
}

package com.dw.practWeb.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.loader.custom.Return;
import org.springframework.data.jpa.domain.Specification;

import com.dw.practWeb.model.Student;
import com.dw.practWeb.model.Student_;

public class StudentSpecification {
  public static Specification<Student> getByCity(final String city) {
    return new Specification<Student>() {
      @Override
      public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
          CriteriaBuilder builder) {
        Predicate predicate = builder.conjunction();

        if (StringUtils.isNotBlank(city)) {
          predicate.getExpressions().add(builder.equal(root.get(Student_.city), city));
        }

        return predicate;
      }
    };
  }

  public static Specification<Student> getByName(final String name) {
    return new Specification<Student>() {
      @Override
      public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
          CriteriaBuilder builder) {
        Predicate predicate = builder.conjunction();

        if (StringUtils.isNotBlank(name)) {
          predicate.getExpressions().add(builder.like(root.get(Student_.name), "%" + name + "%"));
        }

        return predicate;
      }
    };
  }
}

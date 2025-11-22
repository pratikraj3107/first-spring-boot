package com.pratik.first.spring.boot.repositoryimplementation;

import com.pratik.first.spring.boot.exception.EmployeeException;
import com.pratik.first.spring.boot.repository.CustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext    // we could have used @Autowired also but @PersistenceContext is more specific to JPA and is the standard way to inject an EntityManager in a JPA context.
    private EntityManager entityManager;

    @Override
    public <T> List<T> getEntityWithCustomCriteria(Map<String, Object> criteria, Class<T> classType) {

//         What is CriteriaBuilder?
//                It is a JPA helper object.
//                It helps you programmatically build SQL-like queries.
//                Think of it as a factory that creates query parts:
//                      predicates (WHERE conditions)
//                      ordering
//                      expressions
//                      functions (like count, max, like)
//
//        Why do we get it from entityManager?
//                Because the EntityManager knows the database and entities tied to it, so it gives you the correct CriteriaBuilder for this persistence context.
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(classType);
//         2. CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
//✔ What is CriteriaQuery<Employee>?
//        It represents a full SQL SELECT query that will return Employee objects.
//                It’s like saying:
//“I want to prepare a SELECT query that returns rows mapped to Employee class.”
//        Why pass Employee.class?
//                Because Criteria API is strongly typed.
//                You're creating a query whose result type is Employee.
//   Putting it together
//        These two lines mean:
//        “Give me a builder, so I can construct a query,
//        and now create a SELECT query that will return Employee objects.”

        Root<T> root = query.from(classType);
//        Root<T> represents the main table (entity) in your SQL query.
        List<Predicate> predicates = new ArrayList<>();
        criteria.forEach((key, value) -> {
            try {
                root.get(key);
                predicates.add(cb.equal(root.get(key), value));
            } catch (Exception e) {
                throw new EmployeeException("Error processing criteria for key: " + key, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
//        converting list to array and passing to cb.and to combine all conditions with AND logic, as "and" method takes varargs
        query.where(finalPredicate);
        return entityManager.createQuery(query).getResultList();
    }

}

package az.spring.hibernate.dao.impl;

import az.spring.hibernate.dao.PersonDao;
import az.spring.hibernate.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDaoImpl extends AbstractSessionFactory implements PersonDao {


    @Transactional
    @Override
    public void save(Person person) {
            getSession().save(person);
    }

    @Override
    public void update(Person person) {
        getSession().update(person);
    }

    @Override
    public void delete(Integer id) {
        Person person = findById(id)
                .orElseThrow(RuntimeException::new);
        getSession().delete(person);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(getSession().get(Person.class,id));
    }

    @Transactional
    @Override
    public List<Person> findAll() {
        return getSession().createQuery("select p from Person p").list();
    }


}

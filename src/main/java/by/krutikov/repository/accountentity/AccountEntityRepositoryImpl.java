package by.krutikov.repository.accountentity;

import by.krutikov.hibernateentity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class AccountEntityRepositoryImpl implements AccountEntityRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public AccountEntity findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager.createQuery("select ae from AccountEntity ae where ae.id=:id", AccountEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Optional<AccountEntity> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<AccountEntity> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select ae from AccountEntity ae", AccountEntity.class)
                .getResultList();
    }

    @Override
    public List<AccountEntity> findAll(int offset, int limit) {
        return null;
    }

    @Override
    public AccountEntity create(AccountEntity object) {
        return null;
    }

    @Override
    public AccountEntity update(AccountEntity object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}

package com.example.commerce.repository;

import com.example.commerce.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }


    @Override
    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    public interface MemberRepository<Member> extends JpaRepository<Member,String> {
        List<NameOnly> findNamesByNameNotNull();
    }

    @Override
    public void clearstore() {

    }
}

package com.example.commerce.repository;

import com.example.commerce.domain.Member;

import java.util.*;

public class TMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id){
        return store.get(id);
    }

    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearstore(){store.clear();}


}

package com.library.management.repository;
import com.library.management.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member,Long>{Optional<Member> findByEmail(String email);}

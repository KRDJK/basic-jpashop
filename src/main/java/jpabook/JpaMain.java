package jpabook;

import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // em을 하나 만들면 db 커넥션을 하나 얻었다고 생각하면 된다.
        EntityManager em = emf.createEntityManager(); // EntityManager 안에는 영속성 컨텍스트가 1:1로 들어있다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // code
        try {

            tx.commit(); // 커밋하는 시점에 관리하던 객체의 변동사항을 체크 (변경감지) 후 변동이 있다면 update 문을 날린다.
            // 엔티티와 스냅샷을 비교 (변경 감지)
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 성공이거나 실패거나 얘는 닫아줘야 함. db 커넥션 자체는 끊어줘야지!
        }
        emf.close();
    }
}

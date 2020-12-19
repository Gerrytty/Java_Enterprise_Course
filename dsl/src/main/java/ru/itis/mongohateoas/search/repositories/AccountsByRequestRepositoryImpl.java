//package ru.itis.mongohateoas.search.repositories;
//
//import com.querydsl.core.BooleanBuilder;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//import ru.itis.mongohateoas.search.dto.AirplaneDto;
//import ru.itis.mongohateoas.search.dto.AirplaneRequest;
//
////import static ru.itis.mongohateoas.models.QAirplane.airplane;
//
//@Repository
//public class AccountsByRequestRepositoryImpl implements AccountsByRequestRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<AirplaneDto> findByRequest(AirplaneRequest airplaneRequest) {
//
//        BooleanBuilder predicate = new BooleanBuilder();
//
//        if (airplaneRequest.getCost_mln_dollars() != null) {
////            predicate.or();
//        }
//
//        if (airplaneRequest.getNumber_of_seats() != null) {
//
//        }
//
//        return null;
//    }
//
//}

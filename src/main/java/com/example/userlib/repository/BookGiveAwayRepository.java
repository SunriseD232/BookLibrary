package com.example.userlib.repository;

import com.example.userlib.implementation.GiveAway.BookGivenAwayImpl;
import com.example.userlib.implementation.User.UserImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookGiveAwayRepository extends
    JpaRepository<BookGivenAwayImpl, Long> {

  List<BookGivenAwayImpl> findByUser(UserImpl user);

  BookGivenAwayImpl findBookGivenAwayById(Long bookGivenAwayId);

  //  Если используем аннотацию @Entity для определения сущности BookGivenAwayImpl,
  //  то имя сущности должно соответствовать имени класса, а не названию таблицы.
  @Query("SELECT b FROM BookGivenAwayImpl b WHERE b.returnDate < :now")
  List<BookGivenAwayImpl> findAllWhereReturnDateMore(@Param("now") LocalDate now);
}

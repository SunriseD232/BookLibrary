package com.example.userlib.Repository;

import com.example.userlib.Impl.GiveAway.BookGivenAwayImpl;
import com.example.userlib.Impl.User.UserImpl;
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

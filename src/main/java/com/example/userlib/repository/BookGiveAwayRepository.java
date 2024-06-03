package com.example.userlib.Repository;

import com.example.userlib.Impl.GiveAway.BookGivenAway;
import com.example.userlib.Impl.User.UserImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookGiveAwayRepository extends
    JpaRepository<BookGivenAway, Long> {

  List<BookGivenAway> findByUser(UserImpl user);

  BookGivenAway findBookGivenAwayById(Long bookGivenAwayId);

  //  Если используем аннотацию @Entity для определения сущности BookGivenAwayImpl,
  //  то имя сущности должно соответствовать имени класса, а не названию таблицы.
  @Query("SELECT b FROM BookGivenAway b WHERE b.returnDate < :now")
  List<BookGivenAway> findAllWhereReturnDateMore(@Param("now") LocalDate now);
}

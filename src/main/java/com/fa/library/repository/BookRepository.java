package com.fa.library.repository;

import com.fa.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from Book b where b.title like :keyword or b.publisher like :keyword " +
            "or b.student_name like :keyword", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
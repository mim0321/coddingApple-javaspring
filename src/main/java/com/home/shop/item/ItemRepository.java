package com.home.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/** 1.리포지토리(인터페이스) 만들기
 *  - 파일명 관습 : 입출력할 Entity name + Repository >> ItemRepository
 *  - public interface 만들기
 *  - <a, b> : a == 입출력할 Entity, b == Entity id column type
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findPageBy(Pageable page);
    List<Item> findAllByTitleContains(String title);

    @Query(value = "select * from item where match(title) against(?1)", nativeQuery = true)
    List<Item> rawQuery1(String text);

    @Query("SELECT i FROM Item i WHERE i.title LIKE %:searchText% OR i.description LIKE %:searchText%")
    Page<Item> searchWithPagination(String searchText, Pageable pageable);

}
